package io.ktor.network.sockets

import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions
import io.ktor.utils.io.ReaderJob
import io.ktor.utils.io.WriterJob
import io.ktor.utils.io.pool.ObjectPool
import java.nio.ByteBuffer
import java.nio.channels.ByteChannel
import java.nio.channels.SelectableChannel
import kotlin.coroutines.EmptyCoroutineContext

internal abstract class NIOSocketImpl<S extends SelectableChannel & ByteChannel> : SocketBase, ReadWriteSocket {
   public open val channel: Any
   public final val selector: SelectorManager
   public final val pool: ObjectPool<ByteBuffer>?
   private final val socketOptions: TCPClientSocketOptions?

   open fun NIOSocketImpl(channel: S, selector: SelectorManager, pool: ObjectPool<ByteBuffer>?, socketOptions: SocketOptions.TCPClientSocketOptions?) {
      super(EmptyCoroutineContext.INSTANCE);
      this.channel = (S)channel;
      this.selector = selector;
      this.pool = pool;
      this.socketOptions = socketOptions;
   }

   public override fun attachForReadingImpl(channel: io.ktor.utils.io.ByteChannel): WriterJob {
      return if (this.pool != null)
         CIOReaderKt.attachForReadingImpl(this, channel, this.getChannel(), this, this.selector, this.pool, this.socketOptions)
         else
         CIOReaderKt.attachForReadingDirectImpl(this, channel, this.getChannel(), this, this.selector, this.socketOptions);
   }

   public override fun attachForWritingImpl(channel: io.ktor.utils.io.ByteChannel): ReaderJob {
      return CIOWriterKt.attachForWritingDirectImpl(this, channel, this.getChannel(), this, this.selector, this.socketOptions);
   }

   internal override fun actualClose(): Throwable? {
      label31: {
         var var7: java.lang.Throwable;
         label32: {
            try {
               try {
                  this.getChannel().close();
                  super.close();
                  var7 = null;
                  break label32;
               } catch (var3: java.lang.Throwable) {
                  var7 = var3;
               }
            } catch (var4: java.lang.Throwable) {
               this.selector.notifyClosed(this);
            }

            this.selector.notifyClosed(this);
            return var7;
         }

         this.selector.notifyClosed(this);
         return var7;
      }
   }
}
