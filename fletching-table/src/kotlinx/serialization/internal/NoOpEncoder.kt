package kotlinx.serialization.internal

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuildersKt

internal object NoOpEncoder : AbstractEncoder {
   public open val serializersModule: SerializersModule = SerializersModuleBuildersKt.EmptySerializersModule()

   public override fun encodeValue(value: Any) {
   }

   public override fun encodeNull() {
   }

   public override fun encodeBoolean(value: Boolean) {
   }

   public override fun encodeByte(value: Byte) {
   }

   public override fun encodeShort(value: Short) {
   }

   public override fun encodeInt(value: Int) {
   }

   public override fun encodeLong(value: Long) {
   }

   public override fun encodeFloat(value: Float) {
   }

   public override fun encodeDouble(value: Double) {
   }

   public override fun encodeChar(value: Char) {
   }

   public override fun encodeString(value: String) {
   }

   public override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
   }
}
