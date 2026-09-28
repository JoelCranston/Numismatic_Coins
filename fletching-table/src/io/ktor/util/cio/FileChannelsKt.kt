@file:SourceDebugExtension(["SMAP\nFileChannels.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileChannels.kt\nio/ktor/util/cio/FileChannelsKt\n+ 2 WriteSuspendSession.kt\nio/ktor/utils/io/jvm/nio/WriteSuspendSessionKt\n+ 3 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n*L\n1#1,110:1\n51#2,4:111\n55#2,3:121\n59#2,3:144\n51#2,4:147\n55#2,3:157\n59#2,3:180\n195#3,6:115\n203#3,20:124\n195#3,6:151\n203#3,20:160\n*S KotlinDebug\n*F\n+ 1 FileChannels.kt\nio/ktor/util/cio/FileChannelsKt\n*L\n64#1:111,4\n64#1:121,3\n64#1:144,3\n73#1:147,4\n73#1:157,3\n73#1:180,3\n64#1:115,6\n64#1:124,20\n73#1:151,6\n73#1:160,20\n*E\n"])

package io.ktor.util.cio

import io.ktor.util.cio.FileChannelsKt.readChannel.writer.1
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import io.ktor.utils.io.WriterJob
import io.ktor.utils.io.WriterScope
import java.io.File
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.channels.SeekableByteChannel
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScopeKt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.io.Buffer
import kotlinx.io.Segment
import kotlinx.io.SegmentKt
import kotlinx.io.unsafe.UnsafeBufferOperations

public fun File.readChannel(start: Long = 0L, endInclusive: Long = -1L, coroutineContext: CoroutineContext = Dispatchers.getIO() as CoroutineContext): ByteReadChannel {
   val `randomAccessFile$delegate`: Lazy = LazyKt.lazy(FileChannelsKt::readChannel$lambda$0);
   val writer: WriterJob = ByteWriteChannelOperationsKt.writer(
      CoroutineScopeKt.CoroutineScope(coroutineContext),
      new CoroutineName("file-reader").plus(coroutineContext),
      false,
      new 1(start, endInclusive, `$this$readChannel`.length(), `randomAccessFile$delegate`, null)
   );
   ByteWriteChannelOperationsKt.invokeOnCompletion(writer, FileChannelsKt::readChannel$lambda$2);
   return writer.getChannel();
}

@JvmSynthetic
fun `readChannel$default`(var0: File, var1: Long, var3: Long, var5: CoroutineContext, var6: Int, var7: Any): ByteReadChannel {
   if ((var6 and 1) != 0) {
      var1 = 0L;
   }

   if ((var6 and 2) != 0) {
      var3 = -1L;
   }

   if ((var6 and 4) != 0) {
      var5 = Dispatchers.getIO();
   }

   return readChannel(var0, var1, var3, var5);
}

internal suspend fun SeekableByteChannel.writeToScope(writerScope: WriterScope, start: Long, endInclusive: Long) {
   var `$continuation`: Continuation;
   label138: {
      if (`$completion` is io.ktor.util.cio.FileChannelsKt.writeToScope.1) {
         `$continuation` = `$completion` as io.ktor.util.cio.FileChannelsKt.writeToScope.1;
         if (((`$completion` as io.ktor.util.cio.FileChannelsKt.writeToScope.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label138;
         }
      }

      `$continuation` = new io.ktor.util.cio.FileChannelsKt.writeToScope.1(`$completion`);
   }

   var var35: Any;
   var var36: ByteWriteChannel;
   var var37: Int;
   var var38: Ref.BooleanRef;
   label158: {
      val `$result`: Any = `$continuation`.result;
      var35 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var position: Ref.LongRef;
      var `$this$writeWhile$ivx`: ByteWriteChannel;
      var `$i$f$writeWhilex`: Int;
      var `done$ivx`: Ref.BooleanRef;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (start > 0L) {
               `$this$writeToScope`.position(start);
            }

            if (endInclusive == -1L) {
               var36 = writerScope.getChannel();
               var37 = 0;
               var38 = new Ref.BooleanRef();
               break label158;
            }

            position = new Ref.LongRef();
            position.element = start;
            `$this$writeWhile$ivx` = writerScope.getChannel();
            `$i$f$writeWhilex` = 0;
            `done$ivx` = new Ref.BooleanRef();
            break;
         case 1:
            var37 = `$continuation`.I$0;
            endInclusive = `$continuation`.J$1;
            start = `$continuation`.J$0;
            var38 = `$continuation`.L$3 as Ref.BooleanRef;
            var36 = `$continuation`.L$2 as ByteWriteChannel;
            writerScope = `$continuation`.L$1 as WriterScope;
            `$this$writeToScope` = `$continuation`.L$0 as SeekableByteChannel;
            ResultKt.throwOnFailure(`$result`);
            break label158;
         case 2:
            `$i$f$writeWhilex` = `$continuation`.I$0;
            endInclusive = `$continuation`.J$1;
            start = `$continuation`.J$0;
            `done$ivx` = `$continuation`.L$4 as Ref.BooleanRef;
            `$this$writeWhile$ivx` = `$continuation`.L$3 as ByteWriteChannel;
            position = `$continuation`.L$2 as Ref.LongRef;
            writerScope = `$continuation`.L$1 as WriterScope;
            `$this$writeToScope` = `$continuation`.L$0 as SeekableByteChannel;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (!done$ivx.element) {
         val `this_$iv$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val `buffer$iv$iv`: Buffer = `$this$writeWhile$ivx`.getWriteBuffer().getBuffer();
         val `tail$iv$iv`: Segment = `buffer$iv$iv`.writableSegment(1);
         val `data$iv$iv`: ByteArray = `tail$iv$iv`.dataAsByteArray(false);
         val var10001: Int = `tail$iv$iv`.getLimit();
         val `buffer$iv`: ByteBuffer = ByteBuffer.wrap(`data$iv$iv`, var10001, `data$iv$iv`.length - var10001);
         val fileRemaining: Long = endInclusive - position.element + 1L;
         val var10000: Int;
         if (endInclusive - position.element + 1L < `buffer$iv`.remaining()) {
            val l: Int = `buffer$iv`.limit();
            ((java.nio.Buffer)`buffer$iv`).limit(`buffer$iv`.position() + (int)fileRemaining);
            val r: Int = `$this$writeToScope`.read(`buffer$iv`);
            ((java.nio.Buffer)`buffer$iv`).limit(l);
            var10000 = r;
         } else {
            var10000 = `$this$writeToScope`.read(`buffer$iv`);
         }

         if (var10000 > 0) {
            position.element += var10000;
         }

         `done$ivx`.element = var10000 == -1 || position.element > endInclusive;
         val `bytesWritten$iv$iv`: Int = `buffer$iv`.position() - var10001;
         if (`bytesWritten$iv$iv` == 1) {
            `tail$iv$iv`.writeBackData(`data$iv$iv`, `bytesWritten$iv$iv`);
            `tail$iv$iv`.setLimit(`tail$iv$iv`.getLimit() + `bytesWritten$iv$iv`);
            `buffer$iv$iv`.setSizeMut(`buffer$iv$iv`.getSizeMut() + (long)`bytesWritten$iv$iv`);
         } else {
            if (0 > `bytesWritten$iv$iv` || `bytesWritten$iv$iv` > `tail$iv$iv`.getRemainingCapacity()) {
               throw new IllegalStateException(
                  ("Invalid number of bytes written: $`bytesWritten$iv$iv`. Should be in 0..${`tail$iv$iv`.getRemainingCapacity()}").toString()
               );
            }

            if (`bytesWritten$iv$iv` != 0) {
               `tail$iv$iv`.writeBackData(`data$iv$iv`, `bytesWritten$iv$iv`);
               `tail$iv$iv`.setLimit(`tail$iv$iv`.getLimit() + `bytesWritten$iv$iv`);
               `buffer$iv$iv`.setSizeMut(`buffer$iv$iv`.getSizeMut() + (long)`bytesWritten$iv$iv`);
            } else if (SegmentKt.isEmpty(`tail$iv$iv`)) {
               `buffer$iv$iv`.recycleTail();
            }
         }

         `$continuation`.L$0 = `$this$writeToScope`;
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(writerScope);
         `$continuation`.L$2 = position;
         `$continuation`.L$3 = `$this$writeWhile$ivx`;
         `$continuation`.L$4 = `done$ivx`;
         `$continuation`.J$0 = start;
         `$continuation`.J$1 = endInclusive;
         `$continuation`.I$0 = `$i$f$writeWhilex`;
         `$continuation`.label = 2;
         if (`$this$writeWhile$ivx`.flush(`$continuation`) === var35) {
            return var35;
         }
      }

      return Unit.INSTANCE;
   }

   while (!done$iv.element) {
      val var39: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      val var40: Buffer = var36.getWriteBuffer().getBuffer();
      val `tail$iv$ivx`: Segment = var40.writableSegment(1);
      val `data$iv$ivx`: ByteArray = `tail$iv$ivx`.dataAsByteArray(false);
      val var53: Int = `tail$iv$ivx`.getLimit();
      val `buffer$ivx`: ByteBuffer = ByteBuffer.wrap(`data$iv$ivx`, var53, `data$iv$ivx`.length - var53);
      var38.element = `$this$writeToScope`.read(`buffer$ivx`) == -1;
      val `bytesWritten$iv$ivx`: Int = `buffer$ivx`.position() - var53;
      if (`bytesWritten$iv$ivx` == 1) {
         `tail$iv$ivx`.writeBackData(`data$iv$ivx`, `bytesWritten$iv$ivx`);
         `tail$iv$ivx`.setLimit(`tail$iv$ivx`.getLimit() + `bytesWritten$iv$ivx`);
         var40.setSizeMut(var40.getSizeMut() + (long)`bytesWritten$iv$ivx`);
      } else {
         if (0 > `bytesWritten$iv$ivx` || `bytesWritten$iv$ivx` > `tail$iv$ivx`.getRemainingCapacity()) {
            throw new IllegalStateException(
               ("Invalid number of bytes written: $`bytesWritten$iv$ivx`. Should be in 0..${`tail$iv$ivx`.getRemainingCapacity()}").toString()
            );
         }

         if (`bytesWritten$iv$ivx` != 0) {
            `tail$iv$ivx`.writeBackData(`data$iv$ivx`, `bytesWritten$iv$ivx`);
            `tail$iv$ivx`.setLimit(`tail$iv$ivx`.getLimit() + `bytesWritten$iv$ivx`);
            var40.setSizeMut(var40.getSizeMut() + (long)`bytesWritten$iv$ivx`);
         } else if (SegmentKt.isEmpty(`tail$iv$ivx`)) {
            var40.recycleTail();
         }
      }

      `$continuation`.L$0 = `$this$writeToScope`;
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(writerScope);
      `$continuation`.L$2 = var36;
      `$continuation`.L$3 = var38;
      `$continuation`.J$0 = start;
      `$continuation`.J$1 = endInclusive;
      `$continuation`.I$0 = var37;
      `$continuation`.label = 1;
      if (var36.flush(`$continuation`) === var35) {
         return var35;
      }
   }

   return Unit.INSTANCE;
}

public fun File.writeChannel(coroutineContext: CoroutineContext = Dispatchers.getIO() as CoroutineContext): ByteWriteChannel {
   return ByteReadChannelOperationsKt.reader(
         GlobalScope.INSTANCE,
         new CoroutineName("file-writer").plus(coroutineContext),
         true,
         new io.ktor.util.cio.FileChannelsKt.writeChannel.1(`$this$writeChannel`, null)
      )
      .getChannel();
}

@JvmSynthetic
fun `writeChannel$default`(var0: File, var1: CoroutineContext, var2: Int, var3: Any): ByteWriteChannel {
   if ((var2 and 1) != 0) {
      var1 = Dispatchers.getIO();
   }

   return writeChannel(var0, var1);
}

fun `readChannel$lambda$0`(`$this_readChannel`: File): RandomAccessFile {
   return new RandomAccessFile(`$this_readChannel`, "r");
}

fun `readChannel$lambda$1`(`$randomAccessFile$delegate`: Lazy<? extends RandomAccessFile>): RandomAccessFile {
   return `$randomAccessFile$delegate`.getValue() as RandomAccessFile;
}

fun `readChannel$lambda$2`(`$randomAccessFile$delegate`: Lazy, it: java.lang.Throwable): Unit {
   readChannel$lambda$1(`$randomAccessFile$delegate`).close();
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$readChannel$lambda$1`(`$randomAccessFile$delegate`: Lazy): RandomAccessFile {
   return readChannel$lambda$1(`$randomAccessFile$delegate`);
}
