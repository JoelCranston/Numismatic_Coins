package com.joelcranston.numismatic_coins.platform.fabric;

//? fabric {

import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.platform.Platform;
import com.joelcranston.numismatic_coins.platform.Registration;
import com.joelcranston.numismatic_coins.purse.PurseStorage;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

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
    public <T extends BlockEntity> BlockEntityType<T> blockEntityType(BlockEntityFactory<T> factory, Set<Block> blocks) {

        return FabricBlockEntityTypeBuilder.<T>create(factory::create).addBlocks(blocks).build();
    }

    @Override
    public <T extends AbstractContainerMenu> MenuType<T> menuType(MenuFactory<T> factory) {

        return new MenuType<>(factory::create, FeatureFlags.VANILLA_SET);
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
    public <T extends CustomPacketPayload> void registerClientboundPayload(CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {

        PayloadTypeRegistry.clientboundPlay().register(type, codec);
    }

    @Override
    public void sendToPlayer(ServerPlayer player, CustomPacketPayload payload) {

        ServerPlayNetworking.send(player, payload);
    }

    @Override
    public void onPlayerJoin(Consumer<ServerPlayer> listener) {

        ServerPlayConnectionEvents.JOIN.register((packetListener, sender, server) -> listener.accept(packetListener.player));
    }

    @Override
    public void registerFeatureCondition() {

        ResourceConditions.register(FabricFeatureCondition.TYPE);
    }

    @Override
    public Path configDir() {

        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public void registerBuiltinDataPack(String name, Component displayName, boolean isEnabledByDefault) {

        ResourceLoader.registerBuiltinPack(NumismaticCoins.id(name),
                FabricLoader.getInstance().getModContainer(NumismaticCoins.MOD_ID).orElseThrow(), displayName,
                isEnabledByDefault ? PackActivationType.DEFAULT_ENABLED : PackActivationType.NORMAL);
    }

    @Override
    public void addLootPools(Function<ResourceKey<LootTable>, Optional<LootPool.Builder>> poolFor) {

        LootTableEvents.MODIFY.register((table, builder, source, registries) -> poolFor.apply(table).ifPresent(builder::withPool));
    }

    @Override
    public void registerCommands(Consumer<CommandDispatcher<CommandSourceStack>> registrar) {

        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> registrar.accept(dispatcher));
    }
}
//?}
