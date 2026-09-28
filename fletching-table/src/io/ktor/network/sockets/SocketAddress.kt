package io.ktor.network.sockets

public sealed class SocketAddress protected constructor() {
   internal abstract val address: java.net.SocketAddress
}
