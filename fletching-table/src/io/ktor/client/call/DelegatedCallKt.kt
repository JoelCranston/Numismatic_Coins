package io.ktor.client.call

import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.utils.io.ByteReadChannel
import kotlin.jvm.functions.Function1

public fun HttpClientCall.replaceResponse(headers: Headers = `$this$replaceResponse`.getResponse().getHeaders(), content: (HttpResponse) -> ByteReadChannel): HttpClientCall {
   return new DelegatedCall(`$this$replaceResponse`.getClient(), `$this$replaceResponse`, content, headers);
}

@JvmSynthetic
fun `replaceResponse$default`(var0: HttpClientCall, var1: Headers, var2: Function1, var3: Int, var4: Any): HttpClientCall {
   if ((var3 and 1) != 0) {
      var1 = var0.getResponse().getHeaders();
   }

   return replaceResponse(var0, var1, var2);
}
