package com.joelcranston.numismatic_coins.client;

import java.util.List;

import com.joelcranston.numismatic_coins.shop.ShopBlockEntity;
import com.joelcranston.numismatic_coins.shop.ShopOffer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

/**
 * Turns one of a shop's offers above it, a new one every three seconds. Blocks sit lower and
 * larger than items, so both fill the counter. (NO: ShopBlockEntityRender.)
 */
public class ShopBlockEntityRenderer implements BlockEntityRenderer<ShopBlockEntity, ShopBlockEntityRenderer.State> {

    private static final int TICKS_PER_OFFER = 60;
    // One turn every 7.2 seconds.
    private static final int TICKS_PER_TURN = 144;
    private static final float DEGREES_PER_TURN = 360;
    private static final float CENTER = 0.5F;
    private static final float BLOCK_HEIGHT = 0.85F, ITEM_HEIGHT = 0.95F;
    private static final float BLOCK_SCALE = 0.95F, ITEM_SCALE = 0.85F;

    private final ItemModelResolver itemModelResolver;

    public ShopBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public State createRenderState() {

        return new State();
    }

    @Override
    public void extractRenderState(ShopBlockEntity shop, State state, float partialTicks, Vec3 cameraPosition, @Nullable CrumblingOverlay breakProgress) {

        BlockEntityRenderer.super.extractRenderState(shop, state, partialTicks, cameraPosition, breakProgress);
        state.item.clear();
        Level level = shop.getLevel();
        List<ShopOffer> offers = shop.offers();
        if (level == null || offers.isEmpty()) return;

        long gameTime = level.getGameTime();
        ItemStack stack = offers.get((int) (gameTime / TICKS_PER_OFFER % offers.size())).stack();
        this.itemModelResolver.updateForTopItem(state.item, stack, ItemDisplayContext.GROUND, level, null, (int) shop.getBlockPos().asLong());
        state.isBlock = stack.getItem() instanceof BlockItem;
        state.angle = (gameTime % TICKS_PER_TURN + partialTicks) * DEGREES_PER_TURN / TICKS_PER_TURN;
    }

    @Override
    public void submit(State state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {

        if (state.item.isEmpty()) return;
        float scale = state.isBlock ? BLOCK_SCALE : ITEM_SCALE;
        poseStack.pushPose();
        poseStack.translate(CENTER, state.isBlock ? BLOCK_HEIGHT : ITEM_HEIGHT, CENTER);
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.angle));
        state.item.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }

    public static class State extends BlockEntityRenderState {

        final ItemStackRenderState item = new ItemStackRenderState();
        boolean isBlock;
        float angle;
    }
}
