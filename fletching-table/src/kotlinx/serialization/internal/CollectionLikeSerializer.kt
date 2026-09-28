package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
@SourceDebugExtension(["SMAP\nCollectionSerializers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/CollectionLikeSerializer\n+ 2 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,283:1\n489#2,4:284\n1#3:288\n*S KotlinDebug\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/CollectionLikeSerializer\n*L\n66#1:284,4\n*E\n"])
internal sealed class CollectionLikeSerializer<Element, Collection, Builder> protected constructor(elementSerializer: KSerializer<Any>) : AbstractCollectionSerializer() {
   private final val elementSerializer: KSerializer<Any>
   public abstract val descriptor: SerialDescriptor

   init {
      this.elementSerializer = elementSerializer;
   }

   protected abstract fun Any.insert(index: Int, element: Any) {
   }

   public override fun serialize(encoder: Encoder, value: Any) {
      val size: Int = this.collectionSize((Collection)value);
      val `descriptor$iv`: SerialDescriptor = this.getDescriptor();
      val `composite$iv`: CompositeEncoder = encoder.beginCollection(`descriptor$iv`, size);
      val `$this$serialize_u24lambda_u240`: CompositeEncoder = `composite$iv`;
      val iterator: java.util.Iterator = this.collectionIterator((Collection)value);

      for (int index = 0; index < size; index++) {
         `$this$serialize_u24lambda_u240`.encodeSerializableElement(this.getDescriptor(), index, access$getElementSerializer$p(this), iterator.next());
      }

      `composite$iv`.endStructure(`descriptor$iv`);
   }

   protected override fun readAll(decoder: CompositeDecoder, builder: Any, startIndex: Int, size: Int) {
      if (size < 0) {
         throw new IllegalArgumentException("Size must be known in advance when using READ_ALL".toString());
      } else {
         for (int index = 0; index < size; index++) {
            this.readElement(decoder, startIndex + index, (Builder)builder, false);
         }
      }
   }

   protected override fun readElement(decoder: CompositeDecoder, index: Int, builder: Any, checkIndex: Boolean) {
      this.insert(
         (Builder)builder,
         index,
         (Element)CompositeDecoder.decodeSerializableElement$default(decoder, this.getDescriptor(), index, this.elementSerializer, null, 8, null)
      );
   }
}
