package kotlinx.serialization.json.internal

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@JsonFriendModuleApi
public fun <T> decodeStringToJsonTree(json: Json, deserializer: DeserializationStrategy<T>, source: String): JsonElement {
   val lexer: StringJsonLexer = StringJsonLexerKt.StringJsonLexer(json, source);
   val tree: JsonElement = new StreamingJsonDecoder(json, WriteMode.OBJ, lexer, deserializer.getDescriptor(), null).decodeJsonElement();
   lexer.expectEof();
   return tree;
}

private inline fun <T> AbstractJsonLexer.parseString(expectedType: String, block: (String) -> T): T {
   val input: java.lang.String = `$this$parseString`.consumeStringLenient();

   try {
      return (T)block.invoke(input);
   } catch (var6: IllegalArgumentException) {
      AbstractJsonLexer.fail$default(`$this$parseString`, "Failed to parse type '$expectedType' for input '$input'", 0, null, 6, null);
      throw new KotlinNothingValueException();
   }
}
