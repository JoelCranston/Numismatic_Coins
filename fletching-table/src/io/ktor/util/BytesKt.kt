package io.ktor.util

import io.ktor.utils.io.InternalAPI

@InternalAPI
public fun ByteArray.readShort(offset: Int): Short {
   return (short)((`$this$readShort`[offset] and 255) shl 8 or `$this$readShort`[offset + 1] and 255);
}
