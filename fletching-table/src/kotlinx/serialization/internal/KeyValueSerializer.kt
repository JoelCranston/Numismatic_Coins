package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
@SourceDebugExtension(["SMAP\nTuples.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Tuples.kt\nkotlinx/serialization/internal/KeyValueSerializer\n+ 2 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,168:1\n571#2,4:169\n*S KotlinDebug\n*F\n+ 1 Tuples.kt\nkotlinx/serialization/internal/KeyValueSerializer\n*L\n35#1:169,4\n*E\n"])
internal sealed class KeyValueSerializer<K, V, R> protected constructor(keySerializer: KSerializer<Any>, valueSerializer: KSerializer<Any>) : KSerializer<R> {
   protected final val keySerializer: KSerializer<Any>
   protected final val valueSerializer: KSerializer<Any>
   protected abstract val key: Any
   protected abstract val value: Any

   init {
      this.keySerializer = keySerializer;
      this.valueSerializer = valueSerializer;
   }

   protected abstract fun toResult(key: Any, value: Any): Any {
   }

   public override fun serialize(encoder: Encoder, value: Any) {
      val structuredEncoder: CompositeEncoder = encoder.beginStructure(this.getDescriptor());
      structuredEncoder.encodeSerializableElement(this.getDescriptor(), 0, this.keySerializer, this.getKey((R)value));
      structuredEncoder.encodeSerializableElement(this.getDescriptor(), 1, this.valueSerializer, this.getValue((R)value));
      structuredEncoder.endStructure(this.getDescriptor());
   }

   public override fun deserialize(decoder: Decoder): Any {
      val `descriptor$iv`: SerialDescriptor = this.getDescriptor();
      val `composite$iv`: CompositeDecoder = decoder.beginStructure(`descriptor$iv`);
      val `result$iv`: CompositeDecoder = `composite$iv`;
      val var10000: Any;
      if (`composite$iv`.decodeSequentially()) {
         var10000 = this.toResult(
            (K)CompositeDecoder.decodeSerializableElement$default(`composite$iv`, this.getDescriptor(), 0, this.getKeySerializer(), null, 8, null),
            (V)CompositeDecoder.decodeSerializableElement$default(`composite$iv`, this.getDescriptor(), 1, this.getValueSerializer(), null, 8, null)
         );
      } else {
         var key: Any = TuplesKt.access$getNULL$p();
         var value: Any = TuplesKt.access$getNULL$p();

         label29:
         while (true) {
            val idx: Int = `result$iv`.decodeElementIndex(this.getDescriptor());
            switch (idx) {
               case -1:
                  if (key === TuplesKt.access$getNULL$p()) {
                     throw new SerializationException("Element 'key' is missing");
                  }

                  if (value === TuplesKt.access$getNULL$p()) {
                     throw new SerializationException("Element 'value' is missing");
                  }

                  var10000 = this.toResult((K)key, (V)value);
                  break label29;
               case 0:
                  key = CompositeDecoder.decodeSerializableElement$default(`result$iv`, this.getDescriptor(), 0, this.getKeySerializer(), null, 8, null);
                  break;
               case 1:
                  value = CompositeDecoder.decodeSerializableElement$default(`result$iv`, this.getDescriptor(), 1, this.getValueSerializer(), null, 8, null);
                  break;
               default:
                  throw new SerializationException("Invalid index: $idx");
            }
         }
      }

      `composite$iv`.endStructure(`descriptor$iv`);
      return (R)var10000;
   }
}
