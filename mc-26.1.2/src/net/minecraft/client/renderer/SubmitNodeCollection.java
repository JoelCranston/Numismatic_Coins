package net.minecraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeStorage.BlockModelSubmit;
import net.minecraft.client.renderer.SubmitNodeStorage.BreakingBlockModelSubmit;
import net.minecraft.client.renderer.SubmitNodeStorage.FlameSubmit;
import net.minecraft.client.renderer.SubmitNodeStorage.ItemSubmit;
import net.minecraft.client.renderer.SubmitNodeStorage.LeashSubmit;
import net.minecraft.client.renderer.SubmitNodeStorage.ModelPartSubmit;
import net.minecraft.client.renderer.SubmitNodeStorage.ModelSubmit;
import net.minecraft.client.renderer.SubmitNodeStorage.MovingBlockSubmit;
import net.minecraft.client.renderer.SubmitNodeStorage.ShadowSubmit;
import net.minecraft.client.renderer.SubmitNodeStorage.TextSubmit;
import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState.LeashState;
import net.minecraft.client.renderer.entity.state.EntityRenderState.ShadowPiece;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.client.renderer.feature.NameTagFeatureRenderer.Storage;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.jspecify.annotations.Nullable;

public class SubmitNodeCollection implements OrderedSubmitNodeCollector {
   private final List<ShadowSubmit> shadowSubmits = new ArrayList<>();
   private final List<FlameSubmit> flameSubmits = new ArrayList<>();
   private final Storage nameTagSubmits = new Storage();
   private final List<TextSubmit> textSubmits = new ArrayList<>();
   private final List<LeashSubmit> leashSubmits = new ArrayList<>();
   private final List<MovingBlockSubmit> movingBlockSubmits = new ArrayList<>();
   private final List<BlockModelSubmit> blockModelSubmits = new ArrayList<>();
   private final List<BreakingBlockModelSubmit> breakingBlockModelSubmits = new ArrayList<>();
   private final List<ItemSubmit> itemSubmits = new ArrayList<>();
   private final List<SubmitNodeCollector.ParticleGroupRenderer> particleGroupRenderers = new ArrayList<>();
   private final net.minecraft.client.renderer.feature.ModelFeatureRenderer.Storage modelSubmits = new net.minecraft.client.renderer.feature.ModelFeatureRenderer.Storage();
   private final net.minecraft.client.renderer.feature.ModelPartFeatureRenderer.Storage modelPartSubmits = new net.minecraft.client.renderer.feature.ModelPartFeatureRenderer.Storage();
   private final net.minecraft.client.renderer.feature.CustomFeatureRenderer.Storage customGeometrySubmits = new net.minecraft.client.renderer.feature.CustomFeatureRenderer.Storage();
   private final SubmitNodeStorage submitNodeStorage;
   private boolean wasUsed = false;

   public SubmitNodeCollection(final SubmitNodeStorage submitNodeStorage) {
      this.submitNodeStorage = submitNodeStorage;
   }

   @Override
   public void submitShadow(final PoseStack poseStack, final float radius, final List<ShadowPiece> pieces) {
      this.wasUsed = true;
      Pose pose = poseStack.last();
      this.shadowSubmits.add(new ShadowSubmit(new Matrix4f(pose.pose()), radius, pieces));
   }

   @Override
   public void submitNameTag(
      final PoseStack poseStack,
      @Nullable final Vec3 nameTagAttachment,
      final int offset,
      final Component name,
      final boolean seeThrough,
      final int lightCoords,
      final double distanceToCameraSq,
      final CameraRenderState camera
   ) {
      this.wasUsed = true;
      this.nameTagSubmits.add(poseStack, nameTagAttachment, offset, name, seeThrough, lightCoords, distanceToCameraSq, camera);
   }

   @Override
   public void submitText(
      final PoseStack poseStack,
      final float x,
      final float y,
      final FormattedCharSequence string,
      final boolean dropShadow,
      final DisplayMode displayMode,
      final int lightCoords,
      final int color,
      final int backgroundColor,
      final int outlineColor
   ) {
      this.wasUsed = true;
      this.textSubmits
         .add(new TextSubmit(new Matrix4f(poseStack.last().pose()), x, y, string, dropShadow, displayMode, lightCoords, color, backgroundColor, outlineColor));
   }

   @Override
   public void submitFlame(final PoseStack poseStack, final EntityRenderState renderState, final Quaternionf rotation) {
      this.wasUsed = true;
      this.flameSubmits.add(new FlameSubmit(poseStack.last().copy(), renderState, rotation));
   }

   @Override
   public void submitLeash(final PoseStack poseStack, final LeashState leashState) {
      this.wasUsed = true;
      this.leashSubmits.add(new LeashSubmit(new Matrix4f(poseStack.last().pose()), leashState));
   }

   @Override
   public <S> void submitModel(
      final Model<? super S> model,
      final S state,
      final PoseStack poseStack,
      final RenderType renderType,
      final int lightCoords,
      final int overlayCoords,
      final int tintedColor,
      @Nullable final TextureAtlasSprite sprite,
      final int outlineColor,
      @Nullable final CrumblingOverlay crumblingOverlay
   ) {
      this.wasUsed = true;
      ModelSubmit<S> modelSubmit = new ModelSubmit<>(
         poseStack.last().copy(), model, state, lightCoords, overlayCoords, tintedColor, sprite, outlineColor, crumblingOverlay
      );
      this.modelSubmits.add(renderType, modelSubmit);
   }

   @Override
   public void submitModelPart(
      final ModelPart modelPart,
      final PoseStack poseStack,
      final RenderType renderType,
      final int lightCoords,
      final int overlayCoords,
      @Nullable final TextureAtlasSprite sprite,
      final boolean sheeted,
      final boolean hasFoil,
      final int tintedColor,
      @Nullable final CrumblingOverlay crumblingOverlay,
      final int outlineColor
   ) {
      this.wasUsed = true;
      this.modelPartSubmits
         .add(
            renderType,
            new ModelPartSubmit(
               poseStack.last().copy(), modelPart, lightCoords, overlayCoords, sprite, sheeted, hasFoil, tintedColor, crumblingOverlay, outlineColor
            )
         );
   }

   @Override
   public void submitMovingBlock(final PoseStack poseStack, final MovingBlockRenderState movingBlockRenderState) {
      this.wasUsed = true;
      this.movingBlockSubmits.add(new MovingBlockSubmit(new Matrix4f(poseStack.last().pose()), movingBlockRenderState));
   }

   @Override
   public void submitBlockModel(
      final PoseStack poseStack,
      final RenderType renderType,
      final List<BlockStateModelPart> modelParts,
      final int[] tintLayers,
      final int lightCoords,
      final int overlayCoords,
      final int outlineColor
   ) {
      this.wasUsed = true;
      this.blockModelSubmits.add(new BlockModelSubmit(poseStack.last().copy(), renderType, modelParts, tintLayers, lightCoords, overlayCoords, outlineColor));
   }

   @Override
   public void submitBreakingBlockModel(final PoseStack poseStack, final BlockStateModel model, final long seed, final int progress) {
      this.wasUsed = true;
      this.breakingBlockModelSubmits.add(new BreakingBlockModelSubmit(poseStack.last().copy(), model, seed, progress));
   }

   @Override
   public void submitItem(
      final PoseStack poseStack,
      final ItemDisplayContext displayContext,
      final int lightCoords,
      final int overlayCoords,
      final int outlineColor,
      final int[] tintLayers,
      final List<BakedQuad> quads,
      final ItemStackRenderState.FoilType foilType
   ) {
      this.wasUsed = true;
      this.itemSubmits.add(new ItemSubmit(poseStack.last().copy(), displayContext, lightCoords, overlayCoords, outlineColor, tintLayers, quads, foilType));
   }

   @Override
   public void submitCustomGeometry(
      final PoseStack poseStack, final RenderType renderType, final SubmitNodeCollector.CustomGeometryRenderer customGeometryRenderer
   ) {
      this.wasUsed = true;
      this.customGeometrySubmits.add(poseStack, renderType, customGeometryRenderer);
   }

   @Override
   public void submitParticleGroup(final SubmitNodeCollector.ParticleGroupRenderer particleGroupRenderer) {
      this.wasUsed = true;
      this.particleGroupRenderers.add(particleGroupRenderer);
   }

   public List<ShadowSubmit> getShadowSubmits() {
      return this.shadowSubmits;
   }

   public List<FlameSubmit> getFlameSubmits() {
      return this.flameSubmits;
   }

   public Storage getNameTagSubmits() {
      return this.nameTagSubmits;
   }

   public List<TextSubmit> getTextSubmits() {
      return this.textSubmits;
   }

   public List<LeashSubmit> getLeashSubmits() {
      return this.leashSubmits;
   }

   public List<MovingBlockSubmit> getMovingBlockSubmits() {
      return this.movingBlockSubmits;
   }

   public List<BlockModelSubmit> getBlockModelSubmits() {
      return this.blockModelSubmits;
   }

   public List<BreakingBlockModelSubmit> getBreakingBlockModelSubmits() {
      return this.breakingBlockModelSubmits;
   }

   public net.minecraft.client.renderer.feature.ModelPartFeatureRenderer.Storage getModelPartSubmits() {
      return this.modelPartSubmits;
   }

   public List<ItemSubmit> getItemSubmits() {
      return this.itemSubmits;
   }

   public List<SubmitNodeCollector.ParticleGroupRenderer> getParticleGroupRenderers() {
      return this.particleGroupRenderers;
   }

   public net.minecraft.client.renderer.feature.ModelFeatureRenderer.Storage getModelSubmits() {
      return this.modelSubmits;
   }

   public net.minecraft.client.renderer.feature.CustomFeatureRenderer.Storage getCustomGeometrySubmits() {
      return this.customGeometrySubmits;
   }

   public boolean wasUsed() {
      return this.wasUsed;
   }

   public void clear() {
      this.shadowSubmits.clear();
      this.flameSubmits.clear();
      this.nameTagSubmits.clear();
      this.textSubmits.clear();
      this.leashSubmits.clear();
      this.movingBlockSubmits.clear();
      this.blockModelSubmits.clear();
      this.breakingBlockModelSubmits.clear();
      this.itemSubmits.clear();
      this.particleGroupRenderers.clear();
      this.modelSubmits.clear();
      this.customGeometrySubmits.clear();
      this.modelPartSubmits.clear();
   }

   public void endFrame() {
      this.modelSubmits.endFrame();
      this.modelPartSubmits.endFrame();
      this.customGeometrySubmits.endFrame();
      this.wasUsed = false;
   }
}
