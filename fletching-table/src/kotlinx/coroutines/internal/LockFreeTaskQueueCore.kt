package kotlinx.coroutines.internal

import java.util.ArrayList
import java.util.concurrent.atomic.AtomicLongFieldUpdater
import java.util.concurrent.atomic.AtomicReferenceArray
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicArray
import kotlinx.atomicfu.AtomicLong
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.DebugKt

@SourceDebugExtension(["SMAP\nLockFreeTaskQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore\n+ 2 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,304:1\n295#2,3:305\n295#2,3:308\n295#2,3:311\n295#2,3:314\n295#2,3:317\n295#2,3:321\n295#2,3:324\n1#3:320\n*S KotlinDebug\n*F\n+ 1 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore\n*L\n87#1:305,3\n88#1:308,3\n103#1:311,3\n163#1:314,3\n196#1:317,3\n227#1:321,3\n243#1:324,3\n*E\n"])
internal class LockFreeTaskQueueCore<E>(capacity: Int, singleConsumer: Boolean) {
   private final val capacity: Int
   private final val singleConsumer: Boolean
   private final val mask: Int
   private final val _next: AtomicRef<LockFreeTaskQueueCore<Any>?>
   private final val _state: AtomicLong
   private final val array: AtomicArray<Any?>

   public final val isEmpty: Boolean
      public final get() {
         val `this_$iv`: LockFreeTaskQueueCore.Companion = Companion;
         val `$this$withState$iv`: Long = get_state$volatile$FU().get(this);
         return (int)((`$this$withState$iv` and 1073741823L) shr 0) == (int)((`$this$withState$iv` and 1152921503533105152L) shr 30);
      }


   public final val size: Int
      public final get() {
         val `this_$iv`: LockFreeTaskQueueCore.Companion = Companion;
         val `$this$withState$iv`: Long = get_state$volatile$FU().get(this);
         return (int)((`$this$withState$iv` and 1152921503533105152L) shr 30) - (int)((`$this$withState$iv` and 1073741823L) shr 0) and 1073741823;
      }


   init {
      this.capacity = capacity;
      this.singleConsumer = singleConsumer;
      this.mask = this.capacity - 1;
      this.array = new AtomicReferenceArray(this.capacity);
      if (this.mask > 1073741823) {
         throw new IllegalStateException("Check failed.");
      } else if ((this.capacity and this.mask) != 0) {
         throw new IllegalStateException("Check failed.");
      }
   }

   public fun close(): Boolean {
      val `handler$atomicfu$iv`: AtomicLongFieldUpdater = get_state$volatile$FU();

      val var3: Long;
      do {
         var3 = `handler$atomicfu$iv`.get(this);
         if ((var3 and 2305843009213693952L) != 0L) {
            return true;
         }

         if ((var3 and 1152921504606846976L) != 0L) {
            return false;
         }
      } while (!handler$atomicfu$iv.compareAndSet(this, var3, var3 | 2305843009213693952L));

      return true;
   }

   public fun addLast(element: Any): Int {
      val `handler$atomicfu$iv`: AtomicLongFieldUpdater = get_state$volatile$FU();

      while (true) {
         val state: Long = `handler$atomicfu$iv`.get(this);
         if ((state and 3458764513820540928L) != 0L) {
            return Companion.addFailReason(state);
         }

         val `this_$iv`: LockFreeTaskQueueCore.Companion = Companion;
         val `head$iv`: Int = (int)((state and 1073741823L) shr 0);
         val `tail$iv`: Int = (int)((state and 1152921503533105152L) shr 30);
         val tail: Int = (int)((state and 1152921503533105152L) shr 30);
         val mask: Int = this.mask;
         if ((`tail$iv` + 2 and this.mask) == (`head$iv` and this.mask)) {
            return 1;
         }

         if (!this.singleConsumer && this.getArray().get(`tail$iv` and mask) != null) {
            if (this.capacity < 1024 || (`tail$iv` - `head$iv` and 1073741823) > this.capacity shr 1) {
               return 1;
            }
         } else if (get_state$volatile$FU().compareAndSet(this, state, Companion.updateTail(state, `tail$iv` + 1 and 1073741823))) {
            this.getArray().set(`tail$iv` and mask, element);
            var cur: LockFreeTaskQueueCore = this;

            while ((get_state$volatile$FU().get(cur) & 1152921504606846976L) != 0L) {
               val var10000: LockFreeTaskQueueCore = cur.next().fillPlaceholder(tail, element);
               if (var10000 == null) {
                  break;
               }

               cur = var10000;
            }

            return 0;
         }
      }
   }

   private fun fillPlaceholder(index: Int, element: Any): LockFreeTaskQueueCore<Any>? {
      val old: Any = this.getArray().get(index and this.mask);
      if (old is LockFreeTaskQueueCore.Placeholder && (old as LockFreeTaskQueueCore.Placeholder).index == index) {
         this.getArray().set(index and this.mask, element);
         return this;
      } else {
         return null;
      }
   }

   public fun removeFirstOrNull(): Any? {
      val `handler$atomicfu$iv`: AtomicLongFieldUpdater = get_state$volatile$FU();

      while (true) {
         val state: Long = `handler$atomicfu$iv`.get(this);
         if ((state and 1152921504606846976L) != 0L) {
            return REMOVE_FROZEN;
         }

         val `this_$iv`: LockFreeTaskQueueCore.Companion = Companion;
         val `head$iv`: Int = (int)((state and 1073741823L) shr 0);
         val `tail$iv`: Int = (int)((state and 1152921503533105152L) shr 30);
         val head: Int = `head$iv`;
         if ((`tail$iv` and this.mask) == (`head$iv` and this.mask)) {
            return null;
         }

         val element: Any = this.getArray().get(`head$iv` and this.mask);
         if (element == null) {
            if (this.singleConsumer) {
               return null;
            }
         } else {
            if (element is LockFreeTaskQueueCore.Placeholder) {
               return null;
            }

            val newHead: Int = `head$iv` + 1 and 1073741823;
            if (get_state$volatile$FU().compareAndSet(this, state, Companion.updateHead(state, `head$iv` + 1 and 1073741823))) {
               this.getArray().set(`head$iv` and this.mask, null);
               return element;
            }

            if (this.singleConsumer) {
               var cur: LockFreeTaskQueueCore = this;

               while (true) {
                  val var10000: LockFreeTaskQueueCore = cur.removeSlowPath(head, newHead);
                  if (var10000 == null) {
                     return element;
                  }

                  cur = var10000;
               }
            }
         }
      }
   }

   private fun removeSlowPath(oldHead: Int, newHead: Int): LockFreeTaskQueueCore<Any>? {
      val `handler$atomicfu$iv`: AtomicLongFieldUpdater = get_state$volatile$FU();

      val state: Long;
      val `head$iv`: Int;
      do {
         state = `handler$atomicfu$iv`.get(this);
         val `this_$iv`: LockFreeTaskQueueCore.Companion = Companion;
         `head$iv` = (int)((state and 1073741823L) shr 0);
         val `tail$iv`: Int = (int)((state and 1152921503533105152L) shr 30);
         if (DebugKt.getASSERTIONS_ENABLED() && `head$iv` != oldHead) {
            throw new AssertionError();
         }

         if ((state and 1152921504606846976L) != 0L) {
            return this.next();
         }
      } while (!get_state$volatile$FU().compareAndSet(this, state, Companion.updateHead(state, newHead)));

      this.getArray().set(`head$iv` and this.mask, null);
      return null;
   }

   public fun next(): LockFreeTaskQueueCore<Any> {
      return this.allocateOrGetNextCopy(this.markFrozen());
   }

   private fun markFrozen(): Long {
      val `handler$atomicfu$iv`: AtomicLongFieldUpdater = get_state$volatile$FU();

      val var3: Long;
      do {
         var3 = `handler$atomicfu$iv`.get(this);
         if ((var3 and 1152921504606846976L) != 0L) {
            return var3;
         }
      } while (!handler$atomicfu$iv.compareAndSet(this, var3, var3 | 1152921504606846976L));

      return var3 or 1152921504606846976L;
   }

   private fun allocateOrGetNextCopy(state: Long): LockFreeTaskQueueCore<Any> {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_next$volatile$FU();

      while (true) {
         val next: LockFreeTaskQueueCore = `handler$atomicfu$iv`.get(this) as LockFreeTaskQueueCore;
         if (next != null) {
            return next;
         }

         get_next$volatile$FU().compareAndSet(this, null, this.allocateNextCopy(state));
      }
   }

   private fun allocateNextCopy(state: Long): LockFreeTaskQueueCore<Any> {
      val next: LockFreeTaskQueueCore = new LockFreeTaskQueueCore(this.capacity * 2, this.singleConsumer);
      val `this_$iv`: LockFreeTaskQueueCore.Companion = Companion;
      val `head$iv`: Int = (int)((state and 1073741823L) shr 0);
      val tail: Int = (int)((state and 1152921503533105152L) shr 30);

      for (int index = head$iv; (index & this.mask) != (tail & this.mask); index++) {
         var var10000: Any = this.getArray().get(index and this.mask);
         if (var10000 == null) {
            var10000 = new LockFreeTaskQueueCore.Placeholder(index);
         }

         next.getArray().set(index and next.mask, var10000);
      }

      get_state$volatile$FU().set(next, Companion.wo(state, 1152921504606846976L));
      return next;
   }

   public fun <R> map(transform: (Any) -> R): List<R> {
      val res: ArrayList = new ArrayList(this.capacity);
      val `this_$iv`: LockFreeTaskQueueCore.Companion = Companion;
      val `$this$withState$iv`: Long = get_state$volatile$FU().get(this);
      val `head$iv`: Int = (int)((`$this$withState$iv` and 1073741823L) shr 0);
      val tail: Int = (int)((`$this$withState$iv` and 1152921503533105152L) shr 30);

      for (int index = head$iv; (index & this.mask) != (tail & this.mask); index++) {
         val element: Any = this.getArray().get(index and this.mask);
         if (element != null && element !is LockFreeTaskQueueCore.Placeholder) {
            res.add(transform.invoke(element));
         }
      }

      return res;
   }

   public fun isClosed(): Boolean {
      return (get_state$volatile$FU().get(this) and 2305843009213693952L) != 0L;
   }

   internal companion object {
      public const val INITIAL_CAPACITY: Int
      public const val CAPACITY_BITS: Int
      public const val MAX_CAPACITY_MASK: Int
      public const val HEAD_SHIFT: Int
      public const val HEAD_MASK: Long
      public const val TAIL_SHIFT: Int
      public const val TAIL_MASK: Long
      public const val FROZEN_SHIFT: Int
      public const val FROZEN_MASK: Long
      public const val CLOSED_SHIFT: Int
      public const val CLOSED_MASK: Long
      public const val MIN_ADD_SPIN_CAPACITY: Int
      public final val REMOVE_FROZEN: Symbol
      public const val ADD_SUCCESS: Int
      public const val ADD_FROZEN: Int
      public const val ADD_CLOSED: Int

      public infix fun Long.wo(other: Long): Long {
         return `$this$wo` and other.inv();
      }

      public fun Long.updateHead(newHead: Int): Long {
         return this.wo(`$this$updateHead`, 1073741823L) or (long)newHead shl 0;
      }

      public fun Long.updateTail(newTail: Int): Long {
         return this.wo(`$this$updateTail`, 1152921503533105152L) or (long)newTail shl 30;
      }

      public inline fun <T> Long.withState(block: (Int, Int) -> T): T {
         return (T)block.invoke((int)((`$this$withState` and 1073741823L) shr 0), (int)((`$this$withState` and 1152921503533105152L) shr 30));
      }

      public fun Long.addFailReason(): Int {
         return if ((`$this$addFailReason` and 2305843009213693952L) != 0L) 2 else 1;
      }
   }

   internal class Placeholder(index: Int) {
      public final val index: Int

      init {
         this.index = index;
      }
   }
}
