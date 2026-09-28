package io.ktor.http

public class LinkHeader(uri: String, params: List<HeaderValueParam>) : HeaderValueWithParameters("<$uri>", params) {
   public final val uri: String
      public final get() {
         return StringsKt.removeSuffix(StringsKt.removePrefix(this.getContent(), "<"), ">");
      }


   public constructor(uri: String, rel: String) : this(uri, CollectionsKt.listOf(new HeaderValueParam("rel", rel)))
   public constructor(uri: String, vararg rel: String) : this(
         uri, CollectionsKt.listOf(new HeaderValueParam("rel", ArraysKt.joinToString$default(rel, " ", null, null, 0, null, null, 62, null)))
      )
   public constructor(uri: String, rel: List<String>, type: ContentType) : this(
         uri,
         CollectionsKt.listOf(
            new HeaderValueParam[]{
               new HeaderValueParam("rel", CollectionsKt.joinToString$default(rel, " ", null, null, 0, null, null, 62, null)),
               new HeaderValueParam("type", type.toString())
            }
         )
      )
   public object Parameters {
      public const val Rel: String = "rel"
      public const val Anchor: String = "anchor"
      public const val Rev: String = "Rev"
      public const val HrefLang: String = "hreflang"
      public const val Media: String = "media"
      public const val Title: String = "title"
      public const val Type: String = "type"
   }

   public object Rel {
      public const val Stylesheet: String = "stylesheet"
      public const val Prefetch: String = "prefetch"
      public const val DnsPrefetch: String = "dns-prefetch"
      public const val PreConnect: String = "preconnect"
      public const val PreLoad: String = "preload"
      public const val PreRender: String = "prerender"
      public const val Next: String = "next"
   }
}
