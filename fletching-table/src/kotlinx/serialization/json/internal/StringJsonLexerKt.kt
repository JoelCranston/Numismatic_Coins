package kotlinx.serialization.json.internal

import kotlinx.serialization.json.Json

internal fun StringJsonLexer(json: Json, source: String): StringJsonLexer {
   return if (!json.getConfiguration().getAllowComments()) new StringJsonLexer(source) else new StringJsonLexerWithComments(source);
}
