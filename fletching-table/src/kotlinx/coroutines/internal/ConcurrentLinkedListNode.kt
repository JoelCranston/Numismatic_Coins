package kotlinx.coroutines.internal

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.DebugKt

@SourceDebugExtension(["SMAP\nConcurrentLinkedList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListNode\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,265:1\n103#1,7:266\n1#2:273\n*S KotlinDebug\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListNode\n*L\n111#1:266,7\n*E\n"])
internal abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {
   private final val _next: AtomicRef<Any?>
   private final val _prev: AtomicRef<Any?>

   private final val nextOrClosed: Any?
      private final get() {
         return get_next$volatile$FU().get(this);
      }


   public final val next: Any?
      public final get() {
         val `it$iv`: Any = access$getNextOrClosed(this);
         return (N)(if (`it$iv` === ConcurrentLinkedListKt.access$getCLOSED$p()) null else `it$iv`);
      }


   public final val isTail: Boolean
      public final get() {
         return this.getNext() == null;
      }


   public final val prev: Any?
      public final get() {
         return (N)get_prev$volatile$FU().get(this);
      }


   public abstract val isRemoved: Boolean

   private final val aliveSegmentLeft: Any?
      private final get() {
         var cur: ConcurrentLinkedListNode = this.getPrev();

         while (cur != null && cur.isRemoved()) {
            cur = get_prev$volatile$FU().get(cur) as ConcurrentLinkedListNode;
         }

         return (N)cur;
      }


   private final val aliveSegmentRight: Any
      private final get() {
         if (DebugKt.getASSERTIONS_ENABLED() && this.isTail()) {
            throw new AssertionError();
         } else {
            var var10000: ConcurrentLinkedListNode = this.getNext();
            var var2: ConcurrentLinkedListNode = var10000;

            while (cur.isRemoved()) {
               var10000 = var2.getNext();
               if (var10000 == null) {
                  return (N)var2;
               }

               var2 = var10000;
            }

            return (N)var2;
         }
      }


   open fun ConcurrentLinkedListNode(prev: N?) {
      this._prev$volatile = prev;
   }

   public inline fun nextOrIfClosed(onClosedAction: () -> Nothing): Any? {
      val it: Any = access$getNextOrClosed(this);
      if (it === ConcurrentLinkedListKt.access$getCLOSED$p()) {
         onClosedAction.invoke();
         throw new KotlinNothingValueException();
      } else {
         return (N)it;
      }
   }

   public fun trySetNext(value: Any): Boolean {
      return get_next$volatile$FU().compareAndSet(this, null, value);
   }

   public fun cleanPrev() {
      get_prev$volatile$FU().set(this, null);
   }

   public fun markAsClosed(): Boolean {
      return get_next$volatile$FU().compareAndSet(this, null, ConcurrentLinkedListKt.access$getCLOSED$p());
   }

   public fun remove() {
      if (DebugKt.getASSERTIONS_ENABLED() && !this.isRemoved() && !this.isTail()) {
         throw new AssertionError();
      } else if (!this.isTail()) {
         val next: ConcurrentLinkedListNode;
         val var8: ConcurrentLinkedListNode;
         do {
            var8 = this.getAliveSegmentLeft();
            next = this.getAliveSegmentRight();
            val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_prev$volatile$FU();

            val var5: Any;
            do {
               var5 = `handler$atomicfu$iv`.get(next);
            } while (!handler$atomicfu$iv.compareAndSet(next, var5, (ConcurrentLinkedListNode)var5 == null ? null : prev));

            if (var8 != null) {
               get_next$volatile$FU().set(var8, next);
            }
         } while (next.isRemoved() && !next.isTail() || prev != null && prev.isRemoved());
      }
   }
}
