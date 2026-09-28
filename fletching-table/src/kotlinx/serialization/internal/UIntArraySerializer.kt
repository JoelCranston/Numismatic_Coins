package kotlinx.serialization.internal

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@ExperimentalSerializationApi
@PublishedApi
@ExperimentalUnsignedTypes
internal object UIntArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(UInt.Companion)), KSerializer<UIntArray> {
   protected open fun UIntArray.collectionSize(): Int {
      return UIntArray.getSize-impl(`$this$collectionSize_u2d_u2dajY_u2d9A`);
   }

   protected open fun UIntArray.toBuilder(): UIntArrayBuilder {
      return new UIntArrayBuilder(`$this$toBuilder_u2d_u2dajY_u2d9A`, null);
   }

   protected open fun empty(): UIntArray {
      return UIntArray.constructor-impl(0);
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: UIntArrayBuilder, checkIndex: Boolean) {
      builder.append-WZ4Q5Ns$kotlinx_serialization_core(UInt.constructor-impl(decoder.decodeInlineElement(this.getDescriptor(), index).decodeInt()));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: UIntArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeInlineElement(this.getDescriptor(), i).encodeInt(UIntArray.get-pVg5ArA(content, i));
      }
   }
}
