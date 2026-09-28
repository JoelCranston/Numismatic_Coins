package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

@InternalCoroutinesApi
public abstract class AbstractCoroutine<T> : JobSupport, Job, Continuation<T>, CoroutineScope {
   public final val context: CoroutineContext

   public open val coroutineContext: CoroutineContext
      public open get() {
         return this.context;
      }


   public open val isActive: Boolean
      public open get() {
         return super.isActive();
      }


   open fun AbstractCoroutine(parentContext: CoroutineContext, initParentJob: Boolean, active: Boolean) {
      super(active);
      if (initParentJob) {
         this.initParentJob(parentContext.get(Job.Key));
      }

      this.context = parentContext.plus(this);
   }

   protected open fun onCompleted(value: Any) {
   }

   protected open fun onCancelled(cause: Throwable, handled: Boolean) {
   }

   protected override fun cancellationExceptionMessage(): String {
      return "${DebugStringsKt.getClassSimpleName(this)} was cancelled";
   }

   protected override fun onCompletionInternal(state: Any?) {
      if (state is CompletedExceptionally) {
         this.onCancelled((state as CompletedExceptionally).cause, (state as CompletedExceptionally).getHandled());
      } else {
         this.onCompleted((T)state);
      }
   }

   public override fun resumeWith(result: Result<Any>) {
      val state: Any = this.makeCompletingOnce$kotlinx_coroutines_core(CompletionStateKt.toState(result));
      if (state != JobSupportKt.COMPLETING_WAITING_CHILDREN) {
         this.afterResume(state);
      }
   }

   protected open fun afterResume(state: Any?) {
      this.afterCompletion(state);
   }

   internal override fun handleOnCompletionException(exception: Throwable) {
      CoroutineExceptionHandlerKt.handleCoroutineException(this.context, exception);
   }

   internal override fun nameString(): String {
      val var10000: java.lang.String = CoroutineContextKt.getCoroutineName(this.context);
      return if (var10000 == null) super.nameString$kotlinx_coroutines_core() else ""$var10000\":${super.nameString$kotlinx_coroutines_core()}";
   }

   public fun <R> start(start: CoroutineStart, receiver: R, block: (R, Continuation<Any>) -> Any?) {
      start.invoke(block, receiver, this);
   }
}
