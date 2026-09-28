package io.ktor.utils.io.core

private fun orderOf(nioOrder: java.nio.ByteOrder): ByteOrder {
   return if (nioOrder === java.nio.ByteOrder.BIG_ENDIAN) ByteOrder.BIG_ENDIAN else ByteOrder.LITTLE_ENDIAN;
}

@JvmSynthetic
fun `access$orderOf`(nioOrder: java.nio.ByteOrder): ByteOrder {
   return orderOf(nioOrder);
}
