package io.ktor.util.debug

import java.lang.management.ManagementFactory

internal object IntellijIdeaDebugDetector {
   public final val isDebuggerConnected: Boolean by LazyKt.lazy(IntellijIdeaDebugDetector::isDebuggerConnected_delegate$lambda$0)
      public final get() {
         return isDebuggerConnected$delegate.getValue() as java.lang.Boolean;
      }


   @JvmStatic
   fun `isDebuggerConnected_delegate$lambda$0`(): Boolean {
      var var0: Boolean;
      try {
         var0 = StringsKt.contains$default(ManagementFactory.getRuntimeMXBean().getInputArguments().toString(), "jdwp", false, 2, null);
      } catch (var2: java.lang.Throwable) {
         var0 = false;
      }

      return var0;
   }
}
