package net.minecraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState.LeashState;
import net.minecraft.client.renderer.entity.state.EntityRenderState.ShadowPiece;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.Submit;
import net.minecraft.client.renderer.feature.phase.FeatureRenderPhase;
import net.minecraft.client.renderer.feature.phase.SimpleFeatureRenderPhase;
import net.minecraft.client.renderer.feature.phase.TranslucentFeatureRenderPhase;
import net.minecraft.client.renderer.feature.submit.TranslucentSubmit;
import net.minecraft.client.renderer.gizmos.DrawableGizmoPrimitives.Group;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.jspecify.annotations.Nullable;

public class SubmitNodeCollection implements OrderedSubmitNodeCollector {
   public final SimpleFeatureRenderPhase solid = new SimpleFeatureRenderPhase();
   public final SimpleFeatureRenderPhase shadows = new SimpleFeatureRenderPhase();
   public final SimpleFeatureRenderPhase nameTags = new SimpleFeatureRenderPhase();
   public final TranslucentFeatureRenderPhase seeThroughNameTags = new TranslucentFeatureRenderPhase();
   public final SimpleFeatureRenderPhase texts = new SimpleFeatureRenderPhase();
   public final SimpleFeatureRenderPhase shapeOutlines = new SimpleFeatureRenderPhase();
   public final TranslucentFeatureRenderPhase translucentBlocksAndItems = new TranslucentFeatureRenderPhase();
   public final TranslucentFeatureRenderPhase translucentModels = new TranslucentFeatureRenderPhase();
   public final SimpleFeatureRenderPhase translucentCustomGeometry = new SimpleFeatureRenderPhase();
   public final SimpleFeatureRenderPhase gizmos = new SimpleFeatureRenderPhase();
   public final SimpleFeatureRenderPhase breakingOverlay = new SimpleFeatureRenderPhase();
   public final SimpleFeatureRenderPhase waterMask = new SimpleFeatureRenderPhase();
   public final SimpleFeatureRenderPhase afterTerrain = new SimpleFeatureRenderPhase();
   public final SimpleFeatureRenderPhase alwaysOnTop = new SimpleFeatureRenderPhase();
   public final SimpleFeatureRenderPhase outline = new SimpleFeatureRenderPhase();
   private final List<FeatureRenderPhase<?>> allPhases = List.of(
      this.solid,
      this.shadows,
      this.nameTags,
      this.seeThroughNameTags,
      this.texts,
      this.shapeOutlines,
      this.translucentBlocksAndItems,
      this.translucentModels,
      this.translucentCustomGeometry,
      this.gizmos,
      this.breakingOverlay,
      this.waterMask,
      this.afterTerrain,
      this.alwaysOnTop,
      this.outline
   );

   @Override
   public void submitShadow(final PoseStack poseStack, final float radius, final List<ShadowPiece> pieces) {
      Pose pose = poseStack.last();
      this.shadows.submit(new net.minecraft.client.renderer.feature.ShadowFeatureRenderer.Submit(new Matrix4f(pose.pose()), radius, pieces));
   }

   @Override
   public void submitNameTag(
      final PoseStack poseStack,
      @Nullable final Vec3 nameTagAttachment,
      final int offset,
      final Component name,
      final boolean seeThrough,
      final int lightCoords,
      final CameraRenderState camera
   ) {
      if (nameTagAttachment != null) {
         Minecraft minecraft = Minecraft.getInstance();
         poseStack.pushPose();
         poseStack.translate(nameTagAttachment.x, nameTagAttachment.y + 0.5, nameTagAttachment.z);
         poseStack.mulPose(camera.orientation);
         poseStack.scale(0.025F, -0.025F, 0.025F);
         Matrix4f pose = new Matrix4f(poseStack.last().pose());
         float x = -minecraft.font.width(name) / 2.0F;
         int backgroundColor = ARGB.color(minecraft.gameRenderer.gameRenderState().optionsRenderState.getBackgroundOpacity(0.25F), -16777216);
         if (seeThrough) {
            this.nameTags
               .submit(
                  new net.minecraft.client.renderer.feature.NameTagFeatureRenderer.Submit(
                     pose, x, offset, name, LightCoordsUtil.lightCoordsWithEmission(lightCoords, 2), -1, 0, DisplayMode.NORMAL
                  )
               );
            this.seeThroughNameTags
               .submit(
                  (TranslucentSubmit)(new net.minecraft.client.renderer.feature.NameTagFeatureRenderer.Submit(
                     pose, x, offset, name, lightCoords, -2130706433, backgroundColor, DisplayMode.SEE_THROUGH
                  ))
               );
         } else {
            this.nameTags
               .submit(
                  new net.minecraft.client.renderer.feature.NameTagFeatureRenderer.Submit(
                     pose, x, offset, name, lightCoords, -2130706433, backgroundColor, DisplayMode.NORMAL
                  )
               );
         }

         poseStack.popPose();
      }
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
      this.texts
         .submit(
            new net.minecraft.client.renderer.feature.TextFeatureRenderer.Submit(
               new Matrix4f(poseStack.last().pose()), x, y, string, dropShadow, displayMode, lightCoords, color, backgroundColor, outlineColor
            )
         );
   }

   @Override
   public void submitFlame(final PoseStack poseStack, final EntityRenderState renderState, final Quaternionf rotation) {
      this.solid.submit(new net.minecraft.client.renderer.feature.FlameFeatureRenderer.Submit(poseStack.last().copy(), renderState, rotation));
   }

   @Override
   public void submitLeash(final PoseStack poseStack, final LeashState leashState) {
      this.solid.submit(new net.minecraft.client.renderer.feature.LeashFeatureRenderer.Submit(new Matrix4f(poseStack.last().pose()), leashState));
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
      Pose pose = poseStack.last().copy();
      if (!renderType.isOutline()) {
         Submit<S> submit = new Submit<>(renderType, pose, model, state, lightCoords, overlayCoords, tintedColor, sprite, null);
         if (renderType == RenderTypes.waterMask()) {
            this.waterMask.submit(submit);
         } else if (renderType.hasBlending()) {
            this.translucentModels.submit((TranslucentSubmit)submit);
         } else {
            this.solid.submit(submit);
         }
      }

      if (outlineColor != 0) {
         RenderType outlineRenderType = getOutlineRenderType(renderType);
         if (outlineRenderType != null) {
            this.outline.submit(new Submit<>(outlineRenderType, pose, model, state, 15728880, OverlayTexture.NO_OVERLAY, outlineColor, sprite, null));
         }
      }

      if (crumblingOverlay != null && renderType.affectsCrumbling()) {
         RenderType crumblingRenderType = ModelBakery.DESTROY_TYPES.get(crumblingOverlay.progress());
         this.breakingOverlay
            .submit(new Submit<>(crumblingRenderType, pose, model, state, lightCoords, overlayCoords, tintedColor, null, crumblingOverlay.cameraPose()));
      }
   }

   @Override
   public void submitMovingBlock(final PoseStack poseStack, final MovingBlockRenderState movingBlockRenderState, final int outlineColor) {
      net.minecraft.client.renderer.feature.MovingBlockFeatureRenderer.Submit submit = new net.minecraft.client.renderer.feature.MovingBlockFeatureRenderer.Submit(
         new Matrix4f(poseStack.last().pose()), movingBlockRenderState, 0
      );
      BlockStateModel model = Minecraft.getInstance().getModelManager().getBlockStateModelSet().get(movingBlockRenderState.blockState);
      if (model.hasMaterialFlag(1)) {
         this.translucentBlocksAndItems.submit((TranslucentSubmit)submit);
      } else {
         this.solid.submit(submit);
      }

      if (outlineColor != 0) {
         this.outline
            .submit(
               new net.minecraft.client.renderer.feature.MovingBlockFeatureRenderer.Submit(
                  new Matrix4f(poseStack.last().pose()), movingBlockRenderState, outlineColor
               )
            );
      }
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
      Pose pose = poseStack.last().copy();
      if (!renderType.isOutline()) {
         net.minecraft.client.renderer.feature.BlockModelFeatureRenderer.Submit submit = new net.minecraft.client.renderer.feature.BlockModelFeatureRenderer.Submit(
            pose, renderType, modelParts, tintLayers, lightCoords, overlayCoords, -1, null
         );
         if (renderType.hasBlending()) {
            this.translucentBlocksAndItems.submit((TranslucentSubmit)submit);
         } else {
            this.solid.submit(submit);
         }
      }

      if (outlineColor != 0) {
         RenderType outlineRenderType = getOutlineRenderType(renderType);
         if (outlineRenderType != null) {
            this.outline
               .submit(
                  new net.minecraft.client.renderer.feature.BlockModelFeatureRenderer.Submit(
                     pose, outlineRenderType, modelParts, BlockModelRenderState.EMPTY_TINTS, 15728880, OverlayTexture.NO_OVERLAY, outlineColor, null
                  )
               );
         }
      }
   }

   @Nullable
   private static RenderType getOutlineRenderType(final RenderType renderType) {
      if (renderType.isOutline()) {
         return renderType;
      } else {
         return renderType.outline().isPresent() ? renderType.outline().get() : null;
      }
   }

   @Override
   public void submitBreakingBlockModel(final PoseStack poseStack, final List<BlockStateModelPart> parts, final int progress) {
      Pose pose = poseStack.last().copy();
      this.breakingOverlay
         .submit(
            new net.minecraft.client.renderer.feature.BlockModelFeatureRenderer.Submit(
               pose,
               ModelBakery.DESTROY_TYPES.get(progress),
               List.copyOf(parts),
               BlockModelRenderState.EMPTY_TINTS,
               15728880,
               OverlayTexture.NO_OVERLAY,
               0,
               pose
            )
         );
   }

   @Override
   public void submitShapeOutline(
      final PoseStack poseStack, final VoxelShape shape, final RenderType renderType, final int color, final float width, final boolean afterTerrain
   ) {
      net.minecraft.client.renderer.feature.ShapeOutlineFeatureRenderer.Submit submit = new net.minecraft.client.renderer.feature.ShapeOutlineFeatureRenderer.Submit(
         poseStack.last().copy(), shape, renderType, color, width
      );
      if (afterTerrain) {
         this.afterTerrain.submit(submit);
      } else {
         this.shapeOutlines.submit(submit);
      }
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
      Pose pose = poseStack.last().copy();
      net.minecraft.client.renderer.feature.ItemFeatureRenderer.Submit submit = new net.minecraft.client.renderer.feature.ItemFeatureRenderer.Submit(
         pose, displayContext, lightCoords, overlayCoords, 0, tintLayers, quads, foilType
      );
      if (submit.hasTranslucency()) {
         this.translucentBlocksAndItems.submit((TranslucentSubmit)submit);
      } else {
         this.solid.submit(submit);
      }

      if (outlineColor != 0) {
         this.outline
            .submit(
               new net.minecraft.client.renderer.feature.ItemFeatureRenderer.Submit(
                  pose,
                  displayContext,
                  15728880,
                  OverlayTexture.NO_OVERLAY,
                  outlineColor,
                  ItemStackRenderState.LayerRenderState.EMPTY_TINTS,
                  quads,
                  ItemStackRenderState.FoilType.NONE
               )
            );
      }
   }

   @Override
   public void submitCustomGeometry(
      final PoseStack poseStack, final RenderType renderType, final SubmitNodeCollector.CustomGeometryRenderer customGeometryRenderer
   ) {
      net.minecraft.client.renderer.feature.CustomFeatureRenderer.Submit submit = new net.minecraft.client.renderer.feature.CustomFeatureRenderer.Submit(
         poseStack.last().copy(), renderType, customGeometryRenderer
      );
      if (renderType.isOutline()) {
         this.outline.submit(submit);
      } else if (renderType.hasBlending()) {
         this.translucentCustomGeometry.submit(submit);
      } else {
         this.solid.submit(submit);
      }
   }

   @Override
   public void submitQuadParticleGroup(final QuadParticleRenderState particles) {
      this.solid.submit(new net.minecraft.client.renderer.feature.QuadParticleFeatureRenderer.Submit(particles, false));
      this.afterTerrain.submit(new net.minecraft.client.renderer.feature.QuadParticleFeatureRenderer.Submit(particles, true));
   }

   @Override
   public void submitGizmoPrimitives(final Group group, final CameraRenderState camera, final boolean onTop) {
      net.minecraft.client.renderer.feature.GizmoFeatureRenderer.Submit submit = new net.minecraft.client.renderer.feature.GizmoFeatureRenderer.Submit(
         group, camera
      );
      if (onTop) {
         this.alwaysOnTop.submit(submit);
      } else {
         this.gizmos.submit(submit);
      }
   }

   public List<FeatureRenderPhase<?>> allPhases() {
      return this.allPhases;
   }
}
