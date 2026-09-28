@file:JvmName(name = "ByteOrderJVMKt")

package io.ktor.utils.io.bits

public inline fun Short.reverseByteOrder(): Short {
   return java.lang.Short.reverseBytes(`$this$reverseByteOrder`);
}

public inline fun Int.reverseByteOrder(): Int {
   return Integer.reverseBytes(`$this$reverseByteOrder`);
}

public inline fun Long.reverseByteOrder(): Long {
   return java.lang.Long.reverseBytes(`$this$reverseByteOrder`);
}

public inline fun Float.reverseByteOrder(): Float {
   return java.lang.Float.intBitsToFloat(Integer.reverseBytes(java.lang.Float.floatToRawIntBits(`$this$reverseByteOrder`)));
}

public inline fun Double.reverseByteOrder(): Double {
   return java.lang.Double.longBitsToDouble(java.lang.Long.reverseBytes(java.lang.Double.doubleToRawLongBits(`$this$reverseByteOrder`)));
}
