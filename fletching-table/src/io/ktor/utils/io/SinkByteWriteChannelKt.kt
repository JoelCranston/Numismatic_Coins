package io.ktor.utils.io

import kotlinx.io.RawSink

public fun RawSink.asByteWriteChannel(): ByteWriteChannel {
   return new SinkByteWriteChannel(`$this$asByteWriteChannel`);
}
