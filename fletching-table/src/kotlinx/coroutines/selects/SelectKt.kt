package kotlinx.coroutines.selects

import kotlin.contracts.InvocationKind
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.InlineMarker
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.Symbol
import kotlinx.coroutines.selects.SelectKt.DUMMY_PROCESS_RESULT_FUNCTION.1

private final val DUMMY_PROCESS_RESULT_FUNCTION: (Any, Any?, Any?) -> Any? = 1.INSTANCE as Function3
private const val TRY_SELECT_SUCCESSFUL: Int = 0
private const val TRY_SELECT_REREGISTER: Int = 1
private const val TRY_SELECT_CANCELLED: Int = 2
private const val TRY_SELECT_ALREADY_SELECTED: Int = 3
private final val STATE_REG: Symbol = new Symbol("STATE_REG")
private final val STATE_COMPLETED: Symbol = new Symbol("STATE_COMPLETED")
private final val STATE_CANCELLED: Symbol = new Symbol("STATE_CANCELLED")
private final val NO_RESULT: Symbol = new Symbol("NO_RESULT")
internal final val PARAM_CLAUSE_0: Symbol = new Symbol("PARAM_CLAUSE_0")

public suspend inline fun <R> select(crossinline builder: (SelectBuilder<R>) -> Unit): R {
   contract {
      callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
   }

   val `$this$select_u24lambda_u240`: SelectImplementation = new SelectImplementation(`$completion`.getContext());
   builder.invoke(`$this$select_u24lambda_u240`);
   return `$this$select_u24lambda_u240`.doSelect(`$completion`);
}

fun <R> `select$$forInline`(builder: (SelectBuilder<? super R>?) -> Unit, `$completion`: Continuation<? super R>): Any {
   InlineMarker.mark(3);
   val `$this$select_u24lambda_u240`: SelectImplementation = new SelectImplementation(null.getContext());
   builder.invoke(`$this$select_u24lambda_u240`);
   InlineMarker.mark(3);
   InlineMarker.mark(0);
   val var10000: Any = `$this$select_u24lambda_u240`.doSelect(null);
   InlineMarker.mark(1);
   return var10000;
}

private fun CancellableContinuation<Unit>.tryResume(onCancellation: ((Throwable, Any?, CoroutineContext) -> Unit)?): Boolean {
   val var10000: Any = `$this$tryResume`.tryResume(Unit.INSTANCE, null, onCancellation);
   if (var10000 == null) {
      return false;
   } else {
      `$this$tryResume`.completeResume(var10000);
      return true;
   }
}

private fun TrySelectDetailedResult(trySelectInternalResult: Int): TrySelectDetailedResult {
   var var10000: TrySelectDetailedResult;
   switch (trySelectInternalResult) {
      case 0:
         var10000 = TrySelectDetailedResult.SUCCESSFUL;
         break;
      case 1:
         var10000 = TrySelectDetailedResult.REREGISTER;
         break;
      case 2:
         var10000 = TrySelectDetailedResult.CANCELLED;
         break;
      case 3:
         var10000 = TrySelectDetailedResult.ALREADY_SELECTED;
         break;
      default:
         throw new IllegalStateException(("Unexpected internal result: $trySelectInternalResult").toString());
   }

   return var10000;
}

/** @deprecated */
@InternalCoroutinesApi
@JvmSynthetic
fun `RegistrationFunction$annotations`() {
}

/** @deprecated */
@InternalCoroutinesApi
@JvmSynthetic
fun `ProcessResultFunction$annotations`() {
}

/** @deprecated */
@InternalCoroutinesApi
@JvmSynthetic
fun `OnCancellationConstructor$annotations`() {
}

@JvmSynthetic
fun `access$getDUMMY_PROCESS_RESULT_FUNCTION$p`(): Function3 {
   return DUMMY_PROCESS_RESULT_FUNCTION;
}

@JvmSynthetic
fun `access$getSTATE_REG$p`(): Symbol {
   return STATE_REG;
}

@JvmSynthetic
fun `access$getNO_RESULT$p`(): Symbol {
   return NO_RESULT;
}

@JvmSynthetic
fun `access$getSTATE_CANCELLED$p`(): Symbol {
   return STATE_CANCELLED;
}

@JvmSynthetic
fun `access$TrySelectDetailedResult`(trySelectInternalResult: Int): TrySelectDetailedResult {
   return TrySelectDetailedResult(trySelectInternalResult);
}

@JvmSynthetic
fun `access$tryResume`(`$receiver`: CancellableContinuation, onCancellation: Function3): Boolean {
   return tryResume(`$receiver`, onCancellation);
}

@JvmSynthetic
fun `access$getSTATE_COMPLETED$p`(): Symbol {
   return STATE_COMPLETED;
}
