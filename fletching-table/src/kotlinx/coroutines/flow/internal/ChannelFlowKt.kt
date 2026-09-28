@file:SourceDebugExtension(["SMAP\nChannelFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChannelFlow.kt\nkotlinx/coroutines/flow/internal/ChannelFlowKt\n+ 2 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n*L\n1#1,241:1\n91#2,5:242\n*S KotlinDebug\n*F\n+ 1 ChannelFlow.kt\nkotlinx/coroutines/flow/internal/ChannelFlowKt\n*L\n222#1:242,5\n*E\n"])

package kotlinx.coroutines.flow.internal

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.internal.ThreadContextKt

internal fun <T> Flow<T>.asChannelFlow(): ChannelFlow<T> {
   var var10000: ChannelFlow = `$this$asChannelFlow` as? ChannelFlow;
   if ((`$this$asChannelFlow` as? ChannelFlow) == null) {
      var10000 = new ChannelFlowOperatorImpl(`$this$asChannelFlow`, null, 0, null, 14, null);
   }

   return var10000;
}

private fun <T> FlowCollector<T>.withUndispatchedContextCollector(emitContext: CoroutineContext): FlowCollector<T> {
   return (FlowCollector<T>)(if (`$this$withUndispatchedContextCollector` !is SendingCollector && `$this$withUndispatchedContextCollector` !is NopCollector)
      new UndispatchedContextCollector(`$this$withUndispatchedContextCollector`, emitContext)
      else
      `$this$withUndispatchedContextCollector`);
}

internal suspend fun <T, V> withContextUndispatched(newContext: CoroutineContext, value: V, countOrElement: Any = ..., block: (V, Continuation<T>) -> Any?): T {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
   //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
   //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
   //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
   //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
   //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
   //   at org.jetbrains.java.decompiler.util.collections.ListStack.pop(ListStack.java:31)
   //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processBlock(ExprProcessor.java:441)
   //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:134)
   //
   // Bytecode:
   // 00: aload 4
   // 02: astore 5
   // 04: bipush 0
   // 05: istore 6
   // 07: bipush 0
   // 08: istore 7
   // 0a: aload 0
   // 0b: aload 2
   // 0c: invokestatic kotlinx/coroutines/internal/ThreadContextKt.updateThreadContext (Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)Ljava/lang/Object;
   // 0f: astore 8
   // 11: nop
   // 12: bipush 0
   // 13: istore 9
   // 15: aload 3
   // 16: astore 10
   // 18: new kotlinx/coroutines/flow/internal/StackFrameContinuation
   // 1b: dup
   // 1c: aload 5
   // 1e: aload 0
   // 1f: invokespecial kotlinx/coroutines/flow/internal/StackFrameContinuation.<init> (Lkotlin/coroutines/Continuation;Lkotlin/coroutines/CoroutineContext;)V
   // 22: checkcast kotlin/coroutines/Continuation
   // 25: astore 11
   // 27: aload 10
   // 29: instanceof kotlin/coroutines/jvm/internal/BaseContinuationImpl
   // 2c: ifne 3a
   // 2f: aload 10
   // 31: aload 1
   // 32: aload 11
   // 34: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.wrapWithContinuationImpl (Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 37: goto 4b
   // 3a: aload 10
   // 3c: bipush 2
   // 3d: invokestatic kotlin/jvm/internal/TypeIntrinsics.beforeCheckcastToFunctionOfArity (Ljava/lang/Object;I)Ljava/lang/Object;
   // 40: checkcast kotlin/jvm/functions/Function2
   // 43: aload 1
   // 44: aload 11
   // 46: invokeinterface kotlin/jvm/functions/Function2.invoke (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; 3
   // 4b: nop
   // 4c: astore 12
   // 4e: aload 0
   // 4f: aload 8
   // 51: invokestatic kotlinx/coroutines/internal/ThreadContextKt.restoreThreadContext (Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V
   // 54: aload 12
   // 56: goto 64
   // 59: astore 13
   // 5b: aload 0
   // 5c: aload 8
   // 5e: invokestatic kotlinx/coroutines/internal/ThreadContextKt.restoreThreadContext (Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V
   // 61: aload 13
   // 63: athrow
   // 64: nop
   // 65: dup
   // 66: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 69: if_acmpne 71
   // 6c: aload 4
   // 6e: invokestatic kotlin/coroutines/jvm/internal/DebugProbesKt.probeCoroutineSuspended (Lkotlin/coroutines/Continuation;)V
   // 71: areturn
}

@JvmSynthetic
fun `withContextUndispatched$default`(var0: CoroutineContext, var1: Any, var2: Any, var3: Function2, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 4) != 0) {
      var2 = ThreadContextKt.threadContextElements(var0);
   }

   return withContextUndispatched(var0, var1, var2, var3, var4);
}

@JvmSynthetic
fun `access$withUndispatchedContextCollector`(`$receiver`: FlowCollector, emitContext: CoroutineContext): FlowCollector {
   return withUndispatchedContextCollector(`$receiver`, emitContext);
}
