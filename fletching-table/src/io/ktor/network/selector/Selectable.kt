package io.ktor.network.selector

import java.io.Closeable
import java.nio.channels.SelectableChannel
import kotlinx.coroutines.DisposableHandle

public interface Selectable : Closeable, DisposableHandle {
   public val suspensions: InterestSuspensionsMap
   public val isClosed: Boolean
   public val interestedOps: Int
   public val channel: SelectableChannel

   public abstract fun interestOp(interest: SelectInterest, state: Boolean) {
   }
}
