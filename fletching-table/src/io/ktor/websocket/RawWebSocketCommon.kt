package io.ktor.websocket

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.websocket.RawWebSocketCommon.writerJob.1
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelKt
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

internal class RawWebSocketCommon(input: ByteReadChannel,
      output: ByteWriteChannel,
      maxFrameSize: Long = 2147483647L,
      masking: Boolean = false,
      coroutineContext: CoroutineContext
   ) :
   WebSocketSession {
   private final val input: ByteReadChannel
   private final val output: ByteWriteChannel
   public open var maxFrameSize: Long
   public open var masking: Boolean
   private final val socketJob: CompletableJob
   private final val _incoming: Channel<Frame>
   private final val _outgoing: Channel<Any>
   private final var lastOpcode: Int
   public open val coroutineContext: CoroutineContext

   public open val incoming: ReceiveChannel<Frame>
      public open get() {
         return this._incoming;
      }


   public open val outgoing: SendChannel<Frame>
      public open get() {
         return this._outgoing;
      }


   public open val extensions: List<WebSocketExtension<*>>
      public open get() {
         return CollectionsKt.emptyList();
      }


   private final val writerJob: Job
   private final val readerJob: Job

   init {
      this.input = input;
      this.output = output;
      this.maxFrameSize = maxFrameSize;
      this.masking = masking;
      this.socketJob = JobKt.Job(coroutineContext.get(Job.Key));
      this._incoming = ChannelKt.Channel$default(8, null, null, 6, null);
      this._outgoing = ChannelKt.Channel$default(8, null, null, 6, null);
      this.coroutineContext = coroutineContext.plus(this.socketJob).plus(new CoroutineName("raw-ws"));
      this.writerJob = BuildersKt.launch(this, new CoroutineName("ws-writer"), CoroutineStart.ATOMIC, new 1(this, null));
      this.readerJob = BuildersKt.launch(
         this, new CoroutineName("ws-reader"), CoroutineStart.ATOMIC, new io.ktor.websocket.RawWebSocketCommon.readerJob.1(this, null)
      );
      this.socketJob.complete();
   }

   public override suspend fun flush() {
      var `$continuation`: Continuation;
      label68: {
         if (`$completion` is io.ktor.websocket.RawWebSocketCommon.flush.1) {
            `$continuation` = `$completion` as io.ktor.websocket.RawWebSocketCommon.flush.1;
            if (((`$completion` as io.ktor.websocket.RawWebSocketCommon.flush.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label68;
            }
         }

         `$continuation` = new io.ktor.websocket.RawWebSocketCommon.flush.1(this, `$completion`);
      }

      var var2: RawWebSocketCommon.FlushRequest;
      var var8: Any;
      label58: {
         val `$result`: Any = `$continuation`.result;
         var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var var15: RawWebSocketCommon.FlushRequest;
         var var16: Int;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               var2 = new RawWebSocketCommon.FlushRequest(this.getCoroutineContext().get(Job.Key));
               var15 = var2;
               var16 = 0;

               var var19: Channel;
               try {
                  var19 = this._outgoing;
                  `$continuation`.L$0 = var2;
                  `$continuation`.L$1 = var15;
                  `$continuation`.I$0 = var16;
                  `$continuation`.label = 1;
                  var19 = (Channel)var19.send(var15, `$continuation`);
               } catch (var11: ClosedSendChannelException) {
                  var2.complete();
                  val var17: Job = this.writerJob;
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
               var15 = `$continuation`.L$1 as RawWebSocketCommon.FlushRequest;
               var2 = `$continuation`.L$0 as RawWebSocketCommon.FlushRequest;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  break;
               } catch (var13: ClosedSendChannelException) {
                  var15.complete();
                  val var10000: Job = this.writerJob;
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
               var15 = `$continuation`.L$1 as RawWebSocketCommon.FlushRequest;
               var2 = `$continuation`.L$0 as RawWebSocketCommon.FlushRequest;
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
            val var20: Job = this.writerJob;
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

   @Deprecated(message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = ["kotlinx.coroutines.cancel"]), level = DeprecationLevel.ERROR)
   public override fun terminate() {
      SendChannel.DefaultImpls.close$default(this.getOutgoing(), null, 1, null);
      this.socketJob.complete();
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
