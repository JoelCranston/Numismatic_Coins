package io.ktor.client.utils

import io.ktor.client.content.ProgressListener
import io.ktor.client.utils.ByteChannelUtilsKt.observable.1
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.GlobalScope

internal fun ByteReadChannel.observable(context: CoroutineContext, contentLength: Long?, listener: ProgressListener): ByteReadChannel {
   return ByteWriteChannelOperationsKt.writer(GlobalScope.INSTANCE, context, true, new 1(`$this$observable`, listener, contentLength, null)).getChannel();
}
