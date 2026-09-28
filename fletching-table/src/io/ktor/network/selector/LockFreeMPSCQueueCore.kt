package io.ktor.network.selector

import java.util.concurrent.atomic.AtomicReferenceArray
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nLockFreeMPSCQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockFreeMPSCQueue.kt\nio/ktor/network/selector/LockFreeMPSCQueueCore\n+ 2 LockFreeMPSCQueue.kt\nio/ktor/network/selector/LockFreeMPSCQueueCore$Companion\n+ 3 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,244:1\n235#2,3:245\n235#2,3:254\n235#2,3:259\n235#2,3:264\n235#2,3:274\n477#3,4:248\n468#3,2:252\n468#3,2:257\n468#3,2:262\n499#3,4:268\n155#3,2:272\n1#4:267\n*S KotlinDebug\n*F\n+ 1 LockFreeMPSCQueue.kt\nio/ktor/network/selector/LockFreeMPSCQueueCore\n*L\n74#1:245,3\n89#1:254,3\n135#1:259,3\n158#1:264,3\n189#1:274,3\n77#1:248,4\n87#1:252,2\n133#1:257,2\n157#1:262,2\n175#1:268,4\n181#1:272,2\n*E\n"])
private class LockFreeMPSCQueueCore<E>(capacity: Int) {
   private final val capacity: Int
   private final val mask: Int
   private final val array: AtomicReferenceArray<Any?>

   public final val isEmpty: Boolean
      public final get() {
         val `this_$iv`: LockFreeMPSCQueueCore.Companion = Companion;
         return (int)((this.stateRef and 1073741823L) shr 0) == (int)((this.stateRef and 1152921503533105152L) shr 30);
      }


   init {
      this.capacity = capacity;
      this.mask = this.capacity - 1;
      this.nextRef = null;
      this.stateRef = 0L;
      this.array = new AtomicReferenceArray<>(this.capacity);
      if (this.mask > 1073741823) {
         throw new IllegalStateException("Check failed.");
      } else if ((this.capacity and this.mask) != 0) {
         throw new IllegalStateException("Check failed.");
      }
   }

   public fun close(): Boolean {
      val `$this$update$iv`: LockFreeMPSCQueueCore = this;

      do {
         if ((`$this$update$iv`.stateRef and 2305843009213693952L) != 0L) {
            return true;
         }

         if ((`$this$update$iv`.stateRef and 1152921504606846976L) != 0L) {
            return false;
         }
      } while (!stateRef$FU.compareAndSet($this$update$iv, $this$update$iv.stateRef, $this$update$iv.stateRef | 2305843009213693952L));

      return true;
   }

   public fun addLast(element: Any): Int {
      val `$this$loop$iv`: LockFreeMPSCQueueCore = this;

      val `tail$iv`: Int;
      val tail: Int;
      do {
         if ((`$this$loop$iv`.stateRef and 3458764513820540928L) != 0L) {
            return LockFreeMPSCQueueCore.Companion.access$addFailReason(Companion, `$this$loop$iv`.stateRef);
         }

         val `this_$iv`: LockFreeMPSCQueueCore.Companion = Companion;
         val `head$iv`: Int = (int)((`$this$loop$iv`.stateRef and 1073741823L) shr 0);
         `tail$iv` = (int)((`$this$loop$iv`.stateRef and 1152921503533105152L) shr 30);
         tail = (int)((`$this$loop$iv`.stateRef and 1152921503533105152L) shr 30);
         if ((`tail$iv` + 2 and this.mask) == (`head$iv` and this.mask)) {
            return 1;
         }
      } while (
         !stateRef$FU.compareAndSet(
            this, $this$loop$iv.stateRef, LockFreeMPSCQueueCore.Companion.access$updateTail(Companion, $this$loop$iv.stateRef, tail$iv + 1 & 1073741823)
         )
      );

      this.array.set(`tail$iv` and this.mask, element);
      var cur: LockFreeMPSCQueueCore = this;

      while ((cur.stateRef & 1152921504606846976L) != 0L) {
         val var10000: LockFreeMPSCQueueCore = cur.next().fillPlaceholder(tail, element);
         if (var10000 == null) {
            break;
         }

         cur = var10000;
      }

      return 0;
   }

   private fun fillPlaceholder(index: Int, element: Any): LockFreeMPSCQueueCore<Any>? {
      val old: Any = this.array.get(index and this.mask);
      if (old is LockFreeMPSCQueueCore.Placeholder && (old as LockFreeMPSCQueueCore.Placeholder).index == index) {
         this.array.set(index and this.mask, element);
         return this;
      } else {
         return null;
      }
   }

   public fun removeFirstOrNull(): Any? {
      val state: Long = this.stateRef;
      if ((this.stateRef and 1152921504606846976L) != 0L) {
         return REMOVE_FROZEN;
      } else {
         val `this_$iv`: LockFreeMPSCQueueCore.Companion = Companion;
         val `head$iv`: Int = (int)((this.stateRef and 1073741823L) shr 0);
         val `tail$iv`: Int = (int)((this.stateRef and 1152921503533105152L) shr 30);
         val head: Int = `head$iv`;
         if ((`tail$iv` and this.mask) == (`head$iv` and this.mask)) {
            return null;
         } else {
            var var10000: Any = this.array.get(`head$iv` and this.mask);
            if (var10000 == null) {
               return null;
            } else if (var10000 is LockFreeMPSCQueueCore.Placeholder) {
               return null;
            } else {
               val newHead: Int = `head$iv` + 1 and 1073741823;
               if (stateRef$FU.compareAndSet(this, state, LockFreeMPSCQueueCore.Companion.access$updateHead(Companion, state, `head$iv` + 1 and 1073741823))) {
                  this.array.set(`head$iv` and this.mask, null);
                  return var10000;
               } else {
                  var cur: LockFreeMPSCQueueCore = this;

                  while (true) {
                     var10000 = cur.removeSlowPath(head, newHead);
                     if (var10000 == null) {
                        return var10000;
                     }

                     cur = (LockFreeMPSCQueueCore)var10000;
                  }
               }
            }
         }
      }
   }

   private fun removeSlowPath(oldHead: Int, newHead: Int): LockFreeMPSCQueueCore<Any>? {
      val `$this$loop$iv`: LockFreeMPSCQueueCore = this;

      val `head$iv`: Int;
      do {
         val `this_$iv`: LockFreeMPSCQueueCore.Companion = Companion;
         `head$iv` = (int)((`$this$loop$iv`.stateRef and 1073741823L) shr 0);
         val `tail$iv`: Int = (int)((`$this$loop$iv`.stateRef and 1152921503533105152L) shr 30);
         if (`head$iv` != oldHead) {
            throw new IllegalStateException("This queue can have only one consumer".toString());
         }

         if ((`$this$loop$iv`.stateRef and 1152921504606846976L) != 0L) {
            return this.next();
         }
      } while (
         !stateRef$FU.compareAndSet(this, $this$loop$iv.stateRef, LockFreeMPSCQueueCore.Companion.access$updateHead(Companion, $this$loop$iv.stateRef, newHead))
      );

      this.array.set(`head$iv` and this.mask, null);
      return null;
   }

   public fun next(): LockFreeMPSCQueueCore<Any> {
      return this.allocateOrGetNextCopy(this.markFrozen());
   }

   private fun markFrozen(): Long {
      val `$this$updateAndGet$iv`: LockFreeMPSCQueueCore = this;

      val `upd$iv`: Long;
      do {
         if ((`$this$updateAndGet$iv`.stateRef and 1152921504606846976L) != 0L) {
            return `$this$updateAndGet$iv`.stateRef;
         }

         `upd$iv` = `$this$updateAndGet$iv`.stateRef or 1152921504606846976L;
      } while (!stateRef$FU.compareAndSet($this$updateAndGet$iv, $this$updateAndGet$iv.stateRef, $this$updateAndGet$iv.stateRef | 1152921504606846976L));

      return `upd$iv`;
   }

   private fun allocateOrGetNextCopy(state: Long): LockFreeMPSCQueueCore<Any> {
      val `$this$loop$iv`: LockFreeMPSCQueueCore = this;

      while (true) {
         val next: LockFreeMPSCQueueCore = `$this$loop$iv`.nextRef as LockFreeMPSCQueueCore;
         if (`$this$loop$iv`.nextRef as LockFreeMPSCQueueCore != null) {
            return next;
         }

         nextRef$FU.compareAndSet(this, null, this.allocateNextCopy(state));
      }
   }

   private fun allocateNextCopy(state: Long): LockFreeMPSCQueueCore<Any> {
      val next: LockFreeMPSCQueueCore = new LockFreeMPSCQueueCore(this.capacity * 2);
      val `this_$iv`: LockFreeMPSCQueueCore.Companion = Companion;
      val `head$iv`: Int = (int)((state and 1073741823L) shr 0);
      val tail: Int = (int)((state and 1152921503533105152L) shr 30);

      for (int index = head$iv; (index & this.mask) != (tail & this.mask); index++) {
         val var10000: AtomicReferenceArray = next.array;
         val var10001: Int = index and next.mask;
         var var10002: Any = this.array.get(index and this.mask);
         if (var10002 == null) {
            var10002 = new LockFreeMPSCQueueCore.Placeholder(index);
         }

         var10000.set(var10001, var10002);
      }

      next.stateRef = LockFreeMPSCQueueCore.Companion.access$wo(Companion, state, 1152921504606846976L);
      return next;
   }

   public companion object {
      internal const val INITIAL_CAPACITY: Int
      private const val CAPACITY_BITS: Int
      private const val MAX_CAPACITY_MASK: Int
      private const val HEAD_SHIFT: Int
      private const val HEAD_MASK: Long
      private const val TAIL_SHIFT: Int
      private const val TAIL_MASK: Long
      private const val FROZEN_SHIFT: Int
      private const val FROZEN_MASK: Long
      private const val CLOSED_SHIFT: Int
      private const val CLOSED_MASK: Long
      internal final val REMOVE_FROZEN: Any
      internal const val ADD_SUCCESS: Int
      internal const val ADD_FROZEN: Int
      internal const val ADD_CLOSED: Int

      private infix fun Long.wo(other: Long): Long {
         return `$this$wo` and other.inv();
      }

      private fun Long.updateHead(newHead: Int): Long {
         return this.wo(`$this$updateHead`, 1073741823L) or (long)newHead shl 0;
      }

      private fun Long.updateTail(newTail: Int): Long {
         return this.wo(`$this$updateTail`, 1152921503533105152L) or (long)newTail shl 30;
      }

      private inline fun <T> Long.withState(block: (Int, Int) -> Any): Any {
         return (T)block.invoke((int)((`$this$withState` and 1073741823L) shr 0), (int)((`$this$withState` and 1152921503533105152L) shr 30));
      }

      private fun Long.addFailReason(): Int {
         return if ((`$this$addFailReason` and 2305843009213693952L) != 0L) 2 else 1;
      }
   }

   private class Placeholder(index: Int) {
      public final val index: Int

      init {
         this.index = index;
      }
   }
}
