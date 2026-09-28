package io.ktor.client.plugins.observer

import io.ktor.client.call.HttpClientCall
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.utils.io.ByteReadChannel
import kotlin.jvm.functions.Function0

@Deprecated(message = "Use 'replaceResponse' instead.", replaceWith = @ReplaceWith(expression = "replaceResponse { content }", imports = ["io.ktor.client.call.replaceResponse"]))
public fun HttpClientCall.wrapWithContent(content: ByteReadChannel): HttpClientCall {
   return io.ktor.client.call.DelegatedCallKt.replaceResponse$default(`$this$wrapWithContent`, null, DelegatedCallKt::wrapWithContent$lambda$0, 1, null);
}

@Deprecated(message = "Use 'replaceResponse' instead.", replaceWith = @ReplaceWith(expression = "replaceResponse { block() }", imports = ["io.ktor.client.call.replaceResponse"]))
public fun HttpClientCall.wrapWithContent(block: () -> ByteReadChannel): HttpClientCall {
   return io.ktor.client.call.DelegatedCallKt.replaceResponse$default(`$this$wrapWithContent`, null, DelegatedCallKt::wrapWithContent$lambda$1, 1, null);
}

@Deprecated(message = "Use 'replaceResponse' instead.", replaceWith = @ReplaceWith(expression = "replaceResponse(headers) { content }", imports = ["io.ktor.client.call.replaceResponse"]))
public fun HttpClientCall.wrap(content: ByteReadChannel, headers: Headers): HttpClientCall {
   return io.ktor.client.call.DelegatedCallKt.replaceResponse(`$this$wrap`, headers, DelegatedCallKt::wrap$lambda$0);
}

fun `wrapWithContent$lambda$0`(`$content`: ByteReadChannel, `$this$replaceResponse`: HttpResponse): ByteReadChannel {
   return `$content`;
}

fun `wrapWithContent$lambda$1`(`$block`: Function0, `$this$replaceResponse`: HttpResponse): ByteReadChannel {
   return `$block`.invoke() as ByteReadChannel;
}

fun `wrap$lambda$0`(`$content`: ByteReadChannel, `$this$replaceResponse`: HttpResponse): ByteReadChannel {
   return `$content`;
}
