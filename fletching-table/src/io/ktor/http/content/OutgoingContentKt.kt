package io.ktor.http.content

import io.ktor.utils.io.InternalAPI

@InternalAPI
public fun OutgoingContent.isEmpty(): Boolean {
   return `$this$isEmpty` is OutgoingContent.NoContent
      || `$this$isEmpty` is OutgoingContent.ContentWrapper && isEmpty((`$this$isEmpty` as OutgoingContent.ContentWrapper).delegate());
}
