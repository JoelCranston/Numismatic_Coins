package com.joelcranston.numismatic_coins.shop;

import com.joelcranston.numismatic_coins.item.CurrencyItem;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.jspecify.annotations.Nullable;

/**
 * Sells a shop's offers through the vanilla trading screen, one customer at a time. Each trade
 * takes the goods out of the stock, unless the shop is inexhaustible, and adds the payment to the
 * shop's earnings. (NO: ShopMerchant.)
 */
public class ShopMerchant implements Merchant {

    // The trading screen's merchant level; 0 hides the level bar.
    private static final int NO_LEVEL = 0;

    private final ShopBlockEntity shop;
    private MerchantOffers offers = new MerchantOffers();
    @Nullable
    private Player customer;

    public ShopMerchant(ShopBlockEntity shop) {

        this.shop = shop;
    }

    /** Shows {@code player} the shop's offers as they stand now. */
    public void startTrading(Player player) {

        this.refreshOffers();
        this.setTradingPlayer(player);
        this.openTradingScreen(player, Component.translatable("gui.numismatic_coins.shop.merchant_title"), NO_LEVEL);
    }

    private void refreshOffers() {

        this.offers = new MerchantOffers();
        this.shop.offers().forEach(offer -> this.offers.add(offer.toMerchantOffer(this.shop.stock(), this.shop.isInexhaustible())));
    }

    @Override
    public void setTradingPlayer(@Nullable Player player) {

        this.customer = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {

        return this.customer;
    }

    @Override
    public MerchantOffers getOffers() {

        return this.offers;
    }

    @Override
    public void overrideOffers(MerchantOffers offers) {

        this.offers = offers;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {

        offer.increaseUses();
        ItemStack payment = offer.getItemCostA().itemStack();
        if (payment.getItem() instanceof CurrencyItem currency) this.shop.addValue(currency.rawValue(payment));
        if (this.shop.isInexhaustible()) return;

        ShopOffer.remove(this.shop.stock(), offer.getResult());
        this.shop.setChanged();
        this.refreshOffers();
        if (this.customer != null) {
            this.customer.sendMerchantOffers(this.customer.containerMenu.containerId, this.offers, NO_LEVEL, 0, false, false);
        }
    }

    @Override
    public void notifyTradeUpdated(ItemStack stack) {

    }

    @Override
    public int getVillagerXp() {

        return 0;
    }

    @Override
    public void overrideXp(int xp) {

    }

    @Override
    public boolean showProgressBar() {

        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {

        return SoundEvents.VILLAGER_YES;
    }

    @Override
    public boolean isClientSide() {

        return false;
    }

    @Override
    public boolean stillValid(Player player) {

        return this.customer == player && !this.shop.isRemoved() && Container.stillValidBlockEntity(this.shop, player);
    }
}
