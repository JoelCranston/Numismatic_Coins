package io.ktor.client.utils

import io.ktor.client.utils.ContentKt.wrapHeaders.1
import io.ktor.client.utils.ContentKt.wrapHeaders.2
import io.ktor.client.utils.ContentKt.wrapHeaders.3
import io.ktor.client.utils.ContentKt.wrapHeaders.4
import io.ktor.client.utils.ContentKt.wrapHeaders.5
import io.ktor.http.Headers
import io.ktor.http.content.OutgoingContent

public fun OutgoingContent.wrapHeaders(block: (Headers) -> Headers): OutgoingContent {
   val var10000: OutgoingContent;
   if (`$this$wrapHeaders` is OutgoingContent.NoContent) {
      var10000 = new 1(block, `$this$wrapHeaders`);
   } else if (`$this$wrapHeaders` is OutgoingContent.ReadChannelContent) {
      var10000 = new 2(block, `$this$wrapHeaders`);
   } else if (`$this$wrapHeaders` is OutgoingContent.WriteChannelContent) {
      var10000 = new 3(block, `$this$wrapHeaders`);
   } else if (`$this$wrapHeaders` is OutgoingContent.ByteArrayContent) {
      var10000 = new 4(block, `$this$wrapHeaders`);
   } else if (`$this$wrapHeaders` is OutgoingContent.ProtocolUpgrade) {
      var10000 = new 5(block, `$this$wrapHeaders`);
   } else {
      if (`$this$wrapHeaders` !is OutgoingContent.ContentWrapper) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = wrapHeaders((`$this$wrapHeaders` as OutgoingContent.ContentWrapper).delegate(), block);
   }

   return var10000;
}
