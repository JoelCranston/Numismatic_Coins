package io.ktor.util.pipeline

import io.ktor.util.debug.ContextUtilsKt
import io.ktor.util.pipeline.PipelineKt.execute.2
import io.ktor.util.pipeline.PipelineKt.intercept.1
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Intrinsics

public suspend inline fun <TContext : Any> Pipeline<Unit, Any>.execute(context: Any) {
   val var10000: Any = ContextUtilsKt.initContextInDebugMode(new 2(`$this$execute`, context, null), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

fun <TContext> Pipeline<Unit, TContext>.`execute$$forInline`(context: TContext, `$completion`: Continuation<? super Unit>): Any {
   val var10000: Function1 = new 2(`$this$execute`, (TContext)context, null);
   InlineMarker.mark(0);
   ContextUtilsKt.initContextInDebugMode(var10000, `$completion`);
   InlineMarker.mark(1);
   return Unit.INSTANCE;
}

@JvmSynthetic
public inline fun <reified TSubject : Any, TContext : Any> Pipeline<*, Any>.intercept(
   phase: PipelinePhase,
   noinline block: (PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?
) {
   Intrinsics.needClassReification();
   `$this$intercept`.intercept(phase, new 1(block, null));
}
