# Numismatic Coins

Terraria-style currency for Minecraft: coins, a purse, shops, pawn shops and piggy banks, with
villagers trading in coins instead of emeralds.

A from-scratch rewrite of [Numismatic Overhaul](https://modrinth.com/mod/numismatic-overhaul) for **Minecraft 26.1.2 and 26.2** on **Fabric** and
**NeoForge**, built from one codebase with [Stonecutter](https://stonecutter.kikugie.dev/). It
started from [rotgruengelb/stonecutter-mod-template](https://github.com/rotgruengelb/stonecutter-mod-template).

| Target | Jar |
|---|---|
| Fabric 26.1.2 | `26.1.2-fabric` |
| Fabric 26.2 | `26.2-fabric` |
| NeoForge 26.1.2 | `26.1.2-neoforge` |

The previous Fabric 1.21.1 mod lives in [`reference/1.21.1-fabric/`](reference/1.21.1-fabric/) as
the content spec for the rewrite. It is not part of the build.

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

## License

MIT, see [LICENSE](LICENSE). Based on Numismatic Overhaul by glisco, Pois1x and Noaaan, whose
textures, sounds and translations it reuses.
