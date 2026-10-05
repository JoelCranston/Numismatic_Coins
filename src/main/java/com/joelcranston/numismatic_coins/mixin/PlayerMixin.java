package com.joelcranston.numismatic_coins.mixin;

import com.joelcranston.numismatic_coins.drops.DeathPenalty;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Drops part of the purse where a dying player drops their inventory, which is skipped when
 * {@code keep_inventory} is on. (NO: ServerPlayerEntityMixin.)
 */
@Mixin (Player.class)
public abstract class PlayerMixin {

    @Inject (method = "destroyVanishingCursedItems", at = @At ("TAIL"))
    private void numismatic_coins$dropShareOfPurse(CallbackInfo callback) {

        Player player = (Player) (Object) this;
        if (player.level() instanceof ServerLevel level) DeathPenalty.dropShareOfPurse(player, level);
    }
}
