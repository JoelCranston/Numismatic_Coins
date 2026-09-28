@file:SourceDebugExtension(["SMAP\nWorkQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkQueue.kt\nkotlinx/coroutines/scheduling/WorkQueueKt\n+ 2 Tasks.kt\nkotlinx/coroutines/scheduling/TasksKt\n*L\n1#1,251:1\n77#2:252\n*S KotlinDebug\n*F\n+ 1 WorkQueue.kt\nkotlinx/coroutines/scheduling/WorkQueueKt\n*L\n21#1:252\n*E\n"])

package kotlinx.coroutines.scheduling

import kotlin.jvm.internal.SourceDebugExtension

internal const val BUFFER_CAPACITY_BASE: Int = 7
internal const val BUFFER_CAPACITY: Int = 128
internal const val MASK: Int = 127
internal const val TASK_STOLEN: Long = -1L
internal const val NOTHING_TO_STEAL: Long = -2L
internal const val STEAL_ANY: Int = 3
internal const val STEAL_CPU_ONLY: Int = 2
internal const val STEAL_BLOCKING_ONLY: Int = 1

internal final val maskForStealingMode: Int
   internal final inline get() {
      return if (`$this$maskForStealingMode`.taskContext) 1 else 2;
   }

