@file:SourceDebugExtension(["SMAP\nConnectUtilsJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectUtilsJvm.kt\nio/ktor/network/sockets/ConnectUtilsJvmKt\n+ 2 SelectorManager.kt\nio/ktor/network/selector/SelectorManagerKt\n*L\n1#1,64:1\n61#2,8:65\n61#2,8:73\n*S KotlinDebug\n*F\n+ 1 ConnectUtilsJvm.kt\nio/ktor/network/sockets/ConnectUtilsJvmKt\n*L\n16#1:65,8\n29#1:73,8\n*E\n"])

package io.ktor.network.sockets

import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.ConnectUtilsJvmKt.tcpConnect.1
import io.ktor.network.sockets.SocketOptions.AcceptorOptions
import io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions
import java.io.Closeable
import java.net.ProtocolFamily
import java.net.StandardProtocolFamily
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.nio.channels.spi.SelectorProvider
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension

internal suspend fun tcpConnect(selector: SelectorManager, remoteAddress: SocketAddress, socketOptions: TCPClientSocketOptions): Socket {
   var `$continuation`: Continuation;
   label42: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label42;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var15: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var `result$iv`: Closeable;
   var var9: SocketImpl;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         val var22: SelectorManager = selector;
         val var23: Int = 0;
         `result$iv` = openSocketChannelFor(selector.getProvider(), remoteAddress);

         var var10000: Any;
         try {
            val var25: SocketChannel = `result$iv` as SocketChannel;
            if (remoteAddress is InetSocketAddress) {
               JavaSocketOptionsKt.assignOptions(var25, socketOptions);
            }

            JavaSocketOptionsKt.nonBlocking(var25);
            var9 = new SocketImpl<>(var25, selector, socketOptions);
            val var10001: java.net.SocketAddress = JavaSocketAddressUtilsKt.toJavaAddress(remoteAddress);
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(selector);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(remoteAddress);
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(socketOptions);
            `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var22);
            `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var25);
            `$continuation`.L$5 = `result$iv`;
            `$continuation`.L$6 = var9;
            `$continuation`.L$7 = SpillingKt.nullOutSpilledVariable(var9);
            `$continuation`.I$0 = var23;
            `$continuation`.I$1 = 0;
            `$continuation`.I$2 = 0;
            `$continuation`.label = 1;
            var10000 = var9.connect$ktor_network(var10001, `$continuation`);
         } catch (var18: java.lang.Throwable) {
            `result$iv`.close();
            throw var18;
         }

         if (var10000 === var15) {
            return var15;
         }
         break;
      case 1:
         val var11: Int = `$continuation`.I$2;
         val var7: Int = `$continuation`.I$1;
         val `$i$f$buildOrClose`: Int = `$continuation`.I$0;
         val `$this$tcpConnect_u24lambda_u241_u240`: SocketImpl = `$continuation`.L$7 as SocketImpl;
         var9 = `$continuation`.L$6 as SocketImpl;
         `result$iv` = `$continuation`.L$5 as Closeable;
         val `$this$tcpConnect_u24lambda_u241`: SocketChannel = `$continuation`.L$4 as SocketChannel;
         val `$this$buildOrClose$iv`: SelectorManager = `$continuation`.L$3 as SelectorManager;
         socketOptions = `$continuation`.L$2 as SocketOptions.TCPClientSocketOptions;
         remoteAddress = `$continuation`.L$1 as SocketAddress;
         selector = `$continuation`.L$0 as SelectorManager;

         try {
            ResultKt.throwOnFailure(`$result`);
            break;
         } catch (var17: java.lang.Throwable) {
            `result$iv`.close();
            throw var17;
         }
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   try {
      return var9;
   } catch (var16: java.lang.Throwable) {
      `result$iv`.close();
      throw var16;
   }
}

internal suspend fun tcpBind(selector: SelectorManager, localAddress: SocketAddress?, socketOptions: AcceptorOptions): ServerSocket {
   val `result$iv`: Closeable = openServerSocketChannelFor(selector.getProvider(), localAddress);

   try {
      val var14: ServerSocketChannel = `result$iv` as ServerSocketChannel;
      if (localAddress is InetSocketAddress) {
         JavaSocketOptionsKt.assignOptions(var14, socketOptions);
      }

      JavaSocketOptionsKt.nonBlocking(var14);
      val var9: ServerSocketImpl = new ServerSocketImpl(var14, selector);
      if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
         var9.getChannel().bind(if (localAddress != null) JavaSocketAddressUtilsKt.toJavaAddress(localAddress) else null, socketOptions.getBacklogSize());
      } else {
         var9.getChannel()
            .socket()
            .bind(if (localAddress != null) JavaSocketAddressUtilsKt.toJavaAddress(localAddress) else null, socketOptions.getBacklogSize());
      }

      return var9;
   } catch (var13: java.lang.Throwable) {
      `result$iv`.close();
      throw var13;
   }
}

internal fun SelectorProvider.openSocketChannelFor(address: SocketAddress): SocketChannel {
   val var10000: SocketChannel;
   if (address is InetSocketAddress) {
      var10000 = `$this$openSocketChannelFor`.openSocketChannel();
   } else {
      if (address !is UnixSocketAddress) {
         throw new NoWhenBranchMatchedException();
      }

      val var9: Any = SelectorProvider.class
         .getMethod("openSocketChannel", ProtocolFamily.class)
         .invoke(`$this$openSocketChannelFor`, StandardProtocolFamily.valueOf("UNIX"));
      var10000 = var9 as SocketChannel;
   }

   return var10000;
}

internal fun SelectorProvider.openServerSocketChannelFor(address: SocketAddress?): ServerSocketChannel {
   val var10000: ServerSocketChannel;
   if (address == null) {
      var10000 = `$this$openServerSocketChannelFor`.openServerSocketChannel();
   } else if (address is InetSocketAddress) {
      var10000 = `$this$openServerSocketChannelFor`.openServerSocketChannel();
   } else {
      if (address !is UnixSocketAddress) {
         throw new NoWhenBranchMatchedException();
      }

      val var9: Any = SelectorProvider.class
         .getMethod("openServerSocketChannel", ProtocolFamily.class)
         .invoke(`$this$openServerSocketChannelFor`, StandardProtocolFamily.valueOf("UNIX"));
      var10000 = var9 as ServerSocketChannel;
   }

   return var10000;
}
