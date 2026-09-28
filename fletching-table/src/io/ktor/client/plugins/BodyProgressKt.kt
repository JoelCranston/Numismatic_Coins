@file:SourceDebugExtension(["SMAP\nBodyProgress.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BodyProgress.kt\nio/ktor/client/plugins/BodyProgressKt\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,101:1\n21#2:102\n21#2:112\n69#3:103\n84#3,8:104\n69#3:113\n84#3,8:114\n*S KotlinDebug\n*F\n+ 1 BodyProgress.kt\nio/ktor/client/plugins/BodyProgressKt\n*L\n21#1:102\n24#1:112\n21#1:103\n21#1:104,8\n24#1:113\n24#1:114,8\n*E\n"])

package io.ktor.client.plugins

import io.ktor.client.call.DelegatedCallKt
import io.ktor.client.content.ProgressListener
import io.ktor.client.plugins.BodyProgressKt.BodyProgress.1.1
import io.ktor.client.plugins.BodyProgressKt.BodyProgress.1.2
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.ByteChannelUtilsKt
import io.ktor.http.HttpMessagePropertiesKt
import io.ktor.util.AttributeKey
import io.ktor.utils.io.ByteReadChannel
import kotlin.jvm.internal.SourceDebugExtension

private final val UploadProgressListenerAttributeKey: AttributeKey<ProgressListener>
private final val DownloadProgressListenerAttributeKey: AttributeKey<ProgressListener>
public final val BodyProgress: ClientPlugin<Unit>

internal fun HttpResponse.withObservableDownload(listener: ProgressListener): HttpResponse {
   return DelegatedCallKt.replaceResponse$default(`$this$withObservableDownload`.getCall(), null, BodyProgressKt::withObservableDownload$lambda$0, 1, null)
      .getResponse();
}

public fun HttpRequestBuilder.onDownload(listener: ProgressListener?) {
   if (listener == null) {
      `$this$onDownload`.getAttributes().remove(DownloadProgressListenerAttributeKey);
   } else {
      `$this$onDownload`.getAttributes().put(DownloadProgressListenerAttributeKey, listener);
   }
}

public fun HttpRequestBuilder.onUpload(listener: ProgressListener?) {
   if (listener == null) {
      `$this$onUpload`.getAttributes().remove(UploadProgressListenerAttributeKey);
   } else {
      `$this$onUpload`.getAttributes().put(UploadProgressListenerAttributeKey, listener);
   }
}

fun ClientPluginBuilder.`BodyProgress$lambda$0`(): Unit {
   `$this$createClientPlugin`.on(AfterRenderHook.INSTANCE, new 1(null));
   `$this$createClientPlugin`.on(AfterReceiveHook.INSTANCE, new 2(null));
   return Unit.INSTANCE;
}

fun `withObservableDownload$lambda$0`(`$listener`: ProgressListener, `$this$replaceResponse`: HttpResponse): ByteReadChannel {
   return ByteChannelUtilsKt.observable(
      `$this$replaceResponse`.getRawContent(),
      `$this$replaceResponse`.getCoroutineContext(),
      HttpMessagePropertiesKt.contentLength(`$this$replaceResponse`),
      `$listener`
   );
}

@JvmSynthetic
fun `access$getUploadProgressListenerAttributeKey$p`(): AttributeKey {
   return UploadProgressListenerAttributeKey;
}

@JvmSynthetic
fun `access$getDownloadProgressListenerAttributeKey$p`(): AttributeKey {
   return DownloadProgressListenerAttributeKey;
}
