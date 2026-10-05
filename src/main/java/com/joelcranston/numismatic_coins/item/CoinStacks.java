package com.joelcranston.numismatic_coins.item;

import java.util.ArrayList;
import java.util.List;

import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.registry.ModItems;
import net.minecraft.world.item.ItemStack;

/** Turns raw values into the item stacks a player is handed. */
public final class CoinStacks {

    private CoinStacks() {}

    /**
     * The fewest coin stacks worth {@code rawValue}, largest coin first, or a single money bag when
     * that would take more than {@code maxStacks} stacks. Zero yields no stacks. (NO: CurrencyHelper#getAsStacks.)
     */
    public static List<ItemStack> forValue(long rawValue, int maxStacks) {

        List<CoinMath.CoinStack> coinStacks = CoinMath.toStacks(rawValue, ModItems.COIN_STACK_SIZE);
        if (coinStacks.size() > maxStacks) return List.of(MoneyBagItem.withValue(rawValue));

        List<ItemStack> stacks = new ArrayList<>(coinStacks.size());
        for (CoinMath.CoinStack coinStack : coinStacks) {
            stacks.add(new ItemStack(ModItems.coin(coinStack.currency()), coinStack.count()));
        }
        return stacks;
    }
}
