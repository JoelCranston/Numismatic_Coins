package io.ktor.http

private fun isDelimiter(ch: Char): Boolean {
   return StringsKt.contains$default("\"(),/:;<=>?@[\\]{}", ch, false, 2, null);
}

@JvmSynthetic
fun `access$isDelimiter`(ch: Char): Boolean {
   return isDelimiter(ch);
}
