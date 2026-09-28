package kotlinx.coroutines.sync

import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicArray
import kotlinx.coroutines.internal.Segment

@SourceDebugExtension(["SMAP\nSemaphore.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreSegment\n*L\n1#1,396:1\n370#1,2:397\n*S KotlinDebug\n*F\n+ 1 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreSegment\n*L\n383#1:397,2\n*E\n"])
private class SemaphoreSegment(id: Long, prev: SemaphoreSegment?, pointers: Int) : Segment(id, prev, pointers) {
   public final val acquirers: AtomicArray<Any?>

   public open val numberOfSlots: Int
      public open get() {
         return SemaphoreKt.access$getSEGMENT_SIZE$p();
      }


   public inline fun get(index: Int): Any? {
      return this.getAcquirers().get(index);
   }

   public inline fun set(index: Int, value: Any?) {
      this.getAcquirers().set(index, value);
   }

   public inline fun cas(index: Int, expected: Any?, value: Any?): Boolean {
      return this.getAcquirers().compareAndSet(index, expected, value);
   }

   public inline fun getAndSet(index: Int, value: Any?): Any? {
      return this.getAcquirers().getAndSet(index, value);
   }

   public override fun onCancellation(index: Int, cause: Throwable?, context: CoroutineContext) {
      this.getAcquirers().set(index, SemaphoreKt.access$getCANCELLED$p());
      this.onSlotCleaned();
   }

   public override fun toString(): String {
      return "SemaphoreSegment[id=${this.id}, hashCode=${this.hashCode()}]";
   }
}
