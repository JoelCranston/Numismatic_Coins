package com.joelcranston.numismatic_coins.mixin;

import com.joelcranston.numismatic_coins.trade.PurseTrading;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Makes up a coin cost from the purse once a picked trade has taken what it can from the inventory. */
@Mixin (MerchantMenu.class)
public abstract class MerchantMenuMixin {

    // The menu's slots: the two payments, the result, then the player's inventory.
    @Unique
    private static final int FIRST_PAYMENT_SLOT = 0;
    @Unique
    private static final int SECOND_PAYMENT_SLOT = 1;
    @Unique
    private static final int FIRST_INVENTORY_SLOT = 3;

    @Inject (method = "tryMoveItems", at = @At ("TAIL"))
    private void numismatic_coins$payFromPurse(int newTradeIndex, CallbackInfo callback) {

        MerchantMenu menu = (MerchantMenu) (Object) this;
        if (newTradeIndex < 0 || newTradeIndex >= menu.getOffers().size()) return;
        if (!(menu.getSlot(FIRST_INVENTORY_SLOT).container instanceof Inventory inventory)) return;

        MerchantOffer offer = menu.getOffers().get(newTradeIndex);
        PurseTrading.topUpFromPurse(inventory.player, menu.getSlot(FIRST_PAYMENT_SLOT), offer.getCostA());
        PurseTrading.topUpFromPurse(inventory.player, menu.getSlot(SECOND_PAYMENT_SLOT), offer.getCostB());
    }
}
