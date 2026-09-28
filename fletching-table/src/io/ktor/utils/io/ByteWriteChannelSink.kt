package io.ktor.utils.io

import io.ktor.utils.io.ByteWriteChannelSink.write.1
import io.ktor.utils.io.core.BytePacketBuilderKt
import kotlinx.coroutines.BuildersKt
import kotlinx.io.Buffer
import kotlinx.io.RawSink

internal class ByteWriteChannelSink(origin: ByteWriteChannel) : RawSink {
   private final val origin: ByteWriteChannel

   init {
      this.origin = origin;
   }

   public override fun write(source: Buffer, byteCount: Long) {
      ByteReadChannelOperationsKt.rethrowCloseCauseIfNeeded(this.origin);
      this.origin.getWriteBuffer().write(source, byteCount);
      val var4: ByteWriteChannel = this.origin;
      if ((this.origin as? ByteChannel) != null && (this.origin as? ByteChannel).getAutoFlush()
         || BytePacketBuilderKt.getSize(this.origin.getWriteBuffer()) >= 1048576) {
         BuildersKt.runBlocking$default(null, new 1(this, null), 1, null);
      }
   }

   public override fun flush() {
      BuildersKt.runBlocking$default(null, new io.ktor.utils.io.ByteWriteChannelSink.flush.1(this, null), 1, null);
   }

   public override fun close() {
      BuildersKt.runBlocking$default(null, new io.ktor.utils.io.ByteWriteChannelSink.close.1(this, null), 1, null);
   }
}
