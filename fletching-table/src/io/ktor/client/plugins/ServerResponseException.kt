package io.ktor.client.plugins

import io.ktor.client.statement.HttpResponse

public class ServerResponseException(response: HttpResponse, cachedResponseText: String) : ResponseException(response, cachedResponseText) {
   public open val message: String

   init {
      this.message = "Server error(${response.getCall().getRequest().getMethod().getValue()} ${response.getCall().getRequest().getUrl()}: ${response.getStatus()}. Text: \"$cachedResponseText"";
   }
}
