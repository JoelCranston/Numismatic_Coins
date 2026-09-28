package io.ktor.utils.io

import io.ktor.utils.io.LookAheadSuspendSession.awaitAtLeast.1
import io.ktor.utils.io.core.ByteReadPacketKt
import java.nio.Buffer
import java.nio.ByteBuffer
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlinx.io.Source
import kotlinx.io.SourcesKt

public class LookAheadSuspendSession(channel: ByteReadChannel) {
   private final val channel: ByteReadChannel

   init {
      this.channel = channel;
   }

   public fun request(skip: Int, atLeast: Int): ByteBuffer? {
      if (ByteReadPacketKt.getRemaining(this.channel.getReadBuffer()) < skip + atLeast) {
         return null;
      } else {
         val buffer: ByteBuffer = ByteReadPacketKt.preview(this.channel.getReadBuffer(), LookAheadSuspendSession::request$lambda$0);
         if (skip > 0) {
            ((Buffer)buffer).position(buffer.position() + skip);
         }

         return buffer;
      }
   }

   public suspend fun awaitAtLeast(min: Int): Boolean {
      var `$continuation`: Continuation;
      label29: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label29;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (ByteReadPacketKt.getRemaining(this.channel.getReadBuffer()) >= min) {
               return Boxing.boxBoolean(true);
            }

            val var10000: ByteReadChannel = this.channel;
            `$continuation`.I$0 = min;
            `$continuation`.label = 1;
            if (var10000.awaitContent(min, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            min = `$continuation`.I$0;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return Boxing.boxBoolean(ByteReadPacketKt.getRemaining(this.channel.getReadBuffer()) >= (long)min);
   }

   public fun consumed(count: Int) {
      ByteReadPacketKt.discard(this.channel.getReadBuffer(), (long)count);
   }

   @JvmStatic
   fun `request$lambda$0`(it: Source): ByteBuffer {
      return ByteBuffer.wrap(SourcesKt.readByteArray(it));
   }
}
