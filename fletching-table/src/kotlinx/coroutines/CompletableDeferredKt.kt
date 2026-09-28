@file:SourceDebugExtension(["SMAP\nCompletableDeferred.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CompletableDeferred.kt\nkotlinx/coroutines/CompletableDeferredKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,91:1\n1#2:92\n*E\n"])

package kotlinx.coroutines

import kotlin.jvm.internal.SourceDebugExtension

public fun <T> CompletableDeferred<T>.completeWith(result: Result<T>): Boolean {
   val var10000: java.lang.Throwable = Result.exceptionOrNull-impl(result);
   return if (var10000 == null) `$this$completeWith`.complete(result) else `$this$completeWith`.completeExceptionally(var10000);
}

public fun <T> CompletableDeferred(parent: Job? = null): CompletableDeferred<T> {
   return new CompletableDeferredImpl(parent);
}

@JvmSynthetic
fun `CompletableDeferred$default`(var0: Job, var1: Int, var2: Any): CompletableDeferred {
   if ((var1 and 1) != 0) {
      var0 = null;
   }

   return CompletableDeferred(var0);
}

public fun <T> CompletableDeferred(value: T): CompletableDeferred<T> {
   val var1: CompletableDeferredImpl = new CompletableDeferredImpl(null);
   var1.complete(value);
   return var1;
}
