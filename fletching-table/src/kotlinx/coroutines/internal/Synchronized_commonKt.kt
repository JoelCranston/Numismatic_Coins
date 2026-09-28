@file:SourceDebugExtension(["SMAP\nSynchronized.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 2 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n*L\n1#1,31:1\n16#2:32\n*S KotlinDebug\n*F\n+ 1 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n*L\n29#1:32\n*E\n"])

package kotlinx.coroutines.internal

import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
public inline fun <T> synchronized(lock: Any, block: () -> T): T {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var10000: Any;
   synchronized (lock) {
      val var5: Any = block.invoke();
      InlineMarker.finallyStart(1);
      InlineMarker.finallyEnd(1);
      var10000 = var5;
   }

   return (T)var10000;
}
