package kotlinx.coroutines.flow

import java.util.ArrayList
import java.util.Arrays
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellableContinuationImpl
import kotlinx.coroutines.CancellableContinuationKt
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.internal.AbstractSharedFlow
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt
import kotlinx.coroutines.flow.internal.FusibleFlow

@SourceDebugExtension(["SMAP\nSharedFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedFlow.kt\nkotlinx/coroutines/flow/SharedFlowImpl\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 4 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 AbstractSharedFlow.kt\nkotlinx/coroutines/flow/internal/AbstractSharedFlow\n+ 7 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 8 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,746:1\n29#2:747\n29#2:750\n29#2:769\n29#2:773\n29#2:782\n29#2:793\n29#2:804\n16#3:748\n16#3:751\n16#3:770\n16#3:774\n16#3:783\n16#3:794\n16#3:805\n375#4:749\n1#5:752\n91#6,2:753\n93#6,2:756\n95#6:759\n91#6,2:775\n93#6,2:778\n95#6:781\n91#6,2:797\n93#6,2:800\n95#6:803\n13402#7:755\n13403#7:758\n13402#7:777\n13403#7:780\n13402#7:799\n13403#7:802\n426#8,9:760\n435#8,2:771\n426#8,9:784\n435#8,2:795\n*S KotlinDebug\n*F\n+ 1 SharedFlow.kt\nkotlinx/coroutines/flow/SharedFlowImpl\n*L\n366#1:747\n406#1:750\n500#1:769\n521#1:773\n641#1:782\n676#1:793\n704#1:804\n366#1:748\n406#1:751\n500#1:770\n521#1:774\n641#1:783\n676#1:794\n704#1:805\n388#1:749\n468#1:753,2\n468#1:756,2\n468#1:759\n544#1:775,2\n544#1:778,2\n544#1:781\n691#1:797,2\n691#1:800,2\n691#1:803\n468#1:755\n468#1:758\n544#1:777\n544#1:780\n691#1:799\n691#1:802\n498#1:760,9\n498#1:771,2\n675#1:784,9\n675#1:795,2\n*E\n"])
internal open class SharedFlowImpl<T>(replay: Int, bufferCapacity: Int, onBufferOverflow: BufferOverflow)
   : AbstractSharedFlow<SharedFlowSlot>,
   MutableSharedFlow<T>,
   CancellableFlow<T>,
   FusibleFlow<T> {
   private final val replay: Int
   private final val bufferCapacity: Int
   private final val onBufferOverflow: BufferOverflow
   private final var buffer: Array<Any?>?
   private final var replayIndex: Long
   private final var minCollectorIndex: Long
   private final var bufferSize: Int
   private final var queueSize: Int

   private final val head: Long
      private final get() {
         return Math.min(this.minCollectorIndex, this.replayIndex);
      }


   private final val replaySize: Int
      private final get() {
         return (int)(this.getHead() + this.bufferSize - this.replayIndex);
      }


   private final val totalSize: Int
      private final get() {
         return this.bufferSize + this.queueSize;
      }


   private final val bufferEndIndex: Long
      private final get() {
         return this.getHead() + this.bufferSize;
      }


   private final val queueEndIndex: Long
      private final get() {
         return this.getHead() + this.bufferSize + this.queueSize;
      }


   public open val replayCache: List<Any>
      public open get() {
         label34: {
            synchronized (this){} // $VF: monitorenter 

            label31: {
               try {
                  val replaySize: Int = this.getReplaySize();
                  if (replaySize == 0) {
                     val var9: java.util.List = CollectionsKt.emptyList();
                     break label31;
                  }

                  val result: ArrayList = new ArrayList(replaySize);
                  val var10000: Array<Any> = this.buffer;
                  val buffer: Array<Any> = var10000;

                  for (int i = 0; i < replaySize; i++) {
                     result.add(SharedFlowKt.access$getBufferAt(buffer, this.replayIndex + (long)i));
                  }
               } catch (var10: java.lang.Throwable) {
                  // $VF: monitorexit
               }

               // $VF: monitorexit
            }

            // $VF: monitorexit
         }
      }


   protected final val lastReplayedLocked: Any
      protected final get() {
         val var10000: Array<Any> = this.buffer;
         return (T)SharedFlowKt.access$getBufferAt(var10000, this.replayIndex + (long)this.getReplaySize() - 1L);
      }


   init {
      this.replay = replay;
      this.bufferCapacity = bufferCapacity;
      this.onBufferOverflow = onBufferOverflow;
   }

   public override suspend fun collect(collector: FlowCollector<Any>): Nothing {
      return collect$suspendImpl(this, collector, `$completion`);
   }

   public override fun tryEmit(value: Any): Boolean {
      var var11: Array<Continuation> = AbstractSharedFlowKt.EMPTY_RESUMES;
      var var15: Boolean;
      synchronized (this) {
         if (this.tryEmitLocked((T)value)) {
            var11 = this.findSlotsToResumeLocked(var11);
            var15 = true;
         } else {
            var15 = false;
         }

         var15 = var15;
      }

      for (Continuation cont : var11) {
         if (var14 != null) {
            var14.resumeWith(Result.constructor-impl(Unit.INSTANCE));
         }
      }

      return var15;
   }

   public override suspend fun emit(value: Any) {
      return emit$suspendImpl(this, (T)value, `$completion`);
   }

   private fun tryEmitLocked(value: Any): Boolean {
      if (this.getNCollectors() == 0) {
         return this.tryEmitNoCollectorsLocked((T)value);
      } else {
         if (this.bufferSize >= this.bufferCapacity && this.minCollectorIndex <= this.replayIndex) {
            switch (SharedFlowImpl.WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()]) {
               case 1:
                  return false;
               case 2:
                  return true;
               case 3:
                  break;
               default:
                  throw new NoWhenBranchMatchedException();
            }
         }

         this.enqueueLocked(value);
         val var2: Int = this.bufferSize++;
         if (this.bufferSize > this.bufferCapacity) {
            this.dropOldestLocked();
         }

         if (this.getReplaySize() > this.replay) {
            this.updateBufferLocked(this.replayIndex + 1L, this.minCollectorIndex, this.getBufferEndIndex(), this.getQueueEndIndex());
         }

         return true;
      }
   }

   private fun tryEmitNoCollectorsLocked(value: Any): Boolean {
      if (DebugKt.getASSERTIONS_ENABLED() && this.getNCollectors() != 0) {
         throw new AssertionError();
      } else if (this.replay == 0) {
         return true;
      } else {
         this.enqueueLocked(value);
         val var3: Int = this.bufferSize++;
         if (this.bufferSize > this.replay) {
            this.dropOldestLocked();
         }

         this.minCollectorIndex = this.getHead() + this.bufferSize;
         return true;
      }
   }

   private fun dropOldestLocked() {
      val var10000: Array<Any> = this.buffer;
      SharedFlowKt.access$setBufferAt(var10000, this.getHead(), null);
      this.bufferSize += -1;
      val var4: Long = this.getHead() + 1L;
      if (this.replayIndex < var4) {
         this.replayIndex = var4;
      }

      if (this.minCollectorIndex < var4) {
         this.correctCollectorIndexesOnDropOldest(var4);
      }

      if (DebugKt.getASSERTIONS_ENABLED() && this.getHead() != var4) {
         throw new AssertionError();
      }
   }

   private fun correctCollectorIndexesOnDropOldest(newHead: Long) {
      val `this_$iv`: AbstractSharedFlow = this;
      if (AbstractSharedFlow.access$getNCollectors(this) != 0) {
         if (AbstractSharedFlow.access$getSlots(`this_$iv`) != null) {
            val `$this$forEach$iv$iv`: Any;
            for (Object element$iv$iv : $this$forEach$iv$iv) {
               if (`element$iv$iv` != null) {
                  val slot: SharedFlowSlot = `element$iv$iv` as SharedFlowSlot;
                  if ((`element$iv$iv` as SharedFlowSlot).index >= 0L && (`element$iv$iv` as SharedFlowSlot).index < newHead) {
                     slot.index = newHead;
                  }
               }
            }
         }
      }

      this.minCollectorIndex = newHead;
   }

   private fun enqueueLocked(item: Any?) {
      val curSize: Int = this.getTotalSize();
      SharedFlowKt.access$setBufferAt(
         if (this.buffer == null)
            this.growBuffer(null, 0, 2)
            else
            (if (curSize >= this.buffer.length) this.growBuffer(this.buffer, curSize, this.buffer.length * 2) else this.buffer),
         this.getHead() + (long)curSize,
         item
      );
   }

   private fun growBuffer(curBuffer: Array<Any?>?, curSize: Int, newSize: Int): Array<Any?> {
      if (newSize <= 0) {
         throw new IllegalStateException("Buffer size overflow".toString());
      } else {
         val head: Array<Any> = new Object[newSize];
         this.buffer = head;
         val newBuffer: Array<Any> = head;
         if (curBuffer == null) {
            return head;
         } else {
            val var8: Long = this.getHead();

            for (int i = 0; i < curSize; i++) {
               SharedFlowKt.access$setBufferAt(newBuffer, var8 + (long)var11, SharedFlowKt.access$getBufferAt(curBuffer, var8 + (long)var11));
            }

            return newBuffer;
         }
      }
   }

   private suspend fun emitSuspend(value: Any) {
      val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
      `cancellable$iv`.initCancellability();
      val cont: CancellableContinuation = `cancellable$iv`;
      var var22: Array<Continuation> = AbstractSharedFlowKt.EMPTY_RESUMES;
      var var27: SharedFlowImpl.Emitter;
      synchronized (this) {
         if (access$tryEmitLocked(this, value)) {
            cont.resumeWith(Result.constructor-impl(Unit.INSTANCE));
            var22 = access$findSlotsToResumeLocked(this, var22);
            var27 = null;
         } else {
            val var14: SharedFlowImpl.Emitter = new SharedFlowImpl.Emitter(this, access$getHead(this) + access$getTotalSize(this), value, cont);
            access$enqueueLocked(this, var14);
            access$setQueueSize$p(this, access$getQueueSize$p(this) + 1);
            if (access$getBufferCapacity$p(this) == 0) {
               var22 = access$findSlotsToResumeLocked(this, var22);
            }

            var27 = var14;
         }

         var27 = var27;
      }

      if (var27 != null) {
         CancellableContinuationKt.disposeOnCancellation(cont, var27);
      }

      for (Continuation r : var22) {
         if (var26 != null) {
            var26.resumeWith(Result.constructor-impl(Unit.INSTANCE));
         }
      }

      var27 = (SharedFlowImpl.Emitter)`cancellable$iv`.getResult();
      if (var27 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return if (var27 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var27 else Unit.INSTANCE;
   }

   private fun cancelEmitter(emitter: kotlinx.coroutines.flow.SharedFlowImpl.Emitter) {
      label30: {
         synchronized (this){} // $VF: monitorenter 

         label27: {
            label26: {
               try {
                  if (emitter.index < this.getHead()) {
                     break label27;
                  }

                  val var10000: Array<Any> = this.buffer;
                  if (SharedFlowKt.access$getBufferAt(var10000, emitter.index) != emitter) {
                     break label26;
                  }

                  SharedFlowKt.access$setBufferAt(var10000, emitter.index, SharedFlowKt.NO_VALUE);
                  this.cleanupTailLocked();
               } catch (var8: java.lang.Throwable) {
                  // $VF: monitorexit
               }

               // $VF: monitorexit
            }

            // $VF: monitorexit
         }

         // $VF: monitorexit
      }
   }

   internal fun updateNewCollectorIndexLocked(): Long {
      val index: Long = this.replayIndex;
      if (this.replayIndex < this.minCollectorIndex) {
         this.minCollectorIndex = this.replayIndex;
      }

      return this.replayIndex;
   }

   internal fun updateCollectorIndexLocked(oldIndex: Long): Array<Continuation<Unit>?> {
      if (DebugKt.getASSERTIONS_ENABLED() && oldIndex < this.minCollectorIndex) {
         throw new AssertionError();
      } else if (oldIndex > this.minCollectorIndex) {
         return AbstractSharedFlowKt.EMPTY_RESUMES;
      } else {
         val var20: Long = this.getHead();
         var var32: Long = var20 + this.bufferSize;
         if (this.bufferCapacity == 0 && this.queueSize > 0) {
            var32++;
         }

         val newBufferEndIndex: AbstractSharedFlow = this;
         if (AbstractSharedFlow.access$getNCollectors(this) != 0) {
            if (AbstractSharedFlow.access$getSlots(newBufferEndIndex) != null) {
               val maxResumeCount: Any;
               for (Object element$iv$iv : maxResumeCount) {
                  if (newBufferSize1 != null) {
                     val slot: SharedFlowSlot = newBufferSize1 as SharedFlowSlot;
                     if ((newBufferSize1 as SharedFlowSlot).index >= 0L && (newBufferSize1 as SharedFlowSlot).index < var32) {
                        var32 = slot.index;
                     }
                  }
               }
            }
         }

         if (DebugKt.getASSERTIONS_ENABLED() && var32 < this.minCollectorIndex) {
            throw new AssertionError();
         } else if (var32 <= this.minCollectorIndex) {
            return AbstractSharedFlowKt.EMPTY_RESUMES;
         } else {
            var var22: Long = this.getBufferEndIndex();
            val var23: Int;
            val var33: Int = var23 = if (this.getNCollectors() > 0) Math.min(this.queueSize, this.bufferCapacity - (int)(var22 - var32)) else this.queueSize;
            var var25: Array<Continuation> = AbstractSharedFlowKt.EMPTY_RESUMES;
            val var26: Long = var22 + this.queueSize;
            if (var33 > 0) {
               var25 = new Continuation[var33];
               var var27: Int = 0;
               val var34: Array<Any> = this.buffer;
               val newReplayIndex: Array<Any> = var34;

               for (long curEmitterIndex = newBufferEndIndex; curEmitterIndex < newQueueEndIndex; curEmitterIndex++) {
                  val var31: Any = SharedFlowKt.access$getBufferAt(newReplayIndex, var30);
                  if (var31 != SharedFlowKt.NO_VALUE) {
                     var25[var27++] = (var31 as SharedFlowImpl.Emitter).cont;
                     SharedFlowKt.access$setBufferAt(newReplayIndex, var30, SharedFlowKt.NO_VALUE);
                     SharedFlowKt.access$setBufferAt(newReplayIndex, var22, (var31 as SharedFlowImpl.Emitter).value);
                     var22++;
                     if (var27 >= var23) {
                        break;
                     }
                  }
               }
            }

            val var28: Int = (int)(var22 - var20);
            if (this.getNCollectors() == 0) {
               var32 = var22;
            }

            var var29: Long = Math.max(this.replayIndex, var22 - (long)Math.min(this.replay, var28));
            if (this.bufferCapacity == 0 && var29 < var26) {
               val var35: Array<Any> = this.buffer;
               if (SharedFlowKt.access$getBufferAt(var35, var29) == SharedFlowKt.NO_VALUE) {
                  var22++;
                  var29++;
               }
            }

            this.updateBufferLocked(var29, var32, var22, var26);
            this.cleanupTailLocked();
            if (var25.length != 0) {
               var25 = this.findSlotsToResumeLocked(var25);
            }

            return var25;
         }
      }
   }

   private fun updateBufferLocked(newReplayIndex: Long, newMinCollectorIndex: Long, newBufferEndIndex: Long, newQueueEndIndex: Long) {
      val newHead: Long = Math.min(newMinCollectorIndex, newReplayIndex);
      if (DebugKt.getASSERTIONS_ENABLED() && newHead < this.getHead()) {
         throw new AssertionError();
      } else {
         for (long index = this.getHead(); index < newHead; index++) {
            val var10000: Array<Any> = this.buffer;
            SharedFlowKt.access$setBufferAt(var10000, var13, null);
         }

         this.replayIndex = newReplayIndex;
         this.minCollectorIndex = newMinCollectorIndex;
         this.bufferSize = (int)(newBufferEndIndex - newHead);
         this.queueSize = (int)(newQueueEndIndex - newBufferEndIndex);
         if (DebugKt.getASSERTIONS_ENABLED() && this.bufferSize < 0) {
            throw new AssertionError();
         } else if (DebugKt.getASSERTIONS_ENABLED() && this.queueSize < 0) {
            throw new AssertionError();
         } else if (DebugKt.getASSERTIONS_ENABLED() && this.replayIndex > this.getHead() + this.bufferSize) {
            throw new AssertionError();
         }
      }
   }

   private fun cleanupTailLocked() {
      if (this.bufferCapacity != 0 || this.queueSize > 1) {
         val var10000: Array<Any> = this.buffer;
         val buffer: Array<Any> = var10000;

         while (this.queueSize > 0 && SharedFlowKt.access$getBufferAt(buffer, this.getHead() + this.getTotalSize() - 1L) == SharedFlowKt.NO_VALUE) {
            this.queueSize += -1;
            SharedFlowKt.access$setBufferAt(buffer, this.getHead() + (long)this.getTotalSize(), null);
         }
      }
   }

   private fun tryTakeValue(slot: SharedFlowSlot): Any? {
      var var16: Array<Continuation> = AbstractSharedFlowKt.EMPTY_RESUMES;
      var var20: Any;
      synchronized (this) {
         val index: Long = this.tryPeekLocked(slot);
         if (index < 0L) {
            var20 = SharedFlowKt.NO_VALUE;
         } else {
            val oldIndex: Long = slot.index;
            val newValue: Any = this.getPeekedValueLockedAt(index);
            slot.index = index + 1L;
            var16 = this.updateCollectorIndexLocked$kotlinx_coroutines_core(oldIndex);
            var20 = newValue;
         }

         var20 = var20;
      }

      for (Continuation resume : var16) {
         if (var19 != null) {
            var19.resumeWith(Result.constructor-impl(Unit.INSTANCE));
         }
      }

      return var20;
   }

   private fun tryPeekLocked(slot: SharedFlowSlot): Long {
      val index: Long = slot.index;
      if (slot.index < this.getBufferEndIndex()) {
         return index;
      } else if (this.bufferCapacity > 0) {
         return -1L;
      } else if (index > this.getHead()) {
         return -1L;
      } else {
         return if (this.queueSize == 0) -1L else index;
      }
   }

   private fun getPeekedValueLockedAt(index: Long): Any? {
      val var10000: Array<Any> = this.buffer;
      val item: Any = SharedFlowKt.access$getBufferAt(var10000, index);
      return if (item is SharedFlowImpl.Emitter) (item as SharedFlowImpl.Emitter).value else item;
   }

   private suspend fun awaitValue(slot: SharedFlowSlot) {
      val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
      `cancellable$iv`.initCancellability();
      val cont: CancellableContinuation = `cancellable$iv`;
      synchronized (this) {
         if (access$tryPeekLocked(this, slot) < 0L) {
            slot.cont = cont;
            slot.cont = cont;
         } else {
            cont.resumeWith(Result.constructor-impl(Unit.INSTANCE));
         }
      }

      val var10000: Any = `cancellable$iv`.getResult();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   private fun findSlotsToResumeLocked(resumesIn: Array<Continuation<Unit>?>): Array<Continuation<Unit>?> {
      var var17: Any = resumesIn;
      var var18: Int = resumesIn.length;
      val `this_$iv`: AbstractSharedFlow = this;
      if (AbstractSharedFlow.access$getNCollectors(this) != 0) {
         if (AbstractSharedFlow.access$getSlots(`this_$iv`) != null) {
            val `$this$forEach$iv$iv`: Any;
            for (Object element$iv$iv : $this$forEach$iv$iv) {
               if (`element$iv$iv` != null) {
                  val slot: SharedFlowSlot = `element$iv$iv` as SharedFlowSlot;
                  if ((`element$iv$iv` as SharedFlowSlot).cont != null) {
                     val cont: Continuation = slot.cont;
                     if (this.tryPeekLocked(slot) >= 0L) {
                        if (var18 >= ((Object[])var17).length) {
                           val var19: Array<Any> = Arrays.copyOf((Object[])var17, Math.max(2, 2 * ((Object[])var17).length));
                           var17 = var19;
                        }

                        (var17 as Array<Continuation>)[var18++] = cont;
                        slot.cont = null;
                     }
                  }
               }
            }
         }
      }

      return var17 as Array<Continuation<Unit>>;
   }

   protected open fun createSlot(): SharedFlowSlot {
      return new SharedFlowSlot();
   }

   protected open fun createSlotArray(size: Int): Array<SharedFlowSlot?> {
      return new SharedFlowSlot[size];
   }

   public override fun resetReplayCache() {
      synchronized (this) {
         this.updateBufferLocked(this.getBufferEndIndex(), this.minCollectorIndex, this.getBufferEndIndex(), this.getQueueEndIndex());
      }
   }

   public override fun fuse(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow): Flow<Any> {
      return SharedFlowKt.fuseSharedFlow(this, context, capacity, onBufferOverflow);
   }

   private class Emitter(flow: SharedFlowImpl<*>, index: Long, value: Any?, cont: Continuation<Unit>) : DisposableHandle {
      public final val flow: SharedFlowImpl<*>

      public final var index: Long
         private set

      public final val value: Any?
      public final val cont: Continuation<Unit>

      init {
         this.flow = flow;
         this.index = index;
         this.value = value;
         this.cont = cont;
      }

      public override fun dispose() {
         SharedFlowImpl.access$cancelEmitter(this.flow, this);
      }
   }
}
