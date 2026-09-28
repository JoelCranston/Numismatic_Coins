package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext

/** @deprecated */
@Deprecated(message = "This is internal API and may be removed in the future releases", level = DeprecationLevel.ERROR)
@InternalCoroutinesApi
public interface ChildJob : Job {
   @InternalCoroutinesApi
   public abstract fun parentCancelled(parentJob: ParentJob) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      /** @deprecated */
      @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
      @JvmStatic
      fun plus(`$this`: ChildJob, other: Job): Job {
         return Job.DefaultImpls.plus(`$this`, other);
      }

      @JvmStatic
      fun plus(`$this`: ChildJob, context: CoroutineContext): CoroutineContext {
         return Job.DefaultImpls.plus(`$this`, context);
      }

      @JvmStatic
      fun <E extends CoroutineContext.Element> get(`$this`: ChildJob, key: CoroutineContextKey<E>): E? {
         return Job.DefaultImpls.get(`$this`, key);
      }

      @JvmStatic
      fun <R> fold(`$this`: ChildJob, initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
         return Job.DefaultImpls.fold(`$this`, (R)initial, operation);
      }

      @JvmStatic
      fun minusKey(`$this`: ChildJob, key: CoroutineContextKey<?>): CoroutineContext {
         return Job.DefaultImpls.minusKey(`$this`, key);
      }
   }
}
