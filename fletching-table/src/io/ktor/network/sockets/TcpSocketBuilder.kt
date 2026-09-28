package io.ktor.network.sockets

import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.SocketOptions.AcceptorOptions
import io.ktor.network.sockets.SocketOptions.PeerSocketOptions
import io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions

public class TcpSocketBuilder internal constructor(selector: SelectorManager, options: PeerSocketOptions) :
   Configurable<TcpSocketBuilder, SocketOptions.PeerSocketOptions> {
   private final val selector: SelectorManager
   public open var options: PeerSocketOptions

   init {
      this.selector = selector;
      this.options = options;
   }

   public suspend fun connect(hostname: String, port: Int, configure: (TCPClientSocketOptions) -> Unit = ...): Socket {
      return this.connect(new InetSocketAddress(hostname, port), configure, `$completion`);
   }

   public suspend fun bind(hostname: String = ..., port: Int = ..., configure: (AcceptorOptions) -> Unit = ...): ServerSocket {
      return this.bind(new InetSocketAddress(hostname, port), configure, `$completion`);
   }

   public suspend fun connect(remoteAddress: SocketAddress, configure: (TCPClientSocketOptions) -> Unit = ...): Socket {
      val var10000: SelectorManager = this.selector;
      val var4: SocketOptions.TCPClientSocketOptions = this.getOptions().tcpConnect$ktor_network();
      configure.invoke(var4);
      return ConnectUtilsJvmKt.tcpConnect(var10000, remoteAddress, var4, `$completion`);
   }

   public suspend fun bind(localAddress: SocketAddress? = ..., configure: (AcceptorOptions) -> Unit = ...): ServerSocket {
      val var10000: SelectorManager = this.selector;
      val var4: SocketOptions.AcceptorOptions = this.getOptions().tcpAccept$ktor_network();
      configure.invoke(var4);
      return ConnectUtilsJvmKt.tcpBind(var10000, localAddress, var4, `$completion`);
   }

   @JvmStatic
   fun `connect$lambda$0`(var0: SocketOptions.TCPClientSocketOptions): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `bind$lambda$0`(var0: SocketOptions.AcceptorOptions): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `connect$lambda$1`(var0: SocketOptions.TCPClientSocketOptions): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `bind$lambda$1`(var0: SocketOptions.AcceptorOptions): Unit {
      return Unit.INSTANCE;
   }
}
