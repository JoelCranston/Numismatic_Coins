package com.joelcranston.numismatic_coins.registry;

import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public final class ModSounds {

    public static final Supplier<SoundEvent> PIGGY_BANK_BREAK = NumismaticCoins.xplat().registration().register(
            Registries.SOUND_EVENT, "piggy_bank_break", SoundEvent::createVariableRangeEvent);

    private ModSounds() {}

    /** Loads the class, which registers its entries. */
    public static void register() {

    }
}
