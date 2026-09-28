# API notes: Minecraft 26.1.2 / 26.2, Fabric API, NeoForge

Confirmed shapes the implementation plan leans on, read from the real jars (M0, 2026-09-28).
Jars: vanilla client 26.1.2 and 26.2 (unobfuscated, Mojang names), Fabric API `0.146.1+26.1.2`
and `0.161.0+26.2`, NeoForge `26.1.2.109`.

**How to re-check:** the `api-dump` workflow (`.github/workflows/api-dump.yml`,
`scripts/api-dump/dump.py`) decompiles the classes we care about and pushes them to
`refs/api-dump/latest`. Read it with:

```sh
git fetch origin refs/api-dump/latest && git worktree add ../api-dump FETCH_HEAD
```

Add patterns to `dump.py` when a new area needs checking. Pyronetics' `docs/api-notes-26.1.2.md`
covers much more of NeoForge 26.1 (block entities, screens, renderers, recipes) and is the next
place to look.

---

## Villager trades (M3)

Trades are fully data-driven. Three registries (all datapack):

| Path | Codec | What it is |
|---|---|---|
| `data/<ns>/villager_trade/<prof>/<level>/<name>.json` | `VillagerTrade.CODEC` | One offer template |
| `data/<ns>/tags/villager_trade/<prof>/level_<n>.json` | tag | The pool a level draws from |
| `data/<ns>/trade_set/<prof>/level_<n>.json` | `TradeSet.CODEC` | `{trades: #tag, amount, allow_duplicates?, random_sequence?}` |

`VillagerProfession` holds a hard-coded `Int2ObjectMap<ResourceKey<TradeSet>>` (level → set key,
e.g. `minecraft:armorer/level_1`); `AbstractVillager.addOffersFromTradeSet` looks the key up in
the `TRADE_SET` registry. The wandering trader draws from `wandering_trader/buying`,
`wandering_trader/uncommon` and `wandering_trader/common` (amount 5). Vanilla ships 387 trades,
68 trade sets and 73 trade tags; shared pools such as `#minecraft:common_smith/level_1` are
included by the armorer, toolsmith and weaponsmith tags.

**A datapack replaces vanilla's emerald trades outright** by overriding the tags
(`"replace": true` in `data/minecraft/tags/villager_trade/<prof>/level_<n>.json`) or the trade
set files themselves. The built-in "Coin trades" pack does the former; disabling the pack
restores vanilla.

`VillagerTrade` JSON:

```json
{
  "wants": { "id": "numismatic_coins:silver_coin", "count": 12, "components": { } },
  "additional_wants": { "id": "minecraft:compass" },
  "gives": { "id": "minecraft:map", "count": 1, "components": { } },
  "max_uses": 12, "xp": 10, "reputation_discount": 0.2,
  "merchant_predicate": { "condition": "minecraft:entity_properties", "entity": "this", "predicate": { } },
  "given_item_modifiers": [ { "function": "minecraft:exploration_map", "...": "..." } ],
  "double_trade_price_enchantments": "#minecraft:double_trade_price"
}
```

- `wants`/`additional_wants` are `TradeCost` (`id`, `count` as a number provider, `components`
  as an exact-match predicate). A money bag of a fixed value can be a cost by giving its
  `numismatic_coins:money_bag` component.
- **`TradeCost.toItemCost` clamps the count to the item's default max stack size.** Coins stack
  to 99, so one slot holds at most 99 coins; the converter must move to the next coin tier or a
  money bag before that.
- `given_item_modifiers` are ordinary loot item functions run with `LootContextParamSets
  .VILLAGER_TRADE`. Vanilla uses: `enchant_with_levels` and `enchant_randomly` (with
  `include_additional_cost_component`), `filtered` + `discard` (drop the offer if the modifier
  failed), `exploration_map`, `set_name`, `set_random_dyes`, `set_stew_effect`, `set_potion`,
  `set_random_potion`. Those cover every NO trade type: enchanted items and books, dyed armour,
  suspicious stew, potions and tipped arrows, explorer maps. NO's dimension-aware stacks become a
  `merchant_predicate` (`location_check` on the dimension).
- **Enchantment trades add their price on top of `wants`.** The enchant functions put
  `DataComponents.ADDITIONAL_TRADE_COST` on the result; `getOffer` removes it and adds it to the
  `wants` count (doubled when a stored enchantment is in `double_trade_price_enchantments`). The
  addition is in units of the `wants` item, so the converter must pick a coin whose unit makes
  that surcharge sensible (silver, not bronze).
- `merchant_predicate` selects by villager variant (`minecraft:villager/variant`), which is how
  the cartographer's biome maps work.
- NeoForge 26.1.2 has **no `VillagerTradesEvent`/`WandererTradesEvent`** (`event/village` only
  has `VillageSiegeEvent`), so trades are datapack-only on both loaders.

## Item models (M1)

Item model definitions live in `assets/<ns>/items/<item>.json`; `models/item/*.json` is only
reached through them (see Pyronetics' notes: an old-style item model alone is silently ignored).

Coins: `range_dispatch` on `minecraft:count` with `"normalize": false` gives the raw stack count
(clamped to the max stack size). `RangeSelectItemModel` picks the last threshold ≤ value and the
`fallback` below the first one. NO's thresholds (0.09, 0.27, 0.45, 0.63 of 100) become 9, 27, 45, 63:

```json
{ "model": { "type": "minecraft:range_dispatch", "property": "minecraft:count", "normalize": false,
  "fallback": { "type": "minecraft:model", "model": "numismatic_coins:item/bronze_coin" },
  "entries": [ { "threshold": 9, "model": { "type": "minecraft:model", "model": "numismatic_coins:item/bronze_coin_0" } } ] } }
```

Money bag: there is no vanilla property for a custom component value, and
`RangeSelectItemModelProperties.ID_MAPPER` is private. **Decision:** the bag's size tier (0 small,
1 at a silver or more, 2 at a gold or more) is written to `minecraft:custom_model_data` (`floats[0]`)
whenever the bag's value is set, and the model does `range_dispatch` on
`minecraft:custom_model_data`. No client registration on either loader.

## Bundles (M1)

`BundleContents.canItemBeInBundle(stack)` is the single check (`!isEmpty() &&
getItem().canFitInsideContainerItems()`). Overriding `Item#canFitInsideContainerItems` would also
keep coins out of shulker boxes, so NO's rule is kept with a small common mixin on
`BundleContents.canItemBeInBundle` instead. There is no item tag for it.

## Item click behaviour (M1)

NO's `onClicked` is `Item#overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot,
ClickAction action, Player player, SlotAccess carried)` and `Item#overrideStackedOnOther(self, slot,
action, player)`; `ClickAction.PRIMARY` is left click, `SECONDARY` right. Tooltips are
`appendHoverText(stack, context, TooltipDisplay, Consumer<Component>, flag)`.
`Item.Properties#setId(ResourceKey<Item>)` must be called before the `Item` constructor.

## Data components

`DataComponentType.builder().persistent(codec).networkSynchronized(streamCodec).build()`, registered
in `Registries.DATA_COMPONENT_TYPE`. With `persistent(codec)` alone it still syncs (a stream codec
is derived). 26.2 adds no breaking change here.

## Player data: the purse (M2)

| | Fabric (`fabric-data-attachment-api-v1`) | NeoForge |
|---|---|---|
| Create | `AttachmentRegistry.create(id, b -> b.initializer(...).persistent(codec).copyOnDeath().syncWith(streamCodec, AttachmentSyncPredicate.targetOnly()))` | `AttachmentType.builder(() -> v).serialize(mapCodec).copyOnDeath().sync((holder, to) -> holder == to, streamCodec).build()` in a `DeferredRegister` over `NeoForgeRegistries.Keys.ATTACHMENT_TYPES` |
| Read | `player.getAttachedOrCreate(type)` | `player.getData(type)` |
| Write | `player.setAttached(type, value)` (syncs on set) | `player.setData(type, value)` (calls `syncData`) |
| Copy on death | `copyOnDeath()` | `copyOnDeath()` |

Both sync only on set, so the purse value is an immutable record (`Purse(long balance)`) that is
replaced, never mutated. Same API on Fabric 26.2 (no signature changes).

## Game rules (M4)

26.1 made game rules a registry: `Registries.GAME_RULE`, `BuiltInRegistries.GAME_RULE`,
`net.minecraft.world.level.gamerules.GameRule<T>` with a public constructor
`(GameRuleCategory, GameRuleType, ArgumentType<T>, VisitorCaller<T>, Codec<T>, ToIntFunction<T>,
T default, FeatureFlagSet)`. Read with `level.getGameRules().get(rule)`.

- Fabric: `GameRuleBuilder.forInteger(5).category(...).range(0, 100).buildAndRegister(id)`.
- NeoForge: no dedicated API; register the `GameRule` through the ordinary registration seam
  (`DeferredRegister` over `Registries.GAME_RULE`). NeoForge only adds client edit-screen
  factories (`RegisterGameRuleEntryFactoryEvent`).

**Plan correction:** game rules need no seam of their own; they go through the registration seam.

## Networking (M2)

- Fabric: `PayloadTypeRegistry.serverboundPlay().register(type, codec)`,
  `ServerPlayNetworking.registerGlobalReceiver(type, handler)`, `ClientPlayNetworking.send`.
- NeoForge: `RegisterPayloadHandlersEvent` → `PayloadRegistrar#playToServer(type, codec, handler)`;
  `ClientPacketDistributor.sendToServer`, `PacketDistributor.sendToPlayer`.

## Commands (M2)

- Fabric: `CommandRegistrationCallback.EVENT` `(dispatcher, buildContext, selection)`.
- NeoForge: `RegisterCommandsEvent` (game bus) with `getDispatcher()`, `getBuildContext()`.

## HUD and screens (M2)

- Fabric HUD: `HudElementRegistry.attachElementAfter(VanillaHudElements.X, id, element)`;
  `HudElement#extractRenderState(GuiGraphicsExtractor, DeltaTracker)`.
- NeoForge HUD: `RegisterGuiLayersEvent#registerAbove(VanillaGuiLayers.X, id, layer)`.
- Widgets on vanilla screens: Fabric `ScreenEvents.AFTER_INIT` + `Screens.getWidgets`; NeoForge
  `ScreenEvent.Init.Post`.
- **26.2 moved screen handling from `Minecraft` to `Gui`:** `Minecraft#setScreen` and the
  `Minecraft.screen` field are gone; use `minecraft.gui.setScreen(screen)` and
  `minecraft.gui.screen()`. HUD state moved into a new `Hud` class (`minecraft.gui.hud`).
  Loader HUD APIs hide this.

## Built-in datapack (M3)

- Fabric: `ResourceLoader.registerBuiltinPack(id, modContainer, displayName,
  PackActivationType.DEFAULT_ENABLED)` (`fabric-resource-loader-v1`; the old
  `ResourceManagerHelper` is gone).
- NeoForge: `AddPackFindersEvent#addPackFinders(id, PackType.SERVER_DATA, displayName,
  PackSource.FEATURE, alwaysActive=false, Pack.Position.TOP)`.

## Reload listeners and loot (M4)

- Fabric: `DataResourceLoader.get().registerReloadListener(id, registries -> listener)`;
  `LootTableEvents.MODIFY` `(key, builder, source, registries)`.
- NeoForge: `AddServerReloadListenersEvent` (a `SortedReloadListenerEvent`; its add method is on the
  parent class, not yet read); global loot modifiers
  (`LootModifier`, `AddTableLootModifier`) with codecs in `NeoForgeRegistries.Keys
  .GLOBAL_LOOT_MODIFIER_SERIALIZERS`.

## Menus and block entities (M5-M7)

- Fabric: `ExtendedMenuType<T, D>(factory, streamCodec)` (`fabric-menu-api-v1`, was
  `ExtendedScreenHandlerType`); `FabricBlockEntityTypeBuilder` still exists in 26.1.2 and 26.2.
- NeoForge: `IMenuTypeExtension.create((id, inv, buf) -> ...)`; `BlockEntityType` constructors
  are public (NeoForge patch).
- **26.2:** vanilla's `BlockEntityType` constructor `(BlockEntitySupplier, Set<Block>)` is public,
  and vanilla's own types moved from `BlockEntityType.FURNACE` etc. to a new `BlockEntityTypes`
  class (with `BlockEntityTypeIds`).

## Creative tab (M1)

Vanilla `CreativeModeTab.builder(Row, column)` is public; Fabric's module is now
`fabric-creative-tab-api-v1` (renamed from `fabric-item-group-api-v1`); NeoForge registers tabs
through `DeferredRegister` over `Registries.CREATIVE_MODE_TAB`. *(Fabric class name not yet
read; confirm when M1 compiles.)*

## Other 26.1.2 → 26.2 changes seen

Added or removed classes in the packages we use are listed in the dump's `mc-diff-classes.txt`.
Relevant ones:

- No `ItemIds`/`BlockIds` in either version (the plan guessed they existed). Items and blocks
  register the same way on 26.1.2 and 26.2.
- `ItemPredicate` moved from `net.minecraft.advancements.criterion` to
  `net.minecraft.advancements.predicates`.
- `EnchantRandomlyFunction.Builder#withOptions` and `SetPotionFunction.fromTagKey` take a
  `HolderSet` instead of `Optional<HolderSet>`.
- `WeatheringCopperItems`/`WeatheringCopperBlocks` became `WeatheringCopperCollection<T>`, and
  coloured block families are `ColorCollection<T>` (`Items.WOOL.get(color)` style). Relevant to
  M5's 16 dyed piggy banks only if we index vanilla dyes through them.

## Decisions log

- 2026-09-28: money bag textures select on `custom_model_data`, set by common code (no client
  property registration).
- 2026-09-28: coins stay out of bundles via a mixin on `BundleContents.canItemBeInBundle`, not
  `canFitInsideContainerItems` (that would also block shulker boxes).
- 2026-09-28: game rules go through the registration seam; no separate game rule seam.
- 2026-09-28: coin trades replace vanilla's trade tags (`replace: true`) in a built-in datapack.
