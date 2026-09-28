@file:JvmName(name = "CloseableKt")

package kotlin.io

import java.io.Closeable
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.InlineMarker

@InlineOnly
public inline fun <T : Closeable?, R> T.use(block: (T) -> R): R {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   label18: {
      var exception: java.lang.Throwable = null;

      try {
         try {
            val var3: Any = block.invoke(`$this$use`);
         } catch (var5: java.lang.Throwable) {
            exception = var5;
            throw var5;
         }
      } catch (var6: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         closeFinally(`$this$use`, exception);
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      closeFinally(`$this$use`, null);
      InlineMarker.finallyEnd(1);
   }
}

@SinceKotlin(version = "1.1")
@PublishedApi
internal fun Closeable?.closeFinally(cause: Throwable?) {
   if (`$this$closeFinally` != null) {
      if (cause == null) {
         `$this$closeFinally`.close();
      } else {
         try {
            `$this$closeFinally`.close();
         } catch (var3: java.lang.Throwable) {
            kotlin.ExceptionsKt.addSuppressed(cause, var3);
         }
      }
   }
}
