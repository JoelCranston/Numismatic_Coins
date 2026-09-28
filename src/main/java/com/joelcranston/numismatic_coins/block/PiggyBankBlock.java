package com.joelcranston.numismatic_coins.block;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.registry.ModBlocks;
import com.joelcranston.numismatic_coins.registry.ModSounds;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

/**
 * A piggy bank: a small block holding one slot each of bronze, silver and gold coins. Broken with
 * a tool, it drops itself with the coins inside; an anvil landing on it, or a tool named "Hammer",
 * smashes it and scatters the coins. (NO: PiggyBankBlock.)
 */
public class PiggyBankBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public static final MapCodec<PiggyBankBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            DyeColor.CODEC.optionalFieldOf("color").forGetter(block -> Optional.ofNullable(block.color)),
                            propertiesCodec())
                    .apply(instance, (color, properties) -> new PiggyBankBlock(color.orElse(null), properties)));

    /** Blocks heavy enough to smash a piggy bank when they fall on it. */
    public static final TagKey<Block> VERY_HEAVY_BLOCKS = TagKey.create(Registries.BLOCK, NumismaticCoins.id("very_heavy_blocks"));

    /** A tool renamed to this smashes the piggy bank instead of picking it up. (NO: PiggyBankBlock#getDroppedStacks.) */
    private static final String HAMMER_NAME = "Hammer";
    // Particles per block fallen when a falling block smashes a piggy bank, and for a hammer.
    private static final int PARTICLES_PER_BLOCK_FALLEN = 6;
    private static final int HAMMER_FALL_DISTANCE = 5;
    private static final double PARTICLE_SPREAD = 0.25;
    private static final double PARTICLE_SPEED = 0.2;

    private static final Map<Direction, VoxelShape> SHAPES = Map.of(
            Direction.NORTH, Shapes.or(Block.box(7, 2, 4, 9, 4, 5), Block.box(5, 1, 5, 11, 6, 11),
                    Block.box(5, 0, 5, 6, 1, 7), Block.box(5, 0, 9, 6, 1, 11), Block.box(10, 0, 9, 11, 1, 11), Block.box(10, 0, 5, 11, 1, 7)),
            Direction.SOUTH, Shapes.or(Block.box(7, 2, 11, 9, 4, 12), Block.box(5, 1, 5, 11, 6, 11),
                    Block.box(10, 0, 9, 11, 1, 11), Block.box(10, 0, 5, 11, 1, 7), Block.box(5, 0, 5, 6, 1, 7), Block.box(5, 0, 9, 6, 1, 11)),
            Direction.EAST, Shapes.or(Block.box(11, 2, 7, 12, 4, 9), Block.box(5, 1, 5, 11, 6, 11),
                    Block.box(9, 0, 5, 11, 1, 6), Block.box(5, 0, 5, 7, 1, 6), Block.box(5, 0, 10, 7, 1, 11), Block.box(9, 0, 10, 11, 1, 11)),
            Direction.WEST, Shapes.or(Block.box(4, 2, 7, 5, 4, 9), Block.box(5, 1, 5, 11, 6, 11),
                    Block.box(5, 0, 10, 7, 1, 11), Block.box(9, 0, 10, 11, 1, 11), Block.box(9, 0, 5, 11, 1, 6), Block.box(5, 0, 5, 7, 1, 6)));

    @Nullable
    private final DyeColor color;

    public PiggyBankBlock(@Nullable DyeColor color, BlockBehaviour.Properties properties) {

        super(properties);
        this.color = color;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    public DyeColor color() {

        return this.color;
    }

    @Override
    protected MapCodec<PiggyBankBlock> codec() {

        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {

        return new PiggyBankBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {

        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof PiggyBankBlockEntity piggyBank) {
            player.openMenu(piggyBank);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {

        if (level instanceof ServerLevel serverLevel && entity instanceof FallingBlockEntity fallingBlock
                && fallingBlock.getBlockState().is(VERY_HEAVY_BLOCKS)) {
            if (level.getBlockEntity(pos) instanceof PiggyBankBlockEntity piggyBank) {
                Containers.dropContents(level, pos.relative(state.getValue(FACING).getOpposite()), piggyBank);
            }
            level.removeBlock(pos, false);
            smashEffects(serverLevel, pos, (int) Math.round(fallDistance));
        }
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {

        // A creative player's break drops nothing, so a filled piggy bank drops itself here instead.
        if (!level.isClientSide() && player.preventsBlockDrops()
                && level.getBlockEntity(pos) instanceof PiggyBankBlockEntity piggyBank && !piggyBank.isEmpty()) {
            ItemStack stack = new ItemStack(this);
            stack.applyComponents(piggyBank.collectComponents());
            ItemEntity entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
            entity.setDefaultPickUpDelay();
            level.addFreshEntity(entity);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {

        ItemStack tool = params.getOptionalParameter(LootContextParams.TOOL);
        if (params.getOptionalParameter(LootContextParams.BLOCK_ENTITY) instanceof PiggyBankBlockEntity piggyBank && isHammer(tool)) {
            Vec3 origin = params.getParameter(LootContextParams.ORIGIN);
            smashEffects(params.getLevel(), BlockPos.containing(origin), HAMMER_FALL_DISTANCE);
            return piggyBank.getItems().stream().filter(stack -> !stack.isEmpty()).map(ItemStack::copy).toList();
        }
        return super.getDrops(state, params);
    }

    private static boolean isHammer(@Nullable ItemStack tool) {

        Component name = tool == null ? null : tool.get(DataComponents.CUSTOM_NAME);
        return name != null && HAMMER_NAME.equals(name.getString());
    }

    private static void smashEffects(ServerLevel level, BlockPos pos, int fallDistance) {

        level.playSound(null, pos, ModSounds.PIGGY_BANK_BREAK.get(), SoundSource.BLOCKS, 1f, 1f);
        level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, ModBlocks.piggyBank(null).defaultBlockState()),
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, PARTICLES_PER_BLOCK_FALLEN * Math.max(1, fallDistance),
                PARTICLE_SPREAD, PARTICLE_SPREAD, PARTICLE_SPREAD, PARTICLE_SPEED);
    }
}
