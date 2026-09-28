package io.ktor.client.plugins

import io.ktor.client.statement.HttpResponse

public open class ResponseException(response: HttpResponse, cachedResponseText: String) : IllegalStateException(
      "Bad response: $response. Text: \"$cachedResponseText""
   ) {
   public final val response: HttpResponse

   init {
      this.response = response;
   }
}
