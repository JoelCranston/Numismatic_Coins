package com.joelcranston.numismatic_coins.mixin;

import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.drops.MobDrops;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Drops a dying mob's coins right after its own loot, and pays out the items in
 * {@code numismatic_coins:dropped_as_coins} that any loot table drops for it as coins.
 * (NO: LivingEntityMixin.)
 */
@Mixin (LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject (method = "dropFromLootTable(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;Z)V", at = @At ("TAIL"))
    private void numismatic_coins$dropCoins(ServerLevel level, DamageSource source, boolean isKilledByPlayer, CallbackInfo callback) {

        MobDrops.dropCoins((LivingEntity) (Object) this, level, source, isKilledByPlayer);
    }

    @ModifyVariable (method = "dropFromLootTable(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;ZLnet/minecraft/resources/ResourceKey;Ljava/util/function/Consumer;)V", at = @At ("HEAD"), argsOnly = true)
    private Consumer<ItemStack> numismatic_coins$payTaggedDropsInCoins(Consumer<ItemStack> output) {

        return MobDrops.payingTaggedDropsInCoins(output);
    }
}
