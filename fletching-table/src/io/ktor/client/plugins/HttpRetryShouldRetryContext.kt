package io.ktor.client.plugins

public class HttpRetryShouldRetryContext(retryCount: Int) {
   public final val retryCount: Int

   init {
      this.retryCount = retryCount;
   }
}
