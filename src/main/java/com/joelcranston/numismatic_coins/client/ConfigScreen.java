package com.joelcranston.numismatic_coins.client;

import java.util.ArrayList;
import java.util.List;

import com.joelcranston.numismatic_coins.config.ConfigOption;
import com.joelcranston.numismatic_coins.config.ConfigOptions;
import com.joelcranston.numismatic_coins.config.Configs;
import com.joelcranston.numismatic_coins.config.NumismaticConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jspecify.annotations.Nullable;

/**
 * The mod's options, one page per section: Features, Mob drops and Client. Opened from Mod Menu on
 * Fabric and the mod list on NeoForge. Edits go straight into the local config, which is saved when
 * the screen closes.
 *
 * <p>Server options are editable only when no remote server is connected, as then this game is the
 * server. On a remote server they are shown as that server has them, read-only.
 */
public class ConfigScreen extends Screen {

    private static final int TITLE_Y = 12;
    private static final int TAB_Y = 30, TAB_WIDTH = 100, TAB_GAP = 4;
    private static final int FIRST_ROW_Y = 60, ROW_HEIGHT = 24;
    private static final int CONTROL_WIDTH = 150, CONTROL_HEIGHT = 20, COLUMN_GAP = 10;
    private static final int NOTE_MARGIN_BOTTOM = 44;
    private static final int DONE_WIDTH = 200, DONE_MARGIN_BOTTOM = 28;
    private static final int LABEL_TEXT_OFFSET_Y = 6;
    private static final int TEXT_COLOR = 0xFFFFFFFF, NOTE_COLOR = 0xFFA0A0A0, INVALID_COLOR = 0xFFFF5555;

    private final @Nullable Screen parent;
    private ConfigOption.Section section = ConfigOption.Section.FEATURES;
    // Labels for the rows on the current page, drawn beside their controls.
    private final List<Row> rows = new ArrayList<>();

    public ConfigScreen(@Nullable Screen parent) {

        super(Component.translatable("config.numismatic_coins.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {

        this.rows.clear();
        ConfigOption.Section[] sections = ConfigOption.Section.values();
        int tabsWidth = sections.length * TAB_WIDTH + (sections.length - 1) * TAB_GAP;
        int tabX = (this.width - tabsWidth) / 2;
        for (ConfigOption.Section tab : sections) {
            Button tabButton = Button.builder(Component.translatable(tab.translationKey()), button -> showSection(tab))
                    .bounds(tabX, TAB_Y, TAB_WIDTH, CONTROL_HEIGHT)
                    .build();
            tabButton.active = tab != this.section;
            addRenderableWidget(tabButton);
            tabX += TAB_WIDTH + TAB_GAP;
        }

        boolean isEditable = !this.section.isServerSide() || isThisGameTheServer();
        NumismaticConfig shownConfig = shownConfig();
        int controlX = this.width / 2 + COLUMN_GAP / 2;
        int rowY = FIRST_ROW_Y;
        for (ConfigOption<?> option : ConfigOptions.ALL) {
            if (option.section() != this.section) continue;
            AbstractWidget control = createControl(option, shownConfig, controlX, rowY);
            control.active = isEditable;
            Component tooltip = tooltip(option);
            if (tooltip != null) control.setTooltip(Tooltip.create(tooltip));
            addRenderableWidget(control);
            this.rows.add(new Row(Component.translatable(option.translationKey()), rowY));
            rowY += ROW_HEIGHT;
        }

        addRenderableWidget(Button.builder(Component.translatable("gui.done"), button -> onClose())
                .bounds((this.width - DONE_WIDTH) / 2, this.height - DONE_MARGIN_BOTTOM, DONE_WIDTH, CONTROL_HEIGHT)
                .build());
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
        graphics.centeredText(this.font, this.title, this.width / 2, TITLE_Y, TEXT_COLOR);
        int labelRight = this.width / 2 - COLUMN_GAP / 2;
        for (Row row : this.rows) {
            graphics.text(this.font, row.label(), labelRight - this.font.width(row.label()), row.y() + LABEL_TEXT_OFFSET_Y, TEXT_COLOR);
        }
        if (this.section.isServerSide() && !isThisGameTheServer()) {
            graphics.centeredText(this.font, Component.translatable("config.numismatic_coins.server_read_only"),
                    this.width / 2, this.height - NOTE_MARGIN_BOTTOM, NOTE_COLOR);
        }
    }

    @Override
    public void onClose() {

        Configs.save();
        // A game hosting its own world acts on the new settings at once; show them the same way.
        if (isThisGameTheServer()) Configs.receiveFromServer(Configs.server());
        NumismaticCoinsClient.clientXplat().setScreen(this.parent);
    }

    private void showSection(ConfigOption.Section newSection) {

        this.section = newSection;
        rebuildWidgets();
    }

    private <T> AbstractWidget createControl(ConfigOption<T> option, NumismaticConfig shownConfig, int x, int y) {

        List<T> values = option.type().values();
        return values.isEmpty() ? textBox(option, shownConfig, x, y) : cyclingButton(option, shownConfig, values, x, y);
    }

    // A button showing the value, which moves to the next value when pressed.
    private <T> Button cyclingButton(ConfigOption<T> option, NumismaticConfig shownConfig, List<T> values, int x, int y) {

        return Button.builder(valueText(option, option.get(shownConfig)), button -> {
            T current = option.get(Configs.local());
            T next = values.get((values.indexOf(current) + 1) % values.size());
            option.set(Configs.local(), next);
            button.setMessage(valueText(option, next));
        }).bounds(x, y, CONTROL_WIDTH, CONTROL_HEIGHT).build();
    }

    // A text box that sets the option whenever its text parses, and turns red when it does not.
    private <T> EditBox textBox(ConfigOption<T> option, NumismaticConfig shownConfig, int x, int y) {

        EditBox box = new EditBox(this.font, x, y, CONTROL_WIDTH, CONTROL_HEIGHT, Component.translatable(option.translationKey()));
        box.setValue(option.type().format(option.get(shownConfig)));
        box.setResponder(text -> box.setTextColor(option.setFromText(Configs.local(), text) ? TEXT_COLOR : INVALID_COLOR));
        return box;
    }

    private static <T> Component valueText(ConfigOption<T> option, T value) {

        return switch (value) {
            case Boolean isOn -> Component.translatable(isOn ? "options.on" : "options.off");
            default -> Component.translatable(option.translationKey() + "." + option.type().format(value));
        };
    }

    // The option's own tooltip if its lang file has one, plus a note when it needs a reload.
    private static @Nullable Component tooltip(ConfigOption<?> option) {

        String tooltipKey = option.translationKey() + ".tooltip";
        boolean hasOwnTooltip = Language.getInstance().has(tooltipKey);
        if (!hasOwnTooltip && !option.needsReload()) return null;
        MutableComponent tooltip = Component.empty();
        if (hasOwnTooltip) tooltip.append(Component.translatable(tooltipKey));
        if (hasOwnTooltip && option.needsReload()) tooltip.append("\n");
        if (option.needsReload()) tooltip.append(Component.translatable("config.numismatic_coins.needs_reload").withStyle(ChatFormatting.YELLOW));
        return tooltip;
    }

    // What this page shows: the joined server's settings for server sections, else the local file.
    private NumismaticConfig shownConfig() {

        if (!this.section.isServerSide() || isThisGameTheServer()) return Configs.local();
        NumismaticConfig.ServerSettings settings = Configs.shown();
        NumismaticConfig shown = new NumismaticConfig();
        shown.features = settings.features();
        shown.mobDrops = settings.mobDrops();
        return shown;
    }

    // True in menus and in single player or a world this game hosts.
    private static boolean isThisGameTheServer() {

        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.getConnection() == null || minecraft.hasSingleplayerServer();
    }

    private record Row(Component label, int y) {}
}
