@file:SourceDebugExtension(["SMAP\nBuffersJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BuffersJvm.kt\nkotlinx/io/BuffersJvmKt\n+ 2 -Util.kt\nkotlinx/io/_UtilKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 5 Buffer.kt\nkotlinx/io/BufferKt\n+ 6 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperationsKt\n*L\n1#1,207:1\n52#2:208\n53#2:210\n107#2:217\n107#2:242\n110#2:260\n1#3:209\n1#3:239\n1#3:250\n1#3:286\n195#4,6:211\n203#4,20:218\n99#4:238\n100#4,2:240\n102#4,6:243\n347#4:249\n348#4,5:251\n353#4:258\n354#4:262\n355#4:284\n99#4:285\n100#4,8:287\n195#4,28:295\n659#5,2:256\n663#5,21:263\n434#6:259\n435#6:261\n*S KotlinDebug\n*F\n+ 1 BuffersJvm.kt\nkotlinx/io/BuffersJvmKt\n*L\n57#1:208\n57#1:210\n68#1:217\n101#1:242\n138#1:260\n57#1:209\n100#1:239\n133#1:250\n160#1:286\n67#1:211,6\n67#1:218,20\n100#1:238\n100#1:240,2\n100#1:243,6\n133#1:249\n133#1:251,5\n133#1:258\n133#1:262\n133#1:284\n160#1:285\n160#1:287,8\n180#1:295,28\n133#1:256,2\n133#1:263,21\n137#1:259\n137#1:261\n*E\n"])

package kotlinx.io

import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.channels.ByteChannel
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.BuffersJvmKt.asByteChannel.1
import kotlinx.io.unsafe.BufferIterationContext
import kotlinx.io.unsafe.SegmentReadContext
import kotlinx.io.unsafe.UnsafeBufferOperations
import kotlinx.io.unsafe.UnsafeBufferOperationsKt

public fun Buffer.transferFrom(input: InputStream): Buffer {
   write(`$this$transferFrom`, input, java.lang.Long.MAX_VALUE, true);
   return `$this$transferFrom`;
}

public fun Buffer.write(input: InputStream, byteCount: Long): Buffer {
   if (byteCount < 0L) {
      throw new IllegalArgumentException(("byteCount ($byteCount) < 0").toString());
   } else {
      write(`$this$write`, input, byteCount, false);
      return `$this$write`;
   }
}

private fun Buffer.write(input: InputStream, byteCount: Long, forever: Boolean) {
   var var26: Long = byteCount;
   var exchaused: Boolean = false;

   while (!exchaused && (var26 > 0L || forever)) {
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      val `tail$iv`: Segment = `$this$write`.writableSegment(1);
      val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
      val var10001: Int = `tail$iv`.getLimit();
      val bytesRead: Int = input.read(`data$iv`, var10001, (int)Math.min(var26, (long)(`data$iv`.length - var10001)));
      val var10000: Int;
      if (bytesRead == -1) {
         if (!forever) {
            throw new EOFException("Stream exhausted before $byteCount bytes were read.");
         }

         exchaused = true;
         var10000 = 0;
      } else {
         var26 -= bytesRead;
         var10000 = bytesRead;
      }

      if (var10000 == 1) {
         `tail$iv`.writeBackData(`data$iv`, var10000);
         `tail$iv`.setLimit(`tail$iv`.getLimit() + var10000);
         `$this$write`.setSizeMut(`$this$write`.getSizeMut() + (long)var10000);
      } else {
         if (0 > var10000 || var10000 > `tail$iv`.getRemainingCapacity()) {
            throw new IllegalStateException(("Invalid number of bytes written: $var10000. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString());
         }

         if (var10000 != 0) {
            `tail$iv`.writeBackData(`data$iv`, var10000);
            `tail$iv`.setLimit(`tail$iv`.getLimit() + var10000);
            `$this$write`.setSizeMut(`$this$write`.getSizeMut() + (long)var10000);
         } else if (SegmentKt.isEmpty(`tail$iv`)) {
            `$this$write`.recycleTail();
         }
      }
   }
}

public fun Buffer.readTo(out: OutputStream, byteCount: Long = `$this$readTo`.getSize()) {
   _UtilKt.checkOffsetAndCount(`$this$readTo`.getSize(), 0L, byteCount);
   var var20: Long = byteCount;

   while (var20 > 0L) {
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      if (`$this$readTo`.exhausted()) {
         throw new IllegalArgumentException("Buffer is empty".toString());
      }

      val var10000: Segment = `$this$readTo`.getHead();
      val var21: ByteArray = var10000.dataAsByteArray(true);
      val pos: Int = var10000.getPos();
      val toCopy: Int = (int)Math.min(var20, (long)(var10000.getLimit() - pos));
      out.write(var21, pos, toCopy);
      var20 -= toCopy;
      if (toCopy != 0) {
         if (toCopy < 0) {
            throw new IllegalStateException("Returned negative read bytes count");
         }

         if (toCopy > var10000.getSize()) {
            throw new IllegalStateException("Returned too many bytes");
         }

         `$this$readTo`.skip((long)toCopy);
      }
   }
}

@JvmSynthetic
fun `readTo$default`(var0: Buffer, var1: OutputStream, var2: Long, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = var0.getSize();
   }

   readTo(var0, var1, var2);
}

public fun Buffer.copyTo(out: OutputStream, startIndex: Long = 0L, endIndex: Long = `$this$copyTo`.getSize()) {
   _UtilKt.checkBounds(`$this$copyTo`.getSize(), startIndex, endIndex);
   if (startIndex != endIndex) {
      var var80: Long = endIndex - startIndex;
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      if (startIndex < 0L) {
         throw new IllegalArgumentException(("Offset must be non-negative: $startIndex").toString());
      } else if (startIndex >= `$this$copyTo`.getSize()) {
         throw new IndexOutOfBoundsException("Offset should be less than buffer's size (${`$this$copyTo`.getSize()}): $startIndex");
      } else {
         if (`$this$copyTo`.getHead() == null) {
            val ctx: BufferIterationContext = UnsafeBufferOperationsKt.getBufferIterationContextImpl();
            var curr: Segment = null;

            for (int var55 = (int)(startIndex - -1L); var80 > 0L; var55 = 0) {
               val `$this$withData$iv`: SegmentReadContext = ctx;
               val var10000: ByteArray = curr.dataAsByteArray(true);
               val pos: Int = curr.getPos();
               val toCopy: Int = (int)Math.min((long)(curr.getLimit() - pos - var55), var80);
               out.write(var10000, pos + var55, toCopy);
               var80 -= toCopy;
               val var81: Segment = ctx.next(curr);
               if (var81 == null) {
                  break;
               }

               curr = var81;
            }
         } else if (`$this$copyTo`.getSize() - startIndex < startIndex) {
            var `s$iv$iv`: Segment = `$this$copyTo`.getTail();

            var `offset$iv$iv`: Long;
            for (offset$iv$iv = $this$copyTo.getSize(); s$iv$iv != null && offset$iv$iv > startIndex; s$iv$iv = s$iv$iv.getPrev()) {
               `offset$iv$iv` -= `s$iv$iv`.getLimit() - `s$iv$iv`.getPos();
               if (`offset$iv$iv` <= startIndex) {
                  break;
               }
            }

            val var49: BufferIterationContext = UnsafeBufferOperationsKt.getBufferIterationContextImpl();
            var var53: Segment = `s$iv$iv`;

            for (int var57 = (int)(startIndex - offset$iv$iv); var80 > 0L; var57 = 0) {
               val var60: SegmentReadContext = var49;
               val var82: ByteArray = var53.dataAsByteArray(true);
               val var66: Int = var53.getPos();
               val var76: Int = (int)Math.min((long)(var53.getLimit() - var66 - var57), var80);
               out.write(var82, var66 + var57, var76);
               var80 -= var76;
               val var83: Segment = var49.next(var53);
               if (var83 == null) {
                  break;
               }

               var53 = var83;
            }
         } else {
            var var78: Segment = `$this$copyTo`.getHead();
            var var79: Long = 0L;

            while (s$iv$iv != null) {
               val `nextOffset$iv$iv`: Long = var79 + (var78.getLimit() - var78.getPos());
               if (`nextOffset$iv$iv` > startIndex) {
                  break;
               }

               var78 = var78.getNext();
               var79 = `nextOffset$iv$iv`;
            }

            val var50: BufferIterationContext = UnsafeBufferOperationsKt.getBufferIterationContextImpl();
            var var54: Segment = var78;

            for (int var59 = (int)(startIndex - offset$iv$iv); var80 > 0L; var59 = 0) {
               val var61: SegmentReadContext = var50;
               val var84: ByteArray = var54.dataAsByteArray(true);
               val var67: Int = var54.getPos();
               val var77: Int = (int)Math.min((long)(var54.getLimit() - var67 - var59), var80);
               out.write(var84, var67 + var59, var77);
               var80 -= var77;
               val var85: Segment = var50.next(var54);
               if (var85 == null) {
                  break;
               }

               var54 = var85;
            }
         }
      }
   }
}

@JvmSynthetic
fun `copyTo$default`(var0: Buffer, var1: OutputStream, var2: Long, var4: Long, var6: Int, var7: Any) {
   if ((var6 and 2) != 0) {
      var2 = 0L;
   }

   if ((var6 and 4) != 0) {
      var4 = var0.getSize();
   }

   copyTo(var0, var1, var2, var4);
}

public fun Buffer.readAtMostTo(sink: ByteBuffer): Int {
   if (`$this$readAtMostTo`.exhausted()) {
      return -1;
   } else {
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      if (`$this$readAtMostTo`.exhausted()) {
         throw new IllegalArgumentException("Buffer is empty".toString());
      } else {
         val var10000: Segment = `$this$readAtMostTo`.getHead();
         val var13: ByteArray = var10000.dataAsByteArray(true);
         val pos: Int = var10000.getPos();
         val var11: Int = Math.min(sink.remaining(), var10000.getLimit() - pos);
         sink.put(var13, pos, var11);
         if (var11 != 0) {
            if (var11 < 0) {
               throw new IllegalStateException("Returned negative read bytes count");
            }

            if (var11 > var10000.getSize()) {
               throw new IllegalStateException("Returned too many bytes");
            }

            `$this$readAtMostTo`.skip((long)var11);
         }

         return var11;
      }
   }
}

public fun Buffer.transferFrom(source: ByteBuffer): Buffer {
   var var16: Int = source.remaining();

   while (var16 > 0) {
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      val `tail$iv`: Segment = `$this$transferFrom`.writableSegment(1);
      val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
      val var10001: Int = `tail$iv`.getLimit();
      val toCopy: Int = Math.min(var16, `data$iv`.length - var10001);
      source.get(`data$iv`, var10001, toCopy);
      var16 -= toCopy;
      if (toCopy == 1) {
         `tail$iv`.writeBackData(`data$iv`, toCopy);
         `tail$iv`.setLimit(`tail$iv`.getLimit() + toCopy);
         `$this$transferFrom`.setSizeMut(`$this$transferFrom`.getSizeMut() + (long)toCopy);
      } else {
         if (0 > toCopy || toCopy > `tail$iv`.getRemainingCapacity()) {
            throw new IllegalStateException(("Invalid number of bytes written: $toCopy. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString());
         }

         if (toCopy != 0) {
            `tail$iv`.writeBackData(`data$iv`, toCopy);
            `tail$iv`.setLimit(`tail$iv`.getLimit() + toCopy);
            `$this$transferFrom`.setSizeMut(`$this$transferFrom`.getSizeMut() + (long)toCopy);
         } else if (SegmentKt.isEmpty(`tail$iv`)) {
            `$this$transferFrom`.recycleTail();
         }
      }
   }

   return `$this$transferFrom`;
}

public fun Buffer.asByteChannel(): ByteChannel {
   return new 1(`$this$asByteChannel`);
}
