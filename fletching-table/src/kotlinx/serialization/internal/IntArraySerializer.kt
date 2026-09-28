package kotlinx.serialization.internal

import kotlin.jvm.internal.IntCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@PublishedApi
internal object IntArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(IntCompanionObject.INSTANCE)), KSerializer<int[]> {
   protected open fun IntArray.collectionSize(): Int {
      return `$this$collectionSize`.length;
   }

   protected open fun IntArray.toBuilder(): IntArrayBuilder {
      return new IntArrayBuilder(`$this$toBuilder`);
   }

   protected open fun empty(): IntArray {
      return new int[0];
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: IntArrayBuilder, checkIndex: Boolean) {
      builder.append$kotlinx_serialization_core(decoder.decodeIntElement(this.getDescriptor(), index));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: IntArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeIntElement(this.getDescriptor(), i, content[i]);
      }
   }
}
