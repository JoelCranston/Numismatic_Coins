package io.ktor.sse

public sealed interface ServerSentEventMetadata<T> {
   public val data: Any?
   public val event: String?
   public val id: String?
   public val retry: Long?
   public val comments: String?
}
