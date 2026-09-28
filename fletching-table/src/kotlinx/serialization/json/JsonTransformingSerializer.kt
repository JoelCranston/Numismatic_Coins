package kotlinx.serialization.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.internal.TreeJsonEncoderKt

public abstract class JsonTransformingSerializer<T> : KSerializer<T> {
   private final val tSerializer: KSerializer<Any>

   public open val descriptor: SerialDescriptor
      public open get() {
         return this.tSerializer.getDescriptor();
      }


   open fun JsonTransformingSerializer(tSerializer: KSerializer<T>) {
      this.tSerializer = tSerializer;
   }

   public override fun serialize(encoder: Encoder, value: Any) {
      val output: JsonEncoder = JsonElementSerializersKt.asJsonEncoder(encoder);
      output.encodeJsonElement(this.transformSerialize(TreeJsonEncoderKt.writeJson(output.getJson(), value, this.tSerializer)));
   }

   public override fun deserialize(decoder: Decoder): Any {
      val input: JsonDecoder = JsonElementSerializersKt.asJsonDecoder(decoder);
      return input.getJson().decodeFromJsonElement(this.tSerializer, this.transformDeserialize(input.decodeJsonElement()));
   }

   protected open fun transformDeserialize(element: JsonElement): JsonElement {
      return element;
   }

   protected open fun transformSerialize(element: JsonElement): JsonElement {
      return element;
   }
}
