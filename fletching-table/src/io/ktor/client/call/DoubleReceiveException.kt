package io.ktor.client.call

public class DoubleReceiveException(call: HttpClientCall) : IllegalStateException {
   public open val message: String

   init {
      this.message = "Response already received: $call";
   }
}
