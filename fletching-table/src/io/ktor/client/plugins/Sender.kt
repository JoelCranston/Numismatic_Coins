package io.ktor.client.plugins

import io.ktor.client.call.HttpClientCall
import io.ktor.client.request.HttpRequestBuilder

public interface Sender {
   public abstract suspend fun execute(requestBuilder: HttpRequestBuilder): HttpClientCall {
   }
}
