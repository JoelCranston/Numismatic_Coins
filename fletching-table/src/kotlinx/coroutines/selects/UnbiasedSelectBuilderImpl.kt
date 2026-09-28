package kotlinx.coroutines.selects

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CancellableContinuationImpl
import kotlinx.coroutines.CoroutineScopeKt
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.initSelectResult.1

@PublishedApi
internal class UnbiasedSelectBuilderImpl<R>(uCont: Continuation<Any>) : UnbiasedSelectImplementation(uCont.getContext()) {
   private final val cont: CancellableContinuationImpl<Any>

   init {
      this.cont = new CancellableContinuationImpl<>(IntrinsicsKt.intercepted(uCont), 1);
   }

   @PublishedApi
   internal fun initSelectResult(): Any? {
      if (this.cont.isCompleted()) {
         return this.cont.getResult();
      } else {
         BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(this.getContext()), null, CoroutineStart.UNDISPATCHED, new 1(this, null), 1, null);
         return this.cont.getResult();
      }
   }

   @PublishedApi
   internal fun handleBuilderException(e: Throwable) {
      this.cont.resumeWith(Result.constructor-impl(ResultKt.createFailure(e)));
   }
}
