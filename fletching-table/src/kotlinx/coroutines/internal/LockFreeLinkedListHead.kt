package kotlinx.coroutines.internal

public open class LockFreeLinkedListHead : LockFreeLinkedListNode {
   public open val isRemoved: Boolean
      public open get() {
         return false;
      }


   public inline fun forEach(block: (LockFreeLinkedListNode) -> Unit) {
      val var10000: Any = this.getNext();

      for (LockFreeLinkedListNode cur = (LockFreeLinkedListNode)var10000; !(cur == this); cur = cur.getNextNode()) {
         block.invoke(cur);
      }
   }

   public fun remove(): Nothing {
      throw new IllegalStateException("head cannot be removed".toString());
   }
}
