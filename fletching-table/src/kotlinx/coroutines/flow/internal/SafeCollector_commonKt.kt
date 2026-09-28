package kotlinx.coroutines.flow.internal

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.internal.SafeCollector_commonKt.unsafeFlow.1
import kotlinx.coroutines.internal.ScopeCoroutine

@JvmName(name = "checkContext")
internal fun SafeCollector<*>.checkContext(currentContext: CoroutineContext) {
   if (currentContext.fold(0, SafeCollector_commonKt::checkContext$lambda$0).intValue() != `$this$checkContext`.collectContextSize) {
      throw new IllegalStateException(
         ("Flow invariant is violated:\n\t\tFlow was collected in ${`$this$checkContext`.collectContext},\n\t\tbut emission happened in $currentContext.\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead")
            .toString()
      );
   }
}

internal tailrec fun Job?.transitiveCoroutineParent(collectJob: Job?): Job? {
   while ($this$transitiveCoroutineParent != null) {
      if (`$this$transitiveCoroutineParent` === collectJob) {
         return `$this$transitiveCoroutineParent`;
      }

      if (`$this$transitiveCoroutineParent` !is ScopeCoroutine) {
         return `$this$transitiveCoroutineParent`;
      }

      `$this$transitiveCoroutineParent` = (`$this$transitiveCoroutineParent` as ScopeCoroutine).getParent();
      collectJob = collectJob;
   }

   return null;
}

@PublishedApi
internal inline fun <T> unsafeFlow(crossinline block: (FlowCollector<T>, Continuation<Unit>) -> Any?): Flow<T> {
   return new 1(block);
}

fun `checkContext$lambda$0`(`$this_checkContext`: SafeCollector, count: Int, element: CoroutineContext.Element): Int {
   val key: CoroutineContext.Key = element.getKey();
   val collectElement: CoroutineContext.Element = `$this_checkContext`.collectContext.get(key);
   if (key != Job.Key) {
      return if (element != collectElement) Integer.MIN_VALUE else count + 1;
   } else {
      val collectJob: Job = collectElement as Job;
      val emissionParentJob: Job = transitiveCoroutineParent(element as Job, collectJob);
      if (emissionParentJob != collectJob) {
         throw new IllegalStateException(
            ("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of $emissionParentJob, expected child of $collectJob.\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'")
               .toString()
         );
      } else {
         return if (collectJob == null) count else count + 1;
      }
   }
}
