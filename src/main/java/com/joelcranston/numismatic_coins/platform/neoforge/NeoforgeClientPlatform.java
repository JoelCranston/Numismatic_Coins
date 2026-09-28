package com.joelcranston.numismatic_coins.platform.neoforge;

//? neoforge {

/*import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.platform.ClientPlatform;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.common.NeoForge;

public class NeoforgeClientPlatform implements ClientPlatform {

    @Override
    public void sendToServer(CustomPacketPayload payload) {

        ClientPacketDistributor.sendToServer(payload);
    }

    @Override
    public void onScreenInit(BiConsumer<Screen, Consumer<AbstractWidget>> listener) {

        NeoForge.EVENT_BUS.addListener((ScreenEvent.Init.Post event) -> listener.accept(event.getScreen(), event::addListener));
    }

    @Override
    public void onScreenExtracted(ScreenExtractListener listener) {

        NeoForge.EVENT_BUS.addListener((ScreenEvent.Render.Post event) ->
                listener.afterExtract(event.getScreen(), event.getGuiGraphics(), event.getMouseX(), event.getMouseY()));
    }
}
*///?}
