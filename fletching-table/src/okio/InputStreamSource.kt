package okio

import java.io.IOException
import java.io.InputStream
import kotlin.jvm.internal.SourceDebugExtension
import okio.internal._JavaIoKt

@SourceDebugExtension(["SMAP\nJvmOkio.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JvmOkio.kt\nokio/InputStreamSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,234:1\n1#2:235\n85#3:236\n*S KotlinDebug\n*F\n+ 1 JvmOkio.kt\nokio/InputStreamSource\n*L\n93#1:236\n*E\n"])
private open class InputStreamSource(input: InputStream, timeout: Timeout) : Source {
   private final val input: InputStream
   private final val timeout: Timeout

   init {
      this.input = input;
      this.timeout = timeout;
   }

   public override fun read(sink: Buffer, byteCount: Long): Long {
      if (byteCount == 0L) {
         return 0L;
      } else if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
      } else {
         try {
            this.timeout.throwIfReached();
            val tail: Segment = sink.writableSegment$okio(1);
            val bytesRead: Int = this.input.read(tail.data, tail.limit, (int)Math.min(byteCount, (long)(8192 - tail.limit)));
            if (bytesRead == -1) {
               if (tail.pos == tail.limit) {
                  sink.head = tail.pop();
                  SegmentPool.recycle(tail);
               }

               return -1L;
            } else {
               tail.limit += bytesRead;
               sink.setSize$okio(sink.size() + (long)bytesRead);
               return bytesRead;
            }
         } catch (var10: AssertionError) {
            if (_JavaIoKt.isAndroidGetsocknameError(var10)) {
               throw new IOException(var10);
            } else {
               throw var10;
            }
         }
      }
   }

   public override fun close() {
      this.input.close();
   }

   public override fun timeout(): Timeout {
      return this.timeout;
   }

   public override fun toString(): String {
      return "source(${this.input})";
   }
}
