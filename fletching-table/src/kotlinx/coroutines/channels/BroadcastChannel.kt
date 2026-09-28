package kotlinx.coroutines.channels

import java.util.concurrent.CancellationException
import kotlinx.coroutines.ObsoleteCoroutinesApi

/** @deprecated */
@Deprecated(message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported", level = DeprecationLevel.ERROR)
@ObsoleteCoroutinesApi
public interface BroadcastChannel<E> : SendChannel<E> {
   public abstract fun openSubscription(): ReceiveChannel<Any> {
   }

   public abstract fun cancel(cause: CancellationException? = ...) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      /** @deprecated */
      @Deprecated(message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = []), level = DeprecationLevel.ERROR)
      @JvmStatic
      fun <E> offer(`$this`: BroadcastChannel<E>, element: E): Boolean {
         return SendChannel.DefaultImpls.offer(`$this`, (E)element);
      }
   }
}
