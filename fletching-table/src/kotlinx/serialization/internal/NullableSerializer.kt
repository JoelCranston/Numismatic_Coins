package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal class NullableSerializer<T>(serializer: KSerializer<Any>) : KSerializer<T> {
   private final val serializer: KSerializer<Any>
   public open val descriptor: SerialDescriptor

   init {
      this.serializer = serializer;
      this.descriptor = new SerialDescriptorForNullable(this.serializer.getDescriptor());
   }

   public override fun serialize(encoder: Encoder, value: Any?) {
      if (value != null) {
         encoder.encodeNotNullMark();
         encoder.encodeSerializableValue(this.serializer, (T)value);
      } else {
         encoder.encodeNull();
      }
   }

   public override fun deserialize(decoder: Decoder): Any? {
      return (T)(if (decoder.decodeNotNullMark()) decoder.decodeSerializableValue(this.serializer) else decoder.decodeNull());
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other == null || this.getClass() != other.getClass()) {
         return false;
      } else {
         return this.serializer == (other as NullableSerializer).serializer;
      }
   }

   public override fun hashCode(): Int {
      return this.serializer.hashCode();
   }
}
