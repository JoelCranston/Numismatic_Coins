package io.ktor.websocket

import io.ktor.websocket.Frame.Ping
import io.ktor.websocket.Frame.Pong
import io.ktor.websocket.PingPongKt.ponger.1
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelKt
import kotlinx.coroutines.channels.SendChannel

private final val PongerCoroutineName: CoroutineName = new CoroutineName("ws-ponger")
private final val PingerCoroutineName: CoroutineName = new CoroutineName("ws-pinger")

internal fun CoroutineScope.ponger(outgoing: SendChannel<Pong>): SendChannel<Ping> {
   val channel: Channel = ChannelKt.Channel$default(5, null, null, 6, null);
   BuildersKt.launch$default(`$this$ponger`, PongerCoroutineName, null, new 1(channel, outgoing, null), 2, null);
   return channel;
}

internal fun CoroutineScope.pinger(outgoing: SendChannel<Frame>, periodMillis: Long, timeoutMillis: Long, onTimeout: (CloseReason, Continuation<Unit>) -> Any?): SendChannel<
      Pong
   > {
   val actorJob: CompletableJob = JobKt.Job$default(null, 1, null);
   val channel: Channel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
   BuildersKt.launch$default(
      `$this$pinger`,
      actorJob.plus(PingerCoroutineName),
      null,
      new io.ktor.websocket.PingPongKt.pinger.1(periodMillis, timeoutMillis, onTimeout, channel, outgoing, null),
      2,
      null
   );
   val var10000: CoroutineContext.Element = `$this$pinger`.getCoroutineContext().get(Job.Key);
   (var10000 as Job).invokeOnCompletion(PingPongKt::pinger$lambda$0);
   return channel;
}

fun `pinger$lambda$0`(`$actorJob`: CompletableJob, it: java.lang.Throwable): Unit {
   Job.DefaultImpls.cancel$default(`$actorJob`, null, 1, null);
   return Unit.INSTANCE;
}
