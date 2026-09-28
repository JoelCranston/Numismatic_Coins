package io.ktor.utils.io

import io.ktor.utils.io.CountedByteWriteChannel.flush.1
import io.ktor.utils.io.core.BytePacketBuilderKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.io.Sink

public class CountedByteWriteChannel(delegate: ByteWriteChannel) : ByteWriteChannel {
   private final val delegate: ByteWriteChannel
   private final var initial: Int
   private final var flushedCount: Int

   public open val autoFlush: Boolean
      public open get() {
         return this.delegate.getAutoFlush();
      }


   public final val totalBytesWritten: Long
      public final get() {
         return this.flushedCount + BytePacketBuilderKt.getSize(this.getWriteBuffer()) - this.initial;
      }


   public open val isClosedForWrite: Boolean
      public open get() {
         return this.delegate.isClosedForWrite();
      }


   public open val closedCause: Throwable?
      public open get() {
         return this.delegate.getClosedCause();
      }


   @InternalAPI
   public open val writeBuffer: Sink
      public open get() {
         return this.delegate.getWriteBuffer();
      }


   init {
      this.delegate = delegate;
      this.initial = BytePacketBuilderKt.getSize(this.delegate.getWriteBuffer());
   }

   public override suspend fun flush() {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            this.flushedCount = this.flushedCount + BytePacketBuilderKt.getSize(this.getWriteBuffer());
            val var10000: ByteWriteChannel = this.delegate;
            `$continuation`.label = 1;
            if (var10000.flush(`$continuation`) === var4) {
               return var4;
            }
            break;
         case 1:
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      this.initial = BytePacketBuilderKt.getSize(this.getWriteBuffer());
      return Unit.INSTANCE;
   }

   public override suspend fun flushAndClose() {
      val var10000: Any = this.delegate.flushAndClose(`$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   public override fun cancel(cause: Throwable?) {
      this.delegate.cancel(cause);
   }
}
