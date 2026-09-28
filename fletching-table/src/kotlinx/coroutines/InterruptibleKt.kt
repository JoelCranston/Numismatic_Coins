package kotlinx.coroutines

import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.functions.Function0
import kotlinx.coroutines.InterruptibleKt.runInterruptible.2

private const val WORKING: Int = 0
private const val FINISHED: Int = 1
private const val INTERRUPTING: Int = 2
private const val INTERRUPTED: Int = 3

public suspend fun <T> runInterruptible(context: CoroutineContext = ..., block: () -> T): T {
   return BuildersKt.withContext(context, new 2(block, null), `$completion`);
}

@JvmSynthetic
fun `runInterruptible$default`(var0: CoroutineContext, var1: Function0, var2: Continuation, var3: Int, var4: Any): Any {
   if ((var3 and 1) != 0) {
      var0 = EmptyCoroutineContext.INSTANCE;
   }

   return runInterruptible(var0, var1, var2);
}

private fun <T> runInterruptibleInExpectedContext(coroutineContext: CoroutineContext, block: () -> T): T {
   try {
      label17: {
         val threadState: ThreadState = new ThreadState();
         threadState.setup(JobKt.getJob(coroutineContext));

         try {
            val e: Any = block.invoke();
         } catch (var5: java.lang.Throwable) {
            threadState.clearInterrupt();
         }

         threadState.clearInterrupt();
      }
   } catch (var6: InterruptedException) {
      throw new CancellationException("Blocking call was interrupted due to parent cancellation").initCause(var6);
   }
}

@JvmSynthetic
fun `access$runInterruptibleInExpectedContext`(coroutineContext: CoroutineContext, block: Function0): Any {
   return runInterruptibleInExpectedContext(coroutineContext, block);
}
