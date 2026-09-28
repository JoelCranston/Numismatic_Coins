package io.ktor.client.plugins

import io.ktor.client.statement.HttpResponse

public class ClientRequestException(response: HttpResponse, cachedResponseText: String) : ResponseException(response, cachedResponseText) {
   public open val message: String

   init {
      this.message = "Client request(${response.getCall().getRequest().getMethod().getValue()} ${response.getCall().getRequest().getUrl()}) invalid: ${response.getStatus()}. Text: \"$cachedResponseText"";
   }
}
