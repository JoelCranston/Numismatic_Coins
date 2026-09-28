package com.joelcranston.numismatic_coins.registry;

import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.block.PiggyBankMenu;
import com.joelcranston.numismatic_coins.shop.ShopMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;

public final class ModMenus {

    public static final Supplier<MenuType<PiggyBankMenu>> PIGGY_BANK = NumismaticCoins.xplat().registration()
            .<MenuType<?>, MenuType<PiggyBankMenu>>register(Registries.MENU, "piggy_bank", id -> NumismaticCoins.xplat().menuType(PiggyBankMenu::new));

    public static final Supplier<MenuType<ShopMenu>> SHOP = NumismaticCoins.xplat().registration()
            .<MenuType<?>, MenuType<ShopMenu>>register(Registries.MENU, "shop", id -> NumismaticCoins.xplat().menuType(ShopMenu::new));

    private ModMenus() {}

    /** Loads the class, which registers its entries. */
    public static void register() {

    }
}
