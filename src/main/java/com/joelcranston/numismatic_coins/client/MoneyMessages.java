package com.joelcranston.numismatic_coins.client;

import com.joelcranston.numismatic_coins.config.Configs;
import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.item.CoinText;
import com.joelcranston.numismatic_coins.network.PurseChanged;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

/** Shows purse changes as "+ [12 Silver 4 Bronze]". (NO: CurrencyComponent#modify.) */
public final class MoneyMessages {

    private MoneyMessages() {}

    public static void show(PurseChanged change) {

        Player player = Minecraft.getInstance().player;
        if (player == null || change.change() == 0) return;
        boolean isDeposit = change.change() > 0;
        MutableComponent message = Component.literal(isDeposit ? "+ " : "- ")
                .withStyle(isDeposit ? ChatFormatting.GREEN : ChatFormatting.RED)
                .append(Component.literal("[").withStyle(ChatFormatting.GRAY))
                .append(CoinText.coinCounts(CoinMath.split(Math.abs(change.change()))))
                .append(Component.literal("]").withStyle(ChatFormatting.GRAY));
        switch (Configs.client().moneyMessageLocation) {
            case ACTION_BAR -> player.sendOverlayMessage(message);
            case CHAT -> player.sendSystemMessage(message);
            case DISABLED -> {}
        }
    }
}
