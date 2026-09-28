package kotlinx.serialization.internal

import kotlin.jvm.internal.ByteCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@PublishedApi
internal object ByteArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(ByteCompanionObject.INSTANCE)), KSerializer<byte[]> {
   protected open fun ByteArray.collectionSize(): Int {
      return `$this$collectionSize`.length;
   }

   protected open fun ByteArray.toBuilder(): ByteArrayBuilder {
      return new ByteArrayBuilder(`$this$toBuilder`);
   }

   protected open fun empty(): ByteArray {
      return new byte[0];
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: ByteArrayBuilder, checkIndex: Boolean) {
      builder.append$kotlinx_serialization_core(decoder.decodeByteElement(this.getDescriptor(), index));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: ByteArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeByteElement(this.getDescriptor(), i, content[i]);
      }
   }
}
