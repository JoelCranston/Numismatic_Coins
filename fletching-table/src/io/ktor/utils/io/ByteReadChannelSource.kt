package io.ktor.utils.io

import io.ktor.utils.io.ByteReadChannelSource.readAtMostTo.1
import kotlinx.coroutines.BuildersKt
import kotlinx.io.Buffer
import kotlinx.io.RawSource

internal class ByteReadChannelSource(origin: ByteReadChannel) : RawSource {
   private final val origin: ByteReadChannel

   init {
      this.origin = origin;
   }

   public override fun readAtMostTo(sink: Buffer, byteCount: Long): Long {
      if (this.origin.getReadBuffer().exhausted()) {
         BuildersKt.runBlocking$default(null, new 1(this, null), 1, null);
      }

      return if (this.origin.getReadBuffer().exhausted()) -1L else this.origin.getReadBuffer().readAtMostTo(sink, byteCount);
   }

   public override fun close() {
      ByteReadChannelKt.cancel(this.origin);
   }
}
