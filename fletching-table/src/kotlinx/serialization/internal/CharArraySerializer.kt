package kotlinx.serialization.internal

import kotlin.jvm.internal.CharCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@PublishedApi
internal object CharArraySerializer : PrimitiveArraySerializer(BuiltinSerializersKt.serializer(CharCompanionObject.INSTANCE)), KSerializer<char[]> {
   protected open fun CharArray.collectionSize(): Int {
      return `$this$collectionSize`.length;
   }

   protected open fun CharArray.toBuilder(): CharArrayBuilder {
      return new CharArrayBuilder(`$this$toBuilder`);
   }

   protected open fun empty(): CharArray {
      return new char[0];
   }

   protected open fun readElement(decoder: CompositeDecoder, index: Int, builder: CharArrayBuilder, checkIndex: Boolean) {
      builder.append$kotlinx_serialization_core(decoder.decodeCharElement(this.getDescriptor(), index));
   }

   protected open fun writeContent(encoder: CompositeEncoder, content: CharArray, size: Int) {
      for (int i = 0; i < size; i++) {
         encoder.encodeCharElement(this.getDescriptor(), i, content[i]);
      }
   }
}
