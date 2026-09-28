package io.ktor.client.call

import io.ktor.client.HttpClient
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.utils.io.ByteReadChannel

internal class DelegatedCall(client: HttpClient,
   originCall: HttpClientCall,
   responseContent: (HttpResponse) -> ByteReadChannel,
   responseHeaders: Headers = originCall.getResponse().getHeaders()
) : HttpClientCall(client) {
   init {
      this.setRequest(new DelegatedRequest(this, originCall.getRequest()));
      this.setResponse(new DelegatedResponse(this, originCall.getResponse(), responseContent, responseHeaders));
   }
}
