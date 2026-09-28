package okio

import java.io.EOFException
import java.io.IOException
import java.util.zip.DataFormatException
import java.util.zip.Inflater
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nInflaterSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InflaterSource.kt\nokio/InflaterSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,132:1\n1#2:133\n85#3:134\n*S KotlinDebug\n*F\n+ 1 InflaterSource.kt\nokio/InflaterSource\n*L\n66#1:134\n*E\n"])
public class InflaterSource internal constructor(source: BufferedSource, inflater: Inflater) : Source {
   private final val source: BufferedSource
   private final val inflater: Inflater
   private final var bufferBytesHeldByInflater: Int
   private final var closed: Boolean

   init {
      this.source = source;
      this.inflater = inflater;
   }

   public constructor(source: Source, inflater: Inflater) : this(Okio.buffer(source), inflater)
   @Throws(java/io/IOException::class)
   public override fun read(sink: Buffer, byteCount: Long): Long {
      do {
         val bytesInflated: Long = this.readOrInflate(sink, byteCount);
         if (bytesInflated > 0L) {
            return bytesInflated;
         }

         if (this.inflater.finished() || this.inflater.needsDictionary()) {
            return -1L;
         }
      } while (!this.source.exhausted());

      throw new EOFException("source exhausted prematurely");
   }

   @Throws(java/io/IOException::class)
   public fun readOrInflate(sink: Buffer, byteCount: Long): Long {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
      } else if (this.closed) {
         throw new IllegalStateException("closed".toString());
      } else if (byteCount == 0L) {
         return 0L;
      } else {
         try {
            val tail: Segment = sink.writableSegment$okio(1);
            val e: Int = (int)Math.min(byteCount, (long)(8192 - tail.limit));
            this.refill();
            val bytesInflated: Int = this.inflater.inflate(tail.data, tail.limit, e);
            this.releaseBytesAfterInflate();
            if (bytesInflated > 0) {
               tail.limit += bytesInflated;
               sink.setSize$okio(sink.size() + (long)bytesInflated);
               return bytesInflated;
            } else {
               if (tail.pos == tail.limit) {
                  sink.head = tail.pop();
                  SegmentPool.recycle(tail);
               }

               return 0L;
            }
         } catch (var10: DataFormatException) {
            throw new IOException(var10);
         }
      }
   }

   @Throws(java/io/IOException::class)
   public fun refill(): Boolean {
      if (!this.inflater.needsInput()) {
         return false;
      } else if (this.source.exhausted()) {
         return true;
      } else {
         val var10000: Segment = this.source.getBuffer().head;
         this.bufferBytesHeldByInflater = var10000.limit - var10000.pos;
         this.inflater.setInput(var10000.data, var10000.pos, this.bufferBytesHeldByInflater);
         return false;
      }
   }

   private fun releaseBytesAfterInflate() {
      if (this.bufferBytesHeldByInflater != 0) {
         val toRelease: Int = this.bufferBytesHeldByInflater - this.inflater.getRemaining();
         this.bufferBytesHeldByInflater -= toRelease;
         this.source.skip((long)toRelease);
      }
   }

   public override fun timeout(): Timeout {
      return this.source.timeout();
   }

   @Throws(java/io/IOException::class)
   public override fun close() {
      if (!this.closed) {
         this.inflater.end();
         this.closed = true;
         this.source.close();
      }
   }
}
