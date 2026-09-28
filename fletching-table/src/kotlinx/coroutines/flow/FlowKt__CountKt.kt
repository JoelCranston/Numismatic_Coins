package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.jvm.internal.Ref
import kotlinx.coroutines.flow.FlowKt__CountKt.count.1
import kotlinx.coroutines.flow.FlowKt__CountKt.count.2
import kotlinx.coroutines.flow.FlowKt__CountKt.count.3
import kotlinx.coroutines.flow.FlowKt__CountKt.count.4

@JvmSynthetic
internal class FlowKt__CountKt {
   @JvmStatic
   public suspend fun <T> Flow<T>.count(): Int {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new 1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var i: Ref.IntRef;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            i = new Ref.IntRef();
            val var10001: FlowCollector = new 2(i);
            `$continuation`.L$0 = i;
            `$continuation`.label = 1;
            if (`$this$count`.collect(var10001, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            i = `$continuation`.L$0 as Ref.IntRef;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return Boxing.boxInt(i.element);
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.count(predicate: (T, Continuation<Boolean>) -> Any?): Int {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is 3) {
            `$continuation` = `$completion` as 3;
            if (((`$completion` as 3).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new 3(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var i: Ref.IntRef;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            i = new Ref.IntRef();
            val var10001: FlowCollector = new 4(predicate, i);
            `$continuation`.L$0 = i;
            `$continuation`.label = 1;
            if (`$this$count`.collect(var10001, `$continuation`) === var6) {
               return var6;
            }
            break;
         case 1:
            i = `$continuation`.L$0 as Ref.IntRef;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return Boxing.boxInt(i.element);
   }
}
