package io.ktor.http

import kotlin.enums.EnumEntries

public enum class CookieEncoding {
   RAW,
   DQUOTES,
   URI_ENCODING,
   BASE64_ENCODING
   @JvmStatic
   fun getEntries(): EnumEntries<CookieEncoding> {
      return $ENTRIES;
   }
}
