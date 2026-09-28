package kotlinx.coroutines.internal

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlinx.atomicfu.AtomicRef

internal open class LockFreeTaskQueue<E>(singleConsumer: Boolean) {
   private final val _cur: AtomicRef<LockFreeTaskQueueCore<Any>>

   public final val isEmpty: Boolean
      public final get() {
         return (get_cur$volatile$FU().get(this) as LockFreeTaskQueueCore).isEmpty();
      }


   public final val size: Int
      public final get() {
         return (get_cur$volatile$FU().get(this) as LockFreeTaskQueueCore).getSize();
      }


   init {
      this._cur$volatile = new LockFreeTaskQueueCore(8, singleConsumer);
   }

   public fun close() {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_cur$volatile$FU();

      while (true) {
         val cur: LockFreeTaskQueueCore = `handler$atomicfu$iv`.get(this) as LockFreeTaskQueueCore;
         if (cur.close()) {
            return;
         }

         get_cur$volatile$FU().compareAndSet(this, cur, cur.next());
      }
   }

   public fun addLast(element: Any): Boolean {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_cur$volatile$FU();

      while (true) {
         val cur: LockFreeTaskQueueCore = `handler$atomicfu$iv`.get(this) as LockFreeTaskQueueCore;
         switch (cur.addLast(element)) {
            case 0:
               return true;
            case 1:
               get_cur$volatile$FU().compareAndSet(this, cur, cur.next());
            default:
               break;
            case 2:
               return false;
         }
      }
   }

   public fun removeFirstOrNull(): Any? {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_cur$volatile$FU();

      while (true) {
         val cur: LockFreeTaskQueueCore = `handler$atomicfu$iv`.get(this) as LockFreeTaskQueueCore;
         val result: Any = cur.removeFirstOrNull();
         if (result != LockFreeTaskQueueCore.REMOVE_FROZEN) {
            return (E)result;
         }

         get_cur$volatile$FU().compareAndSet(this, cur, cur.next());
      }
   }

   public fun <R> map(transform: (Any) -> R): List<R> {
      return (get_cur$volatile$FU().get(this) as LockFreeTaskQueueCore).map(transform);
   }

   public fun isClosed(): Boolean {
      return (get_cur$volatile$FU().get(this) as LockFreeTaskQueueCore).isClosed();
   }
}
