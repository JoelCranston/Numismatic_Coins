@file:SourceDebugExtension(["SMAP\nHttpClientEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpClientEngine.kt\nio/ktor/client/engine/HttpClientEngineKt\n+ 2 Utils.kt\nio/ktor/client/engine/UtilsKt\n+ 3 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 Attributes.kt\nio/ktor/util/AttributesKt\n+ 6 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,239:1\n104#2:240\n106#2,9:242\n375#3:241\n774#4:251\n865#4,2:252\n21#5:254\n69#6:255\n84#6,8:256\n*S KotlinDebug\n*F\n+ 1 HttpClientEngine.kt\nio/ktor/client/engine/HttpClientEngineKt\n*L\n222#1:240\n222#1:242,9\n222#1:241\n232#1:251\n232#1:252,2\n20#1:254\n20#1:255\n20#1:256,8\n*E\n"])

package io.ktor.client.engine

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineKt.config.1
import io.ktor.client.engine.UtilsKt.attachToUserJob.2
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.UnsafeHeaderException
import io.ktor.util.AttributeKey
import java.util.ArrayList
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt

internal final val CALL_COROUTINE: CoroutineName = new CoroutineName("call-context")
internal final val CLIENT_CONFIG: AttributeKey<HttpClientConfig<*>>

public fun <T : HttpClientEngineConfig> HttpClientEngineFactory<Any>.config(nested: (Any) -> Unit): HttpClientEngineFactory<Any> {
   return new 1(`$this$config`, nested);
}

internal suspend fun HttpClientEngine.createCallContext(parentJob: Job): CoroutineContext {
   val callJob: CompletableJob = JobKt.Job(parentJob);
   val callContext: CoroutineContext = `$this$createCallContext`.getCoroutineContext().plus(callJob).plus(CALL_COROUTINE);
   val `callJob$iv`: Job = callJob;
   val var10000: Job = `$completion`.getContext().get(Job.Key);
   if (var10000 != null) {
      `callJob$iv`.invokeOnCompletion(
         new 2(
            Job.DefaultImpls.invokeOnCompletion$default(
               var10000, true, false, new io.ktor.client.engine.UtilsKt.attachToUserJob.cleanupHandler.1(`callJob$iv`), 2, null
            )
         )
      );
   }

   return callContext;
}

private fun validateHeaders(request: HttpRequestData) {
   val `$this$filter$iv`: java.lang.Iterable = request.getHeaders().names();
   val `destination$iv$iv`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : $this$filter$iv) {
      if (HttpHeaders.INSTANCE.getUnsafeHeadersList().contains(`element$iv$iv` as java.lang.String)) {
         `destination$iv$iv`.add(`element$iv$iv`);
      }
   }

   val unsafeRequestHeaders: java.util.List = `destination$iv$iv` as java.util.List;
   if (!(`destination$iv$iv` as java.util.List).isEmpty()) {
      throw new UnsafeHeaderException(unsafeRequestHeaders.toString());
   }
}

@JvmSynthetic
fun `access$validateHeaders`(request: HttpRequestData) {
   validateHeaders(request);
}
