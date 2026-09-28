package io.ktor.websocket

@JvmSynthetic
internal class UtilsKt__UtilsKt {
   @JvmStatic
   internal inline infix fun Byte.xor(other: Byte): Byte {
      return (byte)(`$this$xor` xor other);
   }

   @JvmStatic
   internal inline fun Boolean.flagAt(at: Int): Int {
      return if (`$this$flagAt`) 1 shl at else 0;
   }
}
