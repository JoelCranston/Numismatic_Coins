package io.ktor.network.sockets

import io.ktor.network.selector.SelectorManager

public fun aSocket(selector: SelectorManager): SocketBuilder {
   return new SocketBuilder(selector, SocketOptions.Companion.create$ktor_network());
}

@Deprecated(message = "noDelay is true by default", replaceWith = @ReplaceWith(expression = "this", imports = []))
public fun <T : Configurable<Any, *>> Any.tcpNoDelay(): Any {
   return (T)`$this$tcpNoDelay`.configure(BuildersKt::tcpNoDelay$lambda$0);
}

fun SocketOptions.`tcpNoDelay$lambda$0`(): Unit {
   if (`$this$configure` is SocketOptions.TCPClientSocketOptions) {
      (`$this$configure` as SocketOptions.TCPClientSocketOptions).setNoDelay(true);
   }

   return Unit.INSTANCE;
}
