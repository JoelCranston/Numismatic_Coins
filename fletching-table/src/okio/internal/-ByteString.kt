@file:JvmName(name = "-ByteString")

@file:SourceDebugExtension(["SMAP\nByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteString.kt\nokio/internal/-ByteString\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Utf8.kt\nokio/Utf8\n*L\n1#1,342:1\n129#1,2:348\n131#1,9:351\n67#2:343\n73#2:344\n73#2:346\n73#2:347\n67#2:375\n73#2:387\n1#3:345\n1#3:350\n212#4,7:360\n122#4:367\n219#4,5:368\n122#4:373\n226#4:374\n228#4:376\n397#4,2:377\n122#4:379\n400#4,6:380\n127#4:386\n406#4:388\n122#4:389\n407#4,13:390\n122#4:403\n422#4:404\n122#4:405\n425#4:406\n230#4,3:407\n440#4,3:410\n122#4:413\n443#4:414\n127#4:415\n446#4,10:416\n127#4:426\n456#4:427\n122#4:428\n457#4,4:429\n127#4:433\n461#4:434\n122#4:435\n462#4,14:436\n122#4:450\n477#4,2:451\n122#4:453\n481#4:454\n122#4:455\n484#4:456\n234#4,3:457\n500#4,3:460\n122#4:463\n503#4:464\n127#4:465\n506#4,2:466\n127#4:468\n510#4,10:469\n127#4:479\n520#4:480\n122#4:481\n521#4,4:482\n127#4:486\n525#4:487\n122#4:488\n526#4,4:489\n127#4:493\n530#4:494\n122#4:495\n531#4,15:496\n122#4:511\n547#4,2:512\n122#4:514\n550#4,2:515\n122#4:517\n554#4:518\n122#4:519\n557#4:520\n241#4:521\n122#4:522\n242#4,5:523\n*S KotlinDebug\n*F\n+ 1 ByteString.kt\nokio/internal/-ByteString\n*L\n308#1:348,2\n308#1:351,9\n65#1:343\n66#1:344\n256#1:346\n257#1:347\n327#1:375\n327#1:387\n308#1:350\n327#1:360,7\n332#1:367\n327#1:368,5\n332#1:373\n327#1:374\n327#1:376\n327#1:377,2\n332#1:379\n327#1:380,6\n327#1:386\n327#1:388\n332#1:389\n327#1:390,13\n332#1:403\n327#1:404\n332#1:405\n327#1:406\n327#1:407,3\n327#1:410,3\n332#1:413\n327#1:414\n327#1:415\n327#1:416,10\n327#1:426\n327#1:427\n332#1:428\n327#1:429,4\n327#1:433\n327#1:434\n332#1:435\n327#1:436,14\n332#1:450\n327#1:451,2\n332#1:453\n327#1:454\n332#1:455\n327#1:456\n327#1:457,3\n327#1:460,3\n332#1:463\n327#1:464\n327#1:465\n327#1:466,2\n327#1:468\n327#1:469,10\n327#1:479\n327#1:480\n332#1:481\n327#1:482,4\n327#1:486\n327#1:487\n332#1:488\n327#1:489,4\n327#1:493\n327#1:494\n332#1:495\n327#1:496,15\n332#1:511\n327#1:512,2\n332#1:514\n327#1:515,2\n332#1:517\n327#1:518\n332#1:519\n327#1:520\n327#1:521\n332#1:522\n327#1:523,5\n*E\n"])

package okio.internal

import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension
import okio.-Base64
import okio.Buffer
import okio.ByteString
import okio._JvmPlatformKt

internal final val HEX_DIGIT_CHARS: CharArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'}

internal inline fun ByteString.commonUtf8(): String {
   var result: java.lang.String = `$this$commonUtf8`.getUtf8$okio();
   if (result == null) {
      result = _JvmPlatformKt.toUtf8String(`$this$commonUtf8`.internalArray$okio());
      `$this$commonUtf8`.setUtf8$okio(result);
   }

   return result;
}

internal inline fun ByteString.commonBase64(): String {
   return -Base64.encodeBase64$default(`$this$commonBase64`.getData$okio(), null, 1, null);
}

internal inline fun ByteString.commonBase64Url(): String {
   return -Base64.encodeBase64(`$this$commonBase64Url`.getData$okio(), -Base64.getBASE64_URL_SAFE());
}

internal inline fun ByteString.commonHex(): String {
   val result: CharArray = new char[`$this$commonHex`.getData$okio().length * 2];
   var c: Int = 0;

   for (byte b : $this$commonHex.getData$okio()) {
      result[c++] = getHEX_DIGIT_CHARS()[b shr 4 and 15];
      result[c++] = getHEX_DIGIT_CHARS()[b and 15];
   }

   return StringsKt.concatToString(result);
}

internal inline fun ByteString.commonToAsciiLowercase(): ByteString {
   for (int i = 0; i < $this$commonToAsciiLowercase.getData$okio().length; i++) {
      var c: Byte = `$this$commonToAsciiLowercase`.getData$okio()[i];
      if (c >= 65 && c <= 90) {
         var var10000: ByteArray = `$this$commonToAsciiLowercase`.getData$okio();
         var10000 = Arrays.copyOf(var10000, var10000.length);
         val lowercase: ByteArray = var10000;
         var10000[i++] = (byte)(c - -32);

         while (i < lowercase.length) {
            c = lowercase[i];
            if (lowercase[i] >= 65 && lowercase[i] <= 90) {
               lowercase[i] = (byte)(c - -32);
               i++;
            } else {
               i++;
            }
         }

         return new ByteString(lowercase);
      }
   }

   return `$this$commonToAsciiLowercase`;
}

internal inline fun ByteString.commonToAsciiUppercase(): ByteString {
   for (int i = 0; i < $this$commonToAsciiUppercase.getData$okio().length; i++) {
      var c: Byte = `$this$commonToAsciiUppercase`.getData$okio()[i];
      if (c >= 97 && c <= 122) {
         var var10000: ByteArray = `$this$commonToAsciiUppercase`.getData$okio();
         var10000 = Arrays.copyOf(var10000, var10000.length);
         val lowercase: ByteArray = var10000;
         var10000[i++] = (byte)(c - 32);

         while (i < lowercase.length) {
            c = lowercase[i];
            if (lowercase[i] >= 97 && lowercase[i] <= 122) {
               lowercase[i] = (byte)(c - 32);
               i++;
            } else {
               i++;
            }
         }

         return new ByteString(lowercase);
      }
   }

   return `$this$commonToAsciiUppercase`;
}

internal inline fun ByteString.commonSubstring(beginIndex: Int, endIndex: Int): ByteString {
   val endIndexx: Int = okio.-SegmentedByteString.resolveDefaultParameter(`$this$commonSubstring`, endIndex);
   if (beginIndex < 0) {
      throw new IllegalArgumentException("beginIndex < 0".toString());
   } else if (endIndexx > `$this$commonSubstring`.getData$okio().length) {
      throw new IllegalArgumentException(("endIndex > length(${`$this$commonSubstring`.getData$okio().length})").toString());
   } else if (endIndexx - beginIndex < 0) {
      throw new IllegalArgumentException("endIndex < beginIndex".toString());
   } else {
      return if (beginIndex == 0 && endIndexx == `$this$commonSubstring`.getData$okio().length)
         `$this$commonSubstring`
         else
         new ByteString(ArraysKt.copyOfRange(`$this$commonSubstring`.getData$okio(), beginIndex, endIndexx));
   }
}

internal inline fun ByteString.commonGetByte(pos: Int): Byte {
   return `$this$commonGetByte`.getData$okio()[pos];
}

internal inline fun ByteString.commonGetSize(): Int {
   return `$this$commonGetSize`.getData$okio().length;
}

internal inline fun ByteString.commonToByteArray(): ByteArray {
   var var10000: ByteArray = `$this$commonToByteArray`.getData$okio();
   var10000 = Arrays.copyOf(var10000, var10000.length);
   return var10000;
}

internal inline fun ByteString.commonInternalArray(): ByteArray {
   return `$this$commonInternalArray`.getData$okio();
}

internal inline fun ByteString.commonRangeEquals(offset: Int, other: ByteString, otherOffset: Int, byteCount: Int): Boolean {
   return other.rangeEquals(otherOffset, `$this$commonRangeEquals`.getData$okio(), offset, byteCount);
}

internal inline fun ByteString.commonRangeEquals(offset: Int, other: ByteArray, otherOffset: Int, byteCount: Int): Boolean {
   return offset >= 0
      && offset <= `$this$commonRangeEquals`.getData$okio().length - byteCount
      && otherOffset >= 0
      && otherOffset <= other.length - byteCount
      && okio.-SegmentedByteString.arrayRangeEquals(`$this$commonRangeEquals`.getData$okio(), offset, other, otherOffset, byteCount);
}

internal inline fun ByteString.commonCopyInto(offset: Int, target: ByteArray, targetOffset: Int, byteCount: Int) {
   ArraysKt.copyInto(`$this$commonCopyInto`.getData$okio(), target, targetOffset, offset, offset + byteCount);
}

internal inline fun ByteString.commonStartsWith(prefix: ByteString): Boolean {
   return `$this$commonStartsWith`.rangeEquals(0, prefix, 0, prefix.size());
}

internal inline fun ByteString.commonStartsWith(prefix: ByteArray): Boolean {
   return `$this$commonStartsWith`.rangeEquals(0, prefix, 0, prefix.length);
}

internal inline fun ByteString.commonEndsWith(suffix: ByteString): Boolean {
   return `$this$commonEndsWith`.rangeEquals(`$this$commonEndsWith`.size() - suffix.size(), suffix, 0, suffix.size());
}

internal inline fun ByteString.commonEndsWith(suffix: ByteArray): Boolean {
   return `$this$commonEndsWith`.rangeEquals(`$this$commonEndsWith`.size() - suffix.length, suffix, 0, suffix.length);
}

internal inline fun ByteString.commonIndexOf(other: ByteArray, fromIndex: Int): Int {
   val limit: Int = `$this$commonIndexOf`.getData$okio().length - other.length;
   var i: Int = Math.max(fromIndex, 0);
   if (i <= limit) {
      while (true) {
         if (okio.-SegmentedByteString.arrayRangeEquals(`$this$commonIndexOf`.getData$okio(), i, other, 0, other.length)) {
            return i;
         }

         if (i == limit) {
            break;
         }

         i++;
      }
   }

   return -1;
}

internal inline fun ByteString.commonLastIndexOf(other: ByteString, fromIndex: Int): Int {
   return `$this$commonLastIndexOf`.lastIndexOf(other.internalArray$okio(), fromIndex);
}

internal inline fun ByteString.commonLastIndexOf(other: ByteArray, fromIndex: Int): Int {
   for (int i = Math.min(
         okio.-SegmentedByteString.resolveDefaultParameter($this$commonLastIndexOf, fromIndex), $this$commonLastIndexOf.getData$okio().length - other.length
      );
      -1 < i;
      i--
   ) {
      if (okio.-SegmentedByteString.arrayRangeEquals(`$this$commonLastIndexOf`.getData$okio(), i, other, 0, other.length)) {
         return i;
      }
   }

   return -1;
}

internal inline fun ByteString.commonEquals(other: Any?): Boolean {
   return other === `$this$commonEquals`
      || other is ByteString
         && (other as ByteString).size() == `$this$commonEquals`.getData$okio().length
         && (other as ByteString).rangeEquals(0, `$this$commonEquals`.getData$okio(), 0, `$this$commonEquals`.getData$okio().length);
}

internal inline fun ByteString.commonHashCode(): Int {
   val result: Int = `$this$commonHashCode`.getHashCode$okio();
   if (result != 0) {
      return result;
   } else {
      val var3: Int = Arrays.hashCode(`$this$commonHashCode`.getData$okio());
      `$this$commonHashCode`.setHashCode$okio(var3);
      return var3;
   }
}

internal inline fun ByteString.commonCompareTo(other: ByteString): Int {
   val sizeA: Int = `$this$commonCompareTo`.size();
   val sizeB: Int = other.size();
   var i: Int = 0;

   for (int size = Math.min(sizeA, sizeB); i < size; i++) {
      val byteA: Int = `$this$commonCompareTo`.getByte(i) and 255;
      val var13: Byte = other.getByte(i);
      val var12: Int = var13 and 255;
      if (byteA != (var13 and 255)) {
         return if (byteA < var12) -1 else 1;
      }
   }

   if (sizeA == sizeB) {
      return 0;
   } else {
      return if (sizeA < sizeB) -1 else 1;
   }
}

internal inline fun commonOf(data: ByteArray): ByteString {
   val var10002: ByteArray = Arrays.copyOf(data, data.length);
   return new ByteString(var10002);
}

internal inline fun ByteArray.commonToByteString(offset: Int, byteCount: Int): ByteString {
   val byteCountx: Int = okio.-SegmentedByteString.resolveDefaultParameter(`$this$commonToByteString`, byteCount);
   okio.-SegmentedByteString.checkOffsetAndCount((long)`$this$commonToByteString`.length, (long)offset, (long)byteCountx);
   return new ByteString(ArraysKt.copyOfRange(`$this$commonToByteString`, offset, offset + byteCountx));
}

internal inline fun String.commonEncodeUtf8(): ByteString {
   val byteString: ByteString = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(`$this$commonEncodeUtf8`));
   byteString.setUtf8$okio(`$this$commonEncodeUtf8`);
   return byteString;
}

internal inline fun String.commonDecodeBase64(): ByteString? {
   val decoded: ByteArray = -Base64.decodeBase64ToArray(`$this$commonDecodeBase64`);
   return if (decoded != null) new ByteString(decoded) else null;
}

internal fun ByteString.commonWrite(buffer: Buffer, offset: Int, byteCount: Int) {
   buffer.write(`$this$commonWrite`.getData$okio(), offset, byteCount);
}

internal inline fun ByteString.commonToString(): String {
   if (`$this$commonToString`.getData$okio().length == 0) {
      return "[size=0]";
   } else {
      val i: Int = access$codePointIndexToCharIndex(`$this$commonToString`.getData$okio(), 64);
      if (i == -1) {
         val var14: java.lang.String;
         if (`$this$commonToString`.getData$okio().length <= 64) {
            var14 = "[hex=${`$this$commonToString`.hex()}]";
         } else {
            val var15: StringBuilder = new StringBuilder().append("[size=").append(`$this$commonToString`.getData$okio().length).append(" hex=");
            val `endIndex$iv`: Int = okio.-SegmentedByteString.resolveDefaultParameter(`$this$commonToString`, 64);
            if (`endIndex$iv` > `$this$commonToString`.getData$okio().length) {
               throw new IllegalArgumentException(("endIndex > length(${`$this$commonToString`.getData$okio().length})").toString());
            }

            if (`endIndex$iv` - 0 < 0) {
               throw new IllegalArgumentException("endIndex < beginIndex".toString());
            }

            var14 = var15.append(
                  (if (`endIndex$iv` == `$this$commonToString`.getData$okio().length)
                        `$this$commonToString`
                        else
                        new ByteString(ArraysKt.copyOfRange(`$this$commonToString`.getData$okio(), 0, `endIndex$iv`)))
                     .hex()
               )
               .append("…]")
               .toString();
         }

         return var14;
      } else {
         val text: java.lang.String = `$this$commonToString`.utf8();
         val var10000: java.lang.String = text.substring(0, i);
         val safeText: java.lang.String = StringsKt.replace$default(
            StringsKt.replace$default(StringsKt.replace$default(var10000, "\\", "\\\\", false, 4, null), "\n", "\\n", false, 4, null),
            "\r",
            "\\r",
            false,
            4,
            null
         );
         return if (i < text.length()) "[size=${`$this$commonToString`.getData$okio().length} text=$safeText…]" else "[text=$safeText]";
      }
   }
}

private fun codePointIndexToCharIndex(s: ByteArray, codePointCount: Int): Int {
   var charCount: Int = 0;
   val j: Int = 0;
   val `$this$processUtf8CodePoints$iv`: ByteArray = s;
   val `endIndex$iv`: Int = s.length;
   var `index$iv`: Int = 0;

   while (index$iv < endIndex$iv) {
      val `b0$iv`: Byte = `$this$processUtf8CodePoints$iv`[`index$iv`];
      if (`$this$processUtf8CodePoints$iv`[`index$iv`] >= 0) {
         if (j++ == codePointCount) {
            return charCount;
         }

         if (`b0$iv` != 10 && `b0$iv` != 13 && (0 <= `b0$iv` && `b0$iv` < 32 || 127 <= `b0$iv` && `b0$iv` < 160)) {
            return -1;
         }

         if (`b0$iv` == '�') {
            return -1;
         }

         charCount += if (`b0$iv` < 65536) 1 else 2;
         `index$iv`++;

         while (index$iv < endIndex$iv && $this$processUtf8CodePoints$iv[index$iv] >= 0) {
            val c: Int = `$this$processUtf8CodePoints$iv`[`index$iv`++];
            if (j++ == codePointCount) {
               return charCount;
            }

            if (c != 10 && c != 13 && (0 <= c && c < 32 || 127 <= c && c < 160)) {
               return -1;
            }

            if (c == 65533) {
               return -1;
            }

            charCount += if (c < 65536) 1 else 2;
         }
      } else if (`b0$iv` shr 5 == -2) {
         val var182: Byte;
         val var10000: Int;
         if (`endIndex$iv` <= `index$iv` + 1) {
            if (j++ == codePointCount) {
               return charCount;
            }

            if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
               return -1;
            }

            if ('�' == '�') {
               return -1;
            }

            charCount += if ('�' < 65536) 1 else 2;
            var10000 = `index$iv`;
            var182 = 1;
         } else {
            val `b0$iv$iv`: Byte = `$this$processUtf8CodePoints$iv`[`index$iv`];
            val `b1$iv$iv`: Byte = `$this$processUtf8CodePoints$iv`[`index$iv` + 1];
            if ((`$this$processUtf8CodePoints$iv`[`index$iv` + 1] and 192) != 128) {
               if (j++ == codePointCount) {
                  return charCount;
               }

               if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                  return -1;
               }

               if ('�' == '�') {
                  return -1;
               }

               charCount += if ('�' < 65536) 1 else 2;
               var10000 = `index$iv`;
               var182 = 1;
            } else {
               val `b2$iv$iv`: Int = 3968 xor `b1$iv$iv` xor `b0$iv$iv` shl 6;
               if ((3968 xor `b1$iv$iv` xor `b0$iv$iv` shl 6) < 128) {
                  if (j++ == codePointCount) {
                     return charCount;
                  }

                  if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                     return -1;
                  }

                  if ('�' == '�') {
                     return -1;
                  }

                  charCount += if ('�' < 65536) 1 else 2;
                  var10000 = `index$iv`;
               } else {
                  if (j++ == codePointCount) {
                     return charCount;
                  }

                  if (`b2$iv$iv` != 10 && `b2$iv$iv` != 13 && (0 <= `b2$iv$iv` && `b2$iv$iv` < 32 || 127 <= `b2$iv$iv` && `b2$iv$iv` < 160)) {
                     return -1;
                  }

                  if (`b2$iv$iv` == 65533) {
                     return -1;
                  }

                  charCount += if (`b2$iv$iv` < 65536) 1 else 2;
                  var10000 = `index$iv`;
               }

               var182 = 2;
            }
         }

         `index$iv` = var10000 + var182;
      } else if (`b0$iv` shr 4 == -2) {
         var var179: Int;
         var var183: Byte;
         label988:
         if (`endIndex$iv` <= `index$iv` + 2) {
            if (j++ == codePointCount) {
               return charCount;
            }

            if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
               return -1;
            }

            if ('�' == '�') {
               return -1;
            }

            charCount += if ('�' < 65536) 1 else 2;
            var179 = `index$iv`;
            var183 = (byte)(if (`endIndex$iv` > `index$iv` + 1 && (`$this$processUtf8CodePoints$iv`[`index$iv` + 1] and 192) == 128) 2 else 1);
            break label988;
         } else {
            val var146: Byte = `$this$processUtf8CodePoints$iv`[`index$iv`];
            val var151: Byte = `$this$processUtf8CodePoints$iv`[`index$iv` + 1];
            if ((`$this$processUtf8CodePoints$iv`[`index$iv` + 1] and 192) != 128) {
               if (j++ == codePointCount) {
                  return charCount;
               }

               if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                  return -1;
               }

               if ('�' == '�') {
                  return -1;
               }

               charCount += if ('�' < 65536) 1 else 2;
               var179 = `index$iv`;
               var183 = 1;
            } else {
               val var156: Byte = `$this$processUtf8CodePoints$iv`[`index$iv` + 2];
               if ((`$this$processUtf8CodePoints$iv`[`index$iv` + 2] and 192) != 128) {
                  if (j++ == codePointCount) {
                     return charCount;
                  }

                  if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                     return -1;
                  }

                  if ('�' == '�') {
                     return -1;
                  }

                  charCount += if ('�' < 65536) 1 else 2;
                  var179 = `index$iv`;
                  var183 = 2;
               } else {
                  val var159: Int = -123008 xor var156 xor var151 shl 6 xor var146 shl 12;
                  if ((-123008 xor var156 xor var151 shl 6 xor var146 shl 12) < 2048) {
                     if (j++ == codePointCount) {
                        return charCount;
                     }

                     if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                        return -1;
                     }

                     if ('�' == '�') {
                        return -1;
                     }

                     charCount += if ('�' < 65536) 1 else 2;
                     var179 = `index$iv`;
                  } else if (55296 <= var159 && var159 < 57344) {
                     if (j++ == codePointCount) {
                        return charCount;
                     }

                     if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                        return -1;
                     }

                     if ('�' == '�') {
                        return -1;
                     }

                     charCount += if ('�' < 65536) 1 else 2;
                     var179 = `index$iv`;
                  } else {
                     if (j++ == codePointCount) {
                        return charCount;
                     }

                     if (var159 != 10 && var159 != 13 && (0 <= var159 && var159 < 32 || 127 <= var159 && var159 < 160)) {
                        return -1;
                     }

                     if (var159 == 65533) {
                        return -1;
                     }

                     charCount += if (var159 < 65536) 1 else 2;
                     var179 = `index$iv`;
                  }

                  var183 = 3;
               }
            }
         }

         `index$iv` = var179 + var183;
      } else if (`b0$iv` shr 3 == -2) {
         var var180: Int;
         var var184: Byte;
         label1140:
         if (`endIndex$iv` <= `index$iv` + 3) {
            if (j++ == codePointCount) {
               return charCount;
            }

            if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
               return -1;
            }

            if ('�' == '�') {
               return -1;
            }

            charCount += if ('�' < 65536) 1 else 2;
            var180 = `index$iv`;
            var184 = (byte)(if (`endIndex$iv` <= `index$iv` + 1 || (`$this$processUtf8CodePoints$iv`[`index$iv` + 1] and 192) != 128)
               1
               else
               (if (`endIndex$iv` > `index$iv` + 2 && (`$this$processUtf8CodePoints$iv`[`index$iv` + 2] and 192) == 128) 3 else 2));
            break label1140;
         } else {
            val var148: Byte = `$this$processUtf8CodePoints$iv`[`index$iv`];
            val var153: Byte = `$this$processUtf8CodePoints$iv`[`index$iv` + 1];
            if ((`$this$processUtf8CodePoints$iv`[`index$iv` + 1] and 192) != 128) {
               if (j++ == codePointCount) {
                  return charCount;
               }

               if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                  return -1;
               }

               if ('�' == '�') {
                  return -1;
               }

               charCount += if ('�' < 65536) 1 else 2;
               var180 = `index$iv`;
               var184 = 1;
            } else {
               val var157: Byte = `$this$processUtf8CodePoints$iv`[`index$iv` + 2];
               if ((`$this$processUtf8CodePoints$iv`[`index$iv` + 2] and 192) != 128) {
                  if (j++ == codePointCount) {
                     return charCount;
                  }

                  if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                     return -1;
                  }

                  if ('�' == '�') {
                     return -1;
                  }

                  charCount += if ('�' < 65536) 1 else 2;
                  var180 = `index$iv`;
                  var184 = 2;
               } else {
                  val var162: Byte = `$this$processUtf8CodePoints$iv`[`index$iv` + 3];
                  if ((`$this$processUtf8CodePoints$iv`[`index$iv` + 3] and 192) != 128) {
                     if (j++ == codePointCount) {
                        return charCount;
                     }

                     if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                        return -1;
                     }

                     if ('�' == '�') {
                        return -1;
                     }

                     charCount += if ('�' < 65536) 1 else 2;
                     var180 = `index$iv`;
                     var184 = 3;
                  } else {
                     val var167: Int = 3678080 xor var162 xor var157 shl 6 xor var153 shl 12 xor var148 shl 18;
                     if ((3678080 xor var162 xor var157 shl 6 xor var153 shl 12 xor var148 shl 18) > 1114111) {
                        if (j++ == codePointCount) {
                           return charCount;
                        }

                        if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                           return -1;
                        }

                        if ('�' == '�') {
                           return -1;
                        }

                        charCount += if ('�' < 65536) 1 else 2;
                        var180 = `index$iv`;
                     } else if (55296 <= var167 && var167 < 57344) {
                        if (j++ == codePointCount) {
                           return charCount;
                        }

                        if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                           return -1;
                        }

                        if ('�' == '�') {
                           return -1;
                        }

                        charCount += if ('�' < 65536) 1 else 2;
                        var180 = `index$iv`;
                     } else if (var167 < 65536) {
                        if (j++ == codePointCount) {
                           return charCount;
                        }

                        if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
                           return -1;
                        }

                        if ('�' == '�') {
                           return -1;
                        }

                        charCount += if ('�' < 65536) 1 else 2;
                        var180 = `index$iv`;
                     } else {
                        if (j++ == codePointCount) {
                           return charCount;
                        }

                        if (var167 != 10 && var167 != 13 && (0 <= var167 && var167 < 32 || 127 <= var167 && var167 < 160)) {
                           return -1;
                        }

                        if (var167 == 65533) {
                           return -1;
                        }

                        charCount += if (var167 < 65536) 1 else 2;
                        var180 = `index$iv`;
                     }

                     var184 = 4;
                  }
               }
            }
         }

         `index$iv` = var180 + var184;
      } else {
         if (j++ == codePointCount) {
            return charCount;
         }

         if ('�' != '\n' && '�' != '\r' && (0 <= '�' && '�' < ' ' || 127 <= '�' && '�' < 160)) {
            return -1;
         }

         if ('�' == '�') {
            return -1;
         }

         charCount += if ('�' < 65536) 1 else 2;
         `index$iv`++;
      }
   }

   return charCount;
}

@JvmSynthetic
fun `access$codePointIndexToCharIndex`(s: ByteArray, codePointCount: Int): Int {
   return codePointIndexToCharIndex(s, codePointCount);
}
