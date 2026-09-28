package io.ktor.network.sockets

import io.ktor.network.selector.SelectableBase
import io.ktor.network.sockets.SocketBase.close.1
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.ByteChannelUtilsKt
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import io.ktor.utils.io.ChannelJob
import io.ktor.utils.io.ReaderJob
import io.ktor.utils.io.WriterJob
import java.io.IOException
import java.util.concurrent.CancellationException
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt

@SourceDebugExtension(["SMAP\nSocketBase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SocketBase.kt\nio/ktor/network/sockets/SocketBase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,125:1\n65#1,25:126\n65#1,25:151\n119#1:176\n122#1,2:178\n122#1,2:181\n1#2:177\n1#2:180\n1#2:183\n1#2:184\n*S KotlinDebug\n*F\n+ 1 SocketBase.kt\nio/ktor/network/sockets/SocketBase\n*L\n49#1:126,25\n53#1:151,25\n95#1:176\n98#1:178,2\n99#1:181,2\n95#1:177\n98#1:180\n99#1:183\n*E\n"])
internal abstract class SocketBase : SelectableBase, ReadWriteSocket, CoroutineScope {
   private final val channelCompletionHandler: (Throwable?) -> Unit = SocketBase::channelCompletionHandler$lambda$0
   public open val socketContext: CompletableJob

   public open val coroutineContext: CoroutineContext
      public open get() {
         return this.getSocketContext();
      }


   private final val completedOrNotStarted: Boolean
   private final val exception: Throwable?

   open fun SocketBase(parent: CoroutineContext) {
      this.socketContext = JobKt.Job(parent.get(Job.Key));
   }

   public override fun dispose() {
      this.close();
   }

   public override fun close() {
      if (closeFlag$FU.compareAndSet(this, 0, 1)) {
         kotlinx.coroutines.BuildersKt.launch$default(this, new CoroutineName("socket-close"), null, new 1(this, null), 2, null);
      }
   }

   public override fun attachForReading(channel: ByteChannel): WriterJob {
      if (this.closeFlag != 0) {
         val var10: IOException = new IOException("Socket closed");
         ByteWriteChannelOperationsKt.close(channel, var10);
         throw var10;
      } else {
         val `j$iv`: ChannelJob = this.attachForReadingImpl(channel);
         if (!writerJob$FU.compareAndSet(this, null, `j$iv`)) {
            val var11: IllegalStateException = new IllegalStateException("reading channel has already been set");
            ByteWriteChannelOperationsKt.cancel(`j$iv`);
            throw var11;
         } else if (this.closeFlag != 0) {
            val `e$iv`: IOException = new IOException("Socket closed");
            ByteWriteChannelOperationsKt.cancel(`j$iv`);
            ByteWriteChannelOperationsKt.close(channel, `e$iv`);
            throw `e$iv`;
         } else {
            ByteChannelUtilsKt.attachJob(channel, `j$iv`);
            ByteWriteChannelOperationsKt.invokeOnCompletion(`j$iv`, this.channelCompletionHandler);
            return `j$iv` as WriterJob;
         }
      }
   }

   public override fun attachForWriting(channel: ByteChannel): ReaderJob {
      if (this.closeFlag != 0) {
         val var10: IOException = new IOException("Socket closed");
         ByteWriteChannelOperationsKt.close(channel, var10);
         throw var10;
      } else {
         val `j$iv`: ChannelJob = this.attachForWritingImpl(channel);
         if (!readerJob$FU.compareAndSet(this, null, `j$iv`)) {
            val var11: IllegalStateException = new IllegalStateException("writing channel has already been set");
            ByteWriteChannelOperationsKt.cancel(`j$iv`);
            throw var11;
         } else if (this.closeFlag != 0) {
            val `e$iv`: IOException = new IOException("Socket closed");
            ByteWriteChannelOperationsKt.cancel(`j$iv`);
            ByteWriteChannelOperationsKt.close(channel, `e$iv`);
            throw `e$iv`;
         } else {
            ByteChannelUtilsKt.attachJob(channel, `j$iv`);
            ByteWriteChannelOperationsKt.invokeOnCompletion(`j$iv`, this.channelCompletionHandler);
            return `j$iv` as ReaderJob;
         }
      }
   }

   public abstract fun attachForReadingImpl(channel: ByteChannel): WriterJob {
   }

   public abstract fun attachForWritingImpl(channel: ByteChannel): ReaderJob {
   }

   internal abstract fun actualClose(): Throwable? {
   }

   private fun checkChannels() {
      if (this.closeFlag != 0
         && (this.readerJob as ChannelJob == null || ByteWriteChannelOperationsKt.isCompleted(this.readerJob as ChannelJob))
         && (this.writerJob as ChannelJob == null || ByteWriteChannelOperationsKt.isCompleted(this.writerJob as ChannelJob))) {
         if (!actualCloseFlag$FU.compareAndSet(this, 0, 1)) {
            return;
         }

         var var10000: java.lang.Throwable;
         label55: {
            val var17: ChannelJob = this.readerJob as ChannelJob;
            if (this.readerJob as ChannelJob != null) {
               val var9: ChannelJob = if (ByteWriteChannelOperationsKt.isCancelled(var17)) var17 else null;
               if (var9 != null) {
                  val var6: CancellationException = ByteWriteChannelOperationsKt.getCancellationException(var9);
                  if (var6 != null) {
                     var10000 = var6.getCause();
                     break label55;
                  }
               }
            }

            var10000 = null;
         }

         label48: {
            val var19: ChannelJob = this.writerJob as ChannelJob;
            if (this.writerJob as ChannelJob != null) {
               val var10: ChannelJob = if (ByteWriteChannelOperationsKt.isCancelled(var19)) var19 else null;
               if (var10 != null) {
                  val `it$iv`: CancellationException = ByteWriteChannelOperationsKt.getCancellationException(var10);
                  if (`it$iv` != null) {
                     var10000 = `it$iv`.getCause();
                     break label48;
                  }
               }
            }

            var10000 = null;
         }

         val var15: java.lang.Throwable = this.combine(this.combine(var10000, var10000), this.actualClose$ktor_network());
         if (var15 == null) {
            this.getSocketContext().complete();
         } else {
            this.getSocketContext().completeExceptionally(var15);
         }
      }
   }

   private fun combine(e1: Throwable?, e2: Throwable?): Throwable? {
      val var10000: java.lang.Throwable;
      if (e1 == null) {
         var10000 = e2;
      } else if (e2 == null) {
         var10000 = e1;
      } else if (e1 === e2) {
         var10000 = e1;
      } else {
         ExceptionsKt.addSuppressed(e1, e2);
         var10000 = e1;
      }

      return var10000;
   }

   @JvmStatic
   fun `channelCompletionHandler$lambda$0`(`this$0`: SocketBase, it: java.lang.Throwable): Unit {
      `this$0`.checkChannels();
      return Unit.INSTANCE;
   }
}
