package kotlinx.coroutines.selects

import kotlin.contracts.InvocationKind
import kotlin.coroutines.Continuation
import kotlin.jvm.internal.InlineMarker

public suspend inline fun <R> selectUnbiased(crossinline builder: (SelectBuilder<R>) -> Unit): R {
   contract {
      callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
   }

   val `$this$selectUnbiased_u24lambda_u240`: UnbiasedSelectImplementation = new UnbiasedSelectImplementation(`$completion`.getContext());
   builder.invoke(`$this$selectUnbiased_u24lambda_u240`);
   return `$this$selectUnbiased_u24lambda_u240`.doSelect(`$completion`);
}

fun <R> `selectUnbiased$$forInline`(builder: (SelectBuilder<? super R>?) -> Unit, `$completion`: Continuation<? super R>): Any {
   InlineMarker.mark(3);
   val `$this$selectUnbiased_u24lambda_u240`: UnbiasedSelectImplementation = new UnbiasedSelectImplementation(null.getContext());
   builder.invoke(`$this$selectUnbiased_u24lambda_u240`);
   InlineMarker.mark(3);
   InlineMarker.mark(0);
   val var10000: Any = `$this$selectUnbiased_u24lambda_u240`.doSelect(null);
   InlineMarker.mark(1);
   return var10000;
}
