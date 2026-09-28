package kotlinx.coroutines

import java.util.concurrent.CancellationException
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.selects.SelectClause0

@SubclassOptInRequired(markerClass = [InternalForInheritanceCoroutinesApi::class])
public interface Job : CoroutineContext.Element {
   public val parent: Job?
   public val isActive: Boolean
   public val isCompleted: Boolean
   public val isCancelled: Boolean
   public val children: Sequence<Job>
   public val onJoin: SelectClause0

   @InternalCoroutinesApi
   public abstract fun getCancellationException(): CancellationException {
   }

   public abstract fun start(): Boolean {
   }

   public abstract fun cancel(cause: CancellationException? = ...) {
   }

   @InternalCoroutinesApi
   public abstract fun attachChild(child: ChildJob): ChildHandle {
   }

   public abstract suspend fun join() {
   }

   public abstract fun invokeOnCompletion(handler: (Throwable?) -> Unit): DisposableHandle {
   }

   @InternalCoroutinesApi
   public abstract fun invokeOnCompletion(onCancelling: Boolean = ..., invokeImmediately: Boolean = ..., handler: (Throwable?) -> Unit): DisposableHandle {
   }

   @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
   public open operator fun plus(other: Job): Job {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      /** @deprecated */
      @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
      @JvmStatic
      fun plus(`$this`: Job, other: Job): Job {
         return other;
      }

      @JvmStatic
      fun <E extends CoroutineContext.Element> get(`$this`: Job, key: CoroutineContextKey<E>): E? {
         return CoroutineContext.Element.DefaultImpls.get(`$this`, key);
      }

      @JvmStatic
      fun <R> fold(`$this`: Job, initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
         return CoroutineContext.Element.DefaultImpls.fold(`$this`, (R)initial, operation);
      }

      @JvmStatic
      fun minusKey(`$this`: Job, key: CoroutineContextKey<?>): CoroutineContext {
         return CoroutineContext.Element.DefaultImpls.minusKey(`$this`, key);
      }

      @JvmStatic
      fun plus(`$this`: Job, context: CoroutineContext): CoroutineContext {
         return CoroutineContext.Element.DefaultImpls.plus(`$this`, context);
      }
   }

   public companion object Key : CoroutineContext.Key<Job>
}
