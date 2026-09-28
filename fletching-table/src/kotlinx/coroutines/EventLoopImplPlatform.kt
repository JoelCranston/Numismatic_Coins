package kotlinx.coroutines

import java.util.concurrent.locks.LockSupport
import kotlinx.coroutines.EventLoopImplBase.DelayedTask

internal abstract class EventLoopImplPlatform : EventLoop {
   protected abstract val thread: Thread

   protected fun unpark() {
      val thread: Thread = this.getThread();
      if (Thread.currentThread() != thread) {
         val var10000: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
         if (var10000 != null) {
            var10000.unpark(thread);
         } else {
            LockSupport.unpark(thread);
         }
      }
   }

   protected open fun reschedule(now: Long, delayedTask: DelayedTask) {
      DefaultExecutor.INSTANCE.schedule(now, delayedTask);
   }
}
