package net.fabricmc.fabric.api.attachment.v1;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.fabricmc.fabric.api.event.Event;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.ApiStatus.NonExtendable;
import org.jspecify.annotations.Nullable;

@NonExtendable
public interface AttachmentTarget {
   String NBT_ATTACHMENT_KEY = "fabric:attachments";

   @Nullable
   default <A> A getAttached(AttachmentType<A> type) {
      throw new UnsupportedOperationException("Implemented via mixin");
   }

   default <A> A getAttachedOrThrow(AttachmentType<A> type) {
      return Objects.requireNonNull(this.getAttached(type), "No value was attached");
   }

   default <A> A getAttachedOrSet(AttachmentType<A> type, A defaultValue) {
      Objects.requireNonNull(defaultValue, "default value cannot be null");
      A attached = this.getAttached(type);
      if (attached != null) {
         return attached;
      } else {
         this.setAttached(type, defaultValue);
         return defaultValue;
      }
   }

   default <A> A getAttachedOrCreate(AttachmentType<A> type, Supplier<A> initializer) {
      A attached = this.getAttached(type);
      if (attached != null) {
         return attached;
      } else {
         A initialized = Objects.requireNonNull(initializer.get(), "initializer result cannot be null");
         this.setAttached(type, initialized);
         return initialized;
      }
   }

   default <A> A getAttachedOrCreate(AttachmentType<A> type) {
      Supplier<A> init = type.initializer();
      if (init == null) {
         throw new IllegalArgumentException("Single-argument getAttachedOrCreate is reserved for attachment types with default initializers");
      } else {
         return this.getAttachedOrCreate(type, init);
      }
   }

   @Contract("_, !null -> !null")
   default <A> A getAttachedOrElse(AttachmentType<A> type, @Nullable A defaultValue) {
      A attached = this.getAttached(type);
      return attached == null ? defaultValue : attached;
   }

   default <A> A getAttachedOrGet(AttachmentType<A> type, Supplier<A> defaultValue) {
      Objects.requireNonNull(defaultValue, "default value supplier cannot be null");
      A attached = this.getAttached(type);
      return attached == null ? defaultValue.get() : attached;
   }

   @Nullable
   default <A> A setAttached(AttachmentType<A> type, @Nullable A value) {
      throw new UnsupportedOperationException("Implemented via mixin");
   }

   default boolean hasAttached(AttachmentType<?> type) {
      throw new UnsupportedOperationException("Implemented via mixin");
   }

   @Nullable
   default <A> A removeAttached(AttachmentType<A> type) {
      return this.setAttached(type, null);
   }

   default <A> Event<AttachmentTarget.OnAttachedSet<A>> onAttachedSet(AttachmentType<A> type) {
      throw new UnsupportedOperationException("Implemented via mixin");
   }

   @Nullable
   default <A> A modifyAttached(AttachmentType<A> type, UnaryOperator<A> modifier) {
      return this.setAttached(type, modifier.apply(this.getAttached(type)));
   }

   @FunctionalInterface
   public interface OnAttachedSet<A> {
      void onAttachedSet(@Nullable A var1, @Nullable A var2);
   }
}
