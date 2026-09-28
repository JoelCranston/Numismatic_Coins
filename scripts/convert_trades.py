#!/usr/bin/env python3
"""Generate the "Coin trades" built-in data pack from Numismatic Overhaul's villager trades.

Reads NO's per-profession trade files (reference/1.21.1-fabric/.../villager_trades/*.json) and
writes vanilla 26.1 `villager_trade` files priced in coins, plus the trade tags that replace
vanilla's emerald pools. Owns everything under src/main/resources/resourcepacks/coin_trades/data/
and rewrites it on every run, so edit this script and rerun it rather than editing the output:

    python3 scripts/convert_trades.py
"""

import json
import shutil
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
NO_DATA = ROOT / "reference/1.21.1-fabric/src/main/resources/data/numismatic-overhaul"
NO_TRADES = NO_DATA / "villager_trades"
NO_ITEM_TAGS = NO_DATA / "tags/item"
PACK_DATA = ROOT / "src/main/resources/resourcepacks/coin_trades/data"

MOD_ID = "numismatic_coins"
NO_NAMESPACE = "numismatic-overhaul"

COIN_IDS = [f"{MOD_ID}:bronze_coin", f"{MOD_ID}:silver_coin", f"{MOD_ID}:gold_coin"]
EXCHANGE_RATE = 100
COIN_STACK_SIZE = 99

# NO's level names, as villager levels.
LEVELS = {"novice": 1, "apprentice": 2, "journeyman": 3, "expert": 4, "master": 5}

# The wandering trader's two NO pools, as its 26.1 trade sets. Its third set, `buying`, is emptied:
# NO's trader only sells. (NO: VillagerTradesHandler, WanderingTraderEntity#fillRecipes.)
WANDERING_TRADER_POOLS = {"novice": "common", "apprentice": "uncommon"}
# NO's trader offered one trade from its second pool; vanilla's `uncommon` set offers two.
WANDERING_TRADER_UNCOMMON_AMOUNT = 1

# Defaults for an NO trade that leaves them out. (NO: TradeJsonAdapter#loadDefaultStats.)
DEFAULT_MAX_USES = 12
DEFAULT_XP = 5
DEFAULT_PRICE_MULTIPLIER = 0.05
# The price an enchant trade starts from when it gives none. (NO: EnchantItemAdapter.)
DEFAULT_ENCHANT_BASE_PRICE = 200

# Enchanted trades are priced after enchanting by `numismatic_coins:enchantment_price`, which
# replaces this placeholder cost.
PLACEHOLDER_WANTS = {"id": COIN_IDS[0]}

# Map trades by the structure tag NO searched. NO's `c:end_cities` map is left out: an overworld
# villager can never find an End city, so the trade could never be offered.
MAP_DESTINATIONS = {
    "minecraft:on_ocean_explorer_maps": {"destination": "minecraft:on_ocean_explorer_maps",
                                         "decoration": "minecraft:monument", "name": "filled_map.monument"},
    # No decoration: `exploration_map` marks a woodland mansion by default.
    "c:on_woodland_explorer_maps": {"destination": "minecraft:on_woodland_explorer_maps",
                                    "decoration": None, "name": "filled_map.mansion"},
}
SKIPPED_MAP_TAGS = {"c:end_cities"}
MAP_SEARCH_RADIUS = 100


def closest_coin(price):
    """The single coin stack NO charged for a raw price: the largest coin in it, with the next
    coin down rounded into it. (NO: CurrencyHelper#getClosest.)"""

    gold, rest = divmod(price, EXCHANGE_RATE * EXCHANGE_RATE)
    silver, bronze = divmod(rest, EXCHANGE_RATE)
    counts = [bronze, silver, gold]
    for denomination in range(2):
        if counts[denomination + 1] == 0:
            break
        # Java's Math.round on a float: halves round up.
        counts[denomination + 1] += int(counts[denomination] / EXCHANGE_RATE + 0.5)
        counts[denomination] = 0
    # Rounding up can make a hundred of one coin, which is one of the next.
    total = counts[0] + counts[1] * EXCHANGE_RATE + counts[2] * EXCHANGE_RATE * EXCHANGE_RATE
    gold, rest = divmod(total, EXCHANGE_RATE * EXCHANGE_RATE)
    counts = [rest % EXCHANGE_RATE, rest // EXCHANGE_RATE, gold]
    for denomination in (2, 1, 0):
        if counts[denomination] > 0:
            count = counts[denomination]
            if count > COIN_STACK_SIZE:
                raise ValueError(f"Price {price} needs {count} of one coin, more than a stack")
            return {"id": COIN_IDS[denomination], "count": count}
    raise ValueError(f"Price {price} is zero")


def item_id(name):

    return name if ":" in name else f"minecraft:{name}"


def path_name(identifier):

    return identifier.split(":", 1)[1].replace("/", "_")


def stack(json_stack):
    """An NO item stack ({"item", "count"}) as a trade cost or result."""

    result = {"id": item_id(json_stack["item"])}
    if json_stack.get("count", 1) != 1:
        result["count"] = json_stack["count"]
    return result


def trade_tag_id(no_tag):
    """Our copy of one of NO's item tags. (NO: tags/item.)"""

    return f"{MOD_ID}:trade_goods/{no_tag.split(':', 1)[1]}"


def filter_or_discard(item, predicates):
    """Drops the offer when a modifier before it left the result without these components."""

    return {
        "function": "minecraft:filtered",
        "item_filter": {"items": item, "predicates": predicates},
        "on_fail": {"function": "minecraft:discard"},
    }


def enchanted_filter(item):

    if item == "minecraft:book":
        return filter_or_discard("minecraft:enchanted_book", {"minecraft:stored_enchantments": [{}]})
    return filter_or_discard(item, {"minecraft:enchantments": [{}]})


def base_trade(no_trade, wants, gives):

    trade = {"wants": wants, "gives": gives}
    trade["max_uses"] = no_trade.get("max_uses", DEFAULT_MAX_USES)
    trade["xp"] = no_trade.get("villager_experience", DEFAULT_XP)
    trade["reputation_discount"] = no_trade.get("price_multiplier", DEFAULT_PRICE_MULTIPLIER)
    return trade


# region Trade types, one per NO adapter

def sell_stack(no_trade):
    """(NO: SellStackAdapter.)"""

    gives = stack(no_trade["sell"])
    return path_name(gives["id"]), base_trade(no_trade, closest_coin(no_trade["price"]), gives)


def buy_stack(no_trade):
    """(NO: BuyStackAdapter.)"""

    wants = stack(no_trade["buy"])
    return path_name(wants["id"]), base_trade(no_trade, wants, closest_coin(no_trade["price"]))


def process_item(no_trade):
    """(NO: ProcessItemAdapter.)"""

    gives = stack(no_trade["sell"])
    trade = base_trade(no_trade, closest_coin(no_trade["price"]), gives)
    trade["additional_wants"] = stack(no_trade["buy"])
    return f"{path_name(trade['additional_wants']['id'])}_to_{path_name(gives['id'])}", trade


def dimension_sell_stack(no_trade):
    """Offered only by a villager in the named dimension. (NO: DimensionAwareSellStackAdapter.)"""

    name, trade = sell_stack(no_trade)
    dimension = item_id(no_trade["dimension"].strip())
    trade["merchant_predicate"] = {
        "condition": "minecraft:location_check",
        "predicate": {"dimension": dimension},
    }
    return f"{name}_in_{path_name(dimension)}", trade


def sell_tag(no_trade):
    """A random item from the tag each time the trade is rolled. (NO: SellTagAdapter.)"""

    tag = trade_tag_id(no_trade["sell"]["tag"])
    gives = {"id": first_tag_value(no_trade["sell"]["tag"])}
    if no_trade["sell"].get("count", 1) != 1:
        gives["count"] = no_trade["sell"]["count"]
    trade = base_trade(no_trade, closest_coin(no_trade["price"]), gives)
    trade["given_item_modifiers"] = [{"function": f"{MOD_ID}:set_random_item", "options": f"#{tag}"}]
    return f"random_{path_name(tag)}", trade


def sell_sus_stew(no_trade):
    """NO gave its stews no effect; each stew here carries the effect its trade names, with NO's
    duration in ticks turned into the seconds `set_stew_effect` takes. (NO: SellSusStewAdapter.)"""

    effect = item_id(no_trade["effect_id"])
    duration_ticks = no_trade.get("duration", 100)
    # Instant effects are given in ticks as they are; the rest in seconds.
    duration = duration_ticks if effect in INSTANT_EFFECTS else max(1, round(duration_ticks / 20))
    trade = base_trade(no_trade, closest_coin(no_trade["price"]), {"id": "minecraft:suspicious_stew"})
    trade["given_item_modifiers"] = [
        {"function": "minecraft:set_stew_effect", "effects": [{"type": effect, "duration": duration}]},
    ]
    return f"suspicious_stew_{path_name(effect)}", trade


INSTANT_EFFECTS = {"minecraft:saturation", "minecraft:instant_health", "minecraft:instant_damage"}


def sell_dyed_armor(no_trade):
    """One dye, a second 30% of the time and a third 20% of the time. (NO: SellDyedArmorAdapter.)"""

    item = item_id(no_trade["item"])
    trade = base_trade(no_trade, closest_coin(no_trade["price"]), {"id": item})
    trade["given_item_modifiers"] = [
        {
            "function": "minecraft:set_random_dyes",
            "number_of_dyes": {
                "type": "minecraft:sum",
                "summands": [
                    1,
                    {"type": "minecraft:binomial", "n": 1, "p": 0.3},
                    {"type": "minecraft:binomial", "n": 1, "p": 0.2},
                ],
            },
        },
        filter_or_discard(item, {"minecraft:dyed_color": {}}),
    ]
    return f"dyed_{path_name(item)}", trade


def sell_potion_container(no_trade):
    """(NO: SellPotionContainerItemAdapter.)"""

    gives = stack(no_trade["container_item"])
    trade = base_trade(no_trade, closest_coin(no_trade["price"]), gives)
    trade["additional_wants"] = stack(no_trade["buy_item"])
    trade["given_item_modifiers"] = [{"function": "minecraft:set_random_potion", "options": "#minecraft:tradeable"}]
    return f"random_{path_name(gives['id'])}", trade


def enchant_item(no_trade):
    """Enchants the item the player hands over, priced by its enchantments. (NO: EnchantItemAdapter.)"""

    item = item_id(no_trade.get("item", {"item": "book"})["item"])
    options = "#minecraft:tradeable" if no_trade.get("allow_treasure", False) else "#minecraft:non_treasure"
    trade = base_trade(no_trade, dict(PLACEHOLDER_WANTS), {"id": item})
    trade["additional_wants"] = {"id": item}
    trade["given_item_modifiers"] = [
        {"function": "minecraft:enchant_with_levels", "levels": no_trade["level"], "options": options},
        enchanted_filter(item),
        {
            "function": f"{MOD_ID}:enchantment_price",
            "formula": "enchanted_item",
            "base_price": no_trade.get("base_price", DEFAULT_ENCHANT_BASE_PRICE),
        },
    ]
    return f"enchanted_{path_name(item)}_level_{no_trade['level']}", trade


def sell_single_enchantment(no_trade):
    """A book with one tradeable enchantment at a random level. (NO: SellSingleEnchantmentAdapter.)"""

    trade = base_trade(no_trade, dict(PLACEHOLDER_WANTS), {"id": "minecraft:book"})
    trade["additional_wants"] = {"id": "minecraft:book"}
    trade["given_item_modifiers"] = [
        {"function": "minecraft:enchant_randomly", "options": "#minecraft:tradeable", "only_compatible": False},
        enchanted_filter("minecraft:book"),
        {"function": f"{MOD_ID}:enchantment_price", "formula": "enchanted_book"},
    ]
    return "enchanted_book", trade


def sell_map_tag(no_trade):
    """A map to the nearest structure in the tag, for coins and a blank map. (NO: SellMapTagAdapter.)"""

    target = MAP_DESTINATIONS[no_trade["tag"]]
    trade = base_trade(no_trade, closest_coin(no_trade["price"]), {"id": "minecraft:map"})
    trade["additional_wants"] = {"id": "minecraft:map"}
    exploration_map = {
        "function": "minecraft:exploration_map",
        "destination": target["destination"],
        "search_radius": MAP_SEARCH_RADIUS,
    }
    if target["decoration"] is not None:
        exploration_map["decoration"] = target["decoration"]
    trade["given_item_modifiers"] = [
        exploration_map,
        {"function": "minecraft:set_name", "name": {"translate": target["name"]}, "target": "item_name"},
        filter_or_discard("minecraft:filled_map", {"minecraft:map_id": {}}),
    ]
    return f"map_{path_name(target['destination'])}", trade


CONVERTERS = {
    "sell_stack": sell_stack,
    "buy_stack": buy_stack,
    "process_item": process_item,
    "dimension_sell_stack": dimension_sell_stack,
    "sell_tag": sell_tag,
    "sell_sus_stew": sell_sus_stew,
    "sell_dyed_armor": sell_dyed_armor,
    "sell_potion_container": sell_potion_container,
    "enchant_item": enchant_item,
    "sell_single_enchantment": sell_single_enchantment,
    "sell_map_tag": sell_map_tag,
}

# endregion


def first_tag_value(no_tag):

    return read_json(NO_ITEM_TAGS / f"{no_tag.split(':', 1)[1]}.json")["values"][0]


def read_json(path):

    with open(path, encoding="utf-8") as file:
        return json.load(file)


def write_json(path, data):

    path.parent.mkdir(parents=True, exist_ok=True)
    with open(path, "w", encoding="utf-8", newline="\n") as file:
        json.dump(data, file, indent=2)
        file.write("\n")


def convert_pool(profession, pool_name, no_trades, written_trade_ids):
    """Writes one pool's trades and returns their ids, in NO's order."""

    pool_trade_ids = []
    for no_trade in no_trades:
        trade_type = no_trade["type"].split(":", 1)[1]
        if trade_type == "sell_map_tag" and no_trade["tag"] in SKIPPED_MAP_TAGS:
            continue
        name, trade = CONVERTERS[trade_type](no_trade)
        base_id = f"{profession}/{pool_name}/{name}"
        trade_id = base_id
        suffix = 2
        while trade_id in written_trade_ids:
            trade_id = f"{base_id}_{suffix}"
            suffix += 1
        written_trade_ids.add(trade_id)
        write_json(PACK_DATA / MOD_ID / "villager_trade" / f"{trade_id}.json", trade)
        pool_trade_ids.append(f"{MOD_ID}:{trade_id}")
    return pool_trade_ids


def write_replacing_tag(tag_path, values):

    write_json(PACK_DATA / "minecraft/tags/villager_trade" / f"{tag_path}.json", {"replace": True, "values": values})


def main():

    shutil.rmtree(PACK_DATA, ignore_errors=True)
    written_trade_ids = set()
    trade_count = 0

    for trade_file in sorted(NO_TRADES.glob("*.json")):
        no_professions = read_json(trade_file)
        profession = no_professions["profession"]
        for level_name, no_trades in no_professions["trades"].items():
            if profession == "wandering_trader":
                pool_name = WANDERING_TRADER_POOLS[level_name]
                tag_path = f"wandering_trader/{pool_name}"
            else:
                pool_name = f"level_{LEVELS[level_name]}"
                tag_path = f"{profession}/{pool_name}"
            pool_trade_ids = convert_pool(profession, pool_name, no_trades, written_trade_ids)
            write_replacing_tag(tag_path, pool_trade_ids)
            trade_count += len(pool_trade_ids)

    write_replacing_tag("wandering_trader/buying", [])
    write_json(PACK_DATA / "minecraft/trade_set/wandering_trader/uncommon.json", {
        "amount": WANDERING_TRADER_UNCOMMON_AMOUNT,
        "random_sequence": "minecraft:trade_set/wandering_trader/uncommon",
        "trades": "#minecraft:wandering_trader/uncommon",
    })

    for no_tag in sorted(NO_ITEM_TAGS.glob("*.json")):
        values = read_json(no_tag)["values"]
        write_json(PACK_DATA / MOD_ID / "tags/item/trade_goods" / no_tag.name, {"values": values})

    print(f"Wrote {trade_count} trades to {PACK_DATA.relative_to(ROOT)}")


if __name__ == "__main__":
    main()
