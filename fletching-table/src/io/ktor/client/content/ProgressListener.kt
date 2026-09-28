package io.ktor.client.content

public fun interface ProgressListener {
   public abstract suspend fun onProgress(bytesSentTotal: Long, contentLength: Long?) {
   }
}
