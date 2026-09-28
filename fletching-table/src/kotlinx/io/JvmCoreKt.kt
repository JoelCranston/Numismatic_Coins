package kotlinx.io

import java.io.InputStream
import java.io.OutputStream

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


public fun OutputStream.asSink(): RawSink {
   return new OutputStreamSink(`$this$asSink`);
}

public fun InputStream.asSource(): RawSource {
   return new InputStreamSource(`$this$asSource`);
}
