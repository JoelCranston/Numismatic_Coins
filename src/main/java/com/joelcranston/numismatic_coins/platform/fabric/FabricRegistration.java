package com.joelcranston.numismatic_coins.platform.fabric;

//? fabric {

import java.util.function.Function;
import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.platform.Registration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

/** Registers each entry immediately, so the returned supplier is readable at once. */
public class FabricRegistration implements Registration {

    @Override
    @SuppressWarnings ("unchecked")
    public <R, T extends R> Supplier<T> register(ResourceKey<? extends Registry<R>> registryKey, String name, Function<Identifier, T> factory) {

        Registry<R> registry = (Registry<R>) BuiltInRegistries.REGISTRY.getValue(registryKey.identifier());
        if (registry == null) {
            throw new IllegalArgumentException("No built-in registry " + registryKey.identifier());
        }
        Identifier id = NumismaticCoins.id(name);
        T value = Registry.register(registry, id, factory.apply(id));
        return () -> value;
    }
}
//?}
