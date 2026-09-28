package io.ktor.client.engine

import kotlin.enums.EnumEntries

public enum class ProxyType {
   SOCKS,
   HTTP,
   UNKNOWN
   @JvmStatic
   fun getEntries(): EnumEntries<ProxyType> {
      return $ENTRIES;
   }
}
