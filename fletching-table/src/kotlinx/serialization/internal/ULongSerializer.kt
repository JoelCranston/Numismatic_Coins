package kotlinx.serialization.internal

import kotlin.jvm.internal.LongCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object ULongSerializer : KSerializer<ULong> {
   public open val descriptor: SerialDescriptor =
      InlineClassDescriptorKt.InlinePrimitiveDescriptor("kotlin.ULong", BuiltinSerializersKt.serializer(LongCompanionObject.INSTANCE))

   public open fun serialize(encoder: Encoder, value: ULong) {
      encoder.encodeInline(this.getDescriptor()).encodeLong(value);
   }

   public open fun deserialize(decoder: Decoder): ULong {
      return ULong.constructor-impl(decoder.decodeInline(this.getDescriptor()).decodeLong());
   }
}
