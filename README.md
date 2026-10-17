# Numismatic Coins

Terraria-style currency for Minecraft: coins, a purse, shops, pawn shops and piggy banks, with
villagers trading in coins instead of emeralds.

A from-scratch rewrite of [Numismatic Overhaul](https://modrinth.com/mod/numismatic-overhaul) for **Minecraft 26.1.2 and 26.2** on **Fabric** and
**NeoForge**, built from one codebase with [Stonecutter](https://stonecutter.kikugie.dev/). It
started from [rotgruengelb/stonecutter-mod-template](https://github.com/rotgruengelb/stonecutter-mod-template).

| Target | Jar | Needs |
|---|---|---|
| Fabric 26.1.2 | `26.1.2-fabric` | Fabric Loader 0.19.2+, Fabric API |
| Fabric 26.2 | `26.2-fabric` | Fabric Loader 0.19.2+, Fabric API |
| NeoForge 26.1.2 | `26.1.2-neoforge` | NeoForge 26.1.2 |

The mod has to be installed on both the server and every client. [Mod Menu](https://modrinth.com/mod/modmenu)
is optional on Fabric and opens the options screen; NeoForge opens it from the mod list.

The previous Fabric 1.21.1 mod lives in [`reference/1.21.1-fabric/`](reference/1.21.1-fabric/) as
the content spec for the rewrite. It is not part of the build.

This is an **alpha**: worlds made with it may not load in later versions, and it does not read
worlds or purses from Numismatic Overhaul.

## Features

- **Coins.** Bronze, silver and gold, each worth a hundred of the one below. Right-click coins
  in your hand to put them in your purse.
- **Money bags** hold any amount of money as one item. Click coins onto a coin stack of another
  kind, or onto a bag, to bag them together; right-click a bag in the inventory to take its
  largest coin stack back out.
- **The purse.** Every player has one; the button next to the inventory, creative and villager
  screens shows the balance and opens it. Shift-click the button to put every coin and bag in
  your inventory into it; take out any amount as coins. Every change to the purse shows as
  "+ [12 Silver 4 Bronze]" in the action bar or chat, or not at all.
- **Villagers trade in coins.** The built-in *Coin trades* data pack replaces every emerald
  trade of villagers and the wandering trader with a coin price. Purchases are paid from your
  inventory, your purse, or both. While it is on, mobs that would drop emeralds (vindicators,
  evokers, modded illagers) drop their worth in coins instead, 10 silver each. Turn it off for a
  world with `/datapack disable`.
- **Money in the world.** Structure chests (desert pyramids, dungeons, mineshafts, bastions,
  strongholds, pillager outposts, buried treasure) hold money and pillagers killed by a player
  drop coins. Amounts are data pack loot tables and the `numismatic_coins:the_bourgeoisie`
  entity tag, so a pack can change them or add mobs per world.
- **Death penalty.** Dying drops part of your purse as coins, set by the
  `numismatic_coins:money_drop_percentage` game rule (10% by default). With `keep_inventory` on you keep it all.
- **Piggy banks** in seventeen colours, one slot each for bronze, silver and gold. Broken with a
  tool, one drops itself with the coins inside; an anvil falling on it or a tool named "Hammer"
  smashes it and scatters the coins. Dye one in the crafting grid.
- **Shops** let a player sell items: stock it, set a price in bronze for each offer, and
  customers buy through the villager trading screen, paying from their inventory or purse.
  Earnings collect in the shop until the owner takes them. Hoppers can feed the stock.
- **Pawn shops** do the opposite: the owner names what it buys and for how much and puts money
  in, and other players sell to it. Hoppers can empty what it bought.
- Creative-only **inexhaustible** shops and pawn shops never run out, for adventure maps and
  server spawns.
- **Tooltips** show the coins inside coins, money bags, piggy banks and the purse as icons.

## Options

`config/numismatic_coins.json`, or the options screen in game. On a server the file decides
the server options and each player's screen shows them read-only; operators change them with
`/numismatic config <option> <value>`.

| Option | Default | |
|---|---|---|
| `piggyBanks`, `shops`, `pawnShops` | on | Off removes the recipe and creative tab entry. Blocks already placed keep working. Needs a world reload. |
| `villagerTrades` | on | Whether new worlds turn on the Coin trades data pack. |
| `chestLoot` | on | Money bags in structure chests. |
| `mobDrops` | on | Coins from mobs. |
| `deathPenalty` | on | Off keeps the whole purse on death, whatever the game rule says. |
| `multiplier` | 1.0 | Scales every mob's coin drop (0 to 100). |
| `scaleOnHealth`, `healthScaleReduction` | off, 1.0 | Scale a mob's drop by its max health divided by 20, divided again by the reduction. |
| `moneyMessageLocation` | action bar | Client: where purse change messages show (action bar, chat, off). |
| purse X/Y offsets | 0 | Client: move the purse button on the inventory, creative and villager screens. |

Game rules: `numismatic_coins:money_drop_percentage` (10) and
`numismatic_coins:money_mob_drop_variance_percentage` (50, so a mob drops 50% to 150% of its
base value).

## Commands

| Command | Who | |
|---|---|---|
| `/numismatic balance` | everyone | Your purse balance. |
| `/numismatic balance <players> get\|set\|add\|subtract [amount]` | operators | Read or change purses, amounts in bronze. |
| `/numismatic serverworth` | operators | Total money in the purses of online players. |
| `/numismatic config <option> [value]` | operators | Read or set a server option. |

## Building

Needs Java 25.

```bash
./gradlew buildAndCollect        # every target, jars in build/libs/<version>/
./gradlew :26.2-fabric:build     # one target
./gradlew runActiveClient        # run the active Stonecutter version
```

## Working with versions and loaders

The source on disk is always in the state of the version in `.sc_active_version`
(`26.1.2-fabric`, which is also the committed `vcsVersion`). Switch it with the Stonecutter
IntelliJ plugin or `./gradlew "Set active project to 26.2-fabric"`, and switch back to
`26.1.2-fabric` before committing (the pre-commit hook in `.pre-commit-config.yaml` checks this).

Loader- and version-specific code uses Stonecutter comments:

```java
//? fabric {
fabricOnlyCode();
//?} else {
/*neoforgeOnlyCode();*/
//?}

//? if >=26.2 {
/*minecraft.gui.setScreen(screen);*/
//?} else {
minecraft.setScreen(screen);
//?}
```

Keep these behind small helpers (`platform/`) rather than scattered through game code.
Per-target dependency versions are in `stonecutter.properties.toml`.

## Credits

Numismatic Coins is a rewrite of [Numismatic Overhaul](https://github.com/wisp-forest/numismatic-overhaul)
by **glisco**, **Pois1x** and **Noaaan**. Its textures, sounds, translations, recipes, villager
trade lists and coin arithmetic come from that mod under the MIT license; the code is new.

Translations: Brazilian Portuguese, Russian, Ukrainian and Simplified Chinese, from Numismatic
Overhaul's translators. Strings added since are English only for now.

## License

MIT, see [LICENSE](LICENSE). The original copyright of glisco, Pois1x and Noaaan covers the
reused assets and data.
