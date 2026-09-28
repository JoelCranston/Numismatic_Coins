package io.ktor.network.sockets

import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.SocketOptions.UDPSocketOptions

public class UDPSocketBuilder internal constructor(selector: SelectorManager, options: UDPSocketOptions) :
   Configurable<UDPSocketBuilder, SocketOptions.UDPSocketOptions> {
   private final val selector: SelectorManager
   public open var options: UDPSocketOptions

   init {
      this.selector = selector;
      this.options = options;
   }

   public suspend fun bind(localAddress: SocketAddress? = ..., configure: (UDPSocketOptions) -> Unit = ...): BoundDatagramSocket {
      val var10000: SelectorManager = this.selector;
      val var4: SocketOptions.UDPSocketOptions = this.getOptions().udp$ktor_network();
      configure.invoke(var4);
      return UDPSocketBuilderJvmKt.udpBind(var10000, localAddress, var4, `$completion`);
   }

   public suspend fun bind(hostname: String = ..., port: Int = ..., configure: (UDPSocketOptions) -> Unit = ...): BoundDatagramSocket {
      return this.bind(new InetSocketAddress(hostname, port), configure, `$completion`);
   }

   public suspend fun connect(remoteAddress: SocketAddress, localAddress: SocketAddress? = ..., configure: (UDPSocketOptions) -> Unit = ...): ConnectedDatagramSocket {
      val var10000: SelectorManager = this.selector;
      val var5: SocketOptions.UDPSocketOptions = this.getOptions().udp$ktor_network();
      configure.invoke(var5);
      return UDPSocketBuilderJvmKt.udpConnect(var10000, remoteAddress, localAddress, var5, `$completion`);
   }

   @JvmStatic
   fun `bind$lambda$0`(var0: SocketOptions.UDPSocketOptions): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `bind$lambda$1`(var0: SocketOptions.UDPSocketOptions): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `connect$lambda$0`(var0: SocketOptions.UDPSocketOptions): Unit {
      return Unit.INSTANCE;
   }
}
