package io.ktor.util.internal

@PublishedApi
internal const val UNDECIDED: Int = 0

@PublishedApi
internal const val SUCCESS: Int = 1

@PublishedApi
internal const val FAILURE: Int = 2

@PublishedApi
internal final val CONDITION_FALSE: Any = new Symbol("CONDITION_FALSE")

@PublishedApi
internal final val ALREADY_REMOVED: Any = new Symbol("ALREADY_REMOVED")

@PublishedApi
internal final val LIST_EMPTY: Any = new Symbol("LIST_EMPTY")

private final val REMOVE_PREPARED: Any = new Symbol("REMOVE_PREPARED")
private final val NO_DECISION: Any = new Symbol("NO_DECISION")

@PublishedApi
internal fun Any.unwrap(): LockFreeLinkedListNode {
   return if ((`$this$unwrap` as? Removed) != null && (`$this$unwrap` as? Removed).ref != null)
      (`$this$unwrap` as? Removed).ref
      else
      `$this$unwrap` as LockFreeLinkedListNode;
}

@JvmSynthetic
fun `access$getNO_DECISION$p`(): Any {
   return NO_DECISION;
}

@JvmSynthetic
fun `access$getREMOVE_PREPARED$p`(): Any {
   return REMOVE_PREPARED;
}
