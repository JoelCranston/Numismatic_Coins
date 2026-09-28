package kotlinx.serialization.json.internal

import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.SerializersModuleCollector

internal class JsonSerializersModuleValidator(configuration: JsonConfiguration) : SerializersModuleCollector {
   private final val discriminator: String
   private final val useArrayPolymorphism: Boolean
   private final val isDiscriminatorRequired: Boolean

   init {
      this.discriminator = configuration.getClassDiscriminator();
      this.useArrayPolymorphism = configuration.getUseArrayPolymorphism();
      this.isDiscriminatorRequired = configuration.getClassDiscriminatorMode() != ClassDiscriminatorMode.NONE;
   }

   public override fun <T : Any> contextual(kClass: KClass<T>, provider: (List<KSerializer<*>>) -> KSerializer<*>) {
   }

   public override fun <Base : Any, Sub : Base> polymorphic(baseClass: KClass<Base>, actualClass: KClass<Sub>, actualSerializer: KSerializer<Sub>) {
      val descriptor: SerialDescriptor = actualSerializer.getDescriptor();
      this.checkKind(descriptor, actualClass);
      if (!this.useArrayPolymorphism && this.isDiscriminatorRequired) {
         this.checkDiscriminatorCollisions(descriptor, actualClass);
      }
   }

   private fun checkKind(descriptor: SerialDescriptor, actualClass: KClass<*>) {
      val kind: SerialKind = descriptor.getKind();
      if (kind is PolymorphicKind || kind == SerialKind.CONTEXTUAL.INSTANCE) {
         throw new IllegalArgumentException(
            "Serializer for ${actualClass.getSimpleName()} can't be registered as a subclass for polymorphic serialization because its kind $kind is not concrete. To work with multiple hierarchies, register it as a base class."
         );
      } else if (!this.useArrayPolymorphism) {
         if (this.isDiscriminatorRequired) {
            if (kind == StructureKind.LIST.INSTANCE || kind == StructureKind.MAP.INSTANCE || kind is PrimitiveKind || kind is SerialKind.ENUM) {
               throw new IllegalArgumentException(
                  "Serializer for ${actualClass.getSimpleName()} of kind $kind cannot be serialized polymorphically with class discriminator."
               );
            }
         }
      }
   }

   private fun checkDiscriminatorCollisions(descriptor: SerialDescriptor, actualClass: KClass<*>) {
      var i: Int = 0;

      for (int var4 = descriptor.getElementsCount(); i < var4; i++) {
         val name: java.lang.String = descriptor.getElementName(i);
         if (name == this.discriminator) {
            throw new IllegalArgumentException(
               "Polymorphic serializer for $actualClass has property '$name' that conflicts with JSON class discriminator. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism"
            );
         }
      }
   }

   public override fun <Base : Any> polymorphicDefaultSerializer(baseClass: KClass<Base>, defaultSerializerProvider: (Base) -> SerializationStrategy<Base>?) {
   }

   public override fun <Base : Any> polymorphicDefaultDeserializer(
      baseClass: KClass<Base>,
      defaultDeserializerProvider: (String?) -> DeserializationStrategy<Base>?
   ) {
   }

   override fun <T> contextual(kClass: KClass<T>, serializer: KSerializer<T>) {
      SerializersModuleCollector.super.contextual(kClass, serializer);
   }

   /** @deprecated */
   @Deprecated(message = "Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer", replaceWith = @ReplaceWith(expression = "polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider)", imports = []), level = DeprecationLevel.WARNING)
   override fun <Base> polymorphicDefault(baseClass: KClass<Base>, defaultDeserializerProvider: (java.lang.String?) -> DeserializationStrategy<? extends Base>) {
      SerializersModuleCollector.super.polymorphicDefault(baseClass, defaultDeserializerProvider);
   }
}
