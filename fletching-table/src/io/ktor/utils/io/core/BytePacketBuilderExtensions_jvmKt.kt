package io.ktor.utils.io.core

import java.nio.ByteBuffer
import kotlinx.io.BuffersJvmKt
import kotlinx.io.Sink

public fun Sink.writeFully(buffer: ByteBuffer) {
   BuffersJvmKt.transferFrom(`$this$writeFully`.getBuffer(), buffer);
}
