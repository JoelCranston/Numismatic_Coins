@file:SourceDebugExtension(["SMAP\nAbstractTomlEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractTomlEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/AbstractTomlEncoderKt\n+ 2 PolymorphismUtils.kt\nnet/peanuuutz/tomlkt/internal/PolymorphismUtilsKt\n*L\n1#1,302:1\n52#2,7:303\n*S KotlinDebug\n*F\n+ 1 AbstractTomlEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/AbstractTomlEncoderKt\n*L\n65#1:303,7\n*E\n"])

package net.peanuuutz.tomlkt.internal.encoder

import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.PolymorphicSerializerKt
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import net.peanuuutz.tomlkt.internal.PolymorphismUtilsKt
import net.peanuuutz.tomlkt.internal.SerialDescriptorUtilsKt

internal fun <T> AbstractTomlEncoder.encodeSerializableValuePolymorphically(serializer: SerializationStrategy<T>, value: T) {
   if (SerialDescriptorUtilsKt.isPrimitiveLike(
      SerialDescriptorUtilsKt.findRealDescriptor(serializer.getDescriptor(), `$this$encodeSerializableValuePolymorphically`.getToml().getConfig())
   )) {
      serializer.serialize(`$this$encodeSerializableValuePolymorphically`, value);
   } else if (serializer !is AbstractPolymorphicSerializer) {
      serializer.serialize(`$this$encodeSerializableValuePolymorphically`, value);
   } else {
      val `serializer$iv`: AbstractPolymorphicSerializer = serializer as AbstractPolymorphicSerializer;
      val `discriminator$iv`: java.lang.String = PolymorphismUtilsKt.findDiscriminator(
         `serializer$iv`.getDescriptor(), `$this$encodeSerializableValuePolymorphically`.getToml().getConfig()
      );
      val `realSerializer$iv`: SerializationStrategy = PolymorphicSerializerKt.findPolymorphicSerializer(
         `serializer$iv`, `$this$encodeSerializableValuePolymorphically`, value
      );
      PolymorphismUtilsKt.access$validateDiscriminator(`serializer$iv`.getDescriptor(), `realSerializer$iv`.getDescriptor(), `discriminator$iv`);
      PolymorphismUtilsKt.access$validateKind(`realSerializer$iv`.getDescriptor());
      `$this$encodeSerializableValuePolymorphically`.setCurrentDiscriminator(`discriminator$iv`);
      `realSerializer$iv`.serialize(`$this$encodeSerializableValuePolymorphically`, value);
   }
}

internal inline fun TomlCompositeEncoder.encodeElement(descriptor: SerialDescriptor, index: Int, encode: () -> Unit) {
   contract {
      callsInPlace(encode, InvocationKind.EXACTLY_ONCE)
   }

   `$this$encodeElement`.beginElement(descriptor, index);
   encode.invoke();
   `$this$encodeElement`.endElement(descriptor, index);
}

internal fun AbstractTomlEncoder.onBeginStructurePolymorphically(
   compositeEncoder: TomlCompositeEncoder,
   descriptor: SerialDescriptor,
   isEmptyStructure: Boolean
) {
   val var10000: java.lang.String = `$this$onBeginStructurePolymorphically`.getCurrentDiscriminator();
   if (var10000 != null) {
      `$this$onBeginStructurePolymorphically`.setCurrentDiscriminator(null);
      compositeEncoder.encodeDiscriminatorElement(var10000, descriptor.getSerialName(), isEmptyStructure);
   }
}
