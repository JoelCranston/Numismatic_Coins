package net.peanuuutz.tomlkt.internal

import java.lang.annotation.Annotation
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.PolymorphicSerializerKt
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import kotlinx.serialization.modules.SerializersModule
import net.peanuuutz.tomlkt.TomlClassDiscriminator
import net.peanuuutz.tomlkt.TomlConfig
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlLiteral
import net.peanuuutz.tomlkt.internal.decoder.AbstractTomlDecoder
import net.peanuuutz.tomlkt.internal.encoder.AbstractTomlEncoder

internal fun SerialDescriptor.findDiscriminator(config: TomlConfig): String {
   for (Annotation annotation : $this$findDiscriminator.getAnnotations()) {
      if (annotation is TomlClassDiscriminator) {
         return (annotation as TomlClassDiscriminator).discriminator();
      }
   }

   return config.getClassDiscriminator();
}

internal inline fun AbstractTomlEncoder.encodePolymorphically(serializer: AbstractPolymorphicSerializer<Any>, value: Any, callback: (String) -> Unit) {
   val discriminator: java.lang.String = findDiscriminator(serializer.getDescriptor(), `$this$encodePolymorphically`.getToml().getConfig());
   val realSerializer: SerializationStrategy = PolymorphicSerializerKt.findPolymorphicSerializer(serializer, `$this$encodePolymorphically`, value);
   access$validateDiscriminator(serializer.getDescriptor(), realSerializer.getDescriptor(), discriminator);
   access$validateKind(realSerializer.getDescriptor());
   callback.invoke(discriminator);
   realSerializer.serialize(`$this$encodePolymorphically`, value);
}

private fun validateDiscriminator(baseDescriptor: SerialDescriptor, realDescriptor: SerialDescriptor, discriminator: String) {
   if (baseDescriptor.getKind() is PolymorphicKind.SEALED && CollectionsKt.contains(SerialDescriptorKt.getElementNames(realDescriptor), discriminator)) {
      throw new IllegalStateException(
         ("Subclass ${realDescriptor.getSerialName()} cannot be serialized as base class ${baseDescriptor.getSerialName()} because one of the property serial name conflicts with class discriminator \"$discriminator\". Please rename the conflicting property, or annotate it with @SerialName providing another serial name")
            .toString()
      );
   }
}

private fun validateKind(realDescriptor: SerialDescriptor) {
   val var1: SerialKind = realDescriptor.getKind();
   if (var1 == SerialKind.ENUM.INSTANCE || var1 is PrimitiveKind) {
      throw new IllegalStateException(("Primitive like ${realDescriptor.getSerialName()} cannot be serialized with class discriminator").toString());
   } else if (var1 is PolymorphicKind) {
      throw new IllegalStateException(("Subclass ${realDescriptor.getSerialName()} cannot be PolymorphicKind again").toString());
   }
}

internal inline fun <T> AbstractTomlDecoder.decodePolymorphically(deserializer: AbstractPolymorphicSerializer<Any>, callback: (String) -> Unit): T {
   var discriminator: java.lang.String;
   var var10: java.lang.String;
   label12: {
      discriminator = findDiscriminator(deserializer.getDescriptor(), `$this$decodePolymorphically`.getToml().getConfig());
      val var10000: TomlElement = TomlElementKt.asTomlTable(`$this$decodePolymorphically`.decodeTomlElement()).get((Object)discriminator) as TomlElement;
      if (var10000 != null) {
         val var9: TomlLiteral = TomlElementKt.asTomlLiteral(var10000);
         if (var9 != null) {
            var10 = var9.getContent();
            break label12;
         }
      }

      var10 = null;
   }

   val var11: DeserializationStrategy = PolymorphicSerializerKt.findPolymorphicSerializer(
      deserializer, access$configureDummyDecoder(`$this$decodePolymorphically`.getSerializersModule()), var10
   );
   callback.invoke(discriminator);
   return (T)var11.deserialize(`$this$decodePolymorphically`);
}

private fun configureDummyDecoder(serializersModule: SerializersModule): CompositeDecoder {
   val decoder: DummyDecoder = DummyDecoder.INSTANCE;
   DummyDecoder.INSTANCE.setSerializersModule(serializersModule);
   return decoder;
}

@JvmSynthetic
fun `access$validateDiscriminator`(baseDescriptor: SerialDescriptor, realDescriptor: SerialDescriptor, discriminator: java.lang.String) {
   validateDiscriminator(baseDescriptor, realDescriptor, discriminator);
}

@JvmSynthetic
fun `access$validateKind`(realDescriptor: SerialDescriptor) {
   validateKind(realDescriptor);
}

@JvmSynthetic
fun `access$configureDummyDecoder`(serializersModule: SerializersModule): CompositeDecoder {
   return configureDummyDecoder(serializersModule);
}
