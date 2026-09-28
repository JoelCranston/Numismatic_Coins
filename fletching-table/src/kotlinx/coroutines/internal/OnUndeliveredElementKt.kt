package kotlinx.coroutines.internal

import kotlin.coroutines.CoroutineContext
import kotlin.jvm.functions.Function1
import kotlinx.coroutines.CoroutineExceptionHandlerKt

internal fun <E> ((E) -> Unit).callUndeliveredElementCatchingException(element: E, undeliveredElementException: UndeliveredElementException? = null): UndeliveredElementException? {
   try {
      `$this$callUndeliveredElementCatchingException`.invoke(element);
   } catch (var4: java.lang.Throwable) {
      if (undeliveredElementException == null || undeliveredElementException.getCause() === var4) {
         return new UndeliveredElementException("Exception in undelivered element handler for $element", var4);
      }

      ExceptionsKt.addSuppressed(undeliveredElementException, var4);
   }

   return undeliveredElementException;
}

@JvmSynthetic
fun `callUndeliveredElementCatchingException$default`(var0: Function1, var1: Any, var2: UndeliveredElementException, var3: Int, var4: Any): UndeliveredElementException {
   if ((var3 and 2) != 0) {
      var2 = null;
   }

   return callUndeliveredElementCatchingException(var0, var1, var2);
}

internal fun <E> ((E) -> Unit).callUndeliveredElement(element: E, context: CoroutineContext) {
   val var10000: UndeliveredElementException = callUndeliveredElementCatchingException(`$this$callUndeliveredElement`, element, null);
   if (var10000 != null) {
      CoroutineExceptionHandlerKt.handleCoroutineException(context, var10000);
   }
}
