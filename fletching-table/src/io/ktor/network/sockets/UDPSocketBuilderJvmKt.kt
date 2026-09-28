@file:SourceDebugExtension(["SMAP\nUDPSocketBuilderJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UDPSocketBuilderJvm.kt\nio/ktor/network/sockets/UDPSocketBuilderJvmKt\n+ 2 SelectorManager.kt\nio/ktor/network/selector/SelectorManagerKt\n*L\n1#1,43:1\n61#2,8:44\n61#2,8:52\n*S KotlinDebug\n*F\n+ 1 UDPSocketBuilderJvm.kt\nio/ktor/network/sockets/UDPSocketBuilderJvmKt\n*L\n14#1:44,8\n32#1:52,8\n*E\n"])

package io.ktor.network.sockets

import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.SocketOptions.UDPSocketOptions
import java.io.Closeable
import java.nio.channels.DatagramChannel
import kotlin.jvm.internal.SourceDebugExtension

internal suspend fun udpConnect(selector: SelectorManager, remoteAddress: SocketAddress, localAddress: SocketAddress?, options: UDPSocketOptions): ConnectedDatagramSocket {
   val `result$iv`: Closeable = selector.getProvider().openDatagramChannel();

   try {
      val var12: DatagramChannel = `result$iv` as DatagramChannel;
      JavaSocketOptionsKt.assignOptions(var12, options);
      JavaSocketOptionsKt.nonBlocking(var12);
      if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
         var12.bind(if (localAddress != null) JavaSocketAddressUtilsKt.toJavaAddress(localAddress) else null);
      } else {
         var12.socket().bind(if (localAddress != null) JavaSocketAddressUtilsKt.toJavaAddress(localAddress) else null);
      }

      var12.connect(JavaSocketAddressUtilsKt.toJavaAddress(remoteAddress));
      return new DatagramSocketImpl(var12, selector);
   } catch (var11: java.lang.Throwable) {
      `result$iv`.close();
      throw var11;
   }
}

internal suspend fun udpBind(selector: SelectorManager, localAddress: SocketAddress?, options: UDPSocketOptions): BoundDatagramSocket {
   val `result$iv`: Closeable = selector.getProvider().openDatagramChannel();

   try {
      val var11: DatagramChannel = `result$iv` as DatagramChannel;
      JavaSocketOptionsKt.assignOptions(var11, options);
      JavaSocketOptionsKt.nonBlocking(var11);
      if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
         var11.bind(if (localAddress != null) JavaSocketAddressUtilsKt.toJavaAddress(localAddress) else null);
      } else {
         var11.socket().bind(if (localAddress != null) JavaSocketAddressUtilsKt.toJavaAddress(localAddress) else null);
      }

      return new DatagramSocketImpl(var11, selector);
   } catch (var10: java.lang.Throwable) {
      `result$iv`.close();
      throw var10;
   }
}
