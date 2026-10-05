package com.joelcranston.numismatic_coins.platform.neoforge;

//? neoforge {

/*import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.platform.Platform;
import com.joelcranston.numismatic_coins.platform.Registration;
import com.joelcranston.numismatic_coins.purse.PurseStorage;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.serialization.MapCodec;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class NeoforgePlatform implements Platform {

    // The mod bus the entry point was given; set before this class is constructed.
    static IEventBus modEventBus;

    // Bumped when a payload's wire format changes, so mismatched clients are refused.
    private static final String NETWORK_VERSION = "1";

    private final Registration registration = new NeoforgeRegistration(modEventBus);
    private final List<Consumer<PayloadRegistrar>> payloadRegistrations = new ArrayList<>();
    private PurseStorage purseStorage;

    public NeoforgePlatform() {

        modEventBus.addListener(this::onRegisterPayloadHandlers);
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public ModLoader loader() {

        return ModLoader.NEOFORGE;
    }

    @Override
    public String mcVersion() {

        return "";
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.getCurrent().isProduction();
    }

    @Override
    public Registration registration() {

        return this.registration;
    }

    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {

        return CreativeModeTab.builder();
    }

    @Override
    public PurseStorage purseStorage() {

        if (this.purseStorage == null) this.purseStorage = new NeoforgePurseStorage(this.registration);
        return this.purseStorage;
    }

    @Override
    public <T extends CustomPacketPayload> void registerServerboundPayload(CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> codec, BiConsumer<T, ServerPlayer> handler) {

        // Handlers run on the server thread unless registered with executesOn(NETWORK).
        this.payloadRegistrations.add(registrar -> registrar.playToServer(type, codec,
                (payload, context) -> handler.accept(payload, (ServerPlayer) context.player())));
    }

    @Override
    public <T extends CustomPacketPayload> void registerClientboundPayload(CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {

        // The client's handler is added by RegisterClientPayloadHandlersEvent.
        this.payloadRegistrations.add(registrar -> registrar.playToClient(type, codec));
    }

    @Override
    public void sendToPlayer(ServerPlayer player, CustomPacketPayload payload) {

        PacketDistributor.sendToPlayer(player, payload);
    }

    @Override
    public void onPlayerJoin(Consumer<ServerPlayer> listener) {

        NeoForge.EVENT_BUS.addListener((PlayerEvent.PlayerLoggedInEvent event) -> {
            if (event.getEntity() instanceof ServerPlayer player) listener.accept(player);
        });
    }

    @Override
    public void registerFeatureCondition() {

        this.registration.<MapCodec<? extends ICondition>, MapCodec<NeoforgeFeatureCondition>>register(
                NeoForgeRegistries.Keys.CONDITION_CODECS, "feature_enabled", id -> NeoforgeFeatureCondition.CODEC);
    }

    @Override
    public Path configDir() {

        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public void registerBuiltinDataPack(String name, Component displayName, boolean isEnabledByDefault) {

        // The source decides whether a new world adds the pack on its own.
        PackSource source = PackSource.create(PackSource.BUILT_IN::decorate, isEnabledByDefault);
        modEventBus.addListener((AddPackFindersEvent event) -> event.addPackFinders(
                NumismaticCoins.id("resourcepacks/" + name), PackType.SERVER_DATA, displayName, source, false, Pack.Position.TOP));
    }

    @Override
    public void registerCommands(Consumer<CommandDispatcher<CommandSourceStack>> registrar) {

        NeoForge.EVENT_BUS.addListener((RegisterCommandsEvent event) -> registrar.accept(event.getDispatcher()));
    }

    private void onRegisterPayloadHandlers(RegisterPayloadHandlersEvent event) {

        PayloadRegistrar registrar = event.registrar(NETWORK_VERSION);
        this.payloadRegistrations.forEach(registration -> registration.accept(registrar));
    }
}
*///?}
