package io.ktor.http.cio

import io.ktor.http.cio.MultipartJvmAndPosixKt.discardBlocking.1
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.BuildersKt

internal fun ByteReadChannel.discardBlocking() {
   BuildersKt.runBlocking$default(null, new 1(`$this$discardBlocking`, null), 1, null);
}
