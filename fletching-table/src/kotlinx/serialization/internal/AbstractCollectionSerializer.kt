package kotlinx.serialization.internal

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@InternalSerializationApi
public sealed class AbstractCollectionSerializer<Element, Collection, Builder> protected constructor() : KSerializer<Collection> {
   protected abstract fun Any.collectionSize(): Int {
   }

   protected abstract fun Any.collectionIterator(): Iterator<Any> {
   }

   protected abstract fun builder(): Any {
   }

   protected abstract fun Any.builderSize(): Int {
   }

   protected abstract fun Any.toResult(): Any {
   }

   protected abstract fun Any.toBuilder(): Any {
   }

   protected abstract fun Any.checkCapacity(size: Int) {
   }

   public abstract override fun serialize(encoder: Encoder, value: Any) {
   }

   @InternalSerializationApi
   public fun merge(decoder: Decoder, previous: Any?): Any {
      var var10000: Any;
      label22: {
         if (previous != null) {
            var10000 = this.toBuilder((Collection)previous);
            if (var10000 != null) {
               break label22;
            }
         }

         var10000 = this.builder();
      }

      val builder: Any = var10000;
      val startIndex: Int = this.builderSize((Builder)var10000);
      val compositeDecoder: CompositeDecoder = decoder.beginStructure(this.getDescriptor());
      if (compositeDecoder.decodeSequentially()) {
         this.readAll(compositeDecoder, (Builder)var10000, startIndex, this.readSize(compositeDecoder, (Builder)var10000));
      } else {
         while (true) {
            val index: Int = compositeDecoder.decodeElementIndex(this.getDescriptor());
            if (index == -1) {
               break;
            }

            readElement$default(this, compositeDecoder, startIndex + index, builder, false, 8, null);
         }
      }

      compositeDecoder.endStructure(this.getDescriptor());
      return this.toResult((Builder)builder);
   }

   public override fun deserialize(decoder: Decoder): Any {
      return this.merge(decoder, null);
   }

   private fun readSize(decoder: CompositeDecoder, builder: Any): Int {
      val size: Int = decoder.decodeCollectionSize(this.getDescriptor());
      this.checkCapacity((Builder)builder, size);
      return size;
   }

   protected abstract fun readElement(decoder: CompositeDecoder, index: Int, builder: Any, checkIndex: Boolean = true) {
   }

   protected abstract fun readAll(decoder: CompositeDecoder, builder: Any, startIndex: Int, size: Int) {
   }
}
