package io.ktor.util.internal

public open class LockFreeLinkedListHead : LockFreeLinkedListNode {
   public final val isEmpty: Boolean
      public final get() {
         return this.getNext() === this;
      }


   public override fun remove(): Boolean {
      throw new UnsupportedOperationException();
   }

   public fun describeRemove(): Nothing {
      throw new UnsupportedOperationException();
   }

   internal fun validate() {
      var prev: LockFreeLinkedListNode = this;
      val var10000: Any = this.getNext();
      var cur: LockFreeLinkedListNode = var10000 as LockFreeLinkedListNode;

      while (!(cur == this)) {
         val next: LockFreeLinkedListNode = cur.getNextNode();
         cur.validateNode$ktor_utils(prev, next);
         prev = cur;
         cur = next;
      }

      val var10002: Any = this.getNext();
      this.validateNode$ktor_utils(prev, var10002 as LockFreeLinkedListNode);
   }
}
