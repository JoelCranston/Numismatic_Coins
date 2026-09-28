package okio

import java.util.zip.CRC32
import java.util.zip.Deflater
import kotlin.jvm.internal.SourceDebugExtension
import okio.internal._ZlibJvmKt

@SourceDebugExtension(["SMAP\nGzipSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GzipSink.kt\nokio/GzipSink\n+ 2 RealBufferedSink.kt\nokio/RealBufferedSink\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,152:1\n51#2:153\n1#3:154\n85#4:155\n*S KotlinDebug\n*F\n+ 1 GzipSink.kt\nokio/GzipSink\n*L\n62#1:153\n130#1:155\n*E\n"])
public class GzipSink(sink: Sink) : Sink {
   private final val sink: RealBufferedSink
   public final val deflater: Deflater
   private final val deflaterSink: DeflaterSink
   private final var closed: Boolean
   private final val crc: CRC32

   init {
      this.sink = new RealBufferedSink(sink);
      this.deflater = new Deflater(_ZlibJvmKt.getDEFAULT_COMPRESSION(), true);
      this.deflaterSink = new DeflaterSink(this.sink, this.deflater);
      this.crc = new CRC32();
      val var5: Buffer = this.sink.bufferField;
      this.sink.bufferField.writeShort(8075);
      var5.writeByte(8);
      var5.writeByte(0);
      var5.writeInt(0);
      var5.writeByte(0);
      var5.writeByte(0);
   }

   @Throws(java/io/IOException::class)
   public override fun write(source: Buffer, byteCount: Long) {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
      } else if (byteCount != 0L) {
         this.updateCrc(source, byteCount);
         this.deflaterSink.write(source, byteCount);
      }
   }

   @Throws(java/io/IOException::class)
   public override fun flush() {
      this.deflaterSink.flush();
   }

   public override fun timeout(): Timeout {
      return this.sink.timeout();
   }

   @Throws(java/io/IOException::class)
   public override fun close() {
      if (!this.closed) {
         var thrown: java.lang.Throwable = null;

         try {
            this.deflaterSink.finishDeflate$okio();
            this.writeFooter();
         } catch (var3: java.lang.Throwable) {
            thrown = var3;
         }

         try {
            this.deflater.end();
         } catch (var5: java.lang.Throwable) {
            if (thrown == null) {
               thrown = var5;
            }
         }

         try {
            this.sink.close();
         } catch (var4: java.lang.Throwable) {
            if (thrown == null) {
               thrown = var4;
            }
         }

         this.closed = true;
         if (thrown != null) {
            throw thrown;
         }
      }
   }

   private fun writeFooter() {
      this.sink.writeIntLe((int)this.crc.getValue());
      this.sink.writeIntLe((int)this.deflater.getBytesRead());
   }

   private fun updateCrc(buffer: Buffer, byteCount: Long) {
      var var10000: Segment = buffer.head;
      var head: Segment = var10000;
      var remaining: Long = byteCount;

      while (remaining > 0L) {
         val segmentLength: Int = (int)Math.min(remaining, (long)(head.limit - head.pos));
         this.crc.update(head.data, head.pos, segmentLength);
         remaining -= segmentLength;
         var10000 = head.next;
         head = var10000;
      }
   }

   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "deflater", imports = []), level = DeprecationLevel.ERROR)
   @JvmName(name = "-deprecated_deflater")
   public fun deflater(): Deflater {
      return this.deflater;
   }
}
