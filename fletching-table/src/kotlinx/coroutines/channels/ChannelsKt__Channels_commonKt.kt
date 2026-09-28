package kotlinx.coroutines.channels

import java.util.concurrent.CancellationException
import kotlin.contracts.InvocationKind
import kotlin.coroutines.Continuation
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.ExceptionsKt

@SourceDebugExtension(["SMAP\nChannels.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Channels.common.kt\nkotlinx/coroutines/channels/ChannelsKt__Channels_commonKt\n*L\n1#1,204:1\n94#1,8:205\n160#1:213\n94#1,3:214\n161#1,2:217\n101#1:219\n97#1,3:220\n*S KotlinDebug\n*F\n+ 1 Channels.common.kt\nkotlinx/coroutines/channels/ChannelsKt__Channels_commonKt\n*L\n160#1:205,8\n192#1:213\n192#1:214,3\n192#1:217,2\n192#1:219\n192#1:220,3\n*E\n"])
@JvmSynthetic
internal class ChannelsKt__Channels_commonKt {
   internal const val DEFAULT_CLOSE_MESSAGE: String

   @JvmStatic
   public inline fun <E, R> ReceiveChannel<E>.consume(block: (ReceiveChannel<E>) -> R): R {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      label18: {
         var cause: java.lang.Throwable = null;

         try {
            try {
               val var4: Any = block.invoke(`$this$consume`);
            } catch (var6: java.lang.Throwable) {
               cause = var6;
               throw var6;
            }
         } catch (var7: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(`$this$consume`, cause);
            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         ChannelsKt.cancelConsumed(`$this$consume`, null);
         InlineMarker.finallyEnd(1);
      }
   }

   @JvmStatic
   public suspend inline fun <E> ReceiveChannel<E>.consumeEach(action: (E) -> Unit) {
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
      // 001: instanceof kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1
      // 004: ifeq 027
      // 007: aload 2
      // 008: checkcast kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1
      // 00b: astore 12
      // 00d: aload 12
      // 00f: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 12
      // 01a: dup
      // 01b: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.label I
      // 024: goto 031
      // 027: new kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1
      // 02a: dup
      // 02b: aload 2
      // 02c: invokespecial kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.<init> (Lkotlin/coroutines/Continuation;)V
      // 02f: astore 12
      // 031: aload 12
      // 033: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.result Ljava/lang/Object;
      // 036: astore 11
      // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03b: astore 13
      // 03d: aload 12
      // 03f: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.label I
      // 042: tableswitch 234 0 1 22 99
      // 058: aload 11
      // 05a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 05d: bipush 0
      // 05e: istore 3
      // 05f: aload 0
      // 060: astore 4
      // 062: bipush 0
      // 063: istore 5
      // 065: aconst_null
      // 066: astore 6
      // 068: nop
      // 069: aload 4
      // 06b: astore 7
      // 06d: bipush 0
      // 06e: istore 8
      // 070: aload 7
      // 072: invokeinterface kotlinx/coroutines/channels/ReceiveChannel.iterator ()Lkotlinx/coroutines/channels/ChannelIterator; 1
      // 077: astore 9
      // 079: aload 9
      // 07b: aload 12
      // 07d: aload 12
      // 07f: aload 1
      // 080: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.L$0 Ljava/lang/Object;
      // 083: aload 12
      // 085: aload 4
      // 087: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.L$1 Ljava/lang/Object;
      // 08a: aload 12
      // 08c: aload 9
      // 08e: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.L$2 Ljava/lang/Object;
      // 091: aload 12
      // 093: bipush 1
      // 094: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.label I
      // 097: invokeinterface kotlinx/coroutines/channels/ChannelIterator.hasNext (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 09c: dup
      // 09d: aload 13
      // 09f: if_acmpne 0d5
      // 0a2: aload 13
      // 0a4: areturn
      // 0a5: bipush 0
      // 0a6: istore 3
      // 0a7: bipush 0
      // 0a8: istore 5
      // 0aa: bipush 0
      // 0ab: istore 8
      // 0ad: aload 12
      // 0af: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.L$2 Ljava/lang/Object;
      // 0b2: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 0b5: astore 9
      // 0b7: aconst_null
      // 0b8: astore 6
      // 0ba: aload 12
      // 0bc: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.L$1 Ljava/lang/Object;
      // 0bf: checkcast kotlinx/coroutines/channels/ReceiveChannel
      // 0c2: astore 4
      // 0c4: aload 12
      // 0c6: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$consumeEach$1.L$0 Ljava/lang/Object;
      // 0c9: checkcast kotlin/jvm/functions/Function1
      // 0cc: astore 1
      // 0cd: nop
      // 0ce: aload 11
      // 0d0: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0d3: aload 11
      // 0d5: checkcast java/lang/Boolean
      // 0d8: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 0db: ifeq 0f3
      // 0de: aload 9
      // 0e0: invokeinterface kotlinx/coroutines/channels/ChannelIterator.next ()Ljava/lang/Object; 1
      // 0e5: astore 10
      // 0e7: aload 1
      // 0e8: aload 10
      // 0ea: invokeinterface kotlin/jvm/functions/Function1.invoke (Ljava/lang/Object;)Ljava/lang/Object; 2
      // 0ef: pop
      // 0f0: goto 079
      // 0f3: nop
      // 0f4: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 0f7: astore 7
      // 0f9: bipush 1
      // 0fa: invokestatic kotlin/jvm/internal/InlineMarker.finallyStart (I)V
      // 0fd: aload 4
      // 0ff: aload 6
      // 101: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 104: bipush 1
      // 105: invokestatic kotlin/jvm/internal/InlineMarker.finallyEnd (I)V
      // 108: goto 128
      // 10b: astore 8
      // 10d: aload 8
      // 10f: astore 6
      // 111: aload 8
      // 113: athrow
      // 114: astore 8
      // 116: bipush 1
      // 117: invokestatic kotlin/jvm/internal/InlineMarker.finallyStart (I)V
      // 11a: aload 4
      // 11c: aload 6
      // 11e: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 121: bipush 1
      // 122: invokestatic kotlin/jvm/internal/InlineMarker.finallyEnd (I)V
      // 125: aload 8
      // 127: athrow
      // 128: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 12b: areturn
      // 12c: new java/lang/IllegalStateException
      // 12f: dup
      // 130: ldc "call to 'resume' before 'invoke' with coroutine"
      // 132: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 135: athrow
   }

   @JvmStatic
   fun <E> ReceiveChannel<? extends E>.`consumeEach$$forInline`(action: (E?) -> Unit, `$completion`: Continuation<? super Unit>): Any {
      label29: {
         val `$this$consume$iv`: ReceiveChannel = `$this$consumeEach`;
         var `cause$iv`: java.lang.Throwable = null;

         try {
            try {
               val var9: ChannelIterator = `$this$consume$iv`.iterator();

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
               `cause$iv` = var11;
               throw var11;
            }
         } catch (var12: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(`$this$consumeEach`, `cause$iv`);
            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         ChannelsKt.cancelConsumed(`$this$consumeEach`, null);
         InlineMarker.finallyEnd(1);
      }
   }

   @JvmStatic
   public suspend fun <E> ReceiveChannel<E>.toList(): List<E> {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
      //
      // Bytecode:
      // 000: aload 1
      // 001: instanceof kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1
      // 004: ifeq 027
      // 007: aload 1
      // 008: checkcast kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1
      // 00b: astore 17
      // 00d: aload 17
      // 00f: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 17
      // 01a: dup
      // 01b: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.label I
      // 024: goto 031
      // 027: new kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1
      // 02a: dup
      // 02b: aload 1
      // 02c: invokespecial kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.<init> (Lkotlin/coroutines/Continuation;)V
      // 02f: astore 17
      // 031: aload 17
      // 033: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.result Ljava/lang/Object;
      // 036: astore 16
      // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03b: astore 18
      // 03d: aload 17
      // 03f: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.label I
      // 042: tableswitch 262 0 1 22 119
      // 058: aload 16
      // 05a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 05d: invokestatic kotlin/collections/CollectionsKt.createListBuilder ()Ljava/util/List;
      // 060: astore 2
      // 061: aload 2
      // 062: astore 3
      // 063: bipush 0
      // 064: istore 4
      // 066: aload 0
      // 067: astore 5
      // 069: bipush 0
      // 06a: istore 6
      // 06c: aload 5
      // 06e: astore 7
      // 070: bipush 0
      // 071: istore 8
      // 073: aconst_null
      // 074: astore 9
      // 076: nop
      // 077: aload 7
      // 079: astore 10
      // 07b: bipush 0
      // 07c: istore 11
      // 07e: aload 10
      // 080: invokeinterface kotlinx/coroutines/channels/ReceiveChannel.iterator ()Lkotlinx/coroutines/channels/ChannelIterator; 1
      // 085: astore 12
      // 087: aload 12
      // 089: aload 17
      // 08b: aload 17
      // 08d: aload 2
      // 08e: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.L$0 Ljava/lang/Object;
      // 091: aload 17
      // 093: aload 3
      // 094: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.L$1 Ljava/lang/Object;
      // 097: aload 17
      // 099: aload 7
      // 09b: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.L$2 Ljava/lang/Object;
      // 09e: aload 17
      // 0a0: aload 12
      // 0a2: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.L$3 Ljava/lang/Object;
      // 0a5: aload 17
      // 0a7: bipush 1
      // 0a8: putfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.label I
      // 0ab: invokeinterface kotlinx/coroutines/channels/ChannelIterator.hasNext (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 0b0: dup
      // 0b1: aload 18
      // 0b3: if_acmpne 0f6
      // 0b6: aload 18
      // 0b8: areturn
      // 0b9: bipush 0
      // 0ba: istore 4
      // 0bc: bipush 0
      // 0bd: istore 6
      // 0bf: bipush 0
      // 0c0: istore 8
      // 0c2: bipush 0
      // 0c3: istore 11
      // 0c5: aload 17
      // 0c7: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.L$3 Ljava/lang/Object;
      // 0ca: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 0cd: astore 12
      // 0cf: aconst_null
      // 0d0: astore 9
      // 0d2: aload 17
      // 0d4: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.L$2 Ljava/lang/Object;
      // 0d7: checkcast kotlinx/coroutines/channels/ReceiveChannel
      // 0da: astore 7
      // 0dc: aload 17
      // 0de: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.L$1 Ljava/lang/Object;
      // 0e1: checkcast java/util/List
      // 0e4: astore 3
      // 0e5: aload 17
      // 0e7: getfield kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt$toList$1.L$0 Ljava/lang/Object;
      // 0ea: checkcast java/util/List
      // 0ed: astore 2
      // 0ee: nop
      // 0ef: aload 16
      // 0f1: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0f4: aload 16
      // 0f6: checkcast java/lang/Boolean
      // 0f9: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 0fc: ifeq 11c
      // 0ff: aload 12
      // 101: invokeinterface kotlinx/coroutines/channels/ChannelIterator.next ()Ljava/lang/Object; 1
      // 106: astore 13
      // 108: aload 13
      // 10a: astore 14
      // 10c: bipush 0
      // 10d: istore 15
      // 10f: aload 3
      // 110: aload 14
      // 112: invokeinterface java/util/List.add (Ljava/lang/Object;)Z 2
      // 117: pop
      // 118: nop
      // 119: goto 087
      // 11c: nop
      // 11d: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 120: astore 10
      // 122: aload 7
      // 124: aload 9
      // 126: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 129: goto 141
      // 12c: astore 11
      // 12e: aload 11
      // 130: astore 9
      // 132: aload 11
      // 134: athrow
      // 135: astore 11
      // 137: aload 7
      // 139: aload 9
      // 13b: invokestatic kotlinx/coroutines/channels/ChannelsKt.cancelConsumed (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V
      // 13e: aload 11
      // 140: athrow
      // 141: nop
      // 142: nop
      // 143: aload 2
      // 144: invokestatic kotlin/collections/CollectionsKt.build (Ljava/util/List;)Ljava/util/List;
      // 147: areturn
      // 148: new java/lang/IllegalStateException
      // 14b: dup
      // 14c: ldc "call to 'resume' before 'invoke' with coroutine"
      // 14e: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 151: athrow
   }

   @PublishedApi
   @JvmStatic
   internal fun ReceiveChannel<*>.cancelConsumed(cause: Throwable?) {
      var var10000: ReceiveChannel = `$this$cancelConsumed`;
      val var10001: CancellationException;
      if (cause != null) {
         var var5: CancellationException = cause as? CancellationException;
         if ((cause as? CancellationException) == null) {
            var5 = ExceptionsKt.CancellationException("Channel was consumed, consumer had failed", cause);
         }

         var10001 = var5;
         var10000 = `$this$cancelConsumed`;
      } else {
         var10001 = null;
      }

      var10000.cancel(var10001);
   }
}
