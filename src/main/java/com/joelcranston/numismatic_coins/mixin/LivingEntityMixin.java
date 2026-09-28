package com.joelcranston.numismatic_coins.mixin;

import com.joelcranston.numismatic_coins.drops.MobDrops;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Drops a dying mob's coins right after its own loot. (NO: LivingEntityMixin.) */
@Mixin (LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject (method = "dropFromLootTable(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;Z)V", at = @At ("TAIL"))
    private void numismatic_coins$dropCoins(ServerLevel level, DamageSource source, boolean isKilledByPlayer, CallbackInfo callback) {

        MobDrops.dropCoins((LivingEntity) (Object) this, level, source, isKilledByPlayer);
    }
}
