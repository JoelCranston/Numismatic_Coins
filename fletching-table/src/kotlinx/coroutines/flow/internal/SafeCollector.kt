package kotlinx.coroutines.flow.internal

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.ContinuationImpl
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.flow.FlowCollector

@SourceDebugExtension(["SMAP\nSafeCollector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.kt\nkotlinx/coroutines/flow/internal/SafeCollector\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,182:1\n1#2:183\n*E\n"])
internal class SafeCollector<T>(collector: FlowCollector<Any>, collectContext: CoroutineContext) : ContinuationImpl(
         NoOpContinuation.INSTANCE, EmptyCoroutineContext.INSTANCE
      ),
   FlowCollector<T>,
   CoroutineStackFrame {
   internal final val collector: FlowCollector<Any>
   internal final val collectContext: CoroutineContext

   public open val callerFrame: CoroutineStackFrame?
      public open get() {
         return this.completion_ as? CoroutineStackFrame;
      }


   internal final val collectContextSize: Int
   private final var lastEmissionContext: CoroutineContext?
   private final var completion_: Continuation<Unit>?

   public open val context: CoroutineContext
      public open get() {
         var var10000: CoroutineContext = this.lastEmissionContext;
         if (this.lastEmissionContext == null) {
            var10000 = EmptyCoroutineContext.INSTANCE;
         }

         return var10000;
      }


   init {
      this.collector = collector;
      this.collectContext = collectContext;
      this.collectContextSize = this.collectContext.fold(0, SafeCollector::collectContextSize$lambda$0).intValue();
   }

   public override fun getStackTraceElement(): StackTraceElement? {
      return null;
   }

   protected override fun invokeSuspend(result: Result<Any?>): Any {
      val var10000: java.lang.Throwable = Result.exceptionOrNull-impl(result);
      if (var10000 != null) {
         this.lastEmissionContext = new DownstreamExceptionContext(var10000, this.getContext());
      }

      if (this.completion_ != null) {
         this.completion_.resumeWith(result);
      }

      return IntrinsicsKt.getCOROUTINE_SUSPENDED();
   }

   public override fun releaseIntercepted() {
      super.releaseIntercepted();
   }

   public override suspend fun emit(value: Any) {
      val uCont: Continuation = `$completion`;

      var var5: Any;
      try {
         var5 = this.emit(uCont, (T)value);
      } catch (var7: java.lang.Throwable) {
         this.lastEmissionContext = new DownstreamExceptionContext(var7, `$completion`.getContext());
         throw var7;
      }

      if (var5 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return if (var5 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var5 else Unit.INSTANCE;
   }

   private fun emit(uCont: Continuation<Unit>, value: Any): Any? {
      val currentContext: CoroutineContext = uCont.getContext();
      JobKt.ensureActive(currentContext);
      if (this.lastEmissionContext != currentContext) {
         this.checkContext(currentContext, this.lastEmissionContext, (T)value);
         this.lastEmissionContext = currentContext;
      }

      this.completion_ = uCont;
      val var10000: Function3 = SafeCollectorKt.access$getEmitFun$p();
      val var10001: FlowCollector = this.collector;
      val result: Any = var10000.invoke(var10001, value, this);
      if (!(result == IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
         this.completion_ = null;
      }

      return result;
   }

   private fun checkContext(currentContext: CoroutineContext, previousContext: CoroutineContext?, value: Any) {
      if (previousContext is DownstreamExceptionContext) {
         this.exceptionTransparencyViolated(previousContext as DownstreamExceptionContext, value);
      }

      SafeCollector_commonKt.checkContext(this, currentContext);
   }

   private fun exceptionTransparencyViolated(exception: DownstreamExceptionContext, value: Any?) {
      throw new IllegalStateException(
         StringsKt.trimIndent(
               "\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception ${exception.e}, but then emission attempt of value '$value' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            "
            )
            .toString()
      );
   }

   @JvmStatic
   fun `collectContextSize$lambda$0`(count: Int, var1: CoroutineContext.Element): Int {
      return count + 1;
   }
}
