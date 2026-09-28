package kotlinx.coroutines.channels

import kotlinx.coroutines.CoroutineScope

public interface ProducerScope<E> : CoroutineScope, SendChannel<E> {
   public val channel: SendChannel<Any>

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      /** @deprecated */
      @Deprecated(message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = []), level = DeprecationLevel.ERROR)
      @JvmStatic
      fun <E> offer(`$this`: ProducerScope<? super E>, element: E): Boolean {
         return SendChannel.DefaultImpls.offer(`$this`, (E)element);
      }
   }
}
