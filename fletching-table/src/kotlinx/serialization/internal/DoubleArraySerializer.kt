package kotlinx.serialization.internal

import kotlin.jvm.internal.DoubleCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@PublishedApi
internal object DoubleArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(DoubleCompanionObject.INSTANCE)), KSerializer<double[]> {
   protected open fun DoubleArray.collectionSize(): Int {
      return `$this$collectionSize`.length;
   }

   protected open fun DoubleArray.toBuilder(): DoubleArrayBuilder {
      return new DoubleArrayBuilder(`$this$toBuilder`);
   }

   protected open fun empty(): DoubleArray {
      return new double[0];
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: DoubleArrayBuilder, checkIndex: Boolean) {
      builder.append$kotlinx_serialization_core(decoder.decodeDoubleElement(this.getDescriptor(), index));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: DoubleArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeDoubleElement(this.getDescriptor(), i, content[i]);
      }
   }
}
