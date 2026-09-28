@file:SourceDebugExtension(["SMAP\nJavaSocketOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaSocketOptions.kt\nio/ktor/network/sockets/JavaSocketOptionsKt\n+ 2 TypeOfService.kt\nio/ktor/network/sockets/TypeOfService\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,140:1\n30#2:141\n30#2:142\n30#2:144\n30#2:145\n1#3:143\n*S KotlinDebug\n*F\n+ 1 JavaSocketOptions.kt\nio/ktor/network/sockets/JavaSocketOptionsKt\n*L\n28#1:141\n30#1:142\n98#1:144\n100#1:145\n*E\n"])

package io.ktor.network.sockets

import java.net.StandardSocketOptions
import java.nio.channels.DatagramChannel
import java.nio.channels.SelectableChannel
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import kotlin.jvm.internal.SourceDebugExtension

internal final val java7NetworkApisAvailable: Boolean

internal fun SelectableChannel.nonBlocking() {
   `$this$nonBlocking`.configureBlocking(false);
}

internal fun SelectableChannel.assignOptions(options: SocketOptions) {
   if (`$this$assignOptions` is SocketChannel) {
      if (!TypeOfService.equals-impl0(options.getTypeOfService-zieKYfw(), TypeOfService.Companion.getUNDEFINED-zieKYfw())) {
         if (java7NetworkApisAvailable) {
            (`$this$assignOptions` as SocketChannel).setOption(StandardSocketOptions.IP_TOS, options.getTypeOfService-zieKYfw() and 255);
         } else {
            (`$this$assignOptions` as SocketChannel).socket().setTrafficClass(options.getTypeOfService-zieKYfw() and 255);
         }
      }

      if (options.getReuseAddress()) {
         if (java7NetworkApisAvailable) {
            (`$this$assignOptions` as SocketChannel).setOption(StandardSocketOptions.SO_REUSEADDR, true);
         } else {
            (`$this$assignOptions` as SocketChannel).socket().setReuseAddress(true);
         }
      }

      if (options.getReusePort()) {
         SocketOptionsPlatformCapabilities.INSTANCE.setReusePort(`$this$assignOptions` as SocketChannel);
      }

      if (options is SocketOptions.PeerSocketOptions) {
         var var10: Int = (options as SocketOptions.PeerSocketOptions).getReceiveBufferSize();
         var it: Int = var10.intValue();
         var var38: Int = if (it > 0) var10 else null;
         if ((if (it > 0) var10 else null) != null) {
            it = var38.intValue();
            if (java7NetworkApisAvailable) {
               (`$this$assignOptions` as SocketChannel).setOption(StandardSocketOptions.SO_RCVBUF, it);
            } else {
               (`$this$assignOptions` as SocketChannel).socket().setReceiveBufferSize(it);
            }
         }

         var10 = (options as SocketOptions.PeerSocketOptions).getSendBufferSize();
         it = var10.intValue();
         var38 = if (it > 0) var10 else null;
         if ((if (it > 0) var10 else null) != null) {
            it = var38.intValue();
            if (java7NetworkApisAvailable) {
               (`$this$assignOptions` as SocketChannel).setOption(StandardSocketOptions.SO_SNDBUF, it);
            } else {
               (`$this$assignOptions` as SocketChannel).socket().setSendBufferSize(it);
            }
         }
      }

      if (options is SocketOptions.TCPClientSocketOptions) {
         val var12: Int = (options as SocketOptions.TCPClientSocketOptions).getLingerSeconds();
         var itx: Int = var12.intValue();
         val var40: Int = if (itx >= 0) var12 else null;
         if ((if (itx >= 0) var12 else null) != null) {
            itx = var40.intValue();
            if (java7NetworkApisAvailable) {
               (`$this$assignOptions` as SocketChannel).setOption(StandardSocketOptions.SO_LINGER, itx);
            } else {
               (`$this$assignOptions` as SocketChannel).socket().setSoLinger(true, itx);
            }
         }

         val var41: java.lang.Boolean = (options as SocketOptions.TCPClientSocketOptions).getKeepAlive();
         if (var41 != null) {
            val itxx: Boolean = var41;
            if (java7NetworkApisAvailable) {
               (`$this$assignOptions` as SocketChannel).setOption(StandardSocketOptions.SO_KEEPALIVE, itxx);
            } else {
               (`$this$assignOptions` as SocketChannel).socket().setKeepAlive(itxx);
            }
         }

         if (java7NetworkApisAvailable) {
            (`$this$assignOptions` as SocketChannel)
               .setOption(StandardSocketOptions.TCP_NODELAY, (options as SocketOptions.TCPClientSocketOptions).getNoDelay());
         } else {
            (`$this$assignOptions` as SocketChannel).socket().setTcpNoDelay((options as SocketOptions.TCPClientSocketOptions).getNoDelay());
         }
      }
   }

   if (`$this$assignOptions` is ServerSocketChannel) {
      if (options.getReuseAddress()) {
         if (java7NetworkApisAvailable) {
            (`$this$assignOptions` as ServerSocketChannel).setOption(StandardSocketOptions.SO_REUSEADDR, true);
         } else {
            (`$this$assignOptions` as ServerSocketChannel).socket().setReuseAddress(true);
         }
      }

      if (options.getReusePort()) {
         SocketOptionsPlatformCapabilities.INSTANCE.setReusePort(`$this$assignOptions` as ServerSocketChannel);
      }
   }

   if (`$this$assignOptions` is DatagramChannel) {
      if (!TypeOfService.equals-impl0(options.getTypeOfService-zieKYfw(), TypeOfService.Companion.getUNDEFINED-zieKYfw())) {
         if (java7NetworkApisAvailable) {
            (`$this$assignOptions` as DatagramChannel).setOption(StandardSocketOptions.IP_TOS, options.getTypeOfService-zieKYfw() and 255);
         } else {
            (`$this$assignOptions` as DatagramChannel).socket().setTrafficClass(options.getTypeOfService-zieKYfw() and 255);
         }
      }

      if (options.getReuseAddress()) {
         if (java7NetworkApisAvailable) {
            (`$this$assignOptions` as DatagramChannel).setOption(StandardSocketOptions.SO_REUSEADDR, true);
         } else {
            (`$this$assignOptions` as DatagramChannel).socket().setReuseAddress(true);
         }
      }

      if (options.getReusePort()) {
         SocketOptionsPlatformCapabilities.INSTANCE.setReusePort(`$this$assignOptions` as DatagramChannel);
      }

      if (options is SocketOptions.UDPSocketOptions) {
         if (java7NetworkApisAvailable) {
            (`$this$assignOptions` as DatagramChannel)
               .setOption(StandardSocketOptions.SO_BROADCAST, (options as SocketOptions.UDPSocketOptions).getBroadcast());
         } else {
            (`$this$assignOptions` as DatagramChannel).socket().setBroadcast((options as SocketOptions.UDPSocketOptions).getBroadcast());
         }
      }

      if (options is SocketOptions.PeerSocketOptions) {
         var var15: Int = (options as SocketOptions.PeerSocketOptions).getReceiveBufferSize();
         var itxx: Int = var15.intValue();
         var var44: Int = if (itxx > 0) var15 else null;
         if ((if (itxx > 0) var15 else null) != null) {
            itxx = var44.intValue();
            if (java7NetworkApisAvailable) {
               (`$this$assignOptions` as DatagramChannel).setOption(StandardSocketOptions.SO_RCVBUF, itxx);
            } else {
               (`$this$assignOptions` as DatagramChannel).socket().setReceiveBufferSize(itxx);
            }
         }

         var15 = (options as SocketOptions.PeerSocketOptions).getSendBufferSize();
         itxx = var15.intValue();
         var44 = if (itxx > 0) var15 else null;
         if ((if (itxx > 0) var15 else null) != null) {
            itxx = var44.intValue();
            if (java7NetworkApisAvailable) {
               (`$this$assignOptions` as DatagramChannel).setOption(StandardSocketOptions.SO_SNDBUF, itxx);
            } else {
               (`$this$assignOptions` as DatagramChannel).socket().setSendBufferSize(itxx);
            }
         }
      }
   }
}
