package io.ktor.utils.io

public class ClosedWriteChannelException(cause: Throwable? = null) : ClosedByteChannelException(cause) {
   fun ClosedWriteChannelException() {
      this(null, 1, null);
   }
}
