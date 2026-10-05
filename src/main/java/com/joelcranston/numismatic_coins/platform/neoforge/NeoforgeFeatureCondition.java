package com.joelcranston.numismatic_coins.platform.neoforge;

//? neoforge {

/*import com.joelcranston.numismatic_coins.config.Configs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.neoforged.neoforge.common.conditions.ICondition;

// In a data file: "neoforge:conditions": [{"type": "numismatic_coins:feature_enabled", "feature": "piggyBanks"}]
public record NeoforgeFeatureCondition(String feature) implements ICondition {

    public static final MapCodec<NeoforgeFeatureCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("feature").forGetter(NeoforgeFeatureCondition::feature)
    ).apply(instance, NeoforgeFeatureCondition::new));

    @Override
    public boolean test(ICondition.IContext context) {

        return Configs.isFeatureEnabled(this.feature);
    }

    @Override
    public MapCodec<? extends ICondition> codec() {

        return CODEC;
    }
}
*///?}
