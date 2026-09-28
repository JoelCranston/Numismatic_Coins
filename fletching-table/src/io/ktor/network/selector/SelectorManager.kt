package io.ktor.network.selector

import java.io.Closeable
import java.nio.channels.spi.SelectorProvider
import kotlinx.coroutines.CoroutineScope

public interface SelectorManager : CoroutineScope, Closeable {
   public val provider: SelectorProvider

   public abstract fun notifyClosed(selectable: Selectable) {
   }

   public abstract suspend fun select(selectable: Selectable, interest: SelectInterest) {
   }

   public companion object
}
