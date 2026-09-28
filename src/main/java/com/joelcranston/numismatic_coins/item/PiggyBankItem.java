package com.joelcranston.numismatic_coins.item;

import java.util.Optional;
import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.currency.Currency;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

/** A piggy bank as an item, which shows the coins inside it. (NO: NumismaticOverhaulBlocks#createBlockItem.) */
public class PiggyBankItem extends BlockItem {

    public PiggyBankItem(Block block, Item.Properties properties) {

        super(block, properties);
    }

    /** The coins inside, as bronze, silver and gold counts. */
    public static long[] coinCounts(ItemStack stack) {

        long[] coinCounts = new long[Currency.values().length];
        stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).nonEmptyItemCopyStream()
                .filter(CurrencyItem::isCurrency)
                .forEach(coins -> {
                    long[] counts = ((CurrencyItem) coins.getItem()).coinCounts(coins);
                    for (int denomination = 0; denomination < coinCounts.length; denomination++) coinCounts[denomination] += counts[denomination];
                });
        return coinCounts;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {

        // The coins themselves are the tooltip image; text is left only to say there are none.
        if (!CoinTooltip.hasCoins(coinCounts(stack))) tooltip.accept(CoinText.coinCounts(coinCounts(stack)));
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {

        long[] coinCounts = coinCounts(stack);
        return CoinTooltip.hasCoins(coinCounts) ? Optional.of(new CoinTooltip(coinCounts)) : Optional.empty();
    }
}
