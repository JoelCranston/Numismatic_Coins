package io.ktor.http.header

import io.ktor.http.HeaderValueParam
import io.ktor.http.HeaderValueWithParameters
import java.util.Locale
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAcceptEncoding.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AcceptEncoding.kt\nio/ktor/http/header/AcceptEncoding\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,132:1\n1761#2,3:133\n*S KotlinDebug\n*F\n+ 1 AcceptEncoding.kt\nio/ktor/http/header/AcceptEncoding\n*L\n102#1:133,3\n*E\n"])
public class AcceptEncoding(acceptEncoding: String, parameters: List<HeaderValueParam> = CollectionsKt.emptyList()) : HeaderValueWithParameters(
      acceptEncoding, parameters
   ) {
   public final val acceptEncoding: String

   init {
      this.acceptEncoding = acceptEncoding;
   }

   public constructor(acceptEncoding: String, qValue: Double) : this(
         acceptEncoding, CollectionsKt.listOf(new HeaderValueParam("q", java.lang.String.valueOf(qValue)))
      )
   public fun withQValue(qValue: Double): AcceptEncoding {
      return if (java.lang.String.valueOf(qValue) == this.parameter("q")) this else new AcceptEncoding(this.acceptEncoding, qValue);
   }

   public fun match(pattern: AcceptEncoding): Boolean {
      if (!(pattern.acceptEncoding == "*") && !StringsKt.equals(pattern.acceptEncoding, this.acceptEncoding, true)) {
         return false;
      } else {
         for (HeaderValueParam var3 : pattern.getParameters()) {
            val patternName: java.lang.String = var3.component1();
            val patternValue: java.lang.String = var3.component2();
            var var10000: Boolean;
            if (patternName == "*") {
               if (patternValue == "*") {
                  var10000 = true;
               } else {
                  val `$this$any$iv`: java.lang.Iterable = this.getParameters();
                  if (`$this$any$iv` is java.util.Collection && (`$this$any$iv` as java.util.Collection).isEmpty()) {
                     var10000 = false;
                  } else {
                     val var10: java.util.Iterator = `$this$any$iv`.iterator();

                     while (true) {
                        if (!var10.hasNext()) {
                           var10000 = false;
                           break;
                        }

                        if (StringsKt.equals((var10.next() as HeaderValueParam).getValue(), patternValue, true)) {
                           var10000 = true;
                           break;
                        }
                     }
                  }
               }
            } else {
               val value: java.lang.String = this.parameter(patternName);
               var10000 = if (patternValue == "*") value != null else StringsKt.equals(value, patternValue, true);
            }

            if (!var10000) {
               return false;
            }
         }

         return true;
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is AcceptEncoding
         && StringsKt.equals(this.acceptEncoding, (other as AcceptEncoding).acceptEncoding, true)
         && this.getParameters() == (other as AcceptEncoding).getParameters();
   }

   public override fun hashCode(): Int {
      val var10000: java.lang.String = this.acceptEncoding.toLowerCase(Locale.ROOT);
      return var10000.hashCode() + 31 * this.getParameters().hashCode();
   }

   public companion object {
      public final val Gzip: AcceptEncoding
      public final val Compress: AcceptEncoding
      public final val Deflate: AcceptEncoding
      public final val Br: AcceptEncoding
      public final val Zstd: AcceptEncoding
      public final val Identity: AcceptEncoding
      public final val All: AcceptEncoding

      public fun mergeAcceptEncodings(vararg encodings: AcceptEncoding): String {
         return ArraysKt.joinToString$default(encodings, ", ", null, null, 0, null, null, 62, null);
      }
   }
}
