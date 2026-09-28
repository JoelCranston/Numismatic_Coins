package io.ktor.http

import io.ktor.utils.io.charsets.CharsetJVMKt
import java.nio.charset.Charset
import java.util.Locale

public fun ContentType.withCharset(charset: Charset): ContentType {
   return `$this$withCharset`.withParameter("charset", CharsetJVMKt.getName(charset));
}

public fun ContentType.withCharsetIfNeeded(charset: Charset): ContentType {
   val var10000: java.lang.String = `$this$withCharsetIfNeeded`.getContentType().toLowerCase(Locale.ROOT);
   return if (!(var10000 == "text")) `$this$withCharsetIfNeeded` else `$this$withCharsetIfNeeded`.withParameter("charset", CharsetJVMKt.getName(charset));
}

public fun HeaderValueWithParameters.charset(): Charset? {
   val var10000: java.lang.String = `$this$charset`.parameter("charset");
   val var6: Charset;
   if (var10000 != null) {
      val it: java.lang.String = var10000;

      var var3: Charset;
      try {
         var3 = CharsetJVMKt.forName(Charsets.INSTANCE, it);
      } catch (var5: IllegalArgumentException) {
         var3 = null;
      }

      var6 = var3;
   } else {
      var6 = null;
   }

   return var6;
}
