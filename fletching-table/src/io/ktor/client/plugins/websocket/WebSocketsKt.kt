@file:SourceDebugExtension(["SMAP\nWebSockets.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSockets.kt\nio/ktor/client/plugins/websocket/WebSocketsKt\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,253:1\n21#2:254\n21#2:264\n69#3:255\n84#3,8:256\n69#3:265\n84#3,8:266\n*S KotlinDebug\n*F\n+ 1 WebSockets.kt\nio/ktor/client/plugins/websocket/WebSocketsKt\n*L\n20#1:254\n23#1:264\n20#1:255\n20#1:256,8\n23#1:265\n23#1:266,8\n*E\n"])

package io.ktor.client.plugins.websocket

import io.ktor.util.AttributeKey
import io.ktor.utils.io.InternalAPI
import io.ktor.websocket.WebSocketExtension
import kotlin.jvm.internal.SourceDebugExtension
import org.slf4j.Logger

private final val REQUEST_EXTENSIONS_KEY: AttributeKey<List<WebSocketExtension<*>>>

@InternalAPI
public final val WEBSOCKETS_KEY: AttributeKey<WebSockets>

internal final val LOGGER: Logger

@JvmSynthetic
fun `access$getREQUEST_EXTENSIONS_KEY$p`(): AttributeKey {
   return REQUEST_EXTENSIONS_KEY;
}
