package io.ktor.serialization.kotlinx

import io.ktor.http.ContentType
import io.ktor.serialization.Configuration
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.StringFormat

public fun Configuration.serialization(contentType: ContentType, format: BinaryFormat) {
   Configuration.register$default(`$this$serialization`, contentType, new KotlinxSerializationConverter(format), null, 4, null);
}

public fun Configuration.serialization(contentType: ContentType, format: StringFormat) {
   Configuration.register$default(`$this$serialization`, contentType, new KotlinxSerializationConverter(format), null, 4, null);
}
