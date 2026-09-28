package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext

public interface CoroutineExceptionHandler : CoroutineContext.Element {
   public abstract fun handleException(context: CoroutineContext, exception: Throwable) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <E extends CoroutineContext.Element> get(`$this`: CoroutineExceptionHandler, key: CoroutineContextKey<E>): E? {
         return CoroutineContext.Element.DefaultImpls.get(`$this`, key);
      }

      @JvmStatic
      fun <R> fold(`$this`: CoroutineExceptionHandler, initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
         return CoroutineContext.Element.DefaultImpls.fold(`$this`, (R)initial, operation);
      }

      @JvmStatic
      fun minusKey(`$this`: CoroutineExceptionHandler, key: CoroutineContextKey<?>): CoroutineContext {
         return CoroutineContext.Element.DefaultImpls.minusKey(`$this`, key);
      }

      @JvmStatic
      fun plus(`$this`: CoroutineExceptionHandler, context: CoroutineContext): CoroutineContext {
         return CoroutineContext.Element.DefaultImpls.plus(`$this`, context);
      }
   }

   public companion object Key : CoroutineContext.Key<CoroutineExceptionHandler>
}
