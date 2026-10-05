package com.joelcranston.numismatic_coins.drops;

import com.joelcranston.numismatic_coins.config.Configs;
import com.joelcranston.numismatic_coins.item.CoinStacks;
import com.joelcranston.numismatic_coins.purse.Purses;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * On death, a player drops the {@code money_drop_percentage} share of their purse as coins, along
 * with their inventory; with {@code keep_inventory} on they keep it all, as they keep their items.
 * (NO: ServerPlayerEntityMixin#onServerDeath.)
 */
public final class DeathPenalty {

    private DeathPenalty() {}

    /** Called where the player's inventory is dropped on death, on the server. */
    public static void dropShareOfPurse(Player player, ServerLevel level) {

        if (!Configs.isFeatureEnabled("deathPenalty")) return;
        int percentage = level.getGameRules().get(ModGameRules.MONEY_DROP_PERCENTAGE.get());
        long balance = Purses.balance(player);
        long dropped = balance / 100 * percentage + balance % 100 * percentage / 100;
        if (dropped <= 0) return;

        Purses.setBalance(player, balance - dropped);
        for (ItemStack stack : CoinStacks.forValue(dropped, Purses.MAX_WITHDRAWN_STACKS)) {
            player.drop(stack, true, false);
        }
    }
}
