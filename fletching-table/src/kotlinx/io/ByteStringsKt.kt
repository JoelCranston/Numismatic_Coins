@file:SourceDebugExtension(["SMAP\nByteStrings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteStrings.kt\nkotlinx/io/ByteStringsKt\n+ 2 -Util.kt\nkotlinx/io/_UtilKt\n+ 3 Sinks.kt\nkotlinx/io/SinksKt\n+ 4 UnsafeByteStringOperations.kt\nkotlinx/io/bytestring/unsafe/UnsafeByteStringOperations\n+ 5 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 7 Buffer.kt\nkotlinx/io/BufferKt\n*L\n1#1,167:1\n38#2:168\n374#3:169\n375#3,2:200\n42#4:170\n43#4:199\n42#4:203\n43#4:229\n195#5,28:171\n1#6:202\n659#7,25:204\n*S KotlinDebug\n*F\n+ 1 ByteStrings.kt\nkotlinx/io/ByteStringsKt\n*L\n31#1:168\n36#1:169\n36#1:200,2\n39#1:170\n39#1:199\n128#1:203\n128#1:229\n42#1:171,28\n129#1:204,25\n*E\n"])

package kotlinx.io

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.bytestring.ByteString
import kotlinx.io.bytestring.ByteStringKt
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations
import kotlinx.io.unsafe.UnsafeBufferOperations

public fun Sink.write(byteString: ByteString, startIndex: Int = 0, endIndex: Int = byteString.getSize()) {
   _UtilKt.checkBounds((long)byteString.getSize(), (long)startIndex, (long)endIndex);
   if (endIndex != startIndex) {
      val buffer: Buffer = `$this$write`.getBuffer();
      var var26: Int = startIndex;
      val `this_$iv`: UnsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
      val data: ByteArray = byteString.getBackingArrayReference();

      while (var26 < endIndex) {
         val `this_$ivx`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val `tail$iv`: Segment = buffer.writableSegment(1);
         val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
         val var10001: Int = `tail$iv`.getLimit();
         val var27: Int = Math.min(endIndex - var26, `data$iv`.length - var10001);
         ArraysKt.copyInto(data, `data$iv`, var10001, var26, var26 + var27);
         if (var27 == 1) {
            `tail$iv`.writeBackData(`data$iv`, var27);
            `tail$iv`.setLimit(`tail$iv`.getLimit() + var27);
            buffer.setSizeMut(buffer.getSizeMut() + (long)var27);
         } else {
            if (0 > var27 || var27 > `tail$iv`.getRemainingCapacity()) {
               throw new IllegalStateException(("Invalid number of bytes written: $var27. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString());
            }

            if (var27 != 0) {
               `tail$iv`.writeBackData(`data$iv`, var27);
               `tail$iv`.setLimit(`tail$iv`.getLimit() + var27);
               buffer.setSizeMut(buffer.getSizeMut() + (long)var27);
            } else if (SegmentKt.isEmpty(`tail$iv`)) {
               buffer.recycleTail();
            }
         }

         var26 += var27;
      }

      `$this$write`.hintEmit();
   }
}

@JvmSynthetic
fun `write$default`(var0: Sink, var1: ByteString, var2: Int, var3: Int, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.getSize();
   }

   write(var0, var1, var2, var3);
}

public fun Source.readByteString(): ByteString {
   return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(SourcesKt.readByteArray(`$this$readByteString`));
}

public fun Source.readByteString(byteCount: Int): ByteString {
   return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(SourcesKt.readByteArray(`$this$readByteString`, byteCount));
}

public fun Source.indexOf(byteString: ByteString, startIndex: Long = 0L): Long {
   if (startIndex < 0L) {
      throw new IllegalArgumentException(("startIndex: $startIndex").toString());
   } else if (ByteStringKt.isEmpty(byteString)) {
      return 0L;
   } else {
      for (long offset = startIndex;
         $this$indexOf.request(offset + byteString.getSize());
         offset = $this$indexOf.getBuffer().getSize() - byteString.getSize() + 1L
      ) {
         val idx: Long = indexOf(`$this$indexOf`.getBuffer(), byteString, offset);
         if (idx >= 0L) {
            return idx;
         }
      }

      return -1L;
   }
}

@JvmSynthetic
fun `indexOf$default`(var0: Source, var1: ByteString, var2: Long, var4: Int, var5: Any): Long {
   if ((var4 and 2) != 0) {
      var2 = 0L;
   }

   return indexOf(var0, var1, var2);
}

public fun Buffer.indexOf(byteString: ByteString, startIndex: Long = 0L): Long {
   if (startIndex > `$this$indexOf`.getSize()) {
      throw new IllegalArgumentException(("startIndex ($startIndex) should not exceed size (${`$this$indexOf`.getSize()})").toString());
   } else if (ByteStringKt.isEmpty(byteString)) {
      return 0L;
   } else if (startIndex > `$this$indexOf`.getSize() - byteString.getSize()) {
      return -1L;
   } else {
      val `this_$iv`: UnsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
      val byteStringData: ByteArray = byteString.getBackingArrayReference();
      if (`$this$indexOf`.getHead() == null) {
         if (-1L == -1L) {
            return -1L;
         } else {
            var var31: Segment = null;
            var var33: Long = -1L;

            do {
               val var35: Int = Math.max((int)(startIndex - var33), 0);
               val var37: Int = SegmentKt.indexOfBytesInbound(var31, byteStringData, var35);
               if (var37 != -1) {
                  return var33 + var37;
               }

               val var41: Int = SegmentKt.indexOfBytesOutbound(var31, byteStringData, Math.max(var35, var31.getSize() - byteStringData.length + 1));
               if (var41 != -1) {
                  return var33 + var41;
               }

               var33 += var31.getSize();
               var31 = var31.getNext();
            } while (segment != null && offset + byteString.getSize() <= $this$indexOf.getSize());

            return -1L;
         }
      } else if (`$this$indexOf`.getSize() - startIndex < startIndex) {
         var var42: Segment = `$this$indexOf`.getTail();

         var var43: Long;
         for (offset$iv = $this$indexOf.getSize(); s$iv != null && offset$iv > startIndex; s$iv = s$iv.getPrev()) {
            var43 -= var42.getLimit() - var42.getPos();
            if (var43 <= startIndex) {
               break;
            }
         }

         if (var43 == -1L) {
            return -1L;
         } else {
            var var30: Segment = var42;
            var var32: Long = var43;

            do {
               val startOffsetx: Int = Math.max((int)(startIndex - var32), 0);
               val idxx: Int = SegmentKt.indexOfBytesInbound(var30, byteStringData, startOffsetx);
               if (idxx != -1) {
                  return var32 + idxx;
               }

               val var40: Int = SegmentKt.indexOfBytesOutbound(var30, byteStringData, Math.max(startOffsetx, var30.getSize() - byteStringData.length + 1));
               if (var40 != -1) {
                  return var32 + var40;
               }

               var32 += var30.getSize();
               var30 = var30.getNext();
            } while (segment != null && offset + byteString.getSize() <= $this$indexOf.getSize());

            return -1L;
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

            do {
               val startOffsetxx: Int = Math.max((int)(startIndex - offset), 0);
               val idxxx: Int = SegmentKt.indexOfBytesInbound(segment, byteStringData, startOffsetxx);
               if (idxxx != -1) {
                  return offset + idxxx;
               }

               val idx1: Int = SegmentKt.indexOfBytesOutbound(segment, byteStringData, Math.max(startOffsetxx, segment.getSize() - byteStringData.length + 1));
               if (idx1 != -1) {
                  return offset + idx1;
               }

               offset += segment.getSize();
               segment = segment.getNext();
            } while (segment != null && offset + byteString.getSize() <= $this$indexOf.getSize());

            return -1L;
         }
      }
   }
}

@JvmSynthetic
fun `indexOf$default`(var0: Buffer, var1: ByteString, var2: Long, var4: Int, var5: Any): Long {
   if ((var4 and 2) != 0) {
      var2 = 0L;
   }

   return indexOf(var0, var1, var2);
}
