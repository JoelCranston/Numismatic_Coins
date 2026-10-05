package com.joelcranston.numismatic_coins.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/**
 * A button drawn from a GUI texture, whose hovered and inactive looks sit one and two button
 * heights below its normal one, as owo-ui's texture buttons lay them out.
 */
public class TextureButton extends AbstractButton {

    private static final int TEXTURE_SIZE = 256;

    private final Identifier texture;
    private final int u;
    private final int v;
    private final Runnable onPress;

    public TextureButton(int x, int y, int width, int height, Identifier texture, int u, int v, Component message, Runnable onPress) {

        super(x, y, width, height, message);
        this.texture = texture;
        this.u = u;
        this.v = v;
        this.onPress = onPress;
    }

    @Override
    public void onPress(InputWithModifiers input) {

        this.onPress.run();
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        int stateV = !this.active ? this.v + 2 * this.height : this.isHoveredOrFocused() ? this.v + this.height : this.v;
        graphics.blit(RenderPipelines.GUI_TEXTURED, this.texture, this.getX(), this.getY(), this.u, stateV,
                this.width, this.height, TEXTURE_SIZE, TEXTURE_SIZE);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {

        this.defaultButtonNarrationText(output);
    }
}
