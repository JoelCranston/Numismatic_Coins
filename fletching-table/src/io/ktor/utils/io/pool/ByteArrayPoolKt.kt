package io.ktor.utils.io.pool

import io.ktor.utils.io.pool.ByteArrayPoolKt.ByteArrayPool.1

private const val DEFAULT_POOL_ARRAY_SIZE: Int = 4096
private const val DEFAULT_POOL_CAPACITY: Int = 128
public final val ByteArrayPool: ObjectPool<ByteArray> = (new 1()) as ObjectPool
