package kotlinx.serialization.encoding

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor

@ExperimentalSerializationApi
@SourceDebugExtension(["SMAP\nAbstractDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractDecoder.kt\nkotlinx/serialization/encoding/AbstractDecoder\n+ 2 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,81:1\n271#2,2:82\n*S KotlinDebug\n*F\n+ 1 AbstractDecoder.kt\nkotlinx/serialization/encoding/AbstractDecoder\n*L\n77#1:82,2\n*E\n"])
public abstract class AbstractDecoder : Decoder, CompositeDecoder {
   public open fun decodeValue(): Any {
      throw new SerializationException("${this.getClass()::class} can't retrieve untyped values");
   }

   public override fun decodeNotNullMark(): Boolean {
      return true;
   }

   public override fun decodeNull(): Nothing? {
      return null;
   }

   public override fun decodeBoolean(): Boolean {
      val var10000: Any = this.decodeValue();
      return var10000 as java.lang.Boolean;
   }

   public override fun decodeByte(): Byte {
      val var10000: Any = this.decodeValue();
      return var10000 as java.lang.Byte;
   }

   public override fun decodeShort(): Short {
      val var10000: Any = this.decodeValue();
      return var10000 as java.lang.Short;
   }

   public override fun decodeInt(): Int {
      val var10000: Any = this.decodeValue();
      return var10000 as Int;
   }

   public override fun decodeLong(): Long {
      val var10000: Any = this.decodeValue();
      return var10000 as java.lang.Long;
   }

   public override fun decodeFloat(): Float {
      val var10000: Any = this.decodeValue();
      return var10000 as java.lang.Float;
   }

   public override fun decodeDouble(): Double {
      val var10000: Any = this.decodeValue();
      return var10000 as java.lang.Double;
   }

   public override fun decodeChar(): Char {
      val var10000: Any = this.decodeValue();
      return var10000 as Character;
   }

   public override fun decodeString(): String {
      val var10000: Any = this.decodeValue();
      return var10000 as java.lang.String;
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      val var10000: Any = this.decodeValue();
      return var10000 as Int;
   }

   public override fun decodeInline(descriptor: SerialDescriptor): Decoder {
      return this;
   }

   public open fun <T : Any?> decodeSerializableValue(deserializer: DeserializationStrategy<T>, previousValue: T? = null): T {
      return (T)this.decodeSerializableValue(deserializer);
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      return this;
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
   }

   public override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
      return this.decodeBoolean();
   }

   public override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
      return this.decodeByte();
   }

   public override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
      return this.decodeShort();
   }

   public override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
      return this.decodeInt();
   }

   public override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
      return this.decodeLong();
   }

   public override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
      return this.decodeFloat();
   }

   public override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
      return this.decodeDouble();
   }

   public override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
      return this.decodeChar();
   }

   public override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
      return this.decodeString();
   }

   public override fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder {
      return this.decodeInline(descriptor.getElementDescriptor(index));
   }

   public override fun <T> decodeSerializableElement(descriptor: SerialDescriptor, index: Int, deserializer: DeserializationStrategy<T>, previousValue: T?): T {
      return (T)this.decodeSerializableValue(deserializer, previousValue);
   }

   public override fun <T : Any> decodeNullableSerializableElement(
      descriptor: SerialDescriptor,
      index: Int,
      deserializer: DeserializationStrategy<T?>,
      previousValue: T?
   ): T? {
      return (T)(if (!deserializer.getDescriptor().isNullable() && !this.decodeNotNullMark())
         this.decodeNull()
         else
         this.decodeSerializableValue(deserializer, previousValue));
   }

   override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<? extends T>): T {
      return Decoder.super.decodeSerializableValue(deserializer);
   }

   @ExperimentalSerializationApi
   override fun <T> decodeNullableSerializableValue(deserializer: DeserializationStrategy<? extends T>): T {
      return Decoder.super.decodeNullableSerializableValue(deserializer);
   }

   @ExperimentalSerializationApi
   override fun decodeSequentially(): Boolean {
      return CompositeDecoder.super.decodeSequentially();
   }

   override fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
      return CompositeDecoder.super.decodeCollectionSize(descriptor);
   }
}
