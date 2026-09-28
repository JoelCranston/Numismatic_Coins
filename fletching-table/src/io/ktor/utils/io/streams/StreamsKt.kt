@file:SourceDebugExtension(["SMAP\nStreams.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Streams.kt\nio/ktor/utils/io/streams/StreamsKt\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n*L\n1#1,57:1\n195#2,28:58\n*S KotlinDebug\n*F\n+ 1 Streams.kt\nio/ktor/utils/io/streams/StreamsKt\n*L\n33#1:58,28\n*E\n"])

package io.ktor.utils.io.streams

import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.SinkByteWriteChannelKt
import java.io.InputStream
import java.io.OutputStream
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.CoreKt
import kotlinx.io.JvmCoreKt
import kotlinx.io.Segment
import kotlinx.io.SegmentKt
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.SourcesJvmKt
import kotlinx.io.unsafe.UnsafeBufferOperations

public fun InputStream.asInput(): Source {
   return CoreKt.buffered(JvmCoreKt.asSource(`$this$asInput`));
}

public fun Source.inputStream(): InputStream {
   return SourcesJvmKt.asInputStream(`$this$inputStream`);
}

public fun OutputStream.writePacket(packet: Source) {
   packet.transferTo(JvmCoreKt.asSink(`$this$writePacket`));
}

public fun OutputStream.writePacket(block: (Sink) -> Unit) {
   val builder: Buffer = new Buffer();
   block.invoke(builder);
   writePacket(`$this$writePacket`, builder);
}

public fun InputStream.readPacketAtLeast(min: Int = 1): Source {
   val buffer: Buffer = new Buffer();
   val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
   val `tail$iv`: Segment = buffer.writableSegment(min);
   val `data$iv`: ByteArray = `tail$iv`.dataAsByteArray(false);
   val var10001: Int = `tail$iv`.getLimit();
   val read: Int = `$this$readPacketAtLeast`.read(`data$iv`, var10001, `data$iv`.length - var10001);
   val `bytesWritten$iv`: Int = if (read < 0) 0 else read;
   if ((if (read < 0) 0 else read) == min) {
      `tail$iv`.writeBackData(`data$iv`, `bytesWritten$iv`);
      `tail$iv`.setLimit(`tail$iv`.getLimit() + `bytesWritten$iv`);
      buffer.setSizeMut(buffer.getSizeMut() + (long)`bytesWritten$iv`);
   } else {
      if (0 > `bytesWritten$iv` || `bytesWritten$iv` > `tail$iv`.getRemainingCapacity()) {
         throw new IllegalStateException(
            ("Invalid number of bytes written: $`bytesWritten$iv`. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString()
         );
      }

      if (`bytesWritten$iv` != 0) {
         `tail$iv`.writeBackData(`data$iv`, `bytesWritten$iv`);
         `tail$iv`.setLimit(`tail$iv`.getLimit() + `bytesWritten$iv`);
         buffer.setSizeMut(buffer.getSizeMut() + (long)`bytesWritten$iv`);
      } else if (SegmentKt.isEmpty(`tail$iv`)) {
         buffer.recycleTail();
      }
   }

   return buffer;
}

@JvmSynthetic
fun `readPacketAtLeast$default`(var0: InputStream, var1: Int, var2: Int, var3: Any): Source {
   if ((var2 and 1) != 0) {
      var1 = 1;
   }

   return readPacketAtLeast(var0, var1);
}

public fun OutputStream.asByteWriteChannel(): ByteWriteChannel {
   return SinkByteWriteChannelKt.asByteWriteChannel(JvmCoreKt.asSink(`$this$asByteWriteChannel`));
}
