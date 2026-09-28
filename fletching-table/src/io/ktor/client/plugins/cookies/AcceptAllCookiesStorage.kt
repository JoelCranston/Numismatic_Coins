package io.ktor.client.plugins.cookies

import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.get.1
import io.ktor.http.Cookie
import io.ktor.http.Url
import io.ktor.util.date.DateJvmKt
import io.ktor.util.date.GMTDate
import java.util.ArrayList
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.MutexKt

@SourceDebugExtension(["SMAP\nAcceptAllCookiesStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AcceptAllCookiesStorage.kt\nio/ktor/client/plugins/cookies/AcceptAllCookiesStorage\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,73:1\n116#2,8:74\n125#2,2:89\n116#2,11:91\n774#3:82\n865#3,2:83\n1563#3:85\n1634#3,3:86\n1803#3,2:102\n1805#3:105\n1#4:104\n*S KotlinDebug\n*F\n+ 1 AcceptAllCookiesStorage.kt\nio/ktor/client/plugins/cookies/AcceptAllCookiesStorage\n*L\n26#1:74,8\n26#1:89,2\n39#1:91,11\n30#1:82\n30#1:83,2\n30#1:85\n30#1:86,3\n63#1:102,2\n63#1:105\n*E\n"])
public class AcceptAllCookiesStorage(clock: () -> Long = AcceptAllCookiesStorage::_init_$lambda$0) : CookiesStorage {
   private final val clock: () -> Long
   private final val container: MutableList<io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.CookieWithTimestamp>
   private final val mutex: Mutex

   init {
      this.clock = clock;
      this.container = new ArrayList<>();
      this.oldestCookie = 0L;
      this.mutex = MutexKt.Mutex$default(false, 1, null);
   }

   public override suspend fun get(requestUrl: Url): List<Cookie> {
      label56: {
         var `$continuation`: Continuation;
         label54: {
            if (`$completion` is 1) {
               `$continuation` = `$completion` as 1;
               if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
                  `$continuation`.label -= Integer.MIN_VALUE;
                  break label54;
               }
            }

            `$continuation` = new 1(this, `$completion`);
         }

         val `$result`: Any = `$continuation`.result;
         val var24: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var `$this$withLock_u24default$iv`: Mutex;
         var `owner$iv`: Any;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               `$this$withLock_u24default$iv` = this.mutex;
               `owner$iv` = null;
               `$continuation`.L$0 = requestUrl;
               `$continuation`.L$1 = `$this$withLock_u24default$iv`;
               `$continuation`.I$0 = 0;
               `$continuation`.label = 1;
               if (`$this$withLock_u24default$iv`.lock(null, `$continuation`) === var24) {
                  return var24;
               }
               break;
            case 1:
               val `$i$f$withLock`: Int = `$continuation`.I$0;
               `owner$iv` = null;
               `$this$withLock_u24default$iv` = `$continuation`.L$1 as Mutex;
               requestUrl = `$continuation`.L$0 as Url;
               ResultKt.throwOnFailure(`$result`);
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            val now: Long = this.clock.invoke().longValue();
            if (now >= this.oldestCookie) {
               this.cleanup(now);
            }

            var `$this$map$iv`: java.lang.Iterable = this.container;
            var `destination$iv$iv`: java.util.Collection = new ArrayList();

            for (Object element$iv$iv : $this$filter$iv) {
               if (CookiesStorageKt.matches((`item$iv$iv` as AcceptAllCookiesStorage.CookieWithTimestamp).getCookie(), requestUrl)) {
                  `destination$iv$iv`.add(`item$iv$iv`);
               }
            }

            `$this$map$iv` = `destination$iv$iv` as java.util.List;
            `destination$iv$iv` = new ArrayList(CollectionsKt.collectionSizeOrDefault(`destination$iv$iv` as java.util.List, 10));

            for (Object item$iv$iv : $this$filter$iv) {
               `destination$iv$iv`.add((var33 as AcceptAllCookiesStorage.CookieWithTimestamp).getCookie());
            }

            val cookies: java.util.List = `destination$iv$iv` as java.util.List;
         } catch (var25: java.lang.Throwable) {
            `$this$withLock_u24default$iv`.unlock(`owner$iv`);
         }

         `$this$withLock_u24default$iv`.unlock(`owner$iv`);
      }
   }

   public override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
      label45: {
         var `$continuation`: Continuation;
         label43: {
            if (`$completion` is io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.addCookie.1) {
               `$continuation` = `$completion` as io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.addCookie.1;
               if (((`$completion` as io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.addCookie.1).label and Integer.MIN_VALUE) != 0) {
                  `$continuation`.label -= Integer.MIN_VALUE;
                  break label43;
               }
            }

            `$continuation` = new io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.addCookie.1(this, `$completion`);
         }

         val `$result`: Any = `$continuation`.result;
         val var17: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var `$this$withLock_u24default$iv`: Mutex;
         var `owner$iv`: Any;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               if (StringsKt.isBlank(cookie.getName())) {
                  return Unit.INSTANCE;
               }

               `$this$withLock_u24default$iv` = this.mutex;
               `owner$iv` = null;
               `$continuation`.L$0 = requestUrl;
               `$continuation`.L$1 = cookie;
               `$continuation`.L$2 = `$this$withLock_u24default$iv`;
               `$continuation`.I$0 = 0;
               `$continuation`.label = 1;
               if (`$this$withLock_u24default$iv`.lock(null, `$continuation`) === var17) {
                  return var17;
               }
               break;
            case 1:
               val `$i$f$withLock`: Int = `$continuation`.I$0;
               `owner$iv` = null;
               `$this$withLock_u24default$iv` = `$continuation`.L$2 as Mutex;
               cookie = `$continuation`.L$1 as Cookie;
               requestUrl = `$continuation`.L$0 as Url;
               ResultKt.throwOnFailure(`$result`);
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            CollectionsKt.removeAll(this.container, AcceptAllCookiesStorage::addCookie$lambda$1$0);
            val createdAt: Long = this.clock.invoke().longValue();
            this.container.add(new AcceptAllCookiesStorage.CookieWithTimestamp(CookiesStorageKt.fillDefaults(cookie, requestUrl), createdAt));
            val var10001: java.lang.Long = this.maxAgeOrExpires(cookie, createdAt);
            if (var10001 != null) {
               val it: Long = var10001.longValue();
               if (this.oldestCookie > it) {
                  this.oldestCookie = it;
               }
            }
         } catch (var18: java.lang.Throwable) {
            `$this$withLock_u24default$iv`.unlock(`owner$iv`);
         }

         `$this$withLock_u24default$iv`.unlock(`owner$iv`);
      }
   }

   public override fun close() {
   }

   private fun cleanup(timestamp: Long) {
      CollectionsKt.removeAll(this.container, AcceptAllCookiesStorage::cleanup$lambda$0);
      val `$this$fold$iv`: java.lang.Iterable = this.container;
      var `accumulator$iv`: Long = java.lang.Long.MAX_VALUE;

      for (Object element$iv : $this$fold$iv) {
         val var10000: java.lang.Long = this.maxAgeOrExpires(
            (`element$iv` as AcceptAllCookiesStorage.CookieWithTimestamp).component1(),
            (`element$iv` as AcceptAllCookiesStorage.CookieWithTimestamp).component2()
         );
         `accumulator$iv` = if (var10000 != null) Math.min(`accumulator$iv`, var10000.longValue()) else `accumulator$iv`;
      }

      this.oldestCookie = `accumulator$iv`;
   }

   private fun Cookie.maxAgeOrExpires(createdAt: Long): Long? {
      val var10000: Int = `$this$maxAgeOrExpires`.getMaxAgeInt();
      val var6: java.lang.Long;
      if (var10000 != null) {
         var6 = createdAt + (long)var10000.intValue() * 1000L;
      } else {
         val var7: GMTDate = `$this$maxAgeOrExpires`.getExpires();
         var6 = if (var7 != null) var7.getTimestamp() else null;
      }

      return var6;
   }

   @JvmStatic
   fun `_init_$lambda$0`(): Long {
      return DateJvmKt.getTimeMillis();
   }

   @JvmStatic
   fun `addCookie$lambda$1$0`(`$cookie`: Cookie, `$requestUrl`: Url, var2: AcceptAllCookiesStorage.CookieWithTimestamp): Boolean {
      val existingCookie: Cookie = var2.component1();
      return existingCookie.getName() == `$cookie`.getName() && CookiesStorageKt.matches(existingCookie, `$requestUrl`);
   }

   @JvmStatic
   fun `cleanup$lambda$0`(`this$0`: AcceptAllCookiesStorage, `$timestamp`: Long, var3: AcceptAllCookiesStorage.CookieWithTimestamp): Boolean {
      val var10000: java.lang.Long = `this$0`.maxAgeOrExpires(var3.component1(), var3.component2());
      if (var10000 != null) {
         return var10000 < `$timestamp`;
      } else {
         return false;
      }
   }

   fun AcceptAllCookiesStorage() {
      this(null, 1, null);
   }

   private data class CookieWithTimestamp(cookie: Cookie, createdAt: Long) {
      public final val cookie: Cookie
      public final val createdAt: Long

      init {
         this.cookie = cookie;
         this.createdAt = createdAt;
      }

      public operator fun component1(): Cookie {
         return this.cookie;
      }

      public operator fun component2(): Long {
         return this.createdAt;
      }

      public fun copy(cookie: Cookie = this.cookie, createdAt: Long = this.createdAt): io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.CookieWithTimestamp {
         return new AcceptAllCookiesStorage.CookieWithTimestamp(cookie, createdAt);
      }

      public override fun toString(): String {
         return "CookieWithTimestamp(cookie=${this.cookie}, createdAt=${this.createdAt})";
      }

      public override fun hashCode(): Int {
         return this.cookie.hashCode() * 31 + java.lang.Long.hashCode(this.createdAt);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is AcceptAllCookiesStorage.CookieWithTimestamp) {
            return false;
         } else {
            val var2: AcceptAllCookiesStorage.CookieWithTimestamp = other as AcceptAllCookiesStorage.CookieWithTimestamp;
            if (!(this.cookie == (other as AcceptAllCookiesStorage.CookieWithTimestamp).cookie)) {
               return false;
            } else {
               return this.createdAt == var2.createdAt;
            }
         }
      }
   }
}
