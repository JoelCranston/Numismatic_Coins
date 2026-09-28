@file:SourceDebugExtension(["SMAP\nBroadcast.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Broadcast.kt\nkotlinx/coroutines/channels/BroadcastKt\n+ 2 CoroutineExceptionHandler.kt\nkotlinx/coroutines/CoroutineExceptionHandlerKt\n*L\n1#1,124:1\n47#2,4:125\n*S KotlinDebug\n*F\n+ 1 Broadcast.kt\nkotlinx/coroutines/channels/BroadcastKt\n*L\n21#1:125,4\n*E\n"])

package kotlinx.coroutines.channels

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CoroutineContextKt
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineScopeKt
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastKt.broadcast.2
import kotlinx.coroutines.channels.BroadcastKt.broadcast..inlined.CoroutineExceptionHandler.1

@Deprecated(message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported", level = DeprecationLevel.ERROR)
@ObsoleteCoroutinesApi
public fun <E> ReceiveChannel<E>.broadcast(capacity: Int = 1, start: CoroutineStart = CoroutineStart.LAZY): BroadcastChannel<E> {
   return broadcast$default(
      CoroutineScopeKt.plus(CoroutineScopeKt.plus(GlobalScope.INSTANCE, Dispatchers.getUnconfined()), new 1(CoroutineExceptionHandler.Key)),
      null,
      capacity,
      start,
      BroadcastKt::broadcast$lambda$1,
      new 2(`$this$broadcast`, null),
      1,
      null
   );
}

/** @deprecated */
@JvmSynthetic
fun `broadcast$default`(var0: ReceiveChannel, var1: Int, var2: CoroutineStart, var3: Int, var4: Any): BroadcastChannel {
   if ((var3 and 1) != 0) {
      var1 = 1;
   }

   if ((var3 and 2) != 0) {
      var2 = CoroutineStart.LAZY;
   }

   return broadcast(var0, var1, var2);
}

@Deprecated(message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported", level = DeprecationLevel.ERROR)
@ObsoleteCoroutinesApi
public fun <E> CoroutineScope.broadcast(
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   capacity: Int = 1,
   start: CoroutineStart = CoroutineStart.LAZY,
   onCompletion: ((Throwable?) -> Unit)? = null,
   block: (ProducerScope<E>, Continuation<Unit>) -> Any?
): BroadcastChannel<E> {
   val newContext: CoroutineContext = CoroutineContextKt.newCoroutineContext(`$this$broadcast`, context);
   val channel: BroadcastChannel = BroadcastChannelKt.BroadcastChannel(capacity);
   val coroutine: BroadcastCoroutine = if (start.isLazy())
      new LazyBroadcastCoroutine(newContext, channel, block)
      else
      new BroadcastCoroutine(newContext, channel, true);
   if (onCompletion != null) {
      coroutine.invokeOnCompletion(onCompletion);
   }

   coroutine.start(start, coroutine, block);
   return coroutine;
}

/** @deprecated */
@JvmSynthetic
fun `broadcast$default`(var0: CoroutineScope, var1: CoroutineContext, var2: Int, var3: CoroutineStart, var4: Function1, var5: Function2, var6: Int, var7: Any): BroadcastChannel {
   if ((var6 and 1) != 0) {
      var1 = EmptyCoroutineContext.INSTANCE;
   }

   if ((var6 and 2) != 0) {
      var2 = 1;
   }

   if ((var6 and 4) != 0) {
      var3 = CoroutineStart.LAZY;
   }

   if ((var6 and 8) != 0) {
      var4 = null;
   }

   return broadcast(var0, var1, var2, var3, var4, var5);
}

fun `broadcast$lambda$1`(`$this_broadcast`: ReceiveChannel, it: java.lang.Throwable): Unit {
   ChannelsKt.cancelConsumed(`$this_broadcast`, it);
   return Unit.INSTANCE;
}
