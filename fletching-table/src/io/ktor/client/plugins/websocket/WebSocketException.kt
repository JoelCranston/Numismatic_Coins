package io.ktor.client.plugins.websocket

public class WebSocketException(message: String, cause: Throwable?) : IllegalStateException(message, cause) {
   public constructor(message: String) : this(message, null)}
