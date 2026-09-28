package kotlinx.io

import java.io.EOFException
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.unsafe.SegmentReadContext
import kotlinx.io.unsafe.UnsafeBufferOperations
import kotlinx.io.unsafe.UnsafeBufferOperationsKt

@SourceDebugExtension(["SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nkotlinx/io/Buffer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -Util.kt\nkotlinx/io/_UtilKt\n+ 4 Buffer.kt\nkotlinx/io/BufferKt\n+ 5 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n*L\n1#1,686:1\n628#1,14:689\n630#1,12:743\n1#2:687\n1#2:729\n1#2:734\n1#2:737\n1#2:741\n95#3:688\n52#3:728\n53#3:730\n107#3:731\n38#3:732\n52#3:733\n53#3:735\n52#3:736\n53#3:738\n38#3:739\n52#3:740\n53#3:742\n110#3:755\n89#3:759\n95#3:760\n659#4,25:703\n378#5,3:756\n381#5,3:761\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nkotlinx/io/Buffer\n*L\n228#1:689,14\n484#1:743,12\n290#1:729\n322#1:734\n330#1:737\n388#1:741\n118#1:688\n290#1:728\n290#1:730\n295#1:731\n307#1:732\n322#1:733\n322#1:735\n330#1:736\n330#1:738\n376#1:739\n388#1:740\n388#1:742\n562#1:755\n572#1:759\n573#1:760\n270#1:703,25\n566#1:756,3\n566#1:761,3\n*E\n"])
public class Buffer : Source, Sink {
   @PublishedApi
   internal final var head: Segment?

   @PublishedApi
   internal final var tail: Segment?

   public final val size: Long
      public final get() {
         return this.sizeMut;
      }


   @PublishedApi
   internal final var sizeMut: Long

   @InternalIoApi
   public open val buffer: Buffer
      public open get() {
         return this;
      }


   public override fun exhausted(): Boolean {
      return this.getSize() == 0L;
   }

   public override fun require(byteCount: Long) {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount: $byteCount").toString());
      } else if (this.getSize() < byteCount) {
         throw new EOFException("Buffer doesn't contain required number of bytes (size: ${this.getSize()}, required: $byteCount)");
      }
   }

   public override fun request(byteCount: Long): Boolean {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount: $byteCount < 0").toString());
      } else {
         return this.getSize() >= byteCount;
      }
   }

   public override fun readByte(): Byte {
      if (this.head == null) {
         this.throwEof(1L);
         throw new KotlinNothingValueException();
      } else {
         val segment: Segment = this.head;
         val segmentSize: Int = this.head.getSize();
         if (segmentSize == 0) {
            this.recycleHead$kotlinx_io_core();
            return this.readByte();
         } else {
            val v: Byte = segment.readByte$kotlinx_io_core();
            this.sizeMut--;
            if (segmentSize == 1) {
               this.recycleHead$kotlinx_io_core();
            }

            return v;
         }
      }
   }

   public override fun readShort(): Short {
      if (this.head == null) {
         this.throwEof(2L);
         throw new KotlinNothingValueException();
      } else {
         val segment: Segment = this.head;
         val segmentSize: Int = this.head.getSize();
         if (segmentSize < 2) {
            this.require(2L);
            if (segmentSize == 0) {
               this.recycleHead$kotlinx_io_core();
               return this.readShort();
            } else {
               return (short)((this.readByte() and 255) shl 8 or this.readByte() and 255);
            }
         } else {
            val v: Short = segment.readShort$kotlinx_io_core();
            this.sizeMut -= 2L;
            if (segmentSize == 2) {
               this.recycleHead$kotlinx_io_core();
            }

            return v;
         }
      }
   }

   public override fun readInt(): Int {
      if (this.head == null) {
         this.throwEof(4L);
         throw new KotlinNothingValueException();
      } else {
         val segment: Segment = this.head;
         val segmentSize: Int = this.head.getSize();
         if (segmentSize < 4) {
            this.require(4L);
            if (segmentSize == 0) {
               this.recycleHead$kotlinx_io_core();
               return this.readInt();
            } else {
               return this.readShort() shl 16 or this.readShort() and 65535;
            }
         } else {
            val v: Int = segment.readInt$kotlinx_io_core();
            this.sizeMut -= 4L;
            if (segmentSize == 4) {
               this.recycleHead$kotlinx_io_core();
            }

            return v;
         }
      }
   }

   public override fun readLong(): Long {
      if (this.head == null) {
         this.throwEof(8L);
         throw new KotlinNothingValueException();
      } else {
         val segment: Segment = this.head;
         val segmentSize: Int = this.head.getSize();
         if (segmentSize < 8) {
            this.require(8L);
            if (segmentSize == 0) {
               this.recycleHead$kotlinx_io_core();
               return this.readLong();
            } else {
               return (long)this.readInt() shl 32 or this.readInt() and 4294967295L;
            }
         } else {
            val v: Long = segment.readLong$kotlinx_io_core();
            this.sizeMut -= 8L;
            if (segmentSize == 8) {
               this.recycleHead$kotlinx_io_core();
            }

            return v;
         }
      }
   }

   private fun throwEof(byteCount: Long): Nothing {
      throw new EOFException("Buffer doesn't contain required number of bytes (size: ${this.getSize()}, required: $byteCount)");
   }

   @InternalIoApi
   public override fun hintEmit() {
   }

   public override fun emit() {
   }

   public override fun flush() {
   }

   public fun copyTo(out: Buffer, startIndex: Long = 0L, endIndex: Long = this.getSize()) {
      _UtilKt.checkBounds(this.getSize(), startIndex, endIndex);
      if (startIndex != endIndex) {
         var currentOffset: Long = startIndex;
         var remainingByteCount: Long = endIndex - startIndex;
         out.sizeMut += endIndex - startIndex;
         var s: Segment = this.head;

         while (true) {
            if (currentOffset < s.getLimit() - s.getPos()) {
               while (remainingByteCount > 0L) {
                  val copy: Segment = s.sharedCopy$kotlinx_io_core();
                  copy.setPos(copy.getPos() + (int)currentOffset);
                  copy.setLimit(Math.min(copy.getPos() + (int)remainingByteCount, copy.getLimit()));
                  if (out.getHead() == null) {
                     out.setHead(copy);
                     out.setTail(copy);
                  } else {
                     val var10001: Segment = out.getTail();
                     out.setTail(var10001.push$kotlinx_io_core(copy));
                  }

                  remainingByteCount -= copy.getLimit() - copy.getPos();
                  currentOffset = 0L;
                  s = s.getNext();
               }

               return;
            }

            currentOffset -= s.getLimit() - s.getPos();
            s = s.getNext();
         }
      }
   }

   internal fun completeSegmentByteCount(): Long {
      var result: Long = this.getSize();
      if (result == 0L) {
         return 0L;
      } else {
         val var10000: Segment = this.tail;
         if (var10000.getLimit() < 8192 && var10000.owner) {
            result -= var10000.getLimit() - var10000.getPos();
         }

         return result;
      }
   }

   public operator fun get(position: Long): Byte {
      if (position < 0L || position >= this.getSize()) {
         throw new IndexOutOfBoundsException("position ($position) is not within the range [0..size(${this.getSize()}))");
      } else if (position == 0L) {
         val var10000: Segment = this.head;
         return var10000.getUnchecked$kotlinx_io_core(0);
      } else if (this.getHead() == null) {
         return null.getUnchecked$kotlinx_io_core((int)(position - -1L));
      } else if (this.getSize() - position < position) {
         var var20: Segment = this.getTail();

         var var21: Long;
         for (offset$iv = this.getSize(); s$iv != null && offset$iv > position; s$iv = s$iv.getPrev()) {
            var21 -= var20.getLimit() - var20.getPos();
            if (var21 <= position) {
               break;
            }
         }

         return var20.getUnchecked$kotlinx_io_core((int)(position - var21));
      } else {
         var `s$iv`: Segment = this.getHead();
         var `offset$iv`: Long = 0L;

         while (s$iv != null) {
            val `nextOffset$iv`: Long = `offset$iv` + (`s$iv`.getLimit() - `s$iv`.getPos());
            if (`nextOffset$iv` > position) {
               break;
            }

            `s$iv` = `s$iv`.getNext();
            `offset$iv` = `nextOffset$iv`;
         }

         return `s$iv`.getUnchecked$kotlinx_io_core((int)(position - `offset$iv`));
      }
   }

   public fun clear() {
      this.skip(this.getSize());
   }

   public override fun skip(byteCount: Long) {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount ($byteCount) < 0").toString());
      } else {
         var var9: Long = byteCount;

         while (remainingByteCount > 0L) {
            if (this.head == null) {
               throw new EOFException("Buffer exhausted before skipping $byteCount bytes.");
            }

            val head: Segment = this.head;
            val toSkip: Int = (int)Math.min(var9, (long)(this.head.getLimit() - head.getPos()));
            this.sizeMut -= toSkip;
            var9 -= toSkip;
            head.setPos(head.getPos() + toSkip);
            if (head.getPos() == head.getLimit()) {
               this.recycleHead$kotlinx_io_core();
            }
         }
      }
   }

   public override fun readAtMostTo(sink: ByteArray, startIndex: Int, endIndex: Int): Int {
      _UtilKt.checkBounds((long)sink.length, (long)startIndex, (long)endIndex);
      if (this.head == null) {
         return -1;
      } else {
         val var6: Segment = this.head;
         val var7: Int = Math.min(endIndex - startIndex, this.head.getSize());
         var6.readTo$kotlinx_io_core(sink, startIndex, startIndex + var7);
         this.sizeMut -= var7;
         if (SegmentKt.isEmpty(var6)) {
            this.recycleHead$kotlinx_io_core();
         }

         return var7;
      }
   }

   public override fun readAtMostTo(sink: Buffer, byteCount: Long): Long {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount ($byteCount) < 0").toString());
      } else if (this.getSize() == 0L) {
         return -1L;
      } else {
         val var6: Long = if (byteCount > this.getSize()) this.getSize() else byteCount;
         sink.write(this, var6);
         return var6;
      }
   }

   public override fun readTo(sink: RawSink, byteCount: Long) {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount ($byteCount) < 0").toString());
      } else if (this.getSize() < byteCount) {
         sink.write(this, this.getSize());
         throw new EOFException("Buffer exhausted before writing $byteCount bytes. Only ${this.getSize()} bytes were written.");
      } else {
         sink.write(this, byteCount);
      }
   }

   public override fun transferTo(sink: RawSink): Long {
      val byteCount: Long = this.getSize();
      if (byteCount > 0L) {
         sink.write(this, byteCount);
      }

      return byteCount;
   }

   public override fun peek(): Source {
      return CoreKt.buffered(new PeekSource(this));
   }

   public override fun write(source: ByteArray, startIndex: Int, endIndex: Int) {
      _UtilKt.checkBounds((long)source.length, (long)startIndex, (long)endIndex);
      var var7: Int = startIndex;

      while (currentOffset < endIndex) {
         val var8: Segment = this.writableSegment(1);
         val toCopy: Int = Math.min(endIndex - var7, var8.getRemainingCapacity());
         var8.write$kotlinx_io_core(source, var7, var7 + toCopy);
         var7 += toCopy;
      }

      this.sizeMut += endIndex - startIndex;
   }

   public override fun write(source: RawSource, byteCount: Long) {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount ($byteCount) < 0").toString());
      } else {
         var var8: Long = byteCount;

         while (remainingByteCount > 0L) {
            val read: Long = source.readAtMostTo(this, var8);
            if (read == -1L) {
               throw new EOFException("Source exhausted before reading $byteCount bytes. Only ${byteCount - var8} were read.");
            }

            var8 -= read;
         }
      }
   }

   public override fun write(source: Buffer, byteCount: Long) {
      if (source === this) {
         throw new IllegalArgumentException("source == this".toString());
      } else {
         _UtilKt.checkOffsetAndCount(source.sizeMut, 0L, byteCount);
         var remainingByteCount: Long = byteCount;

         while (remainingByteCount > 0L) {
            var var10001: Segment = source.head;
            if (remainingByteCount < var10001.getSize()) {
               val segmentToMove: Segment = this.tail;
               if (this.tail != null
                  && this.tail.owner
                  && remainingByteCount + this.tail.getLimit() - (if (segmentToMove.getShared$kotlinx_io_core()) 0 else segmentToMove.getPos()) <= 8192L) {
                  val var15: Segment = source.head;
                  var15.writeTo$kotlinx_io_core(segmentToMove, (int)remainingByteCount);
                  source.sizeMut -= remainingByteCount;
                  this.sizeMut += remainingByteCount;
                  return;
               }

               var10001 = source.head;
               source.head = var10001.split$kotlinx_io_core((int)remainingByteCount);
            }

            var var10000: Segment = source.head;
            val movedByteCount: Long = var10000.getSize();
            source.head = var10000.pop$kotlinx_io_core();
            if (source.head == null) {
               source.tail = null;
            }

            if (this.getHead() == null) {
               this.setHead(var10000);
               this.setTail(var10000);
            } else {
               var10001 = this.getTail();
               this.setTail(var10001.push$kotlinx_io_core(var10000).compact$kotlinx_io_core());
               var10000 = this.getTail();
               if (var10000.getPrev() == null) {
                  this.setHead(this.getTail());
               }
            }

            source.sizeMut -= movedByteCount;
            this.sizeMut += movedByteCount;
            remainingByteCount -= movedByteCount;
         }
      }
   }

   public override fun transferFrom(source: RawSource): Long {
      var totalBytesRead: Long = 0L;

      while (true) {
         val readCount: Long = source.readAtMostTo(this, 8192L);
         if (readCount == -1L) {
            return totalBytesRead;
         }

         totalBytesRead += readCount;
      }
   }

   public override fun writeByte(byte: Byte) {
      this.writableSegment(1).writeByte$kotlinx_io_core(var1);
      this.sizeMut++;
   }

   public override fun writeShort(short: Short) {
      this.writableSegment(2).writeShort$kotlinx_io_core(var1);
      this.sizeMut += 2L;
   }

   public override fun writeInt(int: Int) {
      this.writableSegment(4).writeInt$kotlinx_io_core(var1);
      this.sizeMut += 4L;
   }

   public override fun writeLong(long: Long) {
      this.writableSegment(8).writeLong$kotlinx_io_core(var1);
      this.sizeMut += 8L;
   }

   public fun copy(): Buffer {
      val result: Buffer = new Buffer();
      if (this.getSize() == 0L) {
         return result;
      } else {
         val var10000: Segment = this.head;
         val headCopy: Segment = var10000.sharedCopy$kotlinx_io_core();
         result.head = headCopy;
         result.tail = headCopy;

         for (Segment s = var10000.getNext(); s != null; s = s.getNext()) {
            val var10001: Segment = result.tail;
            result.tail = var10001.push$kotlinx_io_core(s.sharedCopy$kotlinx_io_core());
         }

         result.sizeMut = this.getSize();
         return result;
      }
   }

   public override fun close() {
   }

   public override fun toString(): String {
      if (this.getSize() == 0L) {
         return "Buffer(size=0)";
      } else {
         val len: Int = (int)Math.min((long)64, this.getSize());
         val var16: StringBuilder = new StringBuilder(len * 2 + (if (this.getSize() > 64) 1 else 0));
         var bytesWritten: Int = 0;
         val var17: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;

         for (Segment curr$iv = this.getHead(); curr$iv != null; curr$iv = curr$iv.getNext()) {
            val var10000: SegmentReadContext = UnsafeBufferOperationsKt.getSegmentReadContextImpl();
            val segment: Segment = `curr$iv`;
            val ctx: SegmentReadContext = var10000;
            var idx: Int = 0;

            while (bytesWritten < len && idx < segment.getSize()) {
               val b: Byte = ctx.getUnchecked(segment, idx++);
               bytesWritten++;
               var16.append(_UtilKt.getHEX_DIGIT_CHARS()[b shr 4 and 15]).append(_UtilKt.getHEX_DIGIT_CHARS()[b and 15]);
            }
         }

         if (this.getSize() > 64) {
            var16.append('…');
         }

         return "Buffer(size=${this.getSize()} hex=$var16)";
      }
   }

   internal fun recycleHead() {
      val var10000: Segment = this.head;
      val nextHead: Segment = var10000.getNext();
      this.head = nextHead;
      if (nextHead == null) {
         this.tail = null;
      } else {
         nextHead.setPrev(null);
      }

      var10000.setNext(null);
      SegmentPool.recycle(var10000);
   }

   private inline fun pushSegment(newTail: Segment, tryCompact: Boolean = false) {
      if (this.getHead() == null) {
         this.setHead(newTail);
         this.setTail(newTail);
      } else if (tryCompact) {
         val var10001: Segment = this.getTail();
         this.setTail(var10001.push$kotlinx_io_core(newTail).compact$kotlinx_io_core());
         val var10000: Segment = this.getTail();
         if (var10000.getPrev() == null) {
            this.setHead(this.getTail());
         }
      } else {
         val var4: Segment = this.getTail();
         this.setTail(var4.push$kotlinx_io_core(newTail));
      }
   }
}
