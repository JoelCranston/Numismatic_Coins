package kotlinx.coroutines.internal

import kotlin.coroutines.CoroutineContext

@PublishedApi
internal data class ThreadLocalKey(threadLocal: ThreadLocal<*>) : CoroutineContext.Key<ThreadLocalElement<?>> {
   private final val threadLocal: ThreadLocal<*>

   init {
      this.threadLocal = threadLocal;
   }

   private operator fun component1(): ThreadLocal<*> {
      return this.threadLocal;
   }

   public fun copy(threadLocal: ThreadLocal<*> = this.threadLocal): ThreadLocalKey {
      return new ThreadLocalKey(threadLocal);
   }

   public override fun toString(): String {
      return "ThreadLocalKey(threadLocal=${this.threadLocal})";
   }

   public override fun hashCode(): Int {
      return this.threadLocal.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ThreadLocalKey) {
         return false;
      } else {
         return this.threadLocal == (other as ThreadLocalKey).threadLocal;
      }
   }
}
