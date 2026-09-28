package io.ktor.websocket

import java.nio.ByteBuffer

// $VF: Class flags could not be determined
internal class UtilsKt {
   @JvmStatic
   fun ByteBuffer.xor(other: ByteBuffer) {
      UtilsKt__UtilsJvmKt.xor(`$this$xor`, other);
   }

   @JvmStatic
   fun getOUTGOING_CHANNEL_CAPACITY(): Int {
      return UtilsKt__UtilsJvmKt.getOUTGOING_CHANNEL_CAPACITY();
   }

   @JvmStatic
   fun Byte.xor(other: Byte): Byte {
      return UtilsKt__UtilsKt.xor(`$this$xor`, other);
   }

   @JvmStatic
   fun Boolean.flagAt(at: Int): Int {
      return UtilsKt__UtilsKt.flagAt(`$this$flagAt`, at);
   }
}
