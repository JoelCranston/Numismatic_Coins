package com.charleskorn.kaml

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@SourceDebugExtension(["SMAP\nYamlNodeSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlListSerializer\n+ 2 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlNodeSerializerKt\n*L\n1#1,152:1\n145#2,3:153\n*S KotlinDebug\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlListSerializer\n*L\n140#1:153,3\n*E\n"])
internal object YamlListSerializer : KSerializer<YamlList> {
   public open val descriptor: SerialDescriptor = BuiltinSerializersKt.ListSerializer(YamlNodeSerializer.INSTANCE).getDescriptor()

   public open fun serialize(encoder: Encoder, value: YamlList) {
      YamlNodeSerializerKt.access$asYamlOutput(encoder);
      BuiltinSerializersKt.ListSerializer(YamlNodeSerializer.INSTANCE).serialize(encoder, value.getItems());
   }

   public open fun deserialize(decoder: Decoder): YamlList {
      var var10000: Decoder = decoder;
      if (decoder !is YamlListInput) {
         var10000 = null;
      }

      val var7: YamlInput = var10000 as YamlListInput;
      if (var10000 as YamlListInput == null) {
         throw new IllegalStateException(
            ("This serializer can be used only with Yaml format. Expected Decoder to be ${(YamlListInput::class).getSimpleName()}, got ${decoder.getClass()::class}")
               .toString()
         );
      } else {
         return (var7 as YamlListInput).getList();
      }
   }
}
