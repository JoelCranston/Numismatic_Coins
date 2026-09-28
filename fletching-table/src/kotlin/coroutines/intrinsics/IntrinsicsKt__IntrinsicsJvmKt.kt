package kotlin.coroutines.intrinsics

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted..inlined.createCoroutineFromSuspendFunction.IntrinsicsKt__IntrinsicsJvmKt.1
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted..inlined.createCoroutineFromSuspendFunction.IntrinsicsKt__IntrinsicsJvmKt.2
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted..inlined.createCoroutineFromSuspendFunction.IntrinsicsKt__IntrinsicsJvmKt.3
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted..inlined.createCoroutineFromSuspendFunction.IntrinsicsKt__IntrinsicsJvmKt.4
import kotlin.coroutines.jvm.internal.BaseContinuationImpl
import kotlin.coroutines.jvm.internal.ContinuationImpl
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.internal.InlineOnly
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics

@SourceDebugExtension(["SMAP\nIntrinsicsJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntrinsicsJvm.kt\nkotlin/coroutines/intrinsics/IntrinsicsKt__IntrinsicsJvmKt\n*L\n1#1,269:1\n204#1,4:270\n225#1:274\n204#1,4:275\n225#1:279\n*S KotlinDebug\n*F\n+ 1 IntrinsicsJvm.kt\nkotlin/coroutines/intrinsics/IntrinsicsKt__IntrinsicsJvmKt\n*L\n130#1:270,4\n130#1:274\n165#1:275,4\n165#1:279\n*E\n"])
internal class IntrinsicsKt__IntrinsicsJvmKt {
   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun <T> ((Continuation<T>) -> Any?).startCoroutineUninterceptedOrReturn(completion: Continuation<T>): Any? {
      return if (`$this$startCoroutineUninterceptedOrReturn` !is BaseContinuationImpl)
         IntrinsicsKt.wrapWithContinuationImpl(`$this$startCoroutineUninterceptedOrReturn`, completion)
         else
         (TypeIntrinsics.beforeCheckcastToFunctionOfArity(`$this$startCoroutineUninterceptedOrReturn`, 1) as Function1).invoke(completion);
   }

   @PublishedApi
   @JvmStatic
   internal fun <T> ((Continuation<T>) -> Any?).wrapWithContinuationImpl(completion: Continuation<T>): Any? {
      return (TypeIntrinsics.beforeCheckcastToFunctionOfArity(`$this$wrapWithContinuationImpl`, 1) as Function1)
         .invoke(createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(DebugProbesKt.probeCoroutineCreated(completion)));
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun <R, T> ((R, Continuation<T>) -> Any?).startCoroutineUninterceptedOrReturn(receiver: R, completion: Continuation<T>): Any? {
      return if (`$this$startCoroutineUninterceptedOrReturn` !is BaseContinuationImpl)
         IntrinsicsKt.wrapWithContinuationImpl(`$this$startCoroutineUninterceptedOrReturn`, receiver, completion)
         else
         (TypeIntrinsics.beforeCheckcastToFunctionOfArity(`$this$startCoroutineUninterceptedOrReturn`, 2) as Function2).invoke(receiver, completion);
   }

   @PublishedApi
   @JvmStatic
   internal fun <R, T> ((R, Continuation<T>) -> Any?).wrapWithContinuationImpl(receiver: R, completion: Continuation<T>): Any? {
      return (TypeIntrinsics.beforeCheckcastToFunctionOfArity(`$this$wrapWithContinuationImpl`, 2) as Function2)
         .invoke(receiver, createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(DebugProbesKt.probeCoroutineCreated(completion)));
   }

   @InlineOnly
   @JvmStatic
   internal inline fun <R, P, T> ((R, P, Continuation<T>) -> Any?).startCoroutineUninterceptedOrReturn(receiver: R, param: P, completion: Continuation<T>): Any? {
      return if (`$this$startCoroutineUninterceptedOrReturn` !is BaseContinuationImpl)
         IntrinsicsKt.wrapWithContinuationImpl(`$this$startCoroutineUninterceptedOrReturn`, receiver, param, completion)
         else
         (TypeIntrinsics.beforeCheckcastToFunctionOfArity(`$this$startCoroutineUninterceptedOrReturn`, 3) as Function3).invoke(receiver, param, completion);
   }

   @PublishedApi
   @JvmStatic
   internal fun <R, P, T> ((R, P, Continuation<T>) -> Any?).wrapWithContinuationImpl(receiver: R, param: P, completion: Continuation<T>): Any? {
      return (TypeIntrinsics.beforeCheckcastToFunctionOfArity(`$this$wrapWithContinuationImpl`, 3) as Function3)
         .invoke(receiver, param, createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(DebugProbesKt.probeCoroutineCreated(completion)));
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun <T> ((Continuation<T>) -> Any?).createCoroutineUnintercepted(completion: Continuation<T>): Continuation<Unit> {
      val probeCompletion: Continuation = DebugProbesKt.probeCoroutineCreated(completion);
      val var10000: Continuation;
      if (`$this$createCoroutineUnintercepted` is BaseContinuationImpl) {
         var10000 = (`$this$createCoroutineUnintercepted` as BaseContinuationImpl).create(probeCompletion);
      } else {
         val `context$iv`: CoroutineContext = probeCompletion.getContext();
         var10000 = if (`context$iv` === EmptyCoroutineContext.INSTANCE)
            new 1(probeCompletion, `$this$createCoroutineUnintercepted`)
            else
            new 2(probeCompletion, `context$iv`, `$this$createCoroutineUnintercepted`);
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun <R, T> ((R, Continuation<T>) -> Any?).createCoroutineUnintercepted(receiver: R, completion: Continuation<T>): Continuation<Unit> {
      val probeCompletion: Continuation = DebugProbesKt.probeCoroutineCreated(completion);
      val var10000: Continuation;
      if (`$this$createCoroutineUnintercepted` is BaseContinuationImpl) {
         var10000 = (`$this$createCoroutineUnintercepted` as BaseContinuationImpl).create(receiver, probeCompletion);
      } else {
         val `context$iv`: CoroutineContext = probeCompletion.getContext();
         var10000 = if (`context$iv` === EmptyCoroutineContext.INSTANCE)
            new 3(probeCompletion, `$this$createCoroutineUnintercepted`, receiver)
            else
            new 4(probeCompletion, `context$iv`, `$this$createCoroutineUnintercepted`, receiver);
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun <T> Continuation<T>.intercepted(): Continuation<T> {
      val var10000: ContinuationImpl = `$this$intercepted` as? ContinuationImpl;
      if ((`$this$intercepted` as? ContinuationImpl) != null) {
         val var1: Continuation = var10000.intercepted();
         if (var1 != null) {
            return var1;
         }
      }

      return `$this$intercepted`;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   private inline fun <T> createCoroutineFromSuspendFunction(completion: Continuation<T>, crossinline block: (Continuation<T>) -> Any?): Continuation<Unit> {
      val context: CoroutineContext = completion.getContext();
      return (Continuation<Unit>)(if (context === EmptyCoroutineContext.INSTANCE)
         new kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineFromSuspendFunction.1(completion, block)
         else
         new kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineFromSuspendFunction.2(completion, context, block));
   }

   @JvmStatic
   private fun <T> createSimpleCoroutineForSuspendFunction(completion: Continuation<T>): Continuation<T> {
      val context: CoroutineContext = completion.getContext();
      return (Continuation<T>)(if (context === EmptyCoroutineContext.INSTANCE)
         new kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createSimpleCoroutineForSuspendFunction.1(completion)
         else
         new kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createSimpleCoroutineForSuspendFunction.2(completion, context));
   }

   open fun IntrinsicsKt__IntrinsicsJvmKt() {
   }
}
