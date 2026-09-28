package io.ktor.network.sockets

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel

public class Connection(socket: Socket, input: ByteReadChannel, output: ByteWriteChannel) {
   public final val socket: Socket
   public final val input: ByteReadChannel
   public final val output: ByteWriteChannel

   init {
      this.socket = socket;
      this.input = input;
      this.output = output;
   }
}
