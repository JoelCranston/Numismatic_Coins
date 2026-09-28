package com.charleskorn.kaml

import kotlin.jvm.internal.Intrinsics
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.SerializersModule

internal class YamlListInput(list: YamlList, yaml: Yaml, context: SerializersModule, configuration: YamlConfiguration) : YamlInput(
      list, yaml, context, configuration
   ) {
   public final val list: YamlList
   private final var nextElementIndex: Int
   private final lateinit var currentElementDecoder: YamlInput

   private final val haveStartedReadingElements: Boolean
      private final get() {
         return this.nextElementIndex > 0;
      }


   init {
      this.list = list;
   }

   public override fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
      return this.list.getItems().size();
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      if (this.nextElementIndex == this.list.getItems().size()) {
         return -1;
      } else {
         this.currentElementDecoder = YamlInput.Companion
            .createFor$kaml(
               this.list.getItems().get(this.nextElementIndex),
               this.getYaml(),
               this.getSerializersModule(),
               this.getConfiguration(),
               descriptor.getElementDescriptor(0)
            );
         return this.nextElementIndex++;
      }
   }

   public override fun decodeNotNullMark(): Boolean {
      if (!this.getHaveStartedReadingElements()) {
         return true;
      } else {
         var var10000: YamlInput = this.currentElementDecoder;
         if (this.currentElementDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
            var10000 = null;
         }

         return var10000.decodeNotNullMark();
      }
   }

   public override fun decodeString(): String {
      var var10000: YamlInput = this.currentElementDecoder;
      if (this.currentElementDecoder == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
         var10000 = null;
      }

      return var10000.decodeString();
   }

   public override fun decodeInt(): Int {
      var var10000: YamlInput = this.currentElementDecoder;
      if (this.currentElementDecoder == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
         var10000 = null;
      }

      return var10000.decodeInt();
   }

   public override fun decodeLong(): Long {
      var var10000: YamlInput = this.currentElementDecoder;
      if (this.currentElementDecoder == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
         var10000 = null;
      }

      return var10000.decodeLong();
   }

   public override fun decodeShort(): Short {
      var var10000: YamlInput = this.currentElementDecoder;
      if (this.currentElementDecoder == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
         var10000 = null;
      }

      return var10000.decodeShort();
   }

   public override fun decodeByte(): Byte {
      var var10000: YamlInput = this.currentElementDecoder;
      if (this.currentElementDecoder == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
         var10000 = null;
      }

      return var10000.decodeByte();
   }

   public override fun decodeDouble(): Double {
      var var10000: YamlInput = this.currentElementDecoder;
      if (this.currentElementDecoder == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
         var10000 = null;
      }

      return var10000.decodeDouble();
   }

   public override fun decodeFloat(): Float {
      var var10000: YamlInput = this.currentElementDecoder;
      if (this.currentElementDecoder == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
         var10000 = null;
      }

      return var10000.decodeFloat();
   }

   public override fun decodeBoolean(): Boolean {
      var var10000: YamlInput = this.currentElementDecoder;
      if (this.currentElementDecoder == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
         var10000 = null;
      }

      return var10000.decodeBoolean();
   }

   public override fun decodeChar(): Char {
      var var10000: YamlInput = this.currentElementDecoder;
      if (this.currentElementDecoder == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
         var10000 = null;
      }

      return var10000.decodeChar();
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      var var10000: YamlInput = this.currentElementDecoder;
      if (this.currentElementDecoder == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
         var10000 = null;
      }

      return var10000.decodeEnum(enumDescriptor);
   }

   public override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
      if (!this.getHaveStartedReadingElements()) {
         return (T)super.decodeSerializableValue(deserializer);
      } else {
         var var10000: YamlInput = this.currentElementDecoder;
         if (this.currentElementDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
            var10000 = null;
         }

         return (T)var10000.decodeSerializableValue(deserializer);
      }
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      if (this.getHaveStartedReadingElements()) {
         var var10000: YamlInput = this.currentElementDecoder;
         if (this.currentElementDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
            var10000 = null;
         }

         return var10000;
      } else {
         return super.beginStructure(descriptor);
      }
   }

   public override fun getCurrentPath(): YamlPath {
      val var1: YamlPath;
      if (this.getHaveStartedReadingElements()) {
         var var10000: YamlInput = this.currentElementDecoder;
         if (this.currentElementDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentElementDecoder");
            var10000 = null;
         }

         var1 = var10000.getNode().getPath();
      } else {
         var1 = this.list.getPath();
      }

      return var1;
   }

   public override fun getCurrentLocation(): Location {
      return this.getCurrentPath().getEndLocation();
   }
}
