@file:SourceDebugExtension(["SMAP\nDurations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Durations.kt\nio/ktor/client/plugins/websocket/DurationsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,49:1\n1#2:50\n*E\n"])

package io.ktor.client.plugins.websocket

import io.ktor.websocket.WebSocketExtensionsConfig
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.time.Duration
import kotlin.time.DurationKt
import kotlin.time.DurationUnit

public final val pingInterval: Duration?
   public final inline get() {
      val var3: java.lang.Long = `$this$pingInterval`.getPingIntervalMillis();
      val var2: java.lang.Long = if (var3.longValue() > 0L) var3 else null;
      return if (var2 != null) Duration.box-impl(DurationKt.toDuration(var2, DurationUnit.MILLISECONDS)) else null;
   }


public final var pingInterval: Duration?
   public final inline get() {
      val var3: java.lang.Long = `$this$pingInterval`.getPingIntervalMillis();
      val var2: java.lang.Long = if (var3.longValue() > 0L) var3 else null;
      return if (var2 != null) Duration.box-impl(DurationKt.toDuration(var2, DurationUnit.MILLISECONDS)) else null;
   }

   public final inline set(new) {
      `$this$pingInterval`.setPingIntervalMillis(if (var1 != null) Duration.getInWholeMilliseconds-impl(var1.unbox-impl()) else 0L);
   }


public fun WebSockets(pingInterval: Duration?, maxFrameSize: Long = ...): WebSockets {
   return new WebSockets(
      if (pingInterval != null) Duration.getInWholeMilliseconds-impl(pingInterval.unbox-impl()) else 0L,
      maxFrameSize,
      new WebSocketExtensionsConfig(),
      null,
      8,
      null
   );
}

@JvmSynthetic
fun `WebSockets-dnQKTGw$default`(var0: Duration, var1: Long, var3: Int, var4: Any): WebSockets {
   if ((var3 and 2) != 0) {
      var1 = 2147483647L;
   }

   return WebSockets-dnQKTGw(var0, var1);
}
