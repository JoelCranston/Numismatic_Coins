package kotlinx.serialization

import kotlin.jvm.internal.StringCompanionObject
import kotlin.reflect.KClass
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.ContextAwareKt
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.internal.AbstractPolymorphicSerializer

public class PolymorphicSerializer<T>(baseClass: KClass<Any>) : AbstractPolymorphicSerializer<T> {
   public open val baseClass: KClass<Any>
   private final var _annotations: List<Annotation>

   public open val descriptor: SerialDescriptor
      public open get() {
         return this.descriptor$delegate.getValue() as SerialDescriptor;
      }


   init {
      this.baseClass = baseClass;
      this._annotations = CollectionsKt.emptyList();
      this.descriptor$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, PolymorphicSerializer::descriptor_delegate$lambda$1);
   }

   @PublishedApi
   internal constructor(baseClass: KClass<Any>, vararg classAnnotations: Any) : this(baseClass) {
      this._annotations = ArraysKt.asList(classAnnotations);
   }

   public override fun toString(): String {
      return "kotlinx.serialization.PolymorphicSerializer(baseClass: ${this.getBaseClass()})";
   }

   @JvmStatic
   fun `descriptor_delegate$lambda$1$lambda$0`(`this$0`: PolymorphicSerializer, `$this$buildSerialDescriptor`: ClassSerialDescriptorBuilder): Unit {
      ClassSerialDescriptorBuilder.element$default(
         `$this$buildSerialDescriptor`, "type", BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE).getDescriptor(), null, false, 12, null
      );
      ClassSerialDescriptorBuilder.element$default(
         `$this$buildSerialDescriptor`,
         "value",
         SerialDescriptorsKt.buildSerialDescriptor$default(
            "kotlinx.serialization.Polymorphic<${`this$0`.getBaseClass().getSimpleName()}>",
            SerialKind.CONTEXTUAL.INSTANCE,
            new SerialDescriptor[0],
            null,
            8,
            null
         ),
         null,
         false,
         12,
         null
      );
      `$this$buildSerialDescriptor`.setAnnotations(`this$0`._annotations);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `descriptor_delegate$lambda$1`(`this$0`: PolymorphicSerializer): SerialDescriptor {
      return ContextAwareKt.withContext(
         SerialDescriptorsKt.buildSerialDescriptor(
            "kotlinx.serialization.Polymorphic",
            PolymorphicKind.OPEN.INSTANCE,
            new SerialDescriptor[0],
            PolymorphicSerializer::descriptor_delegate$lambda$1$lambda$0
         ),
         `this$0`.getBaseClass()
      );
   }
}
