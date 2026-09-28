@file:SourceDebugExtension(["SMAP\nURLUtilsJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 URLUtilsJvm.kt\nio/ktor/http/URLUtilsJvmKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,74:1\n1#2:75\n*E\n"])

package io.ktor.http

import java.net.URI
import java.net.URL
import kotlin.jvm.internal.SourceDebugExtension

public fun URLBuilder.takeFrom(uri: URI): URLBuilder {
   var var10000: java.lang.String = uri.getScheme();
   if (var10000 != null) {
      `$this$takeFrom`.setProtocol(URLProtocol.Companion.createOrDefault(var10000));
      `$this$takeFrom`.setPort(`$this$takeFrom`.getProtocol().getDefaultPort());
   }

   if (uri.getPort() > 0) {
      `$this$takeFrom`.setPort(uri.getPort());
   } else {
      val parts: java.lang.String = uri.getScheme();
      if (parts == "http") {
         `$this$takeFrom`.setPort(80);
      } else if (parts == "https") {
         `$this$takeFrom`.setPort(443);
      }
   }

   if (uri.getRawUserInfo() != null) {
      var10000 = uri.getRawUserInfo();
      if (var10000.length() > 0) {
         var10000 = uri.getRawUserInfo();
         val var10: java.util.List = StringsKt.split$default(var10000, new java.lang.String[]{":"}, false, 0, 6, null);
         `$this$takeFrom`.setEncodedUser(CollectionsKt.first(var10));
         `$this$takeFrom`.setEncodedPassword(CollectionsKt.getOrNull(var10, 1));
      }
   }

   var10000 = uri.getHost();
   if (var10000 != null) {
      `$this$takeFrom`.setHost(var10000);
   }

   val var10001: java.lang.String = uri.getRawPath();
   URLBuilderKt.setEncodedPath(`$this$takeFrom`, var10001);
   var10000 = uri.getRawQuery();
   if (var10000 != null) {
      val var6: ParametersBuilder = ParametersKt.ParametersBuilder$default(0, 1, null);
      var6.appendAll(QueryKt.parseQueryString$default(var10000, 0, 0, false, 6, null));
      `$this$takeFrom`.setEncodedParameters(var6);
   }

   var10000 = uri.getQuery();
   if (var10000 != null && var10000.length() == 0) {
      `$this$takeFrom`.setTrailingQuery(true);
   }

   var10000 = uri.getRawFragment();
   if (var10000 != null) {
      `$this$takeFrom`.setEncodedFragment(var10000);
   }

   return `$this$takeFrom`;
}

public fun URLBuilder.takeFrom(url: URL): URLBuilder {
   val var10000: java.lang.String = url.getHost();
   val var2: URLBuilder;
   if (StringsKt.contains$default(var10000, '_', false, 2, null)) {
      val var10001: java.lang.String = url.toString();
      var2 = URLParserKt.takeFrom(`$this$takeFrom`, var10001);
   } else {
      val var3: URI = url.toURI();
      var2 = takeFrom(`$this$takeFrom`, var3);
   }

   return var2;
}

public fun Url.toURI(): URI {
   return new URI(`$this$toURI`.toString());
}

public fun Url(uri: URI): Url {
   return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), uri).build();
}
