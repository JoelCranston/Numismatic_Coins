package com.joelcranston.numismatic_coins.shop;

import com.joelcranston.numismatic_coins.item.CurrencyItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;

/**
 * Sells a shop's offers. Each trade adds the payment to the shop's earnings and, unless the shop
 * is inexhaustible, takes the goods out of the stock. (NO: ShopMerchant.)
 */
public class ShopMerchant extends AbstractShopMerchant {

    public ShopMerchant(ShopBlockEntity shop) {

        super(shop);
    }

    @Override
    protected Component title() {

        return Component.translatable("gui.numismatic_coins.shop.merchant_title");
    }

    @Override
    protected MerchantOffer toMerchantOffer(ShopOffer offer) {

        int maxUses = this.shop.isInexhaustible()
                ? Integer.MAX_VALUE
                : ShopOffer.count(this.shop.stock(), offer.stack()) / offer.stack().getCount();
        return new MerchantOffer(ShopOffer.cost(offer.price()), offer.stack().copy(), maxUses, 0, 0);
    }

    @Override
    protected void completeTrade(MerchantOffer offer) {

        ItemStack payment = offer.getItemCostA().itemStack();
        if (payment.getItem() instanceof CurrencyItem currency) this.shop.addValue(currency.rawValue(payment));
        if (!this.shop.isInexhaustible()) ShopOffer.remove(this.shop.stock(), offer.getResult());
    }
}
