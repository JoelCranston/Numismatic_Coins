package net.peanuuutz.tomlkt.internal.encoder

import java.util.ArrayList
import java.util.LinkedHashMap
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeEncoder
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.internal.TomlSerializationExceptionsKt

private fun AbstractTomlElementEncoder.beginStructurePolymorphically(descriptor: SerialDescriptor): CompositeEncoder {
   val kind: SerialKind = descriptor.getKind();
   if (!(kind == StructureKind.CLASS.INSTANCE) && kind !is PolymorphicKind && !(kind == StructureKind.OBJECT.INSTANCE)) {
      TomlSerializationExceptionsKt.throwUnsupportedSerialKind(kind);
      throw new KotlinNothingValueException();
   } else {
      val builder: java.util.Map = new LinkedHashMap();
      val annotations: java.util.Map = new LinkedHashMap();
      `$this$beginStructurePolymorphically`.setElement(new TomlTable(builder, annotations));
      val compositeEncoder: TomlElementClassEncoder = new TomlElementClassEncoder(`$this$beginStructurePolymorphically`, builder, annotations);
      AbstractTomlEncoderKt.onBeginStructurePolymorphically(
         `$this$beginStructurePolymorphically`, compositeEncoder, descriptor, descriptor.getElementsCount() == 0
      );
      return compositeEncoder;
   }
}

private fun AbstractTomlElementEncoder.beginCollectionPolymorphically(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
   val kind: SerialKind = descriptor.getKind();
   val var10000: CompositeEncoder;
   if (kind == StructureKind.LIST.INSTANCE) {
      val builder: java.util.List = new ArrayList();
      val annotations: java.util.List = new ArrayList();
      `$this$beginCollectionPolymorphically`.setElement(new TomlArray(builder, annotations));
      var10000 = new TomlElementArrayEncoder(`$this$beginCollectionPolymorphically`, builder, annotations);
   } else {
      if (!(kind == StructureKind.MAP.INSTANCE)) {
         TomlSerializationExceptionsKt.throwUnsupportedSerialKind(kind);
         throw new KotlinNothingValueException();
      }

      val var6: java.util.Map = new LinkedHashMap();
      val var7: java.util.Map = new LinkedHashMap();
      `$this$beginCollectionPolymorphically`.setElement(new TomlTable(var6, var7));
      var10000 = new TomlElementMapEncoder(`$this$beginCollectionPolymorphically`, var6, var7);
   }

   return var10000;
}

@JvmSynthetic
fun `access$beginStructurePolymorphically`(`$receiver`: AbstractTomlElementEncoder, descriptor: SerialDescriptor): CompositeEncoder {
   return beginStructurePolymorphically(`$receiver`, descriptor);
}

@JvmSynthetic
fun `access$beginCollectionPolymorphically`(`$receiver`: AbstractTomlElementEncoder, descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
   return beginCollectionPolymorphically(`$receiver`, descriptor, collectionSize);
}
