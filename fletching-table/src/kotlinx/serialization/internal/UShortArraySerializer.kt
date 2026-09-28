package kotlinx.serialization.internal

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@ExperimentalSerializationApi
@PublishedApi
@ExperimentalUnsignedTypes
internal object UShortArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(UShort.Companion)), KSerializer<UShortArray> {
   protected open fun UShortArray.collectionSize(): Int {
      return UShortArray.getSize-impl(`$this$collectionSize_u2drL5Bavg`);
   }

   protected open fun UShortArray.toBuilder(): UShortArrayBuilder {
      return new UShortArrayBuilder(`$this$toBuilder_u2drL5Bavg`, null);
   }

   protected open fun empty(): UShortArray {
      return UShortArray.constructor-impl(0);
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: UShortArrayBuilder, checkIndex: Boolean) {
      builder.append-xj2QHRw$kotlinx_serialization_core(UShort.constructor-impl(decoder.decodeInlineElement(this.getDescriptor(), index).decodeShort()));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: UShortArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeInlineElement(this.getDescriptor(), i).encodeShort(UShortArray.get-Mh2AYeg(content, i));
      }
   }
}
