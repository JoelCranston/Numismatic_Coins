package io.ktor.utils.io

import java.io.IOException

public fun ByteReadChannel.cancel() {
   `$this$cancel`.cancel(new IOException("Channel was cancelled"));
}
