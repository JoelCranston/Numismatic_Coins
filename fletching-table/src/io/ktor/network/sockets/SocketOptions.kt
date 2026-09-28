package io.ktor.network.sockets

import java.util.HashMap

public sealed class SocketOptions protected constructor(customOptions: MutableMap<Any, Any?>) {
   protected final val customOptions: MutableMap<Any, Any?>
   public final var typeOfService: TypeOfService
   public final var reuseAddress: Boolean
   public final var reusePort: Boolean

   init {
      this.customOptions = customOptions;
      this.typeOfService = TypeOfService.Companion.getUNDEFINED-zieKYfw();
   }

   internal abstract fun copy(): SocketOptions {
   }

   protected open fun copyCommon(from: SocketOptions) {
      this.typeOfService = from.typeOfService;
      this.reuseAddress = from.reuseAddress;
      this.reusePort = from.reusePort;
   }

   internal fun peer(): io.ktor.network.sockets.SocketOptions.PeerSocketOptions {
      val var1: SocketOptions.PeerSocketOptions = new SocketOptions.PeerSocketOptions(new HashMap<>(this.customOptions));
      this.copyCommon(this);
      return var1;
   }

   internal fun tcpAccept(): io.ktor.network.sockets.SocketOptions.AcceptorOptions {
      val var1: SocketOptions.AcceptorOptions = new SocketOptions.AcceptorOptions(new HashMap<>(this.customOptions));
      var1.copyCommon(this);
      return var1;
   }

   public class AcceptorOptions internal constructor(customOptions: MutableMap<Any, Any?>) : SocketOptions(customOptions) {
      public final var backlogSize: Int = 511

      internal open fun copy(): io.ktor.network.sockets.SocketOptions.AcceptorOptions {
         val var1: SocketOptions.AcceptorOptions = new SocketOptions.AcceptorOptions(new HashMap<>(this.getCustomOptions()));
         var1.copyCommon(this);
         return var1;
      }
   }

   internal companion object {
      internal fun create(): SocketOptions {
         return new SocketOptions.GeneralSocketOptions(new HashMap<>());
      }
   }

   private class GeneralSocketOptions(customOptions: MutableMap<Any, Any?>) : SocketOptions(customOptions) {
      internal open fun copy(): io.ktor.network.sockets.SocketOptions.GeneralSocketOptions {
         val var1: SocketOptions.GeneralSocketOptions = new SocketOptions.GeneralSocketOptions(new HashMap<>(this.getCustomOptions()));
         var1.copyCommon(this);
         return var1;
      }
   }

   public open class PeerSocketOptions internal constructor(customOptions: MutableMap<Any, Any?>) : SocketOptions(customOptions) {
      public final var sendBufferSize: Int = -1
      public final var receiveBufferSize: Int = -1

      protected override fun copyCommon(from: SocketOptions) {
         super.copyCommon(from);
         if (from is SocketOptions.PeerSocketOptions) {
            this.sendBufferSize = (from as SocketOptions.PeerSocketOptions).sendBufferSize;
            this.receiveBufferSize = (from as SocketOptions.PeerSocketOptions).receiveBufferSize;
         }
      }

      internal open fun copy(): io.ktor.network.sockets.SocketOptions.PeerSocketOptions {
         val var1: SocketOptions.PeerSocketOptions = new SocketOptions.PeerSocketOptions(new HashMap<>(this.getCustomOptions()));
         var1.copyCommon(this);
         return var1;
      }

      internal fun tcpConnect(): io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions {
         val var1: SocketOptions.TCPClientSocketOptions = new SocketOptions.TCPClientSocketOptions(new HashMap<>(this.getCustomOptions()));
         this.copyCommon(this);
         return var1;
      }

      internal fun udp(): io.ktor.network.sockets.SocketOptions.UDPSocketOptions {
         val var1: SocketOptions.UDPSocketOptions = new SocketOptions.UDPSocketOptions(new HashMap<>(this.getCustomOptions()));
         this.copyCommon(this);
         return var1;
      }
   }

   public class TCPClientSocketOptions internal constructor(customOptions: MutableMap<Any, Any?>) : SocketOptions.PeerSocketOptions(customOptions) {
      public final var noDelay: Boolean = true
      public final var lingerSeconds: Int = -1
      public final var keepAlive: Boolean?
      public final var socketTimeout: Long = java.lang.Long.MAX_VALUE

      protected override fun copyCommon(from: SocketOptions) {
         super.copyCommon(from);
         if (from is SocketOptions.TCPClientSocketOptions) {
            this.noDelay = (from as SocketOptions.TCPClientSocketOptions).noDelay;
            this.lingerSeconds = (from as SocketOptions.TCPClientSocketOptions).lingerSeconds;
            this.keepAlive = (from as SocketOptions.TCPClientSocketOptions).keepAlive;
         }
      }

      internal open fun copy(): io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions {
         val var1: SocketOptions.TCPClientSocketOptions = new SocketOptions.TCPClientSocketOptions(new HashMap<>(this.getCustomOptions()));
         var1.copyCommon(this);
         return var1;
      }
   }

   public class UDPSocketOptions internal constructor(customOptions: MutableMap<Any, Any?>) : SocketOptions.PeerSocketOptions(customOptions) {
      public final var broadcast: Boolean

      protected override fun copyCommon(from: SocketOptions) {
         super.copyCommon(from);
         if (from is SocketOptions.UDPSocketOptions) {
            this.broadcast = (from as SocketOptions.UDPSocketOptions).broadcast;
         }
      }

      internal open fun copy(): io.ktor.network.sockets.SocketOptions.UDPSocketOptions {
         val var1: SocketOptions.UDPSocketOptions = new SocketOptions.UDPSocketOptions(new HashMap<>(this.getCustomOptions()));
         var1.copyCommon(this);
         return var1;
      }
   }
}
