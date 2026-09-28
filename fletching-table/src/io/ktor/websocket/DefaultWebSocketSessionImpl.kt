package io.ktor.websocket

import io.ktor.util.logging.LoggerJvmKt
import io.ktor.utils.io.core.BytePacketBuilderKt
import io.ktor.websocket.DefaultWebSocketSessionImpl.runIncomingProcessor.1
import io.ktor.websocket.DefaultWebSocketSessionImpl.start.2
import io.ktor.websocket.Frame.Ping
import io.ktor.websocket.Frame.Pong
import java.util.ArrayList
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CompletableDeferredKt
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScopeKt
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelIterator
import kotlinx.coroutines.channels.ChannelKt
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.io.Sink
import org.slf4j.Logger

@SourceDebugExtension(["SMAP\nDefaultWebSocketSession.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultWebSocketSession.kt\nio/ktor/websocket/DefaultWebSocketSessionImpl\n+ 2 Logger.kt\nio/ktor/util/logging/LoggerKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,401:1\n38#2,2:402\n38#2,2:404\n38#2,2:406\n1803#3,3:408\n1803#3,3:411\n*S KotlinDebug\n*F\n+ 1 DefaultWebSocketSession.kt\nio/ktor/websocket/DefaultWebSocketSessionImpl\n*L\n153#1:402,2\n287#1:404,2\n307#1:406,2\n367#1:408,3\n370#1:411,3\n*E\n"])
internal class DefaultWebSocketSessionImpl(raw: WebSocketSession, pingIntervalMillis: Long, timeoutMillis: Long) : DefaultWebSocketSession, WebSocketSession {
   private final val raw: WebSocketSession
   private final val closeReasonRef: CompletableDeferred<CloseReason>
   private final val filtered: Channel<Frame>
   private final val outgoingToBeProcessed: Channel<Frame>
   private final val context: CompletableJob
   private final val _extensions: MutableList<WebSocketExtension<*>>

   public open val incoming: ReceiveChannel<Frame>
      public open get() {
         return this.filtered;
      }


   public open val outgoing: SendChannel<Frame>
      public open get() {
         return this.outgoingToBeProcessed;
      }


   public open val extensions: List<WebSocketExtension<*>>
      public open get() {
         return this._extensions;
      }


   public open val coroutineContext: CoroutineContext

   public open var masking: Boolean
      public open get() {
         return this.raw.getMasking();
      }

      public open set(value) {
         this.raw.setMasking(value);
      }


   public open var maxFrameSize: Long
      public open get() {
         return this.raw.getMaxFrameSize();
      }

      public open set(value) {
         this.raw.setMaxFrameSize(value);
      }


   public open var pingIntervalMillis: Long
      public open set(newValue) {
         this.pingIntervalMillis = newValue;
         this.runOrCancelPinger();
      }


   public open var timeoutMillis: Long
      public open set(newValue) {
         this.timeoutMillis = newValue;
         this.runOrCancelPinger();
      }


   public open val closeReason: Deferred<CloseReason?>

   init {
      this.raw = raw;
      this.pinger = null;
      this.closeReasonRef = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
      this.filtered = ChannelKt.Channel$default(8, null, null, 6, null);
      this.outgoingToBeProcessed = ChannelKt.Channel$default(UtilsKt.getOUTGOING_CHANNEL_CAPACITY(), null, null, 6, null);
      this.closed = 0;
      this.context = JobKt.Job(this.raw.getCoroutineContext().get(Job.Key));
      this._extensions = new ArrayList<>();
      this.started = 0;
      this.coroutineContext = this.raw.getCoroutineContext().plus(this.context).plus(new CoroutineName("ws-default"));
      this.pingIntervalMillis = pingIntervalMillis;
      this.timeoutMillis = timeoutMillis;
      this.closeReason = this.closeReasonRef;
   }

   public override fun start(negotiatedExtensions: List<WebSocketExtension<*>>) {
      if (!started$FU.compareAndSet(this, 0, 1)) {
         throw new IllegalStateException(("WebSocket session $this is already started.").toString());
      } else {
         val incomingJob: Logger = DefaultWebSocketSessionKt.getLOGGER();
         if (LoggerJvmKt.isTraceEnabled(incomingJob)) {
            incomingJob.trace(
               "Starting default WebSocketSession($this) with negotiated extensions: ${CollectionsKt.joinToString$default(
                  negotiatedExtensions, null, null, null, 0, null, null, 63, null
               )}"
            );
         }

         this._extensions.addAll(negotiatedExtensions);
         this.runOrCancelPinger();
         BuildersKt.launch$default(
            this, null, null, new 2(this.runIncomingProcessor(PingPongKt.ponger(this, this.getOutgoing())), this.runOutgoingProcessor(), this, null), 3, null
         );
      }
   }

   public suspend fun goingAway(message: String = ...) {
      val var10000: Any = sendCloseSequence$default(this, new CloseReason(CloseReason.Codes.GOING_AWAY, message), null, `$completion`, 2, null);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   public override suspend fun flush() {
      val var10000: Any = this.raw.flush(`$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   @Deprecated(message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = ["kotlinx.coroutines.cancel"]), level = DeprecationLevel.ERROR)
   public override fun terminate() {
      Job.DefaultImpls.cancel$default(this.context, null, 1, null);
      CoroutineScopeKt.cancel$default(this.raw, null, 1, null);
   }

   private fun runIncomingProcessor(ponger: SendChannel<Ping>): Job {
      return BuildersKt.launch$default(
         this,
         DefaultWebSocketSessionKt.access$getIncomingProcessorCoroutineName$p().plus(Dispatchers.getUnconfined()),
         null,
         new 1(this, ponger, null),
         2,
         null
      );
   }

   private fun runOutgoingProcessor(): Job {
      return BuildersKt.launch(
         this,
         DefaultWebSocketSessionKt.access$getOutgoingProcessorCoroutineName$p().plus(Dispatchers.getUnconfined()),
         CoroutineStart.UNDISPATCHED,
         new io.ktor.websocket.DefaultWebSocketSessionImpl.runOutgoingProcessor.1(this, null)
      );
   }

   private suspend fun outgoingProcessorLoop() {
      var `$continuation`: Continuation;
      label74: {
         if (`$completion` is io.ktor.websocket.DefaultWebSocketSessionImpl.outgoingProcessorLoop.1) {
            `$continuation` = `$completion` as io.ktor.websocket.DefaultWebSocketSessionImpl.outgoingProcessorLoop.1;
            if (((`$completion` as io.ktor.websocket.DefaultWebSocketSessionImpl.outgoingProcessorLoop.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label74;
            }
         }

         `$continuation` = new io.ktor.websocket.DefaultWebSocketSessionImpl.outgoingProcessorLoop.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var2: ChannelIterator;
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            var2 = this.outgoingToBeProcessed.iterator();
            `$continuation`.L$0 = var2;
            `$continuation`.L$1 = null;
            `$continuation`.L$2 = null;
            `$continuation`.label = 1;
            var10000 = var2.hasNext(`$continuation`);
            if (var10000 === var10) {
               return var10;
            }
            break;
         case 1:
            var2 = `$continuation`.L$0 as ChannelIterator;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         case 2:
            val framex: Frame = `$continuation`.L$0 as Frame;
            ResultKt.throwOnFailure(`$result`);
            return Unit.INSTANCE;
         case 3:
            val processedFrame: Frame = `$continuation`.L$2 as Frame;
            val frame: Frame = `$continuation`.L$1 as Frame;
            var2 = `$continuation`.L$0 as ChannelIterator;
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = var2;
            `$continuation`.L$1 = null;
            `$continuation`.L$2 = null;
            `$continuation`.label = 1;
            var10000 = var2.hasNext(`$continuation`);
            if (var10000 === var10) {
               return var10;
            }
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      do {
         if (!var10000 as java.lang.Boolean) {
            return Unit.INSTANCE;
         }

         val var12: Frame = var2.next() as Frame;
         val var13: Logger = DefaultWebSocketSessionKt.getLOGGER();
         if (LoggerJvmKt.isTraceEnabled(var13)) {
            var13.trace("Sending $var12 from session $this");
         }

         if (var12 is Frame.Close) {
            val var10001: CloseReason = FrameCommonKt.readReason(var12 as Frame.Close);
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var12);
            `$continuation`.label = 2;
            if (sendCloseSequence$default(this, var10001, null, `$continuation`, 2, null) === var10) {
               return var10;
            }

            return Unit.INSTANCE;
         }

         val var14: Frame = if (var12 !is Frame.Text)
            (if (var12 is Frame.Binary) this.processOutgoingExtensions(var12) else var12)
            else
            this.processOutgoingExtensions(var12);
         var10000 = this.raw.getOutgoing();
         `$continuation`.L$0 = var2;
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var12);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var14);
         `$continuation`.label = 3;
         if (((SendChannel<Frame>)var10000).send(var14, `$continuation`) === var10) {
            return var10;
         }

         `$continuation`.L$0 = var2;
         `$continuation`.L$1 = null;
         `$continuation`.L$2 = null;
         `$continuation`.label = 1;
         var10000 = var2.hasNext(`$continuation`);
      } while (var10000 != var10);

      return var10;
   }

   private suspend fun sendCloseSequence(reason: CloseReason?, exception: Throwable? = ...) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1057)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.verifyFinallyEx(FinallyProcessor.java:572)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:90)
      //
      // Bytecode:
      // 000: aload 3
      // 001: instanceof io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1
      // 004: ifeq 029
      // 007: aload 3
      // 008: checkcast io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1
      // 00b: astore 9
      // 00d: aload 9
      // 00f: getfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.label I
      // 012: ldc_w -2147483648
      // 015: iand
      // 016: ifeq 029
      // 019: aload 9
      // 01b: dup
      // 01c: getfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.label I
      // 01f: ldc_w -2147483648
      // 022: isub
      // 023: putfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.label I
      // 026: goto 034
      // 029: new io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1
      // 02c: dup
      // 02d: aload 0
      // 02e: aload 3
      // 02f: invokespecial io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.<init> (Lio/ktor/websocket/DefaultWebSocketSessionImpl;Lkotlin/coroutines/Continuation;)V
      // 032: astore 9
      // 034: aload 9
      // 036: getfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.result Ljava/lang/Object;
      // 039: astore 8
      // 03b: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03e: astore 10
      // 040: aload 9
      // 042: getfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.label I
      // 045: tableswitch 348 0 1 23 223
      // 05c: aload 8
      // 05e: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 061: aload 0
      // 062: invokespecial io/ktor/websocket/DefaultWebSocketSessionImpl.tryClose ()Z
      // 065: ifne 06c
      // 068: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 06b: areturn
      // 06c: invokestatic io/ktor/websocket/DefaultWebSocketSessionKt.getLOGGER ()Lorg/slf4j/Logger;
      // 06f: astore 4
      // 071: bipush 0
      // 072: istore 5
      // 074: aload 4
      // 076: invokestatic io/ktor/util/logging/LoggerJvmKt.isTraceEnabled (Lorg/slf4j/Logger;)Z
      // 079: ifeq 0b3
      // 07c: aload 4
      // 07e: astore 7
      // 080: bipush 0
      // 081: istore 6
      // 083: new java/lang/StringBuilder
      // 086: dup
      // 087: invokespecial java/lang/StringBuilder.<init> ()V
      // 08a: ldc_w "Sending Close Sequence for session "
      // 08d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 090: aload 0
      // 091: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      // 094: ldc_w " with reason "
      // 097: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 09a: aload 1
      // 09b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      // 09e: ldc_w " and exception "
      // 0a1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0a4: aload 2
      // 0a5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      // 0a8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0ab: aload 7
      // 0ad: swap
      // 0ae: invokeinterface org/slf4j/Logger.trace (Ljava/lang/String;)V 2
      // 0b3: nop
      // 0b4: aload 0
      // 0b5: getfield io/ktor/websocket/DefaultWebSocketSessionImpl.context Lkotlinx/coroutines/CompletableJob;
      // 0b8: invokeinterface kotlinx/coroutines/CompletableJob.complete ()Z 1
      // 0bd: pop
      // 0be: aload 1
      // 0bf: dup
      // 0c0: ifnonnull 0d1
      // 0c3: pop
      // 0c4: new io/ktor/websocket/CloseReason
      // 0c7: dup
      // 0c8: getstatic io/ktor/websocket/CloseReason$Codes.NORMAL Lio/ktor/websocket/CloseReason$Codes;
      // 0cb: ldc_w ""
      // 0ce: invokespecial io/ktor/websocket/CloseReason.<init> (Lio/ktor/websocket/CloseReason$Codes;Ljava/lang/String;)V
      // 0d1: astore 4
      // 0d3: nop
      // 0d4: aload 0
      // 0d5: invokespecial io/ktor/websocket/DefaultWebSocketSessionImpl.runOrCancelPinger ()V
      // 0d8: aload 4
      // 0da: invokevirtual io/ktor/websocket/CloseReason.getCode ()S
      // 0dd: getstatic io/ktor/websocket/CloseReason$Codes.CLOSED_ABNORMALLY Lio/ktor/websocket/CloseReason$Codes;
      // 0e0: invokevirtual io/ktor/websocket/CloseReason$Codes.getCode ()S
      // 0e3: if_icmpeq 149
      // 0e6: aload 0
      // 0e7: getfield io/ktor/websocket/DefaultWebSocketSessionImpl.raw Lio/ktor/websocket/WebSocketSession;
      // 0ea: invokeinterface io/ktor/websocket/WebSocketSession.getOutgoing ()Lkotlinx/coroutines/channels/SendChannel; 1
      // 0ef: new io/ktor/websocket/Frame$Close
      // 0f2: dup
      // 0f3: aload 4
      // 0f5: invokespecial io/ktor/websocket/Frame$Close.<init> (Lio/ktor/websocket/CloseReason;)V
      // 0f8: aload 9
      // 0fa: aload 9
      // 0fc: aload 1
      // 0fd: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 100: putfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.L$0 Ljava/lang/Object;
      // 103: aload 9
      // 105: aload 2
      // 106: putfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.L$1 Ljava/lang/Object;
      // 109: aload 9
      // 10b: aload 4
      // 10d: putfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.L$2 Ljava/lang/Object;
      // 110: aload 9
      // 112: bipush 1
      // 113: putfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.label I
      // 116: invokeinterface kotlinx/coroutines/channels/SendChannel.send (Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 3
      // 11b: dup
      // 11c: aload 10
      // 11e: if_acmpne 148
      // 121: aload 10
      // 123: areturn
      // 124: aload 9
      // 126: getfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.L$2 Ljava/lang/Object;
      // 129: checkcast io/ktor/websocket/CloseReason
      // 12c: astore 4
      // 12e: aload 9
      // 130: getfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.L$1 Ljava/lang/Object;
      // 133: checkcast java/lang/Throwable
      // 136: astore 2
      // 137: aload 9
      // 139: getfield io/ktor/websocket/DefaultWebSocketSessionImpl$sendCloseSequence$1.L$0 Ljava/lang/Object;
      // 13c: checkcast io/ktor/websocket/CloseReason
      // 13f: astore 1
      // 140: nop
      // 141: aload 8
      // 143: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 146: aload 8
      // 148: pop
      // 149: aload 0
      // 14a: getfield io/ktor/websocket/DefaultWebSocketSessionImpl.closeReasonRef Lkotlinx/coroutines/CompletableDeferred;
      // 14d: aload 4
      // 14f: invokeinterface kotlinx/coroutines/CompletableDeferred.complete (Ljava/lang/Object;)Z 2
      // 154: pop
      // 155: aload 2
      // 156: ifnull 16f
      // 159: aload 0
      // 15a: getfield io/ktor/websocket/DefaultWebSocketSessionImpl.outgoingToBeProcessed Lkotlinx/coroutines/channels/Channel;
      // 15d: aload 2
      // 15e: invokeinterface kotlinx/coroutines/channels/Channel.close (Ljava/lang/Throwable;)Z 2
      // 163: pop
      // 164: aload 0
      // 165: getfield io/ktor/websocket/DefaultWebSocketSessionImpl.filtered Lkotlinx/coroutines/channels/Channel;
      // 168: aload 2
      // 169: invokeinterface kotlinx/coroutines/channels/Channel.close (Ljava/lang/Throwable;)Z 2
      // 16e: pop
      // 16f: goto 19d
      // 172: astore 5
      // 174: aload 0
      // 175: getfield io/ktor/websocket/DefaultWebSocketSessionImpl.closeReasonRef Lkotlinx/coroutines/CompletableDeferred;
      // 178: aload 4
      // 17a: invokeinterface kotlinx/coroutines/CompletableDeferred.complete (Ljava/lang/Object;)Z 2
      // 17f: pop
      // 180: aload 2
      // 181: ifnull 19a
      // 184: aload 0
      // 185: getfield io/ktor/websocket/DefaultWebSocketSessionImpl.outgoingToBeProcessed Lkotlinx/coroutines/channels/Channel;
      // 188: aload 2
      // 189: invokeinterface kotlinx/coroutines/channels/Channel.close (Ljava/lang/Throwable;)Z 2
      // 18e: pop
      // 18f: aload 0
      // 190: getfield io/ktor/websocket/DefaultWebSocketSessionImpl.filtered Lkotlinx/coroutines/channels/Channel;
      // 193: aload 2
      // 194: invokeinterface kotlinx/coroutines/channels/Channel.close (Ljava/lang/Throwable;)Z 2
      // 199: pop
      // 19a: aload 5
      // 19c: athrow
      // 19d: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 1a0: areturn
      // 1a1: new java/lang/IllegalStateException
      // 1a4: dup
      // 1a5: ldc_w "call to 'resume' before 'invoke' with coroutine"
      // 1a8: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 1ab: athrow
   }

   private fun tryClose(): Boolean {
      return closed$FU.compareAndSet(this, 0, 1);
   }

   private fun runOrCancelPinger() {
      val interval: Long = this.getPingIntervalMillis();
      val newPinger: SendChannel = if (this.closed != 0)
         null
         else
         (
            if (interval > 0L)
               PingPongKt.pinger(
                  this,
                  this.raw.getOutgoing(),
                  interval,
                  this.getTimeoutMillis(),
                  new io.ktor.websocket.DefaultWebSocketSessionImpl.runOrCancelPinger.newPinger.1(this, null)
               )
               else
               null
         );
      val var10000: SendChannel = pinger$FU.getAndSet(this, newPinger);
      if (var10000 != null) {
         SendChannel.DefaultImpls.close$default(var10000, null, 1, null);
      }

      if (newPinger != null) {
         ChannelResult.isSuccess-impl(newPinger.trySend-JP2dKIU(EmptyPong));
      }

      if (this.closed != 0 && newPinger != null) {
         this.runOrCancelPinger();
      }
   }

   private suspend fun checkMaxFrameSize(packet: Sink?, frame: Frame) {
      var `$continuation`: Continuation;
      label34: {
         if (`$completion` is io.ktor.websocket.DefaultWebSocketSessionImpl.checkMaxFrameSize.1) {
            `$continuation` = `$completion` as io.ktor.websocket.DefaultWebSocketSessionImpl.checkMaxFrameSize.1;
            if (((`$completion` as io.ktor.websocket.DefaultWebSocketSessionImpl.checkMaxFrameSize.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label34;
            }
         }

         `$continuation` = new io.ktor.websocket.DefaultWebSocketSessionImpl.checkMaxFrameSize.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var size: Int;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            size = frame.getData().length + (if (packet != null) BytePacketBuilderKt.getSize(packet) else 0);
            if (size <= this.getMaxFrameSize()) {
               return Unit.INSTANCE;
            }

            if (packet != null) {
               packet.close();
            }

            val var10000: WebSocketSession = this;
            val var10001: CloseReason = new CloseReason(CloseReason.Codes.TOO_BIG, "Frame is too big: $size. Max size is ${this.getMaxFrameSize()}");
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(packet);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(frame);
            `$continuation`.I$0 = size;
            `$continuation`.label = 1;
            if (WebSocketSessionKt.close(var10000, var10001, `$continuation`) === var7) {
               return var7;
            }
            break;
         case 1:
            size = `$continuation`.I$0;
            frame = `$continuation`.L$1 as Frame;
            packet = `$continuation`.L$0 as Sink;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      throw new FrameTooBigException(size);
   }

   private fun processIncomingExtensions(frame: Frame): Frame {
      val `$this$fold$iv`: java.lang.Iterable = this.getExtensions();
      var `accumulator$iv`: Any = frame;

      for (Object element$iv : $this$fold$iv) {
         `accumulator$iv` = (`element$iv` as WebSocketExtension).processIncomingFrame((Frame)`accumulator$iv`);
      }

      return (Frame)`accumulator$iv`;
   }

   private fun processOutgoingExtensions(frame: Frame): Frame {
      val `$this$fold$iv`: java.lang.Iterable = this.getExtensions();
      var `accumulator$iv`: Any = frame;

      for (Object element$iv : $this$fold$iv) {
         `accumulator$iv` = (`element$iv` as WebSocketExtension).processOutgoingFrame((Frame)`accumulator$iv`);
      }

      return (Frame)`accumulator$iv`;
   }

   public companion object {
      private final val EmptyPong: Pong
   }
}
