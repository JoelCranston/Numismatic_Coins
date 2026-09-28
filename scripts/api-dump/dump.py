#!/usr/bin/env python3
"""Dump the game and loader API shapes the implementation plan depends on.

Run by .github/workflows/api-dump.yml after a Gradle build has filled the dependency cache. It
downloads the vanilla client jars, finds the Fabric API and NeoForge jars in ~/.gradle, and writes
into api-dump/:

  mc-<ver>/data/...          vanilla data files whose path mentions trades, plus bundle tags
  mc-<ver>/items/...         item model definitions that use range_dispatch
  mc-<ver>/src/...           decompiled vanilla classes (Vineflower)
  mc-<ver>/sigs/*.txt        javap -public signatures
  mc-<ver>/data-dirs.txt     every data/minecraft/<dir> with a file count
  mc-diff-classes.txt        classes added or removed between 26.1.2 and 26.2, in packages we use
  fabric-<ver>/sigs/*.txt    javap of the Fabric API packages we use
  fabric-<ver>/src/...       decompiled Fabric API classes
  neoforge/sigs, neoforge/src  the same for NeoForge

The output is meant for a human (or Claude) writing docs/api-notes.md; it is not committed to main.
"""

import json
import os
import re
import shutil
import subprocess
import sys
import urllib.request
import zipfile
from pathlib import Path

OUT = Path("api-dump")
WORK = Path("build/api-dump-work")
GRADLE = Path.home() / ".gradle" / "caches"
MANIFEST = "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json"
VINEFLOWER = "https://repo1.maven.org/maven2/org/vineflower/vineflower/1.11.1/vineflower-1.11.1.jar"

MC_VERSIONS = ["26.1.2", "26.2"]
FABRIC_API = {"26.1.2": "0.146.1+26.1.2", "26.2": "0.161.0+26.2"}

# Vanilla classes to decompile in full (regex on the class path, without .class).
MC_DECOMPILE = [
	r"net/minecraft/world/item/trading/.*",
	r".*/[A-Za-z]*VillagerTrade[A-Za-z]*",
	r".*/TradeSet[A-Za-z]*",
	r".*/[A-Za-z]*Trades?",
	r"net/minecraft/world/entity/npc/villager/(Villager|AbstractVillager|VillagerProfession|VillagerData)",
	r"net/minecraft/world/entity/npc/wanderingtrader/WanderingTrader",
	r"net/minecraft/world/entity/npc/WanderingTrader",
	r"net/minecraft/world/inventory/(MerchantMenu|MerchantContainer|MerchantResultSlot)",
	r"net/minecraft/world/level/gamerules/.*",
	r"net/minecraft/client/renderer/item/properties/numeric/(Count|RangeSelectItemModelProperty|RangeSelectItemModelProperties)",
	r"net/minecraft/client/renderer/item/RangeSelectItemModel",
	r"net/minecraft/world/item/BundleItem",
	r"net/minecraft/world/item/component/BundleContents",
	r"net/minecraft/world/item/[A-Za-z]*Ids",
	r"net/minecraft/world/level/block/[A-Za-z]*Ids",
	r"net/minecraft/core/registries/Registries",
	r"net/minecraft/client/gui/screens/inventory/(InventoryScreen|CreativeModeInventoryScreen|MerchantScreen)",
]

# Vanilla classes to show as javap -public signatures only.
MC_SIGS = [
	r"net/minecraft/world/item/(Item|Items|Item\$Properties|CreativeModeTab|CreativeModeTab\$Builder|CreativeModeTabs|ItemStack)",
	r"net/minecraft/world/level/block/(Block|Blocks)",
	r"net/minecraft/core/component/(DataComponents|DataComponentType|DataComponentType\$Builder)",
	r"net/minecraft/client/Minecraft",
	r"net/minecraft/client/gui/Gui",
	r"net/minecraft/server/level/ServerPlayer",
	r"net/minecraft/world/level/storage/loot/functions/[A-Za-z]*",
	r"net/minecraft/world/level/storage/loot/entries/(LootPoolEntries|LootPoolEntryType|LootPoolEntryContainer|LootPoolSingletonContainer)",
	r"net/minecraft/world/inventory/(MenuType|AbstractContainerMenu)",
	r"net/minecraft/client/gui/screens/inventory/AbstractContainerScreen",
	r"net/minecraft/client/gui/screens/MenuScreens",
	r"net/minecraft/world/level/block/entity/BlockEntityType",
	r"net/minecraft/network/protocol/common/custom/CustomPacketPayload",
	r"net/minecraft/commands/(Commands|CommandSourceStack)",
	r"net/minecraft/server/packs/repository/(Pack|PackSource|RepositorySource)",
	r"net/minecraft/world/entity/npc/villager/VillagerProfession",
]

# Packages whose added/removed classes between the two MC versions are worth seeing.
MC_DIFF_PACKAGES = [
	"net/minecraft/world/item/", "net/minecraft/world/level/block/", "net/minecraft/core/",
	"net/minecraft/client/gui/", "net/minecraft/world/inventory/", "net/minecraft/world/entity/npc/",
	"net/minecraft/world/level/gamerules/", "net/minecraft/client/renderer/item/",
	"net/minecraft/world/level/storage/loot/", "net/minecraft/server/packs/",
]

FABRIC_SIG_PACKAGES = [
	"net/fabricmc/fabric/api/attachment/", "net/fabricmc/fabric/api/gamerule/",
	"net/fabricmc/fabric/api/resource/", "net/fabricmc/fabric/api/loot/",
	"net/fabricmc/fabric/api/networking/v1/", "net/fabricmc/fabric/api/client/networking/v1/",
	"net/fabricmc/fabric/api/client/rendering/v1/", "net/fabricmc/fabric/api/itemgroup/",
	"net/fabricmc/fabric/api/screenhandler/", "net/fabricmc/fabric/api/command/",
	"net/fabricmc/fabric/api/client/screen/", "net/fabricmc/fabric/api/entity/event/",
	"net/fabricmc/fabric/api/event/player/", "net/fabricmc/fabric/api/object/builder/",
	"net/fabricmc/fabric/api/item/v1/", "net/fabricmc/fabric/api/registry/",
	"net/fabricmc/fabric/api/client/rendering/", "net/fabricmc/fabric/api/menu/",
	"net/fabricmc/fabric/api/client/gui/", "net/fabricmc/fabric/api/tag/",
]
FABRIC_DECOMPILE = [
	r"net/fabricmc/fabric/api/attachment/.*",
	r"net/fabricmc/fabric/api/gamerule/.*",
	r"net/fabricmc/fabric/api/resource/v1/.*",
	r"net/fabricmc/fabric/api/client/rendering/v1/hud/.*",
	r"net/fabricmc/fabric/api/itemgroup/v1/FabricItemGroup",
]

NEOFORGE_SIG_PACKAGES = [
	"net/neoforged/neoforge/attachment/", "net/neoforged/neoforge/registries/",
	"net/neoforged/neoforge/common/loot/", "net/neoforged/neoforge/network/",
	"net/neoforged/neoforge/client/network/", "net/neoforged/neoforge/client/gui/",
	"net/neoforged/neoforge/event/village/", "net/neoforged/neoforge/common/extensions/IMenuTypeExtension",
	"net/neoforged/neoforge/event/AddPackFindersEvent", "net/neoforged/neoforge/event/AddServerReloadListenersEvent",
	"net/neoforged/neoforge/event/RegisterCommandsEvent", "net/neoforged/neoforge/event/entity/player/PlayerEvent",
	"net/neoforged/neoforge/client/event/RegisterGuiLayersEvent", "net/neoforged/neoforge/client/event/RegisterMenuScreensEvent",
	"net/neoforged/neoforge/client/event/ScreenEvent", "net/neoforged/neoforge/client/event/ContainerScreenEvent",
	"net/neoforged/neoforge/event/BuildCreativeModeTabContentsEvent", "net/neoforged/neoforge/common/NeoForgeMod",
	"net/neoforged/neoforge/event/entity/player/ItemEntityPickupEvent", "net/neoforged/neoforge/event/entity/living/LivingDropsEvent",
	"net/neoforged/neoforge/event/entity/player/PlayerInteractEvent",
]
NEOFORGE_DECOMPILE = [
	r"net/neoforged/neoforge/attachment/(AttachmentType|IAttachmentHolder|AttachmentHolder|IAttachmentCopyHandler|AttachmentSync|IAttachmentSyncHandler)",
	r"net/neoforged/neoforge/common/loot/(IGlobalLootModifier|LootModifier|AddTableLootModifier)",
	r"net/neoforged/neoforge/event/village/.*",
	r"net/neoforged/neoforge/client/gui/(VanillaGuiLayers|IConfigScreenFactory)",
	r".*GameRule.*",
]


def log(msg):
	print(msg, flush=True)


def fetch(url, dest):
	dest.parent.mkdir(parents=True, exist_ok=True)
	if not dest.exists():
		log(f"download {url}")
		with urllib.request.urlopen(url) as r, open(dest, "wb") as f:
			shutil.copyfileobj(r, f)
	return dest


def mc_client_jar(version):
	manifest = json.load(urllib.request.urlopen(MANIFEST))
	entry = next((v for v in manifest["versions"] if v["id"] == version), None)
	if entry is None:
		log(f"!! {version} not in the version manifest")
		return None
	meta = json.load(urllib.request.urlopen(entry["url"]))
	return fetch(meta["downloads"]["client"]["url"], WORK / f"minecraft-{version}-client.jar")


def class_names(jar):
	with zipfile.ZipFile(jar) as z:
		return [n[:-6] for n in z.namelist() if n.endswith(".class")]


def matching(names, patterns):
	compiled = [re.compile(p + r"(\$.*)?$") for p in patterns]
	return sorted(n for n in names if any(c.fullmatch(n) for c in compiled))


def decompile(jar, classes, out_dir, label):
	"""Extracts the classes (with their inner classes) and runs Vineflower over them."""
	if not classes:
		log(f"!! {label}: nothing to decompile")
		return
	stage = WORK / "stage" / label
	shutil.rmtree(stage, ignore_errors=True)
	stage.mkdir(parents=True)
	with zipfile.ZipFile(jar) as z:
		for c in classes:
			target = stage / (c + ".class")
			target.parent.mkdir(parents=True, exist_ok=True)
			target.write_bytes(z.read(c + ".class"))
	out_dir.mkdir(parents=True, exist_ok=True)
	vf = fetch(VINEFLOWER, WORK / "vineflower.jar")
	result = subprocess.run(
		["java", "-jar", str(vf), "-e=" + str(jar), "--silent", str(stage), str(out_dir)],
		capture_output=True, text=True)
	if result.returncode != 0:
		log(f"!! vineflower failed for {label}: {result.stderr[-2000:]}")
	log(f"{label}: decompiled {len(classes)} classes")


def javap(classpath, classes, out_file):
	if not classes:
		return
	out_file.parent.mkdir(parents=True, exist_ok=True)
	with open(out_file, "w") as f:
		for i in range(0, len(classes), 200):
			chunk = [c.replace("/", ".") for c in classes[i:i + 200]]
			r = subprocess.run(["javap", "-public", "-cp", classpath] + chunk, capture_output=True, text=True)
			f.write(r.stdout)
			if r.stderr:
				f.write("\n// javap stderr:\n// " + r.stderr.replace("\n", "\n// ") + "\n")


def dump_mc(version):
	jar = mc_client_jar(version)
	if jar is None:
		return None
	out = OUT / f"mc-{version}"
	names = class_names(jar)
	decompile(jar, matching(names, MC_DECOMPILE), out / "src", f"mc-{version}")
	sigs = matching(names, MC_SIGS)
	javap(str(jar), [n for n in sigs if "$" not in n or "Properties" in n or "Builder" in n], out / "sigs" / "vanilla.txt")

	dirs = {}
	with zipfile.ZipFile(jar) as z:
		for n in z.namelist():
			if n.endswith("/"):
				continue
			if n.startswith("data/minecraft/"):
				parts = n.split("/")
				key = "/".join(parts[2:4]) if parts[2] in ("tags", "worldgen") else parts[2]
				dirs[key] = dirs.get(key, 0) + 1
				if "trade" in n or ("tags/item" in n and "bundle" in n) or "villager" in n:
					(out / n).parent.mkdir(parents=True, exist_ok=True)
					(out / n).write_bytes(z.read(n))
			elif n.startswith("assets/minecraft/items/") and n.endswith(".json"):
				data = z.read(n)
				if b"range_dispatch" in data:
					dest = out / "items" / Path(n).name
					dest.parent.mkdir(parents=True, exist_ok=True)
					dest.write_bytes(data)
		with open(out / "data-dirs.txt", "w") as f:
			for k in sorted(dirs):
				f.write(f"{dirs[k]:6d}  {k}\n")
	return set(names)


def find_jars(pattern):
	return sorted(p for p in GRADLE.rglob(pattern) if not p.name.endswith("-sources.jar"))


def fabric_api_jars(api_version):
	"""The module jars of one Fabric API release, read from its pom in the Gradle cache."""
	base = GRADLE / "modules-2" / "files-2.1" / "net.fabricmc.fabric-api"
	poms = list((base / "fabric-api" / api_version).rglob("*.pom"))
	if not poms:
		log(f"!! no pom for fabric-api {api_version}")
		return []
	pom = poms[0].read_text()
	jars = []
	for artifact, version in re.findall(r"<artifactId>([^<]+)</artifactId>\s*<version>([^<]+)</version>", pom):
		found = [p for p in (base / artifact / version).rglob("*.jar") if not p.name.endswith("-sources.jar")]
		if found:
			jars.append(found[0])
		else:
			log(f"!! {artifact} {version} not in the cache")
	return jars


def dump_loader_jars(label, jars, sig_packages, decompile_patterns):
	out = OUT / label
	out.mkdir(parents=True, exist_ok=True)
	with open(out / "jars.txt", "w") as f:
		f.write("\n".join(str(j) for j in jars) + "\n")
	for jar in jars:
		names = class_names(jar)
		sigs = [n for n in names if any(n.startswith(p) for p in sig_packages) and "/impl/" not in n
				and not re.search(r"\$\d+$", n)]
		if sigs:
			javap(str(jar), sigs, out / "sigs" / (jar.stem + ".txt"))
		dec = [n for n in matching(names, decompile_patterns) if "/impl/" not in n and "/mixin/" not in n]
		if dec:
			decompile(jar, dec, out / "src", f"{label}-{jar.stem}")


def main():
	OUT.mkdir(exist_ok=True)
	WORK.mkdir(parents=True, exist_ok=True)

	names = {v: dump_mc(v) for v in MC_VERSIONS}
	a, b = names.get("26.1.2"), names.get("26.2")
	if a and b:
		with open(OUT / "mc-diff-classes.txt", "w") as f:
			for n in sorted((a | b)):
				if "$" in n or not any(n.startswith(p) for p in MC_DIFF_PACKAGES):
					continue
				if n in a and n not in b:
					f.write(f"- {n}\n")
				elif n in b and n not in a:
					f.write(f"+ {n}\n")

	for version, api in FABRIC_API.items():
		dump_loader_jars(f"fabric-{version}", fabric_api_jars(api), FABRIC_SIG_PACKAGES, FABRIC_DECOMPILE)

	neo = [j for j in find_jars("neoforge-26.1.2*.jar") if "universal" in j.name] or find_jars("neoforge-26.1.2*.jar")
	dump_loader_jars("neoforge", neo[:1], NEOFORGE_SIG_PACKAGES, NEOFORGE_DECOMPILE)

	# Where Gradle put things, in case something above missed.
	with open(OUT / "gradle-jars.txt", "w") as f:
		for p in sorted(GRADLE.rglob("*.jar")):
			s = str(p)
			if any(k in s for k in ("fabric-api", "neoforge", "minecraft", "fabric-loader")):
				f.write(s + "\n")
	log("done")


if __name__ == "__main__":
	sys.exit(main())
