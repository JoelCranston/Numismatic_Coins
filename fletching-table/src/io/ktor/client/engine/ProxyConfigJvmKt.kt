package io.ktor.client.engine

import java.net.Proxy
import java.net.SocketAddress
import java.net.Proxy.Type

public final val type: ProxyType
   public final get() {
      val var10000: Type = `$this$type`.type();
      var var1: ProxyType;
      switch (var10000 == null ? -1 : ProxyConfigJvmKt.WhenMappings.$EnumSwitchMapping$0[var10000.ordinal()]) {
         case 1:
            var1 = ProxyType.SOCKS;
            break;
         case 2:
            var1 = ProxyType.HTTP;
            break;
         default:
            var1 = ProxyType.UNKNOWN;
      }

      return var1;
   }


public fun Proxy.resolveAddress(): SocketAddress {
   val var10000: SocketAddress = `$this$resolveAddress`.address();
   return var10000;
}
// $VF: Class flags could not be determined
@JvmSynthetic
internal class WhenMappings {
   @JvmStatic
   fun {
      val var0: IntArray = new int[Type.values().length];

      try {
         var0[Type.SOCKS.ordinal()] = 1;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[Type.HTTP.ordinal()] = 2;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
