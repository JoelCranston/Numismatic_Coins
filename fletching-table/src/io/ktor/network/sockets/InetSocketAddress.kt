package io.ktor.network.sockets

import java.net.InetAddress

public class InetSocketAddress internal constructor(address: java.net.InetSocketAddress) : SocketAddress() {
   internal open val address: java.net.InetSocketAddress

   public final val hostname: String
      public final get() {
         val var10000: java.lang.String = this.getAddress$ktor_network().getHostName();
         return var10000;
      }


   public final val port: Int
      public final get() {
         return this.getAddress$ktor_network().getPort();
      }


   init {
      this.address = address;
   }

   public fun resolveAddress(): ByteArray? {
      val var10000: InetAddress = this.getAddress$ktor_network().getAddress();
      return if (var10000 != null) var10000.getAddress() else null;
   }

   public constructor(hostname: String, port: Int) : this(new java.net.InetSocketAddress(hostname, port))
   public operator fun component1(): String {
      return this.getHostname();
   }

   public operator fun component2(): Int {
      return this.getPort();
   }

   public fun copy(hostname: String = this.getHostname(), port: Int = this.getPort()): InetSocketAddress {
      return new InetSocketAddress(hostname, port);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (!(this.getClass() == (if (other != null) other.getClass() else null))) {
         return false;
      } else {
         return this.getAddress$ktor_network() == (other as InetSocketAddress).getAddress$ktor_network();
      }
   }

   public override fun hashCode(): Int {
      return this.getAddress$ktor_network().hashCode();
   }

   public override fun toString(): String {
      val var10000: java.lang.String = this.getAddress$ktor_network().toString();
      return var10000;
   }
}
