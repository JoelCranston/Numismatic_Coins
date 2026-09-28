package io.ktor.websocket

import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.Continuation
import kotlinx.coroutines.Deferred

public interface DefaultWebSocketSession : WebSocketSession {
   public var pingIntervalMillis: Long
   public var timeoutMillis: Long
   public val closeReason: Deferred<CloseReason?>

   @InternalAPI
   public abstract fun start(negotiatedExtensions: List<WebSocketExtension<*>> = CollectionsKt.emptyList()) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun send(`$this`: DefaultWebSocketSession, frame: Frame, `$completion`: Continuation<? super Unit>): Any {
         return DefaultWebSocketSession.access$send$jd(`$this`, frame, `$completion`);
      }
   }
}
