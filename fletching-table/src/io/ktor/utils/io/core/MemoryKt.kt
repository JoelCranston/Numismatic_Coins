package io.ktor.utils.io.core

public fun <T> withMemory(size: Int, block: (ByteArray) -> Any): Any {
   return (T)block.invoke(new byte[size]);
}

public fun ByteArray.storeIntAt(index: Int, value: Int) {
   `$this$storeIntAt`[index] = (byte)(value shr 24);
   `$this$storeIntAt`[index + 1] = (byte)(value shr 16);
   `$this$storeIntAt`[index + 2] = (byte)(value shr 8);
   `$this$storeIntAt`[index + 3] = (byte)value;
}

/** @deprecated */
@Deprecated(message = "ByteArray instead", replaceWith = @ReplaceWith(expression = "ByteArray", imports = []))
@JvmSynthetic
fun `Memory$annotations`() {
}
