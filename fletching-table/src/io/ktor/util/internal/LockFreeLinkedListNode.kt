package io.ktor.util.internal

import io.ktor.util.internal.LockFreeLinkedListNode.makeCondAddOp.1
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nLockFreeLinkedList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockFreeLinkedList.kt\nio/ktor/util/internal/LockFreeLinkedListNode\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n*L\n1#1,856:1\n196#1,3:862\n196#1,3:865\n1#2:857\n155#3,2:858\n155#3,2:860\n155#3,2:868\n155#3,2:870\n*S KotlinDebug\n*F\n+ 1 LockFreeLinkedList.kt\nio/ktor/util/internal/LockFreeLinkedListNode\n*L\n266#1:862,3\n300#1:865,3\n205#1:858,2\n217#1:860,2\n660#1:868,2\n678#1:870,2\n*E\n"])
public open class LockFreeLinkedListNode {
   public final val isRemoved: Boolean
      public final get() {
         return this.getNext() is Removed;
      }


   public final val next: Any
      public final get() {
         val `$this$loop$iv`: LockFreeLinkedListNode = this;

         while (true) {
            if (`$this$loop$iv`._next !is OpDescriptor) {
               return `$this$loop$iv`._next;
            }

            (`$this$loop$iv`._next as OpDescriptor).perform(this);
         }
      }


   public final val nextNode: LockFreeLinkedListNode
      public final get() {
         return LockFreeLinkedListKt.unwrap(this.getNext());
      }


   public final val prev: Any
      public final get() {
         val `$this$loop$iv`: LockFreeLinkedListNode = this;

         while (true) {
            val prev: Any = `$this$loop$iv`._prev;
            if (`$this$loop$iv`._prev is Removed) {
               return `$this$loop$iv`._prev;
            }

            if ((prev as LockFreeLinkedListNode).getNext() === this) {
               return prev;
            }

            this.correctPrev(prev as LockFreeLinkedListNode, null);
         }
      }


   public final val prevNode: LockFreeLinkedListNode
      public final get() {
         return LockFreeLinkedListKt.unwrap(this.getPrev());
      }


   private fun removed(): Removed {
      var var10000: Removed = this.removedRef as Removed;
      if (this.removedRef as Removed == null) {
         val var1: Removed = new Removed(this);
         removedRef$FU.lazySet(this, var1);
         var10000 = var1;
      }

      return var10000;
   }

   @PublishedApi
   internal inline fun makeCondAddOp(node: LockFreeLinkedListNode, crossinline condition: () -> Boolean): io.ktor.util.internal.LockFreeLinkedListNode.CondAddOp {
      return new 1(node, condition);
   }

   public fun addOneIfEmpty(node: LockFreeLinkedListNode): Boolean {
      _prev$FU.lazySet(node, this);
      _next$FU.lazySet(node, this);

      do {
         if (this.getNext() != this) {
            return false;
         }
      } while (!_next$FU.compareAndSet(this, this, node));

      node.finishAdd(this);
      return true;
   }

   public fun addLast(node: LockFreeLinkedListNode) {
      val var10000: Any;
      do {
         var10000 = this.getPrev();
      } while (!((LockFreeLinkedListNode)var10000).addNext(node, this));
   }

   public fun <T : LockFreeLinkedListNode> describeAddLast(node: Any): io.ktor.util.internal.LockFreeLinkedListNode.AddLastDesc<Any> {
      return (LockFreeLinkedListNode.AddLastDesc<T>)(new LockFreeLinkedListNode.AddLastDesc<>(this, node));
   }

   public inline fun addLastIf(node: LockFreeLinkedListNode, crossinline condition: () -> Boolean): Boolean {
      val condAdd: LockFreeLinkedListNode.CondAddOp = new 1(node, condition);

      while (true) {
         val var10000: Any = this.getPrev();
         switch (((LockFreeLinkedListNode)var10000).tryCondAddNext(node, this, condAdd)) {
            case 1:
               return true;
            case 2:
               return false;
            default:
         }
      }
   }

   public inline fun addLastIfPrev(node: LockFreeLinkedListNode, predicate: (LockFreeLinkedListNode) -> Boolean): Boolean {
      val prev: LockFreeLinkedListNode;
      do {
         val var10000: Any = this.getPrev();
         prev = var10000 as LockFreeLinkedListNode;
         if (!predicate.invoke(var10000 as LockFreeLinkedListNode) as java.lang.Boolean) {
            return false;
         }
      } while (!prev.addNext(node, this));

      return true;
   }

   public inline fun addLastIfPrevAndIf(node: LockFreeLinkedListNode, predicate: (LockFreeLinkedListNode) -> Boolean, crossinline condition: () -> Boolean): Boolean {
      val condAdd: LockFreeLinkedListNode.CondAddOp = new 1(node, condition);

      while (true) {
         val var10000: Any = this.getPrev();
         val prev: LockFreeLinkedListNode = var10000 as LockFreeLinkedListNode;
         if (!predicate.invoke(var10000 as LockFreeLinkedListNode) as java.lang.Boolean) {
            return false;
         }

         switch (prev.tryCondAddNext(node, this, condAdd)) {
            case 1:
               return true;
            case 2:
               return false;
            default:
         }
      }
   }

   @PublishedApi
   internal fun addNext(node: LockFreeLinkedListNode, next: LockFreeLinkedListNode): Boolean {
      _prev$FU.lazySet(node, this);
      _next$FU.lazySet(node, next);
      if (!_next$FU.compareAndSet(this, next, node)) {
         return false;
      } else {
         node.finishAdd(next);
         return true;
      }
   }

   @PublishedApi
   internal fun tryCondAddNext(node: LockFreeLinkedListNode, next: LockFreeLinkedListNode, condAdd: io.ktor.util.internal.LockFreeLinkedListNode.CondAddOp): Int {
      _prev$FU.lazySet(node, this);
      _next$FU.lazySet(node, next);
      condAdd.oldNext = next;
      if (!_next$FU.compareAndSet(this, next, condAdd)) {
         return 0;
      } else {
         return if (condAdd.perform(this) == null) 1 else 2;
      }
   }

   public open fun remove(): Boolean {
      val next: Any;
      do {
         next = this.getNext();
         if (next is Removed) {
            return false;
         }

         if (next === this) {
            return false;
         }
      } while (!_next$FU.compareAndSet(this, next, ((LockFreeLinkedListNode)next).removed()));

      this.finishRemove(next as LockFreeLinkedListNode);
      return true;
   }

   public fun helpRemove() {
      val var2: Any = this.getNext();
      val var10000: Removed = var2 as? Removed;
      if ((var2 as? Removed) == null) {
         throw new IllegalStateException("Must be invoked on a removed node".toString());
      } else {
         this.finishRemove(var10000.ref);
      }
   }

   public open fun describeRemove(): AtomicDesc? {
      return if (this.isRemoved()) null else new io.ktor.util.internal.LockFreeLinkedListNode.describeRemove.1(this);
   }

   public fun removeFirstOrNull(): LockFreeLinkedListNode? {
      while (true) {
         val var10000: Any = this.getNext();
         val first: LockFreeLinkedListNode = var10000 as LockFreeLinkedListNode;
         if (var10000 as LockFreeLinkedListNode === this) {
            return null;
         }

         if (first.remove()) {
            return first;
         }

         first.helpDelete();
      }
   }

   public fun describeRemoveFirst(): io.ktor.util.internal.LockFreeLinkedListNode.RemoveFirstDesc<LockFreeLinkedListNode> {
      return new LockFreeLinkedListNode.RemoveFirstDesc<>(this);
   }

   private fun finishAdd(next: LockFreeLinkedListNode) {
      val `$this$loop$iv`: LockFreeLinkedListNode = next;

      val nextPrev: Any;
      do {
         nextPrev = `$this$loop$iv`._prev;
         if (`$this$loop$iv`._prev is Removed || this.getNext() != next) {
            return;
         }
      } while (!_prev$FU.compareAndSet(next, nextPrev, this));

      if (this.getNext() is Removed) {
         next.correctPrev(nextPrev as LockFreeLinkedListNode, null);
      }
   }

   private fun finishRemove(next: LockFreeLinkedListNode) {
      this.helpDelete();
      next.correctPrev(LockFreeLinkedListKt.unwrap(this._prev), null);
   }

   private fun markPrev(): LockFreeLinkedListNode {
      val `$this$loop$iv`: LockFreeLinkedListNode = this;

      val prev: Any;
      val var10000: LockFreeLinkedListNode;
      do {
         prev = `$this$loop$iv`._prev;
         if (`$this$loop$iv`._prev is Removed) {
            return (`$this$loop$iv`._prev as Removed).ref;
         }

         if (`$this$loop$iv`._prev === this) {
            var10000 = this.findHead();
         } else {
            var10000 = prev as LockFreeLinkedListNode;
         }
      } while (!_prev$FU.compareAndSet(this, prev, var10000.removed()));

      return prev as LockFreeLinkedListNode;
   }

   private fun findHead(): LockFreeLinkedListNode {
      var cur: LockFreeLinkedListNode = this;

      while (!(cur instanceof LockFreeLinkedListHead)) {
         cur = cur.getNextNode();
         if (cur === this) {
            throw new IllegalStateException("Cannot loop to this while looking for list head".toString());
         }
      }

      return cur;
   }

   @PublishedApi
   internal fun helpDelete() {
      var last: LockFreeLinkedListNode = null;
      var prev: LockFreeLinkedListNode = this.markPrev();
      val var10000: Any = this._next;
      var next: LockFreeLinkedListNode = (var10000 as Removed).ref;

      while (true) {
         val nextNext: Any = next.getNext();
         if (nextNext is Removed) {
            next.markPrev();
            next = (nextNext as Removed).ref;
         } else {
            val prevNext: Any = prev.getNext();
            if (prevNext is Removed) {
               if (last != null) {
                  prev.markPrev();
                  _next$FU.compareAndSet(last, prev, (prevNext as Removed).ref);
                  prev = last;
                  last = null;
               } else {
                  prev = LockFreeLinkedListKt.unwrap(prev._prev);
               }
            } else if (prevNext != this) {
               last = prev;
               prev = prevNext as LockFreeLinkedListNode;
               if (prevNext as LockFreeLinkedListNode === next) {
                  return;
               }
            } else if (_next$FU.compareAndSet(prev, this, next)) {
               return;
            }
         }
      }
   }

   private fun correctPrev(_prev: LockFreeLinkedListNode, op: OpDescriptor?): LockFreeLinkedListNode? {
      var prev: LockFreeLinkedListNode = _prev;
      var last: LockFreeLinkedListNode = null;

      while (true) {
         val prevNext: Any = prev._next;
         if (prev._next === op) {
            return prev;
         }

         if (prev._next is OpDescriptor) {
            (prev._next as OpDescriptor).perform(prev);
         } else if (prev._next is Removed) {
            if (last != null) {
               prev.markPrev();
               _next$FU.compareAndSet(last, prev, (prevNext as Removed).ref);
               prev = last;
               last = null;
            } else {
               prev = LockFreeLinkedListKt.unwrap(prev._prev);
            }
         } else {
            if (this._prev is Removed) {
               return null;
            }

            if (prev._next != this) {
               last = prev;
               prev = prevNext as LockFreeLinkedListNode;
            } else {
               if (this._prev === prev) {
                  return null;
               }

               if (_prev$FU.compareAndSet(this, this._prev, prev) && prev._prev !is Removed) {
                  return null;
               }
            }
         }
      }
   }

   internal fun validateNode(prev: LockFreeLinkedListNode, next: LockFreeLinkedListNode) {
      if (prev != this._prev) {
         throw new IllegalStateException("Check failed.");
      } else if (next != this._next) {
         throw new IllegalStateException("Check failed.");
      }
   }

   public override fun toString(): String {
      return "${(this.getClass()::class).getSimpleName()}@${this.hashCode()}";
   }

   public abstract class AbstractAtomicDesc : AtomicDesc {
      protected abstract val affectedNode: LockFreeLinkedListNode?
      protected abstract val originalNext: LockFreeLinkedListNode?

      protected open fun takeAffectedNode(op: OpDescriptor): LockFreeLinkedListNode {
         val var10000: LockFreeLinkedListNode = this.getAffectedNode();
         return var10000;
      }

      protected open fun failure(affected: LockFreeLinkedListNode, next: Any): Any? {
         return null;
      }

      protected open fun retry(affected: LockFreeLinkedListNode, next: Any): Boolean {
         return false;
      }

      protected abstract fun onPrepare(affected: LockFreeLinkedListNode, next: LockFreeLinkedListNode): Any? {
      }

      protected abstract fun updatedNext(affected: LockFreeLinkedListNode, next: LockFreeLinkedListNode): Any {
      }

      protected abstract fun finishOnSuccess(affected: LockFreeLinkedListNode, next: LockFreeLinkedListNode) {
      }

      public override fun prepare(op: AtomicOp<*>): Any? {
         while (true) {
            val affected: LockFreeLinkedListNode = this.takeAffectedNode(op);
            val next: Any = affected._next;
            if (affected._next === op) {
               return null;
            }

            if (op.isDecided()) {
               return null;
            }

            if (next is OpDescriptor) {
               (next as OpDescriptor).perform(affected);
            } else {
               val failure: Any = this.failure(affected, next);
               if (failure != null) {
                  return failure;
               }

               if (!this.retry(affected, next)) {
                  val prepareOp: LockFreeLinkedListNode.AbstractAtomicDesc.PrepareOp = new LockFreeLinkedListNode.AbstractAtomicDesc.PrepareOp(
                     next as LockFreeLinkedListNode, op, this
                  );
                  if (LockFreeLinkedListNode._next$FU.compareAndSet(affected, next, prepareOp)) {
                     val prepFail: Any = prepareOp.perform(affected);
                     if (prepFail != LockFreeLinkedListKt.access$getREMOVE_PREPARED$p()) {
                        return prepFail;
                     }
                  }
               }
            }
         }
      }

      public override fun complete(op: AtomicOp<*>, failure: Any?) {
         val success: Boolean = failure == null;
         var var10000: LockFreeLinkedListNode = this.getAffectedNode();
         if (var10000 == null) {
            if (success) {
               throw new IllegalStateException("Check failed.");
            }
         } else {
            var10000 = this.getOriginalNext();
            if (var10000 == null) {
               if (success) {
                  throw new IllegalStateException("Check failed.");
               }
            } else {
               if (LockFreeLinkedListNode._next$FU.compareAndSet(var10000, op, (AtomicOp)(if (success) this.updatedNext(var10000, var10000) else var10000))
                  && success) {
                  this.finishOnSuccess(var10000, var10000);
               }
            }
         }
      }

      private class PrepareOp(next: LockFreeLinkedListNode,
            op: AtomicOp<LockFreeLinkedListNode>,
            desc: io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
         )
         : OpDescriptor {
         public final val next: LockFreeLinkedListNode
         public final val op: AtomicOp<LockFreeLinkedListNode>
         public final val desc: io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc

         init {
            this.next = next;
            this.op = op;
            this.desc = desc;
         }

         public override fun perform(affected: Any?): Any? {
            val decision: Any = this.desc.onPrepare(affected as LockFreeLinkedListNode, this.next);
            if (decision != null) {
               if (decision === LockFreeLinkedListKt.access$getREMOVE_PREPARED$p()) {
                  if (LockFreeLinkedListNode._next$FU.compareAndSet(affected as LockFreeLinkedListNode, this, LockFreeLinkedListNode.access$removed(this.next))
                     )
                   {
                     (affected as LockFreeLinkedListNode).helpDelete();
                  }
               } else {
                  this.op.tryDecide(decision);
                  LockFreeLinkedListNode._next$FU.compareAndSet(affected as LockFreeLinkedListNode, this, this.next);
               }

               return decision;
            } else {
               LockFreeLinkedListNode._next$FU
                  .compareAndSet(
                     affected as LockFreeLinkedListNode,
                     this,
                     (LockFreeLinkedListNode.AbstractAtomicDesc.PrepareOp)(if (this.op.isDecided()) this.next else this.op)
                  );
               return null;
            }
         }
      }
   }

   public open class AddLastDesc<T extends LockFreeLinkedListNode>(queue: LockFreeLinkedListNode, node: Any) : LockFreeLinkedListNode.AbstractAtomicDesc {
      public final val queue: LockFreeLinkedListNode
      public final val node: Any

      protected final val affectedNode: LockFreeLinkedListNode?
         protected final get() {
            return this._affectedNode as LockFreeLinkedListNode;
         }


      protected final val originalNext: LockFreeLinkedListNode
         protected final get() {
            return this.queue;
         }


      init {
         this.queue = queue;
         this.node = (T)node;
         if (this.node._next != this.node || this.node._prev != this.node) {
            throw new IllegalStateException("Check failed.");
         } else {
            this._affectedNode = null;
         }
      }

      protected override fun takeAffectedNode(op: OpDescriptor): LockFreeLinkedListNode {
         while (true) {
            val var10000: Any = this.queue._prev;
            val prev: LockFreeLinkedListNode = var10000 as LockFreeLinkedListNode;
            if ((var10000 as LockFreeLinkedListNode)._next === this.queue) {
               return prev;
            }

            if ((var10000 as LockFreeLinkedListNode)._next === op) {
               return prev;
            }

            if ((var10000 as LockFreeLinkedListNode)._next is OpDescriptor) {
               ((var10000 as LockFreeLinkedListNode)._next as OpDescriptor).perform(prev);
            } else {
               val affected: LockFreeLinkedListNode = LockFreeLinkedListNode.access$correctPrev(this.queue, prev, op);
               if (affected != null) {
                  return affected;
               }
            }
         }
      }

      protected override fun retry(affected: LockFreeLinkedListNode, next: Any): Boolean {
         return next != this.queue;
      }

      protected override fun onPrepare(affected: LockFreeLinkedListNode, next: LockFreeLinkedListNode): Any? {
         _affectedNode$FU.compareAndSet(this, null, affected);
         return null;
      }

      protected override fun updatedNext(affected: LockFreeLinkedListNode, next: LockFreeLinkedListNode): Any {
         LockFreeLinkedListNode._prev$FU.compareAndSet(this.node, this.node, (T)affected);
         LockFreeLinkedListNode._next$FU.compareAndSet(this.node, this.node, (T)this.queue);
         return this.node;
      }

      protected override fun finishOnSuccess(affected: LockFreeLinkedListNode, next: LockFreeLinkedListNode) {
         LockFreeLinkedListNode.access$finishAdd(this.node, this.queue);
      }
   }

   @PublishedApi
   internal abstract class CondAddOp : AtomicOp<LockFreeLinkedListNode> {
      public final val newNode: LockFreeLinkedListNode

      public final var oldNext: LockFreeLinkedListNode?
         private set

      open fun CondAddOp(newNode: LockFreeLinkedListNode) {
         this.newNode = newNode;
      }

      public open fun complete(affected: LockFreeLinkedListNode, failure: Any?) {
         if ((if (failure == null) this.newNode else this.oldNext) != null
            && LockFreeLinkedListNode._next$FU.compareAndSet(affected, this, if (failure == null) this.newNode else this.oldNext)
            && failure == null) {
            val var10000: LockFreeLinkedListNode = this.newNode;
            val var10001: LockFreeLinkedListNode = this.oldNext;
            LockFreeLinkedListNode.access$finishAdd(var10000, var10001);
         }
      }
   }

   public open class RemoveFirstDesc<T>(queue: LockFreeLinkedListNode) : LockFreeLinkedListNode.AbstractAtomicDesc {
      public final val queue: LockFreeLinkedListNode

      public final val result: Any
         public final get() {
            val var10000: LockFreeLinkedListNode = this.getAffectedNode();
            return (T)var10000;
         }


      protected final val affectedNode: LockFreeLinkedListNode?
         protected final get() {
            return this._affectedNode as LockFreeLinkedListNode;
         }


      protected final val originalNext: LockFreeLinkedListNode?
         protected final get() {
            return this._originalNext as LockFreeLinkedListNode;
         }


      init {
         this.queue = queue;
         this._affectedNode = null;
         this._originalNext = null;
      }

      protected override fun takeAffectedNode(op: OpDescriptor): LockFreeLinkedListNode {
         val var10000: Any = this.queue.getNext();
         return var10000 as LockFreeLinkedListNode;
      }

      protected override fun failure(affected: LockFreeLinkedListNode, next: Any): Any? {
         return if (affected === this.queue) LockFreeLinkedListKt.getLIST_EMPTY() else null;
      }

      protected open fun validatePrepared(node: Any): Boolean {
         return true;
      }

      protected override fun retry(affected: LockFreeLinkedListNode, next: Any): Boolean {
         if (next !is Removed) {
            return false;
         } else {
            affected.helpDelete();
            return true;
         }
      }

      protected override fun onPrepare(affected: LockFreeLinkedListNode, next: LockFreeLinkedListNode): Any? {
         if (affected is LockFreeLinkedListHead) {
            throw new IllegalStateException("Check failed.");
         } else if (!this.validatePrepared((T)affected)) {
            return LockFreeLinkedListKt.access$getREMOVE_PREPARED$p();
         } else {
            _affectedNode$FU.compareAndSet(this, null, affected);
            _originalNext$FU.compareAndSet(this, null, next);
            return null;
         }
      }

      protected override fun updatedNext(affected: LockFreeLinkedListNode, next: LockFreeLinkedListNode): Any {
         return LockFreeLinkedListNode.access$removed(next);
      }

      protected override fun finishOnSuccess(affected: LockFreeLinkedListNode, next: LockFreeLinkedListNode) {
         LockFreeLinkedListNode.access$finishRemove(affected, next);
      }
   }
}
