package kotlinx.serialization.encoding

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.modules.SerializersModule

public interface CompositeDecoder {
   public val serializersModule: SerializersModule

   public abstract fun endStructure(descriptor: SerialDescriptor) {
   }

   @ExperimentalSerializationApi
   public open fun decodeSequentially(): Boolean {
      return false;
   }

   public abstract fun decodeElementIndex(descriptor: SerialDescriptor): Int {
   }

   public open fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
      return -1;
   }

   public abstract fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
   }

   public abstract fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
   }

   public abstract fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
   }

   public abstract fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
   }

   public abstract fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
   }

   public abstract fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
   }

   public abstract fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
   }

   public abstract fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
   }

   public abstract fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
   }

   public abstract fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder {
   }

   public abstract fun <T : Any?> decodeSerializableElement(
      descriptor: SerialDescriptor,
      index: Int,
      deserializer: DeserializationStrategy<T>,
      previousValue: T? = null
   ): T {
   }

   @ExperimentalSerializationApi
   public abstract fun <T : Any> decodeNullableSerializableElement(
      descriptor: SerialDescriptor,
      index: Int,
      deserializer: DeserializationStrategy<T?>,
      previousValue: T? = null
   ): T? {
   }

   public companion object {
      public const val DECODE_DONE: Int = -1
      public const val UNKNOWN_NAME: Int = -3
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun decodeSequentially(`$this`: CompositeDecoder): Boolean {
         return CompositeDecoder.access$decodeSequentially$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun decodeCollectionSize(`$this`: CompositeDecoder, descriptor: SerialDescriptor): Int {
         return CompositeDecoder.access$decodeCollectionSize$jd(`$this`, descriptor);
      }
   }
}
