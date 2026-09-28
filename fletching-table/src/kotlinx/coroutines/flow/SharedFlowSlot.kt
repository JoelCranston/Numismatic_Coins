package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot

@SourceDebugExtension(["SMAP\nSharedFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedFlow.kt\nkotlinx/coroutines/flow/SharedFlowSlot\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,746:1\n1#2:747\n*E\n"])
internal class SharedFlowSlot : AbstractSharedFlowSlot<SharedFlowImpl<?>> {
   public final var index: Long = -1L
      private set

   public final var cont: Continuation<Unit>?
      private set

   public open fun allocateLocked(flow: SharedFlowImpl<*>): Boolean {
      if (this.index >= 0L) {
         return false;
      } else {
         this.index = flow.updateNewCollectorIndexLocked$kotlinx_coroutines_core();
         return true;
      }
   }

   public open fun freeLocked(flow: SharedFlowImpl<*>): Array<Continuation<Unit>?> {
      if (DebugKt.getASSERTIONS_ENABLED() && this.index < 0L) {
         throw new AssertionError();
      } else {
         val var4: Long = this.index;
         this.index = -1L;
         this.cont = null;
         return flow.updateCollectorIndexLocked$kotlinx_coroutines_core(var4);
      }
   }
}
