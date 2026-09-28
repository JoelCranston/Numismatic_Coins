package com.joelcranston.numismatic_coins.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.block.PiggyBankBlock;
import com.joelcranston.numismatic_coins.item.DescribedBlockItem;
import com.joelcranston.numismatic_coins.item.PiggyBankItem;
import com.joelcranston.numismatic_coins.shop.PawnShopBlock;
import com.joelcranston.numismatic_coins.shop.ShopBlock;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jspecify.annotations.Nullable;

public final class ModBlocks {

    // Hardness and blast resistance. (NO: PiggyBankBlock.)
    private static final float PIGGY_BANK_HARDNESS = 1.25f;
    private static final float PIGGY_BANK_RESISTANCE = 4.2f;
    // (NO: ShopBlock.)
    private static final float SHOP_HARDNESS = 5.0f;

    public static final Supplier<ShopBlock> SHOP = registerShop("shop", false);
    public static final Supplier<ShopBlock> INEXHAUSTIBLE_SHOP = registerShop("inexhaustible_shop", true);
    public static final Supplier<PawnShopBlock> PAWN_SHOP = registerShop("pawn_shop", false, PawnShopBlock::new);
    public static final Supplier<PawnShopBlock> INEXHAUSTIBLE_PAWN_SHOP = registerShop("inexhaustible_pawn_shop", true, PawnShopBlock::new);
    public static final Supplier<PiggyBankBlock> PIGGY_BANK = registerPiggyBank(null);
    private static final Map<DyeColor, Supplier<PiggyBankBlock>> DYED_PIGGY_BANKS = registerDyedPiggyBanks();

    private ModBlocks() {}

    /** Loads the class, which registers its entries. */
    public static void register() {

    }

    /** The piggy bank of {@code color}, or the plain one for null. */
    public static PiggyBankBlock piggyBank(@Nullable DyeColor color) {

        return (color == null ? PIGGY_BANK : DYED_PIGGY_BANKS.get(color)).get();
    }

    /** The plain piggy bank, then each dyed one in dye order. */
    public static List<PiggyBankBlock> piggyBanks() {

        List<PiggyBankBlock> piggyBanks = new ArrayList<>();
        piggyBanks.add(PIGGY_BANK.get());
        DYED_PIGGY_BANKS.values().forEach(piggyBank -> piggyBanks.add(piggyBank.get()));
        return Collections.unmodifiableList(piggyBanks);
    }

    private static Supplier<ShopBlock> registerShop(String name, boolean isInexhaustible) {

        return registerShop(name, isInexhaustible, ShopBlock::new);
    }

    private static <B extends ShopBlock> Supplier<B> registerShop(String name, boolean isInexhaustible, BiFunction<Boolean, BlockBehaviour.Properties, B> factory) {

        Supplier<B> block = NumismaticCoins.xplat().registration().register(Registries.BLOCK, name,
                id -> factory.apply(isInexhaustible, BlockBehaviour.Properties.of()
                        .setId(ResourceKey.create(Registries.BLOCK, id))
                        .destroyTime(SHOP_HARDNESS)
                        .noOcclusion()));
        NumismaticCoins.xplat().registration().register(Registries.ITEM, name,
                id -> new DescribedBlockItem(block.get(), new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, id))
                        .useBlockDescriptionPrefix()
                        .rarity(isInexhaustible ? Rarity.EPIC : Rarity.COMMON)));
        return block;
    }

    private static Map<DyeColor, Supplier<PiggyBankBlock>> registerDyedPiggyBanks() {

        Map<DyeColor, Supplier<PiggyBankBlock>> piggyBanks = new EnumMap<>(DyeColor.class);
        for (DyeColor color : DyeColor.values()) piggyBanks.put(color, registerPiggyBank(color));
        return piggyBanks;
    }

    private static Supplier<PiggyBankBlock> registerPiggyBank(@Nullable DyeColor color) {

        String name = color == null ? "piggy_bank" : color.getSerializedName() + "_piggy_bank";
        Supplier<PiggyBankBlock> block = NumismaticCoins.xplat().registration().register(Registries.BLOCK, name,
                id -> new PiggyBankBlock(color, BlockBehaviour.Properties.of()
                        .setId(ResourceKey.create(Registries.BLOCK, id))
                        .strength(PIGGY_BANK_HARDNESS, PIGGY_BANK_RESISTANCE)));
        // The coin counts replace the container's own list of items in the tooltip.
        NumismaticCoins.xplat().registration().register(Registries.ITEM, name,
                id -> new PiggyBankItem(block.get(), new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, id))
                        .useBlockDescriptionPrefix()
                        .stacksTo(1)
                        .equippable(EquipmentSlot.HEAD)
                        .component(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT.withHidden(DataComponents.CONTAINER, true))));
        return block;
    }
}
