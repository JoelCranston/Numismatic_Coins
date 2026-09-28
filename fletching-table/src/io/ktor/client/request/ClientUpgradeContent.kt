package io.ktor.client.request

import io.ktor.http.Headers
import io.ktor.http.content.OutgoingContent
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.intrinsics.IntrinsicsKt

@InternalAPI
public abstract class ClientUpgradeContent : OutgoingContent.NoContent {
   private final val content: ByteChannel by LazyKt.lazy(ClientUpgradeContent::content_delegate$lambda$0)
      private final get() {
         return this.content$delegate.getValue() as ByteChannel;
      }


   public final val output: ByteWriteChannel
      public final get() {
         return this.getContent();
      }


   public suspend fun pipeTo(output: ByteWriteChannel) {
      val var10000: Any = ByteReadChannelOperationsKt.copyAndClose(this.getContent(), output, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   public abstract fun verify(headers: Headers) {
   }

   @JvmStatic
   fun `content_delegate$lambda$0`(): ByteChannel {
      return new ByteChannel(false, 1, null);
   }
}
