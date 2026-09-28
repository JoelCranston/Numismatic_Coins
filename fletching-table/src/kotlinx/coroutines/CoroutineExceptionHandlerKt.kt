package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandlerKt.CoroutineExceptionHandler.1
import kotlinx.coroutines.internal.CoroutineExceptionHandlerImpl_commonKt

@InternalCoroutinesApi
public fun handleCoroutineException(context: CoroutineContext, exception: Throwable) {
   val reportException: java.lang.Throwable = if (exception is DispatchException) (exception as DispatchException).getCause() else exception;

   try {
      val var3: CoroutineExceptionHandler = context.get(CoroutineExceptionHandler.Key);
      if (var3 != null) {
         var3.handleException(context, reportException);
         return;
      }
   } catch (var7: java.lang.Throwable) {
      CoroutineExceptionHandlerImpl_commonKt.handleUncaughtCoroutineException(context, handlerException(reportException, var7));
      return;
   }

   CoroutineExceptionHandlerImpl_commonKt.handleUncaughtCoroutineException(context, reportException);
}

internal fun handlerException(originalException: Throwable, thrownException: Throwable): Throwable {
   if (originalException === thrownException) {
      return originalException;
   } else {
      val var2: RuntimeException = new RuntimeException("Exception while trying to handle coroutine exception", thrownException);
      kotlin.ExceptionsKt.addSuppressed(var2, originalException);
      return var2;
   }
}

public inline fun CoroutineExceptionHandler(crossinline handler: (CoroutineContext, Throwable) -> Unit): CoroutineExceptionHandler {
   return new 1(handler, CoroutineExceptionHandler.Key);
}
