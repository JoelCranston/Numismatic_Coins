package com.joelcranston.numismatic_coins.platform.fabric;

//? fabric {

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.platform.ClientPlatform;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jspecify.annotations.Nullable;

public class FabricClientPlatform implements ClientPlatform {

    @Override
    public void setScreen(@Nullable Screen screen) {

        //? if >=26.2 {
        /*Minecraft.getInstance().gui.setScreen(screen);
        *///?} else {
        Minecraft.getInstance().setScreen(screen);
        //?}
    }

    @Override
    public void sendToServer(CustomPacketPayload payload) {

        ClientPlayNetworking.send(payload);
    }

    @Override
    public <T extends CustomPacketPayload> void registerClientboundHandler(CustomPacketPayload.Type<T> type, Consumer<T> handler) {

        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) -> handler.accept(payload));
    }

    @Override
    public void onScreenInit(BiConsumer<Screen, Consumer<AbstractWidget>> listener) {

        ScreenEvents.AFTER_INIT.register((minecraft, screen, width, height) -> listener.accept(screen, Screens.getWidgets(screen)::add));
    }

    @Override
    public void onScreenExtracted(ScreenExtractListener listener) {

        // Fabric's per-screen events are cleared when a screen initialises, so subscribe on each init.
        ScreenEvents.AFTER_INIT.register((minecraft, screen, width, height) -> ScreenEvents.afterExtract(screen).register(
                (extractedScreen, graphics, mouseX, mouseY, partialTick) -> listener.afterExtract(extractedScreen, graphics, mouseX, mouseY)));
    }

    @Override
    public <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void registerMenuScreen(
            Supplier<? extends MenuType<? extends M>> menuType, MenuScreenFactory<M, U> factory) {

        MenuScreens.register(menuType.get(), factory::create);
    }

    @Override
    public <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRenderer(
            Supplier<? extends BlockEntityType<T>> type, BlockEntityRendererProvider<T, S> provider) {

        BlockEntityRendererRegistry.register(type.get(), provider);
    }
}
//?}
