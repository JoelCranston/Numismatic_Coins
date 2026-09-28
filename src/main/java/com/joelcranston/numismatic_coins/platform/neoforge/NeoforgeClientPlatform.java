package com.joelcranston.numismatic_coins.platform.neoforge;

//? neoforge {

/*import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.platform.ClientPlatform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.jspecify.annotations.Nullable;

public class NeoforgeClientPlatform implements ClientPlatform {

    private final List<Consumer<RegisterClientPayloadHandlersEvent>> payloadHandlers = new ArrayList<>();

    public NeoforgeClientPlatform() {

        NeoforgePlatform.modEventBus.addListener((RegisterClientPayloadHandlersEvent event) -> this.payloadHandlers.forEach(handler -> handler.accept(event)));
    }

    @Override
    public void setScreen(@Nullable Screen screen) {

        Minecraft.getInstance().setScreen(screen);
    }

    @Override
    public void sendToServer(CustomPacketPayload payload) {

        ClientPacketDistributor.sendToServer(payload);
    }

    @Override
    public <T extends CustomPacketPayload> void registerClientboundHandler(CustomPacketPayload.Type<T> type, Consumer<T> handler) {

        this.payloadHandlers.add(event -> event.register(type, (payload, context) -> handler.accept(payload)));
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

    @Override
    public <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void registerMenuScreen(
            Supplier<? extends MenuType<? extends M>> menuType, MenuScreenFactory<M, U> factory) {

        NeoforgePlatform.modEventBus.addListener((RegisterMenuScreensEvent event) -> event.register(menuType.get(), factory::create));
    }
}
*///?}
