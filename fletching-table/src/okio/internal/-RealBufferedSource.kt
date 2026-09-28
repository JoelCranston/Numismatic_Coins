@file:JvmName(name = "-RealBufferedSource")

@file:SourceDebugExtension(["SMAP\nRealBufferedSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSource.kt\nokio/internal/-RealBufferedSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,472:1\n1#2:473\n63#3:474\n63#3:475\n63#3:476\n63#3:477\n63#3:478\n63#3:479\n63#3:480\n63#3:481\n63#3:482\n63#3:483\n63#3:484\n63#3:485\n63#3:486\n63#3:487\n63#3:488\n63#3:489\n63#3:490\n63#3:491\n63#3:492\n63#3:493\n63#3:494\n63#3:495\n63#3:496\n63#3:498\n63#3:499\n63#3:500\n63#3:501\n63#3:502\n63#3:503\n63#3:504\n63#3:505\n63#3:506\n63#3:507\n63#3:508\n63#3:509\n63#3:510\n63#3:511\n63#3:512\n63#3:513\n63#3:514\n63#3:515\n63#3:516\n63#3:517\n63#3:519\n63#3:520\n63#3:521\n63#3:522\n63#3:523\n63#3:524\n63#3:525\n63#3:526\n63#3:527\n63#3:528\n63#3:529\n63#3:530\n63#3:531\n63#3:532\n63#3:533\n63#3:534\n63#3:535\n63#3:536\n63#3:537\n63#3:538\n63#3:539\n63#3:540\n63#3:541\n63#3:543\n63#3:544\n63#3:545\n63#3:546\n88#4:497\n88#4:518\n88#4:542\n*S KotlinDebug\n*F\n+ 1 RealBufferedSource.kt\nokio/internal/-RealBufferedSource\n*L\n42#1:474\n44#1:475\n48#1:476\n49#1:477\n54#1:478\n64#1:479\n65#1:480\n72#1:481\n76#1:482\n77#1:483\n82#1:484\n89#1:485\n96#1:486\n101#1:487\n109#1:488\n110#1:489\n115#1:490\n124#1:491\n125#1:492\n132#1:493\n138#1:494\n140#1:495\n144#1:496\n145#1:498\n153#1:499\n157#1:500\n162#1:501\n163#1:502\n166#1:503\n169#1:504\n170#1:505\n171#1:506\n177#1:507\n178#1:508\n183#1:509\n190#1:510\n191#1:511\n196#1:512\n204#1:513\n206#1:514\n207#1:515\n209#1:516\n212#1:517\n214#1:519\n222#1:520\n229#1:521\n234#1:522\n239#1:523\n244#1:524\n249#1:525\n254#1:526\n259#1:527\n267#1:528\n278#1:529\n286#1:530\n300#1:531\n307#1:532\n310#1:533\n311#1:534\n322#1:535\n327#1:536\n328#1:537\n349#1:538\n358#1:539\n362#1:540\n372#1:541\n425#1:543\n428#1:544\n429#1:545\n466#1:546\n144#1:497\n212#1:518\n406#1:542\n*E\n"])

package okio.internal

import java.io.EOFException
import kotlin.jvm.internal.SourceDebugExtension
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.Okio
import okio.Options
import okio.PeekSource
import okio.RealBufferedSource
import okio.Sink
import okio.Timeout

internal inline fun RealBufferedSource.commonRead(sink: Buffer, byteCount: Long): Long {
   if (byteCount < 0L) {
      throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
   } else if (`$this$commonRead`.closed) {
      throw new IllegalStateException("closed".toString());
   } else {
      if (`$this$commonRead`.bufferField.size() == 0L) {
         if (byteCount == 0L) {
            return 0L;
         }

         if (`$this$commonRead`.source.read(`$this$commonRead`.bufferField, 8192L) == -1L) {
            return -1L;
         }
      }

      return `$this$commonRead`.bufferField.read(sink, Math.min(byteCount, `$this$commonRead`.bufferField.size()));
   }
}

internal inline fun RealBufferedSource.commonExhausted(): Boolean {
   if (`$this$commonExhausted`.closed) {
      throw new IllegalStateException("closed".toString());
   } else {
      return `$this$commonExhausted`.bufferField.exhausted() && `$this$commonExhausted`.source.read(`$this$commonExhausted`.bufferField, 8192L) == -1L;
   }
}

internal inline fun RealBufferedSource.commonRequire(byteCount: Long) {
   if (!`$this$commonRequire`.request(byteCount)) {
      throw new EOFException();
   }
}

internal inline fun RealBufferedSource.commonRequest(byteCount: Long): Boolean {
   if (byteCount < 0L) {
      throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
   } else if (`$this$commonRequest`.closed) {
      throw new IllegalStateException("closed".toString());
   } else {
      do {
         if (`$this$commonRequest`.bufferField.size() >= byteCount) {
            return true;
         }
      } while ($this$commonRequest.source.read($this$commonRequest.bufferField, 8192L) != -1L);

      return false;
   }
}

internal inline fun RealBufferedSource.commonReadByte(): Byte {
   `$this$commonReadByte`.require(1L);
   return `$this$commonReadByte`.bufferField.readByte();
}

internal inline fun RealBufferedSource.commonReadByteString(): ByteString {
   `$this$commonReadByteString`.bufferField.writeAll(`$this$commonReadByteString`.source);
   return `$this$commonReadByteString`.bufferField.readByteString();
}

internal inline fun RealBufferedSource.commonReadByteString(byteCount: Long): ByteString {
   `$this$commonReadByteString`.require(byteCount);
   return `$this$commonReadByteString`.bufferField.readByteString(byteCount);
}

internal inline fun RealBufferedSource.commonSelect(options: Options): Int {
   if (`$this$commonSelect`.closed) {
      throw new IllegalStateException("closed".toString());
   } else {
      label18:
      while (true) {
         val index: Int = -Buffer.selectPrefix(`$this$commonSelect`.bufferField, options, true);
         switch (index) {
            case -2:
               if (`$this$commonSelect`.source.read(`$this$commonSelect`.bufferField, 8192L) == -1L) {
                  break label18;
               }
               break;
            case -1:
               return -1;
            default:
               `$this$commonSelect`.bufferField.skip((long)options.getByteStrings$okio()[index].size());
               return index;
         }
      }

      return -1;
   }
}

internal inline fun RealBufferedSource.commonReadByteArray(): ByteArray {
   `$this$commonReadByteArray`.bufferField.writeAll(`$this$commonReadByteArray`.source);
   return `$this$commonReadByteArray`.bufferField.readByteArray();
}

internal inline fun RealBufferedSource.commonReadByteArray(byteCount: Long): ByteArray {
   `$this$commonReadByteArray`.require(byteCount);
   return `$this$commonReadByteArray`.bufferField.readByteArray(byteCount);
}

internal inline fun RealBufferedSource.commonReadFully(sink: ByteArray) {
   try {
      `$this$commonReadFully`.require((long)sink.length);
   } catch (var8: EOFException) {
      var `$i$f$getBuffer`: Int = 0;

      while (true) {
         if (`$this$commonReadFully`.bufferField.size() <= 0L) {
            throw var8;
         }

         val read: Int = `$this$commonReadFully`.bufferField.read(sink, `$i$f$getBuffer`, (int)`$this$commonReadFully`.bufferField.size());
         if (read == -1) {
            throw new AssertionError();
         }

         `$i$f$getBuffer` += read;
      }
   }

   `$this$commonReadFully`.bufferField.readFully(sink);
}

internal inline fun RealBufferedSource.commonRead(sink: ByteArray, offset: Int, byteCount: Int): Int {
   okio.-SegmentedByteString.checkOffsetAndCount((long)sink.length, (long)offset, (long)byteCount);
   if (`$this$commonRead`.bufferField.size() == 0L) {
      if (byteCount == 0) {
         return 0;
      }

      if (`$this$commonRead`.source.read(`$this$commonRead`.bufferField, 8192L) == -1L) {
         return -1;
      }
   }

   return `$this$commonRead`.bufferField.read(sink, offset, (int)Math.min((long)byteCount, `$this$commonRead`.bufferField.size()));
}

internal inline fun RealBufferedSource.commonReadFully(sink: Buffer, byteCount: Long) {
   try {
      `$this$commonReadFully`.require(byteCount);
   } catch (var8: EOFException) {
      sink.writeAll(`$this$commonReadFully`.bufferField);
      throw var8;
   }

   `$this$commonReadFully`.bufferField.readFully(sink, byteCount);
}

internal inline fun RealBufferedSource.commonReadAll(sink: Sink): Long {
   var totalBytesWritten: Long = 0L;

   while (true) {
      if (`$this$commonReadAll`.source.read(`$this$commonReadAll`.bufferField, 8192L) == -1L) {
         if (`$this$commonReadAll`.bufferField.size() > 0L) {
            totalBytesWritten += `$this$commonReadAll`.bufferField.size();
            sink.write(`$this$commonReadAll`.bufferField, `$this$commonReadAll`.bufferField.size());
         }

         return totalBytesWritten;
      }

      val `this_$iv`: Long = `$this$commonReadAll`.bufferField.completeSegmentByteCount();
      if (`this_$iv` > 0L) {
         totalBytesWritten += `this_$iv`;
         sink.write(`$this$commonReadAll`.bufferField, `this_$iv`);
      }
   }
}

internal inline fun RealBufferedSource.commonReadUtf8(): String {
   `$this$commonReadUtf8`.bufferField.writeAll(`$this$commonReadUtf8`.source);
   return `$this$commonReadUtf8`.bufferField.readUtf8();
}

internal inline fun RealBufferedSource.commonReadUtf8(byteCount: Long): String {
   `$this$commonReadUtf8`.require(byteCount);
   return `$this$commonReadUtf8`.bufferField.readUtf8(byteCount);
}

internal inline fun RealBufferedSource.commonReadUtf8Line(): String? {
   val newline: Long = `$this$commonReadUtf8Line`.indexOf((byte)10);
   return if (newline == -1L)
      (if (`$this$commonReadUtf8Line`.bufferField.size() != 0L) `$this$commonReadUtf8Line`.readUtf8(`$this$commonReadUtf8Line`.bufferField.size()) else null)
      else
      -Buffer.readUtf8Line(`$this$commonReadUtf8Line`.bufferField, newline);
}

internal inline fun RealBufferedSource.commonReadUtf8LineStrict(limit: Long): String {
   if (limit < 0L) {
      throw new IllegalArgumentException(("limit < 0: $limit").toString());
   } else {
      val scanLength: Long = if (limit == java.lang.Long.MAX_VALUE) java.lang.Long.MAX_VALUE else limit + 1L;
      val newline: Long = `$this$commonReadUtf8LineStrict`.indexOf(
         (byte)10, 0L, if (limit == java.lang.Long.MAX_VALUE) java.lang.Long.MAX_VALUE else limit + 1L
      );
      if (newline != -1L) {
         return -Buffer.readUtf8Line(`$this$commonReadUtf8LineStrict`.bufferField, newline);
      } else if (scanLength < java.lang.Long.MAX_VALUE
         && `$this$commonReadUtf8LineStrict`.request(scanLength)
         && `$this$commonReadUtf8LineStrict`.bufferField.getByte(scanLength - 1L) == 13
         && `$this$commonReadUtf8LineStrict`.request(scanLength + 1L)
         && `$this$commonReadUtf8LineStrict`.bufferField.getByte(scanLength) == 10) {
         return -Buffer.readUtf8Line(`$this$commonReadUtf8LineStrict`.bufferField, scanLength);
      } else {
         val data: Buffer = new Buffer();
         `$this$commonReadUtf8LineStrict`.bufferField.copyTo(data, 0L, Math.min((long)32, `$this$commonReadUtf8LineStrict`.bufferField.size()));
         throw new EOFException(
            "\\n not found: limit=${Math.min(`$this$commonReadUtf8LineStrict`.bufferField.size(), limit)} content=${data.readByteString().hex()}…"
         );
      }
   }
}

internal inline fun RealBufferedSource.commonReadUtf8CodePoint(): Int {
   `$this$commonReadUtf8CodePoint`.require(1L);
   val b0: Int = `$this$commonReadUtf8CodePoint`.bufferField.getByte(0L);
   if ((b0 and 224) == 192) {
      `$this$commonReadUtf8CodePoint`.require(2L);
   } else if ((b0 and 240) == 224) {
      `$this$commonReadUtf8CodePoint`.require(3L);
   } else if ((b0 and 248) == 240) {
      `$this$commonReadUtf8CodePoint`.require(4L);
   }

   return `$this$commonReadUtf8CodePoint`.bufferField.readUtf8CodePoint();
}

internal inline fun RealBufferedSource.commonReadShort(): Short {
   `$this$commonReadShort`.require(2L);
   return `$this$commonReadShort`.bufferField.readShort();
}

internal inline fun RealBufferedSource.commonReadShortLe(): Short {
   `$this$commonReadShortLe`.require(2L);
   return `$this$commonReadShortLe`.bufferField.readShortLe();
}

internal inline fun RealBufferedSource.commonReadInt(): Int {
   `$this$commonReadInt`.require(4L);
   return `$this$commonReadInt`.bufferField.readInt();
}

internal inline fun RealBufferedSource.commonReadIntLe(): Int {
   `$this$commonReadIntLe`.require(4L);
   return `$this$commonReadIntLe`.bufferField.readIntLe();
}

internal inline fun RealBufferedSource.commonReadLong(): Long {
   `$this$commonReadLong`.require(8L);
   return `$this$commonReadLong`.bufferField.readLong();
}

internal inline fun RealBufferedSource.commonReadLongLe(): Long {
   `$this$commonReadLongLe`.require(8L);
   return `$this$commonReadLongLe`.bufferField.readLongLe();
}

internal inline fun RealBufferedSource.commonReadDecimalLong(): Long {
   `$this$commonReadDecimalLong`.require(1L);

   for (long pos = 0L; $this$commonReadDecimalLong.request(pos + 1L); pos++) {
      val `this_$iv`: Byte = `$this$commonReadDecimalLong`.bufferField.getByte(pos);
      if ((`this_$iv` < 48 || `this_$iv` > 57) && (pos != 0L || `this_$iv` != 45)) {
         if (pos == 0L) {
            val var10002: StringBuilder = new StringBuilder().append("Expected a digit or '-' but was 0x");
            val var10003: java.lang.String = Integer.toString(`this_$iv`, CharsKt.checkRadix(16));
            throw new NumberFormatException(var10002.append(var10003).toString());
         }
         break;
      }
   }

   return `$this$commonReadDecimalLong`.bufferField.readDecimalLong();
}

internal inline fun RealBufferedSource.commonReadHexadecimalUnsignedLong(): Long {
   `$this$commonReadHexadecimalUnsignedLong`.require(1L);

   for (int pos = 0; $this$commonReadHexadecimalUnsignedLong.request(pos + 1); pos++) {
      val `this_$iv`: Byte = `$this$commonReadHexadecimalUnsignedLong`.bufferField.getByte((long)pos);
      if ((`this_$iv` < 48 || `this_$iv` > 57) && (`this_$iv` < 97 || `this_$iv` > 102) && (`this_$iv` < 65 || `this_$iv` > 70)) {
         if (pos == 0) {
            val var10002: StringBuilder = new StringBuilder().append("Expected leading [0-9a-fA-F] character but was 0x");
            val var10003: java.lang.String = Integer.toString(`this_$iv`, CharsKt.checkRadix(16));
            throw new NumberFormatException(var10002.append(var10003).toString());
         }
         break;
      }
   }

   return `$this$commonReadHexadecimalUnsignedLong`.bufferField.readHexadecimalUnsignedLong();
}

internal inline fun RealBufferedSource.commonSkip(byteCount: Long) {
   var byteCountx: Long = byteCount;
   if (`$this$commonSkip`.closed) {
      throw new IllegalStateException("closed".toString());
   } else {
      while (byteCountx > 0L) {
         if (`$this$commonSkip`.bufferField.size() == 0L && `$this$commonSkip`.source.read(`$this$commonSkip`.bufferField, 8192L) == -1L) {
            throw new EOFException();
         }

         val toSkip: Long = Math.min(byteCountx, `$this$commonSkip`.bufferField.size());
         `$this$commonSkip`.bufferField.skip(toSkip);
         byteCountx -= toSkip;
      }
   }
}

internal inline fun RealBufferedSource.commonIndexOf(b: Byte, fromIndex: Long, toIndex: Long): Long {
   var var19: Long = fromIndex;
   if (`$this$commonIndexOf`.closed) {
      throw new IllegalStateException("closed".toString());
   } else if (0L > fromIndex || fromIndex > toIndex) {
      throw new IllegalArgumentException(("fromIndex=$fromIndex toIndex=$toIndex").toString());
   } else {
      while (true) {
         if (var19 >= toIndex) {
            return -1L;
         }

         val result: Long = `$this$commonIndexOf`.bufferField.indexOf(b, var19, toIndex);
         if (result != -1L) {
            return result;
         }

         val lastBufferSize: Long = `$this$commonIndexOf`.bufferField.size();
         if (lastBufferSize >= toIndex || `$this$commonIndexOf`.source.read(`$this$commonIndexOf`.bufferField, 8192L) == -1L) {
            break;
         }

         var19 = Math.max(var19, lastBufferSize);
      }

      return -1L;
   }
}

internal fun RealBufferedSource.commonIndexOf(
   bytes: ByteString,
   bytesOffset: Int = 0,
   byteCount: Int = bytes.size(),
   fromIndex: Long,
   toIndex: Long = java.lang.Long.MAX_VALUE
): Long {
   okio.-SegmentedByteString.checkOffsetAndCount((long)bytes.size(), (long)bytesOffset, (long)byteCount);
   var fromIndexx: Long = fromIndex;
   if (`$this$commonIndexOf`.closed) {
      throw new IllegalStateException("closed".toString());
   } else {
      while (true) {
         val result: Long = -Buffer.commonIndexOf(`$this$commonIndexOf`.bufferField, bytes, fromIndexx, toIndex, bytesOffset, byteCount);
         if (result != -1L) {
            return result;
         }

         val nextFromIndex: Long = `$this$commonIndexOf`.bufferField.size() - byteCount + 1L;
         if (nextFromIndex >= toIndex) {
            return -1L;
         }

         if (!isMatchPossibleByExpandingBuffer(`$this$commonIndexOf`.bufferField, bytes, bytesOffset, byteCount, fromIndexx, toIndex)) {
            return -1L;
         }

         if (`$this$commonIndexOf`.source.read(`$this$commonIndexOf`.bufferField, 8192L) == -1L) {
            return -1L;
         }

         fromIndexx = Math.max(fromIndexx, nextFromIndex);
      }
   }
}

@JvmSynthetic
fun `commonIndexOf$default`(var0: RealBufferedSource, var1: ByteString, var2: Int, var3: Int, var4: Long, var6: Long, var8: Int, var9: Any): Long {
   if ((var8 and 2) != 0) {
      var2 = 0;
   }

   if ((var8 and 4) != 0) {
      var3 = var1.size();
   }

   if ((var8 and 16) != 0) {
      var6 = java.lang.Long.MAX_VALUE;
   }

   return commonIndexOf(var0, var1, var2, var3, var4, var6);
}

private fun Buffer.isMatchPossibleByExpandingBuffer(bytes: ByteString, bytesOffset: Int, byteCount: Int, fromIndex: Long, toIndex: Long): Boolean {
   if (`$this$isMatchPossibleByExpandingBuffer`.size() < toIndex) {
      return true;
   } else {
      val begin: Int = (int)Math.max(1L, `$this$isMatchPossibleByExpandingBuffer`.size() - toIndex + 1L);
      var i: Int = (int)Math.min((long)byteCount, `$this$isMatchPossibleByExpandingBuffer`.size() - fromIndex + 1L) - 1;
      if (begin <= i) {
         while (true) {
            if (`$this$isMatchPossibleByExpandingBuffer`.rangeEquals(`$this$isMatchPossibleByExpandingBuffer`.size() - (long)i, bytes, bytesOffset, i)) {
               return true;
            }

            if (i == begin) {
               break;
            }

            i--;
         }
      }

      return false;
   }
}

internal inline fun RealBufferedSource.commonIndexOfElement(targetBytes: ByteString, fromIndex: Long): Long {
   var fromIndexx: Long = fromIndex;
   if (`$this$commonIndexOfElement`.closed) {
      throw new IllegalStateException("closed".toString());
   } else {
      while (true) {
         val result: Long = `$this$commonIndexOfElement`.bufferField.indexOfElement(targetBytes, fromIndexx);
         if (result != -1L) {
            return result;
         }

         val lastBufferSize: Long = `$this$commonIndexOfElement`.bufferField.size();
         if (`$this$commonIndexOfElement`.source.read(`$this$commonIndexOfElement`.bufferField, 8192L) == -1L) {
            return -1L;
         }

         fromIndexx = Math.max(fromIndexx, lastBufferSize);
      }
   }
}

internal inline fun RealBufferedSource.commonRangeEquals(offset: Long, bytes: ByteString, bytesOffset: Int, byteCount: Int): Boolean {
   if (`$this$commonRangeEquals`.closed) {
      throw new IllegalStateException("closed".toString());
   } else if (byteCount < 0) {
      return false;
   } else if (offset < 0L) {
      return false;
   } else if (bytesOffset < 0 || bytesOffset + byteCount > bytes.size()) {
      return false;
   } else if (byteCount == 0) {
      return true;
   } else {
      return commonIndexOf(`$this$commonRangeEquals`, bytes, bytesOffset, byteCount, offset, offset + 1L) != -1L;
   }
}

internal inline fun RealBufferedSource.commonPeek(): BufferedSource {
   return Okio.buffer(new PeekSource(`$this$commonPeek`));
}

internal inline fun RealBufferedSource.commonClose() {
   if (!`$this$commonClose`.closed) {
      `$this$commonClose`.closed = true;
      `$this$commonClose`.source.close();
      `$this$commonClose`.bufferField.clear();
   }
}

internal inline fun RealBufferedSource.commonTimeout(): Timeout {
   return `$this$commonTimeout`.source.timeout();
}

internal inline fun RealBufferedSource.commonToString(): String {
   return "buffer(${`$this$commonToString`.source})";
}
