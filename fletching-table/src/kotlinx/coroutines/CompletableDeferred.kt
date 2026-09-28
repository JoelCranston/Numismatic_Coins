package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext

@SubclassOptInRequired(markerClass = [InternalForInheritanceCoroutinesApi::class])
public interface CompletableDeferred<T> : Deferred<T> {
   public abstract fun complete(value: Any): Boolean {
   }

   public abstract fun completeExceptionally(exception: Throwable): Boolean {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      /** @deprecated */
      @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
      @JvmStatic
      fun <T> plus(`$this`: CompletableDeferred<T>, other: Job): Job {
         return Deferred.DefaultImpls.plus(`$this`, other);
      }

      @JvmStatic
      fun <T> plus(`$this`: CompletableDeferred<T>, context: CoroutineContext): CoroutineContext {
         return Deferred.DefaultImpls.plus(`$this`, context);
      }

      @JvmStatic
      fun <T, E extends CoroutineContext.Element> get(`$this`: CompletableDeferred<T>, key: CoroutineContextKey<E>): E? {
         return Deferred.DefaultImpls.get(`$this`, key);
      }

      @JvmStatic
      fun <T, R> fold(`$this`: CompletableDeferred<T>, initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
         return Deferred.DefaultImpls.fold(`$this`, (R)initial, operation);
      }

      @JvmStatic
      fun <T> minusKey(`$this`: CompletableDeferred<T>, key: CoroutineContextKey<?>): CoroutineContext {
         return Deferred.DefaultImpls.minusKey(`$this`, key);
      }
   }
}
