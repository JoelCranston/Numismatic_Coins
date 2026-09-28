package com.charleskorn.kaml

import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.StringCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@SourceDebugExtension(["SMAP\nYamlNodeSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlTaggedNodeSerializer\n+ 2 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 3 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlNodeSerializerKt\n*L\n1#1,152:1\n476#2,4:153\n145#3,3:157\n*S KotlinDebug\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlTaggedNodeSerializer\n*L\n105#1:153,4\n112#1:157,3\n*E\n"])
internal object YamlTaggedNodeSerializer : KSerializer<YamlTaggedNode> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.buildSerialDescriptor(
         "com.charleskorn.kaml.YamlTaggedNode", PolymorphicKind.OPEN.INSTANCE, new SerialDescriptor[0], YamlTaggedNodeSerializer::descriptor$lambda$0
      )

   public open fun serialize(encoder: Encoder, value: YamlTaggedNode) {
      val `$this$encodeStructure$iv`: Encoder = YamlNodeSerializerKt.access$asYamlOutput(encoder);
      val `descriptor$iv`: SerialDescriptor = this.getDescriptor();
      val `composite$iv`: CompositeEncoder = `$this$encodeStructure$iv`.beginStructure(`descriptor$iv`);
      `composite$iv`.encodeStringElement(INSTANCE.getDescriptor(), 0, value.getTag());
      `composite$iv`.encodeSerializableElement(INSTANCE.getDescriptor(), 1, YamlNodeSerializer.INSTANCE, value.getInnerNode());
      `composite$iv`.endStructure(`descriptor$iv`);
   }

   public open fun deserialize(decoder: Decoder): YamlTaggedNode {
      var var10000: Decoder = decoder;
      if (decoder !is YamlPolymorphicInput) {
         var10000 = null;
      }

      val var7: YamlInput = var10000 as YamlPolymorphicInput;
      if (var10000 as YamlPolymorphicInput == null) {
         throw new IllegalStateException(
            ("This serializer can be used only with Yaml format. Expected Decoder to be ${(YamlPolymorphicInput::class).getSimpleName()}, got ${decoder.getClass()::class}")
               .toString()
         );
      } else {
         return new YamlTaggedNode((var7 as YamlPolymorphicInput).getTypeName(), (var7 as YamlPolymorphicInput).getContentNode());
      }
   }

   @JvmStatic
   fun ClassSerialDescriptorBuilder.`descriptor$lambda$0`(): Unit {
      ClassSerialDescriptorBuilder.element$default(
         `$this$buildSerialDescriptor`, "tag", BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE).getDescriptor(), null, false, 12, null
      );
      ClassSerialDescriptorBuilder.element$default(`$this$buildSerialDescriptor`, "node", YamlNodeSerializer.INSTANCE.getDescriptor(), null, false, 12, null);
      return Unit.INSTANCE;
   }
}
