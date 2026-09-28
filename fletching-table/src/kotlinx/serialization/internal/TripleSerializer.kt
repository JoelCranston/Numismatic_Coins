package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal class TripleSerializer<A, B, C>(aSerializer: KSerializer<Any>, bSerializer: KSerializer<Any>, cSerializer: KSerializer<Any>) :
   KSerializer<Triple<? extends A, ? extends B, ? extends C>> {
   private final val aSerializer: KSerializer<Any>
   private final val bSerializer: KSerializer<Any>
   private final val cSerializer: KSerializer<Any>
   public open val descriptor: SerialDescriptor

   init {
      this.aSerializer = aSerializer;
      this.bSerializer = bSerializer;
      this.cSerializer = cSerializer;
      this.descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("kotlin.Triple", new SerialDescriptor[0], TripleSerializer::descriptor$lambda$0);
   }

   public open fun serialize(encoder: Encoder, value: Triple<Any, Any, Any>) {
      val structuredEncoder: CompositeEncoder = encoder.beginStructure(this.getDescriptor());
      structuredEncoder.encodeSerializableElement(this.getDescriptor(), 0, this.aSerializer, (A)value.getFirst());
      structuredEncoder.encodeSerializableElement(this.getDescriptor(), 1, this.bSerializer, (B)value.getSecond());
      structuredEncoder.encodeSerializableElement(this.getDescriptor(), 2, this.cSerializer, (C)value.getThird());
      structuredEncoder.endStructure(this.getDescriptor());
   }

   public open fun deserialize(decoder: Decoder): Triple<Any, Any, Any> {
      val composite: CompositeDecoder = decoder.beginStructure(this.getDescriptor());
      return if (composite.decodeSequentially()) this.decodeSequentially(composite) else this.decodeStructure(composite);
   }

   private fun decodeSequentially(composite: CompositeDecoder): Triple<Any, Any, Any> {
      val a: Any = CompositeDecoder.decodeSerializableElement$default(composite, this.getDescriptor(), 0, this.aSerializer, null, 8, null);
      val b: Any = CompositeDecoder.decodeSerializableElement$default(composite, this.getDescriptor(), 1, this.bSerializer, null, 8, null);
      val c: Any = CompositeDecoder.decodeSerializableElement$default(composite, this.getDescriptor(), 2, this.cSerializer, null, 8, null);
      composite.endStructure(this.getDescriptor());
      return new Triple<>((A)a, (B)b, (C)c);
   }

   private fun decodeStructure(composite: CompositeDecoder): Triple<Any, Any, Any> {
      var a: Any = TuplesKt.access$getNULL$p();
      var b: Any = TuplesKt.access$getNULL$p();
      var c: Any = TuplesKt.access$getNULL$p();

      while (true) {
         val index: Int = composite.decodeElementIndex(this.getDescriptor());
         switch (index) {
            case -1:
               composite.endStructure(this.getDescriptor());
               if (a === TuplesKt.access$getNULL$p()) {
                  throw new SerializationException("Element 'first' is missing");
               }

               if (b === TuplesKt.access$getNULL$p()) {
                  throw new SerializationException("Element 'second' is missing");
               }

               if (c === TuplesKt.access$getNULL$p()) {
                  throw new SerializationException("Element 'third' is missing");
               }

               return new Triple<>((A)a, (B)b, (C)c);
            case 0:
               a = CompositeDecoder.decodeSerializableElement$default(composite, this.getDescriptor(), 0, this.aSerializer, null, 8, null);
               break;
            case 1:
               b = CompositeDecoder.decodeSerializableElement$default(composite, this.getDescriptor(), 1, this.bSerializer, null, 8, null);
               break;
            case 2:
               c = CompositeDecoder.decodeSerializableElement$default(composite, this.getDescriptor(), 2, this.cSerializer, null, 8, null);
               break;
            default:
               throw new SerializationException("Unexpected index $index");
         }
      }
   }

   @JvmStatic
   fun `descriptor$lambda$0`(`this$0`: TripleSerializer, `$this$buildClassSerialDescriptor`: ClassSerialDescriptorBuilder): Unit {
      ClassSerialDescriptorBuilder.element$default(`$this$buildClassSerialDescriptor`, "first", `this$0`.aSerializer.getDescriptor(), null, false, 12, null);
      ClassSerialDescriptorBuilder.element$default(`$this$buildClassSerialDescriptor`, "second", `this$0`.bSerializer.getDescriptor(), null, false, 12, null);
      ClassSerialDescriptorBuilder.element$default(`$this$buildClassSerialDescriptor`, "third", `this$0`.cSerializer.getDescriptor(), null, false, 12, null);
      return Unit.INSTANCE;
   }
}
