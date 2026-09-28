package io.ktor.utils.io

import kotlinx.io.RawSource

public fun ByteReadChannel.asSource(): RawSource {
   return new ByteReadChannelSource(`$this$asSource`);
}
