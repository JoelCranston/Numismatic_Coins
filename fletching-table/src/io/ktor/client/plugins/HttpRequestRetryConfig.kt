package io.ktor.client.plugins

import io.ktor.client.plugins.HttpRequestRetryConfig.delay.1
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.KtorDsl
import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.random.Random

@KtorDsl
@SourceDebugExtension(["SMAP\nHttpRequestRetry.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpRequestRetry.kt\nio/ktor/client/plugins/HttpRequestRetryConfig\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,497:1\n1#2:498\n*E\n"])
public class HttpRequestRetryConfig {
   internal final lateinit var shouldRetry: (HttpRetryShouldRetryContext, HttpRequest, HttpResponse) -> Boolean
   internal final lateinit var shouldRetryOnException: (HttpRetryShouldRetryContext, HttpRequestBuilder, Throwable) -> Boolean
   internal final lateinit var delayMillis: (HttpRetryDelayContext, Int) -> Long
   internal final var delay: (Long, Continuation<Unit>) -> Any? = (new 1(null)) as Function2

   public final val retryIf: ((HttpRetryShouldRetryContext, HttpRequest, HttpResponse) -> Boolean)?
      public final get() {
         return if (this.shouldRetry != null) this.getShouldRetry$ktor_client_core() else null;
      }


   public final val retryOnExceptionIf: ((HttpRetryShouldRetryContext, HttpRequestBuilder, Throwable) -> Boolean)?
      public final get() {
         return if (this.shouldRetryOnException != null) this.getShouldRetryOnException$ktor_client_core() else null;
      }


   public final var modifyRequest: (HttpRetryModifyRequestContext, HttpRequestBuilder) -> Unit = HttpRequestRetryConfig::modifyRequest$lambda$0
      private set

   public final var maxRetries: Int

   public fun noRetry() {
      this.maxRetries = 0;
      this.setShouldRetry$ktor_client_core(HttpRequestRetryConfig::noRetry$lambda$0);
      this.setShouldRetryOnException$ktor_client_core(HttpRequestRetryConfig::noRetry$lambda$1);
   }

   public fun modifyRequest(block: (HttpRetryModifyRequestContext, HttpRequestBuilder) -> Unit) {
      this.modifyRequest = block;
   }

   public fun retryIf(maxRetries: Int = -1, block: (HttpRetryShouldRetryContext, HttpRequest, HttpResponse) -> Boolean) {
      if (maxRetries != -1) {
         this.maxRetries = maxRetries;
      }

      this.setShouldRetry$ktor_client_core(block);
   }

   public fun retryOnExceptionIf(maxRetries: Int = -1, block: (HttpRetryShouldRetryContext, HttpRequestBuilder, Throwable) -> Boolean) {
      if (maxRetries != -1) {
         this.maxRetries = maxRetries;
      }

      this.setShouldRetryOnException$ktor_client_core(block);
   }

   public fun retryOnException(maxRetries: Int = -1, retryOnTimeout: Boolean = false) {
      this.retryOnExceptionIf(maxRetries, HttpRequestRetryConfig::retryOnException$lambda$0);
   }

   public fun retryOnServerErrors(maxRetries: Int = -1) {
      this.retryIf(maxRetries, HttpRequestRetryConfig::retryOnServerErrors$lambda$0);
   }

   public fun retryOnExceptionOrServerErrors(maxRetries: Int = -1) {
      this.retryOnServerErrors(maxRetries);
      retryOnException$default(this, maxRetries, false, 2, null);
   }

   public fun delayMillis(respectRetryAfterHeader: Boolean = true, block: (HttpRetryDelayContext, Int) -> Long) {
      this.setDelayMillis$ktor_client_core(HttpRequestRetryConfig::delayMillis$lambda$0);
   }

   public fun constantDelay(millis: Long = 1000L, randomizationMs: Long = 1000L, respectRetryAfterHeader: Boolean = true) {
      if (millis <= 0L) {
         throw new IllegalStateException("Check failed.");
      } else if (randomizationMs < 0L) {
         throw new IllegalStateException("Check failed.");
      } else {
         this.delayMillis(respectRetryAfterHeader, HttpRequestRetryConfig::constantDelay$lambda$0);
      }
   }

   public fun exponentialDelay(
      base: Double = 2.0,
      baseDelayMs: Long = 1000L,
      maxDelayMs: Long = 60000L,
      randomizationMs: Long = 1000L,
      respectRetryAfterHeader: Boolean = true
   ) {
      if (!(base > 0.0)) {
         throw new IllegalStateException("Check failed.");
      } else if (baseDelayMs <= 0L) {
         throw new IllegalStateException("Check failed.");
      } else if (maxDelayMs <= 0L) {
         throw new IllegalStateException("Check failed.");
      } else if (randomizationMs < 0L) {
         throw new IllegalStateException("Check failed.");
      } else {
         this.delayMillis(respectRetryAfterHeader, HttpRequestRetryConfig::exponentialDelay$lambda$0);
      }
   }

   public fun delay(block: (Long, Continuation<Unit>) -> Any?) {
      this.delay = block;
   }

   private fun randomMs(randomizationMs: Long): Long {
      return if (randomizationMs == 0L) 0L else Random.Default.nextLong(randomizationMs);
   }

   @JvmStatic
   fun `modifyRequest$lambda$0`(var0: HttpRetryModifyRequestContext, it: HttpRequestBuilder): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `noRetry$lambda$0`(var0: HttpRetryShouldRetryContext, var1: HttpRequest, var2: HttpResponse): Boolean {
      return false;
   }

   @JvmStatic
   fun `noRetry$lambda$1`(var0: HttpRetryShouldRetryContext, var1: HttpRequestBuilder, var2: java.lang.Throwable): Boolean {
      return false;
   }

   @JvmStatic
   fun `retryOnException$lambda$0`(
      `$retryOnTimeout`: Boolean, `$this$retryOnExceptionIf`: HttpRetryShouldRetryContext, var2: HttpRequestBuilder, cause: java.lang.Throwable
   ): Boolean {
      return if (HttpRequestRetryKt.access$isTimeoutException(cause)) `$retryOnTimeout` else cause !is CancellationException;
   }

   @JvmStatic
   fun HttpRetryShouldRetryContext.`retryOnServerErrors$lambda$0`(var1: HttpRequest, response: HttpResponse): Boolean {
      val it: Int = response.getStatus().getValue();
      return 500 <= it && it < 600;
   }

   @JvmStatic
   fun `delayMillis$lambda$0`(`$respectRetryAfterHeader`: Boolean, `$block`: Function2, var2: HttpRetryDelayContext, it: Int): Long {
      val var9: Long;
      if (`$respectRetryAfterHeader`) {
         label24: {
            val var10000: HttpResponse = var2.getResponse();
            if (var10000 != null) {
               val var5: Headers = var10000.getHeaders();
               if (var5 != null) {
                  val var6: java.lang.String = var5.get(HttpHeaders.INSTANCE.getRetryAfter());
                  if (var6 != null) {
                     val var7: java.lang.Long = StringsKt.toLongOrNull(var6);
                     if (var7 != null) {
                        var8 = var7 * (long)1000;
                        break label24;
                     }
                  }
               }
            }

            var8 = null;
         }

         var9 = Math.max((`$block`.invoke(var2, it) as java.lang.Number).longValue(), var8 ?: 0L);
      } else {
         var9 = (`$block`.invoke(var2, it) as java.lang.Number).longValue();
      }

      return var9;
   }

   @JvmStatic
   fun `constantDelay$lambda$0`(
      `$millis`: Long, `this$0`: HttpRequestRetryConfig, `$randomizationMs`: Long, `$this$delayMillis`: HttpRetryDelayContext, it: Int
   ): Long {
      return `$millis` + `this$0`.randomMs(`$randomizationMs`);
   }

   @JvmStatic
   fun `exponentialDelay$lambda$0`(
      `$base`: Double,
      `$baseDelayMs`: Long,
      `$maxDelayMs`: Long,
      `this$0`: HttpRequestRetryConfig,
      `$randomizationMs`: Long,
      `$this$delayMillis`: HttpRetryDelayContext,
      retry: Int
   ): Long {
      return Math.min((long)(Math.pow(`$base`, (double)(retry - 1)) * (double)`$baseDelayMs`), `$maxDelayMs`) + `this$0`.randomMs(`$randomizationMs`);
   }
}
