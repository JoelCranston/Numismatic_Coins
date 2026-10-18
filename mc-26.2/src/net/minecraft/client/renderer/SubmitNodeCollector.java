package net.minecraft.client.renderer;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack.Pose;

public interface SubmitNodeCollector extends OrderedSubmitNodeCollector {
   OrderedSubmitNodeCollector order(int order);

   public interface CustomGeometryRenderer {
      void render(Pose pose, VertexConsumer buffer);
   }
}
