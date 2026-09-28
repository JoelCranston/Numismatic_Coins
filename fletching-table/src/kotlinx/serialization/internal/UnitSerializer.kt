package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object UnitSerializer : KSerializer<Unit> {
   public open val descriptor: SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Unit) {
      this.$$delegate_0.serialize(encoder, value);
   }

   public open fun deserialize(decoder: Decoder) {
      this.$$delegate_0.deserialize(decoder);
   }
}
