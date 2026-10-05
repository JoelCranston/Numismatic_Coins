package com.joelcranston.numismatic_coins.item;

import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.purse.Purses;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/** An item that holds money: a coin stack or a money bag. */
public interface CurrencyItem {

    /** The coins the stack holds, as bronze, silver and gold counts. */
    long[] coinCounts(ItemStack stack);

    /** The stack's raw value in bronze. */
    default long rawValue(ItemStack stack) {

        return CoinMath.combine(coinCounts(stack));
    }

    static boolean isCurrency(ItemStack stack) {

        return stack.getItem() instanceof CurrencyItem;
    }

    /** Using a coin stack or money bag puts all of it into the player's purse. (NO: CoinItem#use.) */
    static InteractionResult depositInPurse(Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);
        if (!(stack.getItem() instanceof CurrencyItem currencyItem)) return InteractionResult.PASS;
        if (!level.isClientSide()) {
            Purses.deposit(player, currencyItem.rawValue(stack));
            player.setItemInHand(hand, ItemStack.EMPTY);
        }
        return InteractionResult.SUCCESS;
    }
}
