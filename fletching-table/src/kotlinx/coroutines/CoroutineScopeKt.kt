package kotlinx.coroutines

import java.util.concurrent.CancellationException
import kotlin.contracts.InvocationKind
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.InlineMarker
import kotlinx.coroutines.internal.ContextScope
import kotlinx.coroutines.internal.ScopeCoroutine
import kotlinx.coroutines.intrinsics.UndispatchedKt

public final val isActive: Boolean
   public final get() {
      val var10000: Job = `$this$isActive`.getCoroutineContext().get(Job.Key);
      return var10000 == null || var10000.isActive();
   }


public operator fun CoroutineScope.plus(context: CoroutineContext): CoroutineScope {
   return new ContextScope(`$this$plus`.getCoroutineContext().plus(context));
}

public fun MainScope(): CoroutineScope {
   return new ContextScope(SupervisorKt.SupervisorJob$default(null, 1, null).plus(Dispatchers.getMain()));
}

public suspend fun <R> coroutineScope(block: (CoroutineScope, Continuation<R>) -> Any?): R {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val coroutine: ScopeCoroutine = new ScopeCoroutine(`$completion`.getContext(), `$completion`);
   val var10000: Any = UndispatchedKt.startUndispatchedOrReturn(coroutine, coroutine, block);
   if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
      DebugProbesKt.probeCoroutineSuspended(`$completion`);
   }

   return var10000;
}

public fun CoroutineScope(context: CoroutineContext): CoroutineScope {
   return new ContextScope(if (context.get(Job.Key) != null) context else context.plus(JobKt.Job$default(null, 1, null)));
}

public fun CoroutineScope.cancel(cause: CancellationException? = null) {
   val var10000: Job = `$this$cancel`.getCoroutineContext().get(Job.Key);
   if (var10000 == null) {
      throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: $`$this$cancel`").toString());
   } else {
      var10000.cancel(cause);
   }
}

@JvmSynthetic
fun `cancel$default`(var0: CoroutineScope, var1: CancellationException, var2: Int, var3: Any) {
   if ((var2 and 1) != 0) {
      var1 = null;
   }

   cancel(var0, var1);
}

public fun CoroutineScope.cancel(message: String, cause: Throwable? = null) {
   cancel(`$this$cancel`, ExceptionsKt.CancellationException(message, cause));
}

@JvmSynthetic
fun `cancel$default`(var0: CoroutineScope, var1: java.lang.String, var2: java.lang.Throwable, var3: Int, var4: Any) {
   if ((var3 and 2) != 0) {
      var2 = null;
   }

   cancel(var0, var1, var2);
}

public fun CoroutineScope.ensureActive() {
   JobKt.ensureActive(`$this$ensureActive`.getCoroutineContext());
}

public suspend inline fun currentCoroutineContext(): CoroutineContext {
   return `$completion`.getContext();
}

fun `currentCoroutineContext$$forInline`(`$completion`: Continuation<? super CoroutineContext>): Any {
   InlineMarker.mark(3);
   return null.getContext();
}
