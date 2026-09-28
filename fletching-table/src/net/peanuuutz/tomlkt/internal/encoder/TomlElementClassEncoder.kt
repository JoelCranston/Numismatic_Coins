package net.peanuuutz.tomlkt.internal.encoder

import kotlin.jvm.internal.Intrinsics
import kotlinx.serialization.descriptors.SerialDescriptor
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementKt

private class TomlElementClassEncoder(delegate: AbstractTomlElementEncoder,
   builder: MutableMap<String, TomlElement>,
   annotations: MutableMap<String, List<Annotation>>
) : AbstractTomlElementCompositeEncoder(delegate) {
   private final val builder: MutableMap<String, TomlElement>
   private final val annotations: MutableMap<String, List<Annotation>>
   private final lateinit var currentKey: String

   init {
      this.builder = builder;
      this.annotations = annotations;
   }

   public override fun encodeDiscriminatorElement(discriminator: String, serialName: String, isEmptyStructure: Boolean) {
      this.builder.put(discriminator, TomlElementKt.TomlLiteral(serialName));
   }

   public override fun beginElement(descriptor: SerialDescriptor, index: Int) {
      this.currentKey = descriptor.getElementName(index);
   }

   public override fun endElement(descriptor: SerialDescriptor, index: Int) {
      var var10000: java.lang.String = this.currentKey;
      if (this.currentKey == null) {
         Intrinsics.throwUninitializedPropertyAccessException("currentKey");
         var10000 = null;
      }

      val propertyAnnotations: java.util.List = descriptor.getElementAnnotations(index);
      val intrinsicAnnotations: java.util.List = descriptor.getElementDescriptor(index).getAnnotations();
      this.builder.put(var10000, this.getElement());
      this.annotations.put(var10000, CollectionsKt.plus(propertyAnnotations, intrinsicAnnotations));
   }
}
