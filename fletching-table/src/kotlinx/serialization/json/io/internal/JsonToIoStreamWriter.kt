package kotlinx.serialization.json.io.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Sink
import kotlinx.io.Utf8Kt
import kotlinx.serialization.json.internal.InternalJsonWriter
import kotlinx.serialization.json.internal.StringOpsKt

@SourceDebugExtension(["SMAP\nIoJsonStreams.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IoJsonStreams.kt\nkotlinx/serialization/json/io/internal/JsonToIoStreamWriter\n+ 2 JsonStreams.kt\nkotlinx/serialization/json/internal/InternalJsonWriter$Companion\n*L\n1#1,41:1\n20#2,12:42\n*S KotlinDebug\n*F\n+ 1 IoJsonStreams.kt\nkotlinx/serialization/json/io/internal/JsonToIoStreamWriter\n*L\n28#1:42,12\n*E\n"])
internal class JsonToIoStreamWriter(sink: Sink) : InternalJsonWriter {
   private final val sink: Sink

   init {
      this.sink = sink;
   }

   public override fun writeLong(value: Long) {
      this.write(java.lang.String.valueOf(value));
   }

   public override fun writeChar(char: Char) {
      Utf8Kt.writeCodePointValue(this.sink, var1);
   }

   public override fun write(text: String) {
      Utf8Kt.writeString$default(this.sink, text, 0, 0, 6, null);
   }

   public override fun writeQuoted(text: String) {
      Utf8Kt.writeCodePointValue(this.sink, 34);
      val `this_$iv`: InternalJsonWriter.Companion = InternalJsonWriter.Companion;
      val `text$iv`: java.lang.String = text;
      var `lastPos$iv`: Int = 0;
      var `i$iv`: Int = 0;

      for (int var7 = text.length(); i$iv < var7; i$iv++) {
         val `c$iv`: Int = `text$iv`.charAt(`i$iv`);
         if (`c$iv` < StringOpsKt.getESCAPE_STRINGS().length && StringOpsKt.getESCAPE_STRINGS()[`c$iv`] != null) {
            Utf8Kt.writeString(this.sink, `text$iv`, `lastPos$iv`, `i$iv`);
            val var10000: java.lang.String = StringOpsKt.getESCAPE_STRINGS()[`c$iv`];
            Utf8Kt.writeString(this.sink, var10000, 0, var10000.length());
            `lastPos$iv` = `i$iv` + 1;
         }
      }

      Utf8Kt.writeString(this.sink, `text$iv`, `lastPos$iv`, `text$iv`.length());
      Utf8Kt.writeCodePointValue(this.sink, 34);
   }

   public override fun release() {
   }
}
