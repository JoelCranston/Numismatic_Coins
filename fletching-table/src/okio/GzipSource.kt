package okio

import java.io.EOFException
import java.io.IOException
import java.util.zip.CRC32
import java.util.zip.Inflater
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nGzipSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GzipSource.kt\nokio/GzipSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 4 GzipSource.kt\nokio/-GzipSourceExtensions\n+ 5 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,222:1\n1#2:223\n63#3:224\n63#3:226\n63#3:228\n63#3:229\n63#3:230\n63#3:232\n63#3:234\n204#4:225\n204#4:227\n204#4:231\n204#4:233\n88#5:235\n*S KotlinDebug\n*F\n+ 1 GzipSource.kt\nokio/GzipSource\n*L\n103#1:224\n105#1:226\n117#1:228\n118#1:229\n120#1:230\n131#1:232\n142#1:234\n104#1:225\n115#1:227\n128#1:231\n139#1:233\n185#1:235\n*E\n"])
public class GzipSource(source: Source) : Source {
   private final var section: Byte
   private final val source: RealBufferedSource
   private final val inflater: Inflater
   private final val inflaterSource: InflaterSource
   private final val crc: CRC32

   init {
      this.source = new RealBufferedSource(source);
      this.inflater = new Inflater(true);
      this.inflaterSource = new InflaterSource(this.source, this.inflater);
      this.crc = new CRC32();
   }

   @Throws(java/io/IOException::class)
   public override fun read(sink: Buffer, byteCount: Long): Long {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
      } else if (byteCount == 0L) {
         return 0L;
      } else {
         if (this.section == 0) {
            this.consumeHeader();
            this.section = 1;
         }

         if (this.section == 1) {
            val offset: Long = sink.size();
            val result: Long = this.inflaterSource.read(sink, byteCount);
            if (result != -1L) {
               this.updateCrc(sink, offset, result);
               return result;
            }

            this.section = 2;
         }

         if (this.section == 2) {
            this.consumeTrailer();
            this.section = 3;
            if (!this.source.exhausted()) {
               throw new IOException("gzip finished without exhausting source");
            }
         }

         return -1L;
      }
   }

   @Throws(java/io/IOException::class)
   private fun consumeHeader() {
      this.source.require(10L);
      val flags: Int = this.source.bufferField.getByte(3L);
      val var8: Boolean = (flags shr 1 and 1) == 1;
      if ((flags shr 1 and 1) == 1) {
         this.updateCrc(this.source.bufferField, 0L, 10L);
      }

      this.checkEqual("ID1ID2", 8075, this.source.readShort());
      this.source.skip(8L);
      if ((flags shr 2 and 1) == 1) {
         this.source.require(2L);
         if (var8) {
            this.updateCrc(this.source.bufferField, 0L, 2L);
         }

         val var13: Long = this.source.bufferField.readShortLe() and '\uffff';
         this.source.require(var13);
         if (var8) {
            this.updateCrc(this.source.bufferField, 0L, var13);
         }

         this.source.skip(var13);
      }

      if ((flags shr 3 and 1) == 1) {
         val var14: Long = this.source.indexOf((byte)0);
         if (var14 == -1L) {
            throw new EOFException();
         }

         if (var8) {
            this.updateCrc(this.source.bufferField, 0L, var14 + 1L);
         }

         this.source.skip(var14 + 1L);
      }

      if ((flags shr 4 and 1) == 1) {
         val indexx: Long = this.source.indexOf((byte)0);
         if (indexx == -1L) {
            throw new EOFException();
         }

         if (var8) {
            this.updateCrc(this.source.bufferField, 0L, indexx + 1L);
         }

         this.source.skip(indexx + 1L);
      }

      if (var8) {
         this.checkEqual("FHCRC", this.source.readShortLe(), (short)((int)this.crc.getValue()));
         this.crc.reset();
      }
   }

   @Throws(java/io/IOException::class)
   private fun consumeTrailer() {
      this.checkEqual("CRC", this.source.readIntLe(), (int)this.crc.getValue());
      this.checkEqual("ISIZE", this.source.readIntLe(), (int)this.inflater.getBytesWritten());
   }

   public override fun timeout(): Timeout {
      return this.source.timeout();
   }

   @Throws(java/io/IOException::class)
   public override fun close() {
      this.inflaterSource.close();
   }

   private fun updateCrc(buffer: Buffer, offset: Long, byteCount: Long) {
      var offsetx: Long = offset;
      var byteCountx: Long = byteCount;
      var var10000: Segment = buffer.head;
      var s: Segment = var10000;

      while (offsetx >= s.limit - s.pos) {
         offsetx -= s.limit - s.pos;
         var10000 = s.next;
         s = var10000;
      }

      while (byteCountx > 0L) {
         val pos: Int = (int)(s.pos + offsetx);
         val toUpdate: Int = (int)Math.min((long)(s.limit - (int)((long)s.pos + offsetx)), byteCountx);
         this.crc.update(s.data, pos, toUpdate);
         byteCountx -= toUpdate;
         offsetx = 0L;
         var10000 = s.next;
         s = var10000;
      }
   }

   private fun checkEqual(name: String, expected: Int, actual: Int) {
      if (actual != expected) {
         throw new IOException(
            "$name: actual 0x${StringsKt.padStart(-SegmentedByteString.toHexString(actual), 8, '0')} != expected 0x${StringsKt.padStart(
               -SegmentedByteString.toHexString(expected), 8, '0'
            )}"
         );
      }
   }
}
