package com.joelcranston.numismatic_coins.platform;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jspecify.annotations.Nullable;

/** The client-only side of {@link Platform}. Only touched from client entry points. */
public interface ClientPlatform {

    static ClientPlatform create() {

        //? fabric {
        return new com.joelcranston.numismatic_coins.platform.fabric.FabricClientPlatform();
        //?} neoforge {
        /*return new com.joelcranston.numismatic_coins.platform.neoforge.NeoforgeClientPlatform();
         *///?}
    }

    /** Opens a screen, or closes the open one when given null. 26.2 moved this from Minecraft to Gui. */
    void setScreen(@Nullable Screen screen);

    void sendToServer(CustomPacketPayload payload);

    /** Handles a payload registered with {@link Platform#registerClientboundPayload}, on the client thread. */
    <T extends CustomPacketPayload> void registerClientboundHandler(CustomPacketPayload.Type<T> type, Consumer<T> handler);

    /**
     * Calls {@code listener} after every screen initialises, with a way to add widgets to it.
     * Widgets added this way are drawn, clicked and removed with the screen.
     */
    void onScreenInit(BiConsumer<Screen, Consumer<AbstractWidget>> listener);

    /** Calls {@code listener} after every screen has drawn everything, slots and items included. */
    void onScreenExtracted(ScreenExtractListener listener);

    @FunctionalInterface
    interface ScreenExtractListener {

        void afterExtract(Screen screen, GuiGraphicsExtractor graphics, int mouseX, int mouseY);
    }
}
