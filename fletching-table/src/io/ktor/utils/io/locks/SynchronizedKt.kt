package io.ktor.utils.io.locks

import io.ktor.utils.io.InternalAPI
import java.util.concurrent.locks.ReentrantLock
import kotlin.jvm.internal.InlineMarker

@InternalAPI
public fun reentrantLock(): ReentrantLock {
   return new ReentrantLock();
}

@InternalAPI
public inline fun <T> ReentrantLock.withLock(block: () -> Any): Any {
   label14: {
      `$this$withLock`.lock();

      try {
         val var3: Any = block.invoke();
      } catch (var5: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         `$this$withLock`.unlock();
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      `$this$withLock`.unlock();
      InlineMarker.finallyEnd(1);
   }
}

@InternalAPI
public inline fun <T> synchronized(lock: Any, block: () -> Any): Any {
   synchronized (lock) {
      val var4: Any = block.invoke();
      InlineMarker.finallyStart(1);
      InlineMarker.finallyEnd(1);
      return (T)var4;
   }
}

/** @deprecated */
@InternalAPI
@JvmSynthetic
fun `SynchronizedObject$annotations`() {
}

/** @deprecated */
@InternalAPI
@JvmSynthetic
fun `ReentrantLock$annotations`() {
}
