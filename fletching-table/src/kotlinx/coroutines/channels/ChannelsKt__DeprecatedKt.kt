package kotlinx.coroutines.channels

import java.util.LinkedHashSet
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filter.1
import kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.2

@SourceDebugExtension(["SMAP\nDeprecated.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Deprecated.kt\nkotlinx/coroutines/channels/ChannelsKt__DeprecatedKt\n+ 2 Channels.common.kt\nkotlinx/coroutines/channels/ChannelsKt__Channels_commonKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,509:1\n24#1,5:510\n94#2,8:515\n94#2,8:523\n94#2,8:531\n94#2,8:539\n160#2:547\n94#2,3:548\n161#2:551\n101#2:552\n162#2:553\n97#2,3:554\n94#2,8:557\n160#2:565\n94#2,3:566\n161#2,2:569\n101#2:571\n97#2,3:572\n94#2,8:575\n94#2,8:583\n94#2,8:591\n160#2:599\n94#2,3:600\n161#2,2:603\n101#2:605\n97#2,3:606\n160#2:609\n94#2,3:610\n161#2,2:613\n101#2:615\n97#2,3:616\n160#2:619\n94#2,3:620\n161#2,2:623\n101#2:625\n97#2,3:626\n160#2:629\n94#2,3:630\n161#2,2:633\n101#2:635\n97#2,3:636\n160#2:639\n94#2,3:640\n161#2,2:643\n101#2:645\n97#2,3:646\n94#2,8:649\n160#2:657\n94#2,3:658\n161#2,2:661\n101#2:663\n97#2,3:664\n94#2,8:667\n94#2,8:675\n94#2,8:683\n1#3:691\n*S KotlinDebug\n*F\n+ 1 Deprecated.kt\nkotlinx/coroutines/channels/ChannelsKt__DeprecatedKt\n*L\n40#1:510,5\n64#1:515,8\n79#1:523,8\n93#1:531,8\n103#1:539,8\n114#1:547\n114#1:548,3\n114#1:551\n114#1:552\n114#1:553\n114#1:554,3\n125#1:557,8\n140#1:565\n140#1:566,3\n140#1:569,2\n140#1:571\n140#1:572,3\n151#1:575,8\n164#1:583,8\n177#1:591,8\n262#1:599\n262#1:600,3\n262#1:603,2\n262#1:605\n262#1:606,3\n271#1:609\n271#1:610,3\n271#1:613,2\n271#1:615\n271#1:616,3\n307#1:619\n307#1:620,3\n307#1:623,2\n307#1:625\n307#1:626,3\n315#1:629\n315#1:630,3\n315#1:633,2\n315#1:635\n315#1:636,3\n328#1:639\n328#1:640,3\n328#1:643,2\n328#1:645\n328#1:646,3\n433#1:649,8\n441#1:657\n441#1:658,3\n441#1:661,2\n441#1:663\n441#1:664,3\n448#1:667,8\n462#1:675,8\n476#1:683,8\n*E\n"])
@JvmSynthetic
internal class ChannelsKt__DeprecatedKt {
   @Deprecated(message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported", level = DeprecationLevel.ERROR)
   @ObsoleteCoroutinesApi
   @JvmStatic
   public inline fun <E, R> BroadcastChannel<E>.consume(block: (ReceiveChannel<E>) -> R): R {
      label14: {
         val channel: ReceiveChannel = `$this$consume`.openSubscription();

         try {
            val var4: Any = block.invoke(channel);
         } catch (var6: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            ReceiveChannel.DefaultImpls.cancel$default(channel, null, 1, null);
            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         ReceiveChannel.DefaultImpls.cancel$default(channel, null, 1, null);
         InlineMarker.finallyEnd(1);
      }
   }

   @Deprecated(message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported", level = DeprecationLevel.ERROR)
   @JvmStatic
   public suspend inline fun <E> BroadcastChannel<E>.consumeEach(action: (E) -> Unit) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1057)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.insertSemaphore(FinallyProcessor.java:350)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:99)
      //
      // Bytecode:
      // 000: aload 2
      // 001: instanceof kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1
      // 004: ifeq 027
      // 007: aload 2
      // 008: checkcast kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1
      // 00b: astore 12
      // 00d: aload 12
      // 00f: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 12
      // 01a: dup
      // 01b: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.label I
      // 024: goto 031
      // 027: new kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1
      // 02a: dup
      // 02b: aload 2
      // 02c: invokespecial kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.<init> (Lkotlin/coroutines/Continuation;)V
      // 02f: astore 12
      // 031: aload 12
      // 033: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.result Ljava/lang/Object;
      // 036: astore 11
      // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03b: astore 13
      // 03d: aload 12
      // 03f: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.label I
      // 042: tableswitch 230 0 1 22 105
      // 058: aload 11
      // 05a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 05d: bipush 0
      // 05e: istore 3
      // 05f: aload 0
      // 060: astore 4
      // 062: bipush 0
      // 063: istore 5
      // 065: aload 4
      // 067: invokeinterface kotlinx/coroutines/channels/BroadcastChannel.openSubscription ()Lkotlinx/coroutines/channels/ReceiveChannel; 1
      // 06c: astore 6
      // 06e: nop
      // 06f: aload 6
      // 071: astore 7
      // 073: bipush 0
      // 074: istore 8
      // 076: aload 7
      // 078: invokeinterface kotlinx/coroutines/channels/ReceiveChannel.iterator ()Lkotlinx/coroutines/channels/ChannelIterator; 1
      // 07d: astore 9
      // 07f: aload 9
      // 081: aload 12
      // 083: aload 12
      // 085: aload 1
      // 086: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.L$0 Ljava/lang/Object;
      // 089: aload 12
      // 08b: aload 6
      // 08d: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.L$1 Ljava/lang/Object;
      // 090: aload 12
      // 092: aload 9
      // 094: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.L$2 Ljava/lang/Object;
      // 097: aload 12
      // 099: bipush 1
      // 09a: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.label I
      // 09d: invokeinterface kotlinx/coroutines/channels/ChannelIterator.hasNext (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 0a2: dup
      // 0a3: aload 13
      // 0a5: if_acmpne 0d8
      // 0a8: aload 13
      // 0aa: areturn
      // 0ab: bipush 0
      // 0ac: istore 3
      // 0ad: bipush 0
      // 0ae: istore 5
      // 0b0: bipush 0
      // 0b1: istore 8
      // 0b3: aload 12
      // 0b5: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.L$2 Ljava/lang/Object;
      // 0b8: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 0bb: astore 9
      // 0bd: aload 12
      // 0bf: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.L$1 Ljava/lang/Object;
      // 0c2: checkcast kotlinx/coroutines/channels/ReceiveChannel
      // 0c5: astore 6
      // 0c7: aload 12
      // 0c9: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$consumeEach$1.L$0 Ljava/lang/Object;
      // 0cc: checkcast kotlin/jvm/functions/Function1
      // 0cf: astore 1
      // 0d0: nop
      // 0d1: aload 11
      // 0d3: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0d6: aload 11
      // 0d8: checkcast java/lang/Boolean
      // 0db: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 0de: ifeq 0f6
      // 0e1: aload 9
      // 0e3: invokeinterface kotlinx/coroutines/channels/ChannelIterator.next ()Ljava/lang/Object; 1
      // 0e8: astore 10
      // 0ea: aload 1
      // 0eb: aload 10
      // 0ed: invokeinterface kotlin/jvm/functions/Function1.invoke (Ljava/lang/Object;)Ljava/lang/Object; 2
      // 0f2: pop
      // 0f3: goto 07f
      // 0f6: nop
      // 0f7: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 0fa: astore 7
      // 0fc: bipush 1
      // 0fd: invokestatic kotlin/jvm/internal/InlineMarker.finallyStart (I)V
      // 100: aload 6
      // 102: aconst_null
      // 103: bipush 1
      // 104: aconst_null
      // 105: invokestatic kotlinx/coroutines/channels/ReceiveChannel$DefaultImpls.cancel$default (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V
      // 108: bipush 1
      // 109: invokestatic kotlin/jvm/internal/InlineMarker.finallyEnd (I)V
      // 10c: goto 124
      // 10f: astore 8
      // 111: bipush 1
      // 112: invokestatic kotlin/jvm/internal/InlineMarker.finallyStart (I)V
      // 115: aload 6
      // 117: aconst_null
      // 118: bipush 1
      // 119: aconst_null
      // 11a: invokestatic kotlinx/coroutines/channels/ReceiveChannel$DefaultImpls.cancel$default (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V
      // 11d: bipush 1
      // 11e: invokestatic kotlin/jvm/internal/InlineMarker.finallyEnd (I)V
      // 121: aload 8
      // 123: athrow
      // 124: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 127: areturn
      // 128: new java/lang/IllegalStateException
      // 12b: dup
      // 12c: ldc "call to 'resume' before 'invoke' with coroutine"
      // 12e: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 131: athrow
   }

   /** @deprecated */
   @Deprecated(message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported", level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <E> BroadcastChannel<E>.`consumeEach$$forInline`(action: (E?) -> Unit, `$completion`: Continuation<? super Unit>): Any {
      label25: {
         val `channel$iv`: ReceiveChannel = `$this$consumeEach`.openSubscription();

         try {
            val var9: ChannelIterator = `channel$iv`.iterator();

            while (true) {
               InlineMarker.mark(3);
               InlineMarker.mark(0);
               val var10000: Any = var9.hasNext(null);
               InlineMarker.mark(1);
               if (!var10000 as java.lang.Boolean) {
                  break;
               }

               action.invoke(var9.next());
            }
         } catch (var11: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            ReceiveChannel.DefaultImpls.cancel$default(`channel$iv`, null, 1, null);
            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         ReceiveChannel.DefaultImpls.cancel$default(`channel$iv`, null, 1, null);
         InlineMarker.finallyEnd(1);
      }
   }

   @PublishedApi
   @JvmStatic
   internal fun consumesAll(vararg channels: ReceiveChannel<*>): (Throwable?) -> Unit {
      return ChannelsKt__DeprecatedKt::consumesAll$lambda$2$ChannelsKt__DeprecatedKt;
   }

   @PublishedApi
   @JvmStatic
   internal fun <E> ReceiveChannel<E>.filter(
      context: CoroutineContext = Dispatchers.getUnconfined() as CoroutineContext,
      predicate: (E, Continuation<Boolean>) -> Any?
   ): ReceiveChannel<E> {
      return ProduceKt.produce$default(
         GlobalScope.INSTANCE, context, 0, null, ChannelsKt.consumes(`$this$filter`), new 1(`$this$filter`, predicate, null), 6, null
      );
   }

   @PublishedApi
   @JvmStatic
   internal fun <E : Any> ReceiveChannel<E?>.filterNotNull(): ReceiveChannel<E> {
      val var10000: ReceiveChannel = ChannelsKt.filter$default(
         `$this$filterNotNull`, null, new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNull.1(null), 1, null
      );
      return var10000;
   }

   @PublishedApi
   @JvmStatic
   internal suspend fun <E, C : SendChannel<E>> ReceiveChannel<E>.toChannel(destination: C): C {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
      //
      // Bytecode:
      // 000: aload 2
      // 001: instanceof kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1
      // 004: ifeq 027
      // 007: aload 2
      // 008: checkcast kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1
      // 00b: astore 15
      // 00d: aload 15
      // 00f: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 15
      // 01a: dup
      // 01b: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.label I
      // 024: goto 031
      // 027: new kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1
      // 02a: dup
      // 02b: aload 2
      // 02c: invokespecial kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.<init> (Lkotlin/coroutines/Continuation;)V
      // 02f: astore 15
      // 031: aload 15
      // 033: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.result Ljava/lang/Object;
      // 036: astore 14
      // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03b: astore 16
      // 03d: aload 15
      // 03f: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.label I
      // 042: tableswitch 321 0 2 26 106 225
      // 05c: aload 14
      // 05e: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 061: aload 0
      // 062: astore 3
      // 063: bipush 0
      // 064: istore 4
      // 066: aload 3
      // 067: astore 5
      // 069: bipush 0
      // 06a: istore 6
      // 06c: aconst_null
      // 06d: astore 7
      // 06f: nop
      // 070: aload 5
      // 072: astore 8
      // 074: bipush 0
      // 075: istore 9
      // 077: aload 8
      // 079: invokeinterface kotlinx/coroutines/channels/ReceiveChannel.iterator ()Lkotlinx/coroutines/channels/ChannelIterator; 1
      // 07e: astore 10
      // 080: aload 10
      // 082: aload 15
      // 084: aload 15
      // 086: aload 1
      // 087: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$0 Ljava/lang/Object;
      // 08a: aload 15
      // 08c: aload 5
      // 08e: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$1 Ljava/lang/Object;
      // 091: aload 15
      // 093: aload 10
      // 095: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$2 Ljava/lang/Object;
      // 098: aload 15
      // 09a: bipush 1
      // 09b: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.label I
      // 09e: invokeinterface kotlinx/coroutines/channels/ChannelIterator.hasNext (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 0a3: dup
      // 0a4: aload 16
      // 0a6: if_acmpne 0dd
      // 0a9: aload 16
      // 0ab: areturn
      // 0ac: bipush 0
      // 0ad: istore 4
      // 0af: bipush 0
      // 0b0: istore 6
      // 0b2: bipush 0
      // 0b3: istore 9
      // 0b5: aload 15
      // 0b7: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$2 Ljava/lang/Object;
      // 0ba: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 0bd: astore 10
      // 0bf: aconst_null
      // 0c0: astore 7
      // 0c2: aload 15
      // 0c4: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$1 Ljava/lang/Object;
      // 0c7: checkcast kotlinx/coroutines/channels/ReceiveChannel
      // 0ca: astore 5
      // 0cc: aload 15
      // 0ce: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$0 Ljava/lang/Object;
      // 0d1: checkcast kotlinx/coroutines/channels/SendChannel
      // 0d4: astore 1
      // 0d5: nop
      // 0d6: aload 14
      // 0d8: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0db: aload 14
      // 0dd: checkcast java/lang/Boolean
      // 0e0: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 0e3: ifeq 15b
      // 0e6: aload 10
      // 0e8: invokeinterface kotlinx/coroutines/channels/ChannelIterator.next ()Ljava/lang/Object; 1
      // 0ed: astore 11
      // 0ef: aload 11
      // 0f1: astore 12
      // 0f3: bipush 0
      // 0f4: istore 13
      // 0f6: aload 1
      // 0f7: aload 12
      // 0f9: aload 15
      // 0fb: aload 15
      // 0fd: aload 1
      // 0fe: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$0 Ljava/lang/Object;
      // 101: aload 15
      // 103: aload 5
      // 105: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$1 Ljava/lang/Object;
      // 108: aload 15
      // 10a: aload 10
      // 10c: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$2 Ljava/lang/Object;
      // 10f: aload 15
      // 111: bipush 2
      // 112: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.label I
      // 115: invokeinterface kotlinx/coroutines/channels/SendChannel.send (Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 3
      // 11a: dup
      // 11b: aload 16
      // 11d: if_acmpne 157
      // 120: aload 16
      // 122: areturn
      // 123: bipush 0
      // 124: istore 4
      // 126: bipush 0
      // 127: istore 6
      // 129: bipush 0
      // 12a: istore 9
      // 12c: bipush 0
      // 12d: istore 13
      // 12f: aload 15
      // 131: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$2 Ljava/lang/Object;
      // 134: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 137: astore 10
      // 139: aconst_null
      // 13a: astore 7
      // 13c: aload 15
      // 13e: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$1 Ljava/lang/Object;
      // 141: checkcast kotlinx/coroutines/channels/ReceiveChannel
      // 144: astore 5
      // 146: aload 15
      // 148: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toChannel$1.L$0 Ljava/lang/Object;
      // 14b: checkcast kotlinx/coroutines/channels/SendChannel
      // 14e: astore 1
      // 14f: nop
      // 150: aload 14
      // 152: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 155: aload 14
      // 157: pop
      // 158: goto 080
      // 15b: nop
      // 15c: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 15f: astore 8
      // 161: aload 5
      // 163: aload 7
      // 165: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 168: goto 180
      // 16b: astore 9
      // 16d: aload 9
      // 16f: astore 7
      // 171: aload 9
      // 173: athrow
      // 174: astore 9
      // 176: aload 5
      // 178: aload 7
      // 17a: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 17d: aload 9
      // 17f: athrow
      // 180: nop
      // 181: aload 1
      // 182: areturn
      // 183: new java/lang/IllegalStateException
      // 186: dup
      // 187: ldc "call to 'resume' before 'invoke' with coroutine"
      // 189: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 18c: athrow
   }

   @PublishedApi
   @JvmStatic
   internal suspend fun <E, C : MutableCollection<in E>> ReceiveChannel<E>.toCollection(destination: C): C {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
      //
      // Bytecode:
      // 000: aload 2
      // 001: instanceof kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1
      // 004: ifeq 027
      // 007: aload 2
      // 008: checkcast kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1
      // 00b: astore 15
      // 00d: aload 15
      // 00f: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 15
      // 01a: dup
      // 01b: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.label I
      // 024: goto 031
      // 027: new kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1
      // 02a: dup
      // 02b: aload 2
      // 02c: invokespecial kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.<init> (Lkotlin/coroutines/Continuation;)V
      // 02f: astore 15
      // 031: aload 15
      // 033: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.result Ljava/lang/Object;
      // 036: astore 14
      // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03b: astore 16
      // 03d: aload 15
      // 03f: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.label I
      // 042: tableswitch 229 0 1 22 102
      // 058: aload 14
      // 05a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 05d: aload 0
      // 05e: astore 3
      // 05f: bipush 0
      // 060: istore 4
      // 062: aload 3
      // 063: astore 5
      // 065: bipush 0
      // 066: istore 6
      // 068: aconst_null
      // 069: astore 7
      // 06b: nop
      // 06c: aload 5
      // 06e: astore 8
      // 070: bipush 0
      // 071: istore 9
      // 073: aload 8
      // 075: invokeinterface kotlinx/coroutines/channels/ReceiveChannel.iterator ()Lkotlinx/coroutines/channels/ChannelIterator; 1
      // 07a: astore 10
      // 07c: aload 10
      // 07e: aload 15
      // 080: aload 15
      // 082: aload 1
      // 083: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.L$0 Ljava/lang/Object;
      // 086: aload 15
      // 088: aload 5
      // 08a: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.L$1 Ljava/lang/Object;
      // 08d: aload 15
      // 08f: aload 10
      // 091: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.L$2 Ljava/lang/Object;
      // 094: aload 15
      // 096: bipush 1
      // 097: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.label I
      // 09a: invokeinterface kotlinx/coroutines/channels/ChannelIterator.hasNext (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 09f: dup
      // 0a0: aload 16
      // 0a2: if_acmpne 0d9
      // 0a5: aload 16
      // 0a7: areturn
      // 0a8: bipush 0
      // 0a9: istore 4
      // 0ab: bipush 0
      // 0ac: istore 6
      // 0ae: bipush 0
      // 0af: istore 9
      // 0b1: aload 15
      // 0b3: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.L$2 Ljava/lang/Object;
      // 0b6: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 0b9: astore 10
      // 0bb: aconst_null
      // 0bc: astore 7
      // 0be: aload 15
      // 0c0: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.L$1 Ljava/lang/Object;
      // 0c3: checkcast kotlinx/coroutines/channels/ReceiveChannel
      // 0c6: astore 5
      // 0c8: aload 15
      // 0ca: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toCollection$1.L$0 Ljava/lang/Object;
      // 0cd: checkcast java/util/Collection
      // 0d0: astore 1
      // 0d1: nop
      // 0d2: aload 14
      // 0d4: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0d7: aload 14
      // 0d9: checkcast java/lang/Boolean
      // 0dc: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 0df: ifeq 0ff
      // 0e2: aload 10
      // 0e4: invokeinterface kotlinx/coroutines/channels/ChannelIterator.next ()Ljava/lang/Object; 1
      // 0e9: astore 11
      // 0eb: aload 11
      // 0ed: astore 12
      // 0ef: bipush 0
      // 0f0: istore 13
      // 0f2: aload 1
      // 0f3: aload 12
      // 0f5: invokeinterface java/util/Collection.add (Ljava/lang/Object;)Z 2
      // 0fa: pop
      // 0fb: nop
      // 0fc: goto 07c
      // 0ff: nop
      // 100: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 103: astore 8
      // 105: aload 5
      // 107: aload 7
      // 109: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 10c: goto 124
      // 10f: astore 9
      // 111: aload 9
      // 113: astore 7
      // 115: aload 9
      // 117: athrow
      // 118: astore 9
      // 11a: aload 5
      // 11c: aload 7
      // 11e: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 121: aload 9
      // 123: athrow
      // 124: nop
      // 125: aload 1
      // 126: areturn
      // 127: new java/lang/IllegalStateException
      // 12a: dup
      // 12b: ldc "call to 'resume' before 'invoke' with coroutine"
      // 12d: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 130: athrow
   }

   @PublishedApi
   @JvmStatic
   internal suspend fun <K, V, M : MutableMap<in K, in V>> ReceiveChannel<Pair<K, V>>.toMap(destination: M): M {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
      //
      // Bytecode:
      // 000: aload 2
      // 001: instanceof kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2
      // 004: ifeq 027
      // 007: aload 2
      // 008: checkcast kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2
      // 00b: astore 15
      // 00d: aload 15
      // 00f: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 15
      // 01a: dup
      // 01b: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.label I
      // 024: goto 031
      // 027: new kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2
      // 02a: dup
      // 02b: aload 2
      // 02c: invokespecial kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.<init> (Lkotlin/coroutines/Continuation;)V
      // 02f: astore 15
      // 031: aload 15
      // 033: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.result Ljava/lang/Object;
      // 036: astore 14
      // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03b: astore 16
      // 03d: aload 15
      // 03f: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.label I
      // 042: tableswitch 240 0 1 22 102
      // 058: aload 14
      // 05a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 05d: aload 0
      // 05e: astore 3
      // 05f: bipush 0
      // 060: istore 4
      // 062: aload 3
      // 063: astore 5
      // 065: bipush 0
      // 066: istore 6
      // 068: aconst_null
      // 069: astore 7
      // 06b: nop
      // 06c: aload 5
      // 06e: astore 8
      // 070: bipush 0
      // 071: istore 9
      // 073: aload 8
      // 075: invokeinterface kotlinx/coroutines/channels/ReceiveChannel.iterator ()Lkotlinx/coroutines/channels/ChannelIterator; 1
      // 07a: astore 10
      // 07c: aload 10
      // 07e: aload 15
      // 080: aload 15
      // 082: aload 1
      // 083: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.L$0 Ljava/lang/Object;
      // 086: aload 15
      // 088: aload 5
      // 08a: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.L$1 Ljava/lang/Object;
      // 08d: aload 15
      // 08f: aload 10
      // 091: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.L$2 Ljava/lang/Object;
      // 094: aload 15
      // 096: bipush 1
      // 097: putfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.label I
      // 09a: invokeinterface kotlinx/coroutines/channels/ChannelIterator.hasNext (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 09f: dup
      // 0a0: aload 16
      // 0a2: if_acmpne 0d9
      // 0a5: aload 16
      // 0a7: areturn
      // 0a8: bipush 0
      // 0a9: istore 4
      // 0ab: bipush 0
      // 0ac: istore 6
      // 0ae: bipush 0
      // 0af: istore 9
      // 0b1: aload 15
      // 0b3: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.L$2 Ljava/lang/Object;
      // 0b6: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 0b9: astore 10
      // 0bb: aconst_null
      // 0bc: astore 7
      // 0be: aload 15
      // 0c0: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.L$1 Ljava/lang/Object;
      // 0c3: checkcast kotlinx/coroutines/channels/ReceiveChannel
      // 0c6: astore 5
      // 0c8: aload 15
      // 0ca: getfield kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt$toMap$2.L$0 Ljava/lang/Object;
      // 0cd: checkcast java/util/Map
      // 0d0: astore 1
      // 0d1: nop
      // 0d2: aload 14
      // 0d4: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0d7: aload 14
      // 0d9: checkcast java/lang/Boolean
      // 0dc: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 0df: ifeq 10a
      // 0e2: aload 10
      // 0e4: invokeinterface kotlinx/coroutines/channels/ChannelIterator.next ()Ljava/lang/Object; 1
      // 0e9: astore 11
      // 0eb: aload 11
      // 0ed: checkcast kotlin/Pair
      // 0f0: astore 12
      // 0f2: bipush 0
      // 0f3: istore 13
      // 0f5: aload 1
      // 0f6: aload 12
      // 0f8: invokevirtual kotlin/Pair.getFirst ()Ljava/lang/Object;
      // 0fb: aload 12
      // 0fd: invokevirtual kotlin/Pair.getSecond ()Ljava/lang/Object;
      // 100: invokeinterface java/util/Map.put (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; 3
      // 105: pop
      // 106: nop
      // 107: goto 07c
      // 10a: nop
      // 10b: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 10e: astore 8
      // 110: aload 5
      // 112: aload 7
      // 114: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 117: goto 12f
      // 11a: astore 9
      // 11c: aload 9
      // 11e: astore 7
      // 120: aload 9
      // 122: athrow
      // 123: astore 9
      // 125: aload 5
      // 127: aload 7
      // 129: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 12c: aload 9
      // 12e: athrow
      // 12f: nop
      // 130: aload 1
      // 131: areturn
      // 132: new java/lang/IllegalStateException
      // 135: dup
      // 136: ldc "call to 'resume' before 'invoke' with coroutine"
      // 138: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 13b: athrow
   }

   @PublishedApi
   @JvmStatic
   internal fun <E, R> ReceiveChannel<E>.map(
      context: CoroutineContext = Dispatchers.getUnconfined() as CoroutineContext,
      transform: (E, Continuation<R>) -> Any?
   ): ReceiveChannel<R> {
      return ProduceKt.produce$default(
         GlobalScope.INSTANCE,
         context,
         0,
         null,
         ChannelsKt.consumes(`$this$map`),
         new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.map.1(`$this$map`, transform, null),
         6,
         null
      );
   }

   @PublishedApi
   @JvmStatic
   internal fun <E, R> ReceiveChannel<E>.mapIndexed(
      context: CoroutineContext = Dispatchers.getUnconfined() as CoroutineContext,
      transform: (Int, E, Continuation<R>) -> Any?
   ): ReceiveChannel<R> {
      return ProduceKt.produce$default(
         GlobalScope.INSTANCE,
         context,
         0,
         null,
         ChannelsKt.consumes(`$this$mapIndexed`),
         new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.mapIndexed.1(`$this$mapIndexed`, transform, null),
         6,
         null
      );
   }

   @PublishedApi
   @JvmStatic
   internal fun <E, K> ReceiveChannel<E>.distinctBy(
      context: CoroutineContext = Dispatchers.getUnconfined() as CoroutineContext,
      selector: (E, Continuation<K>) -> Any?
   ): ReceiveChannel<E> {
      return ProduceKt.produce$default(
         GlobalScope.INSTANCE,
         context,
         0,
         null,
         ChannelsKt.consumes(`$this$distinctBy`),
         new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.distinctBy.1(`$this$distinctBy`, selector, null),
         6,
         null
      );
   }

   @PublishedApi
   @JvmStatic
   internal suspend fun <E> ReceiveChannel<E>.toMutableSet(): MutableSet<E> {
      return ChannelsKt.toCollection(`$this$toMutableSet`, new LinkedHashSet(), `$completion`);
   }

   @PublishedApi
   @JvmStatic
   internal fun <E, R, V> ReceiveChannel<E>.zip(
      other: ReceiveChannel<R>,
      context: CoroutineContext = Dispatchers.getUnconfined() as CoroutineContext,
      transform: (E, R) -> V
   ): ReceiveChannel<V> {
      return ProduceKt.produce$default(
         GlobalScope.INSTANCE, context, 0, null, ChannelsKt.consumesAll(`$this$zip`, other), new 2(other, `$this$zip`, transform, null), 6, null
      );
   }

   @PublishedApi
   @JvmStatic
   internal fun ReceiveChannel<*>.consumes(): (Throwable?) -> Unit {
      return ChannelsKt__DeprecatedKt::consumes$lambda$24$ChannelsKt__DeprecatedKt;
   }

   @JvmStatic
   fun `consumesAll$lambda$2$ChannelsKt__DeprecatedKt`(`$channels`: Array<ReceiveChannel>, cause: java.lang.Throwable): Unit {
      var exception: java.lang.Throwable = null;

      for (ReceiveChannel channel : $channels) {
         try {
            ChannelsKt.cancelConsumed(it, cause);
         } catch (var7: java.lang.Throwable) {
            if (exception == null) {
               exception = var7;
            } else {
               ExceptionsKt.addSuppressed(exception, var7);
            }
         }
      }

      if (exception != null) {
         throw exception;
      } else {
         return Unit.INSTANCE;
      }
   }

   @JvmStatic
   fun `zip$lambda$23$ChannelsKt__DeprecatedKt`(t1: Any, t2: Any): Pair {
      return TuplesKt.to(t1, t2);
   }

   @JvmStatic
   fun `consumes$lambda$24$ChannelsKt__DeprecatedKt`(`$this_consumes`: ReceiveChannel, cause: java.lang.Throwable): Unit {
      ChannelsKt.cancelConsumed(`$this_consumes`, cause);
      return Unit.INSTANCE;
   }
}
