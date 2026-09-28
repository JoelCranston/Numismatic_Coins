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
import net.minecraft.world.item.trading.MerchantOffer;

/**
 * One thing a shop sells: a stack and its price in bronze. The price is asked as coins when one
 * kind of coin pays it, and as a money bag holding exactly that value otherwise. (NO: ShopOffer.)
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

    /** The trade a customer sees, with as many uses as the stock covers. */
    public MerchantOffer toMerchantOffer(List<ItemStack> stock, boolean isInexhaustible) {

        int maxUses = isInexhaustible ? Integer.MAX_VALUE : count(stock, this.stack) / this.stack.getCount();
        return new MerchantOffer(cost(this.price), this.stack.copy(), maxUses, 0, 0);
    }

    /** What a customer hands over for {@code price}: coins of one kind, or an exact money bag. */
    public static ItemCost cost(long price) {

        CoinMath.CoinStack coins = CoinMath.closestCoin(price);
        if (coins.currency().rawValue(coins.count()) == price && coins.count() <= ModItems.COIN_STACK_SIZE) {
            return new ItemCost(ModItems.coin(coins.currency()), (int) coins.count());
        }
        ItemStack bag = MoneyBagItem.withValue(price);
        return new ItemCost(ModItems.MONEY_BAG.get()).withComponents(components -> components
                .expect(ModDataComponents.MONEY_BAG.get(), bag.get(ModDataComponents.MONEY_BAG.get()))
                .expect(DataComponents.CUSTOM_MODEL_DATA, bag.get(DataComponents.CUSTOM_MODEL_DATA)));
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
}
