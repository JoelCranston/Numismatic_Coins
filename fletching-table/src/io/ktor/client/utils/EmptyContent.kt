package io.ktor.client.utils

import io.ktor.http.content.OutgoingContent

public data object EmptyContent : OutgoingContent.NoContent {
   public open val contentLength: Long

   public override fun toString(): String {
      return "EmptyContent";
   }

   public override fun hashCode(): Int {
      return 1450860306;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is EmptyContent;
      }
   }
}
