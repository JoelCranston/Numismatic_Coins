package io.ktor.network.sockets

import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.ReaderJob

public interface AWritable {
   public abstract fun attachForWriting(channel: ByteChannel): ReaderJob {
   }
}
