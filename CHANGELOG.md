# Changelog

Written by hand. The release workflow publishes the section whose heading matches the tag; a
version with no section here gets a changelog generated from its commits by git-cliff instead.

## [0.1.0-alpha.1]

First alpha of Numismatic Coins, a from-scratch rewrite of Numismatic Overhaul for Minecraft
26.1.2 (Fabric and NeoForge) and 26.2 (Fabric).

### Added

- Bronze, silver and gold coins and money bags.
- The purse: a per-player balance with a button on the inventory, creative and villager screens,
  and money messages in the action bar or chat.
- Villager and wandering trader trades in coins, as the built-in "Coin trades" data pack. Trades
  are paid from the inventory, the purse or both. While the pack is on, emeralds that mobs drop
  from their loot tables, modded mobs included, come out as coins.
- Money bags in structure chests, coins from pillagers, and a death penalty set by the
  `money_drop_percentage` game rule.
- Piggy banks in seventeen colours.
- Shops and pawn shops, plus creative-only inexhaustible versions.
- Coin icons in the tooltips of coins, money bags, piggy banks and the purse.
- `config/numismatic_coins.json` and an options screen (Mod Menu on Fabric, the mod list on
  NeoForge) with switches for each feature, mob drop scaling and client options.
- `/numismatic balance`, `serverworth` and `config` commands.

### Not carried over from Numismatic Overhaul

- Worlds, purses and config files from Numismatic Overhaul are not read.
- Other mods' emerald trades are not converted to coins.
- No recipe viewer (EMI, JEI, REI) integration yet.
