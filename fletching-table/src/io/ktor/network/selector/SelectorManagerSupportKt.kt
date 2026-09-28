package io.ktor.network.selector

import java.io.IOException

private fun selectableIsClosed(): Nothing {
   throw new IOException("Selectable is already closed");
}

private fun selectableIsInvalid(interestedOps: Int, flag: Int): Nothing {
   throw new IllegalStateException(("Selectable is invalid state: $interestedOps, $flag").toString());
}

@JvmSynthetic
fun `access$selectableIsClosed`(): Void {
   return selectableIsClosed();
}

@JvmSynthetic
fun `access$selectableIsInvalid`(interestedOps: Int, flag: Int): Void {
   return selectableIsInvalid(interestedOps, flag);
}
