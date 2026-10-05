package com.joelcranston.numismatic_coins.purse;

import net.minecraft.world.entity.player.Player;

/** Where each loader keeps a player's purse: a Fabric data attachment or a NeoForge attachment type. */
public interface PurseStorage {

    Purse get(Player player);

    /** Stores the purse; on the server this also syncs it to the player's client. */
    void set(Player player, Purse purse);
}
