package kotlinx.coroutines.scheduling

import java.util.concurrent.TimeUnit
import kotlinx.coroutines.internal.SystemPropsKt

internal final val DEFAULT_SCHEDULER_NAME: String = SystemPropsKt.systemProp("kotlinx.coroutines.scheduler.default.name", "DefaultDispatcher")
internal final val WORK_STEALING_TIME_RESOLUTION_NS: Long =
   SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 0L, 0L, 12, null)
   internal final val CORE_POOL_SIZE: Int =
   SystemPropsKt.systemProp$default(
      "kotlinx.coroutines.scheduler.core.pool.size", RangesKt.coerceAtLeast(SystemPropsKt.getAVAILABLE_PROCESSORS(), 2), 1, 0, 8, null
   )
   internal final val MAX_POOL_SIZE: Int = SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4, null)
internal final val IDLE_WORKER_KEEP_ALIVE_NS: Long =
   TimeUnit.SECONDS.toNanos(SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, null))

internal final var schedulerTimeSource: SchedulerTimeSource = NanoTimeSource.INSTANCE as SchedulerTimeSource
   private set

internal const val NonBlockingContext: Boolean = false
internal const val BlockingContext: Boolean = true

internal final val isBlocking: Boolean
   internal final inline get() {
      return `$this$isBlocking`.taskContext;
   }


private fun taskContextString(taskContext: Boolean): String {
   return if (taskContext) "Blocking" else "Non-blocking";
}

internal fun Runnable.asTask(submissionTime: Long, taskContext: Boolean): Task {
   return new TaskImpl(`$this$asTask`, submissionTime, taskContext);
}

@JvmSynthetic
fun `access$taskContextString`(taskContext: Boolean): java.lang.String {
   return taskContextString(taskContext);
}
