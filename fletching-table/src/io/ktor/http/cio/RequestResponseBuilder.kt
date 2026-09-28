package io.ktor.http.cio

import io.ktor.http.HttpMethod
import io.ktor.utils.io.core.BytePacketBuilderExtensions_jvmKt
import io.ktor.utils.io.core.BytePacketBuilderKt
import io.ktor.utils.io.core.StringsKt
import java.nio.ByteBuffer
import kotlinx.io.Sink
import kotlinx.io.Source

public class RequestResponseBuilder {
   private final val packet: Sink = BytePacketBuilderKt.BytePacketBuilder()

   public fun responseLine(version: CharSequence, status: Int, statusText: CharSequence) {
      StringsKt.writeText$default(this.packet, version, 0, 0, null, 14, null);
      this.packet.writeByte((byte)32);
      StringsKt.writeText$default(this.packet, java.lang.String.valueOf(status), 0, 0, null, 14, null);
      this.packet.writeByte((byte)32);
      StringsKt.writeText$default(this.packet, statusText, 0, 0, null, 14, null);
      this.packet.writeByte((byte)13);
      this.packet.writeByte((byte)10);
   }

   public fun requestLine(method: HttpMethod, uri: CharSequence, version: CharSequence) {
      StringsKt.writeText$default(this.packet, method.getValue(), 0, 0, null, 14, null);
      this.packet.writeByte((byte)32);
      StringsKt.writeText$default(this.packet, uri, 0, 0, null, 14, null);
      this.packet.writeByte((byte)32);
      StringsKt.writeText$default(this.packet, version, 0, 0, null, 14, null);
      this.packet.writeByte((byte)13);
      this.packet.writeByte((byte)10);
   }

   public fun line(line: CharSequence) {
      BytePacketBuilderKt.append$default(this.packet, line, 0, 0, 6, null);
      this.packet.writeByte((byte)13);
      this.packet.writeByte((byte)10);
   }

   public fun bytes(content: ByteArray, offset: Int = 0, length: Int = content.length) {
      BytePacketBuilderKt.writeFully(this.packet, content, offset, length);
   }

   public fun bytes(content: ByteBuffer) {
      BytePacketBuilderExtensions_jvmKt.writeFully(this.packet, content);
   }

   public fun headerLine(name: CharSequence, value: CharSequence) {
      BytePacketBuilderKt.append$default(this.packet, name, 0, 0, 6, null);
      BytePacketBuilderKt.append$default(this.packet, ": ", 0, 0, 6, null);
      BytePacketBuilderKt.append$default(this.packet, value, 0, 0, 6, null);
      this.packet.writeByte((byte)13);
      this.packet.writeByte((byte)10);
   }

   public fun emptyLine() {
      this.packet.writeByte((byte)13);
      this.packet.writeByte((byte)10);
   }

   public fun build(): Source {
      return BytePacketBuilderKt.build(this.packet);
   }

   public fun release() {
      this.packet.close();
   }
}
