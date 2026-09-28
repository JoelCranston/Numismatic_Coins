package io.ktor.util.cio

import io.ktor.utils.io.pool.ByteBufferPool
import io.ktor.utils.io.pool.ObjectPool
import java.nio.ByteBuffer

internal const val DEFAULT_BUFFER_SIZE: Int = 4098
internal const val DEFAULT_KTOR_POOL_SIZE: Int = 2048
public final val KtorDefaultPool: ObjectPool<ByteBuffer> = (new ByteBufferPool(2048, 4098)) as ObjectPool
