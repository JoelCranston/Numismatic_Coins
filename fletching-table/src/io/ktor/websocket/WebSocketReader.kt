package io.ktor.websocket

import io.ktor.util.NIOKt
import io.ktor.util.cio.ByteBufferPoolKt
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperations_jvmKt
import io.ktor.utils.io.pool.ObjectPool
import io.ktor.websocket.WebSocketReader.readerJob.1
import java.nio.Buffer
import java.nio.ByteBuffer
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.enums.EnumEntries
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelKt
import kotlinx.coroutines.channels.ReceiveChannel

public class WebSocketReader(byteChannel: ByteReadChannel,
      coroutineContext: CoroutineContext,
      maxFrameSize: Long,
      pool: ObjectPool<ByteBuffer> = ByteBufferPoolKt.getKtorDefaultPool()
   ) :
   CoroutineScope {
   private final val byteChannel: ByteReadChannel
   public open val coroutineContext: CoroutineContext
   public final var maxFrameSize: Long
   private final var state: io.ktor.websocket.WebSocketReader.State
   private final val frameParser: FrameParser
   private final val collector: SimpleFrameCollector
   private final val queue: Channel<Frame>
   private final val readerJob: Job

   public final val incoming: ReceiveChannel<Frame>
      public final get() {
         return this.queue;
      }


   init {
      this.byteChannel = byteChannel;
      this.coroutineContext = coroutineContext;
      this.maxFrameSize = maxFrameSize;
      this.state = WebSocketReader.State.HEADER;
      this.frameParser = new FrameParser();
      this.collector = new SimpleFrameCollector();
      this.queue = ChannelKt.Channel$default(8, null, null, 6, null);
      this.readerJob = BuildersKt.launch(this, new CoroutineName("ws-reader"), CoroutineStart.ATOMIC, new 1(pool, this, null));
   }

   private suspend fun readLoop(buffer: ByteBuffer) {
      var `$continuation`: Continuation;
      label54: {
         if (`$completion` is io.ktor.websocket.WebSocketReader.readLoop.1) {
            `$continuation` = `$completion` as io.ktor.websocket.WebSocketReader.readLoop.1;
            if (((`$completion` as io.ktor.websocket.WebSocketReader.readLoop.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label54;
            }
         }

         `$continuation` = new io.ktor.websocket.WebSocketReader.readLoop.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            ((Buffer)buffer).clear();
            break;
         case 1:
            buffer = `$continuation`.L$0 as ByteBuffer;
            ResultKt.throwOnFailure(`$result`);
            if ((`$result` as java.lang.Number).intValue() == -1) {
               this.state = WebSocketReader.State.CLOSED;
               return Unit.INSTANCE;
            }

            ((Buffer)buffer).flip();
            `$continuation`.L$0 = buffer;
            `$continuation`.label = 2;
            if (this.parseLoop(buffer, `$continuation`) === var5) {
               return var5;
            }

            buffer.compact();
            break;
         case 2:
            buffer = `$continuation`.L$0 as ByteBuffer;
            ResultKt.throwOnFailure(`$result`);
            buffer.compact();
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (this.state != WebSocketReader.State.CLOSED) {
         var var10000: ByteReadChannel = this.byteChannel;
         `$continuation`.L$0 = buffer;
         `$continuation`.label = 1;
         var10000 = (ByteReadChannel)ByteReadChannelOperations_jvmKt.readAvailable(var10000, buffer, `$continuation`);
         if (var10000 === var5) {
            return var5;
         }

         if ((var10000 as java.lang.Number).intValue() == -1) {
            this.state = WebSocketReader.State.CLOSED;
            break;
         }

         ((Buffer)buffer).flip();
         `$continuation`.L$0 = buffer;
         `$continuation`.label = 2;
         if (this.parseLoop(buffer, `$continuation`) === var5) {
            return var5;
         }

         buffer.compact();
      }

      return Unit.INSTANCE;
   }

   private suspend fun parseLoop(buffer: ByteBuffer) {
      var `$continuation`: Continuation;
      label64: {
         if (`$completion` is io.ktor.websocket.WebSocketReader.parseLoop.1) {
            `$continuation` = `$completion` as io.ktor.websocket.WebSocketReader.parseLoop.1;
            if (((`$completion` as io.ktor.websocket.WebSocketReader.parseLoop.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label64;
            }
         }

         `$continuation` = new io.ktor.websocket.WebSocketReader.parseLoop.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            break;
         case 1:
            buffer = `$continuation`.L$0 as ByteBuffer;
            ResultKt.throwOnFailure(`$result`);
            break;
         case 2:
            buffer = `$continuation`.L$0 as ByteBuffer;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (buffer.hasRemaining()) {
         switch (WebSocketReader.WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
            case 1:
               this.frameParser.frame(buffer);
               if (!this.frameParser.getBodyReady()) {
                  return Unit.INSTANCE;
               }

               this.state = WebSocketReader.State.BODY;
               if (this.frameParser.getLength() > 2147483647L || this.frameParser.getLength() > this.maxFrameSize) {
                  throw new FrameTooBigException(this.frameParser.getLength());
               }

               this.collector.start((int)this.frameParser.getLength(), buffer);
               `$continuation`.L$0 = buffer;
               `$continuation`.label = 1;
               if (this.handleFrameIfProduced(`$continuation`) === var5) {
                  return var5;
               }
               break;
            case 2:
               this.collector.handle(buffer);
               `$continuation`.L$0 = buffer;
               `$continuation`.label = 2;
               if (this.handleFrameIfProduced(`$continuation`) === var5) {
                  return var5;
               }
               break;
            case 3:
               return Unit.INSTANCE;
            default:
               throw new NoWhenBranchMatchedException();
         }
      }

      return Unit.INSTANCE;
   }

   private suspend fun handleFrameIfProduced() {
      var `$continuation`: Continuation;
      label32: {
         if (`$completion` is io.ktor.websocket.WebSocketReader.handleFrameIfProduced.1) {
            `$continuation` = `$completion` as io.ktor.websocket.WebSocketReader.handleFrameIfProduced.1;
            if (((`$completion` as io.ktor.websocket.WebSocketReader.handleFrameIfProduced.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label32;
            }
         }

         `$continuation` = new io.ktor.websocket.WebSocketReader.handleFrameIfProduced.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0: {
            ResultKt.throwOnFailure(`$result`);
            if (this.collector.getHasRemaining()) {
               return Unit.INSTANCE;
            }

            this.state = if (this.frameParser.getFrameType() === FrameType.CLOSE) WebSocketReader.State.CLOSED else WebSocketReader.State.HEADER;
            val `$this$handleFrameIfProduced_u24lambda_u240`: FrameParser = this.frameParser;
            val var8: Frame = Frame.Companion
               .byType(
                  this.frameParser.getFin(),
                  `$this$handleFrameIfProduced_u24lambda_u240`.getFrameType(),
                  NIOKt.moveToByteArray(this.collector.take(`$this$handleFrameIfProduced_u24lambda_u240`.getMaskKey())),
                  `$this$handleFrameIfProduced_u24lambda_u240`.getRsv1(),
                  `$this$handleFrameIfProduced_u24lambda_u240`.getRsv2(),
                  `$this$handleFrameIfProduced_u24lambda_u240`.getRsv3()
               );
            val var10000: Channel = this.queue;
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var8);
            `$continuation`.label = 1;
            if (var10000.send(var8, `$continuation`) === var7) {
               return var7;
            }
            break;
         }
         case 1: {
            val frame: Frame = `$continuation`.L$0 as Frame;
            ResultKt.throwOnFailure(`$result`);
            break;
         }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      this.frameParser.bodyComplete();
      return Unit.INSTANCE;
   }

   private enum class State {
      HEADER,
      BODY,
      CLOSED
      @JvmStatic
      fun getEntries(): EnumEntries<WebSocketReader.State> {
         return $ENTRIES;
      }
   }
}
