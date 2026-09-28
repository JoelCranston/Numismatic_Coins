@file:SourceDebugExtension(["SMAP\nDefaultWebSocketSession.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultWebSocketSession.kt\nio/ktor/websocket/DefaultWebSocketSessionKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,401:1\n1#2:402\n*E\n"])

package io.ktor.websocket

import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.time.Duration
import kotlin.time.DurationKt
import kotlin.time.DurationUnit
import kotlinx.coroutines.CoroutineName
import org.slf4j.Logger

internal final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.websocket.WebSocket")
public const val PINGER_DISABLED: Long = 0L
private final val IncomingProcessorCoroutineName: CoroutineName = new CoroutineName("ws-incoming-processor")
private final val OutgoingProcessorCoroutineName: CoroutineName = new CoroutineName("ws-outgoing-processor")
private final val NORMAL_CLOSE: CloseReason = new CloseReason(CloseReason.Codes.NORMAL, "OK")

public final var pingInterval: Duration?
   public final inline get() {
      val var3: java.lang.Long = `$this$pingInterval`.getPingIntervalMillis();
      val var2: java.lang.Long = if (var3.longValue() > 0L) var3 else null;
      return if (var2 != null) Duration.box-impl(DurationKt.toDuration(var2, DurationUnit.MILLISECONDS)) else null;
   }

   public final inline set(newDuration) {
      `$this$pingInterval`.setPingIntervalMillis(if (newDuration != null) Duration.getInWholeMilliseconds-impl(newDuration.unbox-impl()) else 0L);
   }


public final var timeout: Duration
   public final inline get() {
      return DurationKt.toDuration(`$this$timeout`.getTimeoutMillis(), DurationUnit.MILLISECONDS);
   }

   public final inline set(newDuration) {
      `$this$timeout`.setTimeoutMillis(Duration.getInWholeMilliseconds-impl(var1));
   }


public fun DefaultWebSocketSession(session: WebSocketSession, pingIntervalMillis: Long = 0L, timeoutMillis: Long = 15000L): DefaultWebSocketSession {
   if (session is DefaultWebSocketSession) {
      throw new IllegalArgumentException("Cannot wrap other DefaultWebSocketSession".toString());
   } else {
      return new DefaultWebSocketSessionImpl(session, pingIntervalMillis, timeoutMillis);
   }
}

@JvmSynthetic
fun `DefaultWebSocketSession$default`(var0: WebSocketSession, var1: Long, var3: Long, var5: Int, var6: Any): DefaultWebSocketSession {
   if ((var5 and 2) != 0) {
      var1 = 0L;
   }

   if ((var5 and 4) != 0) {
      var3 = 15000L;
   }

   return DefaultWebSocketSession(var0, var1, var3);
}

@JvmSynthetic
fun `access$getIncomingProcessorCoroutineName$p`(): CoroutineName {
   return IncomingProcessorCoroutineName;
}

@JvmSynthetic
fun `access$getNORMAL_CLOSE$p`(): CloseReason {
   return NORMAL_CLOSE;
}

@JvmSynthetic
fun `access$getOutgoingProcessorCoroutineName$p`(): CoroutineName {
   return OutgoingProcessorCoroutineName;
}
