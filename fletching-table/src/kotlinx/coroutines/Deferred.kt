package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.selects.SelectClause1

@SubclassOptInRequired(markerClass = [InternalForInheritanceCoroutinesApi::class])
public interface Deferred<T> : Job {
   public val onAwait: SelectClause1<Any>

   public abstract suspend fun await(): Any {
   }

   @ExperimentalCoroutinesApi
   public abstract fun getCompleted(): Any {
   }

   @ExperimentalCoroutinesApi
   public abstract fun getCompletionExceptionOrNull(): Throwable? {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      /** @deprecated */
      @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
      @JvmStatic
      fun <T> plus(`$this`: Deferred<? extends T>, other: Job): Job {
         return Job.DefaultImpls.plus(`$this`, other);
      }

      @JvmStatic
      fun <T> plus(`$this`: Deferred<? extends T>, context: CoroutineContext): CoroutineContext {
         return Job.DefaultImpls.plus(`$this`, context);
      }

      @JvmStatic
      fun <T, E extends CoroutineContext.Element> get(`$this`: Deferred<? extends T>, key: CoroutineContextKey<E>): E? {
         return Job.DefaultImpls.get(`$this`, key);
      }

      @JvmStatic
      fun <T, R> fold(`$this`: Deferred<? extends T>, initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
         return Job.DefaultImpls.fold(`$this`, (R)initial, operation);
      }

      @JvmStatic
      fun <T> minusKey(`$this`: Deferred<? extends T>, key: CoroutineContextKey<?>): CoroutineContext {
         return Job.DefaultImpls.minusKey(`$this`, key);
      }
   }
}
