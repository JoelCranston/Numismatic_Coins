package io.ktor.network.sockets

public fun SocketAddress.toJavaAddress(): java.net.SocketAddress {
   return `$this$toJavaAddress`.getAddress$ktor_network();
}

internal fun java.net.SocketAddress.toSocketAddress(): SocketAddress {
   val var10000: SocketAddress;
   if (`$this$toSocketAddress` is java.net.InetSocketAddress) {
      var10000 = new InetSocketAddress(`$this$toSocketAddress` as java.net.InetSocketAddress);
   } else {
      if (!(`$this$toSocketAddress`.getClass().getName() == "java.net.UnixDomainSocketAddress")) {
         throw new IllegalStateException("Unknown socket address type".toString());
      }

      var10000 = new UnixSocketAddress(`$this$toSocketAddress`);
   }

   return var10000;
}
