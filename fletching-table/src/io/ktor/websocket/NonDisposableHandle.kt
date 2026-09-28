package io.ktor.websocket

import kotlinx.coroutines.DisposableHandle

internal data object NonDisposableHandle : DisposableHandle {
   public override fun dispose() {
   }

   public override fun toString(): String {
      return "NonDisposableHandle";
   }

   public override fun hashCode(): Int {
      return 207988788;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is NonDisposableHandle;
      }
   }
}
