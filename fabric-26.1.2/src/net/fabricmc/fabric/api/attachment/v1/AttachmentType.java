package net.fabricmc.fabric.api.attachment.v1;

import com.mojang.serialization.Codec;
import java.util.function.Supplier;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus.NonExtendable;
import org.jspecify.annotations.Nullable;

@NonExtendable
public interface AttachmentType<A> {
   Identifier identifier();

   @Nullable
   Codec<A> persistenceCodec();

   default boolean isPersistent() {
      return this.persistenceCodec() != null;
   }

   @Nullable
   Supplier<A> initializer();

   boolean isSynced();

   boolean copyOnDeath();
}
