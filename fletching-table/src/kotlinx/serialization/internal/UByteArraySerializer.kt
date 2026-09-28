package kotlinx.serialization.internal

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@ExperimentalSerializationApi
@PublishedApi
@ExperimentalUnsignedTypes
internal object UByteArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(UByte.Companion)), KSerializer<UByteArray> {
   protected open fun UByteArray.collectionSize(): Int {
      return UByteArray.getSize-impl(`$this$collectionSize_u2dGBYM_sE`);
   }

   protected open fun UByteArray.toBuilder(): UByteArrayBuilder {
      return new UByteArrayBuilder(`$this$toBuilder_u2dGBYM_sE`, null);
   }

   protected open fun empty(): UByteArray {
      return UByteArray.constructor-impl(0);
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: UByteArrayBuilder, checkIndex: Boolean) {
      builder.append-7apg3OU$kotlinx_serialization_core(UByte.constructor-impl(decoder.decodeInlineElement(this.getDescriptor(), index).decodeByte()));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: UByteArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeInlineElement(this.getDescriptor(), i).encodeByte(UByteArray.get-w2LRezQ(content, i));
      }
   }
}
