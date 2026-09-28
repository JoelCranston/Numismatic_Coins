package com.joelcranston.numismatic_coins.platform.neoforge;

//? neoforge {

/*import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.platform.Registration;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

// One DeferredRegister per registry, each subscribed to the mod bus when it is first needed.
public class NeoforgeRegistration implements Registration {

    private final IEventBus modEventBus;
    private final Map<ResourceKey<?>, DeferredRegister<?>> registersByRegistry = new HashMap<>();

    public NeoforgeRegistration(IEventBus modEventBus) {

        this.modEventBus = modEventBus;
    }

    @Override
    @SuppressWarnings ("unchecked")
    public <R, T extends R> Supplier<T> register(ResourceKey<? extends Registry<R>> registryKey, String name, Function<Identifier, T> factory) {

        DeferredRegister<R> register = (DeferredRegister<R>) this.registersByRegistry.computeIfAbsent(registryKey, key -> {
            DeferredRegister<R> created = DeferredRegister.create(registryKey, NumismaticCoins.MOD_ID);
            created.register(this.modEventBus);
            return created;
        });
        return register.register(name, factory);
    }
}
*///?}
