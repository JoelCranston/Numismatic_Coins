package com.joelcranston.numismatic_coins.trade;

import com.joelcranston.numismatic_coins.item.CoinItem;
import com.joelcranston.numismatic_coins.item.MoneyBagItem;
import com.joelcranston.numismatic_coins.purse.Purses;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/**
 * Lets the purse pay a merchant. When a trade is picked, the menu first fills its payment slots
 * from the inventory as usual; a coin cost the inventory left short is then made up from the
 * purse. (NO: MerchantScreenHandlerMixin.)
 */
public final class PurseTrading {

    private PurseTrading() {}

    /**
     * Fills {@code paymentSlot} up to {@code cost} from the player's purse, if the cost is coins or
     * a money bag, the slot holds nothing else, and the purse holds enough for the whole shortfall.
     * Server only; the filled slot reaches the client through the menu's own sync.
     */
    public static void topUpFromPurse(Player player, Slot paymentSlot, ItemStack cost) {

        if (player.level().isClientSide()) return;
        if (cost.getItem() instanceof MoneyBagItem bag) {
            // A money bag price is paid with one exact bag, made from the purse when the slot is empty.
            if (paymentSlot.getItem().isEmpty() && Purses.spend(player, bag.rawValue(cost))) paymentSlot.set(cost.copy());
            return;
        }
        if (!(cost.getItem() instanceof CoinItem coin)) return;
        ItemStack paid = paymentSlot.getItem();
        if (!paid.isEmpty() && !paid.is(coin)) return;

        int missingCoins = cost.getCount() - paid.getCount();
        if (missingCoins <= 0) return;
        if (!Purses.spend(player, coin.currency().rawValue(missingCoins))) return;
        paymentSlot.set(cost.copy());
    }
}
