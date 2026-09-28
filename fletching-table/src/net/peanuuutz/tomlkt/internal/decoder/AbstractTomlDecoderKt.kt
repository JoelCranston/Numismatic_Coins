@file:SourceDebugExtension(["SMAP\nAbstractTomlDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractTomlDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/AbstractTomlDecoderKt\n+ 2 PolymorphismUtils.kt\nnet/peanuuutz/tomlkt/internal/PolymorphismUtilsKt\n*L\n1#1,295:1\n94#2,8:296\n*S KotlinDebug\n*F\n+ 1 AbstractTomlDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/AbstractTomlDecoderKt\n*L\n61#1:296,8\n*E\n"])

package net.peanuuutz.tomlkt.internal.decoder

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.PolymorphicSerializerKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlLiteral
import net.peanuuutz.tomlkt.internal.PolymorphismUtilsKt
import net.peanuuutz.tomlkt.internal.SerialDescriptorUtilsKt

internal fun <T> AbstractTomlDecoder.decodeSerializableValuePolymorphically(deserializer: DeserializationStrategy<T>): T {
   var var10000: Any;
   if (SerialDescriptorUtilsKt.isPrimitiveLike(
      SerialDescriptorUtilsKt.findRealDescriptor(deserializer.getDescriptor(), `$this$decodeSerializableValuePolymorphically`.getToml().getConfig())
   )) {
      var10000 = (TomlLiteral)deserializer.deserialize(`$this$decodeSerializableValuePolymorphically`);
   } else if (deserializer !is AbstractPolymorphicSerializer) {
      var10000 = (TomlLiteral)deserializer.deserialize(`$this$decodeSerializableValuePolymorphically`);
   } else {
      var `deserializer$iv`: AbstractPolymorphicSerializer;
      var `discriminator$iv`: java.lang.String;
      label17: {
         `deserializer$iv` = deserializer as AbstractPolymorphicSerializer;
         `discriminator$iv` = PolymorphismUtilsKt.findDiscriminator(
            (deserializer as AbstractPolymorphicSerializer).getDescriptor(), `$this$decodeSerializableValuePolymorphically`.getToml().getConfig()
         );
         var10000 = TomlElementKt.asTomlTable(`$this$decodeSerializableValuePolymorphically`.decodeTomlElement()).get((Object)`discriminator$iv`) as TomlElement;
         if (var10000 != null) {
            var10000 = TomlElementKt.asTomlLiteral((TomlElement)var10000);
            if (var10000 != null) {
               var10000 = var10000.getContent();
               break label17;
            }
         }

         var10000 = null;
      }

      val var15: DeserializationStrategy = PolymorphicSerializerKt.findPolymorphicSerializer(
         `deserializer$iv`,
         PolymorphismUtilsKt.access$configureDummyDecoder(`$this$decodeSerializableValuePolymorphically`.getSerializersModule()),
         (java.lang.String)var10000
      );
      `$this$decodeSerializableValuePolymorphically`.setCurrentDiscriminator(`discriminator$iv`);
      var10000 = (TomlLiteral)var15.deserialize(`$this$decodeSerializableValuePolymorphically`);
   }

   return (T)var10000;
}

internal inline fun <T> TomlCompositeDecoder.decodeElement(descriptor: SerialDescriptor, index: Int, decode: () -> T): T {
   `$this$decodeElement`.beginElement(descriptor, index);
   val value: Any = decode.invoke();
   `$this$decodeElement`.endElement(descriptor, index);
   return (T)value;
}
