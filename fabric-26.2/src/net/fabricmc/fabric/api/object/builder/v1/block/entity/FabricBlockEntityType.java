package net.fabricmc.fabric.api.object.builder.v1.block.entity;

import net.minecraft.world.level.block.Block;

public interface FabricBlockEntityType {
   default void addValidBlock(Block block) {
      throw new AssertionError("Implemented in Mixin");
   }
}
