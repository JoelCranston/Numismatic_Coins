package com.charleskorn.kaml

import com.charleskorn.kaml.YamlNodeSerializer.annotationImpl.com_charleskorn_kaml_YamlContentPolymorphicSerializer_Marker.0
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@SourceDebugExtension(["SMAP\nYamlNodeSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlNodeSerializer\n+ 2 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlNodeSerializerKt\n*L\n1#1,152:1\n145#2,3:153\n*S KotlinDebug\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlNodeSerializer\n*L\n58#1:153,3\n*E\n"])
internal object YamlNodeSerializer : KSerializer<YamlNode> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.getNullable(
         SerialDescriptorsKt.buildSerialDescriptor(
            "com.charleskorn.kaml.YamlNode", PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], YamlNodeSerializer::descriptor$lambda$0
         )
      )

   public open fun serialize(encoder: Encoder, value: YamlNode) {
      YamlNodeSerializerKt.access$asYamlOutput(encoder);
      if (value is YamlList) {
         encoder.encodeSerializableValue(YamlListSerializer.INSTANCE, value);
      } else if (value is YamlMap) {
         encoder.encodeSerializableValue(YamlMapSerializer.INSTANCE, value);
      } else if (value is YamlNull) {
         encoder.encodeSerializableValue(YamlNullSerializer.INSTANCE, value);
      } else if (value is YamlScalar) {
         encoder.encodeSerializableValue(YamlScalarSerializer.INSTANCE, value);
      } else {
         if (value !is YamlTaggedNode) {
            throw new NoWhenBranchMatchedException();
         }

         encoder.encodeSerializableValue(YamlTaggedNodeSerializer.INSTANCE, value);
      }
   }

   public open fun deserialize(decoder: Decoder): YamlNode {
      var var10000: Decoder = decoder;
      if (decoder !is YamlInput) {
         var10000 = null;
      }

      val var7: YamlInput = var10000 as YamlInput;
      if (var10000 as YamlInput == null) {
         throw new IllegalStateException(
            ("This serializer can be used only with Yaml format. Expected Decoder to be ${(YamlInput::class).getSimpleName()}, got ${decoder.getClass()::class}")
               .toString()
         );
      } else {
         return if (var7 is YamlPolymorphicInput) new YamlTaggedNode((var7 as YamlPolymorphicInput).getTypeName(), var7.getNode()) else var7.getNode();
      }
   }

   @JvmStatic
   fun ClassSerialDescriptorBuilder.`descriptor$lambda$0`(): Unit {
      `$this$buildSerialDescriptor`.setAnnotations(CollectionsKt.plus(`$this$buildSerialDescriptor`.getAnnotations(), new 0()));
      return Unit.INSTANCE;
   }
}
