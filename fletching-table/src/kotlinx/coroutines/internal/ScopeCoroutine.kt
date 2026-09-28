package kotlinx.coroutines.internal

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlinx.coroutines.AbstractCoroutine
import kotlinx.coroutines.CompletionStateKt

internal open class ScopeCoroutine<T>(context: CoroutineContext, uCont: Continuation<Any>) : AbstractCoroutine(context, true, true), CoroutineStackFrame {
   public final val uCont: Continuation<Any>

   public final val callerFrame: CoroutineStackFrame?
      public final get() {
         return this.uCont as? CoroutineStackFrame;
      }


   protected final val isScopedCoroutine: Boolean
      protected final get() {
         return true;
      }


   init {
      this.uCont = uCont;
   }

   public override fun getStackTraceElement(): StackTraceElement? {
      return null;
   }

   protected override fun afterCompletion(state: Any?) {
      DispatchedContinuationKt.resumeCancellableWith(IntrinsicsKt.intercepted(this.uCont), CompletionStateKt.recoverResult(state, this.uCont));
   }

   public open fun afterCompletionUndispatched() {
   }

   protected override fun afterResume(state: Any?) {
      this.uCont.resumeWith(CompletionStateKt.recoverResult(state, this.uCont));
   }
}
