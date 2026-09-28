package net.peanuuutz.tomlkt.internal.encoder

import kotlinx.serialization.descriptors.SerialDescriptor
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.internal.TomlSerializationExceptionsKt

private class TomlElementArrayEncoder(delegate: AbstractTomlElementEncoder, builder: MutableList<TomlElement>, annotations: MutableList<List<Annotation>>) : AbstractTomlElementCompositeEncoder(
      delegate
   ) {
   private final val builder: MutableList<TomlElement>
   private final val annotations: MutableList<List<Annotation>>

   init {
      this.builder = builder;
      this.annotations = annotations;
   }

   public override fun encodeDiscriminatorElement(discriminator: String, serialName: String, isEmptyStructure: Boolean) {
      TomlSerializationExceptionsKt.throwPolymorphicCollection();
      throw new KotlinNothingValueException();
   }

   public override fun beginElement(descriptor: SerialDescriptor, index: Int) {
   }

   public override fun endElement(descriptor: SerialDescriptor, index: Int) {
      this.builder.add(this.getElement());
      this.annotations.add(descriptor.getElementDescriptor(index).getAnnotations());
   }
}
