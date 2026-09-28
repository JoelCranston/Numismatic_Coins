@file:SourceDebugExtension(["SMAP\nBuffers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffers.kt\nkotlinx/io/BuffersKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ByteStringBuilder.kt\nkotlinx/io/bytestring/ByteStringBuilderKt\n+ 4 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 5 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperationsKt\n+ 6 Buffer.kt\nkotlinx/io/BufferKt\n*L\n1#1,80:1\n1#2:81\n127#3:82\n378#4,3:83\n381#4,3:88\n434#5,2:86\n659#6,25:91\n*S KotlinDebug\n*F\n+ 1 Buffers.kt\nkotlinx/io/BuffersKt\n*L\n24#1:82\n25#1:83,3\n25#1:88,3\n26#1:86,2\n52#1:91,25\n*E\n"])

package kotlinx.io

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.bytestring.ByteString
import kotlinx.io.bytestring.ByteStringBuilder
import kotlinx.io.bytestring.ByteStringKt
import kotlinx.io.unsafe.SegmentReadContext
import kotlinx.io.unsafe.UnsafeBufferOperations
import kotlinx.io.unsafe.UnsafeBufferOperationsKt

public fun Buffer.snapshot(): ByteString {
   if (`$this$snapshot`.getSize() == 0L) {
      return ByteStringKt.ByteString();
   } else if (`$this$snapshot`.getSize() > 2147483647L) {
      throw new IllegalStateException(("Buffer is too long (${`$this$snapshot`.getSize()}) to be converted into a byte string.").toString());
   } else {
      val var3: ByteStringBuilder = new ByteStringBuilder((int)`$this$snapshot`.getSize());
      val `$this$snapshot_u24lambda_u242`: ByteStringBuilder = var3;
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;

      for (Segment curr$iv = $this$snapshot.getHead(); curr$iv != null; curr$iv = curr$iv.getNext()) {
         val ctx: SegmentReadContext = UnsafeBufferOperationsKt.getSegmentReadContextImpl();
         `$this$snapshot_u24lambda_u242`.append(`curr$iv`.dataAsByteArray(true), `curr$iv`.getPos(), `curr$iv`.getLimit());
      }

      return var3.toByteString();
   }
}

public fun Buffer.indexOf(byte: Byte, startIndex: Long = 0L, endIndex: Long = `$this$indexOf`.getSize()): Long {
   val endOffset: Long = Math.min(endIndex, `$this$indexOf`.getSize());
   _UtilKt.checkBounds(`$this$indexOf`.getSize(), startIndex, endOffset);
   if (startIndex == endOffset) {
      return -1L;
   } else if (`$this$indexOf`.getHead() == null) {
      if (-1L == -1L) {
         return -1L;
      } else {
         var var27: Segment = null;
         var var29: Long = -1L;

         while (endOffset > offset) {
            val var33: Int = SegmentKt.indexOf(var27, var1, Math.max((int)(startIndex - var29), 0), Math.min(var27.getSize(), (int)(endOffset - var29)));
            if (var33 != -1) {
               return var29 + var33;
            }

            var29 += var27.getSize();
            var27 = var27.getNext();
            if (var27 == null || var29 >= endOffset) {
               return -1L;
            }
         }

         throw new IllegalStateException("Check failed.".toString());
      }
   } else if (`$this$indexOf`.getSize() - startIndex < startIndex) {
      var var34: Segment = `$this$indexOf`.getTail();

      var var35: Long;
      for (offset$iv = $this$indexOf.getSize(); s$iv != null && offset$iv > startIndex; s$iv = s$iv.getPrev()) {
         var35 -= var34.getLimit() - var34.getPos();
         if (var35 <= startIndex) {
            break;
         }
      }

      if (var35 == -1L) {
         return -1L;
      } else {
         var var26: Segment = var34;
         var var28: Long = var35;

         while (endOffset > offset) {
            val idxx: Int = SegmentKt.indexOf(var26, var1, Math.max((int)(startIndex - var28), 0), Math.min(var26.getSize(), (int)(endOffset - var28)));
            if (idxx != -1) {
               return var28 + idxx;
            }

            var28 += var26.getSize();
            var26 = var26.getNext();
            if (var26 == null || var28 >= endOffset) {
               return -1L;
            }
         }

         throw new IllegalStateException("Check failed.".toString());
      }
   } else {
      var `s$iv`: Segment = `$this$indexOf`.getHead();
      var `offset$ivx`: Long = 0L;

      while (s$iv != null) {
         val `nextOffset$iv`: Long = `offset$ivx` + (`s$iv`.getLimit() - `s$iv`.getPos());
         if (`nextOffset$iv` > startIndex) {
            break;
         }

         `s$iv` = `s$iv`.getNext();
         `offset$ivx` = `nextOffset$iv`;
      }

      if (`offset$ivx` == -1L) {
         return -1L;
      } else {
         var segment: Segment = `s$iv`;
         var offset: Long = `offset$ivx`;

         while (endOffset > offset) {
            val idxxx: Int = SegmentKt.indexOf(segment, var1, Math.max((int)(startIndex - offset), 0), Math.min(segment.getSize(), (int)(endOffset - offset)));
            if (idxxx != -1) {
               return offset + idxxx;
            }

            offset += segment.getSize();
            segment = segment.getNext();
            if (segment == null || offset >= endOffset) {
               return -1L;
            }
         }

         throw new IllegalStateException("Check failed.".toString());
      }
   }
}

@JvmSynthetic
fun `indexOf$default`(var0: Buffer, var1: Byte, var2: Long, var4: Long, var6: Int, var7: Any): Long {
   if ((var6 and 2) != 0) {
      var2 = 0L;
   }

   if ((var6 and 4) != 0) {
      var4 = var0.getSize();
   }

   return indexOf(var0, var1, var2, var4);
}
