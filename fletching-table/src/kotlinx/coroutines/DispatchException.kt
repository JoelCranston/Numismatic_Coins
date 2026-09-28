package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext

internal class DispatchException(cause: Throwable, dispatcher: CoroutineDispatcher, context: CoroutineContext) : Exception(
      "Coroutine dispatcher $dispatcher threw an exception, context = $context", cause
   ) {
   public open val cause: Throwable

   init {
      this.cause = cause;
   }
}
