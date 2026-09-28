package kotlinx.coroutines

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.AbstractCoroutineContextKey
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.internal.DispatchedContinuation
import kotlinx.coroutines.internal.DispatchedContinuationKt
import kotlinx.coroutines.internal.LimitedDispatcher
import kotlinx.coroutines.internal.LimitedDispatcherKt

public abstract class CoroutineDispatcher : AbstractCoroutineContextElement, ContinuationInterceptor {
   open fun CoroutineDispatcher() {
      super(ContinuationInterceptor.Key);
   }

   public open fun isDispatchNeeded(context: CoroutineContext): Boolean {
      return true;
   }

   public open fun limitedParallelism(parallelism: Int, name: String? = null): CoroutineDispatcher {
      LimitedDispatcherKt.checkParallelism(parallelism);
      return new LimitedDispatcher(this, parallelism, name);
   }

   public abstract fun dispatch(context: CoroutineContext, block: Runnable) {
   }

   @InternalCoroutinesApi
   public open fun dispatchYield(context: CoroutineContext, block: Runnable) {
      DispatchedContinuationKt.safeDispatch(this, context, block);
   }

   public override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
      return new DispatchedContinuation(this, continuation);
   }

   public override fun releaseInterceptedContinuation(continuation: Continuation<*>) {
      (continuation as DispatchedContinuation).release$kotlinx_coroutines_core();
   }

   @Deprecated(message = "Operator '+' on two CoroutineDispatcher objects is meaningless. CoroutineDispatcher is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The dispatcher to the right of `+` just replaces the dispatcher to the left.", level = DeprecationLevel.ERROR)
   public operator fun plus(other: CoroutineDispatcher): CoroutineDispatcher {
      return other;
   }

   public override fun toString(): String {
      return "${DebugStringsKt.getClassSimpleName(this)}@${DebugStringsKt.getHexAddress(this)}";
   }

   override fun <E extends CoroutineContext.Element> get(key: CoroutineContextKey<E>): E? {
      return ContinuationInterceptor.DefaultImpls.get(this, key);
   }

   override fun minusKey(key: CoroutineContextKey<?>): CoroutineContext {
      return ContinuationInterceptor.DefaultImpls.minusKey(this, key);
   }

   @ExperimentalStdlibApi
   public companion object Key : AbstractCoroutineContextKey(ContinuationInterceptor.Key, CoroutineDispatcher.Key::_init_$lambda$0) {
      @JvmStatic
      fun `_init_$lambda$0`(it: CoroutineContext.Element): CoroutineDispatcher {
         return it as? CoroutineDispatcher;
      }
   }
}
