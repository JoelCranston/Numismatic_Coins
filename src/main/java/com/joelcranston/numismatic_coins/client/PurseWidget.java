package com.joelcranston.numismatic_coins.client;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.currency.Currency;
import com.joelcranston.numismatic_coins.item.CoinText;
import com.joelcranston.numismatic_coins.mixin.client.AbstractContainerScreenAccessor;
import com.joelcranston.numismatic_coins.network.RequestPurseAction;
import com.joelcranston.numismatic_coins.purse.Purses;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;

/**
 * The purse button on the inventory, creative inventory and villager screens. Clicking it opens a
 * panel for choosing coins to take out; shift-clicking it puts every coin in the inventory into the
 * purse. (NO: PurseLayerElement, purse.xml.)
 *
 * <p>One widget draws both the button and the panel, and follows the screen's background, which
 * moves when the recipe book opens.
 */
public class PurseWidget extends AbstractWidget {

    private static final Identifier TEXTURE = NumismaticCoins.id("textures/gui/purse_widget.png");
    private static final int TEXTURE_WIDTH = 128, TEXTURE_HEIGHT = 64;

    // Button: the purse icon, with its hovered version below it.
    private static final int BUTTON_WIDTH = 11, BUTTON_HEIGHT = 13, BUTTON_U = 62, BUTTON_V = 0;

    // Panel: below and left of the button, its tail pointing up at it.
    private static final int PANEL_OFFSET_X = -30, PANEL_OFFSET_Y = 15;
    private static final int PANEL_WIDTH = 37, PANEL_HEIGHT = 59;

    // One row per coin, gold first: a count, and up and down arrows beside it.
    private static final int ROW_HEIGHT = 12;
    private static final int COUNT_X = 5, COUNT_Y = 12;
    private static final int ARROW_X = 18, ARROW_Y = 10, ARROW_WIDTH = 9, ARROW_HEIGHT = 5, ARROW_GAP = 1;
    private static final int INCREASE_U = 37, DECREASE_U = 46, ARROW_V = 24;
    private static final int[] ROW_DENOMINATIONS = {Currency.GOLD.ordinal(), Currency.SILVER.ordinal(), Currency.BRONZE.ordinal()};

    // Take-out button along the bottom of the panel.
    private static final int WITHDRAW_X = 3, WITHDRAW_Y = 46, WITHDRAW_WIDTH = 24, WITHDRAW_HEIGHT = 8, WITHDRAW_U = 37, WITHDRAW_V = 0;

    private static final int TEXT_COLOR = 0xFFFFFFFF;
    private static final int MAX_SELECTED_COINS = 99;
    private static final int SHIFT_STEP = 10;

    // Where the button sits, relative to the screen's background. (NO: NumismaticOverhaulClient.)
    private static final int INVENTORY_X = 160, INVENTORY_Y = 5;
    private static final int CREATIVE_X = 38, CREATIVE_Y = 4;
    private static final int MERCHANT_X = 260, MERCHANT_Y = 5;

    // The purse widget of the open screen, if it shows one.
    private static @Nullable PurseWidget shownWidget;

    private final AbstractContainerScreenAccessor screen;
    private final int offsetX, offsetY;
    private final BooleanSupplier isShown;

    private boolean isOpen;
    // Coins chosen to take out, in Currency order.
    private final long[] selected = new long[Currency.values().length];

    public PurseWidget(AbstractContainerScreen<?> screen, int offsetX, int offsetY, BooleanSupplier isShown) {

        super(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT, Component.translatable("gui.numismatic_coins.purse_title"));
        this.screen = (AbstractContainerScreenAccessor) screen;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.isShown = isShown;
        followScreen();
    }

    /** Adds the widget to the screens that show a purse. */
    public static void register() {

        NumismaticCoinsClient.clientXplat().onScreenInit((screen, addWidget) -> {
            shownWidget = switch (screen) {
                case CreativeModeInventoryScreen creative -> new PurseWidget(creative, CREATIVE_X, CREATIVE_Y, creative::isInventoryOpen);
                case InventoryScreen inventory -> new PurseWidget(inventory, INVENTORY_X, INVENTORY_Y, () -> true);
                case MerchantScreen merchant -> new PurseWidget(merchant, MERCHANT_X, MERCHANT_Y, () -> true);
                default -> null;
            };
            if (shownWidget != null) addWidget.accept(shownWidget);
        });
        // The panel is drawn after the screen, as a widget would be drawn beneath the slots' items.
        NumismaticCoinsClient.clientXplat().onScreenExtracted((screen, graphics, mouseX, mouseY) -> {
            if (shownWidget != null && shownWidget.screen == screen && shownWidget.visible && shownWidget.isOpen) {
                shownWidget.extractPanel(graphics, mouseX, mouseY);
            }
        });
    }

    @Override
    protected void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        followScreen();
        if (!this.visible) return;
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        long balance = Purses.balance(player);
        clampSelection(balance);

        boolean isButtonHovered = isOverButton(mouseX, mouseY);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, getX(), getY(), BUTTON_U, BUTTON_V + (isButtonHovered ? BUTTON_HEIGHT : 0),
                BUTTON_WIDTH, BUTTON_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        if (isButtonHovered) {
            List<Component> tooltip = List.of(
                    getMessage().copy().withStyle(CoinText.style(Currency.GOLD)),
                    CoinText.coinCounts(CoinMath.split(balance)));
            graphics.setTooltipForNextFrame(Minecraft.getInstance().font, tooltip, Optional.empty(), mouseX, mouseY);
        }
    }

    private void extractPanel(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {

        graphics.nextStratum();
        int panelX = panelX(), panelY = panelY();
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, panelX, panelY, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        for (int row = 0; row < ROW_DENOMINATIONS.length; row++) {
            String count = Long.toString(this.selected[ROW_DENOMINATIONS[row]]);
            graphics.text(Minecraft.getInstance().font, count, panelX + COUNT_X, panelY + COUNT_Y + row * ROW_HEIGHT, TEXT_COLOR, false);
            extractPanelButton(graphics, mouseX, mouseY, ARROW_X, increaseArrowY(row), ARROW_WIDTH, ARROW_HEIGHT, INCREASE_U, ARROW_V);
            extractPanelButton(graphics, mouseX, mouseY, ARROW_X, decreaseArrowY(row), ARROW_WIDTH, ARROW_HEIGHT, DECREASE_U, ARROW_V);
        }
        extractPanelButton(graphics, mouseX, mouseY, WITHDRAW_X, WITHDRAW_Y, WITHDRAW_WIDTH, WITHDRAW_HEIGHT, WITHDRAW_U, WITHDRAW_V);
    }

    // Each panel button's hovered look sits directly below its normal one in the texture.
    private void extractPanelButton(GuiGraphicsExtractor graphics, int mouseX, int mouseY, int x, int y, int width, int height, int u, int v) {

        boolean isHovered = isOverPanelPart(mouseX, mouseY, x, y, width, height);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, panelX() + x, panelY() + y, u, v + (isHovered ? height : 0),
                width, height, TEXTURE_WIDTH, TEXTURE_HEIGHT);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {

        if (!this.visible) return false;
        return isOverButton(mouseX, mouseY) || (this.isOpen && isOverPanelPart(mouseX, mouseY, 0, 0, PANEL_WIDTH, PANEL_HEIGHT));
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean isDoubleClick) {

        if (!this.visible || !this.active || !isValidClickButton(event.buttonInfo())) return false;
        double mouseX = event.x(), mouseY = event.y();
        if (isOverButton(mouseX, mouseY)) {
            if (event.hasShiftDown()) {
                NumismaticCoinsClient.clientXplat().sendToServer(RequestPurseAction.storeAll());
            } else {
                this.isOpen = !this.isOpen;
            }
            playDownSound(Minecraft.getInstance().getSoundManager());
            return true;
        }
        if (!this.isOpen || !isOverPanelPart(mouseX, mouseY, 0, 0, PANEL_WIDTH, PANEL_HEIGHT)) return false;

        int step = event.hasShiftDown() ? SHIFT_STEP : 1;
        for (int row = 0; row < ROW_DENOMINATIONS.length; row++) {
            if (isOverPanelPart(mouseX, mouseY, ARROW_X, increaseArrowY(row), ARROW_WIDTH, ARROW_HEIGHT)) {
                adjust(ROW_DENOMINATIONS[row], step);
            } else if (isOverPanelPart(mouseX, mouseY, ARROW_X, decreaseArrowY(row), ARROW_WIDTH, ARROW_HEIGHT)) {
                adjust(ROW_DENOMINATIONS[row], -step);
            }
        }
        if (isOverPanelPart(mouseX, mouseY, WITHDRAW_X, WITHDRAW_Y, WITHDRAW_WIDTH, WITHDRAW_HEIGHT)) withdraw(event.hasShiftDown() && event.hasControlDown());
        // The panel swallows every click on it, so none reaches the slot underneath.
        return true;
    }

    private void withdraw(boolean isEverything) {

        if (isEverything) {
            NumismaticCoinsClient.clientXplat().sendToServer(RequestPurseAction.withdrawAll());
        } else {
            long rawValue = CoinMath.combine(this.selected);
            if (rawValue <= 0) return;
            NumismaticCoinsClient.clientXplat().sendToServer(RequestPurseAction.withdraw(rawValue));
        }
        Arrays.fill(this.selected, 0);
        playDownSound(Minecraft.getInstance().getSoundManager());
    }

    /**
     * Changes one coin's count by {@code step}, keeping the total chosen within the balance and each
     * count within 0 to 99. (NO: PurseLayerElement#adjust.)
     */
    private void adjust(int denomination, int step) {

        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        long balance = Purses.balance(player);
        long unitValue = Currency.values()[denomination].unitValue();
        long unchosen = balance - CoinMath.combine(this.selected);
        long change = Math.min(step, unchosen / unitValue);
        long maximum = Math.min(balance / unitValue, MAX_SELECTED_COINS);
        this.selected[denomination] = Math.clamp(this.selected[denomination] + change, 0, maximum);
        playDownSound(Minecraft.getInstance().getSoundManager());
    }

    // After a withdrawal or a command, the balance may no longer cover what was chosen.
    private void clampSelection(long balance) {

        if (CoinMath.combine(this.selected) <= balance) return;
        Arrays.fill(this.selected, 0);
    }

    private void followScreen() {

        setX(this.screen.numismatic_coins$leftPos() + this.offsetX);
        setY(this.screen.numismatic_coins$topPos() + this.offsetY);
        this.visible = this.isShown.getAsBoolean();
        if (!this.visible) this.isOpen = false;
    }

    private boolean isOverButton(double mouseX, double mouseY) {

        return this.visible && mouseX >= getX() && mouseX < getX() + BUTTON_WIDTH && mouseY >= getY() && mouseY < getY() + BUTTON_HEIGHT;
    }

    // Whether the mouse is over a rectangle given relative to the panel's top left corner.
    private boolean isOverPanelPart(double mouseX, double mouseY, int x, int y, int width, int height) {

        double relativeX = mouseX - panelX(), relativeY = mouseY - panelY();
        return relativeX >= x && relativeX < x + width && relativeY >= y && relativeY < y + height;
    }

    private int panelX() {

        return getX() + PANEL_OFFSET_X;
    }

    private int panelY() {

        return getY() + PANEL_OFFSET_Y;
    }

    private static int increaseArrowY(int row) {

        return ARROW_Y + row * 2 * (ARROW_HEIGHT + ARROW_GAP);
    }

    private static int decreaseArrowY(int row) {

        return increaseArrowY(row) + ARROW_HEIGHT + ARROW_GAP;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {

        output.add(NarratedElementType.TITLE, getMessage());
    }
}
