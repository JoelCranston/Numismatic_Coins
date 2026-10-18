package net.minecraft.client.renderer;

import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import net.minecraft.client.renderer.feature.ParticleFeatureRenderer.ParticleBufferCache;
import net.minecraft.client.renderer.state.level.QuadParticleRenderState.PreparedBuffers;
import net.minecraft.client.renderer.texture.TextureManager;
import org.jspecify.annotations.Nullable;

public interface SubmitNodeCollector extends OrderedSubmitNodeCollector {
   OrderedSubmitNodeCollector order(int order);

   public interface CustomGeometryRenderer {
      void render(Pose pose, VertexConsumer buffer);
   }

   public interface ParticleGroupRenderer {
      boolean isEmpty();

      @Nullable
      PreparedBuffers prepare(ParticleBufferCache buffer, boolean translucent);

      void render(PreparedBuffers buffers, ParticleBufferCache bufferCache, RenderPass renderPass, TextureManager textureManager);
   }
}
