package kotlinx.serialization.json

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SealedSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder

@SubclassOptInRequired(markerClass = [SealedSerializationApi::class])
public interface JsonEncoder : Encoder, CompositeEncoder {
   public val json: Json

   public abstract fun encodeJsonElement(element: JsonElement) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun encodeNotNullMark(`$this`: JsonEncoder) {
         JsonEncoder.access$encodeNotNullMark$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun beginCollection(`$this`: JsonEncoder, descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
         return JsonEncoder.access$beginCollection$jd(`$this`, descriptor, collectionSize);
      }

      @Deprecated
      @JvmStatic
      fun <T> encodeSerializableValue(`$this`: JsonEncoder, serializer: SerializationStrategy<? super T>, value: T) {
         JsonEncoder.access$encodeSerializableValue$jd(`$this`, serializer, value);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun <T> encodeNullableSerializableValue(`$this`: JsonEncoder, serializer: SerializationStrategy<? super T>, value: T?) {
         JsonEncoder.access$encodeNullableSerializableValue$jd(`$this`, serializer, value);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun shouldEncodeElementDefault(`$this`: JsonEncoder, descriptor: SerialDescriptor, index: Int): Boolean {
         return JsonEncoder.access$shouldEncodeElementDefault$jd(`$this`, descriptor, index);
      }
   }
}
