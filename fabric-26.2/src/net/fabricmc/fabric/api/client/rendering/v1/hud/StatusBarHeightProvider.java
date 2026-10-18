package net.fabricmc.fabric.api.client.rendering.v1.hud;

import java.util.function.ToIntFunction;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

@FunctionalInterface
public interface StatusBarHeightProvider extends ToIntFunction<Player> {
   int getStatusBarHeight(Player var1);

   @NonExtendable
   default int applyAsInt(Player player) {
      return this.getStatusBarHeight(player);
   }
}
