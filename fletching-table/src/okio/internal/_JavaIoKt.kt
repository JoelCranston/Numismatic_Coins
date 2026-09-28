package okio.internal

import java.util.logging.Logger

private final val logger: Logger = Logger.getLogger("okio.Okio")

internal final val isAndroidGetsocknameError: Boolean
   internal final get() {
      if (`$this$isAndroidGetsocknameError`.getCause() != null) {
         val var10000: java.lang.String = `$this$isAndroidGetsocknameError`.getMessage();
         if (var10000 != null && StringsKt.contains$default(var10000, "getsockname failed", false, 2, null)) {
            return true;
         }
      }

      return false;
   }


@JvmSynthetic
fun `access$getLogger$p`(): Logger {
   return logger;
}
