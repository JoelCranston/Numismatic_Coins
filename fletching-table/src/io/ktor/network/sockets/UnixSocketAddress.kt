package io.ktor.network.sockets

import java.lang.reflect.Method

public class UnixSocketAddress internal constructor(address: java.net.SocketAddress) : SocketAddress() {
   internal open val address: java.net.SocketAddress

   public final val path: String
      public final get() {
         val var10000: Method = Companion.checkSupportForUnixDomainSockets$ktor_network().getMethod("getPath");
         return var10000.invoke(this.getAddress$ktor_network()).toString();
      }


   init {
      this.address = address;
      if (!(this.getAddress$ktor_network().getClass().getName() == "java.net.UnixDomainSocketAddress")) {
         throw new IllegalStateException("address should be java.net.UnixDomainSocketAddress".toString());
      }
   }

   public constructor(path: String)  {
      val var2: Any = Companion.checkSupportForUnixDomainSockets$ktor_network().getMethod("of", java.lang.String.class).invoke(null, path);
      this(var2 as java.net.SocketAddress);
   }

   public operator fun component1(): String {
      return this.getPath();
   }

   public fun copy(path: String = this.getPath()): UnixSocketAddress {
      return new UnixSocketAddress(path);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (!(this.getClass() == (if (other != null) other.getClass() else null))) {
         return false;
      } else {
         return this.getAddress$ktor_network() == (other as UnixSocketAddress).getAddress$ktor_network();
      }
   }

   public override fun hashCode(): Int {
      return this.getAddress$ktor_network().hashCode();
   }

   public override fun toString(): String {
      return this.getAddress$ktor_network().toString();
   }

   @JvmStatic
   fun {
      var var0: Class;
      try {
         var0 = Class.forName("java.net.UnixDomainSocketAddress");
      } catch (var2: ClassNotFoundException) {
         var0 = null;
      }

      unixDomainSocketAddressClass = var0;
   }

   public companion object {
      private final val unixDomainSocketAddressClass: Class<*>?

      internal fun checkSupportForUnixDomainSockets(): Class<*> {
         val var10000: Class = UnixSocketAddress.access$getUnixDomainSocketAddressClass$cp();
         if (var10000 == null) {
            throw new IllegalStateException("Unix domain sockets are unsupported before Java 16.".toString());
         } else {
            return var10000;
         }
      }

      public fun isSupported(): Boolean {
         return UnixSocketAddress.access$getUnixDomainSocketAddressClass$cp() != null;
      }
   }
}
