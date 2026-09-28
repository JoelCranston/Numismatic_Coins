package com.charleskorn.kaml

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@SourceDebugExtension(["SMAP\nYamlNodeSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlNullSerializer\n+ 2 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlNodeSerializerKt\n*L\n1#1,152:1\n145#2,3:153\n*S KotlinDebug\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlNullSerializer\n*L\n91#1:153,3\n*E\n"])
internal object YamlNullSerializer : KSerializer<YamlNull> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.buildSerialDescriptor$default("com.charleskorn.kaml.YamlNull", SerialKind.ENUM.INSTANCE, new SerialDescriptor[0], null, 8, null)

   public open fun serialize(encoder: Encoder, value: YamlNull) {
      YamlNodeSerializerKt.access$asYamlOutput(encoder).encodeNull();
   }

   public open fun deserialize(decoder: Decoder): YamlNull {
      var var10000: Decoder = decoder;
      if (decoder !is YamlNullInput) {
         var10000 = null;
      }

      val var7: YamlInput = var10000 as YamlNullInput;
      if (var10000 as YamlNullInput == null) {
         throw new IllegalStateException(
            ("This serializer can be used only with Yaml format. Expected Decoder to be ${(YamlNullInput::class).getSimpleName()}, got ${decoder.getClass()::class}")
               .toString()
         );
      } else {
         return (var7 as YamlNullInput).getNullValue();
      }
   }
}
