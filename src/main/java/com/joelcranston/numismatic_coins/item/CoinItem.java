package com.joelcranston.numismatic_coins.item;

import com.joelcranston.numismatic_coins.currency.Currency;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.MerchantResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * A bronze, silver or gold coin. Left-clicking a different coin, or a stack that would overflow,
 * onto a coin stack turns both into one money bag. (NO: CoinItem.)
 */
public class CoinItem extends Item implements CurrencyItem {

    private final Currency currency;

    public CoinItem(Currency currency, Item.Properties properties) {

        super(properties);
        this.currency = currency;
    }

    public Currency currency() {

        return this.currency;
    }

    @Override
    public long[] coinCounts(ItemStack stack) {

        long[] coinCounts = new long[Currency.values().length];
        coinCounts[this.currency.ordinal()] = stack.getCount();
        return coinCounts;
    }

    @Override
    public Component getName(ItemStack stack) {

        return super.getName(stack).copy().withStyle(CoinText.style(this.currency));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        return CurrencyItem.depositInPurse(level, player, hand);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack carried, Slot slot, ClickAction action, Player player, SlotAccess carriedAccess) {

        if (action != ClickAction.PRIMARY || slot instanceof MerchantResultSlot) return false;
        if (!CurrencyItem.isCurrency(carried) || carried.isEmpty()) return false;
        // Vanilla merges a matching coin stack that fits; only a mix or an overflow becomes a bag.
        boolean isMatchingStackThatFits = carried.is(this) && carried.getCount() + self.getCount() <= self.getMaxStackSize();
        if (isMatchingStackThatFits) return false;

        ItemStack bag = MoneyBagItem.combine(self, carried);
        if (!slot.mayPlace(bag)) return false;
        slot.set(bag);
        carriedAccess.set(ItemStack.EMPTY);
        return true;
    }
}
