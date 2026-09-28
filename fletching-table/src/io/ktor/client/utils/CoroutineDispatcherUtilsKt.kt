package io.ktor.client.utils

import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InternalAPI
public fun Dispatchers.clientDispatcher(threadCount: Int, dispatcherName: String = "ktor-client-dispatcher"): CoroutineDispatcher {
   return CoroutineDispatcher.limitedParallelism$default(Dispatchers.getIO(), threadCount, null, 2, null);
}

@JvmSynthetic
fun `clientDispatcher$default`(var0: Dispatchers, var1: Int, var2: java.lang.String, var3: Int, var4: Any): CoroutineDispatcher {
   if ((var3 and 2) != 0) {
      var2 = "ktor-client-dispatcher";
   }

   return clientDispatcher(var0, var1, var2);
}
