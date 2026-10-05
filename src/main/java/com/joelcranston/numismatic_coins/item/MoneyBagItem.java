package com.joelcranston.numismatic_coins.item;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.currency.Currency;
import com.joelcranston.numismatic_coins.registry.ModDataComponents;
import com.joelcranston.numismatic_coins.registry.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.MerchantResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

/**
 * A bag holding any number of coins. Left-clicking coins or another bag onto it adds them;
 * right-clicking it with an empty cursor takes out the largest coin stack it can. (NO: MoneyBagItem.)
 *
 * <p>Every bag stack is made through {@link #withContents}, which also sets the texture tier the
 * item model selects on through {@code minecraft:custom_model_data}.
 */
public class MoneyBagItem extends Item implements CurrencyItem {

    public MoneyBagItem(Item.Properties properties) {

        super(properties);
    }

    public static MoneyBagContents contents(ItemStack stack) {

        return stack.getOrDefault(ModDataComponents.MONEY_BAG.get(), MoneyBagContents.EMPTY);
    }

    /** A bag holding exactly these coins. */
    public static ItemStack withContents(MoneyBagContents contents) {

        ItemStack bag = new ItemStack(ModItems.MONEY_BAG.get());
        bag.set(ModDataComponents.MONEY_BAG.get(), contents);
        bag.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(List.of((float) contents.sizeTier()), List.of(), List.of(), List.of()));
        return bag;
    }

    /** A bag holding the fewest coins that make up {@code rawValue}. */
    public static ItemStack withValue(long rawValue) {

        return withContents(MoneyBagContents.ofValue(rawValue));
    }

    /** One bag holding the coins of both stacks, which must both be currency. */
    public static ItemStack combine(ItemStack first, ItemStack second) {

        long[] firstCoins = ((CurrencyItem) first.getItem()).coinCounts(first);
        long[] secondCoins = ((CurrencyItem) second.getItem()).coinCounts(second);
        return withContents(MoneyBagContents.of(CoinMath.add(firstCoins, secondCoins)));
    }

    @Override
    public long[] coinCounts(ItemStack stack) {

        return contents(stack).coins();
    }

    @Override
    public Component getName(ItemStack stack) {

        return super.getName(stack).copy().withStyle(CoinText.style(Currency.SILVER));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        return CurrencyItem.depositInPurse(level, player, hand);
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

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack carried, Slot slot, ClickAction action, Player player, SlotAccess carriedAccess) {

        if (slot instanceof MerchantResultSlot) return false;
        if (action == ClickAction.SECONDARY && carried.isEmpty()) {
            return takeLargestStack(self, slot, carriedAccess);
        }
        if (action == ClickAction.PRIMARY && CurrencyItem.isCurrency(carried) && !carried.isEmpty()) {
            ItemStack bag = combine(self, carried);
            if (!slot.mayPlace(bag)) return false;
            slot.set(bag);
            carriedAccess.set(ItemStack.EMPTY);
            return true;
        }
        return false;
    }

    /**
     * Moves the bag's largest coin stack to the cursor. What is left becomes a plain coin stack
     * when it fits in one, else stays a bag; an emptied bag disappears.
     */
    private static boolean takeLargestStack(ItemStack bag, Slot slot, SlotAccess carriedAccess) {

        long[] coins = contents(bag).coins();
        List<CoinMath.CoinStack> stacks = CoinMath.toStacks(coins, ModItems.COIN_STACK_SIZE);
        if (stacks.isEmpty()) return false;

        CoinMath.CoinStack taken = stacks.getFirst();
        coins[taken.currency().ordinal()] -= taken.count();
        carriedAccess.set(new ItemStack(ModItems.coin(taken.currency()), taken.count()));

        List<CoinMath.CoinStack> remaining = CoinMath.toStacks(coins, ModItems.COIN_STACK_SIZE);
        if (remaining.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else if (remaining.size() == 1) {
            CoinMath.CoinStack last = remaining.getFirst();
            slot.set(new ItemStack(ModItems.coin(last.currency()), last.count()));
        } else {
            slot.set(withContents(MoneyBagContents.of(coins)));
        }
        return true;
    }
}
