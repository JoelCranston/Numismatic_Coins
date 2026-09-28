package kotlinx.serialization.json.io

import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.json.DecodeSequenceMode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.internal.JsonStreamsKt
import kotlinx.serialization.json.io.internal.IoSerialReader
import kotlinx.serialization.json.io.internal.JsonToIoStreamWriter
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
public fun <T> Json.encodeToSink(serializer: SerializationStrategy<T>, value: T, sink: Sink) {
   label15: {
      val writer: JsonToIoStreamWriter = new JsonToIoStreamWriter(sink);

      try {
         JsonStreamsKt.encodeByWriter(`$this$encodeToSink`, writer, serializer, value);
      } catch (var6: java.lang.Throwable) {
         writer.release();
      }

      writer.release();
   }
}

@ExperimentalSerializationApi
@JvmSynthetic
public inline fun <reified T> Json.encodeToSink(value: T, sink: Sink) {
   val var4: SerializersModule = `$this$encodeToSink`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   encodeToSink(`$this$encodeToSink`, SerializersKt.serializer(var4, null), value, sink);
}

@ExperimentalSerializationApi
public fun <T> Json.decodeFromSource(deserializer: DeserializationStrategy<T>, source: Source): T {
   return (T)JsonStreamsKt.decodeByReader(`$this$decodeFromSource`, deserializer, new IoSerialReader(source));
}

@ExperimentalSerializationApi
@JvmSynthetic
public inline fun <reified T> Json.decodeFromSource(source: Source): T {
   val var3: SerializersModule = `$this$decodeFromSource`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)decodeFromSource(`$this$decodeFromSource`, SerializersKt.serializer(var3, null), source);
}

@ExperimentalSerializationApi
public fun <T> Json.decodeSourceToSequence(
   source: Source,
   deserializer: DeserializationStrategy<T>,
   format: DecodeSequenceMode = DecodeSequenceMode.AUTO_DETECT
): Sequence<T> {
   return JsonStreamsKt.decodeToSequenceByReader(`$this$decodeSourceToSequence`, new IoSerialReader(source), deserializer, format);
}

@JvmSynthetic
fun `decodeSourceToSequence$default`(var0: Json, var1: Source, var2: DeserializationStrategy, var3: DecodeSequenceMode, var4: Int, var5: Any): Sequence {
   if ((var4 and 4) != 0) {
      var3 = DecodeSequenceMode.AUTO_DETECT;
   }

   return decodeSourceToSequence(var0, var1, var2, var3);
}

@ExperimentalSerializationApi
@JvmSynthetic
public inline fun <reified T> Json.decodeSourceToSequence(source: Source, format: DecodeSequenceMode = DecodeSequenceMode.AUTO_DETECT): Sequence<T> {
   val var4: SerializersModule = `$this$decodeSourceToSequence`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (Sequence<T>)decodeSourceToSequence(`$this$decodeSourceToSequence`, source, SerializersKt.serializer(var4, null), format);
}

@JvmSynthetic
fun Json.`decodeSourceToSequence$default`(source: Source, format: DecodeSequenceMode, `$i$f$decodeSourceToSequence`: Int, var4: Any): Sequence {
   if ((`$i$f$decodeSourceToSequence` and 2) != 0) {
      format = DecodeSequenceMode.AUTO_DETECT;
   }

   var4 = `$this$decodeSourceToSequence_u24default`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return decodeSourceToSequence(`$this$decodeSourceToSequence_u24default`, source, SerializersKt.serializer(var4, null), format);
}
