package com.joelcranston.numismatic_coins.mixin;

import com.joelcranston.numismatic_coins.item.CoinItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Keeps coins out of bundles, which would otherwise hide them from the purse and from trades.
 * Hooks the bundle's own check so shulker boxes, which share {@code Item#canFitInsideContainerItems},
 * still take coins. (NO: BundleItemMixin.)
 */
@Mixin (BundleContents.class)
public abstract class BundleContentsMixin {

    @Inject (method = "canItemBeInBundle", at = @At ("HEAD"), cancellable = true)
    private static void numismatic_coins$refuseCoins(ItemStack itemToAdd, CallbackInfoReturnable<Boolean> result) {

        if (itemToAdd.getItem() instanceof CoinItem) result.setReturnValue(false);
    }
}
