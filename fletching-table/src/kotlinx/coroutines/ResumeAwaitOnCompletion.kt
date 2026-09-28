package kotlinx.coroutines

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nJobSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/ResumeAwaitOnCompletion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1583:1\n1#2:1584\n*E\n"])
private class ResumeAwaitOnCompletion<T>(continuation: CancellableContinuationImpl<Any>) : JobNode {
   private final val continuation: CancellableContinuationImpl<Any>

   public open val onCancelling: Boolean
      public open get() {
         return false;
      }


   init {
      this.continuation = continuation;
   }

   public override fun invoke(cause: Throwable?) {
      val state: Any = this.getJob().getState$kotlinx_coroutines_core();
      if (DebugKt.getASSERTIONS_ENABLED() && state is Incomplete) {
         throw new AssertionError();
      } else {
         if (state is CompletedExceptionally) {
            this.continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure((state as CompletedExceptionally).cause)));
         } else {
            this.continuation.resumeWith(Result.constructor-impl(JobSupportKt.unboxState(state)));
         }
      }
   }
}
