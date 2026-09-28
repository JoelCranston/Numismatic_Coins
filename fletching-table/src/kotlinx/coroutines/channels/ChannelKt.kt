package kotlinx.coroutines.channels

import kotlin.contracts.InvocationKind
import kotlin.jvm.functions.Function1

public inline fun <T> ChannelResult<T>.getOrElse(onFailure: (Throwable?) -> T): T {
   contract {
      callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
   }

   return (T)(if (`$this$getOrElse_u2dWpGqRn0` is ChannelResult.Failed)
      onFailure.invoke(ChannelResult.exceptionOrNull-impl(`$this$getOrElse_u2dWpGqRn0`))
      else
      `$this$getOrElse_u2dWpGqRn0`);
}

public inline fun <T> ChannelResult<T>.onSuccess(action: (T) -> Unit): ChannelResult<T> {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   if (`$this$onSuccess_u2dWpGqRn0` !is ChannelResult.Failed) {
      action.invoke(`$this$onSuccess_u2dWpGqRn0`);
   }

   return `$this$onSuccess_u2dWpGqRn0`;
}

public inline fun <T> ChannelResult<T>.onFailure(action: (Throwable?) -> Unit): ChannelResult<T> {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   if (`$this$onFailure_u2dWpGqRn0` is ChannelResult.Failed) {
      action.invoke(ChannelResult.exceptionOrNull-impl(`$this$onFailure_u2dWpGqRn0`));
   }

   return `$this$onFailure_u2dWpGqRn0`;
}

public inline fun <T> ChannelResult<T>.onClosed(action: (Throwable?) -> Unit): ChannelResult<T> {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   if (`$this$onClosed_u2dWpGqRn0` is ChannelResult.Closed) {
      action.invoke(ChannelResult.exceptionOrNull-impl(`$this$onClosed_u2dWpGqRn0`));
   }

   return `$this$onClosed_u2dWpGqRn0`;
}

public fun <E> Channel(capacity: Int = 0, onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND, onUndeliveredElement: ((E) -> Unit)? = null): Channel<E> {
   var var10000: Channel;
   switch (capacity) {
      case -2:
         var10000 = if (onBufferOverflow === BufferOverflow.SUSPEND)
            new BufferedChannel(Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core(), onUndeliveredElement)
            else
            new ConflatedBufferedChannel(1, onBufferOverflow, onUndeliveredElement);
         break;
      case -1:
         if (onBufferOverflow != BufferOverflow.SUSPEND) {
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
         }

         var10000 = new ConflatedBufferedChannel(1, BufferOverflow.DROP_OLDEST, onUndeliveredElement);
         break;
      case 0:
         var10000 = if (onBufferOverflow === BufferOverflow.SUSPEND)
            new BufferedChannel(0, onUndeliveredElement)
            else
            new ConflatedBufferedChannel(1, onBufferOverflow, onUndeliveredElement);
         break;
      case Integer.MAX_VALUE:
         var10000 = new BufferedChannel(Integer.MAX_VALUE, onUndeliveredElement);
         break;
      default:
         var10000 = if (onBufferOverflow === BufferOverflow.SUSPEND)
            new BufferedChannel(capacity, onUndeliveredElement)
            else
            new ConflatedBufferedChannel(capacity, onBufferOverflow, onUndeliveredElement);
   }

   return var10000;
}

@JvmSynthetic
fun `Channel$default`(var0: Int, var1: BufferOverflow, var2: Function1, var3: Int, var4: Any): Channel {
   if ((var3 and 1) != 0) {
      var0 = 0;
   }

   if ((var3 and 2) != 0) {
      var1 = BufferOverflow.SUSPEND;
   }

   if ((var3 and 4) != 0) {
      var2 = null;
   }

   return Channel(var0, var1, var2);
}

@Deprecated(message = "Since 1.4.0, binary compatibility with earlier versions", level = DeprecationLevel.HIDDEN)
@JvmSynthetic
public fun <E> Channel(capacity: Int = 0): Channel<E> {
   return Channel$default(capacity, null, null, 6, null);
}

/** @deprecated */
@JvmSynthetic
fun `Channel$default`(var0: Int, var1: Int, var2: Any): Channel {
   if ((var1 and 1) != 0) {
      var0 = 0;
   }

   return Channel(var0);
}
