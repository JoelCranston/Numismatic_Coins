package kotlinx.coroutines.selects

import kotlin.coroutines.CoroutineContext
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.TypeIntrinsics
import kotlinx.coroutines.DelayKt
import kotlinx.coroutines.selects.OnTimeout.selectClause.1

private class OnTimeout(timeMillis: Long) {
   private final val timeMillis: Long

   public final val selectClause: SelectClause0
      public final get() {
         val var10003: 1 = 1.INSTANCE;
         return new SelectClause0Impl(this, TypeIntrinsics.beforeCheckcastToFunctionOfArity(var10003, 3) as Function3, null, 4, null);
      }


   init {
      this.timeMillis = timeMillis;
   }

   private fun register(select: SelectInstance<*>, ignoredParam: Any?) {
      if (this.timeMillis <= 0L) {
         select.selectInRegistrationPhase(Unit.INSTANCE);
      } else {
         val action: Runnable = OnTimeout::register$lambda$0;
         val context: CoroutineContext = (select as SelectImplementation).getContext();
         (select as SelectImplementation).disposeOnCompletion(DelayKt.getDelay(context).invokeOnTimeout(this.timeMillis, action, context));
      }
   }

   @JvmStatic
   fun `register$lambda$0`(`$select`: SelectInstance, `this$0`: OnTimeout) {
      `$select`.trySelect(`this$0`, Unit.INSTANCE);
   }
}
