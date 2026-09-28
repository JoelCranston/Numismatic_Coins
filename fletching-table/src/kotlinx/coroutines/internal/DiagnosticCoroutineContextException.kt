package kotlinx.coroutines.internal

import kotlin.coroutines.CoroutineContext

internal class DiagnosticCoroutineContextException(context: CoroutineContext) : RuntimeException {
   private final val context: CoroutineContext?

   init {
      this.context = context;
   }

   public override fun getLocalizedMessage(): String {
      return java.lang.String.valueOf(this.context);
   }

   public override fun fillInStackTrace(): Throwable {
      this.setStackTrace(new StackTraceElement[0]);
      return this;
   }
}
