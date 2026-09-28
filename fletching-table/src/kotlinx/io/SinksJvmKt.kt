@file:SourceDebugExtension(["SMAP\nSinksJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SinksJvm.kt\nkotlinx/io/SinksJvmKt\n+ 2 -Util.kt\nkotlinx/io/_UtilKt\n*L\n1#1,133:1\n38#2:134\n*S KotlinDebug\n*F\n+ 1 SinksJvm.kt\nkotlinx/io/SinksJvmKt\n*L\n46#1:134\n*E\n"])

package kotlinx.io

import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.channels.WritableByteChannel
import java.nio.charset.Charset
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.SinksJvmKt.asOutputStream.isClosed.1

public fun Sink.writeString(string: String, charset: Charset, startIndex: Int = 0, endIndex: Int = string.length()) {
   _UtilKt.checkBounds((long)string.length(), (long)startIndex, (long)endIndex);
   if (charset == Charsets.UTF_8) {
      Utf8Kt.writeString(`$this$writeString`, string, startIndex, endIndex);
   } else {
      val var10000: java.lang.String = string.substring(startIndex, endIndex);
      val var8: ByteArray = var10000.getBytes(charset);
      `$this$writeString`.write(var8, 0, var8.length);
   }
}

@JvmSynthetic
fun `writeString$default`(var0: Sink, var1: java.lang.String, var2: Charset, var3: Int, var4: Int, var5: Int, var6: Any) {
   if ((var5 and 4) != 0) {
      var3 = 0;
   }

   if ((var5 and 8) != 0) {
      var4 = var1.length();
   }

   writeString(var0, var1, var2, var3, var4);
}

public fun Sink.asOutputStream(): OutputStream {
   val var10000: Function0;
   if (`$this$asOutputStream` is RealSink) {
      var10000 = new 1(`$this$asOutputStream`);
   } else {
      if (`$this$asOutputStream` !is Buffer) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = SinksJvmKt::asOutputStream$lambda$0;
   }

   return new kotlinx.io.SinksJvmKt.asOutputStream.1(var10000, `$this$asOutputStream`);
}

public fun Sink.write(source: ByteBuffer): Int {
   val sizeBefore: Long = `$this$write`.getBuffer().getSize();
   BuffersJvmKt.transferFrom(`$this$write`.getBuffer(), source);
   val bytesRead: Long = `$this$write`.getBuffer().getSize() - sizeBefore;
   `$this$write`.hintEmit();
   return (int)bytesRead;
}

public fun Sink.asByteChannel(): WritableByteChannel {
   val var10000: Function0;
   if (`$this$asByteChannel` is RealSink) {
      var10000 = new kotlinx.io.SinksJvmKt.asByteChannel.isClosed.1(`$this$asByteChannel`);
   } else {
      if (`$this$asByteChannel` !is Buffer) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = SinksJvmKt::asByteChannel$lambda$1;
   }

   return new kotlinx.io.SinksJvmKt.asByteChannel.1(`$this$asByteChannel`, var10000);
}

fun `asOutputStream$lambda$0`(): Boolean {
   return false;
}

fun `asByteChannel$lambda$1`(): Boolean {
   return false;
}
