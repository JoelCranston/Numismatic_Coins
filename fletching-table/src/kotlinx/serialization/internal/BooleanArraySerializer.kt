package kotlinx.serialization.internal

import kotlin.jvm.internal.BooleanCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@PublishedApi
internal object BooleanArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(BooleanCompanionObject.INSTANCE)), KSerializer<boolean[]> {
   protected open fun BooleanArray.collectionSize(): Int {
      return `$this$collectionSize`.length;
   }

   protected open fun BooleanArray.toBuilder(): BooleanArrayBuilder {
      return new BooleanArrayBuilder(`$this$toBuilder`);
   }

   protected open fun empty(): BooleanArray {
      return new boolean[0];
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: BooleanArrayBuilder, checkIndex: Boolean) {
      builder.append$kotlinx_serialization_core(decoder.decodeBooleanElement(this.getDescriptor(), index));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: BooleanArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeBooleanElement(this.getDescriptor(), i, content[i]);
      }
   }
}
