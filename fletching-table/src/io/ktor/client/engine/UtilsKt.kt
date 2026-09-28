@file:SourceDebugExtension(["SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\nio/ktor/client/engine/UtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,117:1\n1#2:118\n375#3:119\n1869#4,2:120\n*S KotlinDebug\n*F\n+ 1 Utils.kt\nio/ktor/client/engine/UtilsKt\n*L\n104#1:119\n54#1:120,2\n*E\n"])

package io.ktor.client.engine

import io.ktor.client.engine.UtilsKt.attachToUserJob.2
import io.ktor.client.engine.UtilsKt.attachToUserJob.cleanupHandler.1
import io.ktor.client.utils.HeadersKt
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.http.content.OutgoingContent
import io.ktor.util.PlatformUtils
import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.Job

@InternalAPI
public final val KTOR_DEFAULT_USER_AGENT: String = "ktor-client"

private final val DATE_HEADERS: Set<String> =
   SetsKt.setOf(
      new java.lang.String[]{
         HttpHeaders.INSTANCE.getDate(),
         HttpHeaders.INSTANCE.getExpires(),
         HttpHeaders.INSTANCE.getLastModified(),
         HttpHeaders.INSTANCE.getIfModifiedSince(),
         HttpHeaders.INSTANCE.getIfUnmodifiedSince()
      }
   )

@InternalAPI
public fun mergeHeaders(requestHeaders: Headers, content: OutgoingContent, block: (String, String) -> Unit) {
   HeadersKt.buildHeaders(UtilsKt::mergeHeaders$lambda$0).forEach(UtilsKt::mergeHeaders$lambda$1);
   if (requestHeaders.get(HttpHeaders.INSTANCE.getUserAgent()) == null
      && content.getHeaders().get(HttpHeaders.INSTANCE.getUserAgent()) == null
      && needUserAgent()) {
      block.invoke(HttpHeaders.INSTANCE.getUserAgent(), KTOR_DEFAULT_USER_AGENT);
   }

   var var9: java.lang.String;
   label42: {
      val var10000: ContentType = content.getContentType();
      if (var10000 != null) {
         var9 = var10000.toString();
         if (var9 != null) {
            break label42;
         }
      }

      var9 = content.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
      if (var9 == null) {
         var9 = requestHeaders.get(HttpHeaders.INSTANCE.getContentType());
      }
   }

   label37: {
      val var10: java.lang.Long = content.getContentLength();
      if (var10 != null) {
         var9 = java.lang.String.valueOf(var10.longValue());
         if (var9 != null) {
            break label37;
         }
      }

      var9 = content.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
      if (var9 == null) {
         var9 = requestHeaders.get(HttpHeaders.INSTANCE.getContentLength());
      }
   }

   if (var9 != null) {
      block.invoke(HttpHeaders.INSTANCE.getContentType(), var9);
   }

   if (var9 != null) {
      block.invoke(HttpHeaders.INSTANCE.getContentLength(), var9);
   }
}

@InternalAPI
public suspend fun callContext(): CoroutineContext {
   val var10000: CoroutineContext.Element = `$completion`.getContext().get(KtorCallContextElement.Companion);
   return (var10000 as KtorCallContextElement).getCallContext();
}

internal suspend inline fun attachToUserJob(callJob: Job) {
   val var10000: Job = `$completion`.getContext().get(Job.Key);
   if (var10000 == null) {
      return Unit.INSTANCE;
   } else {
      callJob.invokeOnCompletion(new 2(Job.DefaultImpls.invokeOnCompletion$default(var10000, true, false, new 1(callJob), 2, null)));
      return Unit.INSTANCE;
   }
}

fun `attachToUserJob$$forInline`(callJob: Job, `$completion`: Continuation<? super Unit>): Any {
   InlineMarker.mark(3);
   val cleanupHandler: Job = null.getContext().get(Job.Key);
   if (cleanupHandler != null) {
      callJob.invokeOnCompletion(new 2(Job.DefaultImpls.invokeOnCompletion$default(cleanupHandler, true, false, new 1(callJob), 2, null)));
      return Unit.INSTANCE;
   } else {
      return Unit.INSTANCE;
   }
}

private fun needUserAgent(): Boolean {
   return !PlatformUtils.INSTANCE.getIS_BROWSER();
}

fun `mergeHeaders$lambda$0`(`$requestHeaders`: Headers, `$content`: OutgoingContent, `$this$buildHeaders`: HeadersBuilder): Unit {
   `$this$buildHeaders`.appendAll(`$requestHeaders`);
   `$this$buildHeaders`.appendAll(`$content`.getHeaders());
   return Unit.INSTANCE;
}

fun `mergeHeaders$lambda$1`(`$block`: Function2, key: java.lang.String, values: java.util.List): Unit {
   if (HttpHeaders.INSTANCE.getContentLength() == key) {
      return Unit.INSTANCE;
   } else if (HttpHeaders.INSTANCE.getContentType() == key) {
      return Unit.INSTANCE;
   } else {
      if (DATE_HEADERS.contains(key)) {
         val separator: java.lang.Iterable;
         for (Object element$iv : separator) {
            `$block`.invoke(key, `element$iv` as java.lang.String);
         }
      } else {
         `$block`.invoke(
            key, CollectionsKt.joinToString$default(values, if (HttpHeaders.INSTANCE.getCookie() == key) "; " else ",", null, null, 0, null, null, 62, null)
         );
      }

      return Unit.INSTANCE;
   }
}
