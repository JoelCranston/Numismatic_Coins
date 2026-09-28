# Numismatic Coins: implementation plan

Written 2026-09-28 against `1.21` at `9d54e0d` (the merged Stonecutter scaffold, PR #1).

The rewrite ships three jars from one source tree: **Fabric 26.1.2**, **Fabric 26.2**, **NeoForge
26.1.2**. The old Fabric 1.21.1 mod under `reference/1.21.1-fabric/` is the content spec. This plan
splits the work into milestones that are each one PR, each leaves all three targets building, and
each adds something you can see in game.

## Ground rules (apply to every milestone)

- **All game code is shared.** Loader- and version-specific code lives only in `platform/`, behind
  small interfaces. A `//? fabric`, `//? neoforge` or `//? if >=26.2` comment outside `platform/`
  is a review flag.
- **CI is the build check.** Cloud sessions can't reach the Fabric/NeoForge mavens, so every PR
  must be green on `build.yml` (`buildAndCollect`, all three targets) before review.
- **Verify APIs against the jar, not memory.** Anything the plan marks *(verify)* is an inferred
  26.1/26.2 API shape. Record confirmed shapes in `docs/api-notes.md` (new), in the same spirit
  as Pyronetics' `docs/api-notes-26.1.2.md`, and keep a short decisions log there.
- **Reuse, don't port.** Copy assets, lang, recipes, loot tables and small MIT logic (coin maths)
  with credit; write everything else fresh against Mojang names. No owo-lib, no Cardinal
  Components, no Architectury.
- **Ids move to `numismatic_coins`.** Every `numismatic-overhaul:` id in reused JSON is renamed.
  There is no world migration from Numismatic Overhaul (different mod id, different MC version).
- **In-game check per milestone.** Each PR description lists what to try in a dev client; test on
  `26.1.2-fabric` every time and on the other two targets whenever the PR touches `platform/`.

## Platform seams

These are the only places loaders or versions differ. Each is an interface in `platform/` with a
Fabric and a NeoForge implementation, selected the way `NumismaticCoins.xplat()` already is. They
are introduced in the milestone that first needs them, not all up front.

| Seam | Fabric | NeoForge | 26.2 note | First needed |
|---|---|---|---|---|
| Registration (items, blocks, BEs, menus, components, sounds, recipe serializers, loot entry types) | `Registry.register` | `DeferredRegister` | ids split out (`ItemIds`, `BlockIds`, `BlockItemIds`) *(verify)* | M1 |
| Creative tab | `FabricItemGroup` | `DeferredRegister<CreativeModeTab>` | | M1 |
| Player data (purse) | Fabric Data Attachment API, synced | `AttachmentType`, synced | | M2 |
| Networking | `PayloadTypeRegistry` + `ServerPlayNetworking` | `RegisterPayloadHandlersEvent` | | M2 |
| Commands | `CommandRegistrationCallback` | `RegisterCommandsEvent` | | M2 |
| Screen opening + HUD layer | Fabric HUD API | `RegisterGuiLayersEvent` | `minecraft.gui.setScreen(...)` | M2 |
| Game rules | Fabric game rule API | vanilla/NeoForge registration *(verify)* | | M4 |
| Built-in datapack | `ResourceManagerHelper.registerBuiltinResourcePack` | `AddPackFindersEvent` | | M3 |
| Data reload listener | `ResourceManagerHelper` | `AddServerReloadListenersEvent` | | M4 |
| Loot injection | `LootTableEvents.MODIFY` | global loot modifier | | M4 |
| Menus with open data | `ExtendedScreenHandlerType` | `IMenuTypeExtension` | | M5 |
| Block entity renderers, menu screens | Fabric client registries | client mod-bus events | | M5 |
| Config screen | ModMenu | `IConfigScreenFactory` | | M8 |

## Milestones

### M0: API check and notes (small PR, docs only)

Confirm the shapes the rest of the plan leans on, from the 26.1.2 and 26.2 jars, and write them
into `docs/api-notes.md`:

- The `villager_trade` and trade-set JSON schema: how trades attach to a profession and level,
  whether a datapack can replace vanilla's emerald trade sets outright, and which item modifiers
  cover NO's trade types (enchanted items, enchanted books, dyed armour, suspicious stew, potions
  and tipped arrows, explorer maps, dimension-dependent stacks).
- Item model definitions (`assets/<ns>/items/*.json`), specifically `range_dispatch` on stack
  count for the coin textures `_0` to `_3`.
- Data attachment APIs on both loaders, including client sync and copy-on-death.
- Game rule registration on both loaders in 26.1.
- The 26.2 registration and screen changes in the table above.

Needs the game jars, so it is done in a local checkout (Remote Control on Joel's device) or by
reading decompiled sources in CI. Output: the notes file, plus any corrections to this plan.

### M1: Coins and money bags

- `platform/Registration` seam and creative tab seam.
- `currency/`: `Currency` (bronze 1, silver 100, gold 10,000, name colours), `CurrencyConverter`
  and `CurrencyHelper` maths copied from NO with attribution. JUnit tests for splitting a raw value
  into coins and back.
- Items: bronze, silver and gold coins (value tooltip, stack-count models), money bag with a
  `money_bag` data component (codec + stream codec) holding a raw value. Clicking coins onto
  coins or a bag merges them, as NO's `CoinItem`/`MoneyBagItem` do (using them to deposit comes
  with the purse in M2).
- Coins can't go in bundles (NO's `BundleItemMixin`), via a mixin or an item tag if 26.1 has one
  *(verify)*.
- Assets: item model definitions, models, lang renamed to `numismatic_coins` keys.
- **In game:** creative tab with coins and bags, tooltips show value, textures change with count.

### M2: The purse

- `Purse` attachment on the player: a `long` balance, saved, synced to its owner, kept on respawn.
- Using a coin or money bag deposits it. A purse widget on the inventory and creative screens shows
  the balance with extract buttons, sending a `RequestPurseAction` C2S payload (validated
  server-side).
- Balance change notification (action bar by default).
- `/numismatic balance [player] [get|set|add|subtract]` and `/numismatic serverworth`, op-gated.
- **In game:** pick up coins, deposit, withdraw, die and respawn, run the commands, on both loaders.

### M3: Villagers trade in coins

The largest content item, but mostly data now that trades are data-driven.

- `scripts/convert_trades.py`: reads `reference/.../villager_trades/*.json` (272 trades across 11
  NO types) and writes `villager_trade` JSON, turning a raw price into coin stacks (gold, silver,
  bronze into the two cost slots; prices that need more than two stacks become a money bag). Run
  once, commit the output, keep the script for tweaks.
- Ship the trades as a **built-in datapack** (“Coin trades”, on by default) that replaces vanilla's
  emerald trade sets. Servers turn it off with `/datapack disable` instead of NO's
  `enableVillagerTrading` config.
- Mixin on `MerchantMenu` so a coin cost is paid from the purse when the player lacks coins in
  their inventory (NO's `MerchantScreenHandlerMixin`), and a purse readout on the merchant screen
  (NO's `MerchantScreenMixin`).
- Wandering trader trades the same way.
- Deferred to a later PR: converting other mods' emerald trades into coins (NO's
  `RemappingTradeWrapper`). Worth doing only if players ask.
- **In game:** each profession at each level offers coin trades; buying works with coins in the
  inventory, in the purse, and split between the two.

### M4: Money in the world

- Custom loot pool entry type `numismatic_coins:money_bag` (min/max raw value) and the injection
  seam; money bags added to desert pyramid, dungeon, mineshaft, bastion, stronghold, outpost and
  buried treasure chests at NO's amounts and chances.
- Mob drops: coins for entities in `#numismatic_coins:the_bourgeoisie` and other configured mobs,
  scaled by the `moneyMobDropVariancePercentage` game rule and optionally by max health.
- Death penalty: `moneyDropPercentage` game rule drops that share of the purse as coins.
- Chest amounts and mob values live in **datapack JSON** (`data/numismatic_coins/mob_drops/*.json`
  through the reload listener seam, and the loot entry's own min/max), not a config file. That
  keeps them per-world and reloadable, and leaves the config client-only.
- **In game:** kill pillagers, open fresh structure chests, die with a full purse.

### M5: Piggy banks

- Plain and 16 dyed piggy banks, block entity with 3 coin slots (saved through `ValueInput` /
  `ValueOutput`), menu and vanilla screen rebuilt from NO's `piggy_bank.xml` layout and texture.
- Breaking one drops its contents with NO's particles and sound; loot tables keep contents.
- Colouring recipe as a record-style custom recipe serializer; recipes and tags copied over.
- Block entity renderer for the coins inside, if NO's renderer maps cleanly *(else defer)*.
- **In game:** craft, dye, fill, break, reload the world.

### M6: Shops

- Shop and inexhaustible shop blocks, block entity with owner, offers (item + price) and stored
  earnings. `ShopMenu` with the offer editor, a vanilla screen rebuilt from `shop.xml`, and the two
  payloads (`ShopScreenHandlerRequest` C2S, `UpdateShopScreen` S2C).
- Customers buy through a merchant (NO's `ShopMerchant`), paying from the purse.
- Block entity renderer showing the first offer; the `shop` advancement.
- Only the owner can edit or break; inexhaustible is creative-only.
- **In game:** set up a shop, buy from it as a second player (LAN or dedicated dev server), collect
  earnings.

### M7: Pawn shops

- Pawn shop and inexhaustible pawn shop: the same pattern as M6 but the player sells, with its own
  screen from `pawn_shop.xml`, payloads and renderer. Reuses M6's menu and screen building blocks.
- **In game:** sell to a pawn shop, check the owner's stock and balance.

### M8: Client settings and polish

- Client config (money message location, purse widget offsets per screen), small JSON in common,
  with a config screen through ModMenu (Fabric) and NeoForge's config screen hook.
- Coin tooltip component (NO's `CurrencyTooltipData`), sounds, any rendering left over from M5-M7.
- Optional integrations as soft dependencies, only once the rest is solid: recipe viewers
  (EMI/JEI/REI) for the piggy bank recipe.

### M9: First alpha release

- Changelog, README feature list and credits, Modrinth/CurseForge metadata already in the
  scaffold's publishing config.
- Smoke test all three jars in a clean instance (not dev) and on a dedicated server.
- Tag `0.1.0-alpha.1` and let `release.yml` publish.

## Order and parallelism

M0 → M1 → M2 is a strict chain (everything needs coins; trading and shops need the purse). After
M2, **M3, M4 and M5 are independent** and can be separate PRs in any order or at once. M6 needs
M2; M7 follows M6. M8 can start any time after M2. 26.2 is not a separate milestone: CI builds it
on every PR, so each seam gets its 26.2 branch when it is introduced.

## Dropped from NO on purpose

- owo config, owo item group buttons, owo particles: replaced by vanilla equivalents above.
- NO's trade JSON loader, 13 adapters, reload listener and the Fabric-internals mixin: replaced by
  vanilla data-driven trades.
- `ItemStackComponentizationFixin` (1.20.4 → 1.20.5 data fixer): no old worlds to upgrade.
- `switchy_cardinal` integration: tied to Cardinal Components.
