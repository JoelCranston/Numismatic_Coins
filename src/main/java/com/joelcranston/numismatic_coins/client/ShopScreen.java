package com.joelcranston.numismatic_coins.client;

import java.util.List;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.currency.Currency;
import com.joelcranston.numismatic_coins.network.ShopAction;
import com.joelcranston.numismatic_coins.shop.ShopBlockEntity;
import com.joelcranston.numismatic_coins.shop.ShopMenu;
import com.joelcranston.numismatic_coins.shop.ShopOffer;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * The owner's shop screen. The stock tab shows the stock slots; the offers tab covers them with
 * the shop's offers, and adds the editor for one offer: a slot to click with the stack to sell, a
 * price in bronze, and buttons to submit or delete it. Both tabs show the earnings, which the
 * owner takes into their purse, and the switch that lets hoppers fill the stock.
 * (NO: ShopScreen.)
 */
public class ShopScreen extends AbstractContainerScreen<ShopMenu> {

    private static final Identifier TEXTURE = NumismaticCoins.id("textures/gui/shop_gui.png");
    private static final Identifier OFFERS_TEXTURE = NumismaticCoins.id("textures/gui/shop_gui_trades.png");
    private static final int TEXTURE_SIZE = 256;
    private static final int IMAGE_WIDTH = 176, IMAGE_HEIGHT = 168;
    private static final int TITLE_Y = 5, INVENTORY_LABEL_Y = 75;
    // Off the screen: the offers tab covers the title.
    private static final int HIDDEN_LABEL_Y = -10_000;
    private static final int SLOT_SIZE = 16;
    private static final int TEXT_COLOR = 0xFFFFFFFF;

    // Tabs, left of the background, the chosen one drawn to blend into it.
    private static final int TAB_X = -29, TAB_Y = 5, TAB_SPACING = 32, TAB_WIDTH = 32, TAB_HEIGHT = 28, TAB_U = 113, TAB_V = 168;
    private static final int TAB_ICON_X = 9, TAB_ICON_Y = 6;

    // The column right of the background: the earnings over the hopper switch, with the offer editor above both on the offers tab.
    private static final int COLUMN_X = 178, COLUMN_GAP = 3;
    private static final int EARNINGS_U = 146, EARNINGS_V = 169, EARNINGS_WIDTH = 34, EARNINGS_HEIGHT = 54;
    // One count per coin, gold at the top.
    private static final int EARNINGS_COUNT_X = 5, EARNINGS_COUNT_Y = 7, EARNINGS_ROW_HEIGHT = 12;
    private static final int EXTRACT_X = 4, EXTRACT_Y = 41, EXTRACT_WIDTH = 26, EXTRACT_HEIGHT = 8, EXTRACT_U = 146, EXTRACT_V = 224;
    private static final int TRANSFER_SIZE = 28, TRANSFER_ICON_OFFSET = 6, TRANSFER_MARK_OFFSET = 15;
    private static final int TRANSFER_ON_COLOR = 0xFF28FFBF, TRANSFER_OFF_COLOR = 0xFFEB1D36;
    // The hopper switch's panel, in the colours of a vanilla container background.
    private static final int PANEL_EDGE_COLOR = 0xFF000000, PANEL_LIGHT_COLOR = 0xFFFFFFFF, PANEL_SHADOW_COLOR = 0xFF555555, PANEL_FACE_COLOR = 0xFFC6C6C6;

    // Offer editor.
    private static final int EDITOR_U = 15, EDITOR_V = 169, EDITOR_WIDTH = 98, EDITOR_HEIGHT = 54;
    private static final int BUFFER_X = 8, BUFFER_Y = 15;
    private static final int PRICE_X = 35, PRICE_Y = 18, PRICE_WIDTH = 47, PRICE_HEIGHT = 11;
    private static final int PRICE_DIGITS = 7;
    // The price split into coins: bronze, silver and gold, left to right.
    private static final int PRICE_COINS_X = 36, PRICE_COINS_Y = 5;
    private static final int[] PRICE_COIN_OFFSETS = {0, 20, 40};
    private static final int PRICE_COINS_COLOR = 0xFF898989;
    private static final int SUBMIT_X = 7, DELETE_X = 50, EDIT_BUTTON_Y = 36, EDIT_BUTTON_WIDTH = 41, EDIT_BUTTON_HEIGHT = 11;
    private static final int SUBMIT_U = 15, DELETE_U = 56, EDIT_BUTTON_V = 223;

    // Offer list, over the stock: two columns, three rows showing.
    private static final int OFFERS_X = 8, OFFERS_Y = 10, OFFERS_WIDTH = 160, OFFERS_HEIGHT = 60;
    private static final int OFFER_WIDTH = 78, OFFER_HEIGHT = 20, OFFER_COLUMN_GAP = 4, OFFER_COLUMNS = 2;
    private static final int OFFER_ITEM_X = 4, OFFER_ITEM_Y = 2;
    private static final int CENT_U = 1, CENT_V = 172, CENT_WIDTH = 5, CENT_HEIGHT = 7, CENT_X = 23, CENT_Y = 6;
    private static final int OFFER_PRICE_X = 30, OFFER_PRICE_Y = 6;
    private static final Identifier OFFER_SPRITE = Identifier.withDefaultNamespace("widget/button");
    private static final Identifier OFFER_HOVERED_SPRITE = Identifier.withDefaultNamespace("widget/button_highlighted");

    private TextureButton stockTab;
    private TextureButton offersTab;
    private TextureButton extractButton;
    private TransferButton transferButton;
    private EditBox priceField;
    private TextureButton submitButton;
    private TextureButton deleteButton;
    private boolean isOffersTab;
    private int scrollRow;
    private int shownOfferCount;

    public ShopScreen(ShopMenu menu, Inventory inventory, Component title) {

        super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
        this.inventoryLabelY = INVENTORY_LABEL_Y;
    }

    @Override
    protected void init() {

        super.init();
        int tabX = this.leftPos + TAB_X, tabY = this.topPos + TAB_Y;
        this.stockTab = this.addRenderableWidget(new TextureButton(tabX, tabY, TAB_WIDTH, TAB_HEIGHT, TEXTURE, TAB_U, TAB_V,
                Component.translatable("gui.numismatic_coins.shop.tab.stock"), () -> this.selectTab(false)));
        this.offersTab = this.addRenderableWidget(new TextureButton(tabX, tabY + TAB_SPACING, TAB_WIDTH, TAB_HEIGHT, TEXTURE, TAB_U, TAB_V,
                Component.translatable("gui.numismatic_coins.shop.tab.offers"), () -> this.selectTab(true)));
        this.extractButton = this.addRenderableWidget(new TextureButton(0, 0, EXTRACT_WIDTH, EXTRACT_HEIGHT, TEXTURE, EXTRACT_U, EXTRACT_V,
                Component.translatable("gui.numismatic_coins.shop.extract"), () -> this.send(ShopAction.Action.EXTRACT_CURRENCY, 0)));
        this.transferButton = this.addRenderableWidget(new TransferButton());

        this.priceField = new EditBox(this.font, PRICE_WIDTH, PRICE_HEIGHT, Component.translatable("gui.numismatic_coins.shop.price"));
        this.priceField.setMaxLength(PRICE_DIGITS);
        this.priceField.setBordered(false);
        this.priceField.setResponder(this::onPriceChanged);
        this.addRenderableWidget(this.priceField);
        this.submitButton = this.addRenderableWidget(new TextureButton(0, 0, EDIT_BUTTON_WIDTH, EDIT_BUTTON_HEIGHT, TEXTURE, SUBMIT_U, EDIT_BUTTON_V,
                Component.translatable("gui.numismatic_coins.shop.submit_offer"), this::submitOffer));
        this.deleteButton = this.addRenderableWidget(new TextureButton(0, 0, EDIT_BUTTON_WIDTH, EDIT_BUTTON_HEIGHT, TEXTURE, DELETE_U, EDIT_BUTTON_V,
                Component.translatable("gui.numismatic_coins.shop.delete_offer"), () -> this.send(ShopAction.Action.DELETE_OFFER, 0)));

        this.selectTab(this.isOffersTab);
    }

    private void selectTab(boolean isOffersTab) {

        this.isOffersTab = isOffersTab;
        this.menu.setShowsStock(!isOffersTab);
        this.stockTab.active = isOffersTab;
        this.offersTab.active = !isOffersTab;
        this.titleLabelY = isOffersTab ? HIDDEN_LABEL_Y : TITLE_Y;

        this.priceField.visible = isOffersTab;
        this.submitButton.visible = isOffersTab;
        this.deleteButton.visible = isOffersTab;
        if (!isOffersTab) this.priceField.setFocused(false);

        int editorX = this.columnX(), editorY = this.topPos;
        this.priceField.setPosition(editorX + PRICE_X, editorY + PRICE_Y);
        this.submitButton.setPosition(editorX + SUBMIT_X, editorY + EDIT_BUTTON_Y);
        this.deleteButton.setPosition(editorX + DELETE_X, editorY + EDIT_BUTTON_Y);
        this.extractButton.setPosition(this.columnX() + EXTRACT_X, this.earningsY() + EXTRACT_Y);
        this.transferButton.setPosition(this.columnX(), this.earningsY() + EARNINGS_HEIGHT + COLUMN_GAP);
    }

    @Override
    protected void containerTick() {

        super.containerTick();
        List<ShopOffer> offers = this.menu.offers();
        // A new offer scrolls the list to show it.
        if (offers.size() > this.shownOfferCount) this.scrollRow = this.maxScrollRow();
        this.shownOfferCount = offers.size();
        this.scrollRow = Math.clamp(this.scrollRow, 0, this.maxScrollRow());

        boolean hasOffer = this.menu.hasOfferForBuffer();
        this.submitButton.active = this.price() > 0 && !this.menu.buffer().isEmpty() && (offers.size() < ShopBlockEntity.MAX_OFFERS || hasOffer);
        this.deleteButton.active = hasOffer;
        this.transferButton.setTooltip(Tooltip.create(Component.translatable(this.menu.allowsTransfer()
                ? "gui.numismatic_coins.shop.transfer_tooltip.enabled"
                : "gui.numismatic_coins.shop.transfer_tooltip.disabled")));
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        graphics.blit(RenderPipelines.GUI_TEXTURED, this.isOffersTab ? OFFERS_TEXTURE : TEXTURE, this.leftPos, this.topPos,
                0, 0, this.imageWidth, this.imageHeight, TEXTURE_SIZE, TEXTURE_SIZE);
        this.extractEarnings(graphics);
        if (!this.isOffersTab) return;
        this.extractEditor(graphics);
        this.extractOffers(graphics, mouseX, mouseY);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        super.extractContents(graphics, mouseX, mouseY, partialTick);
        // The tab icons go over the tab buttons, which are drawn after the background.
        graphics.item(Items.CHEST.getDefaultInstance(), this.stockTab.getX() + TAB_ICON_X, this.stockTab.getY() + TAB_ICON_Y);
        graphics.item(Items.EMERALD.getDefaultInstance(), this.offersTab.getX() + TAB_ICON_X, this.offersTab.getY() + TAB_ICON_Y);
    }

    private void extractEarnings(GuiGraphicsExtractor graphics) {

        int x = this.columnX(), y = this.earningsY();
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, EARNINGS_U, EARNINGS_V, EARNINGS_WIDTH, EARNINGS_HEIGHT, TEXTURE_SIZE, TEXTURE_SIZE);
        long[] coins = CoinMath.split(this.menu.storedValue());
        Currency[] currencies = Currency.values();
        for (int row = 0; row < currencies.length; row++) {
            Currency currency = currencies[currencies.length - 1 - row];
            graphics.text(this.font, Long.toString(coins[currency.ordinal()]), x + EARNINGS_COUNT_X, y + EARNINGS_COUNT_Y + row * EARNINGS_ROW_HEIGHT, TEXT_COLOR, false);
        }
    }

    private void extractEditor(GuiGraphicsExtractor graphics) {

        int x = this.columnX(), y = this.topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, EDITOR_U, EDITOR_V, EDITOR_WIDTH, EDITOR_HEIGHT, TEXTURE_SIZE, TEXTURE_SIZE);
        long[] coins = CoinMath.split(this.price());
        for (Currency currency : Currency.values()) {
            graphics.text(this.font, Long.toString(coins[currency.ordinal()]), x + PRICE_COINS_X + PRICE_COIN_OFFSETS[currency.ordinal()],
                    y + PRICE_COINS_Y, PRICE_COINS_COLOR, false);
        }
        ItemStack buffer = this.menu.buffer();
        graphics.item(buffer, x + BUFFER_X, y + BUFFER_Y);
        graphics.itemDecorations(this.font, buffer, x + BUFFER_X, y + BUFFER_Y);
    }

    private void extractOffers(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {

        int left = this.leftPos + OFFERS_X, top = this.topPos + OFFERS_Y;
        graphics.enableScissor(left, top, left + OFFERS_WIDTH, top + OFFERS_HEIGHT);
        List<ShopOffer> offers = this.menu.offers();
        int hovered = this.offerAt(mouseX, mouseY);
        for (int index = 0; index < offers.size(); index++) {
            ShopOffer offer = offers.get(index);
            int x = this.offerX(index), y = this.offerY(index);
            if (y + OFFER_HEIGHT <= top || y >= top + OFFERS_HEIGHT) continue;

            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, index == hovered ? OFFER_HOVERED_SPRITE : OFFER_SPRITE, x, y, OFFER_WIDTH, OFFER_HEIGHT);
            graphics.item(offer.stack(), x + OFFER_ITEM_X, y + OFFER_ITEM_Y);
            graphics.itemDecorations(this.font, offer.stack(), x + OFFER_ITEM_X, y + OFFER_ITEM_Y);
            graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + CENT_X, y + CENT_Y, CENT_U, CENT_V, CENT_WIDTH, CENT_HEIGHT, TEXTURE_SIZE, TEXTURE_SIZE);
            graphics.text(this.font, Long.toString(offer.price()), x + OFFER_PRICE_X, y + OFFER_PRICE_Y, TEXT_COLOR, true);
        }
        graphics.disableScissor();
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {

        super.extractTooltip(graphics, mouseX, mouseY);
        if (!this.isOffersTab || !this.menu.getCarried().isEmpty()) return;
        ItemStack buffer = this.menu.buffer();
        if (!buffer.isEmpty() && this.isOverBuffer(mouseX, mouseY)) graphics.setTooltipForNextFrame(this.font, buffer, mouseX, mouseY);
        int index = this.offerAt(mouseX, mouseY);
        if (index >= 0) graphics.setTooltipForNextFrame(this.font, this.menu.offers().get(index).stack(), mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean isDoubleClick) {

        if (this.isOffersTab) {
            if (this.isOverBuffer(event.x(), event.y())) {
                this.send(ShopAction.Action.CLICK_BUFFER, 0);
                return true;
            }
            int index = this.offerAt(event.x(), event.y());
            if (index >= 0) {
                this.send(ShopAction.Action.LOAD_OFFER, index);
                this.priceField.setValue(Long.toString(this.menu.offers().get(index).price()));
                AbstractButton.playButtonClickSound(this.minecraft.getSoundManager());
                return true;
            }
        }
        return super.mouseClicked(event, isDoubleClick);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {

        if (this.isOffersTab && this.isOverOffers(mouseX, mouseY)) {
            this.scrollRow = Math.clamp(this.scrollRow - (int) Math.signum(scrollY), 0, this.maxScrollRow());
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {

        // A focused price field keeps typed keys, the inventory key among them, from closing the screen.
        if (this.priceField.visible && this.priceField.isFocused() && !event.isEscape()) {
            return this.priceField.keyPressed(event) || this.priceField.canConsumeInput() || super.keyPressed(event);
        }
        return super.keyPressed(event);
    }

    // The tabs and the column beside the background count as inside it, so a click there never drops the carried stack.
    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int left, int top) {

        if (isOver(mouseX, mouseY, left + TAB_X, top + TAB_Y, TAB_WIDTH, TAB_SPACING + TAB_HEIGHT)) return false;
        int columnHeight = this.earningsY() - top + EARNINGS_HEIGHT + COLUMN_GAP + TRANSFER_SIZE;
        if (isOver(mouseX, mouseY, left + COLUMN_X, top, EDITOR_WIDTH, columnHeight)) return false;
        return super.hasClickedOutside(mouseX, mouseY, left, top);
    }

    private void onPriceChanged(String value) {

        String digits = value.replaceAll("\\D", "");
        if (!digits.equals(value)) this.priceField.setValue(digits);
    }

    private void submitOffer() {

        long price = this.price();
        if (price > 0) this.send(ShopAction.Action.CREATE_OFFER, price);
    }

    private long price() {

        String value = this.priceField.getValue();
        return value.isEmpty() ? 0 : Long.parseLong(value);
    }

    private int offerAt(double mouseX, double mouseY) {

        if (!this.isOffersTab || !this.isOverOffers(mouseX, mouseY)) return -1;
        List<ShopOffer> offers = this.menu.offers();
        for (int index = 0; index < offers.size(); index++) {
            if (isOver(mouseX, mouseY, this.offerX(index), this.offerY(index), OFFER_WIDTH, OFFER_HEIGHT)) return index;
        }
        return -1;
    }

    private boolean isOverOffers(double mouseX, double mouseY) {

        return isOver(mouseX, mouseY, this.leftPos + OFFERS_X, this.topPos + OFFERS_Y, OFFERS_WIDTH, OFFERS_HEIGHT);
    }

    private boolean isOverBuffer(double mouseX, double mouseY) {

        return isOver(mouseX, mouseY, this.columnX() + BUFFER_X, this.topPos + BUFFER_Y, SLOT_SIZE, SLOT_SIZE);
    }

    private static boolean isOver(double mouseX, double mouseY, int x, int y, int width, int height) {

        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    private int offerX(int index) {

        return this.leftPos + OFFERS_X + index % OFFER_COLUMNS * (OFFER_WIDTH + OFFER_COLUMN_GAP);
    }

    private int offerY(int index) {

        return this.topPos + OFFERS_Y + (index / OFFER_COLUMNS - this.scrollRow) * OFFER_HEIGHT;
    }

    private int maxScrollRow() {

        int rows = (this.menu.offers().size() + OFFER_COLUMNS - 1) / OFFER_COLUMNS;
        return Math.max(0, rows - OFFERS_HEIGHT / OFFER_HEIGHT);
    }

    private int columnX() {

        return this.leftPos + COLUMN_X;
    }

    private int earningsY() {

        return this.topPos + (this.isOffersTab ? EDITOR_HEIGHT + COLUMN_GAP : 0);
    }

    private void send(ShopAction.Action action, long value) {

        NumismaticCoinsClient.clientXplat().sendToServer(new ShopAction(action, value));
    }

    /** The hopper switch: a panel holding a hopper, ticked while hoppers may fill the stock. */
    private class TransferButton extends AbstractButton {

        TransferButton() {

            super(0, 0, TRANSFER_SIZE, TRANSFER_SIZE, Component.translatable("gui.numismatic_coins.shop.transfer"));
        }

        @Override
        public void onPress(InputWithModifiers input) {

            ShopScreen.this.send(ShopAction.Action.TOGGLE_TRANSFER, 0);
        }

        @Override
        protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

            int x = this.getX(), y = this.getY(), right = x + this.width, bottom = y + this.height;
            graphics.fill(x + 1, y, right - 1, bottom, PANEL_EDGE_COLOR);
            graphics.fill(x, y + 1, right, bottom - 1, PANEL_EDGE_COLOR);
            graphics.fill(x + 1, y + 1, right - 1, bottom - 1, PANEL_LIGHT_COLOR);
            graphics.fill(x + 2, y + 2, right - 1, bottom - 1, PANEL_SHADOW_COLOR);
            graphics.fill(x + 2, y + 2, right - 2, bottom - 2, PANEL_FACE_COLOR);
            graphics.item(Items.HOPPER.getDefaultInstance(), x + TRANSFER_ICON_OFFSET, y + TRANSFER_ICON_OFFSET);

            boolean allowsTransfer = ShopScreen.this.menu.allowsTransfer();
            Component mark = Component.literal(allowsTransfer ? "✔" : "✘");
            graphics.text(ShopScreen.this.font, mark, x + TRANSFER_MARK_OFFSET, y + TRANSFER_MARK_OFFSET,
                    allowsTransfer ? TRANSFER_ON_COLOR : TRANSFER_OFF_COLOR, true);
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput output) {

            this.defaultButtonNarrationText(output);
        }
    }
}
