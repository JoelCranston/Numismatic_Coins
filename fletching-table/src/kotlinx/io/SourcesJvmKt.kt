@file:SourceDebugExtension(["SMAP\nSourcesJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SourcesJvm.kt\nkotlinx/io/SourcesJvmKt\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,177:1\n99#2:178\n100#2,8:180\n1#3:179\n*S KotlinDebug\n*F\n+ 1 SourcesJvm.kt\nkotlinx/io/SourcesJvmKt\n*L\n41#1:178\n41#1:180,8\n41#1:179\n*E\n"])

package kotlinx.io

import java.io.EOFException
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.channels.ReadableByteChannel
import java.nio.charset.Charset
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.SourcesJvmKt.asInputStream.isClosed.1
import kotlinx.io.unsafe.UnsafeBufferOperations

private fun Buffer.readStringImpl(byteCount: Long, charset: Charset): String {
   if (byteCount < 0L || byteCount > 2147483647L) {
      throw new IllegalArgumentException(("byteCount ($byteCount) is not within the range [0..2147483647)").toString());
   } else if (`$this$readStringImpl`.getSize() < byteCount) {
      throw new EOFException("Buffer contains less bytes then required (byteCount: $byteCount, size: ${`$this$readStringImpl`.getSize()})");
   } else if (byteCount == 0L) {
      return "";
   } else {
      var result: Any = null;
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      if (`$this$readStringImpl`.exhausted()) {
         throw new IllegalArgumentException("Buffer is empty".toString());
      } else {
         val var10000: Segment = `$this$readStringImpl`.getHead();
         val var18: ByteArray = var10000.dataAsByteArray(true);
         val limit: Int = var10000.getLimit();
         val pos: Int = var10000.getPos();
         val var19: Int;
         if (limit - pos >= byteCount) {
            result = new java.lang.String(var18, pos, (int)byteCount, charset);
            var19 = (int)byteCount;
         } else {
            var19 = 0;
         }

         if (var19 != 0) {
            if (var19 < 0) {
               throw new IllegalStateException("Returned negative read bytes count");
            }

            if (var19 > var10000.getSize()) {
               throw new IllegalStateException("Returned too many bytes");
            }

            `$this$readStringImpl`.skip((long)var19);
         }

         return (java.lang.String)(if (result == null)
            new java.lang.String(SourcesKt.readByteArray(`$this$readStringImpl`, (int)byteCount), charset)
            else
            result);
      }
   }
}

public fun Source.readString(charset: Charset): String {
   var req: Long = 1L;

   while ($this$readString.request(req)) {
      req *= 2;
   }

   return readStringImpl(`$this$readString`.getBuffer(), `$this$readString`.getBuffer().getSize(), charset);
}

public fun Source.readString(byteCount: Long, charset: Charset): String {
   `$this$readString`.require(byteCount);
   return readStringImpl(`$this$readString`.getBuffer(), byteCount, charset);
}

public fun Source.asInputStream(): InputStream {
   val var10000: Function0;
   if (`$this$asInputStream` is RealSource) {
      var10000 = new 1(`$this$asInputStream`);
   } else {
      if (`$this$asInputStream` !is Buffer) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = SourcesJvmKt::asInputStream$lambda$2;
   }

   return new kotlinx.io.SourcesJvmKt.asInputStream.1(var10000, `$this$asInputStream`);
}

public fun Source.readAtMostTo(sink: ByteBuffer): Int {
   if (`$this$readAtMostTo`.getBuffer().getSize() == 0L) {
      `$this$readAtMostTo`.request(8192L);
      if (`$this$readAtMostTo`.getBuffer().getSize() == 0L) {
         return -1;
      }
   }

   return BuffersJvmKt.readAtMostTo(`$this$readAtMostTo`.getBuffer(), sink);
}

public fun Source.asByteChannel(): ReadableByteChannel {
   val var10000: Function0;
   if (`$this$asByteChannel` is RealSource) {
      var10000 = new kotlinx.io.SourcesJvmKt.asByteChannel.isClosed.1(`$this$asByteChannel`);
   } else {
      if (`$this$asByteChannel` !is Buffer) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = SourcesJvmKt::asByteChannel$lambda$3;
   }

   return new kotlinx.io.SourcesJvmKt.asByteChannel.1(`$this$asByteChannel`, var10000);
}

fun `asInputStream$lambda$2`(): Boolean {
   return false;
}

fun `asByteChannel$lambda$3`(): Boolean {
   return false;
}
