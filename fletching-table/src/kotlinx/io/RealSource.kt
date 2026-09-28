package kotlinx.io

import java.io.EOFException
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nRealSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealSource.kt\nkotlinx/io/RealSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -Util.kt\nkotlinx/io/_UtilKt\n*L\n1#1,162:1\n159#1:163\n160#1:165\n159#1:167\n160#1:169\n159#1:170\n160#1:172\n159#1:176\n160#1:178\n159#1:180\n160#1:182\n1#2:164\n1#2:166\n1#2:168\n1#2:171\n1#2:173\n1#2:177\n1#2:179\n1#2:181\n1#2:183\n38#3:174\n110#3:175\n*S KotlinDebug\n*F\n+ 1 RealSource.kt\nkotlinx/io/RealSource\n*L\n38#1:163\n38#1:165\n51#1:167\n51#1:169\n60#1:170\n60#1:172\n127#1:176\n127#1:178\n144#1:180\n144#1:182\n38#1:164\n51#1:168\n60#1:171\n127#1:177\n144#1:181\n74#1:174\n80#1:175\n*E\n"])
internal class RealSource(source: RawSource) : Source {
   public final val source: RawSource

   public final var closed: Boolean
      private set

   private final val bufferField: Buffer

   @InternalIoApi
   public open val buffer: Buffer
      public open get() {
         return this.bufferField;
      }


   init {
      this.source = source;
      this.bufferField = new Buffer();
   }

   public override fun readAtMostTo(sink: Buffer, byteCount: Long): Long {
      if (this.closed) {
         throw new IllegalStateException("Source is closed.".toString());
      } else {
         label21:
         if (byteCount < 0L) {
            throw new IllegalArgumentException(("byteCount: $byteCount").toString());
         } else {
            return if (this.bufferField.getSize() == 0L && this.source.readAtMostTo(this.bufferField, 8192L) == -1L)
               -1L
               else
               this.bufferField.readAtMostTo(sink, Math.min(byteCount, this.bufferField.getSize()));
         }
      }
   }

   public override fun exhausted(): Boolean {
      if (this.closed) {
         throw new IllegalStateException("Source is closed.".toString());
      } else {
         return this.bufferField.exhausted() && this.source.readAtMostTo(this.bufferField, 8192L) == -1L;
      }
   }

   public override fun require(byteCount: Long) {
      if (!this.request(byteCount)) {
         throw new EOFException("Source doesn't contain required number of bytes ($byteCount).");
      }
   }

   public override fun request(byteCount: Long): Boolean {
      if (this.closed) {
         throw new IllegalStateException("Source is closed.".toString());
      } else if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount: $byteCount").toString());
      } else {
         while (this.bufferField.getSize() < byteCount) {
            if (this.source.readAtMostTo(this.bufferField, 8192L) == -1L) {
               return false;
            }
         }

         return true;
      }
   }

   public override fun readByte(): Byte {
      this.require(1L);
      return this.bufferField.readByte();
   }

   public override fun readAtMostTo(sink: ByteArray, startIndex: Int, endIndex: Int): Int {
      _UtilKt.checkBounds((long)sink.length, (long)startIndex, (long)endIndex);
      return if (this.bufferField.getSize() == 0L && this.source.readAtMostTo(this.bufferField, 8192L) == -1L)
         -1
         else
         this.bufferField.readAtMostTo(sink, startIndex, startIndex + (int)Math.min((long)(endIndex - startIndex), this.bufferField.getSize()));
   }

   public override fun readTo(sink: RawSink, byteCount: Long) {
      try {
         this.require(byteCount);
      } catch (var5: EOFException) {
         sink.write(this.bufferField, this.bufferField.getSize());
         throw var5;
      }

      this.bufferField.readTo(sink, byteCount);
   }

   public override fun transferTo(sink: RawSink): Long {
      var totalBytesWritten: Long = 0L;

      while (this.source.readAtMostTo(this.bufferField, 8192L) != -1L) {
         val emitByteCount: Long = this.bufferField.completeSegmentByteCount$kotlinx_io_core();
         if (emitByteCount > 0L) {
            totalBytesWritten += emitByteCount;
            sink.write(this.bufferField, emitByteCount);
         }
      }

      if (this.bufferField.getSize() > 0L) {
         totalBytesWritten += this.bufferField.getSize();
         sink.write(this.bufferField, this.bufferField.getSize());
      }

      return totalBytesWritten;
   }

   public override fun readShort(): Short {
      this.require(2L);
      return this.bufferField.readShort();
   }

   public override fun readInt(): Int {
      this.require(4L);
      return this.bufferField.readInt();
   }

   public override fun readLong(): Long {
      this.require(8L);
      return this.bufferField.readLong();
   }

   public override fun skip(byteCount: Long) {
      if (this.closed) {
         throw new IllegalStateException("Source is closed.".toString());
      } else if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount: $byteCount").toString());
      } else {
         var remainingByteCount: Long = byteCount;

         while (remainingByteCount > 0L) {
            if (this.bufferField.getSize() == 0L && this.source.readAtMostTo(this.bufferField, 8192L) == -1L) {
               throw new EOFException("Source exhausted before skipping $byteCount bytes (only ${remainingByteCount - byteCount} bytes were skipped).");
            }

            val toSkip: Long = Math.min(remainingByteCount, this.bufferField.getSize());
            this.bufferField.skip(toSkip);
            remainingByteCount -= toSkip;
         }
      }
   }

   public override fun peek(): Source {
      if (this.closed) {
         throw new IllegalStateException("Source is closed.".toString());
      } else {
         return CoreKt.buffered(new PeekSource(this));
      }
   }

   public override fun close() {
      if (!this.closed) {
         this.closed = true;
         this.source.close();
         this.bufferField.clear();
      }
   }

   public override fun toString(): String {
      return "buffered(${this.source})";
   }

   private inline fun checkNotClosed() {
      if (this.closed) {
         throw new IllegalStateException("Source is closed.".toString());
      }
   }
}
