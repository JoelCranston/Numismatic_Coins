package io.ktor.client.plugins

import io.ktor.utils.io.KtorDsl
import java.nio.charset.Charset
import java.util.LinkedHashMap
import java.util.LinkedHashSet
import kotlin.jvm.internal.SourceDebugExtension

@KtorDsl
@SourceDebugExtension(["SMAP\nHttpPlainText.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpPlainText.kt\nio/ktor/client/plugins/HttpPlainTextConfig\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,179:1\n1#2:180\n*E\n"])
public class HttpPlainTextConfig {
   internal final val charsets: MutableSet<Charset> = (new LinkedHashSet()) as java.util.Set
   internal final val charsetQuality: MutableMap<Charset, Float> = (new LinkedHashMap()) as java.util.Map
   public final var sendCharset: Charset?
   public final var responseCharsetFallback: Charset = Charsets.UTF_8

   public fun register(charset: Charset, quality: Float? = null) {
      if (quality != null) {
         val var5: Double = quality.floatValue();
         if (!(0.0 <= var5) || !(var5 <= 1.0)) {
            throw new IllegalStateException("Check failed.");
         }
      }

      this.charsets.add(charset);
      if (quality == null) {
         this.charsetQuality.remove(charset);
      } else {
         this.charsetQuality.put(charset, quality);
      }
   }
}
