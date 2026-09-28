package kotlin

import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.InlineMarker

internal class StandardKt__SynchronizedKt : StandardKt__StandardKt {
   @InlineOnly
   @JvmStatic
   public inline fun <R> synchronized(lock: Any, block: () -> R): R {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      synchronized (lock) {
         val var3: Any = block.invoke();
         InlineMarker.finallyStart(1);
         InlineMarker.finallyEnd(1);
         return (R)var3;
      }
   }

   open fun StandardKt__SynchronizedKt() {
   }
}
