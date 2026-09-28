package io.ktor.client.request

import io.ktor.client.call.HttpClientCall
import io.ktor.http.HttpMessage
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import io.ktor.http.content.OutgoingContent
import io.ktor.util.Attributes
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope

public interface HttpRequest : HttpMessage, CoroutineScope {
   public val call: HttpClientCall

   public open val coroutineContext: CoroutineContext
      public open get() {
         return this.getCall().getCoroutineContext();
      }


   public val method: HttpMethod
   public val url: Url
   public val attributes: Attributes
   public val content: OutgoingContent

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun getCoroutineContext(`$this`: HttpRequest): CoroutineContext {
         return HttpRequest.access$getCoroutineContext$jd(`$this`);
      }
   }
}
