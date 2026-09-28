package kotlinx.serialization.encoding

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.modules.SerializersModule

public interface CompositeEncoder {
   public val serializersModule: SerializersModule

   public abstract fun endStructure(descriptor: SerialDescriptor) {
   }

   @ExperimentalSerializationApi
   public open fun shouldEncodeElementDefault(descriptor: SerialDescriptor, index: Int): Boolean {
      return true;
   }

   public abstract fun encodeBooleanElement(descriptor: SerialDescriptor, index: Int, value: Boolean) {
   }

   public abstract fun encodeByteElement(descriptor: SerialDescriptor, index: Int, value: Byte) {
   }

   public abstract fun encodeShortElement(descriptor: SerialDescriptor, index: Int, value: Short) {
   }

   public abstract fun encodeCharElement(descriptor: SerialDescriptor, index: Int, value: Char) {
   }

   public abstract fun encodeIntElement(descriptor: SerialDescriptor, index: Int, value: Int) {
   }

   public abstract fun encodeLongElement(descriptor: SerialDescriptor, index: Int, value: Long) {
   }

   public abstract fun encodeFloatElement(descriptor: SerialDescriptor, index: Int, value: Float) {
   }

   public abstract fun encodeDoubleElement(descriptor: SerialDescriptor, index: Int, value: Double) {
   }

   public abstract fun encodeStringElement(descriptor: SerialDescriptor, index: Int, value: String) {
   }

   public abstract fun encodeInlineElement(descriptor: SerialDescriptor, index: Int): Encoder {
   }

   public abstract fun <T : Any?> encodeSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<T>, value: T) {
   }

   @ExperimentalSerializationApi
   public abstract fun <T : Any> encodeNullableSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<T>, value: T?) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun shouldEncodeElementDefault(`$this`: CompositeEncoder, descriptor: SerialDescriptor, index: Int): Boolean {
         return CompositeEncoder.access$shouldEncodeElementDefault$jd(`$this`, descriptor, index);
      }
   }
}
