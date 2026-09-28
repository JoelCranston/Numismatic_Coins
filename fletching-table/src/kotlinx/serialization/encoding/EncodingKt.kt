@file:SourceDebugExtension(["SMAP\nEncoding.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,508:1\n489#1,2:509\n491#1,2:514\n1878#2,3:511\n*S KotlinDebug\n*F\n+ 1 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n*L\n502#1:509,2\n502#1:514,2\n503#1:511,3\n*E\n"])

package kotlinx.serialization.encoding

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor

public inline fun Encoder.encodeStructure(descriptor: SerialDescriptor, crossinline block: (CompositeEncoder) -> Unit) {
   val composite: CompositeEncoder = `$this$encodeStructure`.beginStructure(descriptor);
   block.invoke(composite);
   composite.endStructure(descriptor);
}

public inline fun Encoder.encodeCollection(descriptor: SerialDescriptor, collectionSize: Int, crossinline block: (CompositeEncoder) -> Unit) {
   val composite: CompositeEncoder = `$this$encodeCollection`.beginCollection(descriptor, collectionSize);
   block.invoke(composite);
   composite.endStructure(descriptor);
}

public inline fun <E> Encoder.encodeCollection(descriptor: SerialDescriptor, collection: Collection<E>, crossinline block: (CompositeEncoder, Int, E) -> Unit) {
   val `composite$iv`: CompositeEncoder = `$this$encodeCollection`.beginCollection(descriptor, collection.size());
   val `$this$encodeCollection_u24lambda_u241`: CompositeEncoder = `composite$iv`;
   val `$this$forEachIndexed$iv`: java.lang.Iterable = collection;
   var `index$iv`: Int = 0;

   for (Object item$iv : $this$forEachIndexed$iv) {
      val var17: Int = `index$iv`++;
      if (var17 < 0) {
         CollectionsKt.throwIndexOverflow();
      }

      block.invoke(`$this$encodeCollection_u24lambda_u241`, var17, `item$iv`);
   }

   `composite$iv`.endStructure(descriptor);
}
