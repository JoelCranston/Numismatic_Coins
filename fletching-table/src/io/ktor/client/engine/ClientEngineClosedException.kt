package io.ktor.client.engine

public class ClientEngineClosedException(cause: Throwable? = null) : IllegalStateException("Client already closed") {
   public open val cause: Throwable?

   init {
      this.cause = cause;
   }

   fun ClientEngineClosedException() {
      this(null, 1, null);
   }
}
