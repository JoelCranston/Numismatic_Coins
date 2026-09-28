package io.ktor.client.plugins.cache.storage

import java.io.File
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

public fun FileStorage(directory: File, dispatcher: CoroutineDispatcher = Dispatchers.getIO()): CacheStorage {
   return new CachingCacheStorage(new FileCacheStorage(directory, dispatcher));
}

@JvmSynthetic
fun `FileStorage$default`(var0: File, var1: CoroutineDispatcher, var2: Int, var3: Any): CacheStorage {
   if ((var2 and 2) != 0) {
      var1 = Dispatchers.getIO();
   }

   return FileStorage(var0, var1);
}
