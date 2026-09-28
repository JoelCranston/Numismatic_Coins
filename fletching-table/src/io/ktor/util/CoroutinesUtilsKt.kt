@file:SourceDebugExtension(["SMAP\nCoroutinesUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutinesUtils.kt\nio/ktor/util/CoroutinesUtilsKt\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 3 CoroutineExceptionHandler.kt\nkotlinx/coroutines/CoroutineExceptionHandlerKt\n*L\n1#1,33:1\n1321#2,2:34\n47#3,4:36\n*S KotlinDebug\n*F\n+ 1 CoroutinesUtils.kt\nio/ktor/util/CoroutinesUtilsKt\n*L\n18#1:34,2\n32#1:36,4\n*E\n"])

package io.ktor.util

import io.ktor.util.CoroutinesUtilsKt.SilentSupervisor..inlined.CoroutineExceptionHandler.1
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorKt

public fun Job.printDebugTree(offset: Int = 0) {
   System.out.println("${StringsKt.repeat(" ", offset)}$`$this$printDebugTree`");

   val `$this$forEach$iv`: Sequence;
   for (Object element$iv : $this$forEach$iv) {
      printDebugTree(`element$iv` as Job, offset + 2);
   }

   if (offset == 0) {
      System.out.println();
   }
}

@JvmSynthetic
fun `printDebugTree$default`(var0: Job, var1: Int, var2: Int, var3: Any) {
   if ((var2 and 1) != 0) {
      var1 = 0;
   }

   printDebugTree(var0, var1);
}

public fun SilentSupervisor(parent: Job? = null): CoroutineContext {
   return SupervisorKt.SupervisorJob(parent).plus(new 1(CoroutineExceptionHandler.Key));
}

@JvmSynthetic
fun `SilentSupervisor$default`(var0: Job, var1: Int, var2: Any): CoroutineContext {
   if ((var1 and 1) != 0) {
      var0 = null;
   }

   return SilentSupervisor(var0);
}
