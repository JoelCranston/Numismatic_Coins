@file:SourceDebugExtension(["SMAP\nLimitedDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcherKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,154:1\n1#2:155\n*E\n"])

package kotlinx.coroutines.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CoroutineDispatcher

internal fun Int.checkParallelism() {
   if (`$this$checkParallelism` < 1) {
      throw new IllegalArgumentException(("Expected positive parallelism level, but got $`$this$checkParallelism`").toString());
   }
}

internal fun CoroutineDispatcher.namedOrThis(name: String?): CoroutineDispatcher {
   return if (name != null) new NamedDispatcher(`$this$namedOrThis`, name) else `$this$namedOrThis`;
}
