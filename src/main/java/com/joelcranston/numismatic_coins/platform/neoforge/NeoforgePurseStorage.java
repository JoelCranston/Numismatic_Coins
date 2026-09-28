package com.joelcranston.numismatic_coins.platform.neoforge;

//? neoforge {

/*import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.platform.Registration;
import com.joelcranston.numismatic_coins.purse.Purse;
import com.joelcranston.numismatic_coins.purse.PurseStorage;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

// Saved with the player, kept through death, and synced to the owning player only.
public class NeoforgePurseStorage implements PurseStorage {

    private final Supplier<AttachmentType<Purse>> purseType;

    public NeoforgePurseStorage(Registration registration) {

        this.purseType = registration.<AttachmentType<?>, AttachmentType<Purse>>register(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, "purse",
                id -> AttachmentType.builder(() -> Purse.EMPTY)
                        .serialize(Purse.MAP_CODEC)
                        .copyOnDeath()
                        .sync((holder, player) -> holder == player, Purse.STREAM_CODEC)
                        .build());
    }

    @Override
    public Purse get(Player player) {

        return player.getData(this.purseType);
    }

    @Override
    public void set(Player player, Purse purse) {

        player.setData(this.purseType, purse);
    }
}
*///?}
