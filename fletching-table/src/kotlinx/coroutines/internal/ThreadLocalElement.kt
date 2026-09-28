package kotlinx.coroutines.internal

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.CoroutineContext.Element
import kotlin.coroutines.CoroutineContext.Key
import kotlinx.coroutines.ThreadContextElement

internal class ThreadLocalElement<T>(value: Any, threadLocal: ThreadLocal<Any>) : ThreadContextElement<T> {
   private final val value: Any
   private final val threadLocal: ThreadLocal<Any>
   public open val key: Key<*>

   init {
      this.value = (T)value;
      this.threadLocal = threadLocal;
      this.key = new ThreadLocalKey(this.threadLocal);
   }

   public override fun updateThreadContext(context: CoroutineContext): Any {
      val oldState: Any = this.threadLocal.get();
      this.threadLocal.set(this.value);
      return (T)oldState;
   }

   public override fun restoreThreadContext(context: CoroutineContext, oldState: Any) {
      this.threadLocal.set((T)oldState);
   }

   public override fun minusKey(key: Key<*>): CoroutineContext {
      return (CoroutineContext)(if (this.getKey() == key) EmptyCoroutineContext.INSTANCE else this);
   }

   public override operator fun <E : Element> get(key: Key<E>): E? {
      val var10000: CoroutineContext.Element;
      if (this.getKey() == key) {
         var10000 = this;
      } else {
         var10000 = null;
      }

      return (E)var10000;
   }

   public override fun toString(): String {
      return "ThreadLocal(value=${this.value}, threadLocal = ${this.threadLocal})";
   }

   override fun <R> fold(initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
      return ThreadContextElement.DefaultImpls.fold(this, (R)initial, operation);
   }

   override fun plus(context: CoroutineContext): CoroutineContext {
      return ThreadContextElement.DefaultImpls.plus(this, context);
   }
}
