package io.ktor.websocket

private const val SERVER_MAX_WINDOW_BITS: String = "server_max_window_bits"
private const val CLIENT_NO_CONTEXT_TAKEOVER: String = "client_no_context_takeover"
private const val SERVER_NO_CONTEXT_TAKEOVER: String = "server_no_context_takeover"
private const val CLIENT_MAX_WINDOW_BITS: String = "client_max_window_bits"
private const val PERMESSAGE_DEFLATE: String = "permessage-deflate"
private const val MAX_WINDOW_BITS: Int = 15
private const val MIN_WINDOW_BITS: Int = 8

private fun Frame.isCompressed(): Boolean {
   return `$this$isCompressed`.getRsv1() && (`$this$isCompressed` is Frame.Text || `$this$isCompressed` is Frame.Binary);
}

@JvmSynthetic
fun `access$isCompressed`(`$receiver`: Frame): Boolean {
   return isCompressed(`$receiver`);
}
