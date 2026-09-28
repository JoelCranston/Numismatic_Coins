package com.joelcranston.numismatic_coins.platform;

import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

/**
 * Adds the mod's entries to game registries. Fabric registers on the spot; NeoForge defers to its
 * registry events, so an entry is only readable through the returned supplier once registration
 * has run, and a factory must not read another entry's supplier unless that registry is filled
 * first.
 */
public interface Registration {

    /**
     * Registers the value {@code factory} builds under {@code numismatic_coins:<name>}. The factory
     * receives the entry's id, which items and blocks need for {@code Properties#setId}.
     */
    <R, T extends R> Supplier<T> register(ResourceKey<? extends Registry<R>> registryKey, String name, Function<Identifier, T> factory);
}
