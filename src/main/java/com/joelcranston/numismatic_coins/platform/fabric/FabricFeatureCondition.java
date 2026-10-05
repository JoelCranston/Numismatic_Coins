package com.joelcranston.numismatic_coins.platform.fabric;

//? fabric {

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.config.Configs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.RegistryOps;

// In a data file: "fabric:load_conditions": [{"condition": "numismatic_coins:feature_enabled", "feature": "piggyBanks"}]
public record FabricFeatureCondition(String feature) implements ResourceCondition {

    public static final MapCodec<FabricFeatureCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("feature").forGetter(FabricFeatureCondition::feature)
    ).apply(instance, FabricFeatureCondition::new));

    public static final ResourceConditionType<FabricFeatureCondition> TYPE = ResourceConditionType.create(NumismaticCoins.id("feature_enabled"), CODEC);

    @Override
    public ResourceConditionType<?> getType() {

        return TYPE;
    }

    @Override
    public boolean test(RegistryOps.RegistryInfoLookup registryInfo) {

        return Configs.isFeatureEnabled(this.feature);
    }
}
//?}
