package com.charleskorn.kaml

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.modules.SerializersModule

internal sealed class YamlMapLikeInputBase protected constructor(map: YamlMap, yaml: Yaml, context: SerializersModule, configuration: YamlConfiguration) : YamlInput(
      map, yaml, context, configuration
   ) {
   protected final lateinit var currentValueDecoder: YamlInput
      internal set

   protected final lateinit var currentKey: YamlScalar
      internal set

   protected final var currentlyReadingValue: Boolean
      internal set

   protected final val haveStartedReadingEntries: Boolean
      protected final get() {
         return this.currentValueDecoder != null;
      }


   protected final val propertyName: String
      protected final get() {
         return this.getCurrentKey().getContent();
      }


   public override fun decodeNotNullMark(): Boolean {
      return !this.getHaveStartedReadingEntries() || this.fromCurrentValue(YamlMapLikeInputBase::decodeNotNullMark$lambda$0);
   }

   public override fun decodeString(): String {
      return this.fromCurrentValue(YamlMapLikeInputBase::decodeString$lambda$0);
   }

   public override fun decodeInt(): Int {
      return this.fromCurrentValue(YamlMapLikeInputBase::decodeInt$lambda$0).intValue();
   }

   public override fun decodeLong(): Long {
      return this.fromCurrentValue(YamlMapLikeInputBase::decodeLong$lambda$0).longValue();
   }

   public override fun decodeShort(): Short {
      return this.fromCurrentValue(YamlMapLikeInputBase::decodeShort$lambda$0).shortValue();
   }

   public override fun decodeByte(): Byte {
      return this.fromCurrentValue(YamlMapLikeInputBase::decodeByte$lambda$0).byteValue();
   }

   public override fun decodeDouble(): Double {
      return this.fromCurrentValue(YamlMapLikeInputBase::decodeDouble$lambda$0).doubleValue();
   }

   public override fun decodeFloat(): Float {
      return this.fromCurrentValue(YamlMapLikeInputBase::decodeFloat$lambda$0).floatValue();
   }

   public override fun decodeBoolean(): Boolean {
      return this.fromCurrentValue(YamlMapLikeInputBase::decodeBoolean$lambda$0);
   }

   public override fun decodeChar(): Char {
      return this.fromCurrentValue(YamlMapLikeInputBase::decodeChar$lambda$0);
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      return this.fromCurrentValue(YamlMapLikeInputBase::decodeEnum$lambda$0).intValue();
   }

   public override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
      return (T)(if (!this.getHaveStartedReadingEntries())
         super.decodeSerializableValue(deserializer)
         else
         this.fromCurrentValue(YamlMapLikeInputBase::decodeSerializableValue$lambda$0));
   }

   protected fun <T> fromCurrentValue(action: (YamlInput) -> T): T {
      try {
         return (T)action.invoke(this.getCurrentValueDecoder());
      } catch (var3: YamlException) {
         if (this.currentlyReadingValue) {
            throw new InvalidPropertyValueException(this.getPropertyName(), var3.getMessage(), var3.getPath(), var3);
         } else {
            throw var3;
         }
      }
   }

   public override fun getCurrentPath(): YamlPath {
      return if (this.getHaveStartedReadingEntries()) this.getCurrentValueDecoder().getNode().getPath() else this.getNode().getPath();
   }

   public override fun getCurrentLocation(): Location {
      return this.getCurrentPath().getEndLocation();
   }

   @JvmStatic
   fun YamlInput.`decodeNotNullMark$lambda$0`(): Boolean {
      return `$this$fromCurrentValue`.decodeNotNullMark();
   }

   @JvmStatic
   fun YamlInput.`decodeString$lambda$0`(): java.lang.String {
      return `$this$fromCurrentValue`.decodeString();
   }

   @JvmStatic
   fun YamlInput.`decodeInt$lambda$0`(): Int {
      return `$this$fromCurrentValue`.decodeInt();
   }

   @JvmStatic
   fun YamlInput.`decodeLong$lambda$0`(): Long {
      return `$this$fromCurrentValue`.decodeLong();
   }

   @JvmStatic
   fun YamlInput.`decodeShort$lambda$0`(): Short {
      return `$this$fromCurrentValue`.decodeShort();
   }

   @JvmStatic
   fun YamlInput.`decodeByte$lambda$0`(): Byte {
      return `$this$fromCurrentValue`.decodeByte();
   }

   @JvmStatic
   fun YamlInput.`decodeDouble$lambda$0`(): Double {
      return `$this$fromCurrentValue`.decodeDouble();
   }

   @JvmStatic
   fun YamlInput.`decodeFloat$lambda$0`(): Float {
      return `$this$fromCurrentValue`.decodeFloat();
   }

   @JvmStatic
   fun YamlInput.`decodeBoolean$lambda$0`(): Boolean {
      return `$this$fromCurrentValue`.decodeBoolean();
   }

   @JvmStatic
   fun YamlInput.`decodeChar$lambda$0`(): Char {
      return `$this$fromCurrentValue`.decodeChar();
   }

   @JvmStatic
   fun `decodeEnum$lambda$0`(`$enumDescriptor`: SerialDescriptor, `$this$fromCurrentValue`: YamlInput): Int {
      return `$this$fromCurrentValue`.decodeEnum(`$enumDescriptor`);
   }

   @JvmStatic
   fun `decodeSerializableValue$lambda$0`(`$deserializer`: DeserializationStrategy, `$this$fromCurrentValue`: YamlInput): Any {
      return `$this$fromCurrentValue`.decodeSerializableValue(`$deserializer`);
   }
}
