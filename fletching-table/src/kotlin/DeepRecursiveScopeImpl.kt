package kotlin

import kotlin.DeepRecursiveScopeImpl.crossFunctionCompletion..inlined.Continuation.1
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.BaseContinuationImpl
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.TypeIntrinsics

private class DeepRecursiveScopeImpl<T, R>(block: (DeepRecursiveScope<Any, Any>, Any, Continuation<Any>) -> Any?, value: Any) : DeepRecursiveScope(),
   Continuation<R> {
   private final var function: (DeepRecursiveScope<*, *>, Any?, Continuation<Any?>) -> Any?
   private final var value: Any?
   private final var cont: Continuation<Any?>?
   private final var result: Result<Any?>

   public open val context: CoroutineContext
      public open get() {
         return EmptyCoroutineContext.INSTANCE;
      }


   init {
      this.function = block;
      this.value = value;
      this.cont = this as Continuation<Object>;
      this.result = DeepRecursiveKt.access$getUNDEFINED_RESULT$p();
   }

   public override fun resumeWith(result: Result<Any>) {
      this.cont = null;
      this.result = result;
   }

   public override suspend fun callRecursive(value: Any): Any {
      this.cont = `$completion`;
      this.value = value;
      val var10000: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return var10000;
   }

   public override suspend fun <U, S> DeepRecursiveFunction<U, S>.callRecursive(value: U): S {
      var var10000: Function3 = `$this$callRecursive`.getBlock$kotlin_stdlib();
      val `$this$callRecursive_u24lambda_u241_u240`: DeepRecursiveScopeImpl = this;
      val currentFunction: Function3 = this.function;
      if (var10000 != this.function) {
         `$this$callRecursive_u24lambda_u241_u240`.function = var10000;
         `$this$callRecursive_u24lambda_u241_u240`.cont = `$this$callRecursive_u24lambda_u241_u240`.crossFunctionCompletion(currentFunction, `$completion`);
      } else {
         `$this$callRecursive_u24lambda_u241_u240`.cont = `$completion`;
      }

      `$this$callRecursive_u24lambda_u241_u240`.value = value;
      var10000 = (Function3)IntrinsicsKt.getCOROUTINE_SUSPENDED();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return var10000;
   }

   private fun crossFunctionCompletion(currentFunction: (DeepRecursiveScope<*, *>, Any?, Continuation<Any?>) -> Any?, cont: Continuation<Any?>): Continuation<
         Any?
      > {
      return new 1(EmptyCoroutineContext.INSTANCE, this, currentFunction, cont);
   }

   public fun runCallLoop(): Any {
      while (true) {
         val result: Any = this.result;
         if (this.cont == null) {
            ResultKt.throwOnFailure(this.result);
            return (R)result;
         }

         val cont: Continuation = this.cont;
         if (Result.equals-impl0(DeepRecursiveKt.access$getUNDEFINED_RESULT$p(), result)) {
            var var7: Any;
            try {
               val e: Any = this.value;
               var7 = if (this.function !is BaseContinuationImpl)
                  IntrinsicsKt.wrapWithContinuationImpl(
                     (Function3<? super DeepRecursiveScopeImpl<T, R>, ? super Object, ? super Continuation<? super Object>, ? extends Object>)this.function,
                     this,
                     this.value,
                     cont
                  )
                  else
                  (TypeIntrinsics.beforeCheckcastToFunctionOfArity(this.function, 3) as Function3).invoke(this, e, cont);
            } catch (var6: java.lang.Throwable) {
               cont.resumeWith(Result.constructor-impl(ResultKt.createFailure(var6)));
               continue;
            }

            if (var7 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               cont.resumeWith(Result.constructor-impl(var7));
            }
         } else {
            this.result = DeepRecursiveKt.access$getUNDEFINED_RESULT$p();
            cont.resumeWith(result);
         }
      }
   }
}
