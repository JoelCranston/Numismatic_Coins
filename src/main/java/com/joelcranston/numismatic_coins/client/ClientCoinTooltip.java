package com.joelcranston.numismatic_coins.client;

import java.util.ArrayList;
import java.util.List;

import com.joelcranston.numismatic_coins.currency.Currency;
import com.joelcranston.numismatic_coins.item.CoinTooltip;
import com.joelcranston.numismatic_coins.registry.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

/**
 * Draws a {@link CoinTooltip}: one row per denomination held, largest coin first, each a coin icon
 * and its count. (NO: CurrencyTooltipComponent.)
 */
public class ClientCoinTooltip implements ClientTooltipComponent {

    // Row layout from NO's CurrencyTooltipComponent; the icon overhangs its row into the padding.
    private static final int ROW_HEIGHT = 10;
    private static final int ICON_X = -4, ICON_Y = -5;
    private static final int TEXT_X = 12;
    private static final int TEXT_COLOR = 0xFFFFFFFF;

    private final List<ItemStack> icons = new ArrayList<>();
    private final List<Component> counts = new ArrayList<>();

    public ClientCoinTooltip(CoinTooltip tooltip) {

        long[] coinCounts = tooltip.coinCounts();
        for (int denomination = coinCounts.length - 1; denomination >= 0; denomination--) {
            if (coinCounts[denomination] <= 0) continue;
            this.icons.add(new ItemStack(ModItems.coin(Currency.values()[denomination])));
            this.counts.add(Component.literal(Long.toString(coinCounts[denomination])).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public int getHeight(Font font) {

        return ROW_HEIGHT * this.counts.size();
    }

    @Override
    public int getWidth(Font font) {

        int widest = 0;
        for (Component count : this.counts) widest = Math.max(widest, font.width(count));
        return TEXT_X + widest;
    }

    @Override
    public void extractText(GuiGraphicsExtractor graphics, Font font, int x, int y) {

        for (int row = 0; row < this.counts.size(); row++) {
            graphics.text(font, this.counts.get(row), x + TEXT_X, y + row * ROW_HEIGHT, TEXT_COLOR, true);
        }
    }

    @Override
    public void extractImage(Font font, int x, int y, int width, int height, GuiGraphicsExtractor graphics) {

        for (int row = 0; row < this.icons.size(); row++) {
            graphics.fakeItem(this.icons.get(row), x + ICON_X, y + ICON_Y + row * ROW_HEIGHT);
        }
    }
}
