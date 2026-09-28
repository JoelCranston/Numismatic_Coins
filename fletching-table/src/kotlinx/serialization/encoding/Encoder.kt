package kotlinx.serialization.encoding

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.modules.SerializersModule

public interface Encoder {
   public val serializersModule: SerializersModule

   @ExperimentalSerializationApi
   public open fun encodeNotNullMark() {
   }

   @ExperimentalSerializationApi
   public abstract fun encodeNull() {
   }

   public abstract fun encodeBoolean(value: Boolean) {
   }

   public abstract fun encodeByte(value: Byte) {
   }

   public abstract fun encodeShort(value: Short) {
   }

   public abstract fun encodeChar(value: Char) {
   }

   public abstract fun encodeInt(value: Int) {
   }

   public abstract fun encodeLong(value: Long) {
   }

   public abstract fun encodeFloat(value: Float) {
   }

   public abstract fun encodeDouble(value: Double) {
   }

   public abstract fun encodeString(value: String) {
   }

   public abstract fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
   }

   public abstract fun encodeInline(descriptor: SerialDescriptor): Encoder {
   }

   public abstract fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
   }

   public open fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
      return this.beginStructure(descriptor);
   }

   public open fun <T : Any?> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
      serializer.serialize(this, value);
   }

   @ExperimentalSerializationApi
   public open fun <T : Any> encodeNullableSerializableValue(serializer: SerializationStrategy<T>, value: T?) {
      if (serializer.getDescriptor().isNullable()) {
         this.encodeSerializableValue(serializer, value);
      } else {
         if (value == null) {
            this.encodeNull();
         } else {
            this.encodeNotNullMark();
            this.encodeSerializableValue(serializer, value);
         }
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun encodeNotNullMark(`$this`: Encoder) {
         Encoder.access$encodeNotNullMark$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun beginCollection(`$this`: Encoder, descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
         return Encoder.access$beginCollection$jd(`$this`, descriptor, collectionSize);
      }

      @Deprecated
      @JvmStatic
      fun <T> encodeSerializableValue(`$this`: Encoder, serializer: SerializationStrategy<? super T>, value: T) {
         Encoder.access$encodeSerializableValue$jd(`$this`, serializer, value);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun <T> encodeNullableSerializableValue(`$this`: Encoder, serializer: SerializationStrategy<? super T>, value: T?) {
         Encoder.access$encodeNullableSerializableValue$jd(`$this`, serializer, value);
      }
   }
}
