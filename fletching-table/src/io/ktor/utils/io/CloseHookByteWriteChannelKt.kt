package io.ktor.utils.io

import kotlin.coroutines.Continuation

public fun ByteWriteChannel.onClose(onClose: (Continuation<Unit>) -> Any?): ByteWriteChannel {
   return new CloseHookByteWriteChannel(`$this$onClose`, onClose);
}
