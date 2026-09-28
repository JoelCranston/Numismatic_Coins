package com.joelcranston.numismatic_coins.shop;

import com.joelcranston.numismatic_coins.item.CurrencyItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

/**
 * Buys a pawn shop's offers: the customer hands over the goods, whatever their components, and
 * takes the price. An offer runs out when the shop's money or stock room does, unless the shop is
 * inexhaustible, which pays from nothing and keeps nothing. (NO: PawnShopMerchant, PawnShopOffer.)
 */
public class PawnShopMerchant extends AbstractShopMerchant {

    public PawnShopMerchant(PawnShopBlockEntity shop) {

        super(shop);
    }

    @Override
    protected Component title() {

        return Component.translatable("gui.numismatic_coins.pawn_shop.merchant_title");
    }

    @Override
    protected MerchantOffer toMerchantOffer(ShopOffer offer) {

        ItemStack goods = offer.stack();
        int maxUses;
        if (this.shop.isInexhaustible()) {
            maxUses = Integer.MAX_VALUE;
        } else {
            maxUses = ShopOffer.fits(this.shop.stock(), goods) ? (int) Math.min(this.shop.storedValue() / offer.price(), Integer.MAX_VALUE) : 0;
        }
        return new MerchantOffer(new ItemCost(goods.getItem(), goods.getCount()), ShopOffer.money(offer.price()), maxUses, 0, 0);
    }

    @Override
    protected void completeTrade(MerchantOffer offer) {

        if (this.shop.isInexhaustible()) return;
        ShopOffer.add(this.shop.stock(), offer.getItemCostA().itemStack());
        ItemStack paid = offer.getResult();
        if (paid.getItem() instanceof CurrencyItem currency) this.shop.removeValue(currency.rawValue(paid));
    }
}
