package io.ktor.serialization

import io.ktor.http.ContentType
import io.ktor.http.content.OutgoingContent
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import java.nio.charset.Charset

public interface ContentConverter {
   public abstract suspend fun serialize(contentType: ContentType, charset: Charset, typeInfo: TypeInfo, value: Any?): OutgoingContent? {
   }

   public abstract suspend fun deserialize(charset: Charset, typeInfo: TypeInfo, content: ByteReadChannel): Any? {
   }
}
