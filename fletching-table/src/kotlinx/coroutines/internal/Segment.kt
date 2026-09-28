package kotlinx.coroutines.internal

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicInt
import kotlinx.coroutines.NotCompleted

@SourceDebugExtension(["SMAP\nConcurrentLinkedList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/Segment\n+ 2 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListKt\n*L\n1#1,265:1\n248#2,4:266\n*S KotlinDebug\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/Segment\n*L\n221#1:266,4\n*E\n"])
internal abstract class Segment<S extends Segment<S>> : ConcurrentLinkedListNode<S>, NotCompleted {
   public final val id: Long
   public abstract val numberOfSlots: Int
   private final val cleanedAndPointers: AtomicInt

   public open val isRemoved: Boolean
      public open get() {
         return getCleanedAndPointers$volatile$FU().get(this) == this.getNumberOfSlots() && !this.isTail();
      }


   open fun Segment(id: Long, prev: S?, pointers: Int) {
      super((S)prev);
      this.id = id;
      this.cleanedAndPointers$volatile = pointers shl 16;
   }

   internal fun tryIncPointers(): Boolean {
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = getCleanedAndPointers$volatile$FU();
      val `delta$iv`: Int = 65536;

      var var10000: Boolean;
      while (true) {
         val `cur$iv`: Int = `handler$atomicfu$iv`.get(this);
         if (`cur$iv` == this.getNumberOfSlots() && !this.isTail()) {
            var10000 = false;
            break;
         }

         if (`handler$atomicfu$iv`.compareAndSet(this, `cur$iv`, `cur$iv` + `delta$iv`)) {
            var10000 = true;
            break;
         }
      }

      return var10000;
   }

   internal fun decPointers(): Boolean {
      return getCleanedAndPointers$volatile$FU().addAndGet(this, -65536) == this.getNumberOfSlots() && !this.isTail();
   }

   public abstract fun onCancellation(index: Int, cause: Throwable?, context: CoroutineContext) {
   }

   public fun onSlotCleaned() {
      if (getCleanedAndPointers$volatile$FU().incrementAndGet(this) == this.getNumberOfSlots()) {
         this.remove();
      }
   }
}
