package kotlinx.coroutines

import java.util.concurrent.CancellationException
import java.util.concurrent.Future
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

// $VF: Class flags could not be determined
internal class JobKt {
   /** @deprecated */
   @Deprecated(message = "This function does not do what its name implies: it will not cancel the future if just cancel() was called.", replaceWith = @ReplaceWith(expression = "this.invokeOnCancellation { future.cancel(false) }", imports = []), level = DeprecationLevel.WARNING)
   @JvmStatic
   fun CancellableContinuation<?>.cancelFutureOnCancellation(future: Future<?>) {
      JobKt__FutureKt.cancelFutureOnCancellation(`$this$cancelFutureOnCancellation`, future);
   }

   @JvmStatic
   fun Job.invokeOnCompletion(invokeImmediately: Boolean, handler: JobNode): DisposableHandle {
      return JobKt__JobKt.invokeOnCompletion(`$this$invokeOnCompletion`, invokeImmediately, handler);
   }

   @JvmStatic
   fun Job(parent: Job?): CompletableJob {
      return JobKt__JobKt.Job(parent);
   }

   @JvmStatic
   fun Job.disposeOnCompletion(handle: DisposableHandle): DisposableHandle {
      return JobKt__JobKt.disposeOnCompletion(`$this$disposeOnCompletion`, handle);
   }

   @JvmStatic
   fun Job.cancelAndJoin(`$completion`: Continuation<? super Unit>): Any? {
      return JobKt__JobKt.cancelAndJoin(`$this$cancelAndJoin`, `$completion`);
   }

   @JvmStatic
   fun Job.cancelChildren(cause: CancellationException?) {
      JobKt__JobKt.cancelChildren(`$this$cancelChildren`, cause);
   }

   @JvmStatic
   fun CoroutineContext.isActive(): Boolean {
      return JobKt__JobKt.isActive(`$this$isActive`);
   }

   @JvmStatic
   fun CoroutineContext.cancel(cause: CancellationException?) {
      JobKt__JobKt.cancel(`$this$cancel`, cause);
   }

   @JvmStatic
   fun Job.ensureActive() {
      JobKt__JobKt.ensureActive(`$this$ensureActive`);
   }

   @JvmStatic
   fun CoroutineContext.ensureActive() {
      JobKt__JobKt.ensureActive(`$this$ensureActive`);
   }

   @JvmStatic
   fun Job.cancel(message: java.lang.String, cause: java.lang.Throwable?) {
      JobKt__JobKt.cancel(`$this$cancel`, message, cause);
   }

   @JvmStatic
   fun CoroutineContext.cancelChildren(cause: CancellationException?) {
      JobKt__JobKt.cancelChildren(`$this$cancelChildren`, cause);
   }

   @JvmStatic
   fun CoroutineContext.getJob(): Job {
      return JobKt__JobKt.getJob(`$this$job`);
   }
}
