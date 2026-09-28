package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.selects.SelectClause1

private open class DeferredCoroutine<T>(parentContext: CoroutineContext, active: Boolean) : AbstractCoroutine(parentContext, true, active), Deferred<T> {
   public open val onAwait: SelectClause1<Any>
      public open get() {
         val var10000: SelectClause1 = this.getOnAwaitInternal();
         return var10000;
      }


   public override fun getCompleted(): Any {
      return (T)this.getCompletedInternal$kotlinx_coroutines_core();
   }

   public override suspend fun await(): Any {
      return await$suspendImpl(this, `$completion`);
   }
}
