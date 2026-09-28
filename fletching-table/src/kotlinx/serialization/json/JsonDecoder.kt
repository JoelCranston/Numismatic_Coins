package kotlinx.serialization.json

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SealedSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder

@SubclassOptInRequired(markerClass = [SealedSerializationApi::class])
public interface JsonDecoder : Decoder, CompositeDecoder {
   public val json: Json

   public abstract fun decodeJsonElement(): JsonElement {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun <T> decodeSerializableValue(`$this`: JsonDecoder, deserializer: DeserializationStrategy<? extends T>): T {
         return (T)JsonDecoder.access$decodeSerializableValue$jd(`$this`, deserializer);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun <T> decodeNullableSerializableValue(`$this`: JsonDecoder, deserializer: DeserializationStrategy<? extends T>): T {
         return (T)JsonDecoder.access$decodeNullableSerializableValue$jd(`$this`, deserializer);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun decodeSequentially(`$this`: JsonDecoder): Boolean {
         return JsonDecoder.access$decodeSequentially$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun decodeCollectionSize(`$this`: JsonDecoder, descriptor: SerialDescriptor): Int {
         return JsonDecoder.access$decodeCollectionSize$jd(`$this`, descriptor);
      }
   }
}
