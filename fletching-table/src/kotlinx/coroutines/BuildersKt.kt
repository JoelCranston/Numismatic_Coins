package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

// $VF: Class flags could not be determined
internal class BuildersKt {
   @Throws(java/lang/InterruptedException::class)
   @JvmStatic
   fun <T> runBlocking(context: CoroutineContext, block: (CoroutineScope?, Continuation<? super T>?) -> Any): T {
      return BuildersKt__BuildersKt.runBlocking(context, block);
   }

   @JvmStatic
   fun CoroutineScope.launch(context: CoroutineContext, start: CoroutineStart, block: (CoroutineScope?, Continuation<? super Unit>?) -> Any): Job {
      return BuildersKt__Builders_commonKt.launch(`$this$launch`, context, start, block);
   }

   @JvmStatic
   fun <T> CoroutineScope.async(context: CoroutineContext, start: CoroutineStart, block: (CoroutineScope?, Continuation<? super T>?) -> Any): Deferred<T> {
      return BuildersKt__Builders_commonKt.async(`$this$async`, context, start, block);
   }

   @JvmStatic
   fun <T> withContext(context: CoroutineContext, block: (CoroutineScope?, Continuation<? super T>?) -> Any, `$completion`: Continuation<? super T>): Any? {
      return BuildersKt__Builders_commonKt.withContext(context, block, `$completion`);
   }

   @JvmStatic
   fun <T> CoroutineDispatcher.invoke(block: (CoroutineScope?, Continuation<? super T>?) -> Any, `$completion`: Continuation<? super T>): Any? {
      return BuildersKt__Builders_commonKt.invoke(`$this$invoke`, block, `$completion`);
   }
}
