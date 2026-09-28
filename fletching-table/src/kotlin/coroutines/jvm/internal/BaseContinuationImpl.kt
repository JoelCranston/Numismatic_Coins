package kotlin.coroutines.jvm.internal

import java.io.Serializable
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt

@SinceKotlin(version = "1.3")
@PublishedApi
internal abstract class BaseContinuationImpl : Continuation<Object>, CoroutineStackFrame, Serializable {
   public final val completion: Continuation<Any?>?

   public open val callerFrame: CoroutineStackFrame?
      public open get() {
         return this.completion as? CoroutineStackFrame;
      }


   open fun BaseContinuationImpl(completion: Continuation<Object>?) {
      this.completion = completion;
   }

   public override fun resumeWith(result: Result<Any?>) {
      var var11: Any = this;
      var var12: Any = result;

      while (true) {
         DebugProbesKt.probeCoroutineResumed(var11 as Continuation<?>);
         val `$this$resumeWith_u24lambda_u240`: BaseContinuationImpl = var11 as BaseContinuationImpl;
         val var10000: Continuation = (var11 as BaseContinuationImpl).completion;

         var outcome: Any;
         try {
            outcome = `$this$resumeWith_u24lambda_u240`.invokeSuspend(var12);
            if (outcome === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               return;
            }

            outcome = Result.constructor-impl(outcome);
         } catch (var10: java.lang.Throwable) {
            outcome = Result.constructor-impl(ResultKt.createFailure(var10));
         }

         `$this$resumeWith_u24lambda_u240`.releaseIntercepted();
         if (var10000 !is BaseContinuationImpl) {
            var10000.resumeWith(outcome);
            return;
         }

         var11 = var10000;
         var12 = outcome;
      }
   }

   protected abstract fun invokeSuspend(result: Result<Any?>): Any? {
   }

   protected open fun releaseIntercepted() {
   }

   public open fun create(completion: Continuation<*>): Continuation<Unit> {
      throw new UnsupportedOperationException("create(Continuation) has not been overridden");
   }

   public open fun create(value: Any?, completion: Continuation<*>): Continuation<Unit> {
      throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
   }

   public override fun toString(): String {
      val var10000: StringBuilder = new StringBuilder().append("Continuation at ");
      var var10001: Any = this.getStackTraceElement();
      if (var10001 == null) {
         var10001 = this.getClass().getName();
      }

      return var10000.append(var10001).toString();
   }

   public override fun getStackTraceElement(): StackTraceElement? {
      return DebugMetadataKt.getStackTraceElement(this);
   }
}
