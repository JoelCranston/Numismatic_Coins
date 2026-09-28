@file:SourceDebugExtension(["SMAP\nUnsafeBufferOperationsJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnsafeBufferOperationsJvm.kt\nkotlinx/io/unsafe/UnsafeBufferOperationsJvmKt\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,186:1\n99#2:187\n100#2,8:189\n195#2,28:197\n1#3:188\n*S KotlinDebug\n*F\n+ 1 UnsafeBufferOperationsJvm.kt\nkotlinx/io/unsafe/UnsafeBufferOperationsJvmKt\n*L\n50#1:187\n50#1:189,8\n100#1:197,28\n50#1:188\n*E\n"])

package kotlinx.io.unsafe

import java.nio.ByteBuffer
import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Segment
import kotlinx.io.SegmentKt
import kotlinx.io.UnsafeIoApi

@UnsafeIoApi
public inline fun UnsafeBufferOperations.readFromHead(buffer: Buffer, readAction: (ByteBuffer) -> Unit): Int {
   contract {
      callsInPlace(readAction, InvocationKind.EXACTLY_ONCE)
   }

   if (buffer.exhausted()) {
      throw new IllegalArgumentException("Buffer is empty".toString());
   } else {
      val var10000: Segment = buffer.getHead();
      val var15: ByteArray = var10000.dataAsByteArray(true);
      val pos: Int = var10000.getPos();
      val bb: ByteBuffer = ByteBuffer.wrap(var15, pos, var10000.getLimit() - pos).slice().asReadOnlyBuffer();
      readAction.invoke(bb);
      val `bytesRead$iv`: Int = bb.position();
      if (`bytesRead$iv` != 0) {
         if (`bytesRead$iv` < 0) {
            throw new IllegalStateException("Returned negative read bytes count");
         }

         if (`bytesRead$iv` > var10000.getSize()) {
            throw new IllegalStateException("Returned too many bytes");
         }

         buffer.skip((long)`bytesRead$iv`);
      }

      return `bytesRead$iv`;
   }
}

@UnsafeIoApi
public inline fun UnsafeBufferOperations.writeToTail(buffer: Buffer, minimumCapacity: Int, writeAction: (ByteBuffer) -> Unit): Int {
   contract {
      callsInPlace(writeAction, InvocationKind.EXACTLY_ONCE)
   }

   val `tail$iv`: Segment = buffer.writableSegment(minimumCapacity);
   val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
   val var10001: Int = `tail$iv`.getLimit();
   val bb: ByteBuffer = ByteBuffer.wrap(`data$iv`, var10001, `data$iv`.length - var10001).slice();
   writeAction.invoke(bb);
   val `bytesWritten$iv`: Int = bb.position();
   val var10000: Int;
   if (`bytesWritten$iv` == minimumCapacity) {
      `tail$iv`.writeBackData(`data$iv`, `bytesWritten$iv`);
      `tail$iv`.setLimit(`tail$iv`.getLimit() + `bytesWritten$iv`);
      buffer.setSizeMut(buffer.getSizeMut() + (long)`bytesWritten$iv`);
      var10000 = `bytesWritten$iv`;
   } else {
      if (0 > `bytesWritten$iv` || `bytesWritten$iv` > `tail$iv`.getRemainingCapacity()) {
         throw new IllegalStateException(
            ("Invalid number of bytes written: $`bytesWritten$iv`. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString()
         );
      }

      if (`bytesWritten$iv` != 0) {
         `tail$iv`.writeBackData(`data$iv`, `bytesWritten$iv`);
         `tail$iv`.setLimit(`tail$iv`.getLimit() + `bytesWritten$iv`);
         buffer.setSizeMut(buffer.getSizeMut() + (long)`bytesWritten$iv`);
         var10000 = `bytesWritten$iv`;
      } else {
         if (SegmentKt.isEmpty(`tail$iv`)) {
            buffer.recycleTail();
         }

         var10000 = `bytesWritten$iv`;
      }
   }

   return var10000;
}

@UnsafeIoApi
public inline fun UnsafeBufferOperations.readBulk(buffer: Buffer, iovec: Array<ByteBuffer?>, readAction: (Array<ByteBuffer?>, Int) -> Long): Long {
   contract {
      callsInPlace(readAction, InvocationKind.EXACTLY_ONCE)
   }

   var var10000: Segment = buffer.getHead();
   if (var10000 == null) {
      throw new IllegalArgumentException("buffer is empty.");
   } else if (iovec.length == 0) {
      throw new IllegalArgumentException("iovec is empty.");
   } else {
      var currentSegment: Segment = var10000;
      var idx: Int = 0;
      var capacity: Long = 0L;

      do {
         val bytesRead: Int = currentSegment.getPos();
         val len: Int = currentSegment.getLimit() - bytesRead;
         iovec[idx++] = ByteBuffer.wrap(currentSegment.dataAsByteArray(true), bytesRead, len).slice().asReadOnlyBuffer();
         capacity += len;
         var10000 = currentSegment.getNext();
         if (var10000 == null) {
            break;
         }

         currentSegment = var10000;
      } while (idx < iovec.length);

      val var13: Long = (readAction.invoke(iovec, idx) as java.lang.Number).longValue();
      if (var13 != 0L) {
         if (var13 < 0L || var13 > capacity) {
            throw new IllegalStateException("readAction should return a value in range [0, $capacity], but returned: $var13");
         }

         buffer.skip(var13);
      }

      return var13;
   }
}
