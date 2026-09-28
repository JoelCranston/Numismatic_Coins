package io.ktor.websocket.internals

import io.ktor.utils.io.core.ByteReadPacketKt
import java.util.Arrays
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.SourcesKt

internal fun Source.endsWith(data: ByteArray): Boolean {
   val `$this$endsWith_u24lambda_u240`: Buffer = `$this$endsWith`.getBuffer().copy();
   ByteReadPacketKt.discard(`$this$endsWith_u24lambda_u240`, ByteReadPacketKt.getRemaining(`$this$endsWith_u24lambda_u240`) - (long)data.length);
   return Arrays.equals(SourcesKt.readByteArray(`$this$endsWith_u24lambda_u240`), data);
}
