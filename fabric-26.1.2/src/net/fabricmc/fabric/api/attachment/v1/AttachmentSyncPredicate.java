package net.fabricmc.fabric.api.attachment.v1;

import java.util.function.BiPredicate;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

@FunctionalInterface
@NonExtendable
public interface AttachmentSyncPredicate extends BiPredicate<AttachmentTarget, ServerPlayer> {
   static AttachmentSyncPredicate all() {
      return (var0, var1) -> true;
   }

   static AttachmentSyncPredicate targetOnly() {
      return (target, player) -> target == player;
   }

   static AttachmentSyncPredicate allButTarget() {
      return (target, player) -> target != player;
   }
}
