package io.ktor.http

public fun URLProtocol.isWebsocket(): Boolean {
   return `$this$isWebsocket`.getName() == "ws" || `$this$isWebsocket`.getName() == "wss";
}

public fun URLProtocol.isSecure(): Boolean {
   return `$this$isSecure`.getName() == "https" || `$this$isSecure`.getName() == "wss";
}
