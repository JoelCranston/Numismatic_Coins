package kotlinx.serialization.internal

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@ExperimentalSerializationApi
@PublishedApi
@ExperimentalUnsignedTypes
internal object ULongArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(ULong.Companion)), KSerializer<ULongArray> {
   protected open fun ULongArray.collectionSize(): Int {
      return ULongArray.getSize-impl(`$this$collectionSize_u2dQwZRm1k`);
   }

   protected open fun ULongArray.toBuilder(): ULongArrayBuilder {
      return new ULongArrayBuilder(`$this$toBuilder_u2dQwZRm1k`, null);
   }

   protected open fun empty(): ULongArray {
      return ULongArray.constructor-impl(0);
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: ULongArrayBuilder, checkIndex: Boolean) {
      builder.append-VKZWuLQ$kotlinx_serialization_core(ULong.constructor-impl(decoder.decodeInlineElement(this.getDescriptor(), index).decodeLong()));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: ULongArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeInlineElement(this.getDescriptor(), i).encodeLong(ULongArray.get-s-VKNKU(content, i));
      }
   }
}
