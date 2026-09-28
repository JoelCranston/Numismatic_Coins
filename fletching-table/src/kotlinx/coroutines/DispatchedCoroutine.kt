package kotlinx.coroutines

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.atomicfu.AtomicInt
import kotlinx.coroutines.internal.DispatchedContinuationKt
import kotlinx.coroutines.internal.ScopeCoroutine

internal class DispatchedCoroutine<T>(context: CoroutineContext, uCont: Continuation<Any>) : ScopeCoroutine(context, uCont) {
   private final val _decision: AtomicInt

   private fun trySuspend(): Boolean {
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = get_decision$volatile$FU();

      label16:
      while (true) {
         switch (handler$atomicfu$iv.get(this)) {
            case 0:
               if (get_decision$volatile$FU().compareAndSet(this, 0, 1)) {
                  break label16;
               }
               break;
            case 1:
            default:
               throw new IllegalStateException("Already suspended".toString());
            case 2:
               return false;
         }
      }

      return true;
   }

   private fun tryResume(): Boolean {
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = get_decision$volatile$FU();

      label16:
      while (true) {
         switch (handler$atomicfu$iv.get(this)) {
            case 0:
               if (get_decision$volatile$FU().compareAndSet(this, 0, 2)) {
                  break label16;
               }
               break;
            case 1:
               return false;
            default:
               throw new IllegalStateException("Already resumed".toString());
         }
      }

      return true;
   }

   protected override fun afterCompletion(state: Any?) {
      this.afterResume(state);
   }

   protected override fun afterResume(state: Any?) {
      if (!this.tryResume()) {
         DispatchedContinuationKt.resumeCancellableWith(IntrinsicsKt.intercepted(this.uCont), CompletionStateKt.recoverResult(state, this.uCont));
      }
   }

   internal fun getResult(): Any? {
      if (this.trySuspend()) {
         return IntrinsicsKt.getCOROUTINE_SUSPENDED();
      } else {
         val state: Any = JobSupportKt.unboxState(this.getState$kotlinx_coroutines_core());
         if (state is CompletedExceptionally) {
            throw (state as CompletedExceptionally).cause;
         } else {
            return state;
         }
      }
   }
}
