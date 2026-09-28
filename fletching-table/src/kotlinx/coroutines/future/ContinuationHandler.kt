package kotlinx.coroutines.future

import java.util.concurrent.CompletionException
import java.util.function.BiFunction
import kotlin.coroutines.Continuation

private class ContinuationHandler<T>(cont: Continuation<Any>?) : BiFunction<T, java.lang.Throwable, Unit> {
   public final var cont: Continuation<Any>?
      private set

   init {
      this.cont = cont;
   }

   public open fun apply(result: Any?, exception: Throwable?) {
      if (this.cont != null) {
         if (exception == null) {
            this.cont.resumeWith(Result.constructor-impl(result));
         } else {
            var var6: java.lang.Throwable;
            label21: {
               val var10000: CompletionException = exception as? CompletionException;
               if ((exception as? CompletionException) != null) {
                  var6 = var10000.getCause();
                  if (var6 != null) {
                     break label21;
                  }
               }

               var6 = exception;
            }

            this.cont.resumeWith(Result.constructor-impl(ResultKt.createFailure(var6)));
         }
      }
   }
}
