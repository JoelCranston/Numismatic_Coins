package io.ktor.network.sockets

public fun SocketAddress.port(): Int {
   if (`$this$port` is InetSocketAddress) {
      return (`$this$port` as InetSocketAddress).getPort();
   } else {
      throw new UnsupportedOperationException("SocketAddress $`$this$port` does not have a port");
   }
}
