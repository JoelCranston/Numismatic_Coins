package io.ktor.network.sockets

import io.ktor.network.selector.SelectInterest
import io.ktor.network.selector.Selectable
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.SocketImpl.connect.1
import io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions
import java.net.InetAddress
import java.nio.channels.SocketChannel
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nSocketImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SocketImpl.kt\nio/ktor/network/sockets/SocketImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,109:1\n1#2:110\n*E\n"])
internal class SocketImpl<S extends SocketChannel>(channel: Any, selector: SelectorManager, socketOptions: TCPClientSocketOptions? = ...) : NIOSocketImpl(
         (S)channel, selector, null, socketOptions
      ),
   Socket {
   public open val channel: Any

   public open val localAddress: SocketAddress
      public open get() {
         val localAddress: java.net.SocketAddress = if (JavaSocketOptionsKt.getJava7NetworkApisAvailable())
            this.getChannel().getLocalAddress()
            else
            this.getChannel().socket().getLocalSocketAddress();
         if (localAddress != null) {
            val var10000: SocketAddress = JavaSocketAddressUtilsKt.toSocketAddress(localAddress);
            if (var10000 != null) {
               return var10000;
            }
         }

         throw new IllegalStateException("Channel is not yet bound");
      }


   public open val remoteAddress: SocketAddress
      public open get() {
         val remoteAddress: java.net.SocketAddress = if (JavaSocketOptionsKt.getJava7NetworkApisAvailable())
            this.getChannel().getRemoteAddress()
            else
            this.getChannel().socket().getRemoteSocketAddress();
         if (remoteAddress != null) {
            val var10000: SocketAddress = JavaSocketAddressUtilsKt.toSocketAddress(remoteAddress);
            if (var10000 != null) {
               return var10000;
            }
         }

         throw new IllegalStateException("Channel is not yet connected");
      }


   init {
      this.channel = (S)channel;
      if (this.getChannel().isBlocking()) {
         throw new IllegalArgumentException("Channel need to be configured as non-blocking.".toString());
      }
   }

   internal suspend fun connect(target: java.net.SocketAddress): Socket {
      var `$continuation`: Continuation;
      label56: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label56;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (this.getChannel().connect(target)) {
               return this;
            }

            this.wantConnect(true);
            val var10000: SelectorManager = this.getSelector();
            val var10001: Selectable = this;
            val var10002: SelectInterest = SelectInterest.CONNECT;
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(target);
            `$continuation`.label = 1;
            if (var10000.select(var10001, var10002, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            target = `$continuation`.L$0 as java.net.SocketAddress;
            ResultKt.throwOnFailure(`$result`);
            break;
         case 2:
            target = `$continuation`.L$0 as java.net.SocketAddress;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (true) {
         while (!this.getChannel().finishConnect()) {
            this.wantConnect(true);
            val var6: SelectorManager = this.getSelector();
            val var7: Selectable = this;
            val var8: SelectInterest = SelectInterest.CONNECT;
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(target);
            `$continuation`.label = 2;
            if (var6.select(var7, var8, `$continuation`) === var5) {
               return var5;
            }
         }

         if (!this.inetSelfConnect()) {
            this.wantConnect(false);
            return this;
         }

         if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
            this.getChannel().close();
         } else {
            this.getChannel().socket().close();
         }
      }
   }

   private fun wantConnect(state: Boolean = true) {
      this.interestOp(SelectInterest.CONNECT, state);
   }

   private fun inetSelfConnect(): Boolean {
      val localAddress: java.net.SocketAddress = if (JavaSocketOptionsKt.getJava7NetworkApisAvailable())
         this.getChannel().getLocalAddress()
         else
         this.getChannel().socket().getLocalSocketAddress();
      val remoteAddress: java.net.SocketAddress = if (JavaSocketOptionsKt.getJava7NetworkApisAvailable())
         this.getChannel().getRemoteAddress()
         else
         this.getChannel().socket().getRemoteSocketAddress();
      if (localAddress != null && remoteAddress != null) {
         val localInetSocketAddress: java.net.InetSocketAddress = localAddress as? java.net.InetSocketAddress;
         val remoteInetSocketAddress: java.net.InetSocketAddress = remoteAddress as? java.net.InetSocketAddress;
         if (localInetSocketAddress == null && (remoteAddress as? java.net.InetSocketAddress) == null) {
            return false;
         } else {
            var var10: java.lang.String;
            label83: {
               if (localInetSocketAddress != null) {
                  val var10000: InetAddress = localInetSocketAddress.getAddress();
                  if (var10000 != null) {
                     var10 = var10000.getHostAddress();
                     if (var10 != null) {
                        break label83;
                     }
                  }
               }

               var10 = "";
            }

            label77: {
               if (remoteInetSocketAddress != null) {
                  val var11: InetAddress = remoteInetSocketAddress.getAddress();
                  if (var11 != null) {
                     var10 = var11.getHostAddress();
                     if (var10 != null) {
                        break label77;
                     }
                  }
               }

               var10 = "";
            }

            label71: {
               if (remoteInetSocketAddress != null) {
                  val var13: InetAddress = remoteInetSocketAddress.getAddress();
                  if (var13 != null) {
                     var14 = var13.isAnyLocalAddress();
                     break label71;
                  }
               }

               var14 = false;
            }

            return (if (localInetSocketAddress != null) localInetSocketAddress.getPort() else null)
                  == (if (remoteInetSocketAddress != null) remoteInetSocketAddress.getPort() else null)
               && (var14 || var10 == var10);
         }
      } else {
         throw new IllegalStateException("localAddress and remoteAddress should not be null.");
      }
   }
}
