package kotlinx.serialization.internal

import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder

@InternalSerializationApi
@SourceDebugExtension(["SMAP\nCollectionSerializers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/MapLikeSerializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 4 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n*L\n1#1,283:1\n1#2:284\n489#3,2:285\n491#3,2:289\n32#4,2:287\n*S KotlinDebug\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/MapLikeSerializer\n*L\n118#1:285,2\n118#1:289,2\n121#1:287,2\n*E\n"])
public sealed class MapLikeSerializer<Key, Value, Collection, Builder extends java.util.Map<Key, Value>> protected constructor(keySerializer: KSerializer<Any>,
   valueSerializer: KSerializer<Any>
) : AbstractCollectionSerializer() {
   public final val keySerializer: KSerializer<Any>
   public final val valueSerializer: KSerializer<Any>
   public abstract val descriptor: SerialDescriptor

   init {
      this.keySerializer = keySerializer;
      this.valueSerializer = valueSerializer;
   }

   protected abstract fun Any.insertKeyValuePair(index: Int, key: Any, value: Any) {
   }

   protected fun readAll(decoder: CompositeDecoder, builder: Any, startIndex: Int, size: Int) {
      if (size < 0) {
         throw new IllegalArgumentException("Size must be known in advance when using READ_ALL".toString());
      } else {
         val var5: IntProgression = RangesKt.step(RangesKt.until(0, size * 2), 2);
         var index: Int = var5.getFirst();
         val var7: Int = var5.getLast();
         val var8: Int = var5.getStep();
         if (var8 > 0 && index <= var7 || var8 < 0 && var7 <= index) {
            while (true) {
               this.readElement(decoder, startIndex + index, (Builder)builder, false);
               if (index == var7) {
                  break;
               }

               index += var8;
            }
         }
      }
   }

   protected fun readElement(decoder: CompositeDecoder, index: Int, builder: Any, checkIndex: Boolean) {
      val key: Any = CompositeDecoder.decodeSerializableElement$default(decoder, this.getDescriptor(), index, this.keySerializer, null, 8, null);
      val var10000: Int;
      if (checkIndex) {
         val value: Int = decoder.decodeElementIndex(this.getDescriptor());
         if (value != index + 1) {
            throw new IllegalArgumentException(("Value must follow key in a map, index for key: $index, returned index for value: $value").toString());
         }

         var10000 = value;
      } else {
         var10000 = index + 1;
      }

      builder.put(
         key,
         if (builder.containsKey(key) && this.valueSerializer.getDescriptor().getKind() !is PrimitiveKind)
            decoder.decodeSerializableElement(this.getDescriptor(), var10000, this.valueSerializer, MapsKt.getValue(builder, key))
            else
            CompositeDecoder.decodeSerializableElement$default(decoder, this.getDescriptor(), var10000, this.valueSerializer, null, 8, null)
      );
   }

   public override fun serialize(encoder: Encoder, value: Any) {
      val size: Int = this.collectionSize((Collection)value);
      val `descriptor$iv`: SerialDescriptor = this.getDescriptor();
      val `composite$iv`: CompositeEncoder = encoder.beginCollection(`descriptor$iv`, size);
      val `$this$serialize_u24lambda_u244`: CompositeEncoder = `composite$iv`;
      val iterator: java.util.Iterator = this.collectionIterator((Collection)value);
      var index: Int = 0;
      val var15: java.util.Iterator = iterator;

      while (var15.hasNext()) {
         val var17: Entry = var15.next() as Entry;
         val v: Any = var17.getValue();
         `$this$serialize_u24lambda_u244`.encodeSerializableElement(this.getDescriptor(), index++, this.getKeySerializer(), (Key)var17.getKey());
         `$this$serialize_u24lambda_u244`.encodeSerializableElement(this.getDescriptor(), index++, this.getValueSerializer(), (Value)v);
      }

      `composite$iv`.endStructure(`descriptor$iv`);
   }
}
