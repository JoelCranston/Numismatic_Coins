package kotlinx.serialization.json.internal

import kotlinx.serialization.json.Json

internal const val BATCH_SIZE: Int = 16384
private const val DEFAULT_THRESHOLD: Int = 128

internal fun ReaderJsonLexer(json: Json, reader: InternalJsonReader, buffer: CharArray = CharArrayPoolBatchSize.INSTANCE.take()): ReaderJsonLexer {
   return if (!json.getConfiguration().getAllowComments()) new ReaderJsonLexer(reader, buffer) else new ReaderJsonLexerWithComments(reader, buffer);
}

@JvmSynthetic
fun `ReaderJsonLexer$default`(var0: Json, var1: InternalJsonReader, var2: CharArray, var3: Int, var4: Any): ReaderJsonLexer {
   if ((var3 and 4) != 0) {
      var2 = CharArrayPoolBatchSize.INSTANCE.take();
   }

   return ReaderJsonLexer(var0, var1, var2);
}
