package io.ktor.utils.io.core

import java.nio.ByteBuffer
import kotlinx.io.Sink
import kotlinx.io.SinksJvmKt

public fun Sink.writeByteBuffer(bb: ByteBuffer) {
   SinksJvmKt.write(`$this$writeByteBuffer`, bb);
}
