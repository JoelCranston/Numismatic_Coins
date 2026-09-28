package io.ktor.util.pipeline

import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.TypeIntrinsics

internal fun <TSubject : Any, TContext : Any> pipelineStartCoroutineUninterceptedOrReturn(
   interceptor: (PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?,
   context: PipelineContext<Any, Any>,
   subject: Any,
   continuation: Continuation<Unit>
): Any? {
   return (TypeIntrinsics.beforeCheckcastToFunctionOfArity(interceptor, 3) as Function3).invoke(context, subject, continuation);
}
