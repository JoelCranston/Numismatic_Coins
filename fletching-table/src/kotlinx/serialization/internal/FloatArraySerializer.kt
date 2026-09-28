package kotlinx.serialization.internal

import kotlin.jvm.internal.FloatCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@PublishedApi
internal object FloatArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(FloatCompanionObject.INSTANCE)), KSerializer<float[]> {
   protected open fun FloatArray.collectionSize(): Int {
      return `$this$collectionSize`.length;
   }

   protected open fun FloatArray.toBuilder(): FloatArrayBuilder {
      return new FloatArrayBuilder(`$this$toBuilder`);
   }

   protected open fun empty(): FloatArray {
      return new float[0];
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: FloatArrayBuilder, checkIndex: Boolean) {
      builder.append$kotlinx_serialization_core(decoder.decodeFloatElement(this.getDescriptor(), index));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: FloatArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeFloatElement(this.getDescriptor(), i, content[i]);
      }
   }
}
