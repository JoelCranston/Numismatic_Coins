package io.ktor.network.sockets

import io.ktor.network.selector.SelectorManager

public class SocketBuilder internal constructor(selector: SelectorManager, options: SocketOptions) : Configurable<SocketBuilder, SocketOptions> {
   private final val selector: SelectorManager
   public open var options: SocketOptions

   init {
      this.selector = selector;
      this.options = options;
   }

   public fun tcp(): TcpSocketBuilder {
      return new TcpSocketBuilder(this.selector, this.getOptions().peer$ktor_network());
   }

   public fun udp(): UDPSocketBuilder {
      return new UDPSocketBuilder(this.selector, this.getOptions().peer$ktor_network().udp$ktor_network());
   }
}
