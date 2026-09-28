package kotlinx.coroutines.flow.internal

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.CoroutineContext.Element
import kotlin.coroutines.CoroutineContext.Key

internal class DownstreamExceptionContext(e: Throwable, originalContext: CoroutineContext) : CoroutineContext {
   public final val e: Throwable

   init {
      this.$$delegate_0 = originalContext;
      this.e = e;
   }

   public override operator fun <E : Element> get(key: Key<E>): E? {
      return (E)this.$$delegate_0.get(key);
   }

   public override fun <R : Any?> fold(initial: R, operation: (R, Element) -> R): R {
      return (R)this.$$delegate_0.fold(initial, operation);
   }

   public override operator fun plus(context: CoroutineContext): CoroutineContext {
      return this.$$delegate_0.plus(context);
   }

   public override fun minusKey(key: Key<*>): CoroutineContext {
      return this.$$delegate_0.minusKey(key);
   }
}
