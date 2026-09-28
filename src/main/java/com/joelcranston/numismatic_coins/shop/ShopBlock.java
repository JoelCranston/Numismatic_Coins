package com.joelcranston.numismatic_coins.shop;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

/**
 * A shop: its owner stocks it and sets prices, and everyone else buys through the trading screen.
 * Only the owner can break it. An inexhaustible shop never runs out. (NO: ShopBlock.)
 */
public class ShopBlock extends BaseEntityBlock {

    public static final MapCodec<ShopBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.BOOL.fieldOf("inexhaustible").forGetter(ShopBlock::isInexhaustible),
            propertiesCodec()
    ).apply(instance, ShopBlock::new));

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(1, 0, 1, 14, 8, 14),
            Block.box(0, 8, 0, 16, 12, 16),
            Block.box(13, 0, 0, 16, 8, 3),
            Block.box(0, 0, 0, 3, 8, 3),
            Block.box(0, 0, 13, 3, 8, 16),
            Block.box(13, 0, 13, 16, 8, 16));

    private final boolean isInexhaustible;

    public ShopBlock(boolean isInexhaustible, BlockBehaviour.Properties properties) {

        super(properties);
        this.isInexhaustible = isInexhaustible;
    }

    public boolean isInexhaustible() {

        return this.isInexhaustible;
    }

    @Override
    protected MapCodec<ShopBlock> codec() {

        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {

        return new ShopBlockEntity(pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        return SHAPE;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.isClientSide()) return;
        if (!(placer instanceof ServerPlayer player)) {
            level.destroyBlock(pos, true);
            return;
        }
        if (level.getBlockEntity(pos) instanceof ShopBlockEntity shop) shop.setOwner(player.getUUID());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (!(player instanceof ServerPlayer serverPlayer) || !(level.getBlockEntity(pos) instanceof ShopBlockEntity shop)) {
            return InteractionResult.SUCCESS;
        }
        if (shop.isBusy()) return InteractionResult.SUCCESS;

        // The owner sneaks to see the shop as a customer does.
        if (shop.isOwner(player) && !player.isShiftKeyDown()) {
            ShopMenu.open(serverPlayer, shop);
        } else {
            shop.merchant().startTrading(player);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {

        boolean mayBreak = player.isCreative() || (level.getBlockEntity(pos) instanceof ShopBlockEntity shop && shop.isOwner(player));
        return mayBreak ? super.getDestroyProgress(state, player, level, pos) : 0;
    }
}
