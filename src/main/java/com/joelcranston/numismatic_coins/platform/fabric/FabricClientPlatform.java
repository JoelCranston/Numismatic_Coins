package com.joelcranston.numismatic_coins.platform.fabric;

//? fabric {

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.platform.ClientPlatform;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class FabricClientPlatform implements ClientPlatform {

    @Override
    public void sendToServer(CustomPacketPayload payload) {

        ClientPlayNetworking.send(payload);
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
}
//?}
