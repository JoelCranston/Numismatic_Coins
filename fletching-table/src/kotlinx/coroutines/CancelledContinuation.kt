package kotlinx.coroutines

import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlinx.atomicfu.AtomicBoolean

internal class CancelledContinuation(continuation: Continuation<*>, cause: Throwable?, handled: Boolean) : CompletedExceptionally {
   private final val _resumed: AtomicBoolean

   init {
      var var10001: java.lang.Throwable = cause;
      if (cause == null) {
         var10001 = new CancellationException("Continuation $continuation was cancelled normally");
      }

      super(var10001, handled);
   }

   public fun makeResumed(): Boolean {
      return get_resumed$volatile$FU().compareAndSet(this, 0, 1);
   }
}
