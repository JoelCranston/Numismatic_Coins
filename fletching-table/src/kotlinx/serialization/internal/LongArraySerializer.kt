package kotlinx.serialization.internal

import kotlin.jvm.internal.LongCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@PublishedApi
internal object LongArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(LongCompanionObject.INSTANCE)), KSerializer<long[]> {
   protected open fun LongArray.collectionSize(): Int {
      return `$this$collectionSize`.length;
   }

   protected open fun LongArray.toBuilder(): LongArrayBuilder {
      return new LongArrayBuilder(`$this$toBuilder`);
   }

   protected open fun empty(): LongArray {
      return new long[0];
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: LongArrayBuilder, checkIndex: Boolean) {
      builder.append$kotlinx_serialization_core(decoder.decodeLongElement(this.getDescriptor(), index));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: LongArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeLongElement(this.getDescriptor(), i, content[i]);
      }
   }
}
