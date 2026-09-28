package io.ktor.http

import kotlin.enums.EnumEntries

public enum class RangeUnits(unitToken: String) {
   Bytes("bytes"),
   None("none")
   public final val unitToken: String

   init {
      this.unitToken = unitToken;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<RangeUnits> {
      return $ENTRIES;
   }
}
