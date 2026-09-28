package kotlinx.coroutines

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nThreadPoolDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ThreadPoolDispatcher.kt\nkotlinx/coroutines/ThreadPoolDispatcherKt__ThreadPoolDispatcherKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,18:1\n1#2:19\n*E\n"])
@JvmSynthetic
internal class ThreadPoolDispatcherKt__ThreadPoolDispatcherKt {
   @DelicateCoroutinesApi
   @JvmStatic
   public fun newFixedThreadPoolContext(nThreads: Int, name: String): ExecutorCoroutineDispatcher {
      if (nThreads < 1) {
         throw new IllegalArgumentException(("Expected at least one thread, but $nThreads specified").toString());
      } else {
         return ExecutorsKt.from(
            Executors.unconfigurableExecutorService(
               Executors.newScheduledThreadPool(
                  nThreads, ThreadPoolDispatcherKt__ThreadPoolDispatcherKt::newFixedThreadPoolContext$lambda$2$ThreadPoolDispatcherKt__ThreadPoolDispatcherKt
               )
            )
         );
      }
   }

   @JvmStatic
   fun `newFixedThreadPoolContext$lambda$2$ThreadPoolDispatcherKt__ThreadPoolDispatcherKt`(
      `$nThreads`: Int, `$name`: java.lang.String, `$threadNo`: AtomicInteger, runnable: Runnable
   ): Thread {
      val var4: Thread = new Thread(runnable, if (`$nThreads` == 1) `$name` else "$`$name`-${`$threadNo`.incrementAndGet()}");
      var4.setDaemon(true);
      return var4;
   }
}
