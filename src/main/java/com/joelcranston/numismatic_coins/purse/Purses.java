package com.joelcranston.numismatic_coins.purse;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.item.CoinStacks;
import com.joelcranston.numismatic_coins.item.CoinText;
import com.joelcranston.numismatic_coins.item.CurrencyItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * Reads and changes players' purses. Changes are made on the server only; each one the player
 * causes shows in their action bar. (NO: CurrencyComponent#modify, CurrencyHelper.)
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

    /** Moves every coin and money bag in the player's inventory into the purse. Returns the amount stored. */
    public static long storeAll(Player player) {

        Inventory inventory = player.getInventory();
        long stored = 0;
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack stack = inventory.getItem(slot);
            if (!(stack.getItem() instanceof CurrencyItem currencyItem)) continue;
            stored = saturatedAdd(stored, currencyItem.rawValue(stack));
            inventory.setItem(slot, ItemStack.EMPTY);
        }
        deposit(player, stored);
        return stored;
    }

    /** Action bar text such as "+ [12 Silver 4 Bronze]". (NO: CurrencyComponent#modify.) */
    private static void showChange(Player player, long rawValue, boolean isDeposit) {

        MutableComponent message = Component.literal(isDeposit ? "+ " : "- ")
                .withStyle(isDeposit ? ChatFormatting.GREEN : ChatFormatting.RED)
                .append(Component.literal("[").withStyle(ChatFormatting.GRAY))
                .append(CoinText.coinCounts(CoinMath.split(rawValue)))
                .append(Component.literal("]").withStyle(ChatFormatting.GRAY));
        player.sendOverlayMessage(message);
    }

    private static long saturatedAdd(long first, long second) {

        long sum = first + second;
        return sum < 0 ? Long.MAX_VALUE : sum;
    }

    private static PurseStorage storage() {

        return NumismaticCoins.xplat().purseStorage();
    }
}
