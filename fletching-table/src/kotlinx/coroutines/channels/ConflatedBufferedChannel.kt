package kotlinx.coroutines.channels

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.internal.OnUndeliveredElementKt
import kotlinx.coroutines.internal.UndeliveredElementException
import kotlinx.coroutines.selects.SelectInstance

@SourceDebugExtension(["SMAP\nConflatedBufferedChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConflatedBufferedChannel.kt\nkotlinx/coroutines/channels/ConflatedBufferedChannel\n+ 2 Channel.kt\nkotlinx/coroutines/channels/ChannelKt\n*L\n1#1,90:1\n1047#2,2:91\n1009#2,2:93\n1009#2,2:95\n1047#2,2:97\n*S KotlinDebug\n*F\n+ 1 ConflatedBufferedChannel.kt\nkotlinx/coroutines/channels/ConflatedBufferedChannel\n*L\n33#1:91,2\n45#1:93,2\n77#1:95,2\n80#1:97,2\n*E\n"])
internal open class ConflatedBufferedChannel<E>(capacity: Int, onBufferOverflow: BufferOverflow, onUndeliveredElement: ((Any) -> Unit)? = null) : BufferedChannel(
      capacity, onUndeliveredElement
   ) {
   private final val capacity: Int
   private final val onBufferOverflow: BufferOverflow

   protected open val isConflatedDropOldest: Boolean
      protected open get() {
         return this.onBufferOverflow === BufferOverflow.DROP_OLDEST;
      }


   init {
      this.capacity = capacity;
      this.onBufferOverflow = onBufferOverflow;
      if (this.onBufferOverflow === BufferOverflow.SUSPEND) {
         throw new IllegalArgumentException(
            ("This implementation does not support suspension for senders, use ${(BufferedChannel::class).getSimpleName()} instead").toString()
         );
      } else if (this.capacity < 1) {
         throw new IllegalArgumentException(("Buffered channel capacity must be at least 1, but ${this.capacity} was specified").toString());
      }
   }

   public override suspend fun send(element: Any) {
      return send$suspendImpl(this, (E)element, `$completion`);
   }

   internal override suspend fun sendBroadcast(element: Any): Boolean {
      return sendBroadcast$suspendImpl(this, (E)element, `$completion`);
   }

   public override fun trySend(element: Any): ChannelResult<Unit> {
      return this.trySendImpl-Mj0NB7M((E)element, false);
   }

   private fun trySendImpl(element: Any, isSendOp: Boolean): ChannelResult<Unit> {
      return if (this.onBufferOverflow === BufferOverflow.DROP_LATEST)
         this.trySendDropLatest-Mj0NB7M((E)element, isSendOp)
         else
         this.trySendDropOldest-JP2dKIU((E)element);
   }

   private fun trySendDropLatest(element: Any, isSendOp: Boolean): ChannelResult<Unit> {
      val result: Any = super.trySend-JP2dKIU((E)element);
      if (!ChannelResult.isSuccess-impl(result) && !ChannelResult.isClosed-impl(result)) {
         if (isSendOp && this.onUndeliveredElement != null) {
            val var5: UndeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(
               this.onUndeliveredElement, element, null, 2, null
            );
            if (var5 != null) {
               throw var5;
            }
         }

         return ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
      } else {
         return result;
      }
   }

   protected override fun registerSelectForSend(select: SelectInstance<*>, element: Any?) {
      val it: Any = this.trySend-JP2dKIU((E)element);
      if (it !is ChannelResult.Failed) {
         val itx: Unit = it as Unit;
         select.selectInRegistrationPhase(Unit.INSTANCE);
      } else if (it is ChannelResult.Closed) {
         val itx: java.lang.Throwable = ChannelResult.exceptionOrNull-impl(it);
         select.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
      } else {
         throw new IllegalStateException("unreachable".toString());
      }
   }

   internal override fun shouldSendSuspend(): Boolean {
      return false;
   }
}
