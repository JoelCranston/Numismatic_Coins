package com.joelcranston.numismatic_coins.config;

import java.util.List;
import java.util.Optional;

import com.joelcranston.numismatic_coins.config.ConfigOption.BooleanType;
import com.joelcranston.numismatic_coins.config.ConfigOption.DoubleType;
import com.joelcranston.numismatic_coins.config.ConfigOption.EnumType;
import com.joelcranston.numismatic_coins.config.ConfigOption.IntType;
import com.joelcranston.numismatic_coins.config.ConfigOption.Section;
import com.joelcranston.numismatic_coins.config.NumismaticConfig.MoneyMessageLocation;

/** Every option, in the order the config screen lists them. */
public final class ConfigOptions {

    private static final BooleanType BOOLEAN = new BooleanType();
    private static final DoubleType MULTIPLIER = new DoubleType(0, 100);
    private static final DoubleType REDUCTION = new DoubleType(0.01, 100);
    private static final IntType PURSE_OFFSET = new IntType(-1000, 1000);

    public static final List<ConfigOption<?>> ALL = List.of(
            new ConfigOption<>("piggyBanks", Section.FEATURES, BOOLEAN, true, config -> config.features.piggyBanks, (config, value) -> config.features.piggyBanks = value),
            new ConfigOption<>("shops", Section.FEATURES, BOOLEAN, true, config -> config.features.shops, (config, value) -> config.features.shops = value),
            new ConfigOption<>("pawnShops", Section.FEATURES, BOOLEAN, true, config -> config.features.pawnShops, (config, value) -> config.features.pawnShops = value),
            new ConfigOption<>("villagerTrades", Section.FEATURES, BOOLEAN, false, config -> config.features.villagerTrades, (config, value) -> config.features.villagerTrades = value),
            new ConfigOption<>("chestLoot", Section.FEATURES, BOOLEAN, true, config -> config.features.chestLoot, (config, value) -> config.features.chestLoot = value),
            new ConfigOption<>("mobDrops", Section.FEATURES, BOOLEAN, false, config -> config.features.mobDrops, (config, value) -> config.features.mobDrops = value),
            new ConfigOption<>("deathPenalty", Section.FEATURES, BOOLEAN, false, config -> config.features.deathPenalty, (config, value) -> config.features.deathPenalty = value),
            new ConfigOption<>("multiplier", Section.MOB_DROPS, MULTIPLIER, false, config -> config.mobDrops.multiplier, (config, value) -> config.mobDrops.multiplier = value),
            new ConfigOption<>("scaleOnHealth", Section.MOB_DROPS, BOOLEAN, false, config -> config.mobDrops.scaleOnHealth, (config, value) -> config.mobDrops.scaleOnHealth = value),
            new ConfigOption<>("healthScaleReduction", Section.MOB_DROPS, REDUCTION, false, config -> config.mobDrops.healthScaleReduction, (config, value) -> config.mobDrops.healthScaleReduction = value),
            new ConfigOption<>("moneyMessageLocation", Section.CLIENT, new EnumType<>(MoneyMessageLocation.class), false, config -> config.client.moneyMessageLocation, (config, value) -> config.client.moneyMessageLocation = value),
            new ConfigOption<>("inventoryPurseX", Section.CLIENT, PURSE_OFFSET, false, config -> config.client.inventoryPurseX, (config, value) -> config.client.inventoryPurseX = value),
            new ConfigOption<>("inventoryPurseY", Section.CLIENT, PURSE_OFFSET, false, config -> config.client.inventoryPurseY, (config, value) -> config.client.inventoryPurseY = value),
            new ConfigOption<>("creativePurseX", Section.CLIENT, PURSE_OFFSET, false, config -> config.client.creativePurseX, (config, value) -> config.client.creativePurseX = value),
            new ConfigOption<>("creativePurseY", Section.CLIENT, PURSE_OFFSET, false, config -> config.client.creativePurseY, (config, value) -> config.client.creativePurseY = value),
            new ConfigOption<>("merchantPurseX", Section.CLIENT, PURSE_OFFSET, false, config -> config.client.merchantPurseX, (config, value) -> config.client.merchantPurseX = value),
            new ConfigOption<>("merchantPurseY", Section.CLIENT, PURSE_OFFSET, false, config -> config.client.merchantPurseY, (config, value) -> config.client.merchantPurseY = value));

    private ConfigOptions() {}

    public static List<ConfigOption<?>> serverSide() {

        return ALL.stream().filter(option -> option.section().isServerSide()).toList();
    }

    public static Optional<ConfigOption<?>> byKey(String key) {

        return ALL.stream().filter(option -> option.key().equals(key)).findFirst();
    }
}
