package kotlinx.coroutines.internal

import java.lang.reflect.Method
import java.util.Collections
import java.util.IdentityHashMap
import java.util.concurrent.Executor
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.jvm.internal.InlineMarker

private final val REMOVE_FUTURE_ON_CANCEL: Method?

internal inline fun <T> ReentrantLock.withLock(action: () -> T): T {
   label15: {
      val var3: Lock = `$this$withLock`;
      `$this$withLock`.lock();

      try {
         val var4: Any = action.invoke();
      } catch (var6: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         var3.unlock();
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      var3.unlock();
      InlineMarker.finallyEnd(1);
   }
}

internal inline fun <E> identitySet(expectedSize: Int): MutableSet<E> {
   return Collections.newSetFromMap(new IdentityHashMap(expectedSize));
}

internal fun removeFutureOnCancel(executor: Executor): Boolean {
   try {
      val var10000: ScheduledThreadPoolExecutor = executor as? ScheduledThreadPoolExecutor;
      if ((executor as? ScheduledThreadPoolExecutor) == null) {
         return false;
      } else if (REMOVE_FUTURE_ON_CANCEL == null) {
         return false;
      } else {
         REMOVE_FUTURE_ON_CANCEL.invoke(var10000, true);
         return true;
      }
   } catch (var3: java.lang.Throwable) {
      return false;
   }
}
