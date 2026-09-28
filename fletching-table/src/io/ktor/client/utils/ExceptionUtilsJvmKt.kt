package io.ktor.client.utils

import java.util.concurrent.CancellationException

public fun Throwable.unwrapCancellationException(): Throwable {
   var exception: java.lang.Throwable;
   for (exception = $this$unwrapCancellationException; exception instanceof CancellationException; exception = ((CancellationException)exception).getCause()) {
      if (exception == (exception as CancellationException).getCause()) {
         return `$this$unwrapCancellationException`;
      }
   }

   var var10000: java.lang.Throwable = exception;
   if (exception == null) {
      var10000 = `$this$unwrapCancellationException`;
   }

   return var10000;
}
