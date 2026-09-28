@file:SourceDebugExtension(["SMAP\nChunkBufferJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChunkBufferJvm.kt\nio/ktor/utils/io/core/internal/ChunkBufferJvmKt\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,31:1\n195#2,28:32\n99#2:60\n100#2,8:62\n1#3:61\n*S KotlinDebug\n*F\n+ 1 ChunkBufferJvm.kt\nio/ktor/utils/io/core/internal/ChunkBufferJvmKt\n*L\n14#1:32,28\n23#1:60\n23#1:62,8\n23#1:61\n*E\n"])

package io.ktor.utils.io.core.internal

import java.nio.ByteBuffer
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Segment
import kotlinx.io.SegmentKt
import kotlinx.io.unsafe.UnsafeBufferOperations

public fun Buffer.writeDirect(min: Int, block: (ByteBuffer) -> Unit) {
   val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
   val `buffer$iv`: Buffer = `$this$writeDirect`.getBuffer();
   val `tail$iv`: Segment = `buffer$iv`.writableSegment(min);
   val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
   val var10001: Int = `tail$iv`.getLimit();
   val buffer: ByteBuffer = ByteBuffer.wrap(`data$iv`, var10001, `data$iv`.length - var10001);
   block.invoke(buffer);
   val `bytesWritten$iv`: Int = buffer.position() - var10001;
   if (`bytesWritten$iv` == min) {
      `tail$iv`.writeBackData(`data$iv`, `bytesWritten$iv`);
      `tail$iv`.setLimit(`tail$iv`.getLimit() + `bytesWritten$iv`);
      `buffer$iv`.setSizeMut(`buffer$iv`.getSizeMut() + (long)`bytesWritten$iv`);
   } else {
      if (0 > `bytesWritten$iv` || `bytesWritten$iv` > `tail$iv`.getRemainingCapacity()) {
         throw new IllegalStateException(
            ("Invalid number of bytes written: $`bytesWritten$iv`. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString()
         );
      }

      if (`bytesWritten$iv` != 0) {
         `tail$iv`.writeBackData(`data$iv`, `bytesWritten$iv`);
         `tail$iv`.setLimit(`tail$iv`.getLimit() + `bytesWritten$iv`);
         `buffer$iv`.setSizeMut(`buffer$iv`.getSizeMut() + (long)`bytesWritten$iv`);
      } else if (SegmentKt.isEmpty(`tail$iv`)) {
         `buffer$iv`.recycleTail();
      }
   }
}

public fun Buffer.readDirect(block: (ByteBuffer) -> Unit) {
   val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
   val `buffer$iv`: Buffer = `$this$readDirect`.getBuffer();
   if (`buffer$iv`.exhausted()) {
      throw new IllegalArgumentException("Buffer is empty".toString());
   } else {
      val var10000: Segment = `buffer$iv`.getHead();
      val var14: ByteArray = var10000.dataAsByteArray(true);
      val start: Int = var10000.getPos();
      val wrap: ByteBuffer = ByteBuffer.wrap(var14, start, var10000.getLimit() - start);
      block.invoke(wrap);
      val consumed: Int = wrap.position() - start;
      if (consumed != 0) {
         if (consumed < 0) {
            throw new IllegalStateException("Returned negative read bytes count");
         }

         if (consumed > var10000.getSize()) {
            throw new IllegalStateException("Returned too many bytes");
         }

         `buffer$iv`.skip((long)consumed);
      }
   }
}
