package io.ktor.client.utils

import io.ktor.utils.io.pool.ByteBufferPool

public final val HttpClientDefaultPool: ByteBufferPool = new ByteBufferPool(0, 0, 3, null)
