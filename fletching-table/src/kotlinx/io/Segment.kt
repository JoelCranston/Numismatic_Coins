package kotlinx.io

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nSegment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Segment.kt\nkotlinx/io/Segment\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -Util.kt\nkotlinx/io/_UtilKt\n*L\n1#1,535:1\n1#2:536\n95#3:537\n95#3:538\n95#3:539\n95#3:540\n95#3:541\n98#3:542\n98#3:543\n98#3:544\n98#3:545\n98#3:546\n98#3:547\n98#3:548\n98#3:549\n*S KotlinDebug\n*F\n+ 1 Segment.kt\nkotlinx/io/Segment\n*L\n282#1:537\n291#1:538\n292#1:539\n293#1:540\n294#1:541\n304#1:542\n305#1:543\n306#1:544\n307#1:545\n308#1:546\n309#1:547\n310#1:548\n311#1:549\n*E\n"])
public class Segment {
   private final val data: ByteArray

   @PublishedApi
   internal final var pos: Int

   @PublishedApi
   internal final var limit: Int

   internal final val shared: Boolean
      internal final get() {
         return this.copyTracker != null && this.copyTracker.getShared();
      }


   internal final var copyTracker: SegmentCopyTracker?

   internal final var owner: Boolean
      private set

   @PublishedApi
   internal final var next: Segment?

   @PublishedApi
   internal final var prev: Segment?

   public final val size: Int
      public final get() {
         return this.limit - this.pos;
      }


   public final val remainingCapacity: Int
      public final get() {
         return this.data.length - this.limit;
      }


   private constructor()  {
      this.data = new byte[8192];
      this.owner = true;
      this.copyTracker = null;
   }

   private constructor(data: ByteArray, pos: Int, limit: Int, shareToken: SegmentCopyTracker?, owner: Boolean)  {
      this.data = data;
      this.pos = pos;
      this.limit = limit;
      this.copyTracker = shareToken;
      this.owner = owner;
   }

   internal fun sharedCopy(): Segment {
      var var10000: SegmentCopyTracker = this.copyTracker;
      if (this.copyTracker == null) {
         val it: SegmentCopyTracker = SegmentPool.tracker();
         this.copyTracker = it;
         var10000 = it;
      }

      val var8: Int = this.limit;
      val var7: Int = this.pos;
      val var6: ByteArray = this.data;
      var10000.addCopy();
      return new Segment(var6, var7, var8, var10000, false);
   }

   internal fun pop(): Segment? {
      val result: Segment = this.next;
      if (this.prev != null) {
         val var10000: Segment = this.prev;
         var10000.next = this.next;
      }

      if (this.next != null) {
         val var2: Segment = this.next;
         var2.prev = this.prev;
      }

      this.next = null;
      this.prev = null;
      return result;
   }

   internal fun push(segment: Segment): Segment {
      segment.prev = this;
      segment.next = this.next;
      if (this.next != null) {
         val var10000: Segment = this.next;
         var10000.prev = segment;
      }

      this.next = segment;
      return segment;
   }

   internal fun split(byteCount: Int): Segment {
      if (byteCount <= 0 || byteCount > this.limit - this.pos) {
         throw new IllegalArgumentException("byteCount out of range".toString());
      } else {
         val var4: Segment;
         if (byteCount >= 1024) {
            var4 = this.sharedCopy$kotlinx_io_core();
         } else {
            var4 = SegmentPool.take();
            ArraysKt.copyInto$default(this.data, var4.data, 0, this.pos, this.pos + byteCount, 2, null);
         }

         var4.limit = var4.pos + byteCount;
         this.pos += byteCount;
         if (this.prev != null) {
            val var10000: Segment = this.prev;
            var10000.push$kotlinx_io_core(var4);
         } else {
            var4.next = this;
            this.prev = var4;
         }

         return var4;
      }
   }

   internal fun compact(): Segment {
      if (this.prev == null) {
         throw new IllegalStateException("cannot compact".toString());
      } else {
         val var10000: Segment = this.prev;
         if (!var10000.owner) {
            return this;
         } else {
            val byteCount: Int = this.limit - this.pos;
            var var10001: Segment = this.prev;
            val var8: Int = 8192 - var10001.limit;
            var10001 = this.prev;
            val var10: Int;
            if (var10001.getShared$kotlinx_io_core()) {
               var10 = 0;
            } else {
               var10001 = this.prev;
               var10 = var10001.pos;
            }

            if (byteCount > var8 + var10) {
               return this;
            } else {
               val predecessor: Segment = this.prev;
               this.writeTo$kotlinx_io_core(predecessor, byteCount);
               if (this.pop$kotlinx_io_core() != null) {
                  throw new IllegalStateException("Check failed.".toString());
               } else {
                  SegmentPool.recycle(this);
                  return predecessor;
               }
            }
         }
      }
   }

   internal fun writeByte(byte: Byte) {
      this.data[this.limit++] = var1;
   }

   internal fun writeShort(short: Short) {
      this.data[this.limit++] = (byte)(var1 ushr 8 and 255);
      val limit: Int;
      this.data[limit++] = (byte)(var1 and 255);
      this.limit = limit;
   }

   internal fun writeInt(int: Int) {
      this.data[this.limit++] = (byte)(var1 ushr 24 and 255);
      var limit: Int;
      this.data[limit++] = (byte)(var1 ushr 16 and 255);
      this.data[limit++] = (byte)(var1 ushr 8 and 255);
      this.data[limit++] = (byte)(var1 and 255);
      this.limit = limit;
   }

   internal fun writeLong(long: Long) {
      this.data[this.limit++] = (byte)(var1 ushr 56 and 255L);
      var limit: Int;
      this.data[limit++] = (byte)(var1 ushr 48 and 255L);
      this.data[limit++] = (byte)(var1 ushr 40 and 255L);
      this.data[limit++] = (byte)(var1 ushr 32 and 255L);
      this.data[limit++] = (byte)(var1 ushr 24 and 255L);
      this.data[limit++] = (byte)(var1 ushr 16 and 255L);
      this.data[limit++] = (byte)(var1 ushr 8 and 255L);
      this.data[limit++] = (byte)(var1 and 255L);
      this.limit = limit;
   }

   internal fun readByte(): Byte {
      return this.data[this.pos++];
   }

   internal fun readShort(): Short {
      val pos: Int;
      val s: Short = (short)((this.data[this.pos++] and 255) shl 8 or this.data[pos++] and 255);
      this.pos = pos;
      return s;
   }

   internal fun readInt(): Int {
      var pos: Int;
      val i: Int = (this.data[this.pos++] and 255) shl 24 or (this.data[pos++] and 255) shl 16 or (this.data[pos++] and 255) shl 8 or this.data[pos++] and 255;
      this.pos = pos;
      return i;
   }

   internal fun readLong(): Long {
      var pos: Int;
      val v: Long = (this.data[this.pos++] and 255L) shl 56 or (this.data[pos++] and 255L) shl 48 or (this.data[pos++] and 255L) shl 40 or (
         this.data[pos++] and 255L
      ) shl 32 or (this.data[pos++] and 255L) shl 24 or (this.data[pos++] and 255L) shl 16 or (this.data[pos++] and 255L) shl 8 or this.data[pos++] and 255L;
      this.pos = pos;
      return v;
   }

   internal fun writeTo(sink: Segment, byteCount: Int) {
      if (!sink.owner) {
         throw new IllegalStateException("only owner can write".toString());
      } else {
         if (sink.limit + byteCount > 8192) {
            if (sink.getShared$kotlinx_io_core()) {
               throw new IllegalArgumentException();
            }

            if (sink.limit + byteCount - sink.pos > 8192) {
               throw new IllegalArgumentException();
            }

            ArraysKt.copyInto$default(sink.data, sink.data, 0, sink.pos, sink.limit, 2, null);
            sink.limit = sink.limit - sink.pos;
            sink.pos = 0;
         }

         ArraysKt.copyInto(this.data, sink.data, sink.limit, this.pos, this.pos + byteCount);
         sink.limit += byteCount;
         this.pos += byteCount;
      }
   }

   internal fun readTo(dst: ByteArray, dstStartOffset: Int, dstEndOffset: Int) {
      val len: Int = dstEndOffset - dstStartOffset;
      ArraysKt.copyInto(this.data, dst, dstStartOffset, this.pos, this.pos + (dstEndOffset - dstStartOffset));
      this.pos += len;
   }

   internal fun write(src: ByteArray, srcStartOffset: Int, srcEndOffset: Int) {
      ArraysKt.copyInto(src, this.data, this.limit, srcStartOffset, srcEndOffset);
      this.limit += srcEndOffset - srcStartOffset;
   }

   internal fun getUnchecked(index: Int): Byte {
      return this.data[this.pos + index];
   }

   internal fun setUnchecked(index: Int, value: Byte) {
      this.data[this.limit + index] = value;
   }

   internal fun setUnchecked(index: Int, b0: Byte, b1: Byte) {
      this.data[this.limit + index] = b0;
      this.data[this.limit + index + 1] = b1;
   }

   internal fun setUnchecked(index: Int, b0: Byte, b1: Byte, b2: Byte) {
      this.data[this.limit + index] = b0;
      this.data[this.limit + index + 1] = b1;
      this.data[this.limit + index + 2] = b2;
   }

   internal fun setUnchecked(index: Int, b0: Byte, b1: Byte, b2: Byte, b3: Byte) {
      this.data[this.limit + index] = b0;
      this.data[this.limit + index + 1] = b1;
      this.data[this.limit + index + 2] = b2;
      this.data[this.limit + index + 3] = b3;
   }

   internal companion object {
      internal const val SIZE: Int
      internal const val SHARE_MINIMUM: Int
   }
}
