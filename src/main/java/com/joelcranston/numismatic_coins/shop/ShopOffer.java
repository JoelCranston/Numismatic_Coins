package com.joelcranston.numismatic_coins.shop;

import java.util.List;

import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.item.MoneyBagItem;
import com.joelcranston.numismatic_coins.registry.ModDataComponents;
import com.joelcranston.numismatic_coins.registry.ModItems;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;

/**
 * One offer of a shop or pawn shop: the stack it sells or buys, and its price in bronze. A price
 * changes hands as coins when one kind of coin pays it, and as a money bag holding exactly that
 * value otherwise. (NO: ShopOffer, PawnShopOffer.)
 */
public record ShopOffer(ItemStack stack, long price) {

    /** The highest price the owner's screen takes: seven digits. (NO: ShopScreen.) */
    public static final long MAX_PRICE = 9_999_999;

    private static final Codec<Long> PRICE_CODEC = Codec.LONG.validate(
            price -> price > 0 && price <= MAX_PRICE ? DataResult.success(price) : DataResult.error(() -> "Price out of range: " + price));

    public static final Codec<ShopOffer> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.fieldOf("stack").forGetter(ShopOffer::stack),
            PRICE_CODEC.fieldOf("price").forGetter(ShopOffer::price)
    ).apply(instance, ShopOffer::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShopOffer> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, ShopOffer::stack,
            ByteBufCodecs.VAR_LONG, ShopOffer::price,
            ShopOffer::new);

    public ShopOffer {

        stack = stack.copy();
    }

    /** The money that pays {@code price}: coins of one kind, or an exact money bag. */
    public static ItemStack money(long price) {

        CoinMath.CoinStack coins = CoinMath.closestCoin(price);
        if (coins.currency().rawValue(coins.count()) == price && coins.count() <= ModItems.COIN_STACK_SIZE) {
            return new ItemStack(ModItems.coin(coins.currency()), (int) coins.count());
        }
        return MoneyBagItem.withValue(price);
    }

    /** What a customer hands over for {@code price}: {@link #money}, the bag matched exactly. */
    public static ItemCost cost(long price) {

        ItemStack money = money(price);
        if (!(money.getItem() instanceof MoneyBagItem)) return new ItemCost(money.getItem(), money.getCount());
        return new ItemCost(money.getItem()).withComponents(components -> components
                .expect(ModDataComponents.MONEY_BAG.get(), money.get(ModDataComponents.MONEY_BAG.get()))
                .expect(DataComponents.CUSTOM_MODEL_DATA, money.get(DataComponents.CUSTOM_MODEL_DATA)));
    }

    /** How many items in {@code stacks} match {@code target}, components included. */
    public static int count(List<ItemStack> stacks, ItemStack target) {

        int count = 0;
        for (ItemStack stack : stacks) {
            if (ItemStack.isSameItemSameComponents(stack, target)) count += stack.getCount();
        }
        return count;
    }

    /** Takes {@code target}'s count of matching items out of {@code stacks}. */
    public static void remove(List<ItemStack> stacks, ItemStack target) {

        int toRemove = target.getCount();
        for (ItemStack stack : stacks) {
            if (toRemove <= 0) return;
            if (!ItemStack.isSameItemSameComponents(stack, target)) continue;
            int removed = Math.min(toRemove, stack.getCount());
            stack.shrink(removed);
            toRemove -= removed;
        }
    }

    /** Whether all of {@code stack} fits into {@code stacks}, onto matching stacks or empty slots. */
    public static boolean fits(List<ItemStack> stacks, ItemStack stack) {

        int room = 0;
        for (ItemStack existing : stacks) {
            if (existing.isEmpty()) {
                room += stack.getMaxStackSize();
            } else if (ItemStack.isSameItemSameComponents(existing, stack)) {
                room += existing.getMaxStackSize() - existing.getCount();
            }
            if (room >= stack.getCount()) return true;
        }
        return false;
    }

    /** Puts {@code stack} into {@code stacks}, onto matching stacks first; what does not fit is lost. */
    public static void add(List<ItemStack> stacks, ItemStack stack) {

        int toAdd = stack.getCount();
        for (ItemStack existing : stacks) {
            if (toAdd <= 0) return;
            if (existing.isEmpty() || !ItemStack.isSameItemSameComponents(existing, stack)) continue;
            int added = Math.min(toAdd, existing.getMaxStackSize() - existing.getCount());
            existing.grow(added);
            toAdd -= added;
        }
        for (int slot = 0; slot < stacks.size() && toAdd > 0; slot++) {
            if (!stacks.get(slot).isEmpty()) continue;
            int added = Math.min(toAdd, stack.getMaxStackSize());
            stacks.set(slot, stack.copyWithCount(added));
            toAdd -= added;
        }
    }
}
