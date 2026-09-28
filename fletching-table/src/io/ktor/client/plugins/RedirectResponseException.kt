package io.ktor.client.plugins

import io.ktor.client.statement.HttpResponse

public class RedirectResponseException(response: HttpResponse, cachedResponseText: String) : ResponseException(response, cachedResponseText) {
   public open val message: String

   init {
      this.message = "Unhandled redirect: ${response.getCall().getRequest().getMethod().getValue()} ${response.getCall().getRequest().getUrl()}. Status: ${response.getStatus()}. Text: \"$cachedResponseText"";
   }
}
