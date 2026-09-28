@file:SourceDebugExtension(["SMAP\nUtf8.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utf8.kt\nkotlinx/io/Utf8Kt\n+ 2 -Util.kt\nkotlinx/io/_UtilKt\n+ 3 Sinks.kt\nkotlinx/io/SinksKt\n+ 4 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperationsKt\n*L\n1#1,624:1\n471#1,7:631\n478#1,15:640\n496#1,57:674\n471#1,7:735\n478#1,15:744\n496#1,57:778\n38#2:625\n38#2:629\n38#2:733\n95#2:838\n95#2:839\n95#2:840\n95#2:841\n95#2:842\n95#2:843\n95#2:844\n95#2:845\n95#2:846\n95#2:847\n374#3,3:626\n374#3:630\n375#3,2:731\n374#3:734\n375#3,2:835\n262#4,2:638\n266#4,19:655\n262#4,2:742\n266#4,19:759\n262#4,23:848\n262#4,23:871\n262#4,23:894\n262#4,23:917\n262#4,23:940\n262#4,23:963\n262#4,23:986\n378#4,3:1009\n381#4,3:1013\n1#5:837\n434#6:1012\n*S KotlinDebug\n*F\n+ 1 Utf8.kt\nkotlinx/io/Utf8Kt\n*L\n173#1:631,7\n173#1:640,15\n173#1:674,57\n194#1:735,7\n194#1:744,15\n194#1:778,57\n89#1:625\n171#1:629\n192#1:733\n395#1:838\n397#1:839\n402#1:840\n404#1:841\n409#1:842\n411#1:843\n416#1:844\n418#1:845\n439#1:846\n442#1:847\n153#1:626,3\n173#1:630\n173#1:731,2\n194#1:734\n194#1:835,2\n173#1:638,2\n173#1:655,19\n194#1:742,2\n194#1:759,19\n477#1:848,23\n498#1:871,23\n511#1:894,23\n538#1:917,23\n570#1:940,23\n584#1:963,23\n594#1:986,23\n610#1:1009,3\n610#1:1013,3\n613#1:1012\n*E\n"])

package kotlinx.io

import java.io.EOFException
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.internal._Utf8Kt
import kotlinx.io.unsafe.SegmentReadContext
import kotlinx.io.unsafe.SegmentWriteContext
import kotlinx.io.unsafe.UnsafeBufferOperations
import kotlinx.io.unsafe.UnsafeBufferOperationsKt

internal fun String.utf8Size(startIndex: Int = 0, endIndex: Int = `$this$utf8Size`.length()): Long {
   _UtilKt.checkBounds((long)`$this$utf8Size`.length(), (long)startIndex, (long)endIndex);
   var var10: Long = 0L;
   var i: Int = startIndex;

   while (i < endIndex) {
      val c: Int = `$this$utf8Size`.charAt(i);
      if (c < 128) {
         var10++;
         i++;
      } else if (c < 2048) {
         var10 += 2;
         i++;
      } else if (c >= 55296 && c <= 57343) {
         val low: Int = if (i + 1 < endIndex) `$this$utf8Size`.charAt(i + 1) else 0;
         if (c <= 56319 && low >= 56320 && low <= 57343) {
            var10 += 4;
            i += 2;
         } else {
            var10++;
            i++;
         }
      } else {
         var10 += 3;
         i++;
      }
   }

   return var10;
}

@JvmSynthetic
fun `utf8Size$default`(var0: java.lang.String, var1: Int, var2: Int, var3: Int, var4: Any): Long {
   if ((var3 and 1) != 0) {
      var1 = 0;
   }

   if ((var3 and 2) != 0) {
      var2 = var0.length();
   }

   return utf8Size(var0, var1, var2);
}

public fun Sink.writeCodePointValue(codePoint: Int) {
   commonWriteUtf8CodePoint(`$this$writeCodePointValue`.getBuffer(), codePoint);
   `$this$writeCodePointValue`.hintEmit();
}

public fun Sink.writeString(string: String, startIndex: Int = 0, endIndex: Int = string.length()) {
   _UtilKt.checkBounds((long)string.length(), (long)startIndex, (long)endIndex);
   val `$this$commonWriteUtf8$iv`: Buffer = `$this$writeString`.getBuffer();
   var var28: Int = startIndex;

   while (var28 < endIndex) {
      val `c$iv`: Ref.IntRef = new Ref.IntRef();
      `c$iv`.element = Character.valueOf(string.charAt(var28));
      if (`c$iv`.element < 128) {
         val var33: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val var42: Segment = `$this$commonWriteUtf8$iv`.writableSegment(1);
         val var62: SegmentWriteContext = UnsafeBufferOperationsKt.getSegmentWriteContextImpl();
         val var43: Segment = var42;
         val var46: SegmentWriteContext = var62;
         val var51: Int = -var28;
         val var56: Int = Math.min(endIndex, var28 + var42.getRemainingCapacity());
         var62.setUnchecked(var42, var51 + var28++, (byte)`c$iv`.element);

         while (var28 < runLimit$iv) {
            `c$iv`.element = Character.valueOf(string.charAt(var28));
            if (`c$iv`.element >= 128) {
               break;
            }

            var46.setUnchecked(var43, var51 + var28++, (byte)`c$iv`.element);
         }

         val var60: Int = var28 + var51;
         if (var28 + var51 == 1) {
            var42.setLimit(var42.getLimit() + var60);
            `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)var60);
         } else {
            if (0 > var60 || var60 > var42.getRemainingCapacity()) {
               throw new IllegalStateException(("Invalid number of bytes written: $var60. Should be in 0..${var42.getRemainingCapacity()}").toString());
            }

            if (var60 != 0) {
               var42.setLimit(var42.getLimit() + var60);
               `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)var60);
            } else if (SegmentKt.isEmpty(var42)) {
               `$this$commonWriteUtf8$iv`.recycleTail();
            }
         }
      } else if (`c$iv`.element < 2048) {
         val var32: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val var41: Segment = `$this$commonWriteUtf8$iv`.writableSegment(2);
         UnsafeBufferOperationsKt.getSegmentWriteContextImpl()
            .setUnchecked(var41, 0, (byte)(`c$iv`.element shr 6 or 192), (byte)(`c$iv`.element and 63 or 128));
         if (2 == 2) {
            var41.setLimit(var41.getLimit() + 2);
            `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)2);
         } else {
            if (0 > 2 || 2 > var41.getRemainingCapacity()) {
               throw new IllegalStateException(("Invalid number of bytes written: ${2}. Should be in 0..${var41.getRemainingCapacity()}").toString());
            }

            if (2 != 0) {
               var41.setLimit(var41.getLimit() + 2);
               `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)2);
            } else if (SegmentKt.isEmpty(var41)) {
               `$this$commonWriteUtf8$iv`.recycleTail();
            }
         }

         var28++;
      } else if (`c$iv`.element >= 55296 && `c$iv`.element <= 57343) {
         val var10000: Char = if (var28 + 1 < endIndex) string.charAt(var28 + 1) else 0;
         if (`c$iv`.element <= 56319 && '\udc00' <= var10000 && var10000 < '\ue000') {
            val var34: Int = 65536 + ((`c$iv`.element and 1023) shl 10 or var10000 and 1023);
            val var37: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            val var44: Segment = `$this$commonWriteUtf8$iv`.writableSegment(4);
            UnsafeBufferOperationsKt.getSegmentWriteContextImpl()
               .setUnchecked(
                  var44, 0, (byte)(var34 shr 18 or 240), (byte)(var34 shr 12 and 63 or 128), (byte)(var34 shr 6 and 63 or 128), (byte)(var34 and 63 or 128)
               );
            if (4 == 4) {
               var44.setLimit(var44.getLimit() + 4);
               `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)4);
            } else {
               if (0 > 4 || 4 > var44.getRemainingCapacity()) {
                  throw new IllegalStateException(("Invalid number of bytes written: ${4}. Should be in 0..${var44.getRemainingCapacity()}").toString());
               }

               if (4 != 0) {
                  var44.setLimit(var44.getLimit() + 4);
                  `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)4);
               } else if (SegmentKt.isEmpty(var44)) {
                  `$this$commonWriteUtf8$iv`.recycleTail();
               }
            }

            var28 += 2;
         } else {
            `$this$commonWriteUtf8$iv`.writeByte((byte)63);
            var28++;
         }
      } else {
         val `low$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val `minimumCapacity$iv$iv`: Segment = `$this$commonWriteUtf8$iv`.writableSegment(3);
         UnsafeBufferOperationsKt.getSegmentWriteContextImpl()
            .setUnchecked(
               `minimumCapacity$iv$iv`,
               0,
               (byte)(`c$iv`.element shr 12 or 224),
               (byte)(`c$iv`.element shr 6 and 63 or 128),
               (byte)(`c$iv`.element and 63 or 128)
            );
         if (3 == 3) {
            `minimumCapacity$iv$iv`.setLimit(`minimumCapacity$iv$iv`.getLimit() + 3);
            `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)3);
         } else {
            if (0 > 3 || 3 > `minimumCapacity$iv$iv`.getRemainingCapacity()) {
               throw new IllegalStateException(
                  ("Invalid number of bytes written: ${3}. Should be in 0..${`minimumCapacity$iv$iv`.getRemainingCapacity()}").toString()
               );
            }

            if (3 != 0) {
               `minimumCapacity$iv$iv`.setLimit(`minimumCapacity$iv$iv`.getLimit() + 3);
               `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)3);
            } else if (SegmentKt.isEmpty(`minimumCapacity$iv$iv`)) {
               `$this$commonWriteUtf8$iv`.recycleTail();
            }
         }

         var28++;
      }
   }

   `$this$writeString`.hintEmit();
}

@JvmSynthetic
fun `writeString$default`(var0: Sink, var1: java.lang.String, var2: Int, var3: Int, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length();
   }

   writeString(var0, var1, var2, var3);
}

public fun Sink.writeString(chars: CharSequence, startIndex: Int = 0, endIndex: Int = chars.length()) {
   _UtilKt.checkBounds((long)chars.length(), (long)startIndex, (long)endIndex);
   val `$this$commonWriteUtf8$iv`: Buffer = `$this$writeString`.getBuffer();
   var var28: Int = startIndex;

   while (var28 < endIndex) {
      val `c$iv`: Ref.IntRef = new Ref.IntRef();
      `c$iv`.element = Character.valueOf(chars.charAt(var28));
      if (`c$iv`.element < 128) {
         val var33: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val var42: Segment = `$this$commonWriteUtf8$iv`.writableSegment(1);
         val var62: SegmentWriteContext = UnsafeBufferOperationsKt.getSegmentWriteContextImpl();
         val var43: Segment = var42;
         val var46: SegmentWriteContext = var62;
         val var51: Int = -var28;
         val var56: Int = Math.min(endIndex, var28 + var42.getRemainingCapacity());
         var62.setUnchecked(var42, var51 + var28++, (byte)`c$iv`.element);

         while (var28 < runLimit$iv) {
            `c$iv`.element = Character.valueOf(chars.charAt(var28));
            if (`c$iv`.element >= 128) {
               break;
            }

            var46.setUnchecked(var43, var51 + var28++, (byte)`c$iv`.element);
         }

         val var60: Int = var28 + var51;
         if (var28 + var51 == 1) {
            var42.setLimit(var42.getLimit() + var60);
            `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)var60);
         } else {
            if (0 > var60 || var60 > var42.getRemainingCapacity()) {
               throw new IllegalStateException(("Invalid number of bytes written: $var60. Should be in 0..${var42.getRemainingCapacity()}").toString());
            }

            if (var60 != 0) {
               var42.setLimit(var42.getLimit() + var60);
               `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)var60);
            } else if (SegmentKt.isEmpty(var42)) {
               `$this$commonWriteUtf8$iv`.recycleTail();
            }
         }
      } else if (`c$iv`.element < 2048) {
         val var32: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val var41: Segment = `$this$commonWriteUtf8$iv`.writableSegment(2);
         UnsafeBufferOperationsKt.getSegmentWriteContextImpl()
            .setUnchecked(var41, 0, (byte)(`c$iv`.element shr 6 or 192), (byte)(`c$iv`.element and 63 or 128));
         if (2 == 2) {
            var41.setLimit(var41.getLimit() + 2);
            `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)2);
         } else {
            if (0 > 2 || 2 > var41.getRemainingCapacity()) {
               throw new IllegalStateException(("Invalid number of bytes written: ${2}. Should be in 0..${var41.getRemainingCapacity()}").toString());
            }

            if (2 != 0) {
               var41.setLimit(var41.getLimit() + 2);
               `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)2);
            } else if (SegmentKt.isEmpty(var41)) {
               `$this$commonWriteUtf8$iv`.recycleTail();
            }
         }

         var28++;
      } else if (`c$iv`.element >= 55296 && `c$iv`.element <= 57343) {
         val var10000: Char = if (var28 + 1 < endIndex) chars.charAt(var28 + 1) else 0;
         if (`c$iv`.element <= 56319 && '\udc00' <= var10000 && var10000 < '\ue000') {
            val var34: Int = 65536 + ((`c$iv`.element and 1023) shl 10 or var10000 and 1023);
            val var37: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            val var44: Segment = `$this$commonWriteUtf8$iv`.writableSegment(4);
            UnsafeBufferOperationsKt.getSegmentWriteContextImpl()
               .setUnchecked(
                  var44, 0, (byte)(var34 shr 18 or 240), (byte)(var34 shr 12 and 63 or 128), (byte)(var34 shr 6 and 63 or 128), (byte)(var34 and 63 or 128)
               );
            if (4 == 4) {
               var44.setLimit(var44.getLimit() + 4);
               `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)4);
            } else {
               if (0 > 4 || 4 > var44.getRemainingCapacity()) {
                  throw new IllegalStateException(("Invalid number of bytes written: ${4}. Should be in 0..${var44.getRemainingCapacity()}").toString());
               }

               if (4 != 0) {
                  var44.setLimit(var44.getLimit() + 4);
                  `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)4);
               } else if (SegmentKt.isEmpty(var44)) {
                  `$this$commonWriteUtf8$iv`.recycleTail();
               }
            }

            var28 += 2;
         } else {
            `$this$commonWriteUtf8$iv`.writeByte((byte)63);
            var28++;
         }
      } else {
         val `low$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val `minimumCapacity$iv$iv`: Segment = `$this$commonWriteUtf8$iv`.writableSegment(3);
         UnsafeBufferOperationsKt.getSegmentWriteContextImpl()
            .setUnchecked(
               `minimumCapacity$iv$iv`,
               0,
               (byte)(`c$iv`.element shr 12 or 224),
               (byte)(`c$iv`.element shr 6 and 63 or 128),
               (byte)(`c$iv`.element and 63 or 128)
            );
         if (3 == 3) {
            `minimumCapacity$iv$iv`.setLimit(`minimumCapacity$iv$iv`.getLimit() + 3);
            `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)3);
         } else {
            if (0 > 3 || 3 > `minimumCapacity$iv$iv`.getRemainingCapacity()) {
               throw new IllegalStateException(
                  ("Invalid number of bytes written: ${3}. Should be in 0..${`minimumCapacity$iv$iv`.getRemainingCapacity()}").toString()
               );
            }

            if (3 != 0) {
               `minimumCapacity$iv$iv`.setLimit(`minimumCapacity$iv$iv`.getLimit() + 3);
               `$this$commonWriteUtf8$iv`.setSizeMut(`$this$commonWriteUtf8$iv`.getSizeMut() + (long)3);
            } else if (SegmentKt.isEmpty(`minimumCapacity$iv$iv`)) {
               `$this$commonWriteUtf8$iv`.recycleTail();
            }
         }

         var28++;
      }
   }

   `$this$writeString`.hintEmit();
}

@JvmSynthetic
fun `writeString$default`(var0: Sink, var1: java.lang.CharSequence, var2: Int, var3: Int, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length();
   }

   writeString(var0, var1, var2, var3);
}

public fun Source.readString(): String {
   `$this$readString`.request(java.lang.Long.MAX_VALUE);
   return commonReadUtf8(`$this$readString`.getBuffer(), `$this$readString`.getBuffer().getSize());
}

public fun Buffer.readString(): String {
   return commonReadUtf8(`$this$readString`, `$this$readString`.getSize());
}

public fun Source.readString(byteCount: Long): String {
   `$this$readString`.require(byteCount);
   return commonReadUtf8(`$this$readString`.getBuffer(), byteCount);
}

public fun Source.readCodePointValue(): Int {
   if (`$this$readCodePointValue` is Buffer) {
      return commonReadUtf8CodePoint(`$this$readCodePointValue` as Buffer);
   } else {
      `$this$readCodePointValue`.require(1L);
      val b0: Int = `$this$readCodePointValue`.getBuffer().get(0L);
      if ((b0 and 224) == 192) {
         `$this$readCodePointValue`.require(2L);
      } else if ((b0 and 240) == 224) {
         `$this$readCodePointValue`.require(3L);
      } else if ((b0 and 248) == 240) {
         `$this$readCodePointValue`.require(4L);
      }

      return commonReadUtf8CodePoint(`$this$readCodePointValue`.getBuffer());
   }
}

public fun Source.readLine(): String? {
   if (!`$this$readLine`.request(1L)) {
      return null;
   } else {
      var lfIndex: Long = SourcesKt.indexOf$default(`$this$readLine`, (byte)10, 0L, 0L, 6, null);
      val var10000: java.lang.String;
      if (lfIndex == -1L) {
         var10000 = readString(`$this$readLine`);
      } else if (lfIndex == 0L) {
         `$this$readLine`.skip(1L);
         var10000 = "";
      } else {
         if (`$this$readLine`.getBuffer().get(lfIndex - 1L) == 13) {
            lfIndex--;
            1++;
         }

         val string: java.lang.String = readString(`$this$readLine`, lfIndex);
         `$this$readLine`.skip((long)1);
         var10000 = string;
      }

      return var10000;
   }
}

public fun Source.readLineStrict(limit: Long = java.lang.Long.MAX_VALUE): String {
   if (limit < 0L) {
      throw new IllegalArgumentException(("limit ($limit) < 0").toString());
   } else {
      `$this$readLineStrict`.require(1L);
      var lfIndex: Long = SourcesKt.indexOf(`$this$readLineStrict`, (byte)10, 0L, limit);
      if (lfIndex == 0L) {
         `$this$readLineStrict`.skip(1L);
         return "";
      } else if (lfIndex > 0L) {
         if (`$this$readLineStrict`.getBuffer().get(lfIndex - 1L) == 13) {
            lfIndex--;
            1L++;
         }

         val str: java.lang.String = readString(`$this$readLineStrict`, lfIndex);
         `$this$readLineStrict`.skip(1L);
         return str;
      } else if (`$this$readLineStrict`.getBuffer().getSize() < limit) {
         throw new EOFException();
      } else if (limit == java.lang.Long.MAX_VALUE) {
         throw new EOFException();
      } else if (!`$this$readLineStrict`.request(limit + 1L)) {
         throw new EOFException();
      } else {
         val b: Byte = `$this$readLineStrict`.getBuffer().get(limit);
         if (b == 10) {
            val var10: java.lang.String = readString(`$this$readLineStrict`, limit);
            `$this$readLineStrict`.skip(1L);
            return var10;
         } else if (b == 13 && `$this$readLineStrict`.request(limit + (long)2)) {
            if (`$this$readLineStrict`.getBuffer().get(limit + 1L) != 10) {
               throw new EOFException();
            } else {
               val res: java.lang.String = readString(`$this$readLineStrict`, limit);
               `$this$readLineStrict`.skip(2L);
               return res;
            }
         } else {
            throw new EOFException();
         }
      }
   }
}

@JvmSynthetic
fun `readLineStrict$default`(var0: Source, var1: Long, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 1) != 0) {
      var1 = java.lang.Long.MAX_VALUE;
   }

   return readLineStrict(var0, var1);
}

private fun Buffer.commonReadUtf8CodePoint(): Int {
   `$this$commonReadUtf8CodePoint`.require(1L);
   val b0: Byte = `$this$commonReadUtf8CodePoint`.get(0L);
   var var10: Int;
   val var12: Byte;
   val var13: Int;
   if ((b0 and 128) == 0) {
      var10 = b0 and 127;
      var12 = 1;
      var13 = 0;
   } else if ((b0 and 224) == 192) {
      var10 = b0 and 31;
      var12 = 2;
      var13 = 128;
   } else if ((b0 and 240) == 224) {
      var10 = b0 and 15;
      var12 = 3;
      var13 = 2048;
   } else {
      if ((b0 and 248) != 240) {
         `$this$commonReadUtf8CodePoint`.skip(1L);
         return 65533;
      }

      var10 = b0 and 7;
      var12 = 4;
      var13 = 65536;
   }

   if (`$this$commonReadUtf8CodePoint`.getSize() < var12) {
      throw new EOFException("size < $var12: ${`$this$commonReadUtf8CodePoint`.getSize()} (to read code point prefixed 0x${_UtilKt.toHexString(b0)}${41}");
   } else {
      for (int i = 1; i < var12; i++) {
         val var21: Byte = `$this$commonReadUtf8CodePoint`.get((long)i);
         if ((var21 and 192) != 128) {
            `$this$commonReadUtf8CodePoint`.skip((long)i);
            return 65533;
         }

         var10 = var10 shl 6 or var21 and 63;
      }

      `$this$commonReadUtf8CodePoint`.skip((long)var12);
      return if (var10 > 1114111) 65533 else (if (55296 <= var10 && var10 < 57344) 65533 else (if (var10 < var13) 65533 else var10));
   }
}

private inline fun Buffer.commonWriteUtf8(beginIndex: Int, endIndex: Int, charAt: (Int) -> Char) {
   var var19: Int = beginIndex;

   while (var19 < endIndex) {
      var var20: Char = charAt.invoke(var19) as Character;
      if (var20 < 128) {
         val var24: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val var33: Segment = `$this$commonWriteUtf8`.writableSegment(1);
         val var10000: SegmentWriteContext = UnsafeBufferOperationsKt.getSegmentWriteContextImpl();
         val var34: Segment = var33;
         val var37: SegmentWriteContext = var10000;
         val var42: Int = -var19;
         val var47: Int = Math.min(endIndex, var19 + var33.getRemainingCapacity());
         var10000.setUnchecked(var33, var42 + var19++, (byte)var20);

         while (var19 < runLimit) {
            var20 = charAt.invoke(var19) as Character;
            if (var20 >= 128) {
               break;
            }

            var37.setUnchecked(var34, var42 + var19++, (byte)var20);
         }

         val var51: Int = var19 + var42;
         if (var19 + var42 == 1) {
            var33.setLimit(var33.getLimit() + var51);
            `$this$commonWriteUtf8`.setSizeMut(`$this$commonWriteUtf8`.getSizeMut() + (long)var51);
         } else {
            if (0 > var51 || var51 > var33.getRemainingCapacity()) {
               throw new IllegalStateException(("Invalid number of bytes written: $var51. Should be in 0..${var33.getRemainingCapacity()}").toString());
            }

            if (var51 != 0) {
               var33.setLimit(var33.getLimit() + var51);
               `$this$commonWriteUtf8`.setSizeMut(`$this$commonWriteUtf8`.getSizeMut() + (long)var51);
            } else if (SegmentKt.isEmpty(var33)) {
               `$this$commonWriteUtf8`.recycleTail();
            }
         }
      } else if (var20 < 2048) {
         val var23: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val var32: Segment = `$this$commonWriteUtf8`.writableSegment(2);
         UnsafeBufferOperationsKt.getSegmentWriteContextImpl().setUnchecked(var32, 0, (byte)(var20 shr 6 or 192), (byte)(var20 and 63 or 128));
         if (2 == 2) {
            var32.setLimit(var32.getLimit() + 2);
            `$this$commonWriteUtf8`.setSizeMut(`$this$commonWriteUtf8`.getSizeMut() + (long)2);
         } else {
            if (0 > 2 || 2 > var32.getRemainingCapacity()) {
               throw new IllegalStateException(("Invalid number of bytes written: ${2}. Should be in 0..${var32.getRemainingCapacity()}").toString());
            }

            if (2 != 0) {
               var32.setLimit(var32.getLimit() + 2);
               `$this$commonWriteUtf8`.setSizeMut(`$this$commonWriteUtf8`.getSizeMut() + (long)2);
            } else if (SegmentKt.isEmpty(var32)) {
               `$this$commonWriteUtf8`.recycleTail();
            }
         }

         var19++;
      } else if (var20 >= '\ud800' && var20 <= '\udfff') {
         val var22: Int = if (var19 + 1 < endIndex) charAt.invoke(var19 + 1) as Character else 0;
         if (var20 <= '\udbff' && 56320 <= var22 && var22 < 57344) {
            val var25: Int = 65536 + ((var20 and 1023) shl 10 or var22 and 1023);
            val var28: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            val var35: Segment = `$this$commonWriteUtf8`.writableSegment(4);
            UnsafeBufferOperationsKt.getSegmentWriteContextImpl()
               .setUnchecked(
                  var35, 0, (byte)(var25 shr 18 or 240), (byte)(var25 shr 12 and 63 or 128), (byte)(var25 shr 6 and 63 or 128), (byte)(var25 and 63 or 128)
               );
            if (4 == 4) {
               var35.setLimit(var35.getLimit() + 4);
               `$this$commonWriteUtf8`.setSizeMut(`$this$commonWriteUtf8`.getSizeMut() + (long)4);
            } else {
               if (0 > 4 || 4 > var35.getRemainingCapacity()) {
                  throw new IllegalStateException(("Invalid number of bytes written: ${4}. Should be in 0..${var35.getRemainingCapacity()}").toString());
               }

               if (4 != 0) {
                  var35.setLimit(var35.getLimit() + 4);
                  `$this$commonWriteUtf8`.setSizeMut(`$this$commonWriteUtf8`.getSizeMut() + (long)4);
               } else if (SegmentKt.isEmpty(var35)) {
                  `$this$commonWriteUtf8`.recycleTail();
               }
            }

            var19 += 2;
         } else {
            `$this$commonWriteUtf8`.writeByte((byte)63);
            var19++;
         }
      } else {
         val low: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val `minimumCapacity$iv`: Segment = `$this$commonWriteUtf8`.writableSegment(3);
         UnsafeBufferOperationsKt.getSegmentWriteContextImpl()
            .setUnchecked(`minimumCapacity$iv`, 0, (byte)(var20 shr 12 or 224), (byte)(var20 shr 6 and 63 or 128), (byte)(var20 and 63 or 128));
         if (3 == 3) {
            `minimumCapacity$iv`.setLimit(`minimumCapacity$iv`.getLimit() + 3);
            `$this$commonWriteUtf8`.setSizeMut(`$this$commonWriteUtf8`.getSizeMut() + (long)3);
         } else {
            if (0 > 3 || 3 > `minimumCapacity$iv`.getRemainingCapacity()) {
               throw new IllegalStateException(
                  ("Invalid number of bytes written: ${3}. Should be in 0..${`minimumCapacity$iv`.getRemainingCapacity()}").toString()
               );
            }

            if (3 != 0) {
               `minimumCapacity$iv`.setLimit(`minimumCapacity$iv`.getLimit() + 3);
               `$this$commonWriteUtf8`.setSizeMut(`$this$commonWriteUtf8`.getSizeMut() + (long)3);
            } else if (SegmentKt.isEmpty(`minimumCapacity$iv`)) {
               `$this$commonWriteUtf8`.recycleTail();
            }
         }

         var19++;
      }
   }
}

private fun Buffer.commonWriteUtf8CodePoint(codePoint: Int) {
   if (codePoint >= 0 && codePoint <= 1114111) {
      if (codePoint < 128) {
         `$this$commonWriteUtf8CodePoint`.writeByte((byte)codePoint);
      } else if (codePoint < 2048) {
         val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val `tail$iv`: Segment = `$this$commonWriteUtf8CodePoint`.writableSegment(2);
         val ctx: SegmentWriteContext = UnsafeBufferOperationsKt.getSegmentWriteContextImpl();
         ctx.setUnchecked(`tail$iv`, 0, (byte)(codePoint shr 6 or 192));
         ctx.setUnchecked(`tail$iv`, 1, (byte)(codePoint and 63 or 128));
         if (2 == 2) {
            `tail$iv`.setLimit(`tail$iv`.getLimit() + 2);
            `$this$commonWriteUtf8CodePoint`.setSizeMut(`$this$commonWriteUtf8CodePoint`.getSizeMut() + (long)2);
         } else {
            if (0 > 2 || 2 > `tail$iv`.getRemainingCapacity()) {
               throw new IllegalStateException(("Invalid number of bytes written: ${2}. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString());
            }

            if (2 != 0) {
               `tail$iv`.setLimit(`tail$iv`.getLimit() + 2);
               `$this$commonWriteUtf8CodePoint`.setSizeMut(`$this$commonWriteUtf8CodePoint`.getSizeMut() + (long)2);
            } else if (SegmentKt.isEmpty(`tail$iv`)) {
               `$this$commonWriteUtf8CodePoint`.recycleTail();
            }
         }
      } else if (55296 <= codePoint && codePoint < 57344) {
         `$this$commonWriteUtf8CodePoint`.writeByte((byte)63);
      } else if (codePoint < 65536) {
         val var11: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val var17: Segment = `$this$commonWriteUtf8CodePoint`.writableSegment(3);
         val var19: SegmentWriteContext = UnsafeBufferOperationsKt.getSegmentWriteContextImpl();
         var19.setUnchecked(var17, 0, (byte)(codePoint shr 12 or 224));
         var19.setUnchecked(var17, 1, (byte)(codePoint shr 6 and 63 or 128));
         var19.setUnchecked(var17, 2, (byte)(codePoint and 63 or 128));
         if (3 == 3) {
            var17.setLimit(var17.getLimit() + 3);
            `$this$commonWriteUtf8CodePoint`.setSizeMut(`$this$commonWriteUtf8CodePoint`.getSizeMut() + (long)3);
         } else {
            if (0 > 3 || 3 > var17.getRemainingCapacity()) {
               throw new IllegalStateException(("Invalid number of bytes written: ${3}. Should be in 0..${var17.getRemainingCapacity()}").toString());
            }

            if (3 != 0) {
               var17.setLimit(var17.getLimit() + 3);
               `$this$commonWriteUtf8CodePoint`.setSizeMut(`$this$commonWriteUtf8CodePoint`.getSizeMut() + (long)3);
            } else if (SegmentKt.isEmpty(var17)) {
               `$this$commonWriteUtf8CodePoint`.recycleTail();
            }
         }
      } else {
         val var12: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val var18: Segment = `$this$commonWriteUtf8CodePoint`.writableSegment(4);
         val var20: SegmentWriteContext = UnsafeBufferOperationsKt.getSegmentWriteContextImpl();
         var20.setUnchecked(var18, 0, (byte)(codePoint shr 18 or 240));
         var20.setUnchecked(var18, 1, (byte)(codePoint shr 12 and 63 or 128));
         var20.setUnchecked(var18, 2, (byte)(codePoint shr 6 and 63 or 128));
         var20.setUnchecked(var18, 3, (byte)(codePoint and 63 or 128));
         if (4 == 4) {
            var18.setLimit(var18.getLimit() + 4);
            `$this$commonWriteUtf8CodePoint`.setSizeMut(`$this$commonWriteUtf8CodePoint`.getSizeMut() + (long)4);
         } else {
            if (0 > 4 || 4 > var18.getRemainingCapacity()) {
               throw new IllegalStateException(("Invalid number of bytes written: ${4}. Should be in 0..${var18.getRemainingCapacity()}").toString());
            }

            if (4 != 0) {
               var18.setLimit(var18.getLimit() + 4);
               `$this$commonWriteUtf8CodePoint`.setSizeMut(`$this$commonWriteUtf8CodePoint`.getSizeMut() + (long)4);
            } else if (SegmentKt.isEmpty(var18)) {
               `$this$commonWriteUtf8CodePoint`.recycleTail();
            }
         }
      }
   } else {
      throw new IllegalArgumentException("Code point value is out of Unicode codespace 0..0x10ffff: 0x${_UtilKt.toHexString(codePoint)} ($codePoint)");
   }
}

private fun Buffer.commonReadUtf8(byteCount: Long): String {
   if (byteCount == 0L) {
      return "";
   } else {
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      val `curr$iv`: Segment = `$this$commonReadUtf8`.getHead();
      if (`curr$iv` != null) {
         val ctx: SegmentReadContext = UnsafeBufferOperationsKt.getSegmentReadContextImpl();
         if (`curr$iv`.getSize() >= byteCount) {
            val var10000: ByteArray = `curr$iv`.dataAsByteArray(true);
            val pos: Int = `curr$iv`.getPos();
            val var17: java.lang.String = _Utf8Kt.commonToUtf8String(var10000, pos, Math.min(`curr$iv`.getLimit(), pos + (int)byteCount));
            `$this$commonReadUtf8`.skip(byteCount);
            return var17;
         } else {
            return _Utf8Kt.commonToUtf8String$default(SourcesKt.readByteArray(`$this$commonReadUtf8`, (int)byteCount), 0, 0, 3, null);
         }
      } else {
         throw new IllegalStateException("Unreacheable".toString());
      }
   }
}
