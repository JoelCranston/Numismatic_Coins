package io.ktor.websocket

import io.ktor.util.cio.ByteBufferPoolKt
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.pool.ObjectPool
import io.ktor.websocket.RawWebSocketJvm.special..inlined.observable.1
import io.ktor.websocket.RawWebSocketJvm.special..inlined.observable.2
import java.nio.ByteBuffer
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.properties.Delegates
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelKt
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

@SourceDebugExtension(["SMAP\nRawWebSocketJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RawWebSocketJvm.kt\nio/ktor/websocket/RawWebSocketJvm\n+ 2 Delegates.kt\nkotlin/properties/Delegates\n*L\n1#1,103:1\n33#2,3:104\n33#2,3:107\n*S KotlinDebug\n*F\n+ 1 RawWebSocketJvm.kt\nio/ktor/websocket/RawWebSocketJvm\n*L\n56#1:104,3\n60#1:107,3\n*E\n"])
internal class RawWebSocketJvm(input: ByteReadChannel,
      output: ByteWriteChannel,
      maxFrameSize: Long = 2147483647L,
      masking: Boolean = false,
      coroutineContext: CoroutineContext,
      pool: ObjectPool<ByteBuffer> = ByteBufferPoolKt.getKtorDefaultPool()
   ) :
   WebSocketSession {
   private final val socketJob: CompletableJob
   private final val filtered: Channel<Frame>
   public open val coroutineContext: CoroutineContext

   public open val incoming: ReceiveChannel<Frame>
      public open get() {
         return this.filtered;
      }


   public open val outgoing: SendChannel<Frame>
      public open get() {
         return this.writer.getOutgoing();
      }


   public open val extensions: List<WebSocketExtension<*>>
      public open get() {
         return CollectionsKt.emptyList();
      }


   public open var maxFrameSize: Long
      public open get() {
         return (this.maxFrameSize$delegate.getValue(this, $$delegatedProperties[0]) as java.lang.Number).longValue();
      }

      public open set(<set-?>) {
         this.maxFrameSize$delegate.setValue(this, $$delegatedProperties[0], var1);
      }


   public open var masking: Boolean
      public open get() {
         return this.masking$delegate.getValue(this, $$delegatedProperties[1]) as java.lang.Boolean;
      }

      public open set(<set-?>) {
         this.masking$delegate.setValue(this, $$delegatedProperties[1], var1);
      }


   internal final val writer: WebSocketWriter
   internal final val reader: WebSocketReader

   init {
      this.socketJob = JobKt.Job(coroutineContext.get(Job.Key));
      this.filtered = ChannelKt.Channel$default(0, null, null, 6, null);
      this.coroutineContext = coroutineContext.plus(this.socketJob).plus(new CoroutineName("raw-ws"));
      var `this_$iv`: Delegates = Delegates.INSTANCE;
      this.maxFrameSize$delegate = new 1(maxFrameSize, this);
      `this_$iv` = Delegates.INSTANCE;
      this.masking$delegate = new 2(masking, this);
      this.writer = new WebSocketWriter(output, this.getCoroutineContext(), masking, pool);
      this.reader = new WebSocketReader(input, this.getCoroutineContext(), maxFrameSize, pool);
      BuildersKt.launch$default(this, null, null, new io.ktor.websocket.RawWebSocketJvm.1(this, null), 3, null);
      this.socketJob.complete();
   }

   public override suspend fun flush() {
      val var10000: Any = this.writer.flush(`$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   @Deprecated(message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = ["kotlinx.coroutines.cancel"]), level = DeprecationLevel.ERROR)
   public override fun terminate() {
      SendChannel.DefaultImpls.close$default(this.getOutgoing(), null, 1, null);
      this.socketJob.complete();
   }
}
