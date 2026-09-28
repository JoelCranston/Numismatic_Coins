package okio

import java.io.IOException
import java.util.zip.Deflater
import kotlin.jvm.internal.SourceDebugExtension
import okio.internal._ZlibJvmKt

@SourceDebugExtension(["SMAP\nDeflaterSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeflaterSink.kt\nokio/DeflaterSink\n+ 2 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,140:1\n85#2:141\n*S KotlinDebug\n*F\n+ 1 DeflaterSink.kt\nokio/DeflaterSink\n*L\n39#1:141\n*E\n"])
public class DeflaterSink internal constructor(sink: BufferedSink, deflater: Deflater) : Sink {
   private final val sink: BufferedSink
   private final val deflater: Deflater
   private final var closed: Boolean

   init {
      this.sink = sink;
      this.deflater = deflater;
   }

   public constructor(sink: Sink, deflater: Deflater) : this(Okio.buffer(sink), deflater)
   @Throws(java/io/IOException::class)
   public override fun write(source: Buffer, byteCount: Long) {
      -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);
      var remaining: Long = byteCount;

      while (remaining > 0L) {
         val var10000: Segment = source.head;
         val toDeflate: Int = (int)Math.min(remaining, (long)(var10000.limit - var10000.pos));
         this.deflater.setInput(var10000.data, var10000.pos, toDeflate);
         this.deflate(false);
         source.setSize$okio(source.size() - (long)toDeflate);
         var10000.pos += toDeflate;
         if (var10000.pos == var10000.limit) {
            source.head = var10000.pop();
            SegmentPool.recycle(var10000);
         }

         remaining -= toDeflate;
      }

      this.deflater.setInput(_ZlibJvmKt.getEMPTY_BYTE_ARRAY(), 0, 0);
   }

   private fun deflate(syncFlush: Boolean) {
      val buffer: Buffer = this.sink.getBuffer();

      while (true) {
         val s: Segment = buffer.writableSegment$okio(1);

         var var5: Int;
         try {
            var5 = if (syncFlush) this.deflater.deflate(s.data, s.limit, 8192 - s.limit, 2) else this.deflater.deflate(s.data, s.limit, 8192 - s.limit);
         } catch (var7: NullPointerException) {
            throw new IOException("Deflater already closed", var7);
         }

         if (var5 > 0) {
            s.limit += var5;
            buffer.setSize$okio(buffer.size() + (long)var5);
            this.sink.emitCompleteSegments();
         } else if (this.deflater.needsInput()) {
            if (s.pos == s.limit) {
               buffer.head = s.pop();
               SegmentPool.recycle(s);
            }

            return;
         }
      }
   }

   @Throws(java/io/IOException::class)
   public override fun flush() {
      this.deflate(true);
      this.sink.flush();
   }

   internal fun finishDeflate() {
      this.deflater.finish();
      this.deflate(false);
   }

   @Throws(java/io/IOException::class)
   public override fun close() {
      if (!this.closed) {
         var thrown: java.lang.Throwable = null;

         try {
            this.finishDeflate$okio();
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

   public override fun timeout(): Timeout {
      return this.sink.timeout();
   }

   public override fun toString(): String {
      return "DeflaterSink(${this.sink})";
   }
}
