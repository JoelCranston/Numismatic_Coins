package kotlinx.coroutines.selects

import kotlin.coroutines.Continuation
import kotlin.internal.LowPriorityInOverloadResolution
import kotlinx.coroutines.ExperimentalCoroutinesApi

public sealed interface SelectBuilder<R> {
   public abstract operator fun SelectClause0.invoke(block: (Continuation<Any>) -> Any?) {
   }

   public abstract operator fun <Q> SelectClause1<Q>.invoke(block: (Q, Continuation<Any>) -> Any?) {
   }

   public abstract operator fun <P, Q> SelectClause2<P, Q>.invoke(param: P, block: (Q, Continuation<Any>) -> Any?) {
   }

   public open operator fun <P, Q> SelectClause2<P?, Q>.invoke(block: (Q, Continuation<Any>) -> Any?) {
   }

   @Deprecated(message = "Replaced with the same extension function", replaceWith = @ReplaceWith(expression = "onTimeout", imports = ["kotlinx.coroutines.selects.onTimeout"]), level = DeprecationLevel.ERROR)
   @ExperimentalCoroutinesApi
   @LowPriorityInOverloadResolution
   public open fun onTimeout(timeMillis: Long, block: (Continuation<Any>) -> Any?) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <R, P, Q> invoke(`$this`: SelectBuilder<? super R>, `$receiver`: SelectClause2<? super P, ? extends Q>, block: (Q?, Continuation<? super R>?) -> Any) {
         `$this`.invoke(`$receiver`, null, block);
      }

      /** @deprecated */
      @Deprecated(message = "Replaced with the same extension function", replaceWith = @ReplaceWith(expression = "onTimeout", imports = ["kotlinx.coroutines.selects.onTimeout"]), level = DeprecationLevel.ERROR)
      @ExperimentalCoroutinesApi
      @LowPriorityInOverloadResolution
      @JvmStatic
      fun <R> onTimeout(`$this`: SelectBuilder<? super R>, timeMillis: Long, block: (Continuation<? super R>?) -> Any) {
         OnTimeoutKt.onTimeout(`$this`, timeMillis, block);
      }
   }
}
