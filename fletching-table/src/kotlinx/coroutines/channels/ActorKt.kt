package kotlinx.coroutines.channels

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2
import kotlinx.coroutines.CoroutineContextKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
public fun <E> CoroutineScope.actor(
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   capacity: Int = 0,
   start: CoroutineStart = CoroutineStart.DEFAULT,
   onCompletion: ((Throwable?) -> Unit)? = null,
   block: (ActorScope<E>, Continuation<Unit>) -> Any?
): SendChannel<E> {
   val newContext: CoroutineContext = CoroutineContextKt.newCoroutineContext(`$this$actor`, context);
   val channel: Channel = ChannelKt.Channel$default(capacity, null, null, 6, null);
   val coroutine: ActorCoroutine = if (start.isLazy()) new LazyActorCoroutine(newContext, channel, block) else new ActorCoroutine(newContext, channel, true);
   if (onCompletion != null) {
      coroutine.invokeOnCompletion(onCompletion);
   }

   coroutine.start(start, coroutine, block);
   return coroutine;
}

@JvmSynthetic
fun `actor$default`(var0: CoroutineScope, var1: CoroutineContext, var2: Int, var3: CoroutineStart, var4: Function1, var5: Function2, var6: Int, var7: Any): SendChannel {
   if ((var6 and 1) != 0) {
      var1 = EmptyCoroutineContext.INSTANCE;
   }

   if ((var6 and 2) != 0) {
      var2 = 0;
   }

   if ((var6 and 4) != 0) {
      var3 = CoroutineStart.DEFAULT;
   }

   if ((var6 and 8) != 0) {
      var4 = null;
   }

   return actor(var0, var1, var2, var3, var4, var5);
}
