package io.ktor.client.plugins.sse

import kotlin.time.Duration

public class SSEConfig {
   internal final var showCommentEvents: Boolean
   internal final var showRetryEvents: Boolean
   public final var reconnectionTime: Duration
   public final var maxReconnectionAttempts: Int
   public final var bufferPolicy: SSEBufferPolicy = SSEBufferPolicy.Off.INSTANCE as SSEBufferPolicy

   public fun showCommentEvents() {
      this.showCommentEvents = true;
   }

   public fun showRetryEvents() {
      this.showRetryEvents = true;
   }
}
