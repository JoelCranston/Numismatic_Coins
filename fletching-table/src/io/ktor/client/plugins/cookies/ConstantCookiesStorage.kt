package io.ktor.client.plugins.cookies

import io.ktor.http.Cookie
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nConstantCookiesStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConstantCookiesStorage.kt\nio/ktor/client/plugins/cookies/ConstantCookiesStorage\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,23:1\n11561#2:24\n11896#2,3:25\n774#3:28\n865#3,2:29\n*S KotlinDebug\n*F\n+ 1 ConstantCookiesStorage.kt\nio/ktor/client/plugins/cookies/ConstantCookiesStorage\n*L\n15#1:24\n15#1:25,3\n17#1:28\n17#1:29,2\n*E\n"])
public class ConstantCookiesStorage(vararg cookies: Cookie) : CookiesStorage {
   private final val storage: List<Cookie>

   init {
      val `destination$iv$iv`: java.util.Collection = new ArrayList(cookies.length);

      for (Object item$iv$iv : cookies) {
         `destination$iv$iv`.add(
            CookiesStorageKt.fillDefaults((Cookie)`item$iv$iv`, new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null).build())
         );
      }

      this.storage = CollectionsKt.toList(`destination$iv$iv`);
   }

   public override suspend fun get(requestUrl: Url): List<Cookie> {
      val `$this$filter$iv`: java.lang.Iterable = this.storage;
      val `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$filter$iv) {
         if (CookiesStorageKt.matches(`element$iv$iv` as Cookie, requestUrl)) {
            `destination$iv$iv`.add(`element$iv$iv`);
         }
      }

      return `destination$iv$iv` as java.util.List;
   }

   public override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
      return Unit.INSTANCE;
   }

   public override fun close() {
   }
}
