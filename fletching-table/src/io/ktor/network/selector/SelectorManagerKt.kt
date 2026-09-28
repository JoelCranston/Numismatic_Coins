package io.ktor.network.selector

import java.io.Closeable
import java.nio.channels.spi.SelectorProvider
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public fun SelectorManager(dispatcher: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext): SelectorManager {
   return new ActorSelectorManager(dispatcher);
}

@JvmSynthetic
fun `SelectorManager$default`(var0: CoroutineContext, var1: Int, var2: Any): SelectorManager {
   if ((var1 and 1) != 0) {
      var0 = EmptyCoroutineContext.INSTANCE;
   }

   return SelectorManager(var0);
}

public inline fun <C : Closeable, R> SelectorManager.buildOrClose(create: (SelectorProvider) -> Any, setup: (Any) -> Any): Any {
   val result: Closeable = create.invoke(`$this$buildOrClose`.getProvider()) as Closeable;

   try {
      return (R)setup.invoke(result);
   } catch (var6: java.lang.Throwable) {
      result.close();
      throw var6;
   }
}
