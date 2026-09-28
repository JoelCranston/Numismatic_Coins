package okio.internal

import java.io.IOException
import java.net.Socket
import java.net.SocketTimeoutException
import java.util.logging.Level
import okio.AsyncTimeout

internal class SocketAsyncTimeout(socket: Socket) : AsyncTimeout {
   private final val socket: Socket

   init {
      this.socket = socket;
   }

   protected override fun newTimeoutException(cause: IOException?): IOException {
      val ioe: SocketTimeoutException = new SocketTimeoutException("timeout");
      if (cause != null) {
         ioe.initCause(cause);
      }

      return ioe;
   }

   protected override fun timedOut() {
      try {
         this.socket.close();
      } catch (var2: Exception) {
         _JavaIoKt.access$getLogger$p().log(Level.WARNING, "Failed to close timed out socket ${this.socket}", var2);
      } catch (var3: AssertionError) {
         if (!_JavaIoKt.isAndroidGetsocknameError(var3)) {
            throw var3;
         }

         _JavaIoKt.access$getLogger$p().log(Level.WARNING, "Failed to close timed out socket ${this.socket}", var3);
      }
   }
}
