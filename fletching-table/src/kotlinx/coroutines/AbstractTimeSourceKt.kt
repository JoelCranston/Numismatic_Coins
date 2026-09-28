package kotlinx.coroutines

import java.util.concurrent.locks.LockSupport
import kotlin.internal.InlineOnly

private final var timeSource: AbstractTimeSource?

internal inline fun mockTimeSource(source: AbstractTimeSource?) {
   access$setTimeSource$p(source);
}

@InlineOnly
internal inline fun currentTimeMillis(): Long {
   val var10000: AbstractTimeSource = access$getTimeSource$p();
   return if (var10000 != null) var10000.currentTimeMillis() else System.currentTimeMillis();
}

@InlineOnly
internal inline fun nanoTime(): Long {
   val var10000: AbstractTimeSource = access$getTimeSource$p();
   return if (var10000 != null) var10000.nanoTime() else System.nanoTime();
}

@InlineOnly
internal inline fun wrapTask(block: Runnable): Runnable {
   val var10000: AbstractTimeSource = access$getTimeSource$p();
   if (var10000 != null) {
      val var1: Runnable = var10000.wrapTask(block);
      if (var1 != null) {
         return var1;
      }
   }

   return block;
}

@InlineOnly
internal inline fun trackTask() {
   val var10000: AbstractTimeSource = access$getTimeSource$p();
   if (var10000 != null) {
      var10000.trackTask();
   }
}

@InlineOnly
internal inline fun unTrackTask() {
   val var10000: AbstractTimeSource = access$getTimeSource$p();
   if (var10000 != null) {
      var10000.unTrackTask();
   }
}

@InlineOnly
internal inline fun registerTimeLoopThread() {
   val var10000: AbstractTimeSource = access$getTimeSource$p();
   if (var10000 != null) {
      var10000.registerTimeLoopThread();
   }
}

@InlineOnly
internal inline fun unregisterTimeLoopThread() {
   val var10000: AbstractTimeSource = access$getTimeSource$p();
   if (var10000 != null) {
      var10000.unregisterTimeLoopThread();
   }
}

@InlineOnly
internal inline fun parkNanos(blocker: Any, nanos: Long) {
   val var10000: AbstractTimeSource = access$getTimeSource$p();
   if (var10000 != null) {
      var10000.parkNanos(blocker, nanos);
   } else {
      LockSupport.parkNanos(blocker, nanos);
   }
}

@InlineOnly
internal inline fun unpark(thread: Thread) {
   val var10000: AbstractTimeSource = access$getTimeSource$p();
   if (var10000 != null) {
      var10000.unpark(thread);
   } else {
      LockSupport.unpark(thread);
   }
}

@JvmSynthetic
fun `access$setTimeSource$p`(var0: AbstractTimeSource) {
   timeSource = var0;
}

@JvmSynthetic
fun `access$getTimeSource$p`(): AbstractTimeSource {
   return timeSource;
}
