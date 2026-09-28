package io.ktor.serialization

import io.ktor.websocket.Frame

public class WebsocketDeserializeException(message: String, cause: Throwable? = null, frame: Frame) : WebsocketContentConvertException(message, cause) {
   public final val frame: Frame

   init {
      this.frame = frame;
   }
}
