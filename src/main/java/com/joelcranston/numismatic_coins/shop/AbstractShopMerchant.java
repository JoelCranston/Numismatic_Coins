package com.joelcranston.numismatic_coins.shop;

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
 * Trades a shop's offers through the vanilla trading screen, one customer at a time. The offers
 * are rebuilt from the shop as each customer arrives and after each trade, so their uses follow
 * the stock and money left. (NO: ShopMerchant, PawnShopMerchant.)
 */
public abstract class AbstractShopMerchant implements Merchant {

    // The trading screen's merchant level; 0 hides the level bar.
    private static final int NO_LEVEL = 0;

    protected final AbstractShopBlockEntity shop;
    private MerchantOffers offers = new MerchantOffers();
    @Nullable
    private Player customer;

    protected AbstractShopMerchant(AbstractShopBlockEntity shop) {

        this.shop = shop;
    }

    /** Shows {@code player} the shop's offers as they stand now. */
    public void startTrading(Player player) {

        this.refreshOffers();
        this.setTradingPlayer(player);
        this.openTradingScreen(player, this.title(), NO_LEVEL);
    }

    /** The trading screen's title. */
    protected abstract Component title();

    /** The trade a customer sees for {@code offer}, with as many uses as the shop can cover. */
    protected abstract MerchantOffer toMerchantOffer(ShopOffer offer);

    /** Moves the goods and money of one trade, which the trading menu has already handed over. */
    protected abstract void completeTrade(MerchantOffer offer);

    private void refreshOffers() {

        this.offers = new MerchantOffers();
        this.shop.offers().forEach(offer -> this.offers.add(this.toMerchantOffer(offer)));
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
        this.completeTrade(offer);
        this.shop.setChanged();
        if (this.shop.isInexhaustible()) return;

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
