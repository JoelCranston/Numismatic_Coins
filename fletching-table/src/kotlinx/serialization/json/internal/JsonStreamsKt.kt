package kotlinx.serialization.json.internal

import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.json.DecodeSequenceMode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.internal.JsonStreamsKt.decodeToSequenceByReader..inlined.Sequence.1
import kotlinx.serialization.modules.SerializersModule

private const val SINGLE_CHAR_MAX_CODEPOINT: Int = 65535
private const val HIGH_SURROGATE_HEADER: Int = 55232
private const val LOW_SURROGATE_HEADER: Int = 56320

@JsonFriendModuleApi
public fun <T> encodeByWriter(json: Json, writer: InternalJsonWriter, serializer: SerializationStrategy<T>, value: T) {
   new StreamingJsonEncoder(writer, json, WriteMode.OBJ, new JsonEncoder[WriteMode.getEntries().size()]).encodeSerializableValue(serializer, value);
}

@JsonFriendModuleApi
public fun <T> decodeByReader(json: Json, deserializer: DeserializationStrategy<T>, reader: InternalJsonReader): T {
   label14: {
      val lexer: ReaderJsonLexer = ReaderJsonLexerKt.ReaderJsonLexer$default(json, reader, null, 4, null);

      try {
         val result: Any = new StreamingJsonDecoder(json, WriteMode.OBJ, lexer, deserializer.getDescriptor(), null).decodeSerializableValue(deserializer);
         lexer.expectEof();
      } catch (var7: java.lang.Throwable) {
         lexer.release();
      }

      lexer.release();
   }
}

@JsonFriendModuleApi
@ExperimentalSerializationApi
public fun <T> decodeToSequenceByReader(
   json: Json,
   reader: InternalJsonReader,
   deserializer: DeserializationStrategy<T>,
   format: DecodeSequenceMode = DecodeSequenceMode.AUTO_DETECT
): Sequence<T> {
   return SequencesKt.constrainOnce(
      new 1(JsonIteratorKt.JsonIterator(format, json, ReaderJsonLexerKt.ReaderJsonLexer(json, reader, new char[16384]), deserializer))
   );
}

@JvmSynthetic
fun `decodeToSequenceByReader$default`(var0: Json, var1: InternalJsonReader, var2: DeserializationStrategy, var3: DecodeSequenceMode, var4: Int, var5: Any): Sequence {
   if ((var4 and 8) != 0) {
      var3 = DecodeSequenceMode.AUTO_DETECT;
   }

   return decodeToSequenceByReader(var0, var1, var2, var3);
}

@JsonFriendModuleApi
@ExperimentalSerializationApi
@JvmSynthetic
public inline fun <reified T> decodeToSequenceByReader(json: Json, reader: InternalJsonReader, format: DecodeSequenceMode = DecodeSequenceMode.AUTO_DETECT): Sequence<
      T
   > {
   val var4: SerializersModule = json.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (Sequence<T>)decodeToSequenceByReader(json, reader, SerializersKt.serializer(var4, null), format);
}

@JvmSynthetic
fun `decodeToSequenceByReader$default`(json: Json, reader: InternalJsonReader, format: DecodeSequenceMode, `$i$f$decodeToSequenceByReader`: Int, var4: Any): Sequence {
   if ((`$i$f$decodeToSequenceByReader` and 4) != 0) {
      format = DecodeSequenceMode.AUTO_DETECT;
   }

   var4 = json.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return decodeToSequenceByReader(json, reader, SerializersKt.serializer(var4, null), format);
}
