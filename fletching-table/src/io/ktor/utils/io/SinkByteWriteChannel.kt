package io.ktor.utils.io

import java.io.IOException
import kotlinx.io.CoreKt
import kotlinx.io.RawSink
import kotlinx.io.Sink

internal class SinkByteWriteChannel(origin: RawSink) : ByteWriteChannel {
   private final val buffer: Sink

   public open val isClosedForWrite: Boolean
      public open get() {
         return this.closed != null;
      }


   public open val closedCause: Throwable?
      public open get() {
         return if (this.closed as CloseToken != null) CloseToken.wrapCause$default(this.closed as CloseToken, null, 1, null) else null;
      }


   @InternalAPI
   public open val writeBuffer: Sink
      public open get() {
         if (this.isClosedForWrite()) {
            var var10000: java.lang.Throwable = this.getClosedCause();
            if (var10000 == null) {
               var10000 = new IOException("Channel is closed for write");
            }

            throw var10000;
         } else {
            return this.buffer;
         }
      }


   init {
      this.buffer = CoreKt.buffered(origin);
   }

   public override suspend fun flush() {
      this.getWriteBuffer().flush();
      return Unit.INSTANCE;
   }

   public override suspend fun flushAndClose() {
      this.getWriteBuffer().flush();
      return if (!closed$FU.compareAndSet(this, null, CloseTokenKt.getCLOSED())) Unit.INSTANCE else Unit.INSTANCE;
   }

   public override fun cancel(cause: Throwable?) {
      if (closed$FU.compareAndSet(this, null, if (cause == null) CloseTokenKt.getCLOSED() else new CloseToken(cause))) {
         ;
      }
   }
}
