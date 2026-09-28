package com.joelcranston.numismatic_coins.platform;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import com.joelcranston.numismatic_coins.purse.PurseStorage;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

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

    /** Registers a server-to-client payload; the client side gives its handler through {@link ClientPlatform}. */
    <T extends CustomPacketPayload> void registerClientboundPayload(CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> codec);

    void sendToPlayer(ServerPlayer player, CustomPacketPayload payload);

    /** Calls {@code listener} on the server thread when a player has joined and can be sent payloads. */
    void onPlayerJoin(Consumer<ServerPlayer> listener);

    /**
     * Registers the {@code numismatic_coins:feature_enabled} load condition, which keeps a recipe
     * or other data file only while the named feature is on.
     */
    void registerFeatureCondition();

    Path configDir();

    /**
     * Offers the data pack at {@code resourcepacks/<name>} in the mod jar in the world's data pack
     * list. {@code isEnabledByDefault} decides whether a new world starts with it turned on; an
     * existing world keeps whatever its data pack list says. Called once, at start-up.
     */
    void registerBuiltinDataPack(String name, Component displayName, boolean isEnabledByDefault);

    /**
     * Adds a pool to loot tables as they load, on every data pack reload. {@code poolFor} is asked
     * once per table and returns the pool to add, or empty to leave the table as it is.
     */
    void addLootPools(Function<ResourceKey<LootTable>, Optional<LootPool.Builder>> poolFor);

    /** Adds commands each time the server builds its command tree. */
    void registerCommands(Consumer<CommandDispatcher<CommandSourceStack>> registrar);

    enum ModLoader {
        FABRIC, NEOFORGE, FORGE, QUILT
    }
}
