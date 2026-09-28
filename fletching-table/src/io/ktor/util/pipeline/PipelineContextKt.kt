package io.ktor.util.pipeline

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

internal fun <TSubject : Any, TContext : Any> pipelineContextFor(
   context: Any,
   interceptors: List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?>,
   subject: Any,
   coroutineContext: CoroutineContext,
   debugMode: Boolean = false
): PipelineContext<Any, Any> {
   return (PipelineContext<TSubject, TContext>)(if (!PipelineContext_jvmKt.getDISABLE_SFG() && !debugMode)
      new SuspendFunctionGun<>(subject, context, interceptors)
      else
      new DebugPipelineContext<>(context, interceptors, subject, coroutineContext));
}

@JvmSynthetic
fun `pipelineContextFor$default`(var0: Any, var1: java.util.List, var2: Any, var3: CoroutineContext, var4: Boolean, var5: Int, var6: Any): PipelineContext {
   if ((var5 and 16) != 0) {
      var4 = false;
   }

   return pipelineContextFor(var0, var1, var2, var3, var4);
}
