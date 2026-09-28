package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

@SubclassOptInRequired(markerClass = [InternalForInheritanceCoroutinesApi::class])
public interface CancellableContinuation<T> : Continuation<T> {
   public val isActive: Boolean
   public val isCompleted: Boolean
   public val isCancelled: Boolean

   @InternalCoroutinesApi
   public abstract fun tryResume(value: Any, idempotent: Any? = ...): Any? {
   }

   @InternalCoroutinesApi
   public abstract fun <R : Any> tryResume(value: R, idempotent: Any?, onCancellation: ((Throwable, R, CoroutineContext) -> Unit)?): Any? {
   }

   @InternalCoroutinesApi
   public abstract fun tryResumeWithException(exception: Throwable): Any? {
   }

   @InternalCoroutinesApi
   public abstract fun completeResume(token: Any) {
   }

   @InternalCoroutinesApi
   public abstract fun initCancellability() {
   }

   public abstract fun cancel(cause: Throwable? = ...): Boolean {
   }

   public abstract fun invokeOnCancellation(handler: (Throwable?) -> Unit) {
   }

   @ExperimentalCoroutinesApi
   public abstract fun CoroutineDispatcher.resumeUndispatched(value: Any) {
   }

   @ExperimentalCoroutinesApi
   public abstract fun CoroutineDispatcher.resumeUndispatchedWithException(exception: Throwable) {
   }

   @Deprecated(message = "Use the overload that also accepts the `value` and the coroutine context in lambda", replaceWith = @ReplaceWith(expression = "resume(value) { cause, _, _ -> onCancellation(cause) }", imports = []), level = DeprecationLevel.WARNING)
   public abstract fun resume(value: Any, onCancellation: ((Throwable) -> Unit)?) {
   }

   public abstract fun <R : Any> resume(value: R, onCancellation: ((Throwable, R, CoroutineContext) -> Unit)?) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
