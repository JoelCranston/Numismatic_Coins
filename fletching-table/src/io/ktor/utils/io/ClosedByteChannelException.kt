package io.ktor.utils.io

import java.io.IOException

public open class ClosedByteChannelException(cause: Throwable? = null) : IOException(if (cause != null) cause.getMessage() else null, cause) {
   open fun ClosedByteChannelException() {
      this(null, 1, null);
   }
}
