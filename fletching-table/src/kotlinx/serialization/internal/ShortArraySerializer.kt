package kotlinx.serialization.internal

import kotlin.jvm.internal.ShortCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@PublishedApi
internal object ShortArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(ShortCompanionObject.INSTANCE)), KSerializer<short[]> {
   protected open fun ShortArray.collectionSize(): Int {
      return `$this$collectionSize`.length;
   }

   protected open fun ShortArray.toBuilder(): ShortArrayBuilder {
      return new ShortArrayBuilder(`$this$toBuilder`);
   }

   protected open fun empty(): ShortArray {
      return new short[0];
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: ShortArrayBuilder, checkIndex: Boolean) {
      builder.append$kotlinx_serialization_core(decoder.decodeShortElement(this.getDescriptor(), index));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: ShortArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeShortElement(this.getDescriptor(), i, content[i]);
      }
   }
}
