package com.joelcranston.numismatic_coins.mixin;

import com.joelcranston.numismatic_coins.trade.CoinTradePrices;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.LootContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Charges a new offer the coin price its result was given by {@code numismatic_coins:enchantment_price}. */
@Mixin (VillagerTrade.class)
public abstract class VillagerTradeMixin {

    @Inject (method = "getOffer", at = @At ("RETURN"), cancellable = true)
    private void numismatic_coins$chargeResultPrice(LootContext lootContext, CallbackInfoReturnable<MerchantOffer> result) {

        MerchantOffer offer = result.getReturnValue();
        if (offer != null) result.setReturnValue(CoinTradePrices.withPriceFromResult(offer));
    }
}
