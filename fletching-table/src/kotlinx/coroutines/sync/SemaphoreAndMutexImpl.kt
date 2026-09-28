package kotlinx.coroutines.sync

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KFunction
import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.AtomicLong
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellableContinuationImpl
import kotlinx.coroutines.CancellableContinuationKt
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.Waiter
import kotlinx.coroutines.internal.ConcurrentLinkedListKt
import kotlinx.coroutines.internal.Segment
import kotlinx.coroutines.internal.SegmentOrClosed
import kotlinx.coroutines.selects.SelectInstance
import kotlinx.coroutines.sync.SemaphoreAndMutexImpl.addAcquireToQueue.createNewSegment.1

@SourceDebugExtension(["SMAP\nSemaphore.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreAndMutexImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 4 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListKt\n+ 5 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreSegment\n*L\n1#1,396:1\n200#1,10:410\n200#1,10:420\n1#2:397\n444#3,12:398\n68#4,3:430\n42#4,8:433\n68#4,3:444\n42#4,8:447\n374#5:441\n374#5:442\n366#5:443\n377#5:455\n366#5:456\n374#5:457\n*S KotlinDebug\n*F\n+ 1 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreAndMutexImpl\n*L\n192#1:410,10\n216#1:420,10\n182#1:398,12\n284#1:430,3\n284#1:433,8\n317#1:444,3\n317#1:447,8\n288#1:441\n294#1:442\n308#1:443\n323#1:455\n329#1:456\n332#1:457\n*E\n"])
internal open class SemaphoreAndMutexImpl(permits: Int, acquiredPermits: Int) {
   private final val permits: Int
   private final val head: AtomicRef<SemaphoreSegment>
   private final val deqIdx: AtomicLong
   private final val tail: AtomicRef<SemaphoreSegment>
   private final val enqIdx: AtomicLong
   private final val _availablePermits: AtomicInt

   public final val availablePermits: Int
      public final get() {
         return Math.max(get_availablePermits$volatile$FU().get(this), 0);
      }


   private final val onCancellationRelease: (Throwable, Unit, CoroutineContext) -> Unit

   init {
      this.permits = permits;
      if (this.permits <= 0) {
         throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had ${this.permits}").toString());
      } else if (0 > acquiredPermits || acquiredPermits > this.permits) {
         throw new IllegalArgumentException(("The number of acquired permits should be in 0..${this.permits}").toString());
      } else {
         val s: SemaphoreSegment = new SemaphoreSegment(0L, null, 2);
         this.head$volatile = s;
         this.tail$volatile = s;
         this._availablePermits$volatile = this.permits - acquiredPermits;
         this.onCancellationRelease = SemaphoreAndMutexImpl::onCancellationRelease$lambda$2;
      }
   }

   public fun tryAcquire(): Boolean {
      while (true) {
         val p: Int = get_availablePermits$volatile$FU().get(this);
         if (p > this.permits) {
            this.coerceAvailablePermitsAtMaximum();
         } else {
            if (p <= 0) {
               return false;
            }

            if (get_availablePermits$volatile$FU().compareAndSet(this, p, p - 1)) {
               return true;
            }
         }
      }
   }

   public suspend fun acquire() {
      if (this.decPermits() > 0) {
         return Unit.INSTANCE;
      } else {
         val var10000: Any = this.acquireSlowPath(`$completion`);
         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }
   }

   private suspend fun acquireSlowPath() {
      val `cancellable$iv`: CancellableContinuationImpl = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(`$completion`));

      try {
         if (!access$addAcquireToQueue(this, `cancellable$iv`)) {
            this.acquire(`cancellable$iv`);
         }
      } catch (var8: java.lang.Throwable) {
         `cancellable$iv`.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
         throw var8;
      }

      val var10000: Any = `cancellable$iv`.getResult();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   protected fun acquire(waiter: CancellableContinuation<Unit>) {
      val `this_$iv`: SemaphoreAndMutexImpl = this;

      do {
         if (`this_$iv`.decPermits() > 0) {
            waiter.resume(Unit.INSTANCE, this.onCancellationRelease);
            break;
         }
      } while (!this.addAcquireToQueue((Waiter)waiter));
   }

   private inline fun <W> acquire(waiter: W, suspend: (W) -> Boolean, onAcquired: (W) -> Unit) {
      do {
         if (this.decPermits() > 0) {
            onAcquired.invoke(waiter);
            return;
         }
      } while (!suspend.invoke(waiter));
   }

   protected fun onAcquireRegFunction(select: SelectInstance<*>, ignoredParam: Any?) {
      val `this_$iv`: SemaphoreAndMutexImpl = this;

      do {
         if (`this_$iv`.decPermits() > 0) {
            select.selectInRegistrationPhase(Unit.INSTANCE);
            break;
         }
      } while (!this.addAcquireToQueue((Waiter)select));
   }

   private fun decPermits(): Int {
      val p: Int;
      do {
         p = get_availablePermits$volatile$FU().getAndDecrement(this);
      } while (p > this.permits);

      return p;
   }

   public fun release() {
      do {
         val p: Int = get_availablePermits$volatile$FU().getAndIncrement(this);
         if (p >= this.permits) {
            this.coerceAvailablePermitsAtMaximum();
            throw new IllegalStateException(("The number of released permits cannot be greater than ${this.permits}").toString());
         }

         if (p >= 0) {
            return;
         }
      } while (!this.tryResumeNextFromQueue());
   }

   private fun coerceAvailablePermitsAtMaximum() {
      val cur: Int;
      do {
         cur = get_availablePermits$volatile$FU().get(this);
      } while (cur > this.permits && !get_availablePermits$volatile$FU().compareAndSet(this, cur, this.permits));
   }

   private fun addAcquireToQueue(waiter: Waiter): Boolean {
      val curTail: SemaphoreSegment = getTail$volatile$FU().get(this) as SemaphoreSegment;
      val enqIdx: Long = getEnqIdx$volatile$FU().getAndIncrement(this);
      val createNewSegment: KFunction = 1.INSTANCE;
      val i: AtomicReferenceFieldUpdater = getTail$volatile$FU();
      val var8: Long = enqIdx / SemaphoreKt.access$getSEGMENT_SIZE$p();

      val `$i$f$get`: Any;
      label65:
      while (true) {
         `$i$f$get` = ConcurrentLinkedListKt.findSegmentInternal(curTail, var8, createNewSegment as (java.lang.Long?, SemaphoreSegment?) -> SemaphoreSegment);
         if (SegmentOrClosed.isClosed-impl(`$i$f$get`)) {
            break;
         }

         val `$i$f$cas`: Segment = SegmentOrClosed.getSegment-impl(`$i$f$get`);

         while (true) {
            val `cur$iv$iv`: Segment = i.get(this) as Segment;
            val var10000: Boolean;
            if (`cur$iv$iv`.id >= `$i$f$cas`.id) {
               var10000 = true;
            } else if (!`$i$f$cas`.tryIncPointers$kotlinx_coroutines_core()) {
               var10000 = false;
            } else {
               if (!i.compareAndSet(this, `cur$iv$iv`, `$i$f$cas`)) {
                  if (`$i$f$cas`.decPointers$kotlinx_coroutines_core()) {
                     `$i$f$cas`.remove();
                  }
                  continue;
               }

               if (`cur$iv$iv`.decPointers$kotlinx_coroutines_core()) {
                  `cur$iv$iv`.remove();
               }

               var10000 = true;
            }

            if (var10000) {
               break label65;
            }
            break;
         }
      }

      val segment: SemaphoreSegment = SegmentOrClosed.getSegment-impl(`$i$f$get`) as SemaphoreSegment;
      val var14: Int = (int)(enqIdx % SemaphoreKt.access$getSEGMENT_SIZE$p());
      if (segment.getAcquirers().compareAndSet(var14, null, waiter)) {
         waiter.invokeOnCancellation(segment, var14);
         return true;
      } else if (segment.getAcquirers().compareAndSet(var14, SemaphoreKt.access$getPERMIT$p(), SemaphoreKt.access$getTAKEN$p())) {
         if (waiter is CancellableContinuation) {
            (waiter as CancellableContinuation).resume(Unit.INSTANCE, this.onCancellationRelease);
         } else {
            if (waiter !is SelectInstance) {
               throw new IllegalStateException(("unexpected: $waiter").toString());
            }

            (waiter as SelectInstance).selectInRegistrationPhase(Unit.INSTANCE);
         }

         return true;
      } else if (DebugKt.getASSERTIONS_ENABLED() && segment.getAcquirers().get(var14) != SemaphoreKt.access$getBROKEN$p()) {
         throw new AssertionError();
      } else {
         return false;
      }
   }

   private fun tryResumeNextFromQueue(): Boolean {
      val curHead: SemaphoreSegment = getHead$volatile$FU().get(this) as SemaphoreSegment;
      val deqIdx: Long = getDeqIdx$volatile$FU().getAndIncrement(this);
      val id: Long = deqIdx / SemaphoreKt.access$getSEGMENT_SIZE$p();
      val createNewSegment: KFunction = kotlinx.coroutines.sync.SemaphoreAndMutexImpl.tryResumeNextFromQueue.createNewSegment.1.INSTANCE;
      val i: AtomicReferenceFieldUpdater = getHead$volatile$FU();

      var cellState: Any;
      label65:
      while (true) {
         cellState = ConcurrentLinkedListKt.findSegmentInternal(curHead, id, createNewSegment as (java.lang.Long?, SemaphoreSegment?) -> SemaphoreSegment);
         if (SegmentOrClosed.isClosed-impl(cellState)) {
            break;
         }

         val `this_$iv`: Segment = SegmentOrClosed.getSegment-impl(cellState);

         while (true) {
            val `expected$iv`: Segment = i.get(this) as Segment;
            val var10000: Boolean;
            if (`expected$iv`.id >= `this_$iv`.id) {
               var10000 = true;
            } else if (!`this_$iv`.tryIncPointers$kotlinx_coroutines_core()) {
               var10000 = false;
            } else {
               if (!i.compareAndSet(this, `expected$iv`, `this_$iv`)) {
                  if (`this_$iv`.decPointers$kotlinx_coroutines_core()) {
                     `this_$iv`.remove();
                  }
                  continue;
               }

               if (`expected$iv`.decPointers$kotlinx_coroutines_core()) {
                  `expected$iv`.remove();
               }

               var10000 = true;
            }

            if (var10000) {
               break label65;
            }
            break;
         }
      }

      val segment: SemaphoreSegment = SegmentOrClosed.getSegment-impl(cellState) as SemaphoreSegment;
      segment.cleanPrev();
      if (segment.id > id) {
         return false;
      } else {
         val var16: Int = (int)(deqIdx % SemaphoreKt.access$getSEGMENT_SIZE$p());
         cellState = segment.getAcquirers().getAndSet(var16, SemaphoreKt.access$getPERMIT$p());
         if (cellState == null) {
            val var18: Int = SemaphoreKt.access$getMAX_SPIN_CYCLES$p();

            for (int var20 = 0; var20 < var18; var20++) {
               if (segment.getAcquirers().get(var16) === SemaphoreKt.access$getTAKEN$p()) {
                  return true;
               }
            }

            return !segment.getAcquirers().compareAndSet(var16, SemaphoreKt.access$getPERMIT$p(), SemaphoreKt.access$getBROKEN$p());
         } else {
            return cellState != SemaphoreKt.access$getCANCELLED$p() && this.tryResumeAcquire(cellState);
         }
      }
   }

   private fun Any.tryResumeAcquire(): Boolean {
      val var10000: Boolean;
      if (`$this$tryResumeAcquire` is CancellableContinuation) {
         val token: Any = (`$this$tryResumeAcquire` as CancellableContinuation).tryResume(Unit.INSTANCE, null, this.onCancellationRelease);
         if (token != null) {
            (`$this$tryResumeAcquire` as CancellableContinuation).completeResume(token);
            var10000 = true;
         } else {
            var10000 = false;
         }
      } else {
         if (`$this$tryResumeAcquire` !is SelectInstance) {
            throw new IllegalStateException(("unexpected: $`$this$tryResumeAcquire`").toString());
         }

         var10000 = (`$this$tryResumeAcquire` as SelectInstance).trySelect(this, Unit.INSTANCE);
      }

      return var10000;
   }

   @JvmStatic
   fun `onCancellationRelease$lambda$2`(`this$0`: SemaphoreAndMutexImpl, var1: java.lang.Throwable, var2: Unit, var3: CoroutineContext): Unit {
      `this$0`.release();
      return Unit.INSTANCE;
   }
}
