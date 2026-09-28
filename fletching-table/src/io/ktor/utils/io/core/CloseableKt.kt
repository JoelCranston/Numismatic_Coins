package io.ktor.utils.io.core

import java.io.Closeable
import kotlin.jdk7.AutoCloseableKt
import kotlin.jvm.internal.InlineMarker

@Deprecated(message = "Use stdlib implementation instead. Remove import of this function")
public inline fun <T : Closeable?, R> Any.use(block: (Any) -> Any): Any {
   label19: {
      val var3: AutoCloseable = `$this$use`;
      var var4: java.lang.Throwable = null;

      try {
         try {
            val var5: Any = block.invoke(var3);
         } catch (var7: java.lang.Throwable) {
            var4 = var7;
            throw var7;
         }
      } catch (var8: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         AutoCloseableKt.closeFinally(var3, var4);
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      AutoCloseableKt.closeFinally(var3, null);
      InlineMarker.finallyEnd(1);
   }
}
