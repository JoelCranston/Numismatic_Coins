package com.joelcranston.numismatic_coins.trade;

import java.util.List;

import com.joelcranston.numismatic_coins.registry.ModDataComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

/**
 * {@code numismatic_coins:enchantment_price}: prices a trade's result by the enchantments on it,
 * for villager trades that enchant what they sell. Runs after the enchanting function; the price
 * goes on the result as {@link ModDataComponents#TRADE_PRICE}, which {@link CoinTradePrices} turns
 * into the offer's coin cost.
 */
public class EnchantmentPriceFunction extends LootItemConditionalFunction {

    /** The price an enchanted item starts from when the trade gives none. (NO: EnchantItemAdapter.) */
    public static final long DEFAULT_BASE_PRICE = 200;

    public static final MapCodec<EnchantmentPriceFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance)
                    .and(instance.group(
                            Formula.CODEC.fieldOf("formula").forGetter(function -> function.formula),
                            Codec.LONG.optionalFieldOf("base_price", DEFAULT_BASE_PRICE).forGetter(function -> function.basePrice)))
                    .apply(instance, EnchantmentPriceFunction::new));

    // Each enchantment on an item adds a tenth of the running price. (NO: EnchantItemAdapter.)
    private static final float RUNNING_PRICE_SHARE = 0.10f;
    private static final float TREASURE_FACTOR = 2f;
    private static final float MIN_PRICE_SPREAD = 0.8f, MAX_PRICE_SPREAD = 1.2f;
    // An enchantment of weight 5 costs the base price per level; rarer ones cost more.
    private static final float ITEM_REFERENCE_WEIGHT = 5f;
    // A book's price per enchantment. (NO: SellSingleEnchantmentAdapter.)
    private static final int BOOK_BASE_PRICE = 100;
    private static final int BOOK_REFERENCE_WEIGHT = 10;
    private static final int BOOK_RANDOM_LEVEL_BONUS = 50;

    private final Formula formula;
    private final long basePrice;

    private EnchantmentPriceFunction(List<LootItemCondition> predicates, Formula formula, long basePrice) {

        super(predicates);
        this.formula = formula;
        this.basePrice = basePrice;
    }

    @Override
    public MapCodec<EnchantmentPriceFunction> codec() {

        return MAP_CODEC;
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {

        ItemEnchantments enchantments = stack.has(DataComponents.STORED_ENCHANTMENTS)
                ? stack.getOrDefault(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY)
                : stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        long price = switch (this.formula) {
            case ENCHANTED_ITEM -> enchantedItemPrice(enchantments, context.getRandom());
            case ENCHANTED_BOOK -> enchantedBookPrice(enchantments, context.getRandom());
        };
        stack.set(ModDataComponents.TRADE_PRICE.get(), Math.max(1, price));
        return stack;
    }

    // (NO: EnchantItemAdapter.)
    private long enchantedItemPrice(ItemEnchantments enchantments, RandomSource random) {

        long price = this.basePrice;
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            Holder<Enchantment> enchantment = entry.getKey();
            if (enchantment.is(EnchantmentTags.DOUBLE_TRADE_PRICE)) price *= 2;
            float treasureFactor = enchantment.is(EnchantmentTags.TREASURE) ? TREASURE_FACTOR : 1f;
            float spread = MIN_PRICE_SPREAD + random.nextFloat() * (MAX_PRICE_SPREAD - MIN_PRICE_SPREAD);
            price += (long) (price * RUNNING_PRICE_SHARE + this.basePrice * treasureFactor * entry.getIntValue() * spread
                    * (ITEM_REFERENCE_WEIGHT / enchantment.value().getWeight()));
        }
        return price;
    }

    // (NO: SellSingleEnchantmentAdapter.)
    private static long enchantedBookPrice(ItemEnchantments enchantments, RandomSource random) {

        long price = 0;
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            Holder<Enchantment> enchantment = entry.getKey();
            int level = entry.getIntValue();
            // Whole-number division, so every weight above 5 counts as 1.
            int rarity = BOOK_REFERENCE_WEIGHT / enchantment.value().getWeight();
            long enchantmentPrice = (long) BOOK_BASE_PRICE * rarity
                    + (long) (random.nextInt(BOOK_RANDOM_LEVEL_BONUS) + level) * level * level * rarity;
            if (enchantment.is(EnchantmentTags.DOUBLE_TRADE_PRICE)) enchantmentPrice *= 2;
            price += enchantmentPrice;
        }
        return price;
    }

    /** Which of NO's two enchantment pricings a trade uses. */
    public enum Formula implements StringRepresentable {

        /** An item enchanted at a fixed level, starting from the trade's base price. */
        ENCHANTED_ITEM("enchanted_item"),
        /** A book with one enchantment at a random level; the base price is not used. */
        ENCHANTED_BOOK("enchanted_book");

        public static final Codec<Formula> CODEC = StringRepresentable.fromEnum(Formula::values);

        private final String serializedName;

        Formula(String serializedName) {

            this.serializedName = serializedName;
        }

        @Override
        public String getSerializedName() {

            return this.serializedName;
        }
    }
}
