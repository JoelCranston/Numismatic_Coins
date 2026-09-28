@file:SourceDebugExtension(["SMAP\nWhileSelect.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WhileSelect.kt\nkotlinx/coroutines/selects/WhileSelectKt\n+ 2 Select.kt\nkotlinx/coroutines/selects/SelectKt\n*L\n1#1,29:1\n54#2,5:30\n*S KotlinDebug\n*F\n+ 1 WhileSelect.kt\nkotlinx/coroutines/selects/WhileSelectKt\n*L\n27#1:30,5\n*E\n"])

package kotlinx.coroutines.selects

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.selects.WhileSelectKt.whileSelect.1

@ExperimentalCoroutinesApi
public suspend inline fun whileSelect(crossinline builder: (SelectBuilder<Boolean>) -> Unit) {
   var `$continuation`: Continuation;
   label33: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label33;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         break;
      case 1:
         builder = `$continuation`.L$0 as Function1;
         ResultKt.throwOnFailure(`$result`);
         if (!`$result` as java.lang.Boolean) {
            return Unit.INSTANCE;
         }
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   val var10000: Any;
   do {
      val `$this$select_u24lambda_u240$iv`: SelectImplementation = new SelectImplementation(`$continuation`.getContext());
      builder.invoke(`$this$select_u24lambda_u240$iv`);
      `$continuation`.L$0 = builder;
      `$continuation`.label = 1;
      var10000 = `$this$select_u24lambda_u240$iv`.doSelect(`$continuation`);
      if (var10000 === var8) {
         return var8;
      }
   } while ((java.lang.Boolean)var10000);

   return Unit.INSTANCE;
}

@ExperimentalCoroutinesApi
fun `whileSelect$$forInline`(builder: (SelectBuilder<? super java.lang.Boolean>?) -> Unit, `$completion`: Continuation<? super Unit>): Any {
   val var10000: Any;
   do {
      InlineMarker.mark(3);
      val `$this$select_u24lambda_u240$iv`: SelectImplementation = new SelectImplementation(null.getContext());
      builder.invoke(`$this$select_u24lambda_u240$iv`);
      InlineMarker.mark(3);
      InlineMarker.mark(0);
      var10000 = `$this$select_u24lambda_u240$iv`.doSelect(null);
      InlineMarker.mark(1);
   } while ((java.lang.Boolean)var10000);

   return Unit.INSTANCE;
}
