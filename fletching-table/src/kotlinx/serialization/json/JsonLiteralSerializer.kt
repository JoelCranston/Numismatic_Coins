package kotlinx.serialization.json

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.internal.JsonExceptionsKt

@SourceDebugExtension(["SMAP\nJsonElementSerializers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonElementSerializers.kt\nkotlinx/serialization/json/JsonLiteralSerializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,236:1\n1#2:237\n*E\n"])
private object JsonLiteralSerializer : KSerializer<JsonLiteral> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.PrimitiveSerialDescriptor("kotlinx.serialization.json.JsonLiteral", PrimitiveKind.STRING.INSTANCE)

   public open fun serialize(encoder: Encoder, value: JsonLiteral) {
      JsonElementSerializersKt.access$verify(encoder);
      if (value.isString()) {
         encoder.encodeString(value.getContent());
      } else if (value.getCoerceToInlineType$kotlinx_serialization_json() != null) {
         encoder.encodeInline(value.getCoerceToInlineType$kotlinx_serialization_json()).encodeString(value.getContent());
      } else {
         val var3: java.lang.Long = StringsKt.toLongOrNull(value.getContent());
         if (var3 != null) {
            encoder.encodeLong(var3.longValue());
         } else {
            val var7: ULong = UStringsKt.toULongOrNull(value.getContent());
            if (var7 != null) {
               encoder.encodeInline(BuiltinSerializersKt.serializer(ULong.Companion).getDescriptor()).encodeLong(var7.unbox-impl());
            } else {
               val var8: java.lang.Double = StringsKt.toDoubleOrNull(value.getContent());
               if (var8 != null) {
                  encoder.encodeDouble(var8.doubleValue());
               } else {
                  val var9: java.lang.Boolean = StringsKt.toBooleanStrictOrNull(value.getContent());
                  if (var9 != null) {
                     encoder.encodeBoolean(var9);
                  } else {
                     encoder.encodeString(value.getContent());
                  }
               }
            }
         }
      }
   }

   public open fun deserialize(decoder: Decoder): JsonLiteral {
      val result: JsonElement = JsonElementSerializersKt.asJsonDecoder(decoder).decodeJsonElement();
      if (result !is JsonLiteral) {
         throw JsonExceptionsKt.JsonDecodingException(-1, "Unexpected JSON element, expected JsonLiteral, had ${result.getClass()::class}", result.toString());
      } else {
         return result as JsonLiteral;
      }
   }
}
