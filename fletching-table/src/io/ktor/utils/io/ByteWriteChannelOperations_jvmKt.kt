@file:SourceDebugExtension(["SMAP\nByteWriteChannelOperations.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteWriteChannelOperations.jvm.kt\nio/ktor/utils/io/ByteWriteChannelOperations_jvmKt\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,73:1\n195#2,28:74\n195#2,28:103\n1#3:102\n*S KotlinDebug\n*F\n+ 1 ByteWriteChannelOperations.jvm.kt\nio/ktor/utils/io/ByteWriteChannelOperations_jvmKt\n*L\n26#1:74,28\n59#1:103,28\n*E\n"])

package io.ktor.utils.io

import io.ktor.utils.io.core.OutputArraysJVMKt
import java.nio.ByteBuffer
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Segment
import kotlinx.io.SegmentKt
import kotlinx.io.SinksJvmKt
import kotlinx.io.unsafe.UnsafeBufferOperations

public suspend fun ByteWriteChannel.writeByteBuffer(value: ByteBuffer) {
   OutputArraysJVMKt.writeByteBuffer(`$this$writeByteBuffer`.getWriteBuffer(), value);
   val var10000: Any = `$this$writeByteBuffer`.flush(`$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeFully(value: ByteBuffer) {
   OutputArraysJVMKt.writeByteBuffer(`$this$writeFully`.getWriteBuffer(), value);
   val var10000: Any = `$this$writeFully`.flush(`$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.write(min: Int = ..., block: (ByteBuffer) -> Unit) {
   val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
   val `buffer$iv`: Buffer = `$this$write`.getWriteBuffer().getBuffer();
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

   val var10000: Any = `$this$write`.flush(`$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `write$default`(var0: ByteWriteChannel, var1: Int, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 1) != 0) {
      var1 = 1;
   }

   return write(var0, var1, var2, var3);
}

public fun ByteWriteChannel.writeAvailable(min: Int = 1, block: (ByteBuffer) -> Unit): Int {
   if (min <= 0) {
      throw new IllegalArgumentException("min should be positive".toString());
   } else if (min > 1048576) {
      throw new IllegalArgumentException(("Min($min) shouldn't be greater than 1048576").toString());
   } else if (`$this$writeAvailable`.isClosedForWrite()) {
      return -1;
   } else {
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      val `buffer$iv`: Buffer = `$this$writeAvailable`.getWriteBuffer().getBuffer();
      val `tail$iv`: Segment = `buffer$iv`.writableSegment(min);
      val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
      val var10001: Int = `tail$iv`.getLimit();
      val buffer: ByteBuffer = ByteBuffer.wrap(`data$iv`, var10001, `data$iv`.length - var10001);
      block.invoke(buffer);
      val var17: Int = buffer.position() - var10001;
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

      return var17;
   }
}

@JvmSynthetic
fun `writeAvailable$default`(var0: ByteWriteChannel, var1: Int, var2: Function1, var3: Int, var4: Any): Int {
   if ((var3 and 1) != 0) {
      var1 = 1;
   }

   return writeAvailable(var0, var1, var2);
}

public fun ByteWriteChannel.writeAvailable(buffer: ByteBuffer) {
   SinksJvmKt.write(`$this$writeAvailable`.getWriteBuffer(), buffer);
}
