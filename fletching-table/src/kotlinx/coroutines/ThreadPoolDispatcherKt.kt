package kotlinx.coroutines

// $VF: Class flags could not be determined
internal class ThreadPoolDispatcherKt {
   @ExperimentalCoroutinesApi
   @DelicateCoroutinesApi
   @JvmStatic
   fun newSingleThreadContext(name: java.lang.String): ExecutorCoroutineDispatcher {
      return ThreadPoolDispatcherKt__MultithreadedDispatchers_commonKt.newSingleThreadContext(name);
   }

   @DelicateCoroutinesApi
   @JvmStatic
   fun newFixedThreadPoolContext(nThreads: Int, name: java.lang.String): ExecutorCoroutineDispatcher {
      return ThreadPoolDispatcherKt__ThreadPoolDispatcherKt.newFixedThreadPoolContext(nThreads, name);
   }
}
