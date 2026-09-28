package io.ktor.client.plugins.cache

import kotlin.enums.EnumEntries

internal enum class ValidateStatus {
   ShouldValidate,
   ShouldNotValidate,
   ShouldWarn
   @JvmStatic
   fun getEntries(): EnumEntries<ValidateStatus> {
      return $ENTRIES;
   }
}
