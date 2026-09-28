package kotlinx.coroutines

import java.util.concurrent.CancellationException
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Timeout.kt\nkotlinx/coroutines/TimeoutCancellationException\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,191:1\n1#2:192\n*E\n"])
public class TimeoutCancellationException internal constructor(message: String, coroutine: Job?) : CancellationException(message),
   CopyableThrowable<TimeoutCancellationException> {
   internal final val coroutine: Job?

   init {
      this.coroutine = coroutine;
   }

   internal constructor(message: String) : this(message, null)
   public open fun createCopy(): TimeoutCancellationException {
      val var10000: TimeoutCancellationException = new TimeoutCancellationException;
      var var10002: java.lang.String = this.getMessage();
      if (var10002 == null) {
         var10002 = "";
      }

      var10000./* $VF: Unable to resugar constructor */<init>(var10002, this.coroutine);
      var10000.initCause(this);
      return var10000;
   }
}
