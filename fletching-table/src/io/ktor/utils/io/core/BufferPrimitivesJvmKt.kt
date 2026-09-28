package io.ktor.utils.io.core

import java.nio.ByteBuffer
import kotlinx.io.Buffer
import kotlinx.io.BuffersJvmKt

@Deprecated(message = "[writeByteBuffer] is deprecated. Consider using [transferFrom] instead", replaceWith = @ReplaceWith(expression = "this.transferFrom(source)", imports = []))
public fun Buffer.writeByteBuffer(source: ByteBuffer) {
   BuffersJvmKt.transferFrom(`$this$writeByteBuffer`, source);
}
