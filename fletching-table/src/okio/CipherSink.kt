package okio

import javax.crypto.Cipher
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCipherSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CipherSink.kt\nokio/CipherSink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,148:1\n1#2:149\n85#3:150\n*S KotlinDebug\n*F\n+ 1 CipherSink.kt\nokio/CipherSink\n*L\n47#1:150\n*E\n"])
public class CipherSink(sink: BufferedSink, cipher: Cipher) : Sink {
   private final val sink: BufferedSink
   public final val cipher: Cipher
   private final val blockSize: Int
   private final var closed: Boolean

   init {
      this.sink = sink;
      this.cipher = cipher;
      this.blockSize = this.cipher.getBlockSize();
      if (this.blockSize <= 0) {
         throw new IllegalArgumentException(("Block cipher required ${this.cipher}").toString());
      }
   }

   @Throws(java/io/IOException::class)
   public override fun write(source: Buffer, byteCount: Long) {
      -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);
      if (this.closed) {
         throw new IllegalStateException("closed".toString());
      } else {
         var remaining: Long = byteCount;

         while (remaining > 0L) {
            remaining -= this.update(source, remaining);
         }
      }
   }

   private fun update(source: Buffer, remaining: Long): Int {
      val var10000: Segment = source.head;
      var size: Int = (int)Math.min(remaining, (long)(var10000.limit - var10000.pos));
      val buffer: Buffer = this.sink.getBuffer();

      var outputSize: Int;
      for (outputSize = this.cipher.getOutputSize(size); outputSize > 8192; outputSize = this.cipher.getOutputSize(size)) {
         if (size <= this.blockSize) {
            val var12: BufferedSink = this.sink;
            val var10001: ByteArray = this.cipher.update(source.readByteArray(remaining));
            var12.write(var10001);
            return (int)remaining;
         }

         size -= this.blockSize;
      }

      val var10: Segment = buffer.writableSegment$okio(outputSize);
      val var11: Int = this.cipher.update(var10000.data, var10000.pos, size, var10.data, var10.limit);
      var10.limit += var11;
      buffer.setSize$okio(buffer.size() + (long)var11);
      if (var10.pos == var10.limit) {
         buffer.head = var10.pop();
         SegmentPool.recycle(var10);
      }

      this.sink.emitCompleteSegments();
      source.setSize$okio(source.size() - (long)size);
      var10000.pos += size;
      if (var10000.pos == var10000.limit) {
         source.head = var10000.pop();
         SegmentPool.recycle(var10000);
      }

      return size;
   }

   public override fun flush() {
      this.sink.flush();
   }

   public override fun timeout(): Timeout {
      return this.sink.timeout();
   }

   @Throws(java/io/IOException::class)
   public override fun close() {
      if (!this.closed) {
         this.closed = true;
         var thrown: java.lang.Throwable = this.doFinal();

         try {
            this.sink.close();
         } catch (var3: java.lang.Throwable) {
            if (thrown == null) {
               thrown = var3;
            }
         }

         if (thrown != null) {
            throw thrown;
         }
      }
   }

   private fun doFinal(): Throwable? {
      val outputSize: Int = this.cipher.getOutputSize(0);
      if (outputSize == 0) {
         return null;
      } else if (outputSize > 8192) {
         try {
            val var10000: BufferedSink = this.sink;
            val var10001: ByteArray = this.cipher.doFinal();
            var10000.write(var10001);
            return null;
         } catch (var6: java.lang.Throwable) {
            return var6;
         }
      } else {
         var thrown: java.lang.Throwable = null;
         val buffer: Buffer = this.sink.getBuffer();
         val s: Segment = buffer.writableSegment$okio(outputSize);

         try {
            val e: Int = this.cipher.doFinal(s.data, s.limit);
            s.limit += e;
            buffer.setSize$okio(buffer.size() + (long)e);
         } catch (var7: java.lang.Throwable) {
            thrown = var7;
         }

         if (s.pos == s.limit) {
            buffer.head = s.pop();
            SegmentPool.recycle(s);
         }

         return thrown;
      }
   }
}
