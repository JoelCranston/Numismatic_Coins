package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlinx.coroutines.internal.ScopeCoroutine

private class TimeoutCoroutine<U, T extends U>(time: Long, uCont: Continuation<Any>) : ScopeCoroutine(uCont.getContext(), uCont), Runnable {
   public final val time: Long

   init {
      this.time = time;
   }

   public override fun run() {
      this.cancelCoroutine(TimeoutKt.TimeoutCancellationException(this.time, DelayKt.getDelay(this.getContext()), this));
   }

   internal override fun nameString(): String {
      return "${super.nameString$kotlinx_coroutines_core()}(timeMillis=${this.time})";
   }
}
