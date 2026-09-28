package kotlinx.serialization.internal

import kotlin.jvm.internal.IntCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object UIntSerializer : KSerializer<UInt> {
   public open val descriptor: SerialDescriptor =
      InlineClassDescriptorKt.InlinePrimitiveDescriptor("kotlin.UInt", BuiltinSerializersKt.serializer(IntCompanionObject.INSTANCE))

   public open fun serialize(encoder: Encoder, value: UInt) {
      encoder.encodeInline(this.getDescriptor()).encodeInt(value);
   }

   public open fun deserialize(decoder: Decoder): UInt {
      return UInt.constructor-impl(decoder.decodeInline(this.getDescriptor()).decodeInt());
   }
}
