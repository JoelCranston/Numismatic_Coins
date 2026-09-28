@file:SourceDebugExtension(["SMAP\nFuture.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Future.kt\nkotlinx/coroutines/future/FutureKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,208:1\n1#2:209\n426#3,11:210\n*S KotlinDebug\n*F\n+ 1 Future.kt\nkotlinx/coroutines/future/FutureKt\n*L\n168#1:210,11\n*E\n"])

package kotlinx.coroutines.future

import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionException
import java.util.concurrent.CompletionStage
import java.util.concurrent.ExecutionException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellableContinuationImpl
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CompletableDeferredKt
import kotlinx.coroutines.CoroutineContextKt
import kotlinx.coroutines.CoroutineExceptionHandlerKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.future.FutureKt.await.2.1

public fun <T> CoroutineScope.future(
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   start: CoroutineStart = CoroutineStart.DEFAULT,
   block: (CoroutineScope, Continuation<T>) -> Any?
): CompletableFuture<T> {
   if (start.isLazy()) {
      throw new IllegalArgumentException(("$start start is not supported").toString());
   } else {
      val newContext: CoroutineContext = CoroutineContextKt.newCoroutineContext(`$this$future`, context);
      val future: CompletableFuture = new CompletableFuture();
      val coroutine: CompletableFutureCoroutine = new CompletableFutureCoroutine(newContext, future);
      future.handle(coroutine);
      coroutine.start(start, coroutine, block);
      return future;
   }
}

@JvmSynthetic
fun `future$default`(var0: CoroutineScope, var1: CoroutineContext, var2: CoroutineStart, var3: Function2, var4: Int, var5: Any): CompletableFuture {
   if ((var4 and 1) != 0) {
      var1 = EmptyCoroutineContext.INSTANCE;
   }

   if ((var4 and 2) != 0) {
      var2 = CoroutineStart.DEFAULT;
   }

   return future(var0, var1, var2, var3);
}

public fun <T> Deferred<T>.asCompletableFuture(): CompletableFuture<T> {
   val future: CompletableFuture = new CompletableFuture();
   setupCancellation(`$this$asCompletableFuture`, future);
   `$this$asCompletableFuture`.invokeOnCompletion(FutureKt::asCompletableFuture$lambda$1);
   return future;
}

public fun Job.asCompletableFuture(): CompletableFuture<Unit> {
   val future: CompletableFuture = new CompletableFuture();
   setupCancellation(`$this$asCompletableFuture`, future);
   `$this$asCompletableFuture`.invokeOnCompletion(FutureKt::asCompletableFuture$lambda$2);
   return future;
}

private fun Job.setupCancellation(future: CompletableFuture<*>) {
   future.handle(FutureKt::setupCancellation$lambda$3);
}

public fun <T> CompletionStage<T>.asDeferred(): Deferred<T> {
   val future: CompletableFuture = `$this$asDeferred`.toCompletableFuture();
   if (future.isDone()) {
      var var9: Deferred;
      try {
         var9 = CompletableDeferredKt.CompletableDeferred(future.get());
      } catch (var8: java.lang.Throwable) {
         var var10: java.lang.Throwable;
         label21: {
            val var10000: ExecutionException = var8 as? ExecutionException;
            if ((var8 as? ExecutionException) != null) {
               var10 = var10000.getCause();
               if (var10 != null) {
                  break label21;
               }
            }

            var10 = var8;
         }

         val var5: CompletableDeferred = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
         var5.completeExceptionally(var10);
         var9 = var5;
      }

      return var9;
   } else {
      val result: CompletableDeferred = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
      `$this$asDeferred`.handle(FutureKt::asDeferred$lambda$6);
      JobKt.invokeOnCompletion$default(result, false, new CancelFutureOnCompletion(future), 1, null);
      return result;
   }
}

public suspend fun <T> CompletionStage<T>.await(): T {
   val future: CompletableFuture = `$this$await`.toCompletableFuture();
   if (future.isDone()) {
      try {
         return future.get();
      } catch (var10: ExecutionException) {
         var var11: java.lang.Throwable = var10.getCause();
         if (var11 == null) {
            var11 = var10;
         }

         throw var11;
      }
   } else {
      val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
      `cancellable$iv`.initCancellability();
      val cont: CancellableContinuation = `cancellable$iv`;
      val consumer: ContinuationHandler = new ContinuationHandler(`cancellable$iv`);
      `$this$await`.handle(consumer);
      cont.invokeOnCancellation(new 1(future, consumer));
      val var10000: Any = `cancellable$iv`.getResult();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return var10000;
   }
}

fun `asCompletableFuture$lambda$1`(`$future`: CompletableFuture, `$this_asCompletableFuture`: Deferred, it: java.lang.Throwable): Unit {
   try {
      `$future`.complete(`$this_asCompletableFuture`.getCompleted());
   } catch (var4: java.lang.Throwable) {
      `$future`.completeExceptionally(var4);
   }

   return Unit.INSTANCE;
}

fun `asCompletableFuture$lambda$2`(`$future`: CompletableFuture, cause: java.lang.Throwable): Unit {
   if (cause == null) {
      `$future`.complete(Unit.INSTANCE);
   } else {
      `$future`.completeExceptionally(cause);
   }

   return Unit.INSTANCE;
}

fun `setupCancellation$lambda$3`(`$tmp0`: Function2, p0: Any, p1: java.lang.Throwable): Unit {
   return `$tmp0`.invoke(p0, p1) as Unit;
}

fun `asDeferred$lambda$5`(`$result`: CompletableDeferred, value: Any, exception: java.lang.Throwable): Any {
   var var3: Any;
   try {
      val var10000: Boolean;
      if (exception == null) {
         var10000 = `$result`.complete(value);
      } else {
         var var6: java.lang.Throwable;
         label25: {
            val var10001: CompletionException = exception as? CompletionException;
            if ((exception as? CompletionException) != null) {
               var6 = var10001.getCause();
               if (var6 != null) {
                  break label25;
               }
            }

            var6 = exception;
         }

         var10000 = `$result`.completeExceptionally(var6);
      }

      var3 = var10000;
   } catch (var5: java.lang.Throwable) {
      CoroutineExceptionHandlerKt.handleCoroutineException(EmptyCoroutineContext.INSTANCE, var5);
      var3 = Unit.INSTANCE;
   }

   return var3;
}

fun `asDeferred$lambda$6`(`$tmp0`: Function2, p0: Any, p1: java.lang.Throwable): Any {
   return `$tmp0`.invoke(p0, p1);
}
