package io.ktor.utils.io

public fun ByteWriteChannel.counted(): CountedByteWriteChannel {
   return new CountedByteWriteChannel(`$this$counted`);
}
