package io.ktor.http

import kotlin.jvm.internal.SourceDebugExtension

public class ContentDisposition(disposition: String, parameters: List<HeaderValueParam> = CollectionsKt.emptyList()) : HeaderValueWithParameters(
      disposition, parameters
   ) {
   public final val disposition: String
      public final get() {
         return this.getContent();
      }


   public final val name: String?
      public final get() {
         return this.parameter("name");
      }


   public fun withParameter(key: String, value: String, encodeValue: Boolean = true): ContentDisposition {
      return new ContentDisposition(
         this.getDisposition(),
         CollectionsKt.plus(
            this.getParameters(),
            new HeaderValueParam(key, if (encodeValue) ContentDispositionKt.access$encodeContentDispositionAttribute(key, value) else value)
         )
      );
   }

   public fun withParameters(newParameters: List<HeaderValueParam>): ContentDisposition {
      return new ContentDisposition(this.getDisposition(), CollectionsKt.plus(this.getParameters(), newParameters));
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is ContentDisposition
         && this.getDisposition() == (other as ContentDisposition).getDisposition()
         && this.getParameters() == (other as ContentDisposition).getParameters();
   }

   public override fun hashCode(): Int {
      return this.getDisposition().hashCode() * 31 + this.getParameters().hashCode();
   }

   @SourceDebugExtension(["SMAP\nContentDisposition.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContentDisposition.kt\nio/ktor/http/ContentDisposition$Companion\n+ 2 HeaderValueWithParameters.kt\nio/ktor/http/HeaderValueWithParameters$Companion\n*L\n1#1,125:1\n70#2,2:126\n*S KotlinDebug\n*F\n+ 1 ContentDisposition.kt\nio/ktor/http/ContentDisposition$Companion\n*L\n96#1:126,2\n*E\n"])
   public companion object {
      public final val File: ContentDisposition
      public final val Mixed: ContentDisposition
      public final val Attachment: ContentDisposition
      public final val Inline: ContentDisposition

      public fun parse(value: String): ContentDisposition {
         val `this_$iv`: HeaderValueWithParameters.Companion = HeaderValueWithParameters.Companion;
         val `headerValue$iv`: HeaderValue = CollectionsKt.last(HttpHeaderValueParserKt.parseHeaderValue(value));
         return new ContentDisposition(`headerValue$iv`.getValue(), `headerValue$iv`.getParams());
      }
   }

   public object Parameters {
      public const val FileName: String = "filename"
      public const val FileNameAsterisk: String = "filename*"
      public const val Name: String = "name"
      public const val CreationDate: String = "creation-date"
      public const val ModificationDate: String = "modification-date"
      public const val ReadDate: String = "read-date"
      public const val Size: String = "size"
      public const val Handling: String = "handling"
   }
}
