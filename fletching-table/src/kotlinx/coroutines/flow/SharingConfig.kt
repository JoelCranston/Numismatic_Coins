package kotlinx.coroutines.flow

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.channels.BufferOverflow

private class SharingConfig<T>(upstream: Flow<Any>, extraBufferCapacity: Int, onBufferOverflow: BufferOverflow, context: CoroutineContext) {
   public final val upstream: Flow<Any>
   public final val extraBufferCapacity: Int
   public final val onBufferOverflow: BufferOverflow
   public final val context: CoroutineContext

   init {
      this.upstream = upstream;
      this.extraBufferCapacity = extraBufferCapacity;
      this.onBufferOverflow = onBufferOverflow;
      this.context = context;
   }
}
