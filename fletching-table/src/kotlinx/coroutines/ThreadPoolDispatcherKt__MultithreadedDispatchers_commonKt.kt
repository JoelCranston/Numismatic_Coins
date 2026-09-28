package kotlinx.coroutines

@JvmSynthetic
internal class ThreadPoolDispatcherKt__MultithreadedDispatchers_commonKt {
   @ExperimentalCoroutinesApi
   @DelicateCoroutinesApi
   @JvmStatic
   public fun newSingleThreadContext(name: String): ExecutorCoroutineDispatcher {
      return ThreadPoolDispatcherKt.newFixedThreadPoolContext(1, name);
   }
}
