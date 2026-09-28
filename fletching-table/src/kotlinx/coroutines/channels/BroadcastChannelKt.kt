package kotlinx.coroutines.channels

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.internal.Symbol

private final val NO_ELEMENT: Symbol = new Symbol("NO_ELEMENT")

@Deprecated(message = "BroadcastChannel is deprecated in the favour of SharedFlow and StateFlow, and is no longer supported", level = DeprecationLevel.ERROR)
@ObsoleteCoroutinesApi
public fun <E> BroadcastChannel(capacity: Int): BroadcastChannel<E> {
   var var10000: BroadcastChannel;
   switch (capacity) {
      case -2:
         var10000 = new BroadcastChannelImpl(Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core());
         break;
      case -1:
         var10000 = new ConflatedBroadcastChannel();
         break;
      case 0:
         throw new IllegalArgumentException("Unsupported 0 capacity for BroadcastChannel");
      case Integer.MAX_VALUE:
         throw new IllegalArgumentException("Unsupported UNLIMITED capacity for BroadcastChannel");
      default:
         var10000 = new BroadcastChannelImpl(capacity);
   }

   return var10000;
}

@JvmSynthetic
fun `access$getNO_ELEMENT$p`(): Symbol {
   return NO_ELEMENT;
}
