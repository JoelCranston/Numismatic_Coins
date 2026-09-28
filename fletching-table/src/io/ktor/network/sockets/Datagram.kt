package io.ktor.network.sockets

import io.ktor.utils.io.core.ByteReadPacketKt
import kotlinx.io.Source

public class Datagram(packet: Source, address: SocketAddress) {
   public final val packet: Source
   public final val address: SocketAddress

   init {
      this.packet = packet;
      this.address = address;
      if (ByteReadPacketKt.getRemaining(this.packet) > 65535L) {
         throw new IllegalArgumentException(("Datagram size limit exceeded: ${ByteReadPacketKt.getRemaining(this.packet)} of possible 65535").toString());
      }
   }
}
