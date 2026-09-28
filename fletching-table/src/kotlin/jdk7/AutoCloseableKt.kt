@file:JvmName(name = "AutoCloseableKt")

package kotlin.jdk7

import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jdk7.AutoCloseableKt.AutoCloseable.1
import kotlin.jvm.internal.InlineMarker

@SinceKotlin(version = "2.0")
@InlineOnly
public inline fun AutoCloseable(crossinline closeAction: () -> Unit): AutoCloseable {
   return new 1(closeAction);
}

@SinceKotlin(version = "1.2")
@InlineOnly
public inline fun <T : AutoCloseable?, R> T.use(block: (T) -> R): R {
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

@SinceKotlin(version = "1.2")
@PublishedApi
internal fun AutoCloseable?.closeFinally(cause: Throwable?) {
   if (`$this$closeFinally` != null) {
      if (cause == null) {
         `$this$closeFinally`.close();
      } else {
         try {
            `$this$closeFinally`.close();
         } catch (var3: java.lang.Throwable) {
            ExceptionsKt.addSuppressed(cause, var3);
         }
      }
   }
}

/** @deprecated */
@SinceKotlin(version = "2.0")
@JvmSynthetic
fun `AutoCloseable$annotations`() {
}
