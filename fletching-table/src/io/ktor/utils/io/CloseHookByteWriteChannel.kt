package io.ktor.utils.io

import io.ktor.utils.io.CloseHookByteWriteChannel.flushAndClose.1
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function1
import kotlinx.io.Sink

internal class CloseHookByteWriteChannel(delegate: ByteWriteChannel, onClose: (Continuation<Unit>) -> Any?) : ByteWriteChannel {
   private final val delegate: ByteWriteChannel
   private final val onClose: (Continuation<Unit>) -> Any?

   public open val autoFlush: Boolean
      public open get() {
         return this.delegate.getAutoFlush();
      }


   public open val closedCause: Throwable?
   public open val isClosedForWrite: Boolean
   public open val writeBuffer: Sink

   init {
      this.delegate = delegate;
      this.onClose = onClose;
   }

   public override suspend fun flushAndClose() {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val var10000: ByteWriteChannel = this.delegate;
            `$continuation`.label = 1;
            if (var10000.flushAndClose(`$continuation`) === var4) {
               return var4;
            }
            break;
         case 1:
            ResultKt.throwOnFailure(`$result`);
            break;
         case 2:
            ResultKt.throwOnFailure(`$result`);
            return Unit.INSTANCE;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val var5: Function1 = this.onClose;
      `$continuation`.label = 2;
      return if (var5.invoke(`$continuation`) === var4) var4 else Unit.INSTANCE;
   }

   public override suspend fun flush() {
      return this.delegate.flush(`$completion`);
   }

   public override fun cancel(cause: Throwable?) {
      this.delegate.cancel(cause);
   }
}
