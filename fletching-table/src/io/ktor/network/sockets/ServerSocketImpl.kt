package io.ktor.network.sockets

import io.ktor.network.selector.SelectInterest
import io.ktor.network.selector.Selectable
import io.ktor.network.selector.SelectableBase
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.ServerSocketImpl.acceptSuspend.1
import java.net.StandardSocketOptions
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.JobKt

@SourceDebugExtension(["SMAP\nServerSocketImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServerSocketImpl.kt\nio/ktor/network/sockets/ServerSocketImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,73:1\n1#2:74\n*E\n"])
internal class ServerSocketImpl(channel: ServerSocketChannel, selector: SelectorManager) : SelectableBase, ServerSocket {
   public open val channel: ServerSocketChannel
   public final val selector: SelectorManager
   public open val socketContext: CompletableJob

   public open val localAddress: SocketAddress
      public open get() {
         val localAddress: java.net.SocketAddress = if (JavaSocketOptionsKt.getJava7NetworkApisAvailable())
            this.getChannel().getLocalAddress()
            else
            this.getChannel().socket().getLocalSocketAddress();
         return JavaSocketAddressUtilsKt.toSocketAddress(localAddress);
      }


   init {
      this.channel = channel;
      this.selector = selector;
      if (this.getChannel().isBlocking()) {
         throw new IllegalArgumentException("Channel need to be configured as non-blocking.".toString());
      } else {
         this.socketContext = JobKt.Job$default(null, 1, null);
      }
   }

   public override suspend fun accept(): Socket {
      val var10000: SocketChannel = this.getChannel().accept();
      return if (var10000 != null) this.accepted(var10000) else this.acceptSuspend(`$completion`);
   }

   private suspend fun acceptSuspend(): Socket {
      var `$continuation`: Continuation;
      label37: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label37;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            break;
         case 1:
            ResultKt.throwOnFailure(`$result`);
            val var10000: SocketChannel = this.getChannel().accept();
            if (var10000 != null) {
               return this.accepted(var10000);
            }
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val var10: SocketChannel;
      do {
         this.interestOp(SelectInterest.ACCEPT, true);
         val var9: SelectorManager = this.selector;
         val var10001: Selectable = this;
         val var10002: SelectInterest = SelectInterest.ACCEPT;
         `$continuation`.label = 1;
         if (var9.select(var10001, var10002, `$continuation`) === var6) {
            return var6;
         }

         var10 = this.getChannel().accept();
      } while (var10 == null);

      return this.accepted(var10);
   }

   private fun accepted(nioChannel: SocketChannel): Socket {
      this.interestOp(SelectInterest.ACCEPT, false);
      nioChannel.configureBlocking(false);
      if (this.getLocalAddress() is InetSocketAddress) {
         if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
            nioChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
         } else {
            nioChannel.socket().setTcpNoDelay(true);
         }
      }

      return new SocketImpl(nioChannel, this.selector, null, 4, null);
   }

   public override fun close() {
      label20: {
         try {
            try {
               this.getChannel().close();
            } catch (var3: java.lang.Throwable) {
               this.selector.notifyClosed(this);
            }

            this.selector.notifyClosed(this);
         } catch (var4: java.lang.Throwable) {
            this.getSocketContext().completeExceptionally(var4);
         }
      }
   }
}
