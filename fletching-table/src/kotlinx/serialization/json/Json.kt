package kotlinx.serialization.json

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.internal.DescriptorSchemaCache
import kotlinx.serialization.json.internal.JsonStreamsKt
import kotlinx.serialization.json.internal.JsonToStringWriter
import kotlinx.serialization.json.internal.StreamingJsonDecoder
import kotlinx.serialization.json.internal.StringJsonLexer
import kotlinx.serialization.json.internal.StringJsonLexerKt
import kotlinx.serialization.json.internal.TreeJsonDecoderKt
import kotlinx.serialization.json.internal.TreeJsonEncoderKt
import kotlinx.serialization.json.internal.WriteMode
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuildersKt

public sealed class Json protected constructor(configuration: JsonConfiguration, serializersModule: SerializersModule) : StringFormat {
   public final val configuration: JsonConfiguration
   public open val serializersModule: SerializersModule

   @Deprecated(
      message = "Should not be accessed directly, use Json.schemaCache accessor instead",
      replaceWith = @ReplaceWith(
         expression = "schemaCache",
         imports = {}
      ),
      level = DeprecationLevel.ERROR
   )
   internal final val _schemaCache: DescriptorSchemaCache

   init {
      this.configuration = configuration;
      this.serializersModule = serializersModule;
      this._schemaCache = new DescriptorSchemaCache();
   }

   public override fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String {
      label14: {
         val result: JsonToStringWriter = new JsonToStringWriter();

         try {
            JsonStreamsKt.encodeByWriter(this, result, serializer, value);
            val var4: java.lang.String = result.toString();
         } catch (var6: java.lang.Throwable) {
            result.release();
         }

         result.release();
      }
   }

   public override fun <T> decodeFromString(deserializer: DeserializationStrategy<T>, string: String): T {
      val lexer: StringJsonLexer = StringJsonLexerKt.StringJsonLexer(this, string);
      val result: Any = new StreamingJsonDecoder(this, WriteMode.OBJ, lexer, deserializer.getDescriptor(), null).decodeSerializableValue(deserializer);
      lexer.expectEof();
      return (T)result;
   }

   public fun <T> encodeToJsonElement(serializer: SerializationStrategy<T>, value: T): JsonElement {
      return TreeJsonEncoderKt.writeJson(this, value, serializer);
   }

   public fun <T> decodeFromJsonElement(deserializer: DeserializationStrategy<T>, element: JsonElement): T {
      return (T)TreeJsonDecoderKt.readJson(this, element, deserializer);
   }

   public fun parseToJsonElement(string: String): JsonElement {
      return this.decodeFromString(JsonElementSerializer.INSTANCE, string);
   }

   public companion object Default : Json(
         new JsonConfiguration(false, false, false, false, false, false, null, false, false, null, false, false, null, false, false, false, null, 131071, null),
         SerializersModuleBuildersKt.EmptySerializersModule()
      )
}
