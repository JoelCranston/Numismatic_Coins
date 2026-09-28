@file:JvmName(name = "-Buffer")

@file:SourceDebugExtension(["SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/internal/-Buffer\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1712:1\n110#1,20:1735\n110#1,20:1768\n110#1:1788\n112#1,18:1790\n110#1,20:1808\n73#2:1713\n73#2:1714\n73#2:1715\n73#2:1716\n73#2:1717\n73#2:1718\n73#2:1719\n73#2:1720\n73#2:1721\n73#2:1722\n73#2:1723\n73#2:1724\n82#2:1725\n82#2:1726\n76#2:1727\n76#2:1728\n76#2:1729\n76#2:1730\n76#2:1731\n76#2:1732\n76#2:1733\n76#2:1734\n85#2:1755\n88#2:1757\n73#2:1758\n73#2:1759\n73#2:1760\n73#2:1761\n73#2:1762\n73#2:1763\n73#2:1764\n73#2:1765\n73#2:1766\n73#2:1767\n88#2:1789\n85#2:1828\n1#3:1756\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/internal/-Buffer\n*L\n413#1:1735,20\n1262#1:1768,20\n1305#1:1788\n1305#1:1790,18\n1341#1:1808,20\n176#1:1713\n200#1:1714\n319#1:1715\n324#1:1716\n347#1:1717\n348#1:1718\n349#1:1719\n350#1:1720\n356#1:1721\n357#1:1722\n358#1:1723\n359#1:1724\n383#1:1725\n384#1:1726\n390#1:1727\n391#1:1728\n392#1:1729\n393#1:1730\n394#1:1731\n395#1:1732\n396#1:1733\n397#1:1734\n425#1:1755\n858#1:1757\n876#1:1758\n878#1:1759\n882#1:1760\n884#1:1761\n888#1:1762\n890#1:1763\n894#1:1764\n896#1:1765\n916#1:1766\n919#1:1767\n1317#1:1789\n1658#1:1828\n*E\n"])

package okio.internal

import java.io.EOFException
import kotlin.jvm.internal.SourceDebugExtension
import okio.Buffer
import okio.ByteString
import okio.Options
import okio.Segment
import okio.SegmentPool
import okio.SegmentedByteString
import okio.Sink
import okio.Source
import okio._JvmPlatformKt
import okio.Buffer.UnsafeCursor

internal final val HEX_DIGIT_BYTES: ByteArray = _JvmPlatformKt.asUtf8ToByteArray("0123456789abcdef")
internal const val SEGMENTING_THRESHOLD: Int = 4096
private final val DigitCountToLargestValue: LongArray =
   new long[]{
      -1L,
      9L,
      99L,
      999L,
      9999L,
      99999L,
      999999L,
      9999999L,
      99999999L,
      999999999L,
      9999999999L,
      99999999999L,
      999999999999L,
      9999999999999L,
      99999999999999L,
      999999999999999L,
      9999999999999999L,
      99999999999999999L,
      999999999999999999L,
      java.lang.Long.MAX_VALUE
   }
   internal const val OVERFLOW_ZONE: Long = -922337203685477580L
internal const val OVERFLOW_DIGIT_START: Long = -7L

internal fun rangeEquals(segment: Segment, segmentPos: Int, bytes: ByteArray, bytesOffset: Int, bytesLimit: Int): Boolean {
   var segmentx: Segment = segment;
   var segmentPosx: Int = segmentPos;
   var segmentLimit: Int = segment.limit;
   var data: ByteArray = segment.data;

   for (int i = bytesOffset; i < bytesLimit; i++) {
      if (segmentPosx == segmentLimit) {
         val var10000: Segment = segmentx.next;
         segmentx = var10000;
         data = var10000.data;
         segmentPosx = var10000.pos;
         segmentLimit = var10000.limit;
      }

      if (data[segmentPosx] != bytes[i]) {
         return false;
      }

      segmentPosx++;
   }

   return true;
}

internal fun Buffer.readUtf8Line(newline: Long): String {
   val var10000: java.lang.String;
   if (newline > 0L && `$this$readUtf8Line`.getByte(newline - 1L) == 13) {
      val var4: java.lang.String = `$this$readUtf8Line`.readUtf8(newline - 1L);
      `$this$readUtf8Line`.skip(2L);
      var10000 = var4;
   } else {
      val result: java.lang.String = `$this$readUtf8Line`.readUtf8(newline);
      `$this$readUtf8Line`.skip(1L);
      var10000 = result;
   }

   return var10000;
}

internal inline fun <T> Buffer.seek(fromIndex: Long, lambda: (Segment?, Long) -> T): T {
   if (`$this$seek`.head == null) {
      return (T)lambda.invoke(null, -1L);
   } else {
      var s: Segment = `$this$seek`.head;
      if (`$this$seek`.size() - fromIndex < fromIndex) {
         var var10: Long;
         val var11: Segment;
         for (offset = $this$seek.size(); offset > fromIndex; offset -= var11.limit - var11.pos) {
            var11 = s.prev;
            s = var11;
         }

         return (T)lambda.invoke(s, var10);
      } else {
         var offsetx: Long = 0L;

         while (true) {
            val nextOffset: Long = offsetx + (s.limit - s.pos);
            if (offsetx + (s.limit - s.pos) > fromIndex) {
               return (T)lambda.invoke(s, offsetx);
            }

            val var10000: Segment = s.next;
            s = var10000;
            offsetx = nextOffset;
         }
      }
   }
}

internal fun Buffer.selectPrefix(options: Options, selectTruncated: Boolean = false): Int {
   if (`$this$selectPrefix`.head == null) {
      return if (selectTruncated) -2 else -1;
   } else {
      val head: Segment = `$this$selectPrefix`.head;
      var s: Segment = `$this$selectPrefix`.head;
      var data: ByteArray = `$this$selectPrefix`.head.data;
      var pos: Int = `$this$selectPrefix`.head.pos;
      var limit: Int = `$this$selectPrefix`.head.limit;
      val trie: IntArray = options.getTrie$okio();
      var triePos: Int = 0;
      var prefixIndex: Int = -1;

      while (true) {
         val scanOrSelect: Int = trie[triePos++];
         val possiblePrefixIndex: Int = trie[triePos++];
         if (possiblePrefixIndex != -1) {
            prefixIndex = possiblePrefixIndex;
         }

         label73:
         if (s != null) {
            val var22: Int;
            if (scanOrSelect < 0) {
               val var15: Int = triePos + -1 * scanOrSelect;

               val var26: Boolean;
               do {
                  if ((data[pos++] and 255) != trie[triePos++]) {
                     return prefixIndex;
                  }

                  var26 = triePos == var15;
                  if (pos == limit) {
                     val var10000: Segment = s.next;
                     s = var10000;
                     pos = var10000.pos;
                     data = var10000.data;
                     limit = var10000.limit;
                     if (var10000 === head) {
                        if (!var26) {
                           break label73;
                        }

                        s = null;
                     }
                  }
               } while (!scanComplete);

               var22 = trie[triePos];
            } else {
               val var23: Int = data[pos++] and 255;
               val var25: Int = triePos + scanOrSelect;

               while (true) {
                  if (triePos == var25) {
                     return prefixIndex;
                  }

                  if (var23 == trie[triePos]) {
                     var22 = trie[triePos + scanOrSelect];
                     if (pos == limit) {
                        val var29: Segment = s.next;
                        s = var29;
                        pos = var29.pos;
                        data = var29.data;
                        limit = var29.limit;
                        if (var29 === head) {
                           s = null;
                        }
                     }
                     break;
                  }

                  triePos++;
               }
            }

            if (var22 >= 0) {
               return var22;
            }

            triePos = -var22;
            continue;
         }

         if (selectTruncated) {
            return -2;
         }

         return prefixIndex;
      }
   }
}

@JvmSynthetic
fun `selectPrefix$default`(var0: Buffer, var1: Options, var2: Boolean, var3: Int, var4: Any): Int {
   if ((var3 and 2) != 0) {
      var2 = false;
   }

   return selectPrefix(var0, var1, var2);
}

internal inline fun Buffer.commonCopyTo(out: Buffer, offset: Long, byteCount: Long): Buffer {
   var offsetx: Long = offset;
   var byteCountx: Long = byteCount;
   okio.-SegmentedByteString.checkOffsetAndCount(`$this$commonCopyTo`.size(), offset, byteCount);
   if (byteCount == 0L) {
      return `$this$commonCopyTo`;
   } else {
      out.setSize$okio(out.size() + byteCount);
      var s: Segment = `$this$commonCopyTo`.head;

      while (true) {
         if (offsetx < s.limit - s.pos) {
            while (byteCountx > 0L) {
               val copy: Segment = s.sharedCopy();
               copy.pos += (int)offsetx;
               copy.limit = Math.min(copy.pos + (int)byteCountx, copy.limit);
               if (out.head == null) {
                  copy.prev = copy;
                  copy.next = copy.prev;
                  out.head = copy.next;
               } else {
                  val var10000: Segment = out.head;
                  val var13: Segment = var10000.prev;
                  var13.push(copy);
               }

               byteCountx -= copy.limit - copy.pos;
               offsetx = 0L;
               s = s.next;
            }

            return `$this$commonCopyTo`;
         }

         offsetx -= s.limit - s.pos;
         s = s.next;
      }
   }
}

internal inline fun Buffer.commonCompleteSegmentByteCount(): Long {
   var result: Long = `$this$commonCompleteSegmentByteCount`.size();
   if (result == 0L) {
      return 0L;
   } else {
      val var10000: Segment = `$this$commonCompleteSegmentByteCount`.head;
      val var5: Segment = var10000.prev;
      if (var5.limit < 8192 && var5.owner) {
         result -= var5.limit - var5.pos;
      }

      return result;
   }
}

internal inline fun Buffer.commonReadByte(): Byte {
   if (`$this$commonReadByte`.size() == 0L) {
      throw new EOFException();
   } else {
      val var10000: Segment = `$this$commonReadByte`.head;
      val limit: Int = var10000.limit;
      val b: Byte = var10000.data[var10000.pos++];
      `$this$commonReadByte`.setSize$okio(`$this$commonReadByte`.size() - 1L);
      val pos: Int;
      if (pos == limit) {
         `$this$commonReadByte`.head = var10000.pop();
         SegmentPool.recycle(var10000);
      } else {
         var10000.pos = pos;
      }

      return b;
   }
}

internal inline fun Buffer.commonReadShort(): Short {
   if (`$this$commonReadShort`.size() < 2L) {
      throw new EOFException();
   } else {
      val var10000: Segment = `$this$commonReadShort`.head;
      val limit: Int = var10000.limit;
      if (var10000.limit - var10000.pos < 2) {
         return (short)((`$this$commonReadShort`.readByte() and 255) shl 8 or `$this$commonReadShort`.readByte() and 255);
      } else {
         val pos: Int;
         val s: Int = (var10000.data[var10000.pos++] and 255) shl 8 or var10000.data[pos++] and 255;
         `$this$commonReadShort`.setSize$okio(`$this$commonReadShort`.size() - 2L);
         if (pos == limit) {
            `$this$commonReadShort`.head = var10000.pop();
            SegmentPool.recycle(var10000);
         } else {
            var10000.pos = pos;
         }

         return (short)s;
      }
   }
}

internal inline fun Buffer.commonReadInt(): Int {
   if (`$this$commonReadInt`.size() < 4L) {
      throw new EOFException();
   } else {
      val var10000: Segment = `$this$commonReadInt`.head;
      val limit: Int = var10000.limit;
      if (var10000.limit - var10000.pos < 4L) {
         return (`$this$commonReadInt`.readByte() and 255) shl 24 or (`$this$commonReadInt`.readByte() and 255) shl 16 or (
            `$this$commonReadInt`.readByte() and 255
         ) shl 8 or `$this$commonReadInt`.readByte() and 255;
      } else {
         var pos: Int;
         val i: Int = (var10000.data[var10000.pos++] and 255) shl 24 or (var10000.data[pos++] and 255) shl 16 or (var10000.data[pos++] and 255) shl 8 or var10000.data[pos++] and 255;
         `$this$commonReadInt`.setSize$okio(`$this$commonReadInt`.size() - 4L);
         if (pos == limit) {
            `$this$commonReadInt`.head = var10000.pop();
            SegmentPool.recycle(var10000);
         } else {
            var10000.pos = pos;
         }

         return i;
      }
   }
}

internal inline fun Buffer.commonReadLong(): Long {
   if (`$this$commonReadLong`.size() < 8L) {
      throw new EOFException();
   } else {
      val var10000: Segment = `$this$commonReadLong`.head;
      val limit: Int = var10000.limit;
      if (var10000.limit - var10000.pos < 8L) {
         return (`$this$commonReadLong`.readInt() and 4294967295L) shl 32 or `$this$commonReadLong`.readInt() and 4294967295L;
      } else {
         var pos: Int;
         val v: Long = (var10000.data[var10000.pos++] and 255L) shl 56 or (var10000.data[pos++] and 255L) shl 48 or (var10000.data[pos++] and 255L) shl 40 or (
            var10000.data[pos++] and 255L
         ) shl 32 or (var10000.data[pos++] and 255L) shl 24 or (var10000.data[pos++] and 255L) shl 16 or (var10000.data[pos++] and 255L) shl 8 or var10000.data[pos++] and 255L;
         `$this$commonReadLong`.setSize$okio(`$this$commonReadLong`.size() - 8L);
         if (pos == limit) {
            `$this$commonReadLong`.head = var10000.pop();
            SegmentPool.recycle(var10000);
         } else {
            var10000.pos = pos;
         }

         return v;
      }
   }
}

internal inline fun Buffer.commonGet(pos: Long): Byte {
   okio.-SegmentedByteString.checkOffsetAndCount(`$this$commonGet`.size(), pos, 1L);
   val `fromIndex$iv`: Long = pos;
   if (`$this$commonGet`.head == null) {
      return ((Segment)null).data[(int)(((Segment)null).pos + pos - -1L)];
   } else {
      var `s$iv`: Segment = `$this$commonGet`.head;
      if (`$this$commonGet`.size() - pos < pos) {
         var var23: Long;
         val var24: Segment;
         for (offset$iv = $this$commonGet.size(); offset$iv > fromIndex$iv; offset$iv -= var24.limit - var24.pos) {
            var24 = `s$iv`.prev;
            `s$iv` = var24;
         }

         return `s$iv`.data[(int)(`s$iv`.pos + pos - var23)];
      } else {
         var `offset$ivx`: Long = 0L;

         while (true) {
            val `nextOffset$iv`: Long = `offset$ivx` + (`s$iv`.limit - `s$iv`.pos);
            if (`offset$ivx` + (`s$iv`.limit - `s$iv`.pos) > `fromIndex$iv`) {
               return `s$iv`.data[(int)(`s$iv`.pos + pos - `offset$ivx`)];
            }

            val var10000: Segment = `s$iv`.next;
            `s$iv` = var10000;
            `offset$ivx` = `nextOffset$iv`;
         }
      }
   }
}

internal inline fun Buffer.commonClear() {
   `$this$commonClear`.skip(`$this$commonClear`.size());
}

internal inline fun Buffer.commonSkip(byteCount: Long) {
   var byteCountx: Long = byteCount;

   while (byteCountx > 0L) {
      if (`$this$commonSkip`.head == null) {
         throw new EOFException();
      }

      val head: Segment = `$this$commonSkip`.head;
      val toSkip: Int = (int)Math.min(byteCountx, (long)(`$this$commonSkip`.head.limit - `$this$commonSkip`.head.pos));
      `$this$commonSkip`.setSize$okio(`$this$commonSkip`.size() - (long)toSkip);
      byteCountx -= toSkip;
      head.pos += toSkip;
      if (head.pos == head.limit) {
         `$this$commonSkip`.head = head.pop();
         SegmentPool.recycle(head);
      }
   }
}

internal inline fun Buffer.commonWrite(byteString: ByteString, offset: Int = 0, byteCount: Int = byteString.size()): Buffer {
   byteString.write$okio(`$this$commonWrite`, offset, byteCount);
   return `$this$commonWrite`;
}

@JvmSynthetic
fun Buffer.`commonWrite$default`(byteString: ByteString, offset: Int, byteCount: Int, `$i$f$commonWrite`: Int, var5: Any): Buffer {
   if ((`$i$f$commonWrite` and 2) != 0) {
      offset = 0;
   }

   if ((`$i$f$commonWrite` and 4) != 0) {
      byteCount = byteString.size();
   }

   byteString.write$okio(`$this$commonWrite_u24default`, offset, byteCount);
   return `$this$commonWrite_u24default`;
}

internal inline fun Buffer.commonWriteDecimalLong(v: Long): Buffer {
   var vx: Long = v;
   if (v == 0L) {
      return `$this$commonWriteDecimalLong`.writeByte(48);
   } else {
      var negative: Boolean = false;
      if (v < 0L) {
         vx = -v;
         if (-v < 0L) {
            return `$this$commonWriteDecimalLong`.writeUtf8("-9223372036854775808");
         }

         negative = true;
      }

      var width: Int = access$countDigitsIn(vx);
      if (negative) {
         width++;
      }

      val tail: Segment = `$this$commonWriteDecimalLong`.writableSegment$okio(width);
      val data: ByteArray = tail.data;

      var pos: Int;
      for (pos = tail.limit + width; vx != 0L; vx /= 10) {
         data[--pos] = getHEX_DIGIT_BYTES()[(int)(vx % 10)];
      }

      if (negative) {
         data[--pos] = 45;
      }

      tail.limit += width;
      `$this$commonWriteDecimalLong`.setSize$okio(`$this$commonWriteDecimalLong`.size() + (long)width);
      return `$this$commonWriteDecimalLong`;
   }
}

private fun countDigitsIn(v: Long): Int {
   val guess: Int = (64 - java.lang.Long.numberOfLeadingZeros(v)) * 10 ushr 5;
   return guess + (if (v > DigitCountToLargestValue[guess]) 1 else 0);
}

internal inline fun Buffer.commonWriteHexadecimalUnsignedLong(v: Long): Buffer {
   var vx: Long = v;
   if (v == 0L) {
      return `$this$commonWriteHexadecimalUnsignedLong`.writeByte(48);
   } else {
      val width: Int = (int)(
         (
               (
                     (
                           (
                                 (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) ushr 2 and 3689348814741910323L
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) and 3689348814741910323L
                                    ) ushr 4
                              )
                              + (
                                 (
                                       v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                       ) ushr 8 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8
                                       ) ushr 16 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16
                                       ) ushr 32
                                    )
                                    - (
                                       (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       ) ushr 1 and 6148914691236517205L
                                    ) ushr 2 and 3689348814741910323L
                              )
                              + (
                                 (
                                       v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                       ) ushr 8 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8
                                       ) ushr 16 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16
                                       ) ushr 32
                                    )
                                    - (
                                       (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       ) ushr 1 and 6148914691236517205L
                                    ) and 3689348814741910323L
                              ) and 1085102592571150095L
                        )
                        + (
                           (
                              (
                                    (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) ushr 4
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) ushr 2 and 3689348814741910323L
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) and 3689348814741910323L
                                 ) and 1085102592571150095L
                           ) ushr 8
                        )
                        + (
                           (
                                 (
                                       (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) ushr 4
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) ushr 2 and 3689348814741910323L
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) and 3689348814741910323L
                                    ) and 1085102592571150095L
                              )
                              + (
                                 (
                                    (
                                          (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) ushr 2 and 3689348814741910323L
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) and 3689348814741910323L
                                             ) ushr 4
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) and 1085102592571150095L
                                 ) ushr 8
                              ) ushr 16
                        ) and 63L
                  )
                  + (
                     (
                           (
                                 (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) ushr 2 and 3689348814741910323L
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) and 3689348814741910323L
                                    ) ushr 4
                              )
                              + (
                                 (
                                       v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                       ) ushr 8 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8
                                       ) ushr 16 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16
                                       ) ushr 32
                                    )
                                    - (
                                       (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       ) ushr 1 and 6148914691236517205L
                                    ) ushr 2 and 3689348814741910323L
                              )
                              + (
                                 (
                                       v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                       ) ushr 8 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8
                                       ) ushr 16 or (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16
                                       ) ushr 32
                                    )
                                    - (
                                       (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       ) ushr 1 and 6148914691236517205L
                                    ) and 3689348814741910323L
                              ) and 1085102592571150095L
                        )
                        + (
                           (
                              (
                                    (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) ushr 4
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) ushr 2 and 3689348814741910323L
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) and 3689348814741910323L
                                 ) and 1085102592571150095L
                           ) ushr 8
                        )
                        + (
                           (
                                 (
                                       (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) ushr 4
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) ushr 2 and 3689348814741910323L
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) and 3689348814741910323L
                                    ) and 1085102592571150095L
                              )
                              + (
                                 (
                                    (
                                          (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) ushr 2 and 3689348814741910323L
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) and 3689348814741910323L
                                             ) ushr 4
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) and 1085102592571150095L
                                 ) ushr 8
                              ) ushr 16
                        ) ushr 32 and 63L
                  )
                  + 3
            )
            / 4
      );
      val tail: Segment = `$this$commonWriteHexadecimalUnsignedLong`.writableSegment$okio(
         (int)(
            (
                  (
                        (
                              (
                                    (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) ushr 4
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) ushr 2 and 3689348814741910323L
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) and 3689348814741910323L
                                 ) and 1085102592571150095L
                           )
                           + (
                              (
                                 (
                                       (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) ushr 4
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) ushr 2 and 3689348814741910323L
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) and 3689348814741910323L
                                    ) and 1085102592571150095L
                              ) ushr 8
                           )
                           + (
                              (
                                    (
                                          (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) ushr 2 and 3689348814741910323L
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) and 3689348814741910323L
                                             ) ushr 4
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) and 1085102592571150095L
                                 )
                                 + (
                                    (
                                       (
                                             (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) ushr 2 and 3689348814741910323L
                                                )
                                                + (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) and 3689348814741910323L
                                                ) ushr 4
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) and 1085102592571150095L
                                    ) ushr 8
                                 ) ushr 16
                           ) and 63L
                     )
                     + (
                        (
                              (
                                    (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) ushr 4
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) ushr 2 and 3689348814741910323L
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) and 3689348814741910323L
                                 ) and 1085102592571150095L
                           )
                           + (
                              (
                                 (
                                       (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) ushr 4
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) ushr 2 and 3689348814741910323L
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) and 3689348814741910323L
                                    ) and 1085102592571150095L
                              ) ushr 8
                           )
                           + (
                              (
                                    (
                                          (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) ushr 2 and 3689348814741910323L
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) and 3689348814741910323L
                                             ) ushr 4
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) and 1085102592571150095L
                                 )
                                 + (
                                    (
                                       (
                                             (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) ushr 2 and 3689348814741910323L
                                                )
                                                + (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) and 3689348814741910323L
                                                ) ushr 4
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) and 1085102592571150095L
                                    ) ushr 8
                                 ) ushr 16
                           ) ushr 32 and 63L
                     )
                     + (long)3
               )
               / (long)4
         )
      );
      val data: ByteArray = tail.data;
      var pos: Int = tail.limit + width - 1;

      for (int start = tail.limit; pos >= start; pos--) {
         data[pos] = getHEX_DIGIT_BYTES()[(int)(vx and 15L)];
         vx >>>= 4;
      }

      tail.limit += width;
      `$this$commonWriteHexadecimalUnsignedLong`.setSize$okio(`$this$commonWriteHexadecimalUnsignedLong`.size() + (long)width);
      return `$this$commonWriteHexadecimalUnsignedLong`;
   }
}

internal inline fun Buffer.commonWritableSegment(minimumCapacity: Int): Segment {
   if (minimumCapacity < 1 || minimumCapacity > 8192) {
      throw new IllegalArgumentException("unexpected capacity".toString());
   } else if (`$this$commonWritableSegment`.head == null) {
      val var5: Segment = SegmentPool.take();
      `$this$commonWritableSegment`.head = var5;
      var5.prev = var5;
      var5.next = var5;
      return var5;
   } else {
      val var10000: Segment = `$this$commonWritableSegment`.head;
      var tail: Segment = var10000.prev;
      if (tail.limit + minimumCapacity > 8192 || !tail.owner) {
         tail = tail.push(SegmentPool.take());
      }

      return tail;
   }
}

internal inline fun Buffer.commonWrite(source: ByteArray): Buffer {
   return `$this$commonWrite`.write(source, 0, source.length);
}

internal inline fun Buffer.commonWrite(source: ByteArray, offset: Int, byteCount: Int): Buffer {
   var offsetx: Int = offset;
   okio.-SegmentedByteString.checkOffsetAndCount((long)source.length, (long)offset, (long)byteCount);
   val limit: Int = offset + byteCount;

   while (offsetx < limit) {
      val tail: Segment = `$this$commonWrite`.writableSegment$okio(1);
      val toCopy: Int = Math.min(limit - offsetx, 8192 - tail.limit);
      ArraysKt.copyInto(source, tail.data, tail.limit, offsetx, offsetx + toCopy);
      offsetx += toCopy;
      tail.limit += toCopy;
   }

   `$this$commonWrite`.setSize$okio(`$this$commonWrite`.size() + (long)byteCount);
   return `$this$commonWrite`;
}

internal inline fun Buffer.commonReadByteArray(): ByteArray {
   return `$this$commonReadByteArray`.readByteArray(`$this$commonReadByteArray`.size());
}

internal inline fun Buffer.commonReadByteArray(byteCount: Long): ByteArray {
   if (byteCount < 0L || byteCount > 2147483647L) {
      throw new IllegalArgumentException(("byteCount: $byteCount").toString());
   } else if (`$this$commonReadByteArray`.size() < byteCount) {
      throw new EOFException();
   } else {
      val result: ByteArray = new byte[(int)byteCount];
      `$this$commonReadByteArray`.readFully(result);
      return result;
   }
}

internal inline fun Buffer.commonRead(sink: ByteArray): Int {
   return `$this$commonRead`.read(sink, 0, sink.length);
}

internal inline fun Buffer.commonReadFully(sink: ByteArray) {
   var offset: Int = 0;

   while (offset < sink.length) {
      val read: Int = `$this$commonReadFully`.read(sink, offset, sink.length - offset);
      if (read == -1) {
         throw new EOFException();
      }

      offset += read;
   }
}

internal inline fun Buffer.commonRead(sink: ByteArray, offset: Int, byteCount: Int): Int {
   okio.-SegmentedByteString.checkOffsetAndCount((long)sink.length, (long)offset, (long)byteCount);
   if (`$this$commonRead`.head == null) {
      return -1;
   } else {
      val s: Segment = `$this$commonRead`.head;
      val toCopy: Int = Math.min(byteCount, `$this$commonRead`.head.limit - `$this$commonRead`.head.pos);
      ArraysKt.copyInto(s.data, sink, offset, s.pos, s.pos + toCopy);
      s.pos += toCopy;
      `$this$commonRead`.setSize$okio(`$this$commonRead`.size() - (long)toCopy);
      if (s.pos == s.limit) {
         `$this$commonRead`.head = s.pop();
         SegmentPool.recycle(s);
      }

      return toCopy;
   }
}

internal inline fun Buffer.commonReadDecimalLong(): Long {
   if (`$this$commonReadDecimalLong`.size() == 0L) {
      throw new EOFException();
   } else {
      var value: Long = 0L;
      var seen: Int = 0;
      var negative: Boolean = false;
      var done: Boolean = false;
      var overflowDigit: Long = -7L;

      while (true) {
         val var10000: Segment = `$this$commonReadDecimalLong`.head;
         val expected: ByteArray = var10000.data;
         var pos: Int = var10000.pos;
         val limit: Int = var10000.limit;

         while (true) {
            label86: {
               if (pos < limit) {
                  val b: Byte = expected[pos];
                  if (expected[pos] >= 48 && expected[pos] <= 57) {
                     val digit: Int = 48 - b;
                     if (value < -922337203685477580L || value == -922337203685477580L && 48 - b < overflowDigit) {
                        val buffer: Buffer = new Buffer().writeDecimalLong(value).writeByte(b);
                        if (!negative) {
                           buffer.readByte();
                        }

                        throw new NumberFormatException("Number too large: ${buffer.readUtf8()}");
                     }

                     value = value * 10L + digit;
                     break label86;
                  }

                  if (b == 45 && seen == 0) {
                     negative = true;
                     overflowDigit--;
                     break label86;
                  }

                  done = true;
               }

               if (pos == limit) {
                  `$this$commonReadDecimalLong`.head = var10000.pop();
                  SegmentPool.recycle(var10000);
               } else {
                  var10000.pos = pos;
               }

               if (!done && `$this$commonReadDecimalLong`.head != null) {
                  break;
               }

               `$this$commonReadDecimalLong`.setSize$okio(`$this$commonReadDecimalLong`.size() - (long)seen);
               if (seen < (if (negative) 2 else 1)) {
                  if (`$this$commonReadDecimalLong`.size() == 0L) {
                     throw new EOFException();
                  }

                  throw new NumberFormatException(
                     "${if (negative) "Expected a digit" else "Expected a digit or '-'"} but was 0x${okio.-SegmentedByteString.toHexString(
                        `$this$commonReadDecimalLong`.getByte(0L)
                     )}"
                  );
               }

               return if (negative) value else -value;
            }

            pos++;
            seen++;
         }
      }
   }
}

internal inline fun Buffer.commonReadHexadecimalUnsignedLong(): Long {
   if (`$this$commonReadHexadecimalUnsignedLong`.size() == 0L) {
      throw new EOFException();
   } else {
      var value: Long = 0L;
      var seen: Int = 0;
      var done: Boolean = false;

      do {
         val var10000: Segment = `$this$commonReadHexadecimalUnsignedLong`.head;
         val data: ByteArray = var10000.data;
         var pos: Int = var10000.pos;

         val limit: Int;
         for (limit = var10000.limit; pos < limit; seen++) {
            val b: Byte = data[pos];
            val var14: Int;
            if (data[pos] >= 48 && data[pos] <= 57) {
               var14 = b - 48;
            } else if (b >= 97 && b <= 102) {
               var14 = b - 97 + 10;
            } else {
               if (b < 65 || b > 70) {
                  if (seen == 0) {
                     throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x${okio.-SegmentedByteString.toHexString(b)}");
                  }

                  done = true;
                  break;
               }

               var14 = b - 65 + 10;
            }

            if ((value and -1152921504606846976L) != 0L) {
               throw new NumberFormatException("Number too large: ${new Buffer().writeHexadecimalUnsignedLong(value).writeByte(b).readUtf8()}");
            }

            value = value shl 4 or var14;
            pos++;
         }

         if (pos == limit) {
            `$this$commonReadHexadecimalUnsignedLong`.head = var10000.pop();
            SegmentPool.recycle(var10000);
         } else {
            var10000.pos = pos;
         }
      } while (!done && $this$commonReadHexadecimalUnsignedLong.head != null);

      `$this$commonReadHexadecimalUnsignedLong`.setSize$okio(`$this$commonReadHexadecimalUnsignedLong`.size() - (long)seen);
      return value;
   }
}

internal inline fun Buffer.commonReadByteString(): ByteString {
   return `$this$commonReadByteString`.readByteString(`$this$commonReadByteString`.size());
}

internal inline fun Buffer.commonReadByteString(byteCount: Long): ByteString {
   if (byteCount < 0L || byteCount > 2147483647L) {
      throw new IllegalArgumentException(("byteCount: $byteCount").toString());
   } else if (`$this$commonReadByteString`.size() < byteCount) {
      throw new EOFException();
   } else if (byteCount >= 4096L) {
      val var4: ByteString = `$this$commonReadByteString`.snapshot((int)byteCount);
      `$this$commonReadByteString`.skip(byteCount);
      return var4;
   } else {
      return new ByteString(`$this$commonReadByteString`.readByteArray(byteCount));
   }
}

internal inline fun Buffer.commonSelect(options: Options): Int {
   val index: Int = selectPrefix$default(`$this$commonSelect`, options, false, 2, null);
   if (index == -1) {
      return -1;
   } else {
      `$this$commonSelect`.skip((long)options.getByteStrings$okio()[index].size());
      return index;
   }
}

internal inline fun Buffer.commonReadFully(sink: Buffer, byteCount: Long) {
   if (`$this$commonReadFully`.size() < byteCount) {
      sink.write(`$this$commonReadFully`, `$this$commonReadFully`.size());
      throw new EOFException();
   } else {
      sink.write(`$this$commonReadFully`, byteCount);
   }
}

internal inline fun Buffer.commonReadAll(sink: Sink): Long {
   val byteCount: Long = `$this$commonReadAll`.size();
   if (byteCount > 0L) {
      sink.write(`$this$commonReadAll`, byteCount);
   }

   return byteCount;
}

internal inline fun Buffer.commonReadUtf8(byteCount: Long): String {
   if (byteCount < 0L || byteCount > 2147483647L) {
      throw new IllegalArgumentException(("byteCount: $byteCount").toString());
   } else if (`$this$commonReadUtf8`.size() < byteCount) {
      throw new EOFException();
   } else if (byteCount == 0L) {
      return "";
   } else {
      val var10000: Segment = `$this$commonReadUtf8`.head;
      if (var10000.pos + byteCount > var10000.limit) {
         return _Utf8Kt.commonToUtf8String$default(`$this$commonReadUtf8`.readByteArray(byteCount), 0, 0, 3, null);
      } else {
         val result: java.lang.String = _Utf8Kt.commonToUtf8String(var10000.data, var10000.pos, var10000.pos + (int)byteCount);
         var10000.pos += (int)byteCount;
         `$this$commonReadUtf8`.setSize$okio(`$this$commonReadUtf8`.size() - byteCount);
         if (var10000.pos == var10000.limit) {
            `$this$commonReadUtf8`.head = var10000.pop();
            SegmentPool.recycle(var10000);
         }

         return result;
      }
   }
}

internal inline fun Buffer.commonReadUtf8Line(): String? {
   val newline: Long = `$this$commonReadUtf8Line`.indexOf((byte)10);
   return if (newline != -1L)
      readUtf8Line(`$this$commonReadUtf8Line`, newline)
      else
      (if (`$this$commonReadUtf8Line`.size() != 0L) `$this$commonReadUtf8Line`.readUtf8(`$this$commonReadUtf8Line`.size()) else null);
}

internal inline fun Buffer.commonReadUtf8LineStrict(limit: Long): String {
   if (limit < 0L) {
      throw new IllegalArgumentException(("limit < 0: $limit").toString());
   } else {
      val scanLength: Long = if (limit == java.lang.Long.MAX_VALUE) java.lang.Long.MAX_VALUE else limit + 1L;
      val newline: Long = `$this$commonReadUtf8LineStrict`.indexOf(
         (byte)10, 0L, if (limit == java.lang.Long.MAX_VALUE) java.lang.Long.MAX_VALUE else limit + 1L
      );
      if (newline != -1L) {
         return readUtf8Line(`$this$commonReadUtf8LineStrict`, newline);
      } else if (scanLength < `$this$commonReadUtf8LineStrict`.size()
         && `$this$commonReadUtf8LineStrict`.getByte(scanLength - 1L) == 13
         && `$this$commonReadUtf8LineStrict`.getByte(scanLength) == 10) {
         return readUtf8Line(`$this$commonReadUtf8LineStrict`, scanLength);
      } else {
         val data: Buffer = new Buffer();
         `$this$commonReadUtf8LineStrict`.copyTo(data, 0L, Math.min((long)32, `$this$commonReadUtf8LineStrict`.size()));
         throw new EOFException("\\n not found: limit=${Math.min(`$this$commonReadUtf8LineStrict`.size(), limit)} content=${data.readByteString().hex()}…");
      }
   }
}

internal inline fun Buffer.commonReadUtf8CodePoint(): Int {
   if (`$this$commonReadUtf8CodePoint`.size() == 0L) {
      throw new EOFException();
   } else {
      val b0: Byte = `$this$commonReadUtf8CodePoint`.getByte(0L);
      var var11: Int;
      val var13: Byte;
      val var14: Int;
      if ((b0 and 128) == 0) {
         var11 = b0 and 127;
         var13 = 1;
         var14 = 0;
      } else if ((b0 and 224) == 192) {
         var11 = b0 and 31;
         var13 = 2;
         var14 = 128;
      } else if ((b0 and 240) == 224) {
         var11 = b0 and 15;
         var13 = 3;
         var14 = 2048;
      } else {
         if ((b0 and 248) != 240) {
            `$this$commonReadUtf8CodePoint`.skip(1L);
            return 65533;
         }

         var11 = b0 and 7;
         var13 = 4;
         var14 = 65536;
      }

      if (`$this$commonReadUtf8CodePoint`.size() < var13) {
         throw new EOFException(
            "size < $var13: ${`$this$commonReadUtf8CodePoint`.size()} (to read code point prefixed 0x${okio.-SegmentedByteString.toHexString(b0)}${41}"
         );
      } else {
         for (int i = 1; i < var13; i++) {
            val var22: Byte = `$this$commonReadUtf8CodePoint`.getByte((long)i);
            if ((var22 and 192) != 128) {
               `$this$commonReadUtf8CodePoint`.skip((long)i);
               return 65533;
            }

            var11 = var11 shl 6 or var22 and 63;
         }

         `$this$commonReadUtf8CodePoint`.skip((long)var13);
         return if (var11 > 1114111) 65533 else (if (55296 <= var11 && var11 < 57344) 65533 else (if (var11 < var14) 65533 else var11));
      }
   }
}

internal inline fun Buffer.commonWriteUtf8(string: String, beginIndex: Int, endIndex: Int): Buffer {
   if (beginIndex < 0) {
      throw new IllegalArgumentException(("beginIndex < 0: $beginIndex").toString());
   } else if (endIndex < beginIndex) {
      throw new IllegalArgumentException(("endIndex < beginIndex: $endIndex < $beginIndex").toString());
   } else if (endIndex > string.length()) {
      throw new IllegalArgumentException(("endIndex > string.length: $endIndex > ${string.length()}").toString());
   } else {
      var i: Int = beginIndex;

      while (i < endIndex) {
         val c: Int = string.charAt(i);
         if (c < 128) {
            val var21: Segment = `$this$commonWriteUtf8`.writableSegment$okio(1);
            val var22: ByteArray = var21.data;
            val var23: Int = var21.limit - i;
            val runLimit: Int = Math.min(endIndex, 8192 - (var21.limit - i));
            var22[var23 + i++] = (byte)c;

            while (i < runLimit) {
               val var12: Char = string.charAt(i);
               if (var12 >= 128) {
                  break;
               }

               var22[var23 + i++] = (byte)var12;
            }

            val runSize: Int = i + var23 - var21.limit;
            var21.limit = var21.limit + (i + var23 - var21.limit);
            `$this$commonWriteUtf8`.setSize$okio(`$this$commonWriteUtf8`.size() + (long)runSize);
         } else if (c < 2048) {
            val var20: Segment = `$this$commonWriteUtf8`.writableSegment$okio(2);
            var20.data[var20.limit] = (byte)(c shr 6 or 192);
            var20.data[var20.limit + 1] = (byte)(c and 63 or 128);
            var20.limit += 2;
            `$this$commonWriteUtf8`.setSize$okio(`$this$commonWriteUtf8`.size() + 2L);
            i++;
         } else if (c >= 55296 && c <= 57343) {
            val var19: Int = if (i + 1 < endIndex) string.charAt(i + 1) else 0;
            if (c <= 56319 && 56320 <= var19 && var19 < 57344) {
               val codePoint: Int = 65536 + ((c and 1023) shl 10 or var19 and 1023);
               val tail: Segment = `$this$commonWriteUtf8`.writableSegment$okio(4);
               tail.data[tail.limit] = (byte)(codePoint shr 18 or 240);
               tail.data[tail.limit + 1] = (byte)(codePoint shr 12 and 63 or 128);
               tail.data[tail.limit + 2] = (byte)(codePoint shr 6 and 63 or 128);
               tail.data[tail.limit + 3] = (byte)(codePoint and 63 or 128);
               tail.limit += 4;
               `$this$commonWriteUtf8`.setSize$okio(`$this$commonWriteUtf8`.size() + 4L);
               i += 2;
            } else {
               `$this$commonWriteUtf8`.writeByte(63);
               i++;
            }
         } else {
            val low: Segment = `$this$commonWriteUtf8`.writableSegment$okio(3);
            low.data[low.limit] = (byte)(c shr 12 or 224);
            low.data[low.limit + 1] = (byte)(c shr 6 and 63 or 128);
            low.data[low.limit + 2] = (byte)(c and 63 or 128);
            low.limit += 3;
            `$this$commonWriteUtf8`.setSize$okio(`$this$commonWriteUtf8`.size() + 3L);
            i++;
         }
      }

      return `$this$commonWriteUtf8`;
   }
}

internal inline fun Buffer.commonWriteUtf8CodePoint(codePoint: Int): Buffer {
   if (codePoint < 128) {
      `$this$commonWriteUtf8CodePoint`.writeByte(codePoint);
   } else if (codePoint < 2048) {
      val tail: Segment = `$this$commonWriteUtf8CodePoint`.writableSegment$okio(2);
      tail.data[tail.limit] = (byte)(codePoint shr 6 or 192);
      tail.data[tail.limit + 1] = (byte)(codePoint and 63 or 128);
      tail.limit += 2;
      `$this$commonWriteUtf8CodePoint`.setSize$okio(`$this$commonWriteUtf8CodePoint`.size() + 2L);
   } else if (55296 <= codePoint && codePoint < 57344) {
      `$this$commonWriteUtf8CodePoint`.writeByte(63);
   } else if (codePoint < 65536) {
      val var4: Segment = `$this$commonWriteUtf8CodePoint`.writableSegment$okio(3);
      var4.data[var4.limit] = (byte)(codePoint shr 12 or 224);
      var4.data[var4.limit + 1] = (byte)(codePoint shr 6 and 63 or 128);
      var4.data[var4.limit + 2] = (byte)(codePoint and 63 or 128);
      var4.limit += 3;
      `$this$commonWriteUtf8CodePoint`.setSize$okio(`$this$commonWriteUtf8CodePoint`.size() + 3L);
   } else {
      if (codePoint > 1114111) {
         throw new IllegalArgumentException("Unexpected code point: 0x${okio.-SegmentedByteString.toHexString(codePoint)}");
      }

      val var5: Segment = `$this$commonWriteUtf8CodePoint`.writableSegment$okio(4);
      var5.data[var5.limit] = (byte)(codePoint shr 18 or 240);
      var5.data[var5.limit + 1] = (byte)(codePoint shr 12 and 63 or 128);
      var5.data[var5.limit + 2] = (byte)(codePoint shr 6 and 63 or 128);
      var5.data[var5.limit + 3] = (byte)(codePoint and 63 or 128);
      var5.limit += 4;
      `$this$commonWriteUtf8CodePoint`.setSize$okio(`$this$commonWriteUtf8CodePoint`.size() + 4L);
   }

   return `$this$commonWriteUtf8CodePoint`;
}

internal inline fun Buffer.commonWriteAll(source: Source): Long {
   var totalBytesRead: Long = 0L;

   while (true) {
      val readCount: Long = source.read(`$this$commonWriteAll`, 8192L);
      if (readCount == -1L) {
         return totalBytesRead;
      }

      totalBytesRead += readCount;
   }
}

internal inline fun Buffer.commonWrite(source: Source, byteCount: Long): Buffer {
   var byteCountx: Long = byteCount;

   while (byteCountx > 0L) {
      val read: Long = source.read(`$this$commonWrite`, byteCountx);
      if (read == -1L) {
         throw new EOFException();
      }

      byteCountx -= read;
   }

   return `$this$commonWrite`;
}

internal inline fun Buffer.commonWriteByte(b: Int): Buffer {
   val tail: Segment = `$this$commonWriteByte`.writableSegment$okio(1);
   tail.data[tail.limit++] = (byte)b;
   `$this$commonWriteByte`.setSize$okio(`$this$commonWriteByte`.size() + 1L);
   return `$this$commonWriteByte`;
}

internal inline fun Buffer.commonWriteShort(s: Int): Buffer {
   val tail: Segment = `$this$commonWriteShort`.writableSegment$okio(2);
   tail.data[tail.limit++] = (byte)(s ushr 8 and 255);
   val limit: Int;
   tail.data[limit++] = (byte)(s and 255);
   tail.limit = limit;
   `$this$commonWriteShort`.setSize$okio(`$this$commonWriteShort`.size() + 2L);
   return `$this$commonWriteShort`;
}

internal inline fun Buffer.commonWriteInt(i: Int): Buffer {
   val tail: Segment = `$this$commonWriteInt`.writableSegment$okio(4);
   tail.data[tail.limit++] = (byte)(i ushr 24 and 255);
   var limit: Int;
   tail.data[limit++] = (byte)(i ushr 16 and 255);
   tail.data[limit++] = (byte)(i ushr 8 and 255);
   tail.data[limit++] = (byte)(i and 255);
   tail.limit = limit;
   `$this$commonWriteInt`.setSize$okio(`$this$commonWriteInt`.size() + 4L);
   return `$this$commonWriteInt`;
}

internal inline fun Buffer.commonWriteLong(v: Long): Buffer {
   val tail: Segment = `$this$commonWriteLong`.writableSegment$okio(8);
   tail.data[tail.limit++] = (byte)(v ushr 56 and 255L);
   var limit: Int;
   tail.data[limit++] = (byte)(v ushr 48 and 255L);
   tail.data[limit++] = (byte)(v ushr 40 and 255L);
   tail.data[limit++] = (byte)(v ushr 32 and 255L);
   tail.data[limit++] = (byte)(v ushr 24 and 255L);
   tail.data[limit++] = (byte)(v ushr 16 and 255L);
   tail.data[limit++] = (byte)(v ushr 8 and 255L);
   tail.data[limit++] = (byte)(v and 255L);
   tail.limit = limit;
   `$this$commonWriteLong`.setSize$okio(`$this$commonWriteLong`.size() + 8L);
   return `$this$commonWriteLong`;
}

internal inline fun Buffer.commonWrite(source: Buffer, byteCount: Long) {
   var byteCountx: Long = byteCount;
   if (source === `$this$commonWrite`) {
      throw new IllegalArgumentException("source == this".toString());
   } else {
      okio.-SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);

      while (byteCountx > 0L) {
         var var10001: Segment = source.head;
         val var18: Int = var10001.limit;
         val var10002: Segment = source.head;
         if (byteCountx < var18 - var10002.pos) {
            var var15: Segment;
            if (`$this$commonWrite`.head != null) {
               var15 = `$this$commonWrite`.head;
               var15 = var15.prev;
            } else {
               var15 = null;
            }

            if (var15 != null && var15.owner && byteCountx + var15.limit - (if (var15.shared) 0 else var15.pos) <= 8192L) {
               val var17: Segment = source.head;
               var17.writeTo(var15, (int)byteCountx);
               source.setSize$okio(source.size() - byteCountx);
               `$this$commonWrite`.setSize$okio(`$this$commonWrite`.size() + byteCountx);
               return;
            }

            var10001 = source.head;
            source.head = var10001.split((int)byteCountx);
         }

         val var11: Segment = source.head;
         val movedByteCount: Long = var11.limit - var11.pos;
         source.head = var11.pop();
         if (`$this$commonWrite`.head == null) {
            `$this$commonWrite`.head = var11;
            var11.prev = var11;
            var11.next = var11.prev;
         } else {
            val var16: Segment = `$this$commonWrite`.head;
            val tail: Segment = var16.prev;
            tail.push(var11).compact();
         }

         source.setSize$okio(source.size() - movedByteCount);
         `$this$commonWrite`.setSize$okio(`$this$commonWrite`.size() + movedByteCount);
         byteCountx -= movedByteCount;
      }
   }
}

internal inline fun Buffer.commonRead(sink: Buffer, byteCount: Long): Long {
   var var9: Long = byteCount;
   if (byteCount < 0L) {
      throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
   } else if (`$this$commonRead`.size() == 0L) {
      return -1L;
   } else {
      if (byteCount > `$this$commonRead`.size()) {
         var9 = `$this$commonRead`.size();
      }

      sink.write(`$this$commonRead`, var9);
      return var9;
   }
}

internal inline fun Buffer.commonIndexOf(b: Byte, fromIndex: Long, toIndex: Long): Long {
   var var44: Long = fromIndex;
   var var45: Long = toIndex;
   if (0L > fromIndex || fromIndex > toIndex) {
      throw new IllegalArgumentException(("size=${`$this$commonIndexOf`.size()} fromIndex=$fromIndex toIndex=$toIndex").toString());
   } else {
      if (toIndex > `$this$commonIndexOf`.size()) {
         var45 = `$this$commonIndexOf`.size();
      }

      if (fromIndex == var45) {
         return -1L;
      } else {
         val `fromIndex$iv`: Long = fromIndex;
         if (`$this$commonIndexOf`.head == null) {
            return -1L;
         } else {
            var `s$iv`: Segment = `$this$commonIndexOf`.head;
            if (`$this$commonIndexOf`.size() - fromIndex < fromIndex) {
               var var43: Long;
               var var47: Segment;
               for (offset$iv = $this$commonIndexOf.size(); offset$iv > fromIndex$iv; offset$iv -= var47.limit - var47.pos) {
                  var47 = `s$iv`.prev;
                  `s$iv` = var47;
               }

               if (`s$iv` == null) {
                  return -1L;
               } else {
                  var s: Segment = `s$iv`;
                  var offset: Long = var43;

                  while (offset < var45) {
                     val data: ByteArray = s.data;
                     val limit: Int = (int)Math.min((long)s.limit, (long)s.pos + var45 - offset);

                     for (int pos = (int)(s.pos + var44 - offset); pos < limit; pos++) {
                        if (data[var42] == b) {
                           return var42 - s.pos + offset;
                        }
                     }

                     offset += s.limit - s.pos;
                     var44 = offset;
                     var47 = s.next;
                     s = var47;
                  }

                  return -1L;
               }
            } else {
               var `offset$ivx`: Long = 0L;

               while (true) {
                  val `nextOffset$iv`: Long = `offset$ivx` + (`s$iv`.limit - `s$iv`.pos);
                  if (`offset$ivx` + (`s$iv`.limit - `s$iv`.pos) > `fromIndex$iv`) {
                     if (`s$iv` == null) {
                        return -1L;
                     } else {
                        var s: Segment = `s$iv`;
                        var offset: Long = `offset$ivx`;

                        while (offset < var45) {
                           val data: ByteArray = s.data;
                           val limit: Int = (int)Math.min((long)s.limit, (long)s.pos + var45 - offset);

                           for (int posx = (int)(s.pos + var44 - offset); posx < limit; posx++) {
                              if (data[posx] == b) {
                                 return posx - s.pos + offset;
                              }
                           }

                           offset += s.limit - s.pos;
                           var44 = offset;
                           val var46: Segment = s.next;
                           s = var46;
                        }

                        return -1L;
                     }
                  }

                  val var10000: Segment = `s$iv`.next;
                  `s$iv` = var10000;
                  `offset$ivx` = `nextOffset$iv`;
               }
            }
         }
      }
   }
}

internal fun Buffer.commonIndexOf(
   bytes: ByteString,
   fromIndex: Long,
   toIndex: Long = java.lang.Long.MAX_VALUE,
   bytesOffset: Int = 0,
   byteCount: Int = bytes.size()
): Long {
   okio.-SegmentedByteString.checkOffsetAndCount((long)bytes.size(), (long)bytesOffset, (long)byteCount);
   if (byteCount <= 0) {
      throw new IllegalArgumentException("byteCount == 0".toString());
   } else if (fromIndex < 0L) {
      throw new IllegalArgumentException(("fromIndex < 0: $fromIndex").toString());
   } else if (fromIndex > toIndex) {
      throw new IllegalArgumentException(("fromIndex > toIndex: $fromIndex > $toIndex").toString());
   } else {
      var var59: Long = fromIndex;
      var var60: Long = toIndex;
      if (toIndex > `$this$commonIndexOf`.size()) {
         var60 = `$this$commonIndexOf`.size();
      }

      if (fromIndex == var60) {
         return -1L;
      } else {
         val `fromIndex$iv`: Long = fromIndex;
         if (`$this$commonIndexOf`.head == null) {
            return -1L;
         } else {
            var `s$iv`: Segment = `$this$commonIndexOf`.head;
            if (`$this$commonIndexOf`.size() - fromIndex < fromIndex) {
               var var58: Long;
               var var62: Segment;
               for (offset$iv = $this$commonIndexOf.size(); offset$iv > fromIndex$iv; offset$iv -= var62.limit - var62.pos) {
                  var62 = `s$iv`.prev;
                  `s$iv` = var62;
               }

               if (`s$iv` == null) {
                  return -1L;
               } else {
                  var var46: Segment = `s$iv`;
                  var var47: Long = var58;
                  val var48: ByteArray = bytes.internalArray$okio();
                  val var49: Byte = var48[bytesOffset];
                  val var50: Long = Math.min(var60, `$this$commonIndexOf`.size() - (long)byteCount + 1L);

                  while (offset < resultLimit) {
                     val var51: ByteArray = var46.data;
                     val var57: Int = (int)Math.min((long)var46.limit, (long)var46.pos + var50 - var47);

                     for (int pos = (int)(s.pos + var59 - offset); pos < segmentLimit; pos++) {
                        if (var51[var54] == var49 && rangeEquals(var46, var54 + 1, var48, bytesOffset + 1, byteCount)) {
                           return var54 - var46.pos + var47;
                        }
                     }

                     var47 += var46.limit - var46.pos;
                     var59 = var47;
                     var62 = var46.next;
                     var46 = var62;
                  }

                  return -1L;
               }
            } else {
               var `offset$ivx`: Long = 0L;

               while (true) {
                  val `nextOffset$iv`: Long = `offset$ivx` + (`s$iv`.limit - `s$iv`.pos);
                  if (`offset$ivx` + (`s$iv`.limit - `s$iv`.pos) > `fromIndex$iv`) {
                     if (`s$iv` == null) {
                        return -1L;
                     } else {
                        var s: Segment = `s$iv`;
                        var offset: Long = `offset$ivx`;
                        val targetByteArray: ByteArray = bytes.internalArray$okio();
                        val b0: Byte = targetByteArray[bytesOffset];
                        val resultLimit: Long = Math.min(var60, `$this$commonIndexOf`.size() - (long)byteCount + 1L);

                        while (offset < resultLimit) {
                           val data: ByteArray = s.data;
                           val segmentLimit: Int = (int)Math.min((long)s.limit, (long)s.pos + resultLimit - offset);

                           for (int posx = (int)(s.pos + var59 - offset); posx < segmentLimit; posx++) {
                              if (data[posx] == b0 && rangeEquals(s, posx + 1, targetByteArray, bytesOffset + 1, byteCount)) {
                                 return posx - s.pos + offset;
                              }
                           }

                           offset += s.limit - s.pos;
                           var59 = offset;
                           val var61: Segment = s.next;
                           s = var61;
                        }

                        return -1L;
                     }
                  }

                  val var10000: Segment = `s$iv`.next;
                  `s$iv` = var10000;
                  `offset$ivx` = `nextOffset$iv`;
               }
            }
         }
      }
   }
}

@JvmSynthetic
fun `commonIndexOf$default`(var0: Buffer, var1: ByteString, var2: Long, var4: Long, var6: Int, var7: Int, var8: Int, var9: Any): Long {
   if ((var8 and 4) != 0) {
      var4 = java.lang.Long.MAX_VALUE;
   }

   if ((var8 and 8) != 0) {
      var6 = 0;
   }

   if ((var8 and 16) != 0) {
      var7 = var1.size();
   }

   return commonIndexOf(var0, var1, var2, var4, var6, var7);
}

internal inline fun Buffer.commonIndexOfElement(targetBytes: ByteString, fromIndex: Long): Long {
   var var62: Long = fromIndex;
   if (fromIndex < 0L) {
      throw new IllegalArgumentException(("fromIndex < 0: $fromIndex").toString());
   } else {
      val `fromIndex$iv`: Long = fromIndex;
      if (`$this$commonIndexOfElement`.head == null) {
         return -1L;
      } else {
         var `s$iv`: Segment = `$this$commonIndexOfElement`.head;
         if (`$this$commonIndexOfElement`.size() - fromIndex < fromIndex) {
            var var61: Long;
            var var65: Segment;
            for (offset$iv = $this$commonIndexOfElement.size(); offset$iv > fromIndex$iv; offset$iv -= var65.limit - var65.pos) {
               var65 = `s$iv`.prev;
               `s$iv` = var65;
            }

            if (`s$iv` == null) {
               return -1L;
            } else {
               var s: Segment = `s$iv`;
               var offset: Long = var61;
               if (targetBytes.size() != 2) {
                  val var43: ByteArray = targetBytes.internalArray$okio();

                  while (offset < $this$commonIndexOfElement.size()) {
                     val var44: ByteArray = s.data;
                     var var47: Int = (int)(s.pos + var62 - offset);

                     for (int limit = s.limit; pos < limit; pos++) {
                        val var53: Int = var44[var47];

                        for (byte t : targetByteArray) {
                           if (var53 == var60) {
                              return var47 - s.pos + offset;
                           }
                        }
                     }

                     offset += s.limit - s.pos;
                     var62 = offset;
                     var65 = s.next;
                     s = var65;
                  }
               } else {
                  val targetByteArray: Byte = targetBytes.getByte(0);
                  val data: Byte = targetBytes.getByte(1);

                  while (offset < $this$commonIndexOfElement.size()) {
                     val var46: ByteArray = s.data;
                     var var49: Int = (int)(s.pos + var62 - offset);

                     for (int limit = s.limit; pos < limit; pos++) {
                        if (var46[var49] == targetByteArray || var46[var49] == data) {
                           return var49 - s.pos + offset;
                        }
                     }

                     offset += s.limit - s.pos;
                     var62 = offset;
                     var65 = s.next;
                     s = var65;
                  }
               }

               return -1L;
            }
         } else {
            var `offset$ivx`: Long = 0L;

            while (true) {
               val `nextOffset$iv`: Long = `offset$ivx` + (`s$iv`.limit - `s$iv`.pos);
               if (`offset$ivx` + (`s$iv`.limit - `s$iv`.pos) > `fromIndex$iv`) {
                  if (`s$iv` == null) {
                     return -1L;
                  } else {
                     var s: Segment = `s$iv`;
                     var offset: Long = `offset$ivx`;
                     if (targetBytes.size() != 2) {
                        val var45: ByteArray = targetBytes.internalArray$okio();

                        while (offset < $this$commonIndexOfElement.size()) {
                           val var48: ByteArray = s.data;
                           var var51: Int = (int)(s.pos + var62 - offset);

                           for (int limitx = s.limit; pos < limitx; pos++) {
                              val var57: Int = var48[var51];

                              for (byte tx : targetByteArray) {
                                 if (var57 == tx) {
                                    return var51 - s.pos + offset;
                                 }
                              }
                           }

                           offset += s.limit - s.pos;
                           var62 = offset;
                           val var64: Segment = s.next;
                           s = var64;
                        }
                     } else {
                        val targetByteArray: Byte = targetBytes.getByte(0);
                        val data: Byte = targetBytes.getByte(1);

                        while (offset < $this$commonIndexOfElement.size()) {
                           val pos: ByteArray = s.data;
                           var limitx: Int = (int)(s.pos + var62 - offset);

                           for (int limitxx = s.limit; limitx < limitxx; limitx++) {
                              if (pos[limitx] == targetByteArray || pos[limitx] == data) {
                                 return limitx - s.pos + offset;
                              }
                           }

                           offset += s.limit - s.pos;
                           var62 = offset;
                           val var63: Segment = s.next;
                           s = var63;
                        }
                     }

                     return -1L;
                  }
               }

               val var10000: Segment = `s$iv`.next;
               `s$iv` = var10000;
               `offset$ivx` = `nextOffset$iv`;
            }
         }
      }
   }
}

internal inline fun Buffer.commonRangeEquals(offset: Long, bytes: ByteString, bytesOffset: Int, byteCount: Int): Boolean {
   if (byteCount < 0) {
      return false;
   } else if (offset < 0L || offset + byteCount > `$this$commonRangeEquals`.size()) {
      return false;
   } else if (bytesOffset < 0 || bytesOffset + byteCount > bytes.size()) {
      return false;
   } else if (byteCount == 0) {
      return true;
   } else {
      return commonIndexOf(`$this$commonRangeEquals`, bytes, offset, offset + 1L, bytesOffset, byteCount) != -1L;
   }
}

internal inline fun Buffer.commonEquals(other: Any?): Boolean {
   if (`$this$commonEquals` === other) {
      return true;
   } else if (other !is Buffer) {
      return false;
   } else if (`$this$commonEquals`.size() != (other as Buffer).size()) {
      return false;
   } else if (`$this$commonEquals`.size() == 0L) {
      return true;
   } else {
      var var10000: Segment = `$this$commonEquals`.head;
      var sa: Segment = var10000;
      val var16: Segment = (other as Buffer).head;
      var sb: Segment = var16;
      var posA: Int = var10000.pos;
      var posB: Int = var16.pos;
      var pos: Long = 0L;

      while (pos < $this$commonEquals.size()) {
         val var15: Long = Math.min(sa.limit - posA, sb.limit - posB);
         var i: Long = 0L;

         for (long var13 = var15; i < var13; i++) {
            if (sa.data[posA++] != sb.data[posB++]) {
               return false;
            }
         }

         if (posA == sa.limit) {
            var10000 = sa.next;
            sa = var10000;
            posA = var10000.pos;
         }

         if (posB == sb.limit) {
            var10000 = sb.next;
            sb = var10000;
            posB = var10000.pos;
         }

         pos += var15;
      }

      return true;
   }
}

internal inline fun Buffer.commonHashCode(): Int {
   if (`$this$commonHashCode`.head == null) {
      return 0;
   } else {
      var s: Segment = `$this$commonHashCode`.head;
      var result: Int = 1;

      val var10000: Segment;
      do {
         var pos: Int = s.pos;

         for (int limit = s.limit; pos < limit; pos++) {
            result = 31 * result + s.data[pos];
         }

         var10000 = s.next;
         s = var10000;
      } while (var10000 != $this$commonHashCode.head);

      return result;
   }
}

internal inline fun Buffer.commonCopy(): Buffer {
   val result: Buffer = new Buffer();
   if (`$this$commonCopy`.size() == 0L) {
      return result;
   } else {
      var var10000: Segment = `$this$commonCopy`.head;
      val head: Segment = var10000;
      val headCopy: Segment = var10000.sharedCopy();
      result.head = headCopy;
      headCopy.prev = result.head;
      headCopy.next = headCopy.prev;

      for (Segment s = var10000.next; s != head; s = s.next) {
         var10000 = headCopy.prev;
         var10000.push(s.sharedCopy());
      }

      result.setSize$okio(`$this$commonCopy`.size());
      return result;
   }
}

internal inline fun Buffer.commonSnapshot(): ByteString {
   if (`$this$commonSnapshot`.size() > 2147483647L) {
      throw new IllegalStateException(("size > Int.MAX_VALUE: ${`$this$commonSnapshot`.size()}").toString());
   } else {
      return `$this$commonSnapshot`.snapshot((int)`$this$commonSnapshot`.size());
   }
}

internal inline fun Buffer.commonSnapshot(byteCount: Int): ByteString {
   if (byteCount == 0) {
      return ByteString.EMPTY;
   } else {
      okio.-SegmentedByteString.checkOffsetAndCount(`$this$commonSnapshot`.size(), 0L, (long)byteCount);
      var offset: Int = 0;
      var segmentCount: Int = 0;

      for (Segment s = $this$commonSnapshot.head; offset < byteCount; s = s.next) {
         if (s.limit == s.pos) {
            throw new AssertionError("s.limit == s.pos");
         }

         offset += s.limit - s.pos;
         segmentCount++;
      }

      val segments: Array<ByteArray> = new byte[segmentCount][];
      val directory: IntArray = new int[segmentCount * 2];
      offset = 0;
      segmentCount = 0;

      for (Segment var10 = $this$commonSnapshot.head; offset < byteCount; var10 = var10.next) {
         segments[segmentCount] = var10.data;
         offset += var10.limit - var10.pos;
         directory[segmentCount] = Math.min(offset, byteCount);
         directory[segmentCount + (segments as Array<Any>).length] = var10.pos;
         var10.shared = true;
         segmentCount++;
      }

      return new SegmentedByteString(segments, directory);
   }
}

internal fun Buffer.commonReadUnsafe(unsafeCursor: UnsafeCursor): UnsafeCursor {
   val unsafeCursorx: Buffer.UnsafeCursor = okio.-SegmentedByteString.resolveDefaultParameter(unsafeCursor);
   if (unsafeCursorx.buffer != null) {
      throw new IllegalStateException("already attached to a buffer".toString());
   } else {
      unsafeCursorx.buffer = `$this$commonReadUnsafe`;
      unsafeCursorx.readWrite = false;
      return unsafeCursorx;
   }
}

internal fun Buffer.commonReadAndWriteUnsafe(unsafeCursor: UnsafeCursor): UnsafeCursor {
   val unsafeCursorx: Buffer.UnsafeCursor = okio.-SegmentedByteString.resolveDefaultParameter(unsafeCursor);
   if (unsafeCursorx.buffer != null) {
      throw new IllegalStateException("already attached to a buffer".toString());
   } else {
      unsafeCursorx.buffer = `$this$commonReadAndWriteUnsafe`;
      unsafeCursorx.readWrite = true;
      return unsafeCursorx;
   }
}

internal inline fun UnsafeCursor.commonNext(): Int {
   val var10000: Long = `$this$commonNext`.offset;
   val var10001: Buffer = `$this$commonNext`.buffer;
   if (var10000 == var10001.size()) {
      throw new IllegalStateException("no more bytes".toString());
   } else {
      return if (`$this$commonNext`.offset == -1L)
         `$this$commonNext`.seek(0L)
         else
         `$this$commonNext`.seek(`$this$commonNext`.offset + (long)(`$this$commonNext`.end - `$this$commonNext`.start));
   }
}

internal inline fun UnsafeCursor.commonSeek(offset: Long): Int {
   if (`$this$commonSeek`.buffer == null) {
      throw new IllegalStateException("not attached to a buffer".toString());
   } else {
      val buffer: Buffer = `$this$commonSeek`.buffer;
      if (offset < -1L || offset > `$this$commonSeek`.buffer.size()) {
         throw new ArrayIndexOutOfBoundsException("offset=$offset > size=${buffer.size()}");
      } else if (offset != -1L && offset != buffer.size()) {
         var min: Long = 0L;
         var max: Long = buffer.size();
         var head: Segment = buffer.head;
         var tail: Segment = buffer.head;
         if (`$this$commonSeek`.getSegment$okio() != null) {
            val var10000: Long = `$this$commonSeek`.offset;
            val var10001: Int = `$this$commonSeek`.start;
            val var10002: Segment = `$this$commonSeek`.getSegment$okio();
            val next: Long = var10000 - (var10001 - var10002.pos);
            if (var10000 - (var10001 - var10002.pos) > offset) {
               max = next;
               tail = `$this$commonSeek`.getSegment$okio();
            } else {
               min = next;
               head = `$this$commonSeek`.getSegment$okio();
            }
         }

         var var17: Segment;
         var var18: Long;
         if (max - offset > offset - min) {
            var17 = head;
            var18 = min;

            while (true) {
               if (offset < var18 + (var17.limit - var17.pos)) {
                  break;
               }

               var18 += var17.limit - var17.pos;
               var17 = var17.next;
            }
         } else {
            var17 = tail;

            for (var18 = max; var18 > offset; var18 -= var17.limit - var17.pos) {
               var17 = var17.prev;
            }
         }

         if (`$this$commonSeek`.readWrite) {
            if (var17.shared) {
               val unsharedNext: Segment = var17.unsharedCopy();
               if (buffer.head === var17) {
                  buffer.head = unsharedNext;
               }

               var17 = var17.push(unsharedNext);
               val var19: Segment = var17.prev;
               var19.pop();
            }
         }

         `$this$commonSeek`.setSegment$okio(var17);
         `$this$commonSeek`.offset = offset;
         `$this$commonSeek`.data = var17.data;
         `$this$commonSeek`.start = var17.pos + (int)(offset - var18);
         `$this$commonSeek`.end = var17.limit;
         return `$this$commonSeek`.end - `$this$commonSeek`.start;
      } else {
         `$this$commonSeek`.setSegment$okio(null);
         `$this$commonSeek`.offset = offset;
         `$this$commonSeek`.data = null;
         `$this$commonSeek`.start = -1;
         `$this$commonSeek`.end = -1;
         return -1;
      }
   }
}

internal inline fun UnsafeCursor.commonResizeBuffer(newSize: Long): Long {
   if (`$this$commonResizeBuffer`.buffer == null) {
      throw new IllegalStateException("not attached to a buffer".toString());
   } else {
      val buffer: Buffer = `$this$commonResizeBuffer`.buffer;
      if (!`$this$commonResizeBuffer`.readWrite) {
         throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers".toString());
      } else {
         val oldSize: Long = `$this$commonResizeBuffer`.buffer.size();
         if (newSize <= oldSize) {
            if (newSize < 0L) {
               throw new IllegalArgumentException(("newSize < 0: $newSize").toString());
            }

            var needsToSeek: Long = oldSize - newSize;

            while (bytesToSubtract > 0L) {
               val var10000: Segment = buffer.head;
               val tail: Segment = var10000.prev;
               val tailx: Int = tail.limit - tail.pos;
               if (tail.limit - tail.pos > needsToSeek) {
                  tail.limit -= (int)needsToSeek;
                  break;
               }

               buffer.head = tail.pop();
               SegmentPool.recycle(tail);
               needsToSeek -= tailx;
            }

            `$this$commonResizeBuffer`.setSegment$okio(null);
            `$this$commonResizeBuffer`.offset = newSize;
            `$this$commonResizeBuffer`.data = null;
            `$this$commonResizeBuffer`.start = -1;
            `$this$commonResizeBuffer`.end = -1;
         } else if (newSize > oldSize) {
            var var19: Boolean = true;
            var var21: Long = newSize - oldSize;

            while (bytesToAdd > 0L) {
               val var22: Segment = buffer.writableSegment$okio(1);
               val segmentBytesToAdd: Int = (int)Math.min(var21, (long)(8192 - var22.limit));
               var22.limit += segmentBytesToAdd;
               var21 -= segmentBytesToAdd;
               if (var19) {
                  `$this$commonResizeBuffer`.setSegment$okio(var22);
                  `$this$commonResizeBuffer`.offset = oldSize;
                  `$this$commonResizeBuffer`.data = var22.data;
                  `$this$commonResizeBuffer`.start = var22.limit - segmentBytesToAdd;
                  `$this$commonResizeBuffer`.end = var22.limit;
                  var19 = false;
               }
            }
         }

         buffer.setSize$okio(newSize);
         return oldSize;
      }
   }
}

internal inline fun UnsafeCursor.commonExpandBuffer(minByteCount: Int): Long {
   if (minByteCount <= 0) {
      throw new IllegalArgumentException(("minByteCount <= 0: $minByteCount").toString());
   } else if (minByteCount > 8192) {
      throw new IllegalArgumentException(("minByteCount > Segment.SIZE: $minByteCount").toString());
   } else if (`$this$commonExpandBuffer`.buffer == null) {
      throw new IllegalStateException("not attached to a buffer".toString());
   } else {
      val buffer: Buffer = `$this$commonExpandBuffer`.buffer;
      if (!`$this$commonExpandBuffer`.readWrite) {
         throw new IllegalStateException("expandBuffer() only permitted for read/write buffers".toString());
      } else {
         val oldSize: Long = `$this$commonExpandBuffer`.buffer.size();
         val tail: Segment = buffer.writableSegment$okio(minByteCount);
         val result: Int = 8192 - tail.limit;
         tail.limit = 8192;
         buffer.setSize$okio(oldSize + (long)result);
         `$this$commonExpandBuffer`.setSegment$okio(tail);
         `$this$commonExpandBuffer`.offset = oldSize;
         `$this$commonExpandBuffer`.data = tail.data;
         `$this$commonExpandBuffer`.start = 8192 - result;
         `$this$commonExpandBuffer`.end = 8192;
         return result;
      }
   }
}

internal inline fun UnsafeCursor.commonClose() {
   if (`$this$commonClose`.buffer == null) {
      throw new IllegalStateException("not attached to a buffer".toString());
   } else {
      `$this$commonClose`.buffer = null;
      `$this$commonClose`.setSegment$okio(null);
      `$this$commonClose`.offset = -1L;
      `$this$commonClose`.data = null;
      `$this$commonClose`.start = -1;
      `$this$commonClose`.end = -1;
   }
}

@JvmSynthetic
fun `access$countDigitsIn`(v: Long): Int {
   return countDigitsIn(v);
}
