package com.joelcranston.numismatic_coins.platform;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.purse.PurseStorage;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;

public interface Platform {

    boolean isModLoaded(String modId);

    ModLoader loader();

    String mcVersion();

    boolean isDevelopmentEnvironment();

    default boolean isDebug() {

        return isDevelopmentEnvironment();
    }

    Registration registration();

    /** A creative tab builder the loader places among its own tab pages. */
    CreativeModeTab.Builder creativeTabBuilder();

    /** The loader's per-player purse attachment, registered the first time this is called. */
    PurseStorage purseStorage();

    /** Registers a client-to-server payload whose handler runs on the server thread. */
    <T extends CustomPacketPayload> void registerServerboundPayload(CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> codec, BiConsumer<T, ServerPlayer> handler);

    /** Adds commands each time the server builds its command tree. */
    void registerCommands(Consumer<CommandDispatcher<CommandSourceStack>> registrar);

    enum ModLoader {
        FABRIC, NEOFORGE, FORGE, QUILT
    }
}
