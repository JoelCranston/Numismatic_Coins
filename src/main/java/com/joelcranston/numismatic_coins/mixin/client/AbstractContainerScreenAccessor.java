package com.joelcranston.numismatic_coins.mixin.client;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/** Where a container screen's background starts, which widgets placed on it are relative to. */
@Mixin (AbstractContainerScreen.class)
public interface AbstractContainerScreenAccessor {

    @Accessor ("leftPos")
    int numismatic_coins$leftPos();

    @Accessor ("topPos")
    int numismatic_coins$topPos();
}
