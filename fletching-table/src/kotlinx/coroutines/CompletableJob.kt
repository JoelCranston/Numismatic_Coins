package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext

@SubclassOptInRequired(markerClass = [InternalForInheritanceCoroutinesApi::class])
public interface CompletableJob : Job {
   public abstract fun complete(): Boolean {
   }

   public abstract fun completeExceptionally(exception: Throwable): Boolean {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      /** @deprecated */
      @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
      @JvmStatic
      fun plus(`$this`: CompletableJob, other: Job): Job {
         return Job.DefaultImpls.plus(`$this`, other);
      }

      @JvmStatic
      fun plus(`$this`: CompletableJob, context: CoroutineContext): CoroutineContext {
         return Job.DefaultImpls.plus(`$this`, context);
      }

      @JvmStatic
      fun <E extends CoroutineContext.Element> get(`$this`: CompletableJob, key: CoroutineContextKey<E>): E? {
         return Job.DefaultImpls.get(`$this`, key);
      }

      @JvmStatic
      fun <R> fold(`$this`: CompletableJob, initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
         return Job.DefaultImpls.fold(`$this`, (R)initial, operation);
      }

      @JvmStatic
      fun minusKey(`$this`: CompletableJob, key: CoroutineContextKey<?>): CoroutineContext {
         return Job.DefaultImpls.minusKey(`$this`, key);
      }
   }
}
