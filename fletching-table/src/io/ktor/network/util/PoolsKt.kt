package io.ktor.network.util

import io.ktor.utils.io.pool.DirectByteBufferPool
import io.ktor.utils.io.pool.ObjectPool
import java.nio.ByteBuffer

internal const val DEFAULT_BYTE_BUFFER_POOL_SIZE: Int = 4096
internal const val DEFAULT_BYTE_BUFFER_BUFFER_SIZE: Int = 4096
public final val DefaultByteBufferPool: ObjectPool<ByteBuffer> = (new DirectByteBufferPool(4096, 4096)) as ObjectPool
public final val DefaultDatagramByteBufferPool: ObjectPool<ByteBuffer> = (new DirectByteBufferPool(2048, 65535)) as ObjectPool
