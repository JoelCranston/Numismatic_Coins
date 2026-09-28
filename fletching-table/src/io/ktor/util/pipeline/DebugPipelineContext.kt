package io.ktor.util.pipeline

import io.ktor.util.pipeline.DebugPipelineContext.proceedLoop.1
import io.ktor.utils.io.KtorDsl
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function3

@KtorDsl
internal class DebugPipelineContext<TSubject, TContext>(context: Any,
   interceptors: List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?>,
   subject: Any,
   coroutineContext: CoroutineContext
) : PipelineContext((TContext)context) {
   private final val interceptors: List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?>
   public open val coroutineContext: CoroutineContext
   public open var subject: Any
   private final var index: Int

   init {
      this.interceptors = interceptors;
      this.coroutineContext = coroutineContext;
      this.subject = (TSubject)subject;
   }

   public override fun finish() {
      this.index = -1;
   }

   public override suspend fun proceedWith(subject: Any): Any {
      this.setSubject((TSubject)subject);
      return this.proceed(`$completion`);
   }

   public override suspend fun proceed(): Any {
      if (this.index < 0) {
         return this.getSubject();
      } else if (this.index >= this.interceptors.size()) {
         this.finish();
         return this.getSubject();
      } else {
         return this.proceedLoop(`$completion`);
      }
   }

   internal override suspend fun execute(initial: Any): Any {
      this.index = 0;
      this.setSubject((TSubject)initial);
      return this.proceed(`$completion`);
   }

   private suspend fun proceedLoop(): Any {
      var `$continuation`: Continuation;
      label40: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label40;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            break;
         case 1:
            val index: Int = `$continuation`.I$0;
            val executeInterceptor: Function3 = `$continuation`.L$1 as Function3;
            val interceptors: java.util.List = `$continuation`.L$0 as java.util.List;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val var10: Function3;
      val var10002: Any;
      do {
         val var8: Int = this.index;
         if (this.index == -1) {
            return this.getSubject();
         }

         val var9: java.util.List = this.interceptors;
         if (this.index >= this.interceptors.size()) {
            this.finish();
            return this.getSubject();
         }

         var10 = var9.get(var8) as Function3;
         this.index = var8 + 1;
         var10002 = this.getSubject();
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var9);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var10);
         `$continuation`.I$0 = var8;
         `$continuation`.label = 1;
      } while (executeInterceptor.invoke(this, var10002, $continuation) != var7);

      return var7;
   }
}
