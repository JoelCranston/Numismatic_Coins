package io.ktor.client.engine

import io.ktor.http.URLUtilsKt
import java.net.Proxy

public fun ProxyBuilder.http(urlString: String): Proxy {
   return `$this$http`.http(URLUtilsKt.Url(urlString));
}
