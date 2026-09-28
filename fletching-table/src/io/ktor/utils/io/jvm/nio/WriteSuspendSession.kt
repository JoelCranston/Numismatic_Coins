package io.ktor.utils.io.jvm.nio

import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperations_jvmKt
import io.ktor.utils.io.core.OutputArraysJVMKt
import io.ktor.utils.io.jvm.nio.WriteSuspendSession.written.1
import java.nio.Buffer
import java.nio.ByteBuffer
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.io.Sink

public class WriteSuspendSession(channel: ByteWriteChannel) {
   public final val channel: ByteWriteChannel
   private final val byteBuffer: ByteBuffer

   init {
      this.channel = channel;
      this.byteBuffer = ByteBuffer.allocate(8192);
   }

   public fun request(count: Int): ByteBuffer? {
      return this.byteBuffer;
   }

   public fun tryAwait(count: Int) {
      val var10000: Sink = this.channel.getWriteBuffer();
      val var10001: ByteBuffer = this.byteBuffer;
      OutputArraysJVMKt.writeByteBuffer(var10000, var10001);
   }

   public suspend fun written(rc: Int) {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            ((Buffer)this.byteBuffer).flip();
            val var10000: ByteWriteChannel = this.channel;
            val var10001: ByteBuffer = this.byteBuffer;
            `$continuation`.I$0 = rc;
            `$continuation`.label = 1;
            if (ByteWriteChannelOperations_jvmKt.writeFully(var10000, var10001, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            rc = `$continuation`.I$0;
            ResultKt.throwOnFailure(`$result`);
            break;
         case 2:
            rc = `$continuation`.I$0;
            ResultKt.throwOnFailure(`$result`);
            return Unit.INSTANCE;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ((Buffer)this.byteBuffer).clear();
      val var7: ByteWriteChannel = this.channel;
      `$continuation`.I$0 = rc;
      `$continuation`.label = 2;
      return if (var7.flush(`$continuation`) === var5) var5 else Unit.INSTANCE;
   }
}
