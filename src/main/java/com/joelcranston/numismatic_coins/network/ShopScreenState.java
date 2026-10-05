package com.joelcranston.numismatic_coins.network;

import java.util.List;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.shop.AbstractShopBlockEntity;
import com.joelcranston.numismatic_coins.shop.ShopMenu;
import com.joelcranston.numismatic_coins.shop.ShopOffer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * What the owner's shop or pawn shop screen shows beyond the stock slots: the offers, the money held, the hopper
 * switch and the offer being edited. Sent when the menu opens and after every change made through
 * it.
 * (NO: UpdateShopScreenS2CPacket, UpdatePawnShopScreenS2CPacket.)
 */
public record ShopScreenState(int containerId, List<ShopOffer> offers, long storedValue, boolean allowsTransfer, ItemStack buffer)
        implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ShopScreenState> TYPE = new CustomPacketPayload.Type<>(NumismaticCoins.id("shop_screen_state"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShopScreenState> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, ShopScreenState::containerId,
            ShopOffer.STREAM_CODEC.apply(ByteBufCodecs.list(AbstractShopBlockEntity.MAX_OFFERS)), ShopScreenState::offers,
            ByteBufCodecs.VAR_LONG, ShopScreenState::storedValue,
            ByteBufCodecs.BOOL, ShopScreenState::allowsTransfer,
            ItemStack.OPTIONAL_STREAM_CODEC, ShopScreenState::buffer,
            ShopScreenState::new);

    @Override
    public CustomPacketPayload.Type<ShopScreenState> type() {

        return TYPE;
    }

    /** Runs on the client thread. */
    public void apply(Player player) {

        if (player.containerMenu instanceof ShopMenu menu && menu.containerId == this.containerId) menu.receiveState(this);
    }
}
