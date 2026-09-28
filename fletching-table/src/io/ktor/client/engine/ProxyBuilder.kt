package io.ktor.client.engine

import io.ktor.http.Url
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.Proxy.Type

public object ProxyBuilder {
   public fun http(url: Url): Proxy {
      return new Proxy(Type.HTTP, new InetSocketAddress(url.getHost(), url.getPort()));
   }

   public fun socks(host: String, port: Int): Proxy {
      return new Proxy(Type.SOCKS, new InetSocketAddress(host, port));
   }
}
