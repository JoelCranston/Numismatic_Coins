package kotlinx.coroutines.channels

import kotlinx.coroutines.CancellableContinuationImpl
import kotlinx.coroutines.Waiter
import kotlinx.coroutines.internal.Segment

private class ReceiveCatching<E>(cont: CancellableContinuationImpl<ChannelResult<Any>>) : Waiter {
   public final val cont: CancellableContinuationImpl<ChannelResult<Any>>

   init {
      this.cont = cont;
   }

   public override fun invokeOnCancellation(segment: Segment<*>, index: Int) {
      this.cont.invokeOnCancellation(segment, index);
   }
}
