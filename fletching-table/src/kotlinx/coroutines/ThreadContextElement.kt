package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext

public interface ThreadContextElement<S> : CoroutineContext.Element {
   public abstract fun updateThreadContext(context: CoroutineContext): Any {
   }

   public abstract fun restoreThreadContext(context: CoroutineContext, oldState: Any) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <S, E extends CoroutineContext.Element> get(`$this`: ThreadContextElement<S>, key: CoroutineContextKey<E>): E? {
         return CoroutineContext.Element.DefaultImpls.get(`$this`, key);
      }

      @JvmStatic
      fun <S, R> fold(`$this`: ThreadContextElement<S>, initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
         return CoroutineContext.Element.DefaultImpls.fold(`$this`, (R)initial, operation);
      }

      @JvmStatic
      fun <S> minusKey(`$this`: ThreadContextElement<S>, key: CoroutineContextKey<?>): CoroutineContext {
         return CoroutineContext.Element.DefaultImpls.minusKey(`$this`, key);
      }

      @JvmStatic
      fun <S> plus(`$this`: ThreadContextElement<S>, context: CoroutineContext): CoroutineContext {
         return CoroutineContext.Element.DefaultImpls.plus(`$this`, context);
      }
   }
}
