package io.ktor.websocket

import kotlin.coroutines.Continuation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

public interface WebSocketSession : CoroutineScope {
   public var masking: Boolean
   public var maxFrameSize: Long
   public val incoming: ReceiveChannel<Frame>
   public val outgoing: SendChannel<Frame>
   public val extensions: List<WebSocketExtension<*>>

   public open suspend fun send(frame: Frame) {
      return send$suspendImpl(this, frame, `$completion`);
   }

   public abstract suspend fun flush() {
   }

   @Deprecated(message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = ["kotlinx.coroutines.cancel"]), level = DeprecationLevel.ERROR)
   public abstract fun terminate() {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @java.lang.Deprecated
      @JvmStatic
      fun send(`$this`: WebSocketSession, frame: Frame, `$completion`: Continuation<? super Unit>): Any {
         return WebSocketSession.access$send$jd(`$this`, frame, `$completion`);
      }
   }
}
