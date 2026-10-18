package net.fabricmc.fabric.api.gamerule.v1;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.impl.gamerule.GameRuleEventsImpl;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.gamerules.GameRule;

public final class GameRuleEvents {
   private GameRuleEvents() {
   }

   public static <T> Event<GameRuleEvents.ValueUpdate<T>> changeCallback(GameRule<T> gameRule) {
      return GameRuleEventsImpl.changeCallback(gameRule);
   }

   @FunctionalInterface
   public interface ValueUpdate<T> {
      void onGameRuleUpdated(T var1, MinecraftServer var2);
   }
}
