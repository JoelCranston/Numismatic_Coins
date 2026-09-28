package kotlinx.serialization.json

import java.io.InputStream
import java.io.OutputStream
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.json.internal.JavaStreamSerialReader
import kotlinx.serialization.json.internal.JsonStreamsKt
import kotlinx.serialization.json.internal.JsonToJavaStreamWriter
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
public fun <T> Json.encodeToStream(serializer: SerializationStrategy<T>, value: T, stream: OutputStream) {
   label15: {
      val writer: JsonToJavaStreamWriter = new JsonToJavaStreamWriter(stream);

      try {
         JsonStreamsKt.encodeByWriter(`$this$encodeToStream`, writer, serializer, value);
      } catch (var6: java.lang.Throwable) {
         writer.release();
      }

      writer.release();
   }
}

@ExperimentalSerializationApi
@JvmSynthetic
public inline fun <reified T> Json.encodeToStream(value: T, stream: OutputStream) {
   val var4: SerializersModule = `$this$encodeToStream`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   encodeToStream(`$this$encodeToStream`, SerializersKt.serializer(var4, null), value, stream);
}

@ExperimentalSerializationApi
public fun <T> Json.decodeFromStream(deserializer: DeserializationStrategy<T>, stream: InputStream): T {
   label14: {
      val reader: JavaStreamSerialReader = new JavaStreamSerialReader(stream);

      try {
         val var4: Any = JsonStreamsKt.decodeByReader(`$this$decodeFromStream`, deserializer, reader);
      } catch (var6: java.lang.Throwable) {
         reader.release();
      }

      reader.release();
   }
}

@ExperimentalSerializationApi
@JvmSynthetic
public inline fun <reified T> Json.decodeFromStream(stream: InputStream): T {
   val var3: SerializersModule = `$this$decodeFromStream`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)decodeFromStream(`$this$decodeFromStream`, SerializersKt.serializer(var3, null), stream);
}

@ExperimentalSerializationApi
public fun <T> Json.decodeToSequence(stream: InputStream, deserializer: DeserializationStrategy<T>, format: DecodeSequenceMode = DecodeSequenceMode.AUTO_DETECT): Sequence<
      T
   > {
   return JsonStreamsKt.decodeToSequenceByReader(`$this$decodeToSequence`, new JavaStreamSerialReader(stream), deserializer, format);
}

@JvmSynthetic
fun `decodeToSequence$default`(var0: Json, var1: InputStream, var2: DeserializationStrategy, var3: DecodeSequenceMode, var4: Int, var5: Any): Sequence {
   if ((var4 and 4) != 0) {
      var3 = DecodeSequenceMode.AUTO_DETECT;
   }

   return decodeToSequence(var0, var1, var2, var3);
}

@ExperimentalSerializationApi
@JvmSynthetic
public inline fun <reified T> Json.decodeToSequence(stream: InputStream, format: DecodeSequenceMode = DecodeSequenceMode.AUTO_DETECT): Sequence<T> {
   val var4: SerializersModule = `$this$decodeToSequence`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (Sequence<T>)decodeToSequence(`$this$decodeToSequence`, stream, SerializersKt.serializer(var4, null), format);
}

@JvmSynthetic
fun Json.`decodeToSequence$default`(stream: InputStream, format: DecodeSequenceMode, `$i$f$decodeToSequence`: Int, var4: Any): Sequence {
   if ((`$i$f$decodeToSequence` and 2) != 0) {
      format = DecodeSequenceMode.AUTO_DETECT;
   }

   var4 = `$this$decodeToSequence_u24default`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return decodeToSequence(`$this$decodeToSequence_u24default`, stream, SerializersKt.serializer(var4, null), format);
}
