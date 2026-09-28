package com.joelcranston.numismatic_coins.trade;

import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.registry.ModDataComponents;
import com.joelcranston.numismatic_coins.registry.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

/**
 * Turns a price left on a new offer's result by {@link EnchantmentPriceFunction} into the offer's
 * first cost, in the one kind of coin that price is closest to. A trade's {@code wants} is fixed in
 * its JSON, so a price that depends on what the villager rolled has to be set here.
 */
public final class CoinTradePrices {

    private CoinTradePrices() {}

    /** The offer charging the price its result carries, or the offer itself when it carries none. */
    public static MerchantOffer withPriceFromResult(MerchantOffer offer) {

        Long price = offer.getResult().get(ModDataComponents.TRADE_PRICE.get());
        if (price == null) return offer;

        ItemStack result = offer.getResult().copy();
        result.remove(ModDataComponents.TRADE_PRICE.get());
        CoinMath.CoinStack coins = CoinMath.closestCoin(price);
        ItemCost cost = new ItemCost(ModItems.coin(coins.currency()), Math.clamp(coins.count(), 1, ModItems.COIN_STACK_SIZE));
        return new MerchantOffer(cost, offer.getItemCostB(), result, offer.getMaxUses(), offer.getXp(), offer.getPriceMultiplier());
    }
}
