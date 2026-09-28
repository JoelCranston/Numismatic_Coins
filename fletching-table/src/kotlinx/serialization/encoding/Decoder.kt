package kotlinx.serialization.encoding

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nDecoding.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Decoding.kt\nkotlinx/serialization/encoding/Decoder\n+ 2 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,576:1\n271#2,2:577\n*S KotlinDebug\n*F\n+ 1 Decoding.kt\nkotlinx/serialization/encoding/Decoder\n*L\n264#1:577,2\n*E\n"])
public interface Decoder {
   public val serializersModule: SerializersModule

   @ExperimentalSerializationApi
   public abstract fun decodeNotNullMark(): Boolean {
   }

   @ExperimentalSerializationApi
   public abstract fun decodeNull(): Nothing? {
   }

   public abstract fun decodeBoolean(): Boolean {
   }

   public abstract fun decodeByte(): Byte {
   }

   public abstract fun decodeShort(): Short {
   }

   public abstract fun decodeChar(): Char {
   }

   public abstract fun decodeInt(): Int {
   }

   public abstract fun decodeLong(): Long {
   }

   public abstract fun decodeFloat(): Float {
   }

   public abstract fun decodeDouble(): Double {
   }

   public abstract fun decodeString(): String {
   }

   public abstract fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
   }

   public abstract fun decodeInline(descriptor: SerialDescriptor): Decoder {
   }

   public abstract fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
   }

   public open fun <T : Any?> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
      return (T)deserializer.deserialize(this);
   }

   @ExperimentalSerializationApi
   public open fun <T : Any> decodeNullableSerializableValue(deserializer: DeserializationStrategy<T?>): T? {
      return (T)(if (!deserializer.getDescriptor().isNullable() && !this.decodeNotNullMark()) this.decodeNull() else this.decodeSerializableValue(deserializer));
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun <T> decodeSerializableValue(`$this`: Decoder, deserializer: DeserializationStrategy<? extends T>): T {
         return (T)Decoder.access$decodeSerializableValue$jd(`$this`, deserializer);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun <T> decodeNullableSerializableValue(`$this`: Decoder, deserializer: DeserializationStrategy<? extends T>): T {
         return (T)Decoder.access$decodeNullableSerializableValue$jd(`$this`, deserializer);
      }
   }
}
