package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
@SourceDebugExtension(["SMAP\nCollectionSerializers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/PrimitiveArraySerializer\n+ 2 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n*L\n1#1,283:1\n489#2,4:284\n*S KotlinDebug\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/PrimitiveArraySerializer\n*L\n174#1:284,4\n*E\n"])
internal abstract class PrimitiveArraySerializer<Element, Array, Builder extends PrimitiveArrayBuilder<Array>>
   : CollectionLikeSerializer<Element, Array, Builder> {
   public final val descriptor: SerialDescriptor

   open fun PrimitiveArraySerializer(primitiveSerializer: KSerializer<Element>) {
      super(primitiveSerializer, null);
      this.descriptor = new PrimitiveArrayDescriptor(primitiveSerializer.getDescriptor());
   }

   protected fun Any.builderSize(): Int {
      return `$this$builderSize`.getPosition$kotlinx_serialization_core();
   }

   protected fun Any.toResult(): Any {
      return (Array)`$this$toResult`.build$kotlinx_serialization_core();
   }

   protected fun Any.checkCapacity(size: Int) {
      `$this$checkCapacity`.ensureCapacity$kotlinx_serialization_core(size);
   }

   protected override fun Any.collectionIterator(): Iterator<Any> {
      throw new IllegalStateException("This method lead to boxing and must not be used, use writeContents instead".toString());
   }

   protected fun Any.insert(index: Int, element: Any) {
      throw new IllegalStateException("This method lead to boxing and must not be used, use Builder.append instead".toString());
   }

   protected fun builder(): Any {
      return this.toBuilder(this.empty());
   }

   protected abstract fun empty(): Any {
   }

   protected abstract fun readElement(decoder: CompositeDecoder, index: Int, builder: Any, checkIndex: Boolean) {
   }

   protected abstract fun writeContent(encoder: CompositeEncoder, content: Any, size: Int) {
   }

   public override fun serialize(encoder: Encoder, value: Any) {
      val size: Int = this.collectionSize((Array)value);
      val `descriptor$iv`: SerialDescriptor = this.descriptor;
      val `composite$iv`: CompositeEncoder = encoder.beginCollection(this.descriptor, size);
      this.writeContent(`composite$iv`, (Array)value, size);
      `composite$iv`.endStructure(`descriptor$iv`);
   }

   public override fun deserialize(decoder: Decoder): Any {
      return this.merge(decoder, null);
   }
}
