package kotlinx.coroutines.selects

import kotlin.coroutines.CoroutineContext

internal class SelectClause0Impl(clauseObject: Any,
      regFunc: (Any, SelectInstance<*>, Any?) -> Unit,
      onCancellationConstructor: ((SelectInstance<*>, Any?, Any?) -> (Throwable, Any?, CoroutineContext) -> Unit)? = null
   ) :
   SelectClause0 {
   public open val clauseObject: Any
   public open val regFunc: (Any, SelectInstance<*>, Any?) -> Unit
   public open val onCancellationConstructor: ((SelectInstance<*>, Any?, Any?) -> (Throwable, Any?, CoroutineContext) -> Unit)?
   public open val processResFunc: (Any, Any?, Any?) -> Any?

   init {
      this.clauseObject = clauseObject;
      this.regFunc = regFunc;
      this.onCancellationConstructor = onCancellationConstructor;
      this.processResFunc = SelectKt.access$getDUMMY_PROCESS_RESULT_FUNCTION$p();
   }
}
