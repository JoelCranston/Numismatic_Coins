package io.ktor.network.util

import io.ktor.util.date.DateJvmKt
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function0
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.InlineMarker
import kotlinx.coroutines.CoroutineScope

internal const val INFINITE_TIMEOUT_MS: Long = java.lang.Long.MAX_VALUE

internal fun CoroutineScope.createTimeout(
   name: String = "",
   timeoutMs: Long,
   clock: () -> Long = UtilsKt::createTimeout$lambda$0,
   onTimeout: (Continuation<Unit>) -> Any?
): Timeout {
   return new Timeout(name, timeoutMs, clock, `$this$createTimeout`, onTimeout);
}

@JvmSynthetic
fun `createTimeout$default`(var0: CoroutineScope, var1: java.lang.String, var2: Long, var4: Function0, var5: Function1, var6: Int, var7: Any): Timeout {
   if ((var6 and 1) != 0) {
      var1 = "";
   }

   if ((var6 and 4) != 0) {
      var4 = UtilsKt::createTimeout$lambda$0;
   }

   return createTimeout(var0, var1, var2, var4, var5);
}

internal inline fun <T> Timeout?.withTimeout(block: () -> Any): Any {
   if (`$this$withTimeout` == null) {
      return (T)block.invoke();
   } else {
      label29: {
         `$this$withTimeout`.start();

         try {
            val var3: Any = block.invoke();
         } catch (var5: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            `$this$withTimeout`.stop();
            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         `$this$withTimeout`.stop();
         InlineMarker.finallyEnd(1);
      }
   }
}

fun `createTimeout$lambda$0`(): Long {
   return DateJvmKt.getTimeMillis();
}
