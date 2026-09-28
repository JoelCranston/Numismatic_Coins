package com.joelcranston.numismatic_coins.platform.fabric;

//? fabric {

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.purse.Purse;
import com.joelcranston.numismatic_coins.purse.PurseStorage;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.world.entity.player.Player;

// Saved with the player, kept through death, and synced to the owning player only.
public class FabricPurseStorage implements PurseStorage {

    private final AttachmentType<Purse> purseType = AttachmentRegistry.<Purse>create(NumismaticCoins.id("purse"), builder -> builder
            .initializer(() -> Purse.EMPTY)
            .persistent(Purse.CODEC)
            .copyOnDeath()
            .syncWith(Purse.STREAM_CODEC, AttachmentSyncPredicate.targetOnly()));

    @Override
    public Purse get(Player player) {

        return player.getAttachedOrElse(this.purseType, Purse.EMPTY);
    }

    @Override
    public void set(Player player, Purse purse) {

        player.setAttached(this.purseType, purse);
    }
}
//?}
