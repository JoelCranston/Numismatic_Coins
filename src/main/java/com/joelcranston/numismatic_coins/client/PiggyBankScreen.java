package com.joelcranston.numismatic_coins.client;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.block.PiggyBankMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

/**
 * The piggy bank's screen: three coin slots, each showing a faint outline of its coin while
 * empty. (NO: PiggyBankScreen, piggy_bank.xml.)
 */
public class PiggyBankScreen extends AbstractContainerScreen<PiggyBankMenu> {

    private static final Identifier TEXTURE = NumismaticCoins.id("textures/gui/piggy_bank.png");
    private static final int TEXTURE_SIZE = 256;
    private static final int IMAGE_WIDTH = 176;
    private static final int IMAGE_HEIGHT = 145;
    // The empty-slot outlines sit under the background, one per coin in slot order.
    private static final int HINT_V = 145;
    private static final int HINT_SIZE = 16;

    public PiggyBankScreen(PiggyBankMenu menu, Inventory inventory, Component title) {

        super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Override
    protected void init() {

        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, TEXTURE_SIZE, TEXTURE_SIZE);
        for (int coin = 0; coin < PiggyBankMenu.COIN_SLOT_COUNT; coin++) {
            Slot slot = this.menu.getSlot(coin);
            if (slot.hasItem()) continue;
            graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos + slot.x, this.topPos + slot.y,
                    coin * HINT_SIZE, HINT_V, HINT_SIZE, HINT_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
        }
    }
}
