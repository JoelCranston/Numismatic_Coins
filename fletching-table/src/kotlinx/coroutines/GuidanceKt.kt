package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.internal.LowPriorityInOverloadResolution
import kotlin.jvm.functions.Function2

@Deprecated(message = "'launch' can not be called without the corresponding coroutine scope. Consider wrapping 'launch' in 'coroutineScope { }', using 'runBlocking { }', or using some other 'CoroutineScope'", level = DeprecationLevel.ERROR)
@LowPriorityInOverloadResolution
public fun launch(
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   start: CoroutineStart = CoroutineStart.DEFAULT,
   block: (CoroutineScope, Continuation<Unit>) -> Any?
): Job {
   throw new UnsupportedOperationException("Should never be called, was introduced to help with incomplete code");
}

/** @deprecated */
@JvmSynthetic
fun `launch$default`(var0: CoroutineContext, var1: CoroutineStart, var2: Function2, var3: Int, var4: Any): Job {
   if ((var3 and 1) != 0) {
      var0 = EmptyCoroutineContext.INSTANCE;
   }

   if ((var3 and 2) != 0) {
      var1 = CoroutineStart.DEFAULT;
   }

   return launch(var0, var1, var2);
}

@Deprecated(message = "'async' can not be called without the corresponding coroutine scope. Consider wrapping 'async' in 'coroutineScope { }', using 'runBlocking { }', or using some other 'CoroutineScope'", level = DeprecationLevel.ERROR)
@LowPriorityInOverloadResolution
public fun <T> async(
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   start: CoroutineStart = CoroutineStart.DEFAULT,
   block: (CoroutineScope, Continuation<T>) -> Any?
): Deferred<T> {
   throw new UnsupportedOperationException("Should never be called, was introduced to help with incomplete code");
}

/** @deprecated */
@JvmSynthetic
fun `async$default`(var0: CoroutineContext, var1: CoroutineStart, var2: Function2, var3: Int, var4: Any): Deferred {
   if ((var3 and 1) != 0) {
      var0 = EmptyCoroutineContext.INSTANCE;
   }

   if ((var3 and 2) != 0) {
      var1 = CoroutineStart.DEFAULT;
   }

   return async(var0, var1, var2);
}
