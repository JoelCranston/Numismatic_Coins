package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationKt
import kotlin.enums.EnumEntries
import kotlinx.coroutines.intrinsics.CancellableKt
import kotlinx.coroutines.intrinsics.UndispatchedKt

public enum class CoroutineStart {
   DEFAULT,
   LAZY,
   @DelicateCoroutinesApi
   ATOMIC,
   UNDISPATCHED

   @InternalCoroutinesApi
   public final val isLazy: Boolean
      public final get() {
         return this === LAZY;
      }


   @InternalCoroutinesApi
   public operator fun <R, T> invoke(block: (R, Continuation<T>) -> Any?, receiver: R, completion: Continuation<T>) {
      switch (CoroutineStart.WhenMappings.$EnumSwitchMapping$0[this.ordinal()]) {
         case 1:
            CancellableKt.startCoroutineCancellable(block, receiver, completion);
            break;
         case 2:
            ContinuationKt.startCoroutine(block, receiver, completion);
            break;
         case 3:
            UndispatchedKt.startCoroutineUndispatched(block, receiver, completion);
         case 4:
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   @JvmStatic
   fun getEntries(): EnumEntries<CoroutineStart> {
      return $ENTRIES;
   }
}
