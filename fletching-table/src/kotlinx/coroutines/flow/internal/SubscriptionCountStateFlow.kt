package kotlinx.coroutines.flow.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.SharedFlowImpl
import kotlinx.coroutines.flow.StateFlow

@SourceDebugExtension(["SMAP\nAbstractSharedFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractSharedFlow.kt\nkotlinx/coroutines/flow/internal/SubscriptionCountStateFlow\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n*L\n1#1,130:1\n29#2:131\n29#2:133\n16#3:132\n16#3:134\n*S KotlinDebug\n*F\n+ 1 AbstractSharedFlow.kt\nkotlinx/coroutines/flow/internal/SubscriptionCountStateFlow\n*L\n124#1:131\n126#1:133\n124#1:132\n126#1:134\n*E\n"])
private class SubscriptionCountStateFlow(initialValue: Int) : SharedFlowImpl(1, Integer.MAX_VALUE, BufferOverflow.DROP_OLDEST), StateFlow<Integer> {
   public open val value: Int
      public open get() {
         val var10000: Int;
         synchronized (this) {
            var10000 = this.getLastReplayedLocked().intValue();
         }

         return var10000;
      }


   init {
      this.tryEmit(initialValue);
   }

   public fun increment(delta: Int): Boolean {
      val var10000: Boolean;
      synchronized (this) {
         var10000 = this.tryEmit(this.getLastReplayedLocked().intValue() + delta);
      }

      return var10000;
   }
}
