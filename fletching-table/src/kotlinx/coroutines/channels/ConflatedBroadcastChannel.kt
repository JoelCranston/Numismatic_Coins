package kotlinx.coroutines.channels

import java.util.concurrent.CancellationException
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.selects.SelectClause2

/** @deprecated */
@Deprecated(message = "ConflatedBroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported", level = DeprecationLevel.ERROR)
@ObsoleteCoroutinesApi
public class ConflatedBroadcastChannel<E> private constructor(broadcast: BroadcastChannelImpl<Any>) : BroadcastChannel<E> {
   private final val broadcast: BroadcastChannelImpl<Any>

   public final val value: Any
      public final get() {
         return this.broadcast.getValue();
      }


   public final val valueOrNull: Any?
      public final get() {
         return this.broadcast.getValueOrNull();
      }


   public open val isClosedForSend: Boolean
   public open val onSend: SelectClause2<Any, SendChannel<Any>>

   init {
      this.broadcast = broadcast;
   }

   public constructor() : this(new BroadcastChannelImpl<>(-1))
   public constructor(value: Any) : this() {
      this.trySend-JP2dKIU((E)value);
   }

   public override fun openSubscription(): ReceiveChannel<Any> {
      return this.broadcast.openSubscription();
   }

   public override fun cancel(cause: CancellationException?) {
      this.broadcast.cancel(cause);
   }

   public override suspend fun send(element: Any) {
      return this.broadcast.send((E)element, `$completion`);
   }

   public override fun trySend(element: Any): ChannelResult<Unit> {
      return this.broadcast.trySend-JP2dKIU((E)element);
   }

   public override fun close(cause: Throwable?): Boolean {
      return this.broadcast.close(cause);
   }

   public override fun invokeOnClose(handler: (Throwable?) -> Unit) {
      this.broadcast.invokeOnClose(handler);
   }

   @Deprecated(message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = []), level = DeprecationLevel.ERROR)
   public override fun offer(element: Any): Boolean {
      return this.broadcast.offer((E)element);
   }
}
