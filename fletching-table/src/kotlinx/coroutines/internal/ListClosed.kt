package kotlinx.coroutines.internal

private class ListClosed(forbiddenElementsBitmask: Int) : LockFreeLinkedListNode {
   public final val forbiddenElementsBitmask: Int

   init {
      this.forbiddenElementsBitmask = forbiddenElementsBitmask;
   }
}
