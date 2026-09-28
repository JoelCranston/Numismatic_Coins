package io.ktor.websocket

import io.ktor.util.cio.ByteBufferPoolKt
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.pool.ObjectPool
import io.ktor.websocket.WebSocketWriter.writeLoopJob.1
import java.nio.ByteBuffer
import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelKt
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.SendChannel

public class WebSocketWriter(writeChannel: ByteWriteChannel,
      coroutineContext: CoroutineContext,
      masking: Boolean = false,
      pool: ObjectPool<ByteBuffer> = ByteBufferPoolKt.getKtorDefaultPool()
   ) :
   CoroutineScope {
   private final val writeChannel: ByteWriteChannel
   public open val coroutineContext: CoroutineContext
   public final var masking: Boolean
   public final val pool: ObjectPool<ByteBuffer>
   private final val queue: Channel<Any>
   private final val serializer: Serializer

   public final val outgoing: SendChannel<Frame>
      public final get() {
         return this.queue;
      }


   private final val writeLoopJob: Job

   init {
      this.writeChannel = writeChannel;
      this.coroutineContext = coroutineContext;
      this.masking = masking;
      this.pool = pool;
      this.queue = ChannelKt.Channel$default(8, null, null, 6, null);
      this.serializer = new Serializer();
      this.writeLoopJob = BuildersKt.launch(this, new CoroutineName("ws-writer"), CoroutineStart.ATOMIC, new 1(this, null));
   }

   private suspend fun writeLoop(buffer: ByteBuffer) {
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
      // 001: instanceof io/ktor/websocket/WebSocketWriter$writeLoop$1
      // 004: ifeq 027
      // 007: aload 2
      // 008: checkcast io/ktor/websocket/WebSocketWriter$writeLoop$1
      // 00b: astore 7
      // 00d: aload 7
      // 00f: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 7
      // 01a: dup
      // 01b: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.label I
      // 024: goto 032
      // 027: new io/ktor/websocket/WebSocketWriter$writeLoop$1
      // 02a: dup
      // 02b: aload 0
      // 02c: aload 2
      // 02d: invokespecial io/ktor/websocket/WebSocketWriter$writeLoop$1.<init> (Lio/ktor/websocket/WebSocketWriter;Lkotlin/coroutines/Continuation;)V
      // 030: astore 7
      // 032: aload 7
      // 034: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.result Ljava/lang/Object;
      // 037: astore 6
      // 039: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03c: astore 8
      // 03e: aload 7
      // 040: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.label I
      // 043: tableswitch 709 0 6 41 103 207 369 481 581 671
      // 06c: aload 6
      // 06e: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 071: aload 1
      // 072: invokevirtual java/nio/ByteBuffer.clear ()Ljava/nio/Buffer;
      // 075: pop
      // 076: nop
      // 077: aload 0
      // 078: getfield io/ktor/websocket/WebSocketWriter.queue Lkotlinx/coroutines/channels/Channel;
      // 07b: invokeinterface kotlinx/coroutines/channels/Channel.iterator ()Lkotlinx/coroutines/channels/ChannelIterator; 1
      // 080: astore 3
      // 081: aload 3
      // 082: aload 7
      // 084: aload 7
      // 086: aload 1
      // 087: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 08a: aload 7
      // 08c: aload 3
      // 08d: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$1 Ljava/lang/Object;
      // 090: aload 7
      // 092: aconst_null
      // 093: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$2 Ljava/lang/Object;
      // 096: aload 7
      // 098: bipush 1
      // 099: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.label I
      // 09c: invokeinterface kotlinx/coroutines/channels/ChannelIterator.hasNext (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 0a1: dup
      // 0a2: aload 8
      // 0a4: if_acmpne 0c4
      // 0a7: aload 8
      // 0a9: areturn
      // 0aa: aload 7
      // 0ac: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$1 Ljava/lang/Object;
      // 0af: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 0b2: astore 3
      // 0b3: aload 7
      // 0b5: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 0b8: checkcast java/nio/ByteBuffer
      // 0bb: astore 1
      // 0bc: nop
      // 0bd: aload 6
      // 0bf: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0c2: aload 6
      // 0c4: checkcast java/lang/Boolean
      // 0c7: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 0ca: ifeq 172
      // 0cd: aload 3
      // 0ce: invokeinterface kotlinx/coroutines/channels/ChannelIterator.next ()Ljava/lang/Object; 1
      // 0d3: astore 4
      // 0d5: aload 4
      // 0d7: astore 5
      // 0d9: aload 5
      // 0db: instanceof io/ktor/websocket/Frame
      // 0de: ifeq 13f
      // 0e1: aload 0
      // 0e2: aload 4
      // 0e4: checkcast io/ktor/websocket/Frame
      // 0e7: aload 1
      // 0e8: aload 7
      // 0ea: aload 7
      // 0ec: aload 1
      // 0ed: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 0f0: aload 7
      // 0f2: aload 3
      // 0f3: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$1 Ljava/lang/Object;
      // 0f6: aload 7
      // 0f8: aload 4
      // 0fa: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 0fd: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$2 Ljava/lang/Object;
      // 100: aload 7
      // 102: bipush 2
      // 103: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.label I
      // 106: invokespecial io/ktor/websocket/WebSocketWriter.drainQueueAndSerialize (Lio/ktor/websocket/Frame;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 109: dup
      // 10a: aload 8
      // 10c: if_acmpne 133
      // 10f: aload 8
      // 111: areturn
      // 112: aload 7
      // 114: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$2 Ljava/lang/Object;
      // 117: astore 4
      // 119: aload 7
      // 11b: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$1 Ljava/lang/Object;
      // 11e: checkcast kotlinx/coroutines/channels/ChannelIterator
      // 121: astore 3
      // 122: aload 7
      // 124: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 127: checkcast java/nio/ByteBuffer
      // 12a: astore 1
      // 12b: nop
      // 12c: aload 6
      // 12e: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 131: aload 6
      // 133: checkcast java/lang/Boolean
      // 136: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 139: ifeq 081
      // 13c: goto 172
      // 13f: aload 5
      // 141: instanceof io/ktor/websocket/WebSocketWriter$FlushRequest
      // 144: ifeq 156
      // 147: aload 4
      // 149: checkcast io/ktor/websocket/WebSocketWriter$FlushRequest
      // 14c: invokevirtual io/ktor/websocket/WebSocketWriter$FlushRequest.complete ()Z
      // 14f: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxBoolean (Z)Ljava/lang/Boolean;
      // 152: pop
      // 153: goto 081
      // 156: new java/lang/IllegalArgumentException
      // 159: dup
      // 15a: new java/lang/StringBuilder
      // 15d: dup
      // 15e: invokespecial java/lang/StringBuilder.<init> ()V
      // 161: ldc "unknown message "
      // 163: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 166: aload 4
      // 168: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      // 16b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 16e: invokespecial java/lang/IllegalArgumentException.<init> (Ljava/lang/String;)V
      // 171: athrow
      // 172: aload 0
      // 173: getfield io/ktor/websocket/WebSocketWriter.queue Lkotlinx/coroutines/channels/Channel;
      // 176: ldc "WebSocket closed."
      // 178: aconst_null
      // 179: invokestatic kotlinx/coroutines/ExceptionsKt.CancellationException (Ljava/lang/String;Ljava/lang/Throwable;)Ljava/util/concurrent/CancellationException;
      // 17c: checkcast java/lang/Throwable
      // 17f: invokeinterface kotlinx/coroutines/channels/Channel.close (Ljava/lang/Throwable;)Z 2
      // 184: pop
      // 185: aload 0
      // 186: getfield io/ktor/websocket/WebSocketWriter.writeChannel Lio/ktor/utils/io/ByteWriteChannel;
      // 189: aload 7
      // 18b: aload 7
      // 18d: aload 1
      // 18e: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 191: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 194: aload 7
      // 196: aconst_null
      // 197: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$1 Ljava/lang/Object;
      // 19a: aload 7
      // 19c: aconst_null
      // 19d: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$2 Ljava/lang/Object;
      // 1a0: aload 7
      // 1a2: bipush 3
      // 1a3: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.label I
      // 1a6: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 1ab: dup
      // 1ac: aload 8
      // 1ae: if_acmpne 1c4
      // 1b1: aload 8
      // 1b3: areturn
      // 1b4: aload 7
      // 1b6: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 1b9: checkcast java/nio/ByteBuffer
      // 1bc: astore 1
      // 1bd: aload 6
      // 1bf: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 1c2: aload 6
      // 1c4: pop
      // 1c5: goto 300
      // 1c8: astore 4
      // 1ca: aload 0
      // 1cb: getfield io/ktor/websocket/WebSocketWriter.queue Lkotlinx/coroutines/channels/Channel;
      // 1ce: ldc_w "Failed to write to WebSocket."
      // 1d1: aload 4
      // 1d3: checkcast java/lang/Throwable
      // 1d6: invokestatic kotlinx/coroutines/ExceptionsKt.CancellationException (Ljava/lang/String;Ljava/lang/Throwable;)Ljava/util/concurrent/CancellationException;
      // 1d9: checkcast java/lang/Throwable
      // 1dc: invokeinterface kotlinx/coroutines/channels/Channel.close (Ljava/lang/Throwable;)Z 2
      // 1e1: pop
      // 1e2: aload 0
      // 1e3: getfield io/ktor/websocket/WebSocketWriter.queue Lkotlinx/coroutines/channels/Channel;
      // 1e6: ldc "WebSocket closed."
      // 1e8: aconst_null
      // 1e9: invokestatic kotlinx/coroutines/ExceptionsKt.CancellationException (Ljava/lang/String;Ljava/lang/Throwable;)Ljava/util/concurrent/CancellationException;
      // 1ec: checkcast java/lang/Throwable
      // 1ef: invokeinterface kotlinx/coroutines/channels/Channel.close (Ljava/lang/Throwable;)Z 2
      // 1f4: pop
      // 1f5: aload 0
      // 1f6: getfield io/ktor/websocket/WebSocketWriter.writeChannel Lio/ktor/utils/io/ByteWriteChannel;
      // 1f9: aload 7
      // 1fb: aload 7
      // 1fd: aload 1
      // 1fe: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 201: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 204: aload 7
      // 206: aconst_null
      // 207: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$1 Ljava/lang/Object;
      // 20a: aload 7
      // 20c: aconst_null
      // 20d: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$2 Ljava/lang/Object;
      // 210: aload 7
      // 212: bipush 4
      // 213: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.label I
      // 216: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 21b: dup
      // 21c: aload 8
      // 21e: if_acmpne 234
      // 221: aload 8
      // 223: areturn
      // 224: aload 7
      // 226: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 229: checkcast java/nio/ByteBuffer
      // 22c: astore 1
      // 22d: aload 6
      // 22f: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 232: aload 6
      // 234: pop
      // 235: goto 300
      // 238: astore 4
      // 23a: aload 0
      // 23b: getfield io/ktor/websocket/WebSocketWriter.queue Lkotlinx/coroutines/channels/Channel;
      // 23e: aload 4
      // 240: invokeinterface kotlinx/coroutines/channels/Channel.close (Ljava/lang/Throwable;)Z 2
      // 245: pop
      // 246: aload 0
      // 247: getfield io/ktor/websocket/WebSocketWriter.queue Lkotlinx/coroutines/channels/Channel;
      // 24a: ldc "WebSocket closed."
      // 24c: aconst_null
      // 24d: invokestatic kotlinx/coroutines/ExceptionsKt.CancellationException (Ljava/lang/String;Ljava/lang/Throwable;)Ljava/util/concurrent/CancellationException;
      // 250: checkcast java/lang/Throwable
      // 253: invokeinterface kotlinx/coroutines/channels/Channel.close (Ljava/lang/Throwable;)Z 2
      // 258: pop
      // 259: aload 0
      // 25a: getfield io/ktor/websocket/WebSocketWriter.writeChannel Lio/ktor/utils/io/ByteWriteChannel;
      // 25d: aload 7
      // 25f: aload 7
      // 261: aload 1
      // 262: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 265: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 268: aload 7
      // 26a: aconst_null
      // 26b: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$1 Ljava/lang/Object;
      // 26e: aload 7
      // 270: aconst_null
      // 271: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$2 Ljava/lang/Object;
      // 274: aload 7
      // 276: bipush 5
      // 277: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.label I
      // 27a: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 27f: dup
      // 280: aload 8
      // 282: if_acmpne 298
      // 285: aload 8
      // 287: areturn
      // 288: aload 7
      // 28a: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 28d: checkcast java/nio/ByteBuffer
      // 290: astore 1
      // 291: aload 6
      // 293: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 296: aload 6
      // 298: pop
      // 299: goto 300
      // 29c: astore 4
      // 29e: aload 0
      // 29f: getfield io/ktor/websocket/WebSocketWriter.queue Lkotlinx/coroutines/channels/Channel;
      // 2a2: ldc "WebSocket closed."
      // 2a4: aconst_null
      // 2a5: invokestatic kotlinx/coroutines/ExceptionsKt.CancellationException (Ljava/lang/String;Ljava/lang/Throwable;)Ljava/util/concurrent/CancellationException;
      // 2a8: checkcast java/lang/Throwable
      // 2ab: invokeinterface kotlinx/coroutines/channels/Channel.close (Ljava/lang/Throwable;)Z 2
      // 2b0: pop
      // 2b1: aload 0
      // 2b2: getfield io/ktor/websocket/WebSocketWriter.writeChannel Lio/ktor/utils/io/ByteWriteChannel;
      // 2b5: aload 7
      // 2b7: aload 7
      // 2b9: aload 1
      // 2ba: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 2bd: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 2c0: aload 7
      // 2c2: aload 4
      // 2c4: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$1 Ljava/lang/Object;
      // 2c7: aload 7
      // 2c9: aconst_null
      // 2ca: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$2 Ljava/lang/Object;
      // 2cd: aload 7
      // 2cf: bipush 6
      // 2d1: putfield io/ktor/websocket/WebSocketWriter$writeLoop$1.label I
      // 2d4: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 2d9: dup
      // 2da: aload 8
      // 2dc: if_acmpne 2fc
      // 2df: aload 8
      // 2e1: areturn
      // 2e2: aload 7
      // 2e4: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$1 Ljava/lang/Object;
      // 2e7: checkcast java/lang/Throwable
      // 2ea: astore 4
      // 2ec: aload 7
      // 2ee: getfield io/ktor/websocket/WebSocketWriter$writeLoop$1.L$0 Ljava/lang/Object;
      // 2f1: checkcast java/nio/ByteBuffer
      // 2f4: astore 1
      // 2f5: aload 6
      // 2f7: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 2fa: aload 6
      // 2fc: pop
      // 2fd: aload 4
      // 2ff: athrow
      // 300: aload 0
      // 301: invokespecial io/ktor/websocket/WebSocketWriter.drainQueueAndDiscard ()V
      // 304: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 307: areturn
      // 308: new java/lang/IllegalStateException
      // 30b: dup
      // 30c: ldc_w "call to 'resume' before 'invoke' with coroutine"
      // 30f: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 312: athrow
   }

   private fun drainQueueAndDiscard() {
      SendChannel.DefaultImpls.close$default(this.queue, null, 1, null);

      try {
         while (true) {
            val var10000: Any = ChannelResult.getOrNull-impl(this.queue.tryReceive-PtdJZtk());
            if (var10000 == null) {
               break;
            }

            if (var10000 !is Frame.Close && var10000 !is Frame.Ping && var10000 !is Frame.Pong) {
               if (var10000 is WebSocketWriter.FlushRequest) {
                  (var10000 as WebSocketWriter.FlushRequest).complete();
               } else if (var10000 !is Frame.Text && var10000 !is Frame.Binary) {
                  throw new IllegalArgumentException("unknown message $var10000");
               }
            }
         }
      } catch (var3: CancellationException) {
      }
   }

   private suspend fun drainQueueAndSerialize(firstMsg: Frame, buffer: ByteBuffer): Boolean {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
      //
      // Bytecode:
      // 000: aload 3
      // 001: instanceof io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1
      // 004: ifeq 027
      // 007: aload 3
      // 008: checkcast io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1
      // 00b: astore 11
      // 00d: aload 11
      // 00f: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 11
      // 01a: dup
      // 01b: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.label I
      // 024: goto 032
      // 027: new io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1
      // 02a: dup
      // 02b: aload 0
      // 02c: aload 3
      // 02d: invokespecial io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.<init> (Lio/ktor/websocket/WebSocketWriter;Lkotlin/coroutines/Continuation;)V
      // 030: astore 11
      // 032: aload 11
      // 034: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.result Ljava/lang/Object;
      // 037: astore 10
      // 039: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03c: astore 12
      // 03e: aload 11
      // 040: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.label I
      // 043: tableswitch 718 0 3 29 322 468 638
      // 060: aload 10
      // 062: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 065: new kotlin/jvm/internal/Ref$ObjectRef
      // 068: dup
      // 069: invokespecial kotlin/jvm/internal/Ref$ObjectRef.<init> ()V
      // 06c: astore 4
      // 06e: aload 0
      // 06f: getfield io/ktor/websocket/WebSocketWriter.serializer Lio/ktor/websocket/Serializer;
      // 072: aload 1
      // 073: invokevirtual io/ktor/websocket/Serializer.enqueue (Lio/ktor/websocket/Frame;)V
      // 076: aload 1
      // 077: instanceof io/ktor/websocket/Frame$Close
      // 07a: istore 5
      // 07c: nop
      // 07d: aload 4
      // 07f: getfield kotlin/jvm/internal/Ref$ObjectRef.element Ljava/lang/Object;
      // 082: ifnonnull 10d
      // 085: iload 5
      // 087: ifne 10d
      // 08a: aload 0
      // 08b: getfield io/ktor/websocket/WebSocketWriter.serializer Lio/ktor/websocket/Serializer;
      // 08e: invokevirtual io/ktor/websocket/Serializer.getRemainingCapacity ()I
      // 091: ifle 10d
      // 094: aload 0
      // 095: getfield io/ktor/websocket/WebSocketWriter.queue Lkotlinx/coroutines/channels/Channel;
      // 098: invokeinterface kotlinx/coroutines/channels/Channel.tryReceive-PtdJZtk ()Ljava/lang/Object; 1
      // 09d: invokestatic kotlinx/coroutines/channels/ChannelResult.getOrNull-impl (Ljava/lang/Object;)Ljava/lang/Object;
      // 0a0: dup
      // 0a1: ifnonnull 0a8
      // 0a4: pop
      // 0a5: goto 10d
      // 0a8: astore 6
      // 0aa: aload 6
      // 0ac: astore 7
      // 0ae: aload 7
      // 0b0: instanceof io/ktor/websocket/WebSocketWriter$FlushRequest
      // 0b3: ifeq 0c0
      // 0b6: aload 4
      // 0b8: aload 6
      // 0ba: putfield kotlin/jvm/internal/Ref$ObjectRef.element Ljava/lang/Object;
      // 0bd: goto 07d
      // 0c0: aload 7
      // 0c2: instanceof io/ktor/websocket/Frame$Close
      // 0c5: ifeq 0da
      // 0c8: aload 0
      // 0c9: getfield io/ktor/websocket/WebSocketWriter.serializer Lio/ktor/websocket/Serializer;
      // 0cc: aload 6
      // 0ce: checkcast io/ktor/websocket/Frame
      // 0d1: invokevirtual io/ktor/websocket/Serializer.enqueue (Lio/ktor/websocket/Frame;)V
      // 0d4: bipush 1
      // 0d5: istore 5
      // 0d7: goto 07d
      // 0da: aload 7
      // 0dc: instanceof io/ktor/websocket/Frame
      // 0df: ifeq 0f1
      // 0e2: aload 0
      // 0e3: getfield io/ktor/websocket/WebSocketWriter.serializer Lio/ktor/websocket/Serializer;
      // 0e6: aload 6
      // 0e8: checkcast io/ktor/websocket/Frame
      // 0eb: invokevirtual io/ktor/websocket/Serializer.enqueue (Lio/ktor/websocket/Frame;)V
      // 0ee: goto 07d
      // 0f1: new java/lang/IllegalArgumentException
      // 0f4: dup
      // 0f5: new java/lang/StringBuilder
      // 0f8: dup
      // 0f9: invokespecial java/lang/StringBuilder.<init> ()V
      // 0fc: ldc "unknown message "
      // 0fe: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 101: aload 6
      // 103: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      // 106: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 109: invokespecial java/lang/IllegalArgumentException.<init> (Ljava/lang/String;)V
      // 10c: athrow
      // 10d: iload 5
      // 10f: ifeq 120
      // 112: aload 0
      // 113: getfield io/ktor/websocket/WebSocketWriter.queue Lkotlinx/coroutines/channels/Channel;
      // 116: checkcast kotlinx/coroutines/channels/SendChannel
      // 119: aconst_null
      // 11a: bipush 1
      // 11b: aconst_null
      // 11c: invokestatic kotlinx/coroutines/channels/SendChannel$DefaultImpls.close$default (Lkotlinx/coroutines/channels/SendChannel;Ljava/lang/Throwable;ILjava/lang/Object;)Z
      // 11f: pop
      // 120: aload 0
      // 121: getfield io/ktor/websocket/WebSocketWriter.serializer Lio/ktor/websocket/Serializer;
      // 124: invokevirtual io/ktor/websocket/Serializer.getHasOutstandingBytes ()Z
      // 127: ifne 131
      // 12a: aload 2
      // 12b: invokevirtual java/nio/ByteBuffer.position ()I
      // 12e: ifeq 281
      // 131: aload 0
      // 132: getfield io/ktor/websocket/WebSocketWriter.serializer Lio/ktor/websocket/Serializer;
      // 135: aload 0
      // 136: getfield io/ktor/websocket/WebSocketWriter.masking Z
      // 139: invokevirtual io/ktor/websocket/Serializer.setMasking (Z)V
      // 13c: aload 0
      // 13d: getfield io/ktor/websocket/WebSocketWriter.serializer Lio/ktor/websocket/Serializer;
      // 140: aload 2
      // 141: invokevirtual io/ktor/websocket/Serializer.serialize (Ljava/nio/ByteBuffer;)V
      // 144: aload 2
      // 145: invokevirtual java/nio/ByteBuffer.flip ()Ljava/nio/Buffer;
      // 148: pop
      // 149: aload 0
      // 14a: getfield io/ktor/websocket/WebSocketWriter.writeChannel Lio/ktor/utils/io/ByteWriteChannel;
      // 14d: aload 2
      // 14e: aload 11
      // 150: aload 11
      // 152: aload 1
      // 153: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 156: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$0 Ljava/lang/Object;
      // 159: aload 11
      // 15b: aload 2
      // 15c: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$1 Ljava/lang/Object;
      // 15f: aload 11
      // 161: aload 4
      // 163: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$2 Ljava/lang/Object;
      // 166: aload 11
      // 168: aconst_null
      // 169: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$3 Ljava/lang/Object;
      // 16c: aload 11
      // 16e: iload 5
      // 170: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.I$0 I
      // 173: aload 11
      // 175: bipush 1
      // 176: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.label I
      // 179: invokestatic io/ktor/utils/io/ByteWriteChannelOperations_jvmKt.writeFully (Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 17c: dup
      // 17d: aload 12
      // 17f: if_acmpne 1af
      // 182: aload 12
      // 184: areturn
      // 185: aload 11
      // 187: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.I$0 I
      // 18a: istore 5
      // 18c: aload 11
      // 18e: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$2 Ljava/lang/Object;
      // 191: checkcast kotlin/jvm/internal/Ref$ObjectRef
      // 194: astore 4
      // 196: aload 11
      // 198: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$1 Ljava/lang/Object;
      // 19b: checkcast java/nio/ByteBuffer
      // 19e: astore 2
      // 19f: aload 11
      // 1a1: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$0 Ljava/lang/Object;
      // 1a4: checkcast io/ktor/websocket/Frame
      // 1a7: astore 1
      // 1a8: aload 10
      // 1aa: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 1ad: aload 10
      // 1af: pop
      // 1b0: aload 0
      // 1b1: getfield io/ktor/websocket/WebSocketWriter.serializer Lio/ktor/websocket/Serializer;
      // 1b4: invokevirtual io/ktor/websocket/Serializer.getHasOutstandingBytes ()Z
      // 1b7: ifne 265
      // 1ba: aload 2
      // 1bb: invokevirtual java/nio/ByteBuffer.hasRemaining ()Z
      // 1be: ifne 265
      // 1c1: aload 4
      // 1c3: getfield kotlin/jvm/internal/Ref$ObjectRef.element Ljava/lang/Object;
      // 1c6: checkcast io/ktor/websocket/WebSocketWriter$FlushRequest
      // 1c9: dup
      // 1ca: ifnull 263
      // 1cd: astore 8
      // 1cf: bipush 0
      // 1d0: istore 9
      // 1d2: aload 0
      // 1d3: getfield io/ktor/websocket/WebSocketWriter.writeChannel Lio/ktor/utils/io/ByteWriteChannel;
      // 1d6: aload 11
      // 1d8: aload 11
      // 1da: aload 1
      // 1db: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 1de: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$0 Ljava/lang/Object;
      // 1e1: aload 11
      // 1e3: aload 2
      // 1e4: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$1 Ljava/lang/Object;
      // 1e7: aload 11
      // 1e9: aload 4
      // 1eb: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$2 Ljava/lang/Object;
      // 1ee: aload 11
      // 1f0: aload 8
      // 1f2: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$3 Ljava/lang/Object;
      // 1f5: aload 11
      // 1f7: iload 5
      // 1f9: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.I$0 I
      // 1fc: aload 11
      // 1fe: iload 9
      // 200: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.I$1 I
      // 203: aload 11
      // 205: bipush 2
      // 206: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.label I
      // 209: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 20e: dup
      // 20f: aload 12
      // 211: if_acmpne 252
      // 214: aload 12
      // 216: areturn
      // 217: aload 11
      // 219: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.I$1 I
      // 21c: istore 9
      // 21e: aload 11
      // 220: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.I$0 I
      // 223: istore 5
      // 225: aload 11
      // 227: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$3 Ljava/lang/Object;
      // 22a: checkcast io/ktor/websocket/WebSocketWriter$FlushRequest
      // 22d: astore 8
      // 22f: aload 11
      // 231: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$2 Ljava/lang/Object;
      // 234: checkcast kotlin/jvm/internal/Ref$ObjectRef
      // 237: astore 4
      // 239: aload 11
      // 23b: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$1 Ljava/lang/Object;
      // 23e: checkcast java/nio/ByteBuffer
      // 241: astore 2
      // 242: aload 11
      // 244: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$0 Ljava/lang/Object;
      // 247: checkcast io/ktor/websocket/Frame
      // 24a: astore 1
      // 24b: aload 10
      // 24d: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 250: aload 10
      // 252: pop
      // 253: aload 8
      // 255: invokevirtual io/ktor/websocket/WebSocketWriter$FlushRequest.complete ()Z
      // 258: pop
      // 259: aload 4
      // 25b: aconst_null
      // 25c: putfield kotlin/jvm/internal/Ref$ObjectRef.element Ljava/lang/Object;
      // 25f: nop
      // 260: goto 265
      // 263: pop
      // 264: nop
      // 265: aload 4
      // 267: getfield kotlin/jvm/internal/Ref$ObjectRef.element Ljava/lang/Object;
      // 26a: ifnonnull 272
      // 26d: iload 5
      // 26f: ifeq 279
      // 272: aload 2
      // 273: invokevirtual java/nio/ByteBuffer.hasRemaining ()Z
      // 276: ifne 149
      // 279: aload 2
      // 27a: invokevirtual java/nio/ByteBuffer.compact ()Ljava/nio/ByteBuffer;
      // 27d: pop
      // 27e: goto 07c
      // 281: aload 0
      // 282: getfield io/ktor/websocket/WebSocketWriter.writeChannel Lio/ktor/utils/io/ByteWriteChannel;
      // 285: aload 11
      // 287: aload 11
      // 289: aload 1
      // 28a: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 28d: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$0 Ljava/lang/Object;
      // 290: aload 11
      // 292: aload 2
      // 293: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 296: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$1 Ljava/lang/Object;
      // 299: aload 11
      // 29b: aload 4
      // 29d: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$2 Ljava/lang/Object;
      // 2a0: aload 11
      // 2a2: aconst_null
      // 2a3: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$3 Ljava/lang/Object;
      // 2a6: aload 11
      // 2a8: iload 5
      // 2aa: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.I$0 I
      // 2ad: aload 11
      // 2af: bipush 3
      // 2b0: putfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.label I
      // 2b3: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 2b8: dup
      // 2b9: aload 12
      // 2bb: if_acmpne 2eb
      // 2be: aload 12
      // 2c0: areturn
      // 2c1: aload 11
      // 2c3: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.I$0 I
      // 2c6: istore 5
      // 2c8: aload 11
      // 2ca: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$2 Ljava/lang/Object;
      // 2cd: checkcast kotlin/jvm/internal/Ref$ObjectRef
      // 2d0: astore 4
      // 2d2: aload 11
      // 2d4: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$1 Ljava/lang/Object;
      // 2d7: checkcast java/nio/ByteBuffer
      // 2da: astore 2
      // 2db: aload 11
      // 2dd: getfield io/ktor/websocket/WebSocketWriter$drainQueueAndSerialize$1.L$0 Ljava/lang/Object;
      // 2e0: checkcast io/ktor/websocket/Frame
      // 2e3: astore 1
      // 2e4: aload 10
      // 2e6: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 2e9: aload 10
      // 2eb: pop
      // 2ec: aload 4
      // 2ee: getfield kotlin/jvm/internal/Ref$ObjectRef.element Ljava/lang/Object;
      // 2f1: checkcast io/ktor/websocket/WebSocketWriter$FlushRequest
      // 2f4: dup
      // 2f5: ifnull 302
      // 2f8: invokevirtual io/ktor/websocket/WebSocketWriter$FlushRequest.complete ()Z
      // 2fb: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxBoolean (Z)Ljava/lang/Boolean;
      // 2fe: pop
      // 2ff: goto 303
      // 302: pop
      // 303: iload 5
      // 305: ifeq 30c
      // 308: bipush 1
      // 309: goto 30d
      // 30c: bipush 0
      // 30d: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxBoolean (Z)Ljava/lang/Boolean;
      // 310: areturn
      // 311: new java/lang/IllegalStateException
      // 314: dup
      // 315: ldc_w "call to 'resume' before 'invoke' with coroutine"
      // 318: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 31b: athrow
   }

   public suspend fun send(frame: Frame) {
      val var10000: Any = this.queue.send(frame, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   public suspend fun flush() {
      var `$continuation`: Continuation;
      label68: {
         if (`$completion` is io.ktor.websocket.WebSocketWriter.flush.1) {
            `$continuation` = `$completion` as io.ktor.websocket.WebSocketWriter.flush.1;
            if (((`$completion` as io.ktor.websocket.WebSocketWriter.flush.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label68;
            }
         }

         `$continuation` = new io.ktor.websocket.WebSocketWriter.flush.1(this, `$completion`);
      }

      var var2: WebSocketWriter.FlushRequest;
      var var8: Any;
      label58: {
         val `$result`: Any = `$continuation`.result;
         var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var var15: WebSocketWriter.FlushRequest;
         var var16: Int;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               var2 = new WebSocketWriter.FlushRequest(this.getCoroutineContext().get(Job.Key));
               var15 = var2;
               var16 = 0;

               var var19: Channel;
               try {
                  var19 = this.queue;
                  `$continuation`.L$0 = var2;
                  `$continuation`.L$1 = var15;
                  `$continuation`.I$0 = var16;
                  `$continuation`.label = 1;
                  var19 = (Channel)var19.send(var15, `$continuation`);
               } catch (var11: ClosedSendChannelException) {
                  var2.complete();
                  val var17: Job = this.writeLoopJob;
                  `$continuation`.L$0 = var2;
                  `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var2);
                  `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var11);
                  `$continuation`.I$0 = 0;
                  `$continuation`.label = 2;
                  if (var17.join(`$continuation`) === var8) {
                     return var8;
                  }
                  break label58;
               } catch (var12: java.lang.Throwable) {
                  var2.complete();
                  throw var12;
               }

               if (var19 === var8) {
                  return var8;
               }
               break;
            case 1:
               var16 = `$continuation`.I$0;
               var15 = `$continuation`.L$1 as WebSocketWriter.FlushRequest;
               var2 = `$continuation`.L$0 as WebSocketWriter.FlushRequest;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  break;
               } catch (var13: ClosedSendChannelException) {
                  var15.complete();
                  val var10000: Job = this.writeLoopJob;
                  `$continuation`.L$0 = var2;
                  `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var15);
                  `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var13);
                  `$continuation`.I$0 = var16;
                  `$continuation`.label = 2;
                  if (var10000.join(`$continuation`) === var8) {
                     return var8;
                  }
                  break label58;
               } catch (var14: java.lang.Throwable) {
                  var15.complete();
                  throw var14;
               }
            case 2:
               var16 = `$continuation`.I$0;
               val sendFailure: ClosedSendChannelException = `$continuation`.L$2 as ClosedSendChannelException;
               var15 = `$continuation`.L$1 as WebSocketWriter.FlushRequest;
               var2 = `$continuation`.L$0 as WebSocketWriter.FlushRequest;
               ResultKt.throwOnFailure(`$result`);
               break label58;
            case 3:
               ResultKt.throwOnFailure(`$result`);
               return Unit.INSTANCE;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            ;
         } catch (var9: ClosedSendChannelException) {
            var15.complete();
            val var20: Job = this.writeLoopJob;
            `$continuation`.L$0 = var2;
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var15);
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var9);
            `$continuation`.I$0 = var16;
            `$continuation`.label = 2;
            if (var20.join(`$continuation`) === var8) {
               return var8;
            }
         } catch (var10: java.lang.Throwable) {
            var15.complete();
            throw var10;
         }
      }

      `$continuation`.L$0 = null;
      `$continuation`.L$1 = null;
      `$continuation`.L$2 = null;
      `$continuation`.label = 3;
      return if (var2.await(`$continuation`) === var8) var8 else Unit.INSTANCE;
   }

   private class FlushRequest(parent: Job?) {
      private final val done: CompletableJob

      init {
         this.done = JobKt.Job(parent);
      }

      public fun complete(): Boolean {
         return this.done.complete();
      }

      public suspend fun await() {
         val var10000: Any = this.done.join(`$completion`);
         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }
   }
}
