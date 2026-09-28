package kotlinx.coroutines.flow

import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.internal.ChannelFlowKt

@JvmSynthetic
internal class FlowKt__ChannelsKt {
   @JvmStatic
   public suspend fun <T> FlowCollector<T>.emitAll(channel: ReceiveChannel<T>) {
      val var10000: Any = emitAllImpl$FlowKt__ChannelsKt(`$this$emitAll`, channel, true, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   @JvmStatic
   private suspend fun <T> FlowCollector<T>.emitAllImpl(channel: ReceiveChannel<T>, consume: Boolean) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
      //
      // Bytecode:
      // 000: aload 3
      // 001: instanceof kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1
      // 004: ifeq 027
      // 007: aload 3
      // 008: checkcast kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1
      // 00b: astore 8
      // 00d: aload 8
      // 00f: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 8
      // 01a: dup
      // 01b: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.label I
      // 024: goto 031
      // 027: new kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1
      // 02a: dup
      // 02b: aload 3
      // 02c: invokespecial kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.<init> (Lkotlin/coroutines/Continuation;)V
      // 02f: astore 8
      // 031: aload 8
      // 033: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.result Ljava/lang/Object;
      // 036: astore 7
      // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03b: astore 9
      // 03d: aload 8
      // 03f: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.label I
      // 042: tableswitch 299 0 2 26 96 209
      // 05c: aload 7
      // 05e: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 061: aload 0
      // 062: invokestatic kotlinx/coroutines/flow/FlowKt.ensureActive (Lkotlinx/coroutines/flow/FlowCollector;)V
      // 065: aconst_null
      // 066: astore 4
      // 068: nop
      // 069: aload 1
      // 06a: invokeinterface kotlinx/coroutines/channels/ReceiveChannel.iterator ()Lkotlinx/coroutines/channels/ChannelIterator; 1
      // 06f: astore 5
      // 071: aload 5
      // 073: aload 8
      // 075: aload 8
      // 077: aload 0
      // 078: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$0 Ljava/lang/Object;
      // 07b: aload 8
      // 07d: aload 1
      // 07e: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$1 Ljava/lang/Object;
      // 081: aload 8
      // 083: aload 5
      // 085: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$2 Ljava/lang/Object;
      // 088: aload 8
      // 08a: iload 2
      // 08b: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.Z$0 Z
      // 08e: aload 8
      // 090: bipush 1
      // 091: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.label I
      // 094: invokeinterface kotlinx/coroutines/channels/ChannelIterator.hasNext (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 099: dup
      // 09a: aload 9
      // 09c: if_acmpne 0cf
      // 09f: aload 9
      // 0a1: areturn
      // 0a2: aload 8
      // 0a4: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.Z$0 Z
      // 0a7: istore 2
      // 0a8: aload 8
      // 0aa: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$2 Ljava/lang/Object;
      // 0ad: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 0b0: astore 5
      // 0b2: aconst_null
      // 0b3: astore 4
      // 0b5: aload 8
      // 0b7: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$1 Ljava/lang/Object;
      // 0ba: checkcast kotlinx/coroutines/channels/ReceiveChannel
      // 0bd: astore 1
      // 0be: aload 8
      // 0c0: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$0 Ljava/lang/Object;
      // 0c3: checkcast kotlinx/coroutines/flow/FlowCollector
      // 0c6: astore 0
      // 0c7: nop
      // 0c8: aload 7
      // 0ca: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0cd: aload 7
      // 0cf: checkcast java/lang/Boolean
      // 0d2: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 0d5: ifeq 144
      // 0d8: aload 5
      // 0da: invokeinterface kotlinx/coroutines/channels/ChannelIterator.next ()Ljava/lang/Object; 1
      // 0df: astore 6
      // 0e1: aload 0
      // 0e2: aload 6
      // 0e4: aload 8
      // 0e6: aload 8
      // 0e8: aload 0
      // 0e9: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$0 Ljava/lang/Object;
      // 0ec: aload 8
      // 0ee: aload 1
      // 0ef: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$1 Ljava/lang/Object;
      // 0f2: aload 8
      // 0f4: aload 5
      // 0f6: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$2 Ljava/lang/Object;
      // 0f9: aload 8
      // 0fb: iload 2
      // 0fc: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.Z$0 Z
      // 0ff: aload 8
      // 101: bipush 2
      // 102: putfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.label I
      // 105: invokeinterface kotlinx/coroutines/flow/FlowCollector.emit (Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 3
      // 10a: dup
      // 10b: aload 9
      // 10d: if_acmpne 140
      // 110: aload 9
      // 112: areturn
      // 113: aload 8
      // 115: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.Z$0 Z
      // 118: istore 2
      // 119: aload 8
      // 11b: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$2 Ljava/lang/Object;
      // 11e: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 121: astore 5
      // 123: aconst_null
      // 124: astore 4
      // 126: aload 8
      // 128: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$1 Ljava/lang/Object;
      // 12b: checkcast kotlinx/coroutines/channels/ReceiveChannel
      // 12e: astore 1
      // 12f: aload 8
      // 131: getfield kotlinx/coroutines/flow/FlowKt__ChannelsKt$emitAllImpl$1.L$0 Ljava/lang/Object;
      // 134: checkcast kotlinx/coroutines/flow/FlowCollector
      // 137: astore 0
      // 138: nop
      // 139: aload 7
      // 13b: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 13e: aload 7
      // 140: pop
      // 141: goto 071
      // 144: iload 2
      // 145: ifeq 14e
      // 148: aload 1
      // 149: aload 4
      // 14b: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 14e: goto 169
      // 151: astore 5
      // 153: aload 5
      // 155: astore 4
      // 157: aload 5
      // 159: athrow
      // 15a: astore 5
      // 15c: iload 2
      // 15d: ifeq 166
      // 160: aload 1
      // 161: aload 4
      // 163: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 166: aload 5
      // 168: athrow
      // 169: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 16c: areturn
      // 16d: new java/lang/IllegalStateException
      // 170: dup
      // 171: ldc "call to 'resume' before 'invoke' with coroutine"
      // 173: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 176: athrow
   }

   @JvmStatic
   public fun <T> ReceiveChannel<T>.receiveAsFlow(): Flow<T> {
      return new ChannelAsFlow(`$this$receiveAsFlow`, false, null, 0, null, 28, null);
   }

   @JvmStatic
   public fun <T> ReceiveChannel<T>.consumeAsFlow(): Flow<T> {
      return new ChannelAsFlow(`$this$consumeAsFlow`, true, null, 0, null, 28, null);
   }

   @JvmStatic
   public fun <T> Flow<T>.produceIn(scope: CoroutineScope): ReceiveChannel<T> {
      return ChannelFlowKt.asChannelFlow(`$this$produceIn`).produceImpl(scope);
   }
}
