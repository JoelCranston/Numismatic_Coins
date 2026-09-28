package com.charleskorn.kaml

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@SourceDebugExtension(["SMAP\nYamlNodeSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlMapSerializer\n+ 2 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlNodeSerializerKt\n*L\n1#1,152:1\n145#2,3:153\n*S KotlinDebug\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlMapSerializer\n*L\n126#1:153,3\n*E\n"])
internal object YamlMapSerializer : KSerializer<YamlMap> {
   public open val descriptor: SerialDescriptor =
      BuiltinSerializersKt.MapSerializer(YamlScalarSerializer.INSTANCE, YamlNodeSerializer.INSTANCE).getDescriptor()

   public open fun serialize(encoder: Encoder, value: YamlMap) {
      YamlNodeSerializerKt.access$asYamlOutput(encoder);
      BuiltinSerializersKt.MapSerializer(YamlScalarSerializer.INSTANCE, YamlNodeSerializer.INSTANCE).serialize(encoder, value.getEntries());
   }

   public open fun deserialize(decoder: Decoder): YamlMap {
      var var10000: Decoder = decoder;
      if (decoder !is YamlMapInput) {
         var10000 = null;
      }

      val var7: YamlInput = var10000 as YamlMapInput;
      if (var10000 as YamlMapInput == null) {
         throw new IllegalStateException(
            ("This serializer can be used only with Yaml format. Expected Decoder to be ${(YamlMapInput::class).getSimpleName()}, got ${decoder.getClass()::class}")
               .toString()
         );
      } else {
         val var8: YamlNode = (var7 as YamlMapInput).getNode();
         return var8 as YamlMap;
      }
   }
}
