package io.ktor.client.call

import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpResponseKt
import io.ktor.http.HttpHeaders
import kotlin.reflect.KClass

public class NoTransformationFoundException(response: HttpResponse, from: KClass<*>, to: KClass<*>) : UnsupportedOperationException {
   public open val message: String

   init {
      this.message = StringsKt.trimIndent(
         "\n        Expected response body of the type '$to' but was '$from'\n        In response from `${HttpResponseKt.getRequest(response).getUrl()}`\n        Response status `${response.getStatus()}`\n        Response header `ContentType: ${response.getHeaders()
            .get(HttpHeaders.INSTANCE.getContentType())}` \n        Request header `Accept: ${HttpResponseKt.getRequest(response)
            .getHeaders()
            .get(HttpHeaders.INSTANCE.getAccept())}`\n        \n        You can read how to resolve NoTransformationFoundException at FAQ: \n        https://ktor.io/docs/faq.html#no-transformation-found-exception\n    "
      );
   }
}
