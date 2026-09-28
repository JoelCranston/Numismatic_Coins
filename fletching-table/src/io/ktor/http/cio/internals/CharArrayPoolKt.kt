package io.ktor.http.cio.internals

import io.ktor.http.cio.internals.CharArrayPoolKt.CharArrayPool.1
import io.ktor.http.cio.internals.CharArrayPoolKt.CharArrayPool.2
import io.ktor.utils.io.pool.ObjectPool

internal const val CHAR_ARRAY_POOL_SIZE: Int = 4096
internal const val CHAR_BUFFER_ARRAY_LENGTH: Int = 2048
internal final val CharArrayPool: ObjectPool<CharArray> = if (CharArrayPoolJvmKt.isPoolingDisabled()) (new 1()) as ObjectPool else (new 2()) as ObjectPool
