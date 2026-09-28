package io.ktor.http.content

import io.ktor.http.HttpStatusCode
import kotlin.enums.EnumEntries

public enum class VersionCheckResult(statusCode: HttpStatusCode) {
   OK(HttpStatusCode.Companion.getOK()),
   NOT_MODIFIED(HttpStatusCode.Companion.getNotModified()),
   PRECONDITION_FAILED(HttpStatusCode.Companion.getPreconditionFailed())
   public final val statusCode: HttpStatusCode

   init {
      this.statusCode = statusCode;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<VersionCheckResult> {
      return $ENTRIES;
   }
}
