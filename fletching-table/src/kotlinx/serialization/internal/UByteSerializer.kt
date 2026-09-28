package kotlinx.serialization.internal

import kotlin.jvm.internal.ByteCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object UByteSerializer : KSerializer<UByte> {
   public open val descriptor: SerialDescriptor =
      InlineClassDescriptorKt.InlinePrimitiveDescriptor("kotlin.UByte", BuiltinSerializersKt.serializer(ByteCompanionObject.INSTANCE))

   public open fun serialize(encoder: Encoder, value: UByte) {
      encoder.encodeInline(this.getDescriptor()).encodeByte(value);
   }

   public open fun deserialize(decoder: Decoder): UByte {
      return UByte.constructor-impl(decoder.decodeInline(this.getDescriptor()).decodeByte());
   }
}
