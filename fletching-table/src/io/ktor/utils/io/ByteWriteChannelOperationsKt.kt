@file:SourceDebugExtension(["SMAP\nByteWriteChannelOperations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteWriteChannelOperations.kt\nio/ktor/utils/io/ByteWriteChannelOperationsKt\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n*L\n1#1,248:1\n195#2,28:249\n*S KotlinDebug\n*F\n+ 1 ByteWriteChannelOperations.kt\nio/ktor/utils/io/ByteWriteChannelOperationsKt\n*L\n227#1:249,28\n*E\n"])

package io.ktor.utils.io

import io.ktor.utils.io.ByteWriteChannelOperationsKt.NO_CALLBACK.1
import io.ktor.utils.io.ByteWriteChannelOperationsKt.writePacket.2
import io.ktor.utils.io.core.BytePacketBuilderKt
import io.ktor.utils.io.core.ByteReadPacketKt
import io.ktor.utils.io.core.StringsKt
import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function0
import kotlin.jvm.functions.Function2
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job
import kotlinx.coroutines.intrinsics.CancellableKt
import kotlinx.io.Buffer
import kotlinx.io.CoreKt
import kotlinx.io.RawSource
import kotlinx.io.Segment
import kotlinx.io.SegmentKt
import kotlinx.io.Sink
import kotlinx.io.SinksKt
import kotlinx.io.Source
import kotlinx.io.unsafe.UnsafeBufferOperations

public final val isCompleted: Boolean
   public final get() {
      return `$this$isCompleted`.getJob().isCompleted();
   }


public final val isCancelled: Boolean
   public final get() {
      return `$this$isCancelled`.getJob().isCancelled();
   }


private final val NO_CALLBACK: 1 = new 1()

public suspend fun ByteWriteChannel.writeByte(value: Byte) {
   `$this$writeByte`.getWriteBuffer().writeByte(value);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeByte`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeShort(value: Short) {
   `$this$writeShort`.getWriteBuffer().writeShort(value);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeShort`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeInt(value: Int) {
   `$this$writeInt`.getWriteBuffer().writeInt(value);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeInt`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeFloat(value: Float) {
   SinksKt.writeFloat(`$this$writeFloat`.getWriteBuffer(), value);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeFloat`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeDouble(value: Double) {
   SinksKt.writeDouble(`$this$writeDouble`.getWriteBuffer(), value);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeDouble`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeLong(value: Long) {
   `$this$writeLong`.getWriteBuffer().writeLong(value);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeLong`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeByteArray(array: ByteArray) {
   Sink.write$default(`$this$writeByteArray`.getWriteBuffer(), array, 0, 0, 6, null);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeByteArray`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeSource(source: Source) {
   val var10000: Any = writePacket(`$this$writeSource`, source, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeString(value: String) {
   StringsKt.writeText$default(`$this$writeString`.getWriteBuffer(), value, 0, 0, null, 14, null);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeString`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeFully(value: ByteArray, startIndex: Int = ..., endIndex: Int = ...) {
   `$this$writeFully`.getWriteBuffer().write(value, startIndex, endIndex);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeFully`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `writeFully$default`(var0: ByteWriteChannel, var1: ByteArray, var2: Int, var3: Int, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 2) != 0) {
      var2 = 0;
   }

   if ((var5 and 4) != 0) {
      var3 = var1.length;
   }

   return writeFully(var0, var1, var2, var3, var4);
}

public suspend fun ByteWriteChannel.writeBuffer(source: RawSource) {
   val var10000: Any = writePacket(`$this$writeBuffer`, CoreKt.buffered(source), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeBuffer(value: RawSource, length: Long) {
   `$this$writeBuffer`.getWriteBuffer().write(value, length);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeBuffer`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writeStringUtf8(value: String) {
   StringsKt.writeText$default(`$this$writeStringUtf8`.getWriteBuffer(), value, 0, 0, null, 14, null);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writeStringUtf8`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writePacket(copy: Buffer) {
   `$this$writePacket`.getWriteBuffer().transferFrom(copy);
   val var10000: Any = ByteWriteChannelKt.flushIfNeeded(`$this$writePacket`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteWriteChannel.writePacket(source: Source) {
   var `$continuation`: Continuation;
   label33: {
      if (`$completion` is 2) {
         `$continuation` = `$completion` as 2;
         if (((`$completion` as 2).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label33;
         }
      }

      `$continuation` = new 2(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         break;
      case 1:
         source = `$continuation`.L$1 as Source;
         `$this$writePacket` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (!source.exhausted()) {
      `$this$writePacket`.getWriteBuffer().write(source, ByteReadPacketKt.getRemaining(source));
      `$continuation`.L$0 = `$this$writePacket`;
      `$continuation`.L$1 = source;
      `$continuation`.label = 1;
      if (ByteWriteChannelKt.flushIfNeeded(`$this$writePacket`, `$continuation`) === var5) {
         return var5;
      }
   }

   return Unit.INSTANCE;
}

public fun ByteWriteChannel.close(cause: Throwable?) {
   if (cause == null) {
      fireAndForget(new io.ktor.utils.io.ByteWriteChannelOperationsKt.close.1(`$this$close`));
   } else {
      `$this$close`.cancel(cause);
   }
}

public suspend fun ChannelJob.join() {
   val var10000: Any = `$this$join`.getJob().join(`$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public fun ChannelJob.getCancellationException(): CancellationException {
   return `$this$getCancellationException`.getJob().getCancellationException();
}

public fun ChannelJob.invokeOnCompletion(block: (Throwable?) -> Unit): DisposableHandle {
   return `$this$invokeOnCompletion`.getJob().invokeOnCompletion(block);
}

@Deprecated(message = "Maintained for binary compatibility", level = DeprecationLevel.HIDDEN)
@JvmSynthetic
public fun ChannelJob.invokeOnCompletion(block: () -> Unit) {
   `$this$invokeOnCompletion`.getJob().invokeOnCompletion(ByteWriteChannelOperationsKt::invokeOnCompletion$lambda$0);
}

public fun ChannelJob.cancel() {
   Job.DefaultImpls.cancel$default(`$this$cancel`.getJob(), null, 1, null);
}

public fun CoroutineScope.writer(
   coroutineContext: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   autoFlush: Boolean = false,
   block: (WriterScope, Continuation<Unit>) -> Any?
): WriterJob {
   return writer(`$this$writer`, coroutineContext, new ByteChannel(false, 1, null), block);
}

@JvmSynthetic
fun `writer$default`(var0: CoroutineScope, var1: CoroutineContext, var2: Boolean, var3: Function2, var4: Int, var5: Any): WriterJob {
   if ((var4 and 1) != 0) {
      var1 = EmptyCoroutineContext.INSTANCE;
   }

   if ((var4 and 2) != 0) {
      var2 = false;
   }

   return writer(var0, var1, var2, var3);
}

public fun CoroutineScope.writer(
   coroutineContext: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   channel: ByteChannel,
   block: (WriterScope, Continuation<Unit>) -> Any?
): WriterJob {
   val var5: Job = BuildersKt.launch$default(
      `$this$writer`, coroutineContext, null, new io.ktor.utils.io.ByteWriteChannelOperationsKt.writer.job.1(block, channel, null), 2, null
   );
   var5.invokeOnCompletion(ByteWriteChannelOperationsKt::writer$lambda$0$0);
   return new WriterJob(channel, var5);
}

@JvmSynthetic
fun `writer$default`(var0: CoroutineScope, var1: CoroutineContext, var2: ByteChannel, var3: Function2, var4: Int, var5: Any): WriterJob {
   if ((var4 and 1) != 0) {
      var1 = EmptyCoroutineContext.INSTANCE;
   }

   return writer(var0, var1, var2, var3);
}

public suspend fun ByteWriteChannel.write(desiredSpace: Int = ..., block: (ByteArray, Int, Int) -> Int): Int {
   var `$continuation`: Continuation;
   label44: {
      if (`$completion` is io.ktor.utils.io.ByteWriteChannelOperationsKt.write.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteWriteChannelOperationsKt.write.1;
         if (((`$completion` as io.ktor.utils.io.ByteWriteChannelOperationsKt.write.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label44;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteWriteChannelOperationsKt.write.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var15: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var written: Int;
   switch ($continuation.label) {
      case 0: {
         ResultKt.throwOnFailure(`$result`);
         val var19: Int = BytePacketBuilderKt.getSize(`$this$write`.getWriteBuffer());
         val var20: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val var22: Buffer = `$this$write`.getWriteBuffer().getBuffer();
         val `tail$iv`: Segment = var22.writableSegment(desiredSpace);
         val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
         val `bytesWritten$iv`: Int = (block.invoke(`data$iv`, Boxing.boxInt(`tail$iv`.getLimit()), Boxing.boxInt(`data$iv`.length)) as java.lang.Number)
            .intValue();
         if (`bytesWritten$iv` == desiredSpace) {
            `tail$iv`.writeBackData(`data$iv`, `bytesWritten$iv`);
            `tail$iv`.setLimit(`tail$iv`.getLimit() + `bytesWritten$iv`);
            var22.setSizeMut(var22.getSizeMut() + (long)`bytesWritten$iv`);
         } else {
            if (0 > `bytesWritten$iv` || `bytesWritten$iv` > `tail$iv`.getRemainingCapacity()) {
               throw new IllegalStateException(
                  ("Invalid number of bytes written: $`bytesWritten$iv`. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString()
               );
            }

            if (`bytesWritten$iv` != 0) {
               `tail$iv`.writeBackData(`data$iv`, `bytesWritten$iv`);
               `tail$iv`.setLimit(`tail$iv`.getLimit() + `bytesWritten$iv`);
               var22.setSizeMut(var22.getSizeMut() + (long)`bytesWritten$iv`);
            } else if (SegmentKt.isEmpty(`tail$iv`)) {
               var22.recycleTail();
            }
         }

         val var21: Int = BytePacketBuilderKt.getSize(`$this$write`.getWriteBuffer());
         written = var21 - var19;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$write`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(block);
         `$continuation`.I$0 = desiredSpace;
         `$continuation`.I$1 = var19;
         `$continuation`.I$2 = var21;
         `$continuation`.I$3 = written;
         `$continuation`.label = 1;
         if (ByteWriteChannelKt.flushIfNeeded(`$this$write`, `$continuation`) === var15) {
            return var15;
         }
         break;
      }
      case 1: {
         written = `$continuation`.I$3;
         val after: Int = `$continuation`.I$2;
         val before: Int = `$continuation`.I$1;
         desiredSpace = `$continuation`.I$0;
         block = `$continuation`.L$1 as Function3;
         `$this$write` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      }
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return Boxing.boxInt(written);
}

@JvmSynthetic
fun `write$default`(var0: ByteWriteChannel, var1: Int, var2: Function3, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 1) != 0) {
      var1 = 1;
   }

   return write(var0, var1, var2, var3);
}

public suspend fun ByteWriteChannel.awaitFreeSpace() {
   val var10000: Any = `$this$awaitFreeSpace`.flush(`$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

internal fun <R> ((Continuation<Any>) -> Any?).fireAndForget() {
   CancellableKt.startCoroutineCancellable(`$this$fireAndForget`, NO_CALLBACK);
}

fun `invokeOnCompletion$lambda$0`(`$block`: Function0, it: java.lang.Throwable): Unit {
   `$block`.invoke();
   return Unit.INSTANCE;
}

fun `writer$lambda$0$0`(`$channel`: ByteChannel, it: java.lang.Throwable): Unit {
   if (it != null && !`$channel`.isClosedForWrite()) {
      `$channel`.cancel(it);
   }

   return Unit.INSTANCE;
}
