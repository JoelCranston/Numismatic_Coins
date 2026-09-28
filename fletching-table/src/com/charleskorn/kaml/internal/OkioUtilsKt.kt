package com.charleskorn.kaml.internal

import okio.Buffer
import okio.BufferedSource
import okio.ByteString

internal fun String.bufferedSource(): BufferedSource {
   return new Buffer().write(ByteString.Companion.encodeUtf8(`$this$bufferedSource`));
}
