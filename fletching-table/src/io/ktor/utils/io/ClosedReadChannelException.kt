package io.ktor.utils.io

public class ClosedReadChannelException(cause: Throwable? = null) : ClosedByteChannelException(cause) {
   fun ClosedReadChannelException() {
      this(null, 1, null);
   }
}
