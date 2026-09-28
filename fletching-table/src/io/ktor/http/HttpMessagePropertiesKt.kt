@file:SourceDebugExtension(["SMAP\nHttpMessageProperties.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpMessageProperties.kt\nio/ktor/http/HttpMessagePropertiesKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,193:1\n1#2:194\n1374#3:195\n1460#3,2:196\n1563#3:198\n1634#3,3:199\n1462#3,3:202\n1374#3:205\n1460#3,2:206\n1563#3:208\n1634#3,3:209\n1462#3,3:212\n1374#3:215\n1460#3,5:216\n1563#3:221\n1634#3,3:222\n1563#3:225\n1634#3,3:226\n*S KotlinDebug\n*F\n+ 1 HttpMessageProperties.kt\nio/ktor/http/HttpMessagePropertiesKt\n*L\n67#1:195\n67#1:196,2\n68#1:198\n68#1:199,3\n67#1:202,3\n104#1:205\n104#1:206,2\n105#1:208\n105#1:209,3\n104#1:212,3\n121#1:215\n121#1:216,5\n122#1:221\n122#1:222,3\n131#1:225\n131#1:226,3\n*E\n"])

package io.ktor.http

import java.nio.charset.Charset
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

public fun HttpMessageBuilder.contentType(type: ContentType) {
   `$this$contentType`.getHeaders().set(HttpHeaders.INSTANCE.getContentType(), type.toString());
}

public fun HttpMessageBuilder.maxAge(seconds: Int) {
   `$this$maxAge`.getHeaders().append(HttpHeaders.INSTANCE.getCacheControl(), "max-age=$seconds");
}

public fun HttpMessageBuilder.ifNoneMatch(value: String) {
   `$this$ifNoneMatch`.getHeaders().set(HttpHeaders.INSTANCE.getIfNoneMatch(), value);
}

public fun HttpMessageBuilder.userAgent(content: String) {
   `$this$userAgent`.getHeaders().set(HttpHeaders.INSTANCE.getUserAgent(), content);
}

public fun HttpMessageBuilder.contentType(): ContentType? {
   val var10000: java.lang.String = `$this$contentType`.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
   return if (var10000 != null) ContentType.Companion.parse(var10000) else null;
}

public fun HttpMessageBuilder.charset(): Charset? {
   val var10000: ContentType = contentType(`$this$charset`);
   return if (var10000 != null) ContentTypesKt.charset(var10000) else null;
}

public fun HttpMessageBuilder.etag(): String? {
   return `$this$etag`.getHeaders().get(HttpHeaders.INSTANCE.getETag());
}

public fun HttpMessageBuilder.vary(): List<String>? {
   var var10000: java.util.List = `$this$vary`.getHeaders().getAll(HttpHeaders.INSTANCE.getVary());
   if (var10000 != null) {
      val `$this$flatMap$iv`: java.lang.Iterable = var10000;
      val `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$flatMap$iv) {
         val var21: java.lang.Iterable = StringsKt.split$default(`element$iv$iv` as java.lang.String, new java.lang.String[]{","}, false, 0, 6, null);
         val `destination$iv$ivx`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var21, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$ivx`.add(StringsKt.trim(`item$iv$iv` as java.lang.String).toString());
         }

         CollectionsKt.addAll(`destination$iv$iv`, `destination$iv$ivx` as java.util.List);
      }

      var10000 = `destination$iv$iv` as java.util.List;
   } else {
      var10000 = null;
   }

   return var10000;
}

public fun HttpMessageBuilder.contentLength(): Long? {
   val var10000: java.lang.String = `$this$contentLength`.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
   return if (var10000 != null) StringsKt.toLongOrNull(var10000) else null;
}

public fun HttpMessage.contentType(): ContentType? {
   val var10000: java.lang.String = `$this$contentType`.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
   return if (var10000 != null) ContentType.Companion.parse(var10000) else null;
}

public fun HttpMessage.charset(): Charset? {
   val var10000: ContentType = contentType(`$this$charset`);
   return if (var10000 != null) ContentTypesKt.charset(var10000) else null;
}

public fun HttpMessage.etag(): String? {
   return `$this$etag`.getHeaders().get(HttpHeaders.INSTANCE.getETag());
}

public fun HttpMessage.vary(): List<String>? {
   var var10000: java.util.List = `$this$vary`.getHeaders().getAll(HttpHeaders.INSTANCE.getVary());
   if (var10000 != null) {
      val `$this$flatMap$iv`: java.lang.Iterable = var10000;
      val `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$flatMap$iv) {
         val var21: java.lang.Iterable = StringsKt.split$default(`element$iv$iv` as java.lang.String, new java.lang.String[]{","}, false, 0, 6, null);
         val `destination$iv$ivx`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var21, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$ivx`.add(StringsKt.trim(`item$iv$iv` as java.lang.String).toString());
         }

         CollectionsKt.addAll(`destination$iv$iv`, `destination$iv$ivx` as java.util.List);
      }

      var10000 = `destination$iv$iv` as java.util.List;
   } else {
      var10000 = null;
   }

   return var10000;
}

public fun HttpMessage.contentLength(): Long? {
   val var10000: java.lang.String = `$this$contentLength`.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
   return if (var10000 != null) StringsKt.toLongOrNull(var10000) else null;
}

public fun HttpMessage.setCookie(): List<Cookie> {
   val var1: java.util.List = `$this$setCookie`.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie());
   val var10000: java.util.List;
   if (var1 != null) {
      val `$this$flatMap$iv`: java.lang.Iterable = var1;
      val `$this$mapTo$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$flatMap$iv) {
         CollectionsKt.addAll(`$this$mapTo$iv$iv`, splitSetCookieHeader(`element$iv$iv` as java.lang.String));
      }

      val var13: java.lang.Iterable = `$this$mapTo$iv$iv` as java.util.List;
      val `destination$iv$ivx`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$mapTo$iv$iv` as java.util.List, 10));

      for (Object item$iv$iv : var13) {
         `destination$iv$ivx`.add(CookieKt.parseServerSetCookieHeader(var18 as java.lang.String));
      }

      var10000 = `destination$iv$ivx` as java.util.List;
   } else {
      var10000 = CollectionsKt.emptyList();
   }

   return var10000;
}

public fun HttpMessageBuilder.cookies(): List<Cookie> {
   var var10000: java.util.List = `$this$cookies`.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie());
   if (var10000 != null) {
      val `$this$map$iv`: java.lang.Iterable = var10000;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var10000, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(CookieKt.parseServerSetCookieHeader(`item$iv$iv` as java.lang.String));
      }

      var10000 = `destination$iv$iv` as java.util.List;
   } else {
      var10000 = CollectionsKt.emptyList();
   }

   return var10000;
}

public fun HttpMessage.cacheControl(): List<HeaderValue> {
   val var10000: java.lang.String = `$this$cacheControl`.getHeaders().get(HttpHeaders.INSTANCE.getCacheControl());
   if (var10000 != null) {
      val var3: java.util.List = HttpHeaderValueParserKt.parseHeaderValue(var10000);
      if (var3 != null) {
         return var3;
      }
   }

   return CollectionsKt.emptyList();
}

internal fun String.splitSetCookieHeader(): List<String> {
   var comma: Int = StringsKt.indexOf$default(`$this$splitSetCookieHeader`, ',', 0, false, 6, null);
   if (comma == -1) {
      return CollectionsKt.listOf(`$this$splitSetCookieHeader`);
   } else {
      val result: java.util.List = new ArrayList();
      var current: Int = 0;
      var equals: Int = StringsKt.indexOf$default(`$this$splitSetCookieHeader`, '=', comma, false, 4, null);
      var semicolon: Int = StringsKt.indexOf$default(`$this$splitSetCookieHeader`, ';', comma, false, 4, null);

      while (current < $this$splitSetCookieHeader.length() && comma > 0) {
         if (equals < comma) {
            equals = StringsKt.indexOf$default(`$this$splitSetCookieHeader`, '=', comma, false, 4, null);
         }

         var nextComma: Int;
         for (nextComma = StringsKt.indexOf$default($this$splitSetCookieHeader, ',', comma + 1, false, 4, null);
            0 <= nextComma && nextComma < equals;
            nextComma = StringsKt.indexOf$default($this$splitSetCookieHeader, ',', nextComma + 1, false, 4, null)
         ) {
            comma = nextComma;
         }

         if (semicolon < comma) {
            semicolon = StringsKt.indexOf$default(`$this$splitSetCookieHeader`, ';', comma, false, 4, null);
         }

         if (equals < 0) {
            val var7: java.util.Collection = result;
            val var9: java.lang.String = `$this$splitSetCookieHeader`.substring(current);
            var7.add(var9);
            return result;
         }

         if (semicolon == -1 || semicolon > equals) {
            val var10000: java.util.Collection = result;
            val var10001: java.lang.String = `$this$splitSetCookieHeader`.substring(current, comma);
            var10000.add(var10001);
            current = comma + 1;
         }

         comma = nextComma;
      }

      if (current < `$this$splitSetCookieHeader`.length()) {
         val var8: java.util.Collection = result;
         val var10: java.lang.String = `$this$splitSetCookieHeader`.substring(current);
         var8.add(var10);
      }

      return result;
   }
}
