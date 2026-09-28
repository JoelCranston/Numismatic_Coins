package io.ktor.utils.io

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

public fun ioDispatcher(): CoroutineDispatcher {
   return Dispatchers.getIO();
}
