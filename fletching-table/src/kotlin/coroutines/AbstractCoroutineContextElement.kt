package kotlin.coroutines

import kotlin.coroutines.CoroutineContext.Key

@SinceKotlin(version = "1.3")
public abstract class AbstractCoroutineContextElement : CoroutineContext.Element {
   public open val key: Key<*>

   open fun AbstractCoroutineContextElement(key: CoroutineContextKey<?>) {
      this.key = key;
   }
}
