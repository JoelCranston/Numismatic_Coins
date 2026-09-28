package com.joelcranston.numismatic_coins.platform.fabric;

//? fabric {

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.platform.Platform;
import com.joelcranston.numismatic_coins.platform.Registration;
import com.joelcranston.numismatic_coins.purse.PurseStorage;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;

public class FabricPlatform implements Platform {

    private final Registration registration = new FabricRegistration();
    private PurseStorage purseStorage;

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public ModLoader loader() {

        return ModLoader.FABRIC;
    }

    @Override
    public String mcVersion() {

        return FabricLoader.getInstance().getRawGameVersion();
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Registration registration() {

        return this.registration;
    }

    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {

        return FabricCreativeModeTab.builder();
    }

    @Override
    public PurseStorage purseStorage() {

        if (this.purseStorage == null) this.purseStorage = new FabricPurseStorage();
        return this.purseStorage;
    }

    @Override
    public <T extends CustomPacketPayload> void registerServerboundPayload(CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> codec, BiConsumer<T, ServerPlayer> handler) {

        PayloadTypeRegistry.serverboundPlay().register(type, codec);
        ServerPlayNetworking.registerGlobalReceiver(type, (payload, context) -> handler.accept(payload, context.player()));
    }

    @Override
    public void registerCommands(Consumer<CommandDispatcher<CommandSourceStack>> registrar) {

        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> registrar.accept(dispatcher));
    }
}
//?}
