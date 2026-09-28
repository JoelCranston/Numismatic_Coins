package com.joelcranston.numismatic_coins.purse;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.item.CoinStacks;
import com.joelcranston.numismatic_coins.item.CurrencyItem;
import com.joelcranston.numismatic_coins.network.PurseChanged;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * Reads and changes players' purses. Changes are made on the server only; each one the player
 * causes is reported to their client. (NO: CurrencyComponent#modify, CurrencyHelper.)
 */
public final class Purses {

    /** A withdrawal that would take more coin stacks than this is handed over as one money bag. */
    public static final int MAX_WITHDRAWN_STACKS = 9;

    private Purses() {}

    public static long balance(Player player) {

        return storage().get(player).balance();
    }

    /** Sets the balance without telling the player, as commands do. */
    public static void setBalance(Player player, long balance) {

        storage().set(player, new Purse(Math.max(0, balance)));
    }

    /** Adds money to the purse and shows the player how much. */
    public static void deposit(Player player, long rawValue) {

        if (rawValue <= 0) return;
        setBalance(player, saturatedAdd(balance(player), rawValue));
        showChange(player, rawValue, true);
    }

    /**
     * Takes up to {@code requested} out of the purse as coins, clamped to the balance so a forged
     * request cannot take more. Returns the amount taken.
     */
    public static long withdraw(Player player, long requested) {

        long taken = Math.clamp(requested, 0, balance(player));
        if (taken == 0) return 0;
        setBalance(player, balance(player) - taken);
        for (ItemStack stack : CoinStacks.forValue(taken, MAX_WITHDRAWN_STACKS)) {
            player.getInventory().placeItemBackInInventory(stack);
        }
        showChange(player, taken, false);
        return taken;
    }

    /**
     * Takes {@code rawValue} out of the purse as a payment, with no coins handed over, and shows the
     * player how much. Takes nothing and returns false when the balance is short.
     */
    public static boolean spend(Player player, long rawValue) {

        if (rawValue <= 0 || balance(player) < rawValue) return false;
        setBalance(player, balance(player) - rawValue);
        showChange(player, rawValue, false);
        return true;
    }

    /** Moves every coin and money bag in the player's inventory into the purse. Returns the amount stored. */
    public static long storeAll(Player player) {

        long stored = takeFromInventory(player);
        deposit(player, stored);
        return stored;
    }

    /** Takes every coin and money bag out of the player's inventory. Returns their value. */
    public static long takeFromInventory(Player player) {

        Inventory inventory = player.getInventory();
        long taken = 0;
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack stack = inventory.getItem(slot);
            if (!(stack.getItem() instanceof CurrencyItem currencyItem)) continue;
            taken = saturatedAdd(taken, currencyItem.rawValue(stack));
            inventory.setItem(slot, ItemStack.EMPTY);
        }
        return taken;
    }

    // The client shows the change where its money message option says.
    private static void showChange(Player player, long rawValue, boolean isDeposit) {

        if (player instanceof ServerPlayer serverPlayer) {
            NumismaticCoins.xplat().sendToPlayer(serverPlayer, new PurseChanged(isDeposit ? rawValue : -rawValue));
        }
    }

    private static long saturatedAdd(long first, long second) {

        long sum = first + second;
        return sum < 0 ? Long.MAX_VALUE : sum;
    }

    private static PurseStorage storage() {

        return NumismaticCoins.xplat().purseStorage();
    }
}
