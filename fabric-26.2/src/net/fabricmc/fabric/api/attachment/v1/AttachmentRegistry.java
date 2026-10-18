package net.fabricmc.fabric.api.attachment.v1;

import com.mojang.serialization.Codec;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.fabricmc.fabric.impl.attachment.AttachmentRegistryImpl;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

public final class AttachmentRegistry {
   private AttachmentRegistry() {
   }

   public static <A> AttachmentType<A> create(Identifier id, Consumer<AttachmentRegistry.Builder<A>> consumer) {
      AttachmentRegistry.Builder<A> builder = AttachmentRegistryImpl.builder();
      consumer.accept(builder);
      return builder.buildAndRegister(id);
   }

   public static <A> AttachmentType<A> create(Identifier id) {
      return create(id, builder -> {});
   }

   public static <A> AttachmentType<A> createDefaulted(Identifier id, Supplier<A> initializer) {
      return create(id, builder -> builder.initializer(initializer));
   }

   public static <A> AttachmentType<A> createPersistent(Identifier id, Codec<A> codec) {
      return create(id, builder -> builder.persistent(codec));
   }

   @Deprecated
   public static <A> AttachmentRegistry.Builder<A> builder() {
      return AttachmentRegistryImpl.builder();
   }

   @NonExtendable
   public interface Builder<A> {
      AttachmentRegistry.Builder<A> persistent(Codec<A> var1);

      AttachmentRegistry.Builder<A> copyOnDeath();

      AttachmentRegistry.Builder<A> initializer(Supplier<A> var1);

      AttachmentRegistry.Builder<A> syncWith(StreamCodec<? super RegistryFriendlyByteBuf, A> var1, AttachmentSyncPredicate var2);

      AttachmentRegistry.Builder<A> syncWith(StreamCodec<? super RegistryFriendlyByteBuf, A> var1, AttachmentSyncPredicate var2, int var3);

      AttachmentType<A> buildAndRegister(Identifier var1);
   }
}
