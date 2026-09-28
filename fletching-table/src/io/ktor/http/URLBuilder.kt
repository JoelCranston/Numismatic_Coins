package io.ktor.http

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nURLBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 URLBuilder.kt\nio/ktor/http/URLBuilder\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,372:1\n1563#2:373\n1634#2,3:374\n1563#2:377\n1634#2,3:378\n1563#2:381\n1634#2,3:382\n*S KotlinDebug\n*F\n+ 1 URLBuilder.kt\nio/ktor/http/URLBuilder\n*L\n78#1:373\n78#1:374,3\n81#1:377\n81#1:378,3\n83#1:381\n83#1:382,3\n*E\n"])
public class URLBuilder(protocol: URLProtocol? = null,
   host: String = "",
   port: Int = 0,
   user: String? = null,
   password: String? = null,
   pathSegments: List<String> = CollectionsKt.emptyList(),
   parameters: Parameters = Parameters.Companion.getEmpty(),
   fragment: String = "",
   trailingQuery: Boolean = false
) {
   public final var host: String
   public final var trailingQuery: Boolean

   public final var port: Int
      public final set(value) {
         if (0 > value || value >= 65536) {
            throw new IllegalArgumentException(("Port must be between 0 and 65535, or 0 if not set. Provided: $value").toString());
         } else {
            this.port = value;
         }
      }


   public final var protocolOrNull: URLProtocol?

   public final var protocol: URLProtocol
      public final get() {
         var var10000: URLProtocol = this.protocolOrNull;
         if (this.protocolOrNull == null) {
            var10000 = URLProtocol.Companion.getHTTP();
         }

         return var10000;
      }

      public final set(value) {
         this.protocolOrNull = value;
      }


   public final var encodedUser: String?

   public final var user: String?
      public final get() {
         return if (this.encodedUser != null) CodecsKt.decodeURLPart$default(this.encodedUser, 0, 0, null, 7, null) else null;
      }

      public final set(value) {
         this.encodedUser = if (value != null) CodecsKt.encodeURLParameter$default(value, false, 1, null) else null;
      }


   public final var encodedPassword: String?

   public final var password: String?
      public final get() {
         return if (this.encodedPassword != null) CodecsKt.decodeURLPart$default(this.encodedPassword, 0, 0, null, 7, null) else null;
      }

      public final set(value) {
         this.encodedPassword = if (value != null) CodecsKt.encodeURLParameter$default(value, false, 1, null) else null;
      }


   public final var encodedFragment: String

   public final var fragment: String
      public final get() {
         return CodecsKt.decodeURLQueryComponent$default(this.encodedFragment, 0, 0, false, null, 15, null);
      }

      public final set(value) {
         this.encodedFragment = CodecsKt.encodeURLQueryComponent$default(value, false, false, null, 7, null);
      }


   public final var encodedPathSegments: List<String>

   public final var pathSegments: List<String>
      public final get() {
         val `$this$map$iv`: java.lang.Iterable = this.encodedPathSegments;
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.encodedPathSegments, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$iv`.add(CodecsKt.decodeURLPart$default(`item$iv$iv` as java.lang.String, 0, 0, null, 7, null));
         }

         return `destination$iv$iv` as MutableList<java.lang.String>;
      }

      public final set(value) {
         val `$this$map$iv`: java.lang.Iterable = value;
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(value, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$iv`.add(CodecsKt.encodeURLPathPart(`item$iv$iv` as java.lang.String));
         }

         this.encodedPathSegments = `destination$iv$iv` as MutableList<java.lang.String>;
      }


   public final var encodedParameters: ParametersBuilder
      public final set(value) {
         this.encodedParameters = value;
         this.parameters = new UrlDecodedParametersBuilder(value);
      }


   public final var parameters: ParametersBuilder
      private set

   init {
      this.host = host;
      this.trailingQuery = trailingQuery;
      this.port = port;
      this.protocolOrNull = protocol;
      this.encodedUser = if (user != null) CodecsKt.encodeURLParameter$default(user, false, 1, null) else null;
      this.encodedPassword = if (password != null) CodecsKt.encodeURLParameter$default(password, false, 1, null) else null;
      this.encodedFragment = CodecsKt.encodeURLQueryComponent$default(fragment, false, false, null, 7, null);
      val `$this$map$iv`: java.lang.Iterable = pathSegments;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(pathSegments, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(CodecsKt.encodeURLPathPart(`item$iv$iv` as java.lang.String));
      }

      this.encodedPathSegments = `destination$iv$iv` as MutableList<java.lang.String>;
      this.encodedParameters = UrlDecodedParametersBuilderKt.encodeParameters(parameters);
      this.parameters = new UrlDecodedParametersBuilder(this.encodedParameters);
   }

   public fun buildString(): String {
      this.applyOrigin();
      val var10000: java.lang.String = (URLBuilderKt.access$appendTo(this, new StringBuilder(256)) as StringBuilder).toString();
      return var10000;
   }

   public override fun toString(): String {
      val var10000: java.lang.String = (URLBuilderKt.access$appendTo(this, new StringBuilder(256)) as StringBuilder).toString();
      return var10000;
   }

   public fun build(): Url {
      this.applyOrigin();
      return new Url(
         this.protocolOrNull,
         this.host,
         this.port,
         this.getPathSegments(),
         this.parameters.build(),
         this.getFragment(),
         this.getUser(),
         this.getPassword(),
         this.trailingQuery,
         this.buildString()
      );
   }

   private fun applyOrigin() {
      if (this.host.length() <= 0 && !(this.getProtocol().getName() == "file")) {
         this.host = originUrl.getHost();
         if (this.protocolOrNull == null) {
            this.protocolOrNull = originUrl.getProtocolOrNull();
         }

         if (this.port == 0) {
            this.setPort(originUrl.getSpecifiedPort());
         }
      }
   }

   fun URLBuilder() {
      this(null, null, 0, null, null, null, null, null, false, 511, null);
   }

   public companion object {
      private final val originUrl: Url
      private const val INITIAL_CAPACITY: Int
   }
}
