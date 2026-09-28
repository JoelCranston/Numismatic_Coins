package io.ktor.http.auth

import kotlin.enums.EnumEntries

public enum class HeaderValueEncoding {
   QUOTED_WHEN_REQUIRED,
   QUOTED_ALWAYS,
   URI_ENCODE
   @JvmStatic
   fun getEntries(): EnumEntries<HeaderValueEncoding> {
      return $ENTRIES;
   }
}
