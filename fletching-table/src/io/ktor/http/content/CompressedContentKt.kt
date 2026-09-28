package io.ktor.http.content

import io.ktor.util.ContentEncoder
import io.ktor.utils.io.ByteChannelCtorKt
import io.ktor.utils.io.ByteReadChannel
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public fun OutgoingContent.compressed(contentEncoder: ContentEncoder, coroutineContext: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext): OutgoingContent? {
   val var10000: OutgoingContent;
   if (`$this$compressed` is OutgoingContent.ReadChannelContent) {
      var10000 = new CompressedReadChannelResponse(`$this$compressed`, CompressedContentKt::compressed$lambda$0, contentEncoder, coroutineContext);
   } else if (`$this$compressed` is OutgoingContent.WriteChannelContent) {
      var10000 = new CompressedWriteChannelResponse(`$this$compressed` as OutgoingContent.WriteChannelContent, contentEncoder, coroutineContext);
   } else if (`$this$compressed` is OutgoingContent.ByteArrayContent) {
      var10000 = new CompressedReadChannelResponse(`$this$compressed`, CompressedContentKt::compressed$lambda$1, contentEncoder, coroutineContext);
   } else if (`$this$compressed` is OutgoingContent.NoContent) {
      var10000 = null;
   } else if (`$this$compressed` is OutgoingContent.ProtocolUpgrade) {
      var10000 = null;
   } else {
      if (`$this$compressed` !is OutgoingContent.ContentWrapper) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = compressed((`$this$compressed` as OutgoingContent.ContentWrapper).delegate(), contentEncoder, coroutineContext);
   }

   return var10000;
}

@JvmSynthetic
fun `compressed$default`(var0: OutgoingContent, var1: ContentEncoder, var2: CoroutineContext, var3: Int, var4: Any): OutgoingContent {
   if ((var3 and 2) != 0) {
      var2 = EmptyCoroutineContext.INSTANCE;
   }

   return compressed(var0, var1, var2);
}

fun `compressed$lambda$0`(`$this_compressed`: OutgoingContent): ByteReadChannel {
   return (`$this_compressed` as OutgoingContent.ReadChannelContent).readFrom();
}

fun `compressed$lambda$1`(`$this_compressed`: OutgoingContent): ByteReadChannel {
   return ByteChannelCtorKt.ByteReadChannel$default((`$this_compressed` as OutgoingContent.ByteArrayContent).bytes(), 0, 0, 6, null);
}
