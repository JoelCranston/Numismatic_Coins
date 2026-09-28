@file:SourceDebugExtension(["SMAP\nWriteSuspendSession.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WriteSuspendSession.kt\nio/ktor/utils/io/jvm/nio/WriteSuspendSessionKt\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n*L\n1#1,62:1\n195#2,28:63\n*S KotlinDebug\n*F\n+ 1 WriteSuspendSession.kt\nio/ktor/utils/io/jvm/nio/WriteSuspendSessionKt\n*L\n54#1:63,28\n*E\n"])

package io.ktor.utils.io.jvm.nio

import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt.writeSuspendSession.1
import java.nio.ByteBuffer
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Segment
import kotlinx.io.SegmentKt
import kotlinx.io.unsafe.UnsafeBufferOperations

@Deprecated(message = "writeSuspendSession deprecated, use writeWhile instead", replaceWith = @ReplaceWith(expression = "writeWhile { buffer -> }", imports = []), level = DeprecationLevel.WARNING)
public suspend fun ByteWriteChannel.writeSuspendSession(block: (WriteSuspendSession, Continuation<Unit>) -> Any?) {
   var `$continuation`: Continuation;
   label60: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label60;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);

         var var10000: Any;
         try {
            val var10001: WriteSuspendSession = new WriteSuspendSession(`$this$writeSuspendSession`);
            `$continuation`.L$0 = `$this$writeSuspendSession`;
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(block);
            `$continuation`.label = 1;
            var10000 = block.invoke(var10001, `$continuation`);
         } catch (var8: java.lang.Throwable) {
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$writeSuspendSession`);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(block);
            `$continuation`.L$2 = var8;
            `$continuation`.label = 3;
            if (`$this$writeSuspendSession`.flush(`$continuation`) === var6) {
               return var6;
            }

            throw var8;
         }

         if (var10000 === var6) {
            return var6;
         }
         break;
      case 1:
         block = `$continuation`.L$1 as Function2;
         `$this$writeSuspendSession` = `$continuation`.L$0 as ByteWriteChannel;

         try {
            ResultKt.throwOnFailure(`$result`);
            break;
         } catch (var9: java.lang.Throwable) {
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$continuation`.L$0 as ByteWriteChannel);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(block);
            `$continuation`.L$2 = var9;
            `$continuation`.label = 3;
            if (`$this$writeSuspendSession`.flush(`$continuation`) === var6) {
               return var6;
            }

            throw var9;
         }
      case 2:
         block = `$continuation`.L$1 as Function2;
         `$this$writeSuspendSession` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         return Unit.INSTANCE;
      case 3:
         val var3: java.lang.Throwable = `$continuation`.L$2 as java.lang.Throwable;
         block = `$continuation`.L$1 as Function2;
         `$this$writeSuspendSession` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         throw var3;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   try {
      ;
   } catch (var7: java.lang.Throwable) {
      `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$writeSuspendSession`);
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(block);
      `$continuation`.L$2 = var7;
      `$continuation`.label = 3;
      if (`$this$writeSuspendSession`.flush(`$continuation`) === var6) {
         return var6;
      }

      throw var7;
   }

   `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$writeSuspendSession`);
   `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(block);
   `$continuation`.label = 2;
   return if (`$this$writeSuspendSession`.flush(`$continuation`) === var6) var6 else Unit.INSTANCE;
}

public suspend inline fun ByteWriteChannel.writeWhile(crossinline block: (ByteBuffer) -> Boolean) {
   var `$continuation`: Continuation;
   label62: {
      if (`$completion` is io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt.writeWhile.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt.writeWhile.1;
         if (((`$completion` as io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt.writeWhile.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label62;
         }
      }

      `$continuation` = new io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt.writeWhile.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var20: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var `$i$f$writeWhile`: Int;
   var done: Ref.BooleanRef;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$i$f$writeWhile` = 0;
         done = new Ref.BooleanRef();
         break;
      case 1:
         `$i$f$writeWhile` = `$continuation`.I$0;
         done = `$continuation`.L$2 as Ref.BooleanRef;
         block = `$continuation`.L$1 as Function1;
         `$this$writeWhile` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (!done.element) {
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      val `buffer$iv`: Buffer = `$this$writeWhile`.getWriteBuffer().getBuffer();
      val `tail$iv`: Segment = `buffer$iv`.writableSegment(1);
      val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
      val var10001: Int = `tail$iv`.getLimit();
      val buffer: ByteBuffer = ByteBuffer.wrap(`data$iv`, var10001, `data$iv`.length - var10001);
      done.element = !block.invoke(buffer) as java.lang.Boolean;
      val `bytesWritten$iv`: Int = buffer.position() - var10001;
      if (`bytesWritten$iv` == 1) {
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

      `$continuation`.L$0 = `$this$writeWhile`;
      `$continuation`.L$1 = block;
      `$continuation`.L$2 = done;
      `$continuation`.I$0 = `$i$f$writeWhile`;
      `$continuation`.label = 1;
      if (`$this$writeWhile`.flush(`$continuation`) === var20) {
         return var20;
      }
   }

   return Unit.INSTANCE;
}

fun ByteWriteChannel.`writeWhile$$forInline`(block: (ByteBuffer?) -> java.lang.Boolean, `$completion`: Continuation<? super Unit>): Any {
   val done: Ref.BooleanRef = new Ref.BooleanRef();

   while (!done.element) {
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      val `buffer$iv`: Buffer = `$this$writeWhile`.getWriteBuffer().getBuffer();
      val `tail$iv`: Segment = `buffer$iv`.writableSegment(1);
      val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
      val var10001: Int = `tail$iv`.getLimit();
      val endExclusive: Int = `data$iv`.length.intValue();
      val start: Int = var10001.intValue();
      val buffer: ByteBuffer = ByteBuffer.wrap(`data$iv`, start, endExclusive - start);
      done.element = !block.invoke(buffer) as java.lang.Boolean;
      val `bytesWritten$iv`: Int = buffer.position() - start.intValue();
      if (`bytesWritten$iv` == 1) {
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

      InlineMarker.mark(0);
      `$this$writeWhile`.flush(`$completion`);
      InlineMarker.mark(1);
   }

   return Unit.INSTANCE;
}
