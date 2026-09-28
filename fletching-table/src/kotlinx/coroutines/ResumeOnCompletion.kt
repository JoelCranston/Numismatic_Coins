package kotlinx.coroutines

import kotlin.coroutines.Continuation

private class ResumeOnCompletion(continuation: Continuation<Unit>) : JobNode {
   private final val continuation: Continuation<Unit>

   public open val onCancelling: Boolean
      public open get() {
         return false;
      }


   init {
      this.continuation = continuation;
   }

   public override fun invoke(cause: Throwable?) {
      this.continuation.resumeWith(Result.constructor-impl(Unit.INSTANCE));
   }
}
