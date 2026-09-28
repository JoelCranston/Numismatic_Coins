package io.ktor.client.network.sockets

import java.net.ConnectException

public class ConnectTimeoutException(message: String, cause: Throwable? = null) : ConnectException(message) {
   public open val cause: Throwable?

   init {
      this.cause = cause;
   }
}
