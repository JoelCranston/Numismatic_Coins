package net.fabricmc.fabric.api.gamerule.v1;

import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;

public interface FabricGameRuleTypeVisitor extends GameRuleTypeVisitor {
   default void visitDouble(GameRule<Double> doubleRule) {
   }

   default <E extends Enum<E>> void visitEnum(GameRule<E> enumRule) {
   }
}
