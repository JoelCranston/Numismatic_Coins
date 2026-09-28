package kotlinx.coroutines.internal

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.DebugStringsKt
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.LockFreeLinkedListNode.toString.1

@InternalCoroutinesApi
@SourceDebugExtension(["SMAP\nLockFreeLinkedList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockFreeLinkedList.kt\nkotlinx/coroutines/internal/LockFreeLinkedListNode\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,288:1\n1#2:289\n*E\n"])
public open class LockFreeLinkedListNode {
   private final val _next: AtomicRef<Any>
   private final val _prev: AtomicRef<LockFreeLinkedListNode>
   private final val _removedRef: AtomicRef<Removed?>

   public open val isRemoved: Boolean
      public open get() {
         return this.getNext() is Removed;
      }


   public final val next: Any
      public final get() {
         return get_next$volatile$FU().get(this);
      }


   public final val nextNode: LockFreeLinkedListNode
      public final get() {
         val it: Any = this.getNext();
         val var10000: Removed = it as? Removed;
         if ((it as? Removed) != null && (it as? Removed).ref != null) {
            return var10000.ref;
         } else {
            return it as LockFreeLinkedListNode;
         }
      }


   public final val prevNode: LockFreeLinkedListNode
      public final get() {
         var var10000: LockFreeLinkedListNode = this.correctPrev();
         if (var10000 == null) {
            var10000 = this.findPrevNonRemoved(get_prev$volatile$FU().get(this) as LockFreeLinkedListNode);
         }

         return var10000;
      }


   private fun removed(): Removed {
      var var10000: Removed = get_removedRef$volatile$FU().get(this) as Removed;
      if (var10000 == null) {
         val var1: Removed = new Removed(this);
         get_removedRef$volatile$FU().set(this, var1);
         var10000 = var1;
      }

      return var10000;
   }

   private tailrec fun findPrevNonRemoved(current: LockFreeLinkedListNode): LockFreeLinkedListNode {
      var var2: LockFreeLinkedListNode = this;

      while (current.isRemoved()) {
         val var4: Any = get_prev$volatile$FU().get(current);
         var2 = var2;
         current = var4 as LockFreeLinkedListNode;
      }

      return current;
   }

   public fun addOneIfEmpty(node: LockFreeLinkedListNode): Boolean {
      get_prev$volatile$FU().set(node, this);
      get_next$volatile$FU().set(node, this);

      do {
         if (this.getNext() != this) {
            return false;
         }
      } while (!get_next$volatile$FU().compareAndSet(this, this, node));

      node.finishAdd(this);
      return true;
   }

   public fun addLast(node: LockFreeLinkedListNode, permissionsBitmask: Int): Boolean {
      while (true) {
         val currentPrev: LockFreeLinkedListNode = this.getPrevNode();
         val var10000: Boolean;
         if (currentPrev is ListClosed) {
            var10000 = ((currentPrev as ListClosed).forbiddenElementsBitmask and permissionsBitmask) == 0 && currentPrev.addLast(node, permissionsBitmask);
         } else {
            if (!currentPrev.addNext(node, this)) {
               continue;
            }

            var10000 = true;
         }

         return var10000;
      }
   }

   public fun close(forbiddenElementsBit: Int) {
      this.addLast(new ListClosed(forbiddenElementsBit), forbiddenElementsBit);
   }

   @PublishedApi
   internal fun addNext(node: LockFreeLinkedListNode, next: LockFreeLinkedListNode): Boolean {
      get_prev$volatile$FU().set(node, this);
      get_next$volatile$FU().set(node, next);
      if (!get_next$volatile$FU().compareAndSet(this, next, node)) {
         return false;
      } else {
         node.finishAdd(next);
         return true;
      }
   }

   public open fun remove(): Boolean {
      return this.removeOrNext() == null;
   }

   @PublishedApi
   internal fun removeOrNext(): LockFreeLinkedListNode? {
      val next: Any;
      do {
         next = this.getNext();
         if (next is Removed) {
            return (next as Removed).ref;
         }

         if (next === this) {
            return next as LockFreeLinkedListNode;
         }
      } while (!get_next$volatile$FU().compareAndSet(this, next, ((LockFreeLinkedListNode)next).removed()));

      (next as LockFreeLinkedListNode).correctPrev();
      return null;
   }

   private fun finishAdd(next: LockFreeLinkedListNode) {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_prev$volatile$FU();

      val nextPrev: LockFreeLinkedListNode;
      do {
         nextPrev = `handler$atomicfu$iv`.get(next) as LockFreeLinkedListNode;
         if (this.getNext() != next) {
            return;
         }
      } while (!get_prev$volatile$FU().compareAndSet(next, nextPrev, this));

      if (this.isRemoved()) {
         next.correctPrev();
      }
   }

   private tailrec fun correctPrev(): LockFreeLinkedListNode? {
      var var1: LockFreeLinkedListNode = this;

      while (true) {
         val oldPrev: LockFreeLinkedListNode = get_prev$volatile$FU().get(var1) as LockFreeLinkedListNode;
         var prev: LockFreeLinkedListNode = oldPrev;
         var last: LockFreeLinkedListNode = null;

         while (true) {
            val prevNext: Any = get_next$volatile$FU().get(prev);
            if (prevNext === var1) {
               if (oldPrev === prev) {
                  return prev;
               }

               if (get_prev$volatile$FU().compareAndSet(var1, oldPrev, prev)) {
                  return prev;
               }

               var1 = var1;
               break;
            }

            if (var1.isRemoved()) {
               return null;
            }

            if (prevNext is Removed) {
               if (last != null) {
                  if (!get_next$volatile$FU().compareAndSet(last, prev, (prevNext as Removed).ref)) {
                     var1 = var1;
                     break;
                  }

                  prev = last;
                  last = null;
               } else {
                  prev = get_prev$volatile$FU().get(prev) as LockFreeLinkedListNode;
               }
            } else {
               last = prev;
               prev = prevNext as LockFreeLinkedListNode;
            }
         }
      }
   }

   internal fun validateNode(prev: LockFreeLinkedListNode, next: LockFreeLinkedListNode) {
      if (DebugKt.getASSERTIONS_ENABLED() && prev != get_prev$volatile$FU().get(this)) {
         throw new AssertionError();
      } else if (DebugKt.getASSERTIONS_ENABLED() && next != get_next$volatile$FU().get(this)) {
         throw new AssertionError();
      }
   }

   public override fun toString(): String {
      return "${new 1(this)}@${DebugStringsKt.getHexAddress(this)}";
   }
}
