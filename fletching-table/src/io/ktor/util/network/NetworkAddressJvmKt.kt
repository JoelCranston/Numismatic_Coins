package io.ktor.util.network

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress

public final val hostname: String
   public final get() {
      var var10000: InetSocketAddress = `$this$hostname` as? InetSocketAddress;
      if ((`$this$hostname` as? InetSocketAddress) != null) {
         val var1: java.lang.String = var10000.getHostName();
         if (var1 != null) {
            return var1;
         }
      }

      label25: {
         var10000 = `$this$hostname` as? InetSocketAddress;
         if ((`$this$hostname` as? InetSocketAddress) != null) {
            val var3: InetAddress = var10000.getAddress();
            if (var3 != null) {
               var4 = var3.getHostName();
               break label25;
            }
         }

         var4 = null;
      }

      if (var4 == null) {
         var4 = "";
      }

      return var4;
   }


public final val address: String
   public final get() {
      val var10000: InetSocketAddress = `$this$address` as? InetSocketAddress;
      if ((`$this$address` as? InetSocketAddress) != null) {
         val var1: java.lang.String = var10000.getHostString();
         if (var1 != null) {
            return var1;
         }
      }

      return "";
   }


public final val port: Int
   public final get() {
      return if ((`$this$port` as? InetSocketAddress) != null) (`$this$port` as? InetSocketAddress).getPort() else 0;
   }


public fun NetworkAddress(hostname: String, port: Int): SocketAddress {
   return new InetSocketAddress(hostname, port);
}
