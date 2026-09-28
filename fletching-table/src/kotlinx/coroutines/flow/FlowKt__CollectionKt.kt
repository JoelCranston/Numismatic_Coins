package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.coroutines.flow.FlowKt__CollectionKt.toCollection.1
import kotlinx.coroutines.flow.FlowKt__CollectionKt.toCollection.2

@JvmSynthetic
internal class FlowKt__CollectionKt {
   @JvmStatic
   public suspend fun <T> Flow<T>.toList(destination: MutableList<T> = ...): List<T> {
      return FlowKt.toCollection(`$this$toList`, destination, `$completion`);
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.toSet(destination: MutableSet<T> = ...): Set<T> {
      return FlowKt.toCollection(`$this$toSet`, destination, `$completion`);
   }

   @JvmStatic
   public suspend fun <T, C : MutableCollection<in T>> Flow<T>.toCollection(destination: C): C {
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
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val var10001: FlowCollector = new 2(destination);
            `$continuation`.L$0 = destination;
            `$continuation`.label = 1;
            if (`$this$toCollection`.collect(var10001, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            destination = `$continuation`.L$0 as java.util.Collection;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return destination;
   }
}
