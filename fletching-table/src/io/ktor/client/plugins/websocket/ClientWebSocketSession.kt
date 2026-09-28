package io.ktor.client.plugins.websocket

import io.ktor.client.call.HttpClientCall
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import kotlin.coroutines.Continuation

public interface ClientWebSocketSession : WebSocketSession {
   public val call: HttpClientCall

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun send(`$this`: ClientWebSocketSession, frame: Frame, `$completion`: Continuation<? super Unit>): Any {
         return ClientWebSocketSession.access$send$jd(`$this`, frame, `$completion`);
      }
   }
}
