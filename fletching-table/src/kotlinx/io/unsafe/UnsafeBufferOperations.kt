package kotlinx.io.unsafe

import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.AlwaysSharedCopyTracker
import kotlinx.io.Buffer
import kotlinx.io.Segment
import kotlinx.io.SegmentKt
import kotlinx.io.UnsafeIoApi
import kotlinx.io._UtilKt

@UnsafeIoApi
@SourceDebugExtension(["SMAP\nUnsafeBufferOperations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 2 -Util.kt\nkotlinx/io/_UtilKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Buffer.kt\nkotlinx/io/BufferKt\n*L\n1#1,568:1\n38#2:569\n1#3:570\n659#4,25:571\n*S KotlinDebug\n*F\n+ 1 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n*L\n43#1:569\n352#1:571,25\n*E\n"])
public object UnsafeBufferOperations {
   public final val maxSafeWriteCapacity: Int
      public final get() {
         return 8192;
      }


   public fun moveToTail(buffer: Buffer, bytes: ByteArray, startIndex: Int = 0, endIndex: Int = bytes.length) {
      _UtilKt.checkBounds((long)bytes.length, (long)startIndex, (long)endIndex);
      val var7: Segment = Segment.Companion.new$kotlinx_io_core(bytes, startIndex, endIndex, AlwaysSharedCopyTracker.INSTANCE, false);
      val var8: Segment = buffer.getTail();
      if (var8 == null) {
         buffer.setHead(var7);
         buffer.setTail(var7);
      } else {
         buffer.setTail(var8.push$kotlinx_io_core(var7));
      }

      buffer.setSizeMut(buffer.getSizeMut() + (long)(endIndex - startIndex));
   }

   public inline fun readFromHead(buffer: Buffer, readAction: (ByteArray, Int, Int) -> Int): Int {
      contract {
         callsInPlace(readAction, InvocationKind.EXACTLY_ONCE)
      }

      if (buffer.exhausted()) {
         throw new IllegalArgumentException("Buffer is empty".toString());
      } else {
         val var10000: Segment = buffer.getHead();
         val bytesRead: Int = (readAction.invoke(var10000.dataAsByteArray(true), var10000.getPos(), var10000.getLimit()) as java.lang.Number).intValue();
         if (bytesRead != 0) {
            if (bytesRead < 0) {
               throw new IllegalStateException("Returned negative read bytes count");
            }

            if (bytesRead > var10000.getSize()) {
               throw new IllegalStateException("Returned too many bytes");
            }

            buffer.skip((long)bytesRead);
         }

         return bytesRead;
      }
   }

   public inline fun readFromHead(buffer: Buffer, readAction: (SegmentReadContext, Segment) -> Int): Int {
      contract {
         callsInPlace(readAction, InvocationKind.EXACTLY_ONCE)
      }

      if (buffer.exhausted()) {
         throw new IllegalArgumentException("Buffer is empty".toString());
      } else {
         val var10000: Segment = buffer.getHead();
         val bytesRead: Int = (readAction.invoke(UnsafeBufferOperationsKt.getSegmentReadContextImpl(), var10000) as java.lang.Number).intValue();
         if (bytesRead != 0) {
            if (bytesRead < 0) {
               throw new IllegalStateException("Returned negative read bytes count");
            }

            if (bytesRead > var10000.getSize()) {
               throw new IllegalStateException("Returned too many bytes");
            }

            buffer.skip((long)bytesRead);
         }

         return bytesRead;
      }
   }

   public inline fun writeToTail(buffer: Buffer, minimumCapacity: Int, writeAction: (ByteArray, Int, Int) -> Int): Int {
      contract {
         callsInPlace(writeAction, InvocationKind.EXACTLY_ONCE)
      }

      val tail: Segment = buffer.writableSegment(minimumCapacity);
      val data: ByteArray = tail.dataAsByteArray(false);
      val bytesWritten: Int = (writeAction.invoke(data, tail.getLimit(), data.length) as java.lang.Number).intValue();
      if (bytesWritten == minimumCapacity) {
         tail.writeBackData(data, bytesWritten);
         tail.setLimit(tail.getLimit() + bytesWritten);
         buffer.setSizeMut(buffer.getSizeMut() + (long)bytesWritten);
         return bytesWritten;
      } else if (0 > bytesWritten || bytesWritten > tail.getRemainingCapacity()) {
         throw new IllegalStateException(("Invalid number of bytes written: $bytesWritten. Should be in 0..${tail.getRemainingCapacity()}").toString());
      } else if (bytesWritten != 0) {
         tail.writeBackData(data, bytesWritten);
         tail.setLimit(tail.getLimit() + bytesWritten);
         buffer.setSizeMut(buffer.getSizeMut() + (long)bytesWritten);
         return bytesWritten;
      } else {
         if (SegmentKt.isEmpty(tail)) {
            buffer.recycleTail();
         }

         return bytesWritten;
      }
   }

   public inline fun writeToTail(buffer: Buffer, minimumCapacity: Int, writeAction: (SegmentWriteContext, Segment) -> Int): Int {
      contract {
         callsInPlace(writeAction, InvocationKind.EXACTLY_ONCE)
      }

      val tail: Segment = buffer.writableSegment(minimumCapacity);
      val bytesWritten: Int = (writeAction.invoke(UnsafeBufferOperationsKt.getSegmentWriteContextImpl(), tail) as java.lang.Number).intValue();
      if (bytesWritten == minimumCapacity) {
         tail.setLimit(tail.getLimit() + bytesWritten);
         buffer.setSizeMut(buffer.getSizeMut() + (long)bytesWritten);
         return bytesWritten;
      } else if (0 > bytesWritten || bytesWritten > tail.getRemainingCapacity()) {
         throw new IllegalStateException(("Invalid number of bytes written: $bytesWritten. Should be in 0..${tail.getRemainingCapacity()}").toString());
      } else if (bytesWritten != 0) {
         tail.setLimit(tail.getLimit() + bytesWritten);
         buffer.setSizeMut(buffer.getSizeMut() + (long)bytesWritten);
         return bytesWritten;
      } else {
         if (SegmentKt.isEmpty(tail)) {
            buffer.recycleTail();
         }

         return bytesWritten;
      }
   }

   public inline fun iterate(buffer: Buffer, iterationAction: (BufferIterationContext, Segment?) -> Unit) {
      contract {
         callsInPlace(iterationAction, InvocationKind.EXACTLY_ONCE)
      }

      iterationAction.invoke(UnsafeBufferOperationsKt.getBufferIterationContextImpl(), buffer.getHead());
   }

   public inline fun iterate(buffer: Buffer, offset: Long, iterationAction: (BufferIterationContext, Segment?, Long) -> Unit) {
      contract {
         callsInPlace(iterationAction, InvocationKind.EXACTLY_ONCE)
      }

      if (offset < 0L) {
         throw new IllegalArgumentException(("Offset must be non-negative: $offset").toString());
      } else if (offset >= buffer.getSize()) {
         throw new IndexOutOfBoundsException("Offset should be less than buffer's size (${buffer.getSize()}): $offset");
      } else {
         if (buffer.getHead() == null) {
            iterationAction.invoke(UnsafeBufferOperationsKt.getBufferIterationContextImpl(), null, -1L);
         } else if (buffer.getSize() - offset < offset) {
            var `s$iv`: Segment = buffer.getTail();

            var `offset$iv`: Long;
            for (offset$iv = buffer.getSize(); s$iv != null && offset$iv > offset; s$iv = s$iv.getPrev()) {
               `offset$iv` -= `s$iv`.getLimit() - `s$iv`.getPos();
               if (`offset$iv` <= offset) {
                  break;
               }
            }

            iterationAction.invoke(UnsafeBufferOperationsKt.getBufferIterationContextImpl(), `s$iv`, `offset$iv`);
         } else {
            var var27: Segment = buffer.getHead();
            var var28: Long = 0L;

            while (s$iv != null) {
               val `nextOffset$iv`: Long = var28 + (var27.getLimit() - var27.getPos());
               if (`nextOffset$iv` > offset) {
                  break;
               }

               var27 = var27.getNext();
               var28 = `nextOffset$iv`;
            }

            iterationAction.invoke(UnsafeBufferOperationsKt.getBufferIterationContextImpl(), var27, var28);
         }
      }
   }

   public inline fun forEachSegment(buffer: Buffer, action: (SegmentReadContext, Segment) -> Unit) {
      for (Segment curr = buffer.getHead(); curr != null; curr = curr.getNext()) {
         action.invoke(UnsafeBufferOperationsKt.getSegmentReadContextImpl(), curr);
      }
   }
}
