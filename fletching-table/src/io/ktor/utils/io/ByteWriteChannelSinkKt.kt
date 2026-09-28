package io.ktor.utils.io

import kotlinx.io.RawSink

public fun ByteWriteChannel.asSink(): RawSink {
   return new ByteWriteChannelSink(`$this$asSink`);
}
