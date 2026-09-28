package io.ktor.http.content

import io.ktor.util.date.DateJvmKt
import java.util.Date

public fun LastModifiedVersion(lastModified: Date): LastModifiedVersion {
   return new LastModifiedVersion(DateJvmKt.GMTDate(lastModified.getTime()));
}
