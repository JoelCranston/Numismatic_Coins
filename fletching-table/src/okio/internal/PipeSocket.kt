package okio.internal

import okio.Pipe
import okio.Sink
import okio.Socket
import okio.Source

internal class PipeSocket(sinkPipe: Pipe, sourcePipe: Pipe) : Socket {
   public final val sinkPipe: Pipe
   public final val sourcePipe: Pipe

   public open val source: Source
      public open get() {
         return this.sourcePipe.source();
      }


   public open val sink: Sink
      public open get() {
         return this.sinkPipe.sink();
      }


   init {
      this.sinkPipe = sinkPipe;
      this.sourcePipe = sourcePipe;
   }

   public override fun cancel() {
      this.sourcePipe.cancel();
      this.sinkPipe.cancel();
   }
}
