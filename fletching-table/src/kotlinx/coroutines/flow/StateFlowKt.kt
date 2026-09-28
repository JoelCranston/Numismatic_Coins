@file:SourceDebugExtension(["SMAP\nStateFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StateFlow.kt\nkotlinx/coroutines/flow/StateFlowKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,433:1\n1#2:434\n*E\n"])

package kotlinx.coroutines.flow

import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.internal.NullSurrogateKt
import kotlinx.coroutines.internal.Symbol

private final val NONE: Symbol = new Symbol("NONE")
private final val PENDING: Symbol = new Symbol("PENDING")

public fun <T> MutableStateFlow(value: T): MutableStateFlow<T> {
   val var10000: StateFlowImpl = new StateFlowImpl;
   var var10002: Any = value;
   if (value == null) {
      var10002 = NullSurrogateKt.NULL;
   }

   var10000./* $VF: Unable to resugar constructor */<init>(var10002);
   return var10000;
}

public inline fun <T> MutableStateFlow<T>.updateAndGet(function: (T) -> T): T {
   val prevValue: Any;
   val nextValue: Any;
   do {
      prevValue = `$this$updateAndGet`.getValue();
      nextValue = function.invoke(prevValue);
   } while (!$this$updateAndGet.compareAndSet(prevValue, nextValue));

   return (T)nextValue;
}

public inline fun <T> MutableStateFlow<T>.getAndUpdate(function: (T) -> T): T {
   val prevValue: Any;
   do {
      prevValue = `$this$getAndUpdate`.getValue();
   } while (!$this$getAndUpdate.compareAndSet(prevValue, function.invoke(prevValue)));

   return (T)prevValue;
}

public inline fun <T> MutableStateFlow<T>.update(function: (T) -> T) {
   val prevValue: Any;
   do {
      prevValue = `$this$update`.getValue();
   } while (!$this$update.compareAndSet(prevValue, function.invoke(prevValue)));
}

internal fun <T> StateFlow<T>.fuseStateFlow(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow): Flow<T> {
   if (DebugKt.getASSERTIONS_ENABLED() && capacity == -1) {
      throw new AssertionError();
   } else {
      return (Flow<T>)(if ((0 <= capacity && capacity < 2 || capacity == -2) && onBufferOverflow === BufferOverflow.DROP_OLDEST)
         `$this$fuseStateFlow`
         else
         SharedFlowKt.fuseSharedFlow(`$this$fuseStateFlow`, context, capacity, onBufferOverflow));
   }
}

@JvmSynthetic
fun `access$getNONE$p`(): Symbol {
   return NONE;
}

@JvmSynthetic
fun `access$getPENDING$p`(): Symbol {
   return PENDING;
}
