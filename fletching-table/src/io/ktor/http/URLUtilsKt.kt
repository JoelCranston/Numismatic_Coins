@file:SourceDebugExtension(["SMAP\nURLUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 URLUtils.kt\nio/ktor/http/URLUtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,226:1\n1#2:227\n1374#3:228\n1460#3,2:229\n1563#3:231\n1634#3,3:232\n1462#3,3:235\n*S KotlinDebug\n*F\n+ 1 URLUtils.kt\nio/ktor/http/URLUtilsKt\n*L\n171#1:228\n171#1:229,2\n172#1:231\n172#1:232,3\n171#1:235,3\n*E\n"])

package io.ktor.http

import io.ktor.util.StringValuesKt
import java.util.ArrayList
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

public final val fullPath: String
   public final get() {
      val var1: StringBuilder = new StringBuilder();
      appendUrlFullPath(var1, `$this$fullPath`.getEncodedPath(), `$this$fullPath`.getEncodedQuery(), `$this$fullPath`.getTrailingQuery());
      return var1.toString();
   }


public final val hostWithPort: String
   public final get() {
      return "${`$this$hostWithPort`.getHost()}:${`$this$hostWithPort`.getPort()}";
   }


public final val hostWithPortIfSpecified: String
   public final get() {
      val var1: Int = `$this$hostWithPortIfSpecified`.getSpecifiedPort();
      return if (var1 != 0 && var1 != `$this$hostWithPortIfSpecified`.getProtocol().getDefaultPort())
         getHostWithPort(`$this$hostWithPortIfSpecified`)
         else
         `$this$hostWithPortIfSpecified`.getHost();
   }


public final val isAbsolutePath: Boolean
   public final get() {
      return CollectionsKt.firstOrNull(`$this$isAbsolutePath`.getRawSegments()) == "";
   }


public final val isRelativePath: Boolean
   public final get() {
      return !isAbsolutePath(`$this$isRelativePath`);
   }


public final val isAbsolutePath: Boolean
   public final get() {
      return CollectionsKt.firstOrNull(`$this$isAbsolutePath`.getPathSegments()) == "";
   }


public final val isRelativePath: Boolean
   public final get() {
      return !isAbsolutePath(`$this$isRelativePath`);
   }


public fun Url(urlString: String): Url {
   return URLBuilder(urlString).build();
}

public fun Url(builder: URLBuilder): Url {
   return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), builder).build();
}

public fun buildUrl(block: (URLBuilder) -> Unit): Url {
   val var1: URLBuilder = new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null);
   block.invoke(var1);
   return var1.build();
}

public fun parseUrl(urlString: String): Url? {
   var var1: Url;
   try {
      val cause: URLBuilder = URLBuilder(urlString);
      val var10000: URLBuilder = if (cause.getHost().length() > 0) cause else null;
      var1 = if (var10000 != null) var10000.build() else null;
   } catch (var5: URLParserException) {
      var1 = null;
   }

   return var1;
}

public fun URLBuilder(urlString: String): URLBuilder {
   return URLParserKt.takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), urlString);
}

public fun URLBuilder(url: Url): URLBuilder {
   return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), url);
}

public fun URLBuilder(builder: URLBuilder): URLBuilder {
   return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), builder);
}

public fun URLBuilder.takeFrom(url: URLBuilder): URLBuilder {
   `$this$takeFrom`.setProtocolOrNull(url.getProtocolOrNull());
   `$this$takeFrom`.setHost(url.getHost());
   `$this$takeFrom`.setPort(url.getPort());
   `$this$takeFrom`.setEncodedPathSegments(url.getEncodedPathSegments());
   `$this$takeFrom`.setEncodedUser(url.getEncodedUser());
   `$this$takeFrom`.setEncodedPassword(url.getEncodedPassword());
   val var2: ParametersBuilder = ParametersKt.ParametersBuilder$default(0, 1, null);
   StringValuesKt.appendAll(var2, url.getEncodedParameters());
   `$this$takeFrom`.setEncodedParameters(var2);
   `$this$takeFrom`.setEncodedFragment(url.getEncodedFragment());
   `$this$takeFrom`.setTrailingQuery(url.getTrailingQuery());
   return `$this$takeFrom`;
}

public fun URLBuilder.takeFrom(url: Url): URLBuilder {
   `$this$takeFrom`.setProtocolOrNull(url.getProtocolOrNull());
   `$this$takeFrom`.setHost(url.getHost());
   `$this$takeFrom`.setPort(url.getPort());
   URLBuilderKt.setEncodedPath(`$this$takeFrom`, url.getEncodedPath());
   `$this$takeFrom`.setEncodedUser(url.getEncodedUser());
   `$this$takeFrom`.setEncodedPassword(url.getEncodedPassword());
   val var2: ParametersBuilder = ParametersKt.ParametersBuilder$default(0, 1, null);
   var2.appendAll(QueryKt.parseQueryString$default(url.getEncodedQuery(), 0, 0, false, 6, null));
   `$this$takeFrom`.setEncodedParameters(var2);
   `$this$takeFrom`.setEncodedFragment(url.getEncodedFragment());
   `$this$takeFrom`.setTrailingQuery(url.getTrailingQuery());
   return `$this$takeFrom`;
}

internal fun Appendable.appendUrlFullPath(encodedPath: String, encodedQuery: String, trailingQuery: Boolean) {
   if (!StringsKt.isBlank(encodedPath) && !StringsKt.startsWith$default(encodedPath, "/", false, 2, null)) {
      `$this$appendUrlFullPath`.append('/');
   }

   `$this$appendUrlFullPath`.append(encodedPath);
   if (encodedQuery.length() > 0 || trailingQuery) {
      `$this$appendUrlFullPath`.append("?");
   }

   `$this$appendUrlFullPath`.append(encodedQuery);
}

public fun Appendable.appendUrlFullPath(encodedPath: String, encodedQueryParameters: ParametersBuilder, trailingQuery: Boolean) {
   if (!StringsKt.isBlank(encodedPath) && !StringsKt.startsWith$default(encodedPath, "/", false, 2, null)) {
      `$this$appendUrlFullPath`.append('/');
   }

   `$this$appendUrlFullPath`.append(encodedPath);
   if (!encodedQueryParameters.isEmpty() || trailingQuery) {
      `$this$appendUrlFullPath`.append("?");
   }

   val `$this$flatMap$iv`: java.lang.Iterable = encodedQueryParameters.entries();
   val `destination$iv$iv`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : $this$flatMap$iv) {
      val key: java.lang.String = (`element$iv$iv` as Entry).getKey() as java.lang.String;
      val value: java.util.List = (`element$iv$iv` as Entry).getValue() as java.util.List;
      val var10000: java.util.List;
      if (value.isEmpty()) {
         var10000 = CollectionsKt.listOf(TuplesKt.to(key, null));
      } else {
         val `$this$map$iv`: java.lang.Iterable = value;
         val `destination$iv$ivx`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(value, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$ivx`.add(TuplesKt.to(key, `item$iv$iv` as java.lang.String));
         }

         var10000 = `destination$iv$ivx` as java.util.List;
      }

      CollectionsKt.addAll(`destination$iv$iv`, var10000);
   }

   CollectionsKt.joinTo$default(
      `destination$iv$iv` as java.util.List, `$this$appendUrlFullPath`, "&", null, null, 0, null, URLUtilsKt::appendUrlFullPath$lambda$1, 60, null
   );
}

internal fun StringBuilder.appendUserAndPassword(encodedUser: String?, encodedPassword: String?) {
   if (encodedUser != null) {
      `$this$appendUserAndPassword`.append(encodedUser);
      if (encodedPassword != null) {
         `$this$appendUserAndPassword`.append(':');
         `$this$appendUserAndPassword`.append(encodedPassword);
      }

      `$this$appendUserAndPassword`.append("@");
   }
}

fun `appendUrlFullPath$lambda$1`(it: Pair): java.lang.CharSequence {
   val key: java.lang.String = it.getFirst() as java.lang.String;
   return if (it.getSecond() == null) key else "$key=${it.getSecond()}";
}
