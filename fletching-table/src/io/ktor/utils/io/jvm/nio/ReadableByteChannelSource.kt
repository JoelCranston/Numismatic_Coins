package io.ktor.utils.io.jvm.nio

import java.nio.ByteBuffer
import java.nio.channels.ReadableByteChannel
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.RawSource
import kotlinx.io.Segment
import kotlinx.io.SegmentKt
import kotlinx.io.unsafe.UnsafeBufferOperations

@SourceDebugExtension(["SMAP\nReading.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Reading.kt\nio/ktor/utils/io/jvm/nio/ReadableByteChannelSource\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n*L\n1#1,78:1\n195#2,28:79\n*S KotlinDebug\n*F\n+ 1 Reading.kt\nio/ktor/utils/io/jvm/nio/ReadableByteChannelSource\n*L\n62#1:79,28\n*E\n"])
private open class ReadableByteChannelSource(channel: ReadableByteChannel) : RawSource {
   private final val channel: ReadableByteChannel

   init {
      this.channel = channel;
   }

   public override fun readAtMostTo(sink: Buffer, byteCount: Long): Long {
      if (byteCount <= 0L) {
         return 0L;
      } else {
         val actualByteCount: Int = (int)Math.min(byteCount, 2147483647L);
         val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val `tail$iv`: Segment = sink.writableSegment(1);
         val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
         val var10001: Int = `tail$iv`.getLimit();
         val var20: Int = this.channel.read(ByteBuffer.wrap(`data$iv`, var10001, Math.min(actualByteCount, `data$iv`.length - var10001)));
         val `bytesWritten$iv`: Int = Math.max(var20, 0);
         if (`bytesWritten$iv` == 1) {
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

         return var20;
      }
   }

   public override fun close() {
      this.channel.close();
   }

   public override fun toString(): String {
      return "ReadableByteChannelSource(${this.channel})";
   }
}
