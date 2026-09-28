package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
@SourceDebugExtension(["SMAP\nObjectSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ObjectSerializer.kt\nkotlinx/serialization/internal/ObjectSerializer\n+ 2 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,57:1\n571#2,4:58\n*S KotlinDebug\n*F\n+ 1 ObjectSerializer.kt\nkotlinx/serialization/internal/ObjectSerializer\n*L\n43#1:58,4\n*E\n"])
internal class ObjectSerializer<T>(serialName: String, objectInstance: Any) : KSerializer<T> {
   private final val objectInstance: Any
   private final var _annotations: List<Annotation>

   public open val descriptor: SerialDescriptor
      public open get() {
         return this.descriptor$delegate.getValue() as SerialDescriptor;
      }


   init {
      this.objectInstance = (T)objectInstance;
      this._annotations = CollectionsKt.emptyList();
      this.descriptor$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, ObjectSerializer::descriptor_delegate$lambda$1);
   }

   @PublishedApi
   internal constructor(serialName: String, objectInstance: Any, vararg classAnnotations: Any) : this(serialName, (T)objectInstance) {
      this._annotations = ArraysKt.asList(classAnnotations);
   }

   public override fun serialize(encoder: Encoder, value: Any) {
      encoder.beginStructure(this.getDescriptor()).endStructure(this.getDescriptor());
   }

   public override fun deserialize(decoder: Decoder): Any {
      val `descriptor$iv`: SerialDescriptor = this.getDescriptor();
      val `composite$iv`: CompositeDecoder = decoder.beginStructure(`descriptor$iv`);
      if (!`composite$iv`.decodeSequentially()) {
         val index: Int = `composite$iv`.decodeElementIndex(this.getDescriptor());
         if (index != -1) {
            throw new SerializationException("Unexpected index $index");
         }
      }

      `composite$iv`.endStructure(`descriptor$iv`);
      return this.objectInstance;
   }

   @JvmStatic
   fun `descriptor_delegate$lambda$1$lambda$0`(`this$0`: ObjectSerializer, `$this$buildSerialDescriptor`: ClassSerialDescriptorBuilder): Unit {
      `$this$buildSerialDescriptor`.setAnnotations(`this$0`._annotations);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `descriptor_delegate$lambda$1`(`$serialName`: java.lang.String, `this$0`: ObjectSerializer): SerialDescriptor {
      return SerialDescriptorsKt.buildSerialDescriptor(
         `$serialName`, StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], ObjectSerializer::descriptor_delegate$lambda$1$lambda$0
      );
   }
}
