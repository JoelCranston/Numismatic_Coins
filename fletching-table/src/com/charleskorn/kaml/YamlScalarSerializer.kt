package com.charleskorn.kaml

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@SourceDebugExtension(["SMAP\nYamlNodeSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlScalarSerializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlNodeSerializerKt\n*L\n1#1,152:1\n1#2:153\n145#3,3:154\n*S KotlinDebug\n*F\n+ 1 YamlNodeSerializer.kt\ncom/charleskorn/kaml/YamlScalarSerializer\n*L\n77#1:154,3\n*E\n"])
internal object YamlScalarSerializer : KSerializer<YamlScalar> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.PrimitiveSerialDescriptor("com.charleskorn.kaml.YamlScalar", PrimitiveKind.STRING.INSTANCE)

   public open fun serialize(encoder: Encoder, value: YamlScalar) {
      YamlNodeSerializerKt.access$asYamlOutput(encoder);
      val var10000: java.lang.Boolean = value.toBooleanOrNull$kaml();
      if (var10000 != null) {
         encoder.encodeBoolean(var10000);
      } else {
         val var11: java.lang.Long = value.toLongOrNull$kaml();
         if (var11 != null) {
            encoder.encodeLong(var11.longValue());
         } else {
            val var12: java.lang.Double = value.toDoubleOrNull$kaml();
            if (var12 != null) {
               encoder.encodeDouble(var12.doubleValue());
            } else {
               val var13: Character = value.toCharOrNull$kaml();
               if (var13 != null) {
                  encoder.encodeChar(var13);
               } else {
                  encoder.encodeString(value.getContent());
               }
            }
         }
      }
   }

   public open fun deserialize(decoder: Decoder): YamlScalar {
      var var10000: Decoder = decoder;
      if (decoder !is YamlScalarInput) {
         var10000 = null;
      }

      val var7: YamlInput = var10000 as YamlScalarInput;
      if (var10000 as YamlScalarInput == null) {
         throw new IllegalStateException(
            ("This serializer can be used only with Yaml format. Expected Decoder to be ${(YamlScalarInput::class).getSimpleName()}, got ${decoder.getClass()::class}")
               .toString()
         );
      } else {
         return (var7 as YamlScalarInput).getScalar();
      }
   }
}
