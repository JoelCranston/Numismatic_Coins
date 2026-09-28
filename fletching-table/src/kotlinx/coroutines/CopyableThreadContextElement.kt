package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.CoroutineContext.Element

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
public interface CopyableThreadContextElement<S> : ThreadContextElement<S> {
   public abstract fun copyForChild(): CopyableThreadContextElement<Any> {
   }

   public abstract fun mergeForChild(overwritingElement: Element): CoroutineContext {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <S, E extends CoroutineContext.Element> get(`$this`: CopyableThreadContextElement<S>, key: CoroutineContextKey<E>): E? {
         return ThreadContextElement.DefaultImpls.get(`$this`, key);
      }

      @JvmStatic
      fun <S, R> fold(`$this`: CopyableThreadContextElement<S>, initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
         return ThreadContextElement.DefaultImpls.fold(`$this`, (R)initial, operation);
      }

      @JvmStatic
      fun <S> minusKey(`$this`: CopyableThreadContextElement<S>, key: CoroutineContextKey<?>): CoroutineContext {
         return ThreadContextElement.DefaultImpls.minusKey(`$this`, key);
      }

      @JvmStatic
      fun <S> plus(`$this`: CopyableThreadContextElement<S>, context: CoroutineContext): CoroutineContext {
         return ThreadContextElement.DefaultImpls.plus(`$this`, context);
      }
   }
}
