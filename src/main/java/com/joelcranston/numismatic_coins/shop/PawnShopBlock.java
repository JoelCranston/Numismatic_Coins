package com.joelcranston.numismatic_coins.shop;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A pawn shop: its owner names what it buys and for how much, and everyone else sells to it
 * through the trading screen. Built, placed and broken as a shop is. (NO: PawnShopBlock.)
 */
public class PawnShopBlock extends ShopBlock {

    public static final MapCodec<PawnShopBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.BOOL.fieldOf("inexhaustible").forGetter(PawnShopBlock::isInexhaustible),
            propertiesCodec()
    ).apply(instance, PawnShopBlock::new));

    public PawnShopBlock(boolean isInexhaustible, BlockBehaviour.Properties properties) {

        super(isInexhaustible, properties);
    }

    @Override
    protected MapCodec<PawnShopBlock> codec() {

        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {

        return new PawnShopBlockEntity(pos, state);
    }
}
