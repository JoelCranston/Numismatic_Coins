package kotlinx.io

import java.io.IOException
import java.io.InputStream
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.unsafe.UnsafeBufferOperations

@SourceDebugExtension(["SMAP\nJvmCore.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JvmCore.kt\nkotlinx/io/InputStreamSource\n+ 2 -Util.kt\nkotlinx/io/_UtilKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n*L\n1#1,112:1\n52#2:113\n53#2:115\n107#2:122\n1#3:114\n195#4,6:116\n203#4,20:123\n*S KotlinDebug\n*F\n+ 1 JvmCore.kt\nkotlinx/io/InputStreamSource\n*L\n80#1:113\n80#1:115\n84#1:122\n80#1:114\n83#1:116,6\n83#1:123,20\n*E\n"])
private open class InputStreamSource(input: InputStream) : RawSource {
   private final val input: InputStream

   init {
      this.input = input;
   }

   public override fun readAtMostTo(sink: Buffer, byteCount: Long): Long {
      if (byteCount == 0L) {
         return 0L;
      } else if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount ($byteCount) < 0").toString());
      } else {
         try {
            val e: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            val `tail$iv`: Segment = sink.writableSegment(1);
            val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
            val var10001: Int = `tail$iv`.getLimit();
            val var25: Long = this.input.read(`data$iv`, var10001, (int)Math.min(byteCount, (long)(`data$iv`.length - var10001)));
            val `bytesWritten$iv`: Int = if (var25 == -1L) 0 else (int)var25;
            if ((if (var25 == -1L) 0 else (int)var25) == 1) {
               `tail$iv`.writeBackData(`data$iv`, `bytesWritten$iv`);
               `tail$iv`.setLimit(`tail$iv`.getLimit() + `bytesWritten$iv`);
               sink.setSizeMut(sink.getSizeMut() + (long)`bytesWritten$iv`);
            } else {
               if (0 > `bytesWritten$iv` || `bytesWritten$iv` > `tail$iv`.getRemainingCapacity()) {
                  throw new IllegalStateException(
                     ("Invalid number of bytes written: $`bytesWritten$iv`. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString()
                  );
               }

               if (`bytesWritten$iv` != 0) {
                  `tail$iv`.writeBackData(`data$iv`, `bytesWritten$iv`);
                  `tail$iv`.setLimit(`tail$iv`.getLimit() + `bytesWritten$iv`);
                  sink.setSizeMut(sink.getSizeMut() + (long)`bytesWritten$iv`);
               } else if (SegmentKt.isEmpty(`tail$iv`)) {
                  sink.recycleTail();
               }
            }

            return var25;
         } catch (var21: AssertionError) {
            if (JvmCoreKt.isAndroidGetsocknameError(var21)) {
               throw new IOException(var21);
            } else {
               throw var21;
            }
         }
      }
   }

   public override fun close() {
      this.input.close();
   }

   public override fun toString(): String {
      return "RawSource(${this.input})";
   }
}
