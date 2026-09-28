package kotlin.sequences

import kotlin.coroutines.RestrictsSuspension
import kotlin.coroutines.intrinsics.IntrinsicsKt

@RestrictsSuspension
@SinceKotlin(version = "1.3")
public abstract class SequenceScope<T> {
   public abstract suspend fun yield(value: Any) {
   }

   public abstract suspend fun yieldAll(iterator: Iterator<Any>) {
   }

   public suspend fun yieldAll(elements: Iterable<Any>) {
      if (elements is java.util.Collection && (elements as java.util.Collection).isEmpty()) {
         return Unit.INSTANCE;
      } else {
         val var10000: Any = this.yieldAll(elements.iterator(), `$completion`);
         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }
   }

   public suspend fun yieldAll(sequence: Sequence<Any>) {
      val var10000: Any = this.yieldAll(sequence.iterator(), `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}
