package io.ktor.client.request

import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.OutgoingContent
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.CoroutineContext

@InternalAPI
public fun interface ResponseAdapter {
   public abstract fun adapt(
      data: HttpRequestData,
      status: HttpStatusCode,
      headers: Headers,
      responseBody: ByteReadChannel,
      outgoingContent: OutgoingContent,
      callContext: CoroutineContext
   ): Any? {
   }
}
