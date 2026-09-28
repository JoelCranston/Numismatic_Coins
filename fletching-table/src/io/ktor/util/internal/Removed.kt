package io.ktor.util.internal

private class Removed(ref: LockFreeLinkedListNode) {
   public final val ref: LockFreeLinkedListNode

   init {
      this.ref = ref;
   }

   public override fun toString(): String {
      return "Removed[${this.ref}]";
   }
}
