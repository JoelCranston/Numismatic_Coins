package kotlinx.coroutines

import kotlinx.coroutines.internal.Symbol

private final val COMPLETING_ALREADY: Symbol = new Symbol("COMPLETING_ALREADY")
internal final val COMPLETING_WAITING_CHILDREN: Symbol = new Symbol("COMPLETING_WAITING_CHILDREN")
private final val COMPLETING_RETRY: Symbol = new Symbol("COMPLETING_RETRY")
private final val TOO_LATE_TO_CANCEL: Symbol = new Symbol("TOO_LATE_TO_CANCEL")
private const val RETRY: Int = -1
private const val FALSE: Int = 0
private const val TRUE: Int = 1
private final val SEALED: Symbol = new Symbol("SEALED")
private final val EMPTY_NEW: Empty = new Empty(false)
private final val EMPTY_ACTIVE: Empty = new Empty(true)
private const val LIST_ON_COMPLETION_PERMISSION: Int = 1
private const val LIST_CHILD_PERMISSION: Int = 2
private const val LIST_CANCELLATION_PERMISSION: Int = 4

internal fun Any?.boxIncomplete(): Any? {
   return if (`$this$boxIncomplete` is Incomplete) new IncompleteStateBox(`$this$boxIncomplete` as Incomplete) else `$this$boxIncomplete`;
}

internal fun Any?.unboxState(): Any? {
   return if ((`$this$unboxState` as? IncompleteStateBox) != null && (`$this$unboxState` as? IncompleteStateBox).state != null)
      (`$this$unboxState` as? IncompleteStateBox).state
      else
      `$this$unboxState`;
}

@JvmSynthetic
fun `access$getEMPTY_ACTIVE$p`(): Empty {
   return EMPTY_ACTIVE;
}

@JvmSynthetic
fun `access$getEMPTY_NEW$p`(): Empty {
   return EMPTY_NEW;
}

@JvmSynthetic
fun `access$getCOMPLETING_ALREADY$p`(): Symbol {
   return COMPLETING_ALREADY;
}

@JvmSynthetic
fun `access$getTOO_LATE_TO_CANCEL$p`(): Symbol {
   return TOO_LATE_TO_CANCEL;
}

@JvmSynthetic
fun `access$getCOMPLETING_RETRY$p`(): Symbol {
   return COMPLETING_RETRY;
}

@JvmSynthetic
fun `access$getSEALED$p`(): Symbol {
   return SEALED;
}
