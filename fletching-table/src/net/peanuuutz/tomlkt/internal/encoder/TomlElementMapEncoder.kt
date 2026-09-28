package net.peanuuutz.tomlkt.internal.encoder

import kotlin.jvm.internal.Intrinsics
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.internal.TomlSerializationExceptionsKt

private class TomlElementMapEncoder(delegate: AbstractTomlElementEncoder,
   builder: MutableMap<String, TomlElement>,
   annotations: MutableMap<String, List<Annotation>>
) : AbstractTomlElementCompositeEncoder(delegate) {
   private final val builder: MutableMap<String, TomlElement>
   private final val annotations: MutableMap<String, List<Annotation>>
   private final var isKey: Boolean
   private final lateinit var currentKey: String

   init {
      this.builder = builder;
      this.annotations = annotations;
      this.isKey = true;
   }

   public override fun <T> encodeSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<T>, value: T) {
      if (this.isKey) {
         this.currentKey = TomlElementKt.toTomlKey(value);
      } else {
         this.encodeSerializableValue(serializer, value);
         var var10000: java.lang.String = this.currentKey;
         if (this.currentKey == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentKey");
            var10000 = null;
         }

         this.builder.put(var10000, this.getElement());
         this.annotations.put(var10000, descriptor.getElementDescriptor(index).getAnnotations());
      }

      this.isKey = !this.isKey;
   }

   public override fun encodeDiscriminatorElement(discriminator: String, serialName: String, isEmptyStructure: Boolean) {
      TomlSerializationExceptionsKt.throwPolymorphicCollection();
      throw new KotlinNothingValueException();
   }

   public override fun beginElement(descriptor: SerialDescriptor, index: Int) {
   }

   public override fun endElement(descriptor: SerialDescriptor, index: Int) {
   }
}
