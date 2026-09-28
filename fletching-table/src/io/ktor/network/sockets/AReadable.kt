package io.ktor.network.sockets

import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.WriterJob

public interface AReadable {
   public abstract fun attachForReading(channel: ByteChannel): WriterJob {
   }
}
