package kotlinx.coroutines.flow

import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellableContinuationImpl
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
import kotlinx.coroutines.internal.Concurrent_commonKt

@SourceDebugExtension(["SMAP\nStateFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StateFlow.kt\nkotlinx/coroutines/flow/StateFlowSlot\n+ 2 Concurrent.common.kt\nkotlinx/coroutines/internal/Concurrent_commonKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,433:1\n37#2,2:434\n1#3:436\n426#4,11:437\n*S KotlinDebug\n*F\n+ 1 StateFlow.kt\nkotlinx/coroutines/flow/StateFlowSlot\n*L\n280#1:434,2\n303#1:437,11\n*E\n"])
private class StateFlowSlot : AbstractSharedFlowSlot<StateFlowImpl<?>> {
   private final val _state: AtomicReference<Any?> = new AtomicReference(null)

   public open fun allocateLocked(flow: StateFlowImpl<*>): Boolean {
      if (Concurrent_commonKt.getValue(this._state) != null) {
         return false;
      } else {
         Concurrent_commonKt.setValue(this._state, StateFlowKt.access$getNONE$p());
         return true;
      }
   }

   public open fun freeLocked(flow: StateFlowImpl<*>): Array<Continuation<Unit>?> {
      Concurrent_commonKt.setValue(this._state, null);
      return AbstractSharedFlowKt.EMPTY_RESUMES;
   }

   public fun makePending() {
      val `$this$loop$iv`: AtomicReference = this._state;

      while (true) {
         val state: Any = Concurrent_commonKt.getValue(`$this$loop$iv`);
         if (state == null) {
            return;
         }

         if (state === StateFlowKt.access$getPENDING$p()) {
            return;
         }

         if (state === StateFlowKt.access$getNONE$p()) {
            if (this._state.compareAndSet(state, StateFlowKt.access$getPENDING$p())) {
               return;
            }
         } else if (this._state.compareAndSet(state, StateFlowKt.access$getNONE$p())) {
            (state as CancellableContinuationImpl).resumeWith(Result.constructor-impl(Unit.INSTANCE));
            return;
         }
      }
   }

   public fun takePending(): Boolean {
      val var10000: Any = this._state.getAndSet(StateFlowKt.access$getNONE$p());
      if (DebugKt.getASSERTIONS_ENABLED() && var10000 is CancellableContinuationImpl) {
         throw new AssertionError();
      } else {
         return var10000 === StateFlowKt.access$getPENDING$p();
      }
   }

   public suspend fun awaitPending() {
      val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
      `cancellable$iv`.initCancellability();
      val cont: CancellableContinuation = `cancellable$iv`;
      if (DebugKt.getASSERTIONS_ENABLED() && Concurrent_commonKt.getValue(access$get_state$p(this)) is CancellableContinuationImpl) {
         throw new AssertionError();
      } else {
         if (!access$get_state$p(this).compareAndSet(StateFlowKt.access$getNONE$p(), cont)) {
            if (DebugKt.getASSERTIONS_ENABLED() && Concurrent_commonKt.getValue(access$get_state$p(this)) != StateFlowKt.access$getPENDING$p()) {
               throw new AssertionError();
            }

            cont.resumeWith(Result.constructor-impl(Unit.INSTANCE));
         }

         val var10000: Any = `cancellable$iv`.getResult();
         if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(`$completion`);
         }

         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }
   }
}
