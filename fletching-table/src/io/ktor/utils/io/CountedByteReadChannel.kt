package io.ktor.utils.io

import io.ktor.utils.io.CountedByteReadChannel.awaitContent.1
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlinx.io.Buffer

public class CountedByteReadChannel(delegate: ByteReadChannel) : ByteReadChannel {
   public final val delegate: ByteReadChannel
   private final val buffer: Buffer
   private final var initial: Long
   private final var consumed: Long

   public final val totalBytesRead: Long
      public final get() {
         this.updateConsumed();
         return this.consumed;
      }


   public open val closedCause: Throwable?
      public open get() {
         return this.delegate.getClosedCause();
      }


   public open val isClosedForRead: Boolean
      public open get() {
         return this.buffer.exhausted() && this.delegate.isClosedForRead();
      }


   @InternalAPI
   public open val readBuffer: Buffer
      public open get() {
         this.transferFromDelegate();
         return this.buffer;
      }


   init {
      this.delegate = delegate;
      this.buffer = new Buffer();
   }

   public override suspend fun awaitContent(min: Int): Boolean {
      var `$continuation`: Continuation;
      label28: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label28;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (this.getReadBuffer().getSize() >= min) {
               return Boxing.boxBoolean(true);
            }

            var10000 = this.delegate;
            `$continuation`.I$0 = min;
            `$continuation`.label = 1;
            var10000 = (ByteReadChannel)var10000.awaitContent(min, `$continuation`);
            if (var10000 === var5) {
               return var5;
            }
            break;
         case 1:
            min = `$continuation`.I$0;
            ResultKt.throwOnFailure(`$result`);
            var10000 = (ByteReadChannel)`$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (var10000 as java.lang.Boolean) {
         this.transferFromDelegate();
         return Boxing.boxBoolean(true);
      } else {
         return Boxing.boxBoolean(false);
      }
   }

   private fun transferFromDelegate() {
      this.updateConsumed();
      this.initial = this.initial + this.buffer.transferFrom(this.delegate.getReadBuffer());
   }

   public override fun cancel(cause: Throwable?) {
      this.delegate.cancel(cause);
      this.buffer.close();
   }

   private fun updateConsumed() {
      this.consumed = this.consumed + (this.initial - this.buffer.getSize());
      this.initial = this.buffer.getSize();
   }
}
