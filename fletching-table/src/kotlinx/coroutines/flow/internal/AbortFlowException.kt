package kotlinx.coroutines.flow.internal

import java.util.concurrent.CancellationException
import kotlinx.coroutines.DebugKt

internal class AbortFlowException(owner: Any) : CancellationException("Flow was aborted, no more elements needed") {
   public final val owner: Any

   init {
      this.owner = owner;
   }

   public override fun fillInStackTrace(): Throwable {
      if (DebugKt.getDEBUG()) {
         return super.fillInStackTrace();
      } else {
         this.setStackTrace(new StackTraceElement[0]);
         return this;
      }
   }
}
