package kotlinx.serialization.internal

import kotlin.jvm.internal.ShortCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object UShortSerializer : KSerializer<UShort> {
   public open val descriptor: SerialDescriptor =
      InlineClassDescriptorKt.InlinePrimitiveDescriptor("kotlin.UShort", BuiltinSerializersKt.serializer(ShortCompanionObject.INSTANCE))

   public open fun serialize(encoder: Encoder, value: UShort) {
      encoder.encodeInline(this.getDescriptor()).encodeShort(value);
   }

   public open fun deserialize(decoder: Decoder): UShort {
      return UShort.constructor-impl(decoder.decodeInline(this.getDescriptor()).decodeShort());
   }
}
