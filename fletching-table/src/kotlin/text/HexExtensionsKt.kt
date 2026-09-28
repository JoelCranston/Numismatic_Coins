@file:SourceDebugExtension(["SMAP\nHexExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HexExtensions.kt\nkotlin/text/HexExtensionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,1237:1\n1186#1,7:1239\n1186#1,7:1246\n1186#1,7:1253\n1186#1,7:1260\n1186#1,7:1267\n1186#1,7:1274\n1186#1,7:1281\n1186#1,7:1288\n1197#1,5:1295\n1197#1,5:1300\n1186#1,7:1305\n1186#1,7:1312\n1197#1,5:1319\n1206#1,5:1324\n1#2:1238\n1188#3,3:1329\n1188#3,3:1332\n1188#3,3:1335\n1188#3,3:1338\n*S KotlinDebug\n*F\n+ 1 HexExtensions.kt\nkotlin/text/HexExtensionsKt\n*L\n450#1:1239,7\n482#1:1246,7\n486#1:1253,7\n489#1:1260,7\n529#1:1267,7\n532#1:1274,7\n537#1:1281,7\n542#1:1288,7\n549#1:1295,5\n550#1:1300,5\n1141#1:1305,7\n1143#1:1312,7\n1171#1:1319,5\n1179#1:1324,5\n42#1:1329,3\n43#1:1332,3\n54#1:1335,3\n55#1:1338,3\n*E\n"])

package kotlin.text

import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.text.HexFormat.BytesHexFormat
import kotlin.text.HexFormat.NumberHexFormat

private const val LOWER_CASE_HEX_DIGITS: String = "0123456789abcdef"
private const val UPPER_CASE_HEX_DIGITS: String = "0123456789ABCDEF"
internal final val BYTE_TO_LOWER_CASE_HEX_DIGITS: IntArray
private final val BYTE_TO_UPPER_CASE_HEX_DIGITS: IntArray
private final val HEX_DIGITS_TO_DECIMAL: IntArray
private final val HEX_DIGITS_TO_LONG_DECIMAL: LongArray

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun ByteArray.toHexString(format: HexFormat = HexFormat.Companion.getDefault()): String {
   return toHexString(`$this$toHexString`, 0, `$this$toHexString`.length, format);
}

@JvmSynthetic
fun `toHexString$default`(var0: ByteArray, var1: HexFormat, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return toHexString(var0, var1);
}

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun ByteArray.toHexString(startIndex: Int = 0, endIndex: Int = `$this$toHexString`.length, format: HexFormat = HexFormat.Companion.getDefault()): String {
   AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, `$this$toHexString`.length);
   if (startIndex == endIndex) {
      return "";
   } else {
      val byteToDigits: IntArray = if (format.getUpperCase()) BYTE_TO_UPPER_CASE_HEX_DIGITS else BYTE_TO_LOWER_CASE_HEX_DIGITS;
      val bytesFormat: HexFormat.BytesHexFormat = format.getBytes();
      return if (bytesFormat.getNoLineAndGroupSeparator$kotlin_stdlib())
         toHexStringNoLineAndGroupSeparator(`$this$toHexString`, startIndex, endIndex, bytesFormat, byteToDigits)
         else
         toHexStringSlowPath(`$this$toHexString`, startIndex, endIndex, bytesFormat, byteToDigits);
   }
}

@JvmSynthetic
fun `toHexString$default`(var0: ByteArray, var1: Int, var2: Int, var3: HexFormat, var4: Int, var5: Any): java.lang.String {
   if ((var4 and 1) != 0) {
      var1 = 0;
   }

   if ((var4 and 2) != 0) {
      var2 = var0.length;
   }

   if ((var4 and 4) != 0) {
      var3 = HexFormat.Companion.getDefault();
   }

   return toHexString(var0, var1, var2, var3);
}

private fun ByteArray.toHexStringNoLineAndGroupSeparator(startIndex: Int, endIndex: Int, bytesFormat: BytesHexFormat, byteToDigits: IntArray): String {
   return if (bytesFormat.getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib())
      toHexStringShortByteSeparatorNoPrefixAndSuffix(`$this$toHexStringNoLineAndGroupSeparator`, startIndex, endIndex, bytesFormat, byteToDigits)
      else
      toHexStringNoLineAndGroupSeparatorSlowPath(`$this$toHexStringNoLineAndGroupSeparator`, startIndex, endIndex, bytesFormat, byteToDigits);
}

private fun ByteArray.toHexStringShortByteSeparatorNoPrefixAndSuffix(startIndex: Int, endIndex: Int, bytesFormat: BytesHexFormat, byteToDigits: IntArray): String {
   val byteSeparatorLength: Int = bytesFormat.getByteSeparator().length();
   if (byteSeparatorLength > 1) {
      throw new IllegalArgumentException("Failed requirement.".toString());
   } else {
      val numberOfBytes: Int = endIndex - startIndex;
      var charIndex: Int = 0;
      if (byteSeparatorLength == 0) {
         val var14: CharArray = new char[checkFormatLength(2L * (long)numberOfBytes)];

         for (int byteIndex = startIndex; byteIndex < endIndex; byteIndex++) {
            charIndex = formatByteAt(`$this$toHexStringShortByteSeparatorNoPrefixAndSuffix`, var15, byteToDigits, var14, charIndex);
         }

         return StringsKt.concatToString(var14);
      } else {
         val charArray: CharArray = new char[checkFormatLength(3L * (long)numberOfBytes - 1L)];
         val byteSeparatorChar: Char = bytesFormat.getByteSeparator().charAt(0);
         charIndex = formatByteAt(`$this$toHexStringShortByteSeparatorNoPrefixAndSuffix`, startIndex, byteToDigits, charArray, 0);

         for (int byteIndex = startIndex + 1; byteIndex < endIndex; byteIndex++) {
            charArray[charIndex++] = byteSeparatorChar;
            charIndex = formatByteAt(`$this$toHexStringShortByteSeparatorNoPrefixAndSuffix`, byteIndex, byteToDigits, charArray, charIndex);
         }

         return StringsKt.concatToString(charArray);
      }
   }
}

private fun ByteArray.toHexStringNoLineAndGroupSeparatorSlowPath(startIndex: Int, endIndex: Int, bytesFormat: BytesHexFormat, byteToDigits: IntArray): String {
   val bytePrefix: java.lang.String = bytesFormat.getBytePrefix();
   val byteSuffix: java.lang.String = bytesFormat.getByteSuffix();
   val byteSeparator: java.lang.String = bytesFormat.getByteSeparator();
   val charArray: CharArray = new char[formattedStringLength(endIndex - startIndex, byteSeparator.length(), bytePrefix.length(), byteSuffix.length())];
   var var12: Int = formatByteAt(`$this$toHexStringNoLineAndGroupSeparatorSlowPath`, startIndex, bytePrefix, byteSuffix, byteToDigits, charArray, 0);

   for (int byteIndex = startIndex + 1; byteIndex < endIndex; byteIndex++) {
      var12 = formatByteAt(
         `$this$toHexStringNoLineAndGroupSeparatorSlowPath`,
         byteIndex,
         bytePrefix,
         byteSuffix,
         byteToDigits,
         charArray,
         toCharArrayIfNotEmpty(byteSeparator, charArray, var12)
      );
   }

   return StringsKt.concatToString(charArray);
}

private fun ByteArray.toHexStringSlowPath(startIndex: Int, endIndex: Int, bytesFormat: BytesHexFormat, byteToDigits: IntArray): String {
   val bytesPerLine: Int = bytesFormat.getBytesPerLine();
   val bytesPerGroup: Int = bytesFormat.getBytesPerGroup();
   val bytePrefix: java.lang.String = bytesFormat.getBytePrefix();
   val byteSuffix: java.lang.String = bytesFormat.getByteSuffix();
   val byteSeparator: java.lang.String = bytesFormat.getByteSeparator();
   val groupSeparator: java.lang.String = bytesFormat.getGroupSeparator();
   val formatLength: Int = formattedStringLength(
      endIndex - startIndex, bytesPerLine, bytesPerGroup, groupSeparator.length(), byteSeparator.length(), bytePrefix.length(), byteSuffix.length()
   );
   val charArray: CharArray = new char[formatLength];
   var charIndex: Int = 0;
   var indexInLine: Int = 0;
   var indexInGroup: Int = 0;

   for (int byteIndex = startIndex; byteIndex < endIndex; byteIndex++) {
      if (indexInLine == bytesPerLine) {
         charArray[charIndex++] = '\n';
         indexInLine = 0;
         indexInGroup = 0;
      } else if (indexInGroup == bytesPerGroup) {
         charIndex = toCharArrayIfNotEmpty(groupSeparator, charArray, charIndex);
         indexInGroup = 0;
      }

      if (indexInGroup != 0) {
         charIndex = toCharArrayIfNotEmpty(byteSeparator, charArray, charIndex);
      }

      charIndex = formatByteAt(`$this$toHexStringSlowPath`, byteIndex, bytePrefix, byteSuffix, byteToDigits, charArray, charIndex);
      indexInGroup++;
      indexInLine++;
   }

   if (charIndex != formatLength) {
      throw new IllegalStateException("Check failed.");
   } else {
      return StringsKt.concatToString(charArray);
   }
}

private fun ByteArray.formatByteAt(index: Int, bytePrefix: String, byteSuffix: String, byteToDigits: IntArray, destination: CharArray, destinationOffset: Int): Int {
   return toCharArrayIfNotEmpty(
      byteSuffix,
      destination,
      formatByteAt(`$this$formatByteAt`, index, byteToDigits, destination, toCharArrayIfNotEmpty(bytePrefix, destination, destinationOffset))
   );
}

private fun ByteArray.formatByteAt(index: Int, byteToDigits: IntArray, destination: CharArray, destinationOffset: Int): Int {
   destination[destinationOffset] = (char)(byteToDigits[`$this$formatByteAt`[index] and 255] shr 8);
   destination[destinationOffset + 1] = (char)(byteToDigits[`$this$formatByteAt`[index] and 255] and 255);
   return destinationOffset + 2;
}

private fun formattedStringLength(numberOfBytes: Int, byteSeparatorLength: Int, bytePrefixLength: Int, byteSuffixLength: Int): Int {
   if (numberOfBytes <= 0) {
      throw new IllegalArgumentException("Failed requirement.".toString());
   } else {
      return checkFormatLength(
         (long)numberOfBytes * (2L + (long)bytePrefixLength + (long)byteSuffixLength + (long)byteSeparatorLength) - (long)byteSeparatorLength
      );
   }
}

internal fun formattedStringLength(
   numberOfBytes: Int,
   bytesPerLine: Int,
   bytesPerGroup: Int,
   groupSeparatorLength: Int,
   byteSeparatorLength: Int,
   bytePrefixLength: Int,
   byteSuffixLength: Int
): Int {
   if (numberOfBytes <= 0) {
      throw new IllegalArgumentException("Failed requirement.".toString());
   } else {
      return checkFormatLength(
         (long)((numberOfBytes - 1) / bytesPerLine)
            + (long)(
                  (numberOfBytes - 1) / bytesPerLine * ((bytesPerLine - 1) / bytesPerGroup)
                     + ((if (numberOfBytes % bytesPerLine == 0) bytesPerLine else numberOfBytes % bytesPerLine) - 1) / bytesPerGroup
               )
               * (long)groupSeparatorLength
            + (long)(
                  numberOfBytes
                     - 1
                     - (numberOfBytes - 1) / bytesPerLine
                     - (
                        (numberOfBytes - 1) / bytesPerLine * ((bytesPerLine - 1) / bytesPerGroup)
                           + ((if (numberOfBytes % bytesPerLine == 0) bytesPerLine else numberOfBytes % bytesPerLine) - 1) / bytesPerGroup
                     )
               )
               * (long)byteSeparatorLength
            + (long)numberOfBytes * ((long)bytePrefixLength + 2L + (long)byteSuffixLength)
      );
   }
}

private fun checkFormatLength(formatLength: Long): Int {
   if (0L > formatLength || formatLength > 2147483647L) {
      throw new IllegalArgumentException("The resulting string length is too big: ${ULong.toString-impl(ULong.constructor-impl(formatLength))}");
   } else {
      return (int)formatLength;
   }
}

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun String.hexToByteArray(format: HexFormat = HexFormat.Companion.getDefault()): ByteArray {
   return hexToByteArray(`$this$hexToByteArray`, 0, `$this$hexToByteArray`.length(), format);
}

@JvmSynthetic
fun `hexToByteArray$default`(var0: java.lang.String, var1: HexFormat, var2: Int, var3: Any): ByteArray {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return hexToByteArray(var0, var1);
}

private fun String.hexToByteArray(startIndex: Int = 0, endIndex: Int = `$this$hexToByteArray`.length(), format: HexFormat = HexFormat.Companion.getDefault()): ByteArray {
   AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, `$this$hexToByteArray`.length());
   if (startIndex == endIndex) {
      return new byte[0];
   } else {
      val bytesFormat: HexFormat.BytesHexFormat = format.getBytes();
      if (bytesFormat.getNoLineAndGroupSeparator$kotlin_stdlib()) {
         val var5: ByteArray = hexToByteArrayNoLineAndGroupSeparator(`$this$hexToByteArray`, startIndex, endIndex, bytesFormat);
         if (var5 != null) {
            return var5;
         }
      }

      return hexToByteArraySlowPath(`$this$hexToByteArray`, startIndex, endIndex, bytesFormat);
   }
}

@JvmSynthetic
fun `hexToByteArray$default`(var0: java.lang.String, var1: Int, var2: Int, var3: HexFormat, var4: Int, var5: Any): ByteArray {
   if ((var4 and 1) != 0) {
      var1 = 0;
   }

   if ((var4 and 2) != 0) {
      var2 = var0.length();
   }

   if ((var4 and 4) != 0) {
      var3 = HexFormat.Companion.getDefault();
   }

   return hexToByteArray(var0, var1, var2, var3);
}

private fun String.hexToByteArrayNoLineAndGroupSeparator(startIndex: Int, endIndex: Int, bytesFormat: BytesHexFormat): ByteArray? {
   return if (bytesFormat.getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib())
      hexToByteArrayShortByteSeparatorNoPrefixAndSuffix(`$this$hexToByteArrayNoLineAndGroupSeparator`, startIndex, endIndex, bytesFormat)
      else
      hexToByteArrayNoLineAndGroupSeparatorSlowPath(`$this$hexToByteArrayNoLineAndGroupSeparator`, startIndex, endIndex, bytesFormat);
}

private fun String.hexToByteArrayShortByteSeparatorNoPrefixAndSuffix(startIndex: Int, endIndex: Int, bytesFormat: BytesHexFormat): ByteArray? {
   val byteSeparatorLength: Int = bytesFormat.getByteSeparator().length();
   if (byteSeparatorLength > 1) {
      throw new IllegalArgumentException("Failed requirement.".toString());
   } else {
      val numberOfChars: Int = endIndex - startIndex;
      var charIndex: Int = 0;
      if (byteSeparatorLength == 0) {
         if ((numberOfChars and 1) != 0) {
            return null;
         } else {
            val var22: Int = numberOfChars shr 1;
            val var23: ByteArray = new byte[numberOfChars shr 1];

            for (int byteIndex = 0; byteIndex < numberOfBytes; byteIndex++) {
               var23[var24] = parseByteAt(`$this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix`, charIndex);
               charIndex += 2;
            }

            return var23;
         }
      } else if (numberOfChars % 3 != 2) {
         return null;
      } else {
         val numberOfBytes: Int = numberOfChars / 3 + 1;
         val byteArray: ByteArray = new byte[numberOfChars / 3 + 1];
         val byteSeparatorChar: Char = bytesFormat.getByteSeparator().charAt(0);
         byteArray[0] = parseByteAt(`$this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix`, 0);
         charIndex = 0 + 2;

         for (int byteIndex = 1; byteIndex < numberOfBytes; byteIndex++) {
            if (`$this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix`.charAt(charIndex) != byteSeparatorChar) {
               val `$this$checkContainsAt$iv`: java.lang.String = `$this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix`;
               val `index$iv`: Int = charIndex;
               val `endIndex$iv`: Int = endIndex;
               val `part$iv`: java.lang.String = bytesFormat.getByteSeparator();
               val `ignoreCase$iv`: Boolean = bytesFormat.getIgnoreCase$kotlin_stdlib();
               val `partName$iv`: java.lang.String = "byte separator";
               if (`part$iv`.length() != 0) {
                  var `i$iv`: Int = 0;

                  for (int var19 = part$iv.length(); i$iv < var19; i$iv++) {
                     if (!CharsKt.equals(`part$iv`.charAt(`i$iv`), `$this$checkContainsAt$iv`.charAt(`index$iv` + `i$iv`), `ignoreCase$iv`)) {
                        throwNotContainedAt(`$this$checkContainsAt$iv`, `index$iv`, `endIndex$iv`, `part$iv`, `partName$iv`);
                     }
                  }

                  val var10000: Int = `index$iv` + `part$iv`.length();
               }
            }

            byteArray[byteIndex] = parseByteAt(`$this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix`, charIndex + 1);
            charIndex += 3;
         }

         return byteArray;
      }
   }
}

private fun String.hexToByteArrayNoLineAndGroupSeparatorSlowPath(startIndex: Int, endIndex: Int, bytesFormat: BytesHexFormat): ByteArray? {
   val bytePrefix: java.lang.String = bytesFormat.getBytePrefix();
   val byteSuffix: java.lang.String = bytesFormat.getByteSuffix();
   val byteSeparator: java.lang.String = bytesFormat.getByteSeparator();
   val byteSeparatorLength: Int = byteSeparator.length();
   val charsPerByte: Long = 2L + bytePrefix.length() + byteSuffix.length() + byteSeparatorLength;
   val numberOfChars: Long = endIndex - startIndex;
   val numberOfBytes: Int = (int)(((long)(endIndex - startIndex) + byteSeparatorLength) / charsPerByte);
   if ((int)(((long)(endIndex - startIndex) + byteSeparatorLength) / charsPerByte) * charsPerByte - byteSeparatorLength != numberOfChars) {
      return null;
   } else {
      val ignoreCase: Boolean = bytesFormat.getIgnoreCase$kotlin_stdlib();
      val byteArray: ByteArray = new byte[numberOfBytes];
      var between: java.lang.String = `$this$hexToByteArrayNoLineAndGroupSeparatorSlowPath`;
      var `$this$checkContainsAt$iv`: Int = startIndex;
      var `index$iv`: Int = endIndex;
      var `endIndex$iv`: java.lang.String = bytePrefix;
      var `part$iv`: Int = ignoreCase;
      val `ignoreCase$iv`: java.lang.String = "byte prefix";
      var var10000: Int;
      if (bytePrefix.length() == 0) {
         var10000 = startIndex;
      } else {
         var `$i$f$checkContainsAt`: Int = 0;

         for (int i$iv = bytePrefix.length(); i$iv < i$iv; i$iv++) {
            if (!CharsKt.equals(
               `endIndex$iv`.charAt(`$i$f$checkContainsAt`), between.charAt(`$this$checkContainsAt$iv` + `$i$f$checkContainsAt`), (boolean)`part$iv`
            )) {
               throwNotContainedAt(between, `$this$checkContainsAt$iv`, `index$iv`, `endIndex$iv`, `ignoreCase$iv`);
            }
         }

         var10000 = `$this$checkContainsAt$iv` + `endIndex$iv`.length();
      }

      var charIndex: Int = var10000;
      between = "$byteSuffix$byteSeparator$bytePrefix";
      `$this$checkContainsAt$iv` = 0;

      for (int var31 = numberOfBytes - 1; byteIndex < var31; byteIndex++) {
         byteArray[`$this$checkContainsAt$iv`] = parseByteAt(`$this$hexToByteArrayNoLineAndGroupSeparatorSlowPath`, charIndex);
         `endIndex$iv` = `$this$hexToByteArrayNoLineAndGroupSeparatorSlowPath`;
         `part$iv` = charIndex + 2;
         val var37: Int = endIndex;
         val var39: java.lang.String = between;
         val var41: Boolean = ignoreCase;
         val `partName$ivx`: java.lang.String = "byte suffix + byte separator + byte prefix";
         if (between.length() == 0) {
            var10000 = `part$iv`;
         } else {
            var `i$ivx`: Int = 0;

            for (int var27 = between.length(); i$ivx < var27; i$ivx++) {
               if (!CharsKt.equals(var39.charAt(`i$ivx`), `endIndex$iv`.charAt(`part$iv` + `i$ivx`), var41)) {
                  throwNotContainedAt(`endIndex$iv`, `part$iv`, var37, var39, `partName$ivx`);
               }
            }

            var10000 = `part$iv` + var39.length();
         }

         charIndex = var10000;
      }

      byteArray[numberOfBytes - 1] = parseByteAt(`$this$hexToByteArrayNoLineAndGroupSeparatorSlowPath`, charIndex);
      val var30: java.lang.String = `$this$hexToByteArrayNoLineAndGroupSeparatorSlowPath`;
      `index$iv` = charIndex + 2;
      val var34: Int = endIndex;
      val var36: java.lang.String = byteSuffix;
      val var38: Boolean = ignoreCase;
      val `partName$ivx`: java.lang.String = "byte suffix";
      if (byteSuffix.length() != 0) {
         var var44: Int = 0;

         for (int var45 = byteSuffix.length(); i$iv < var45; i$iv++) {
            if (!CharsKt.equals(var36.charAt(var44), var30.charAt(`index$iv` + var44), var38)) {
               throwNotContainedAt(var30, `index$iv`, var34, var36, `partName$ivx`);
            }
         }

         var10000 = `index$iv` + var36.length();
      }

      return byteArray;
   }
}

private fun String.hexToByteArraySlowPath(startIndex: Int, endIndex: Int, bytesFormat: BytesHexFormat): ByteArray {
   val bytesPerLine: Int = bytesFormat.getBytesPerLine();
   val bytesPerGroup: Int = bytesFormat.getBytesPerGroup();
   val bytePrefix: java.lang.String = bytesFormat.getBytePrefix();
   val byteSuffix: java.lang.String = bytesFormat.getByteSuffix();
   val byteSeparator: java.lang.String = bytesFormat.getByteSeparator();
   val groupSeparator: java.lang.String = bytesFormat.getGroupSeparator();
   val ignoreCase: Boolean = bytesFormat.getIgnoreCase$kotlin_stdlib();
   val byteArray: ByteArray = new byte[parsedByteArrayMaxSize(
      endIndex - startIndex, bytesPerLine, bytesPerGroup, groupSeparator.length(), byteSeparator.length(), bytePrefix.length(), byteSuffix.length()
   )];
   var charIndex: Int = startIndex;
   var byteIndex: Int = 0;
   var indexInLine: Int = 0;
   var indexInGroup: Int = 0;

   while (charIndex < endIndex) {
      if (indexInLine == bytesPerLine) {
         charIndex = checkNewLineAt(`$this$hexToByteArraySlowPath`, charIndex, endIndex);
         indexInLine = 0;
         indexInGroup = 0;
      } else if (indexInGroup == bytesPerGroup) {
         val var27: java.lang.String = `$this$hexToByteArraySlowPath`;
         val var30: Int = charIndex;
         val var33: Int = endIndex;
         val var36: java.lang.String = groupSeparator;
         val var39: Boolean = ignoreCase;
         val var42: java.lang.String = "group separator";
         val var54: Int;
         if (groupSeparator.length() == 0) {
            var54 = charIndex;
         } else {
            var var48: Int = 0;

            for (int var51 = groupSeparator.length(); i$iv < var51; i$iv++) {
               if (!CharsKt.equals(var36.charAt(var48), var27.charAt(var30 + var48), var39)) {
                  throwNotContainedAt(var27, var30, var33, var36, var42);
               }
            }

            var54 = var30 + var36.length();
         }

         charIndex = var54;
         indexInGroup = 0;
      } else if (indexInGroup != 0) {
         val `$this$checkContainsAt$iv`: java.lang.String = `$this$hexToByteArraySlowPath`;
         val `index$iv`: Int = charIndex;
         val `endIndex$iv`: Int = endIndex;
         val `part$iv`: java.lang.String = byteSeparator;
         val `ignoreCase$iv`: Boolean = ignoreCase;
         val `partName$iv`: java.lang.String = "byte separator";
         val var10000: Int;
         if (byteSeparator.length() == 0) {
            var10000 = charIndex;
         } else {
            var `i$iv`: Int = 0;

            for (int var25 = byteSeparator.length(); i$iv < var25; i$iv++) {
               if (!CharsKt.equals(`part$iv`.charAt(`i$iv`), `$this$checkContainsAt$iv`.charAt(`index$iv` + `i$iv`), `ignoreCase$iv`)) {
                  throwNotContainedAt(`$this$checkContainsAt$iv`, `index$iv`, `endIndex$iv`, `part$iv`, `partName$iv`);
               }
            }

            var10000 = `index$iv` + `part$iv`.length();
         }

         charIndex = var10000;
      }

      indexInLine++;
      indexInGroup++;
      var var28: java.lang.String = `$this$hexToByteArraySlowPath`;
      var var31: Int = charIndex;
      var var34: Int = endIndex;
      var var37: java.lang.String = bytePrefix;
      var var40: Boolean = ignoreCase;
      var var43: java.lang.String = "byte prefix";
      var var55: Int;
      if (bytePrefix.length() == 0) {
         var55 = charIndex;
      } else {
         var var49: Int = 0;

         for (int var52 = bytePrefix.length(); i$iv < var52; i$iv++) {
            if (!CharsKt.equals(var37.charAt(var49), var28.charAt(var31 + var49), var40)) {
               throwNotContainedAt(var28, var31, var34, var37, var43);
            }
         }

         var55 = var31 + var37.length();
      }

      if (endIndex - 2 < var55) {
         throwInvalidNumberOfDigits(`$this$hexToByteArraySlowPath`, var55, endIndex, "exactly", 2);
      }

      byteArray[byteIndex++] = parseByteAt(`$this$hexToByteArraySlowPath`, var55);
      var28 = `$this$hexToByteArraySlowPath`;
      var31 = var55 + 2;
      var34 = endIndex;
      var37 = byteSuffix;
      var40 = ignoreCase;
      var43 = "byte suffix";
      if (byteSuffix.length() == 0) {
         var55 = var31;
      } else {
         var var50: Int = 0;

         for (int var53 = byteSuffix.length(); i$iv < var53; i$iv++) {
            if (!CharsKt.equals(var37.charAt(var50), var28.charAt(var31 + var50), var40)) {
               throwNotContainedAt(var28, var31, var34, var37, var43);
            }
         }

         var55 = var31 + var37.length();
      }

      charIndex = var55;
   }

   val var57: ByteArray;
   if (byteIndex == byteArray.length) {
      var57 = byteArray;
   } else {
      var57 = Arrays.copyOf(byteArray, byteIndex);
   }

   return var57;
}

private fun String.parseByteAt(index: Int): Byte {
   val `$i$f$decimalFromHexDigitAt`: Int = `$this$parseByteAt`.charAt(index);
   if (`$i$f$decimalFromHexDigitAt` ushr 8 == 0 && HEX_DIGITS_TO_DECIMAL[`$i$f$decimalFromHexDigitAt`] >= 0) {
      val high: Int = HEX_DIGITS_TO_DECIMAL[`$i$f$decimalFromHexDigitAt`];
      val var8: Int = index + 1;
      val `code$ivx`: Int = `$this$parseByteAt`.charAt(index + 1);
      if (`code$ivx` ushr 8 == 0 && HEX_DIGITS_TO_DECIMAL[`code$ivx`] >= 0) {
         return (byte)(high shl 4 or HEX_DIGITS_TO_DECIMAL[`code$ivx`]);
      } else {
         throwInvalidDigitAt(`$this$parseByteAt`, var8);
         throw new KotlinNothingValueException();
      }
   } else {
      throwInvalidDigitAt(`$this$parseByteAt`, index);
      throw new KotlinNothingValueException();
   }
}

internal fun parsedByteArrayMaxSize(
   stringLength: Int,
   bytesPerLine: Int,
   bytesPerGroup: Int,
   groupSeparatorLength: Int,
   byteSeparatorLength: Int,
   bytePrefixLength: Int,
   byteSuffixLength: Int
): Int {
   if (stringLength <= 0) {
      throw new IllegalArgumentException("Failed requirement.".toString());
   } else {
      val charsPerByte: Long = bytePrefixLength + 2L + byteSuffixLength;
      val charsPerGroup: Long = charsPerSet((long)bytePrefixLength + 2L + (long)byteSuffixLength, bytesPerGroup, byteSeparatorLength);
      val var10000: Long;
      if (bytesPerLine <= bytesPerGroup) {
         var10000 = charsPerSet(charsPerByte, bytesPerLine, byteSeparatorLength);
      } else {
         var result: Long = charsPerSet(charsPerGroup, bytesPerLine / bytesPerGroup, groupSeparatorLength);
         val bytesPerLastGroupInLine: Int = bytesPerLine % bytesPerGroup;
         if (bytesPerLine % bytesPerGroup != 0) {
            result = result + groupSeparatorLength + charsPerSet(charsPerByte, bytesPerLastGroupInLine, byteSeparatorLength);
         }

         var10000 = result;
      }

      val var22: Long = stringLength;
      val wholeLines: Long = wholeElementsPerSet((long)stringLength, var10000, 1);
      val var23: Long = stringLength - wholeLines * (var10000 + 1L);
      val wholeGroupsInLastLine: Long = wholeElementsPerSet(var22 - wholeLines * (var10000 + 1L), charsPerGroup, groupSeparatorLength);
      val wholeBytesInLastGroup: Long = wholeElementsPerSet(
         var23 - wholeGroupsInLastLine * (charsPerGroup + (long)groupSeparatorLength), charsPerByte, byteSeparatorLength
      );
      return (int)(
         wholeLines * bytesPerLine
            + wholeGroupsInLastLine * bytesPerGroup
            + wholeBytesInLastGroup
            + (
               if (var23 - wholeGroupsInLastLine * (charsPerGroup + groupSeparatorLength) - wholeBytesInLastGroup * (charsPerByte + byteSeparatorLength) > 0L)
                  1
                  else
                  0
            )
      );
   }
}

private fun charsPerSet(charsPerElement: Long, elementsPerSet: Int, elementSeparatorLength: Int): Long {
   if (elementsPerSet <= 0) {
      throw new IllegalArgumentException("Failed requirement.".toString());
   } else {
      return charsPerElement * elementsPerSet + elementSeparatorLength * (elementsPerSet - 1L);
   }
}

private fun wholeElementsPerSet(charsPerSet: Long, charsPerElement: Long, elementSeparatorLength: Int): Long {
   return if (charsPerSet > 0L && charsPerElement > 0L) (charsPerSet + elementSeparatorLength) / (charsPerElement + elementSeparatorLength) else 0L;
}

private fun String.checkNewLineAt(index: Int, endIndex: Int): Int {
   val var10000: Int;
   if (`$this$checkNewLineAt`.charAt(index) == '\r') {
      var10000 = if (index + 1 < endIndex && `$this$checkNewLineAt`.charAt(index + 1) == '\n') index + 2 else index + 1;
   } else {
      if (`$this$checkNewLineAt`.charAt(index) != '\n') {
         throw new NumberFormatException("Expected a new line at index $index, but was ${`$this$checkNewLineAt`.charAt(index)}");
      }

      var10000 = index + 1;
   }

   return var10000;
}

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun Byte.toHexString(format: HexFormat = HexFormat.Companion.getDefault()): String {
   val digits: java.lang.String = if (format.getUpperCase()) "0123456789ABCDEF" else "0123456789abcdef";
   val numberFormat: HexFormat.NumberHexFormat = format.getNumber();
   if (numberFormat.isDigitsOnlyAndNoPadding$kotlin_stdlib()) {
      val charArray: CharArray = new char[]{digits.charAt(`$this$toHexString` shr 4 and 15), digits.charAt(`$this$toHexString` and 15)};
      return if (numberFormat.getRemoveLeadingZeros())
         StringsKt.concatToString$default(charArray, RangesKt.coerceAtMost(Integer.numberOfLeadingZeros(`$this$toHexString` and 255) - 24 shr 2, 1), 0, 2, null)
         else
         StringsKt.concatToString(charArray);
   } else {
      return toHexStringImpl((long)`$this$toHexString`, numberFormat, digits, 8);
   }
}

@JvmSynthetic
fun `toHexString$default`(var0: Byte, var1: HexFormat, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return toHexString(var0, var1);
}

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun String.hexToByte(format: HexFormat = HexFormat.Companion.getDefault()): Byte {
   return hexToByte(`$this$hexToByte`, 0, `$this$hexToByte`.length(), format);
}

@JvmSynthetic
fun `hexToByte$default`(var0: java.lang.String, var1: HexFormat, var2: Int, var3: Any): Byte {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return hexToByte(var0, var1);
}

private fun String.hexToByte(startIndex: Int = 0, endIndex: Int = `$this$hexToByte`.length(), format: HexFormat = HexFormat.Companion.getDefault()): Byte {
   return (byte)hexToIntImpl(`$this$hexToByte`, startIndex, endIndex, format, 2);
}

@JvmSynthetic
fun `hexToByte$default`(var0: java.lang.String, var1: Int, var2: Int, var3: HexFormat, var4: Int, var5: Any): Byte {
   if ((var4 and 1) != 0) {
      var1 = 0;
   }

   if ((var4 and 2) != 0) {
      var2 = var0.length();
   }

   if ((var4 and 4) != 0) {
      var3 = HexFormat.Companion.getDefault();
   }

   return hexToByte(var0, var1, var2, var3);
}

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun Short.toHexString(format: HexFormat = HexFormat.Companion.getDefault()): String {
   val digits: java.lang.String = if (format.getUpperCase()) "0123456789ABCDEF" else "0123456789abcdef";
   val numberFormat: HexFormat.NumberHexFormat = format.getNumber();
   if (numberFormat.isDigitsOnlyAndNoPadding$kotlin_stdlib()) {
      val charArray: CharArray = new char[]{
         digits.charAt(`$this$toHexString` shr 12 and 15),
         digits.charAt(`$this$toHexString` shr 8 and 15),
         digits.charAt(`$this$toHexString` shr 4 and 15),
         digits.charAt(`$this$toHexString` and 15)
      };
      return if (numberFormat.getRemoveLeadingZeros())
         StringsKt.concatToString$default(
            charArray, RangesKt.coerceAtMost(Integer.numberOfLeadingZeros(`$this$toHexString` and '\uffff') - 16 shr 2, 3), 0, 2, null
         )
         else
         StringsKt.concatToString(charArray);
   } else {
      return toHexStringImpl((long)`$this$toHexString`, numberFormat, digits, 16);
   }
}

@JvmSynthetic
fun `toHexString$default`(var0: Short, var1: HexFormat, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return toHexString(var0, var1);
}

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun String.hexToShort(format: HexFormat = HexFormat.Companion.getDefault()): Short {
   return hexToShort(`$this$hexToShort`, 0, `$this$hexToShort`.length(), format);
}

@JvmSynthetic
fun `hexToShort$default`(var0: java.lang.String, var1: HexFormat, var2: Int, var3: Any): Short {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return hexToShort(var0, var1);
}

private fun String.hexToShort(startIndex: Int = 0, endIndex: Int = `$this$hexToShort`.length(), format: HexFormat = HexFormat.Companion.getDefault()): Short {
   return (short)hexToIntImpl(`$this$hexToShort`, startIndex, endIndex, format, 4);
}

@JvmSynthetic
fun `hexToShort$default`(var0: java.lang.String, var1: Int, var2: Int, var3: HexFormat, var4: Int, var5: Any): Short {
   if ((var4 and 1) != 0) {
      var1 = 0;
   }

   if ((var4 and 2) != 0) {
      var2 = var0.length();
   }

   if ((var4 and 4) != 0) {
      var3 = HexFormat.Companion.getDefault();
   }

   return hexToShort(var0, var1, var2, var3);
}

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun Int.toHexString(format: HexFormat = HexFormat.Companion.getDefault()): String {
   val digits: java.lang.String = if (format.getUpperCase()) "0123456789ABCDEF" else "0123456789abcdef";
   val numberFormat: HexFormat.NumberHexFormat = format.getNumber();
   if (numberFormat.isDigitsOnlyAndNoPadding$kotlin_stdlib()) {
      val charArray: CharArray = new char[]{
         digits.charAt(`$this$toHexString` shr 28 and 15),
         digits.charAt(`$this$toHexString` shr 24 and 15),
         digits.charAt(`$this$toHexString` shr 20 and 15),
         digits.charAt(`$this$toHexString` shr 16 and 15),
         digits.charAt(`$this$toHexString` shr 12 and 15),
         digits.charAt(`$this$toHexString` shr 8 and 15),
         digits.charAt(`$this$toHexString` shr 4 and 15),
         digits.charAt(`$this$toHexString` and 15)
      };
      return if (numberFormat.getRemoveLeadingZeros())
         StringsKt.concatToString$default(charArray, RangesKt.coerceAtMost(Integer.numberOfLeadingZeros(`$this$toHexString`) shr 2, 7), 0, 2, null)
         else
         StringsKt.concatToString(charArray);
   } else {
      return toHexStringImpl((long)`$this$toHexString`, numberFormat, digits, 32);
   }
}

@JvmSynthetic
fun `toHexString$default`(var0: Int, var1: HexFormat, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return toHexString(var0, var1);
}

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun String.hexToInt(format: HexFormat = HexFormat.Companion.getDefault()): Int {
   return hexToInt(`$this$hexToInt`, 0, `$this$hexToInt`.length(), format);
}

@JvmSynthetic
fun `hexToInt$default`(var0: java.lang.String, var1: HexFormat, var2: Int, var3: Any): Int {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return hexToInt(var0, var1);
}

internal fun String.hexToInt(startIndex: Int = 0, endIndex: Int = `$this$hexToInt`.length(), format: HexFormat = HexFormat.Companion.getDefault()): Int {
   return hexToIntImpl(`$this$hexToInt`, startIndex, endIndex, format, 8);
}

@JvmSynthetic
fun `hexToInt$default`(var0: java.lang.String, var1: Int, var2: Int, var3: HexFormat, var4: Int, var5: Any): Int {
   if ((var4 and 1) != 0) {
      var1 = 0;
   }

   if ((var4 and 2) != 0) {
      var2 = var0.length();
   }

   if ((var4 and 4) != 0) {
      var3 = HexFormat.Companion.getDefault();
   }

   return hexToInt(var0, var1, var2, var3);
}

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun Long.toHexString(format: HexFormat = HexFormat.Companion.getDefault()): String {
   val digits: java.lang.String = if (format.getUpperCase()) "0123456789ABCDEF" else "0123456789abcdef";
   val numberFormat: HexFormat.NumberHexFormat = format.getNumber();
   if (numberFormat.isDigitsOnlyAndNoPadding$kotlin_stdlib()) {
      val charArray: CharArray = new char[]{
         digits.charAt((int)(`$this$toHexString` shr 60 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 56 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 52 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 48 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 44 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 40 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 36 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 32 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 28 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 24 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 20 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 16 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 12 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 8 and 15L)),
         digits.charAt((int)(`$this$toHexString` shr 4 and 15L)),
         digits.charAt((int)(`$this$toHexString` and 15L))
      };
      return if (numberFormat.getRemoveLeadingZeros())
         StringsKt.concatToString$default(charArray, RangesKt.coerceAtMost(java.lang.Long.numberOfLeadingZeros(`$this$toHexString`) shr 2, 15), 0, 2, null)
         else
         StringsKt.concatToString(charArray);
   } else {
      return toHexStringImpl(`$this$toHexString`, numberFormat, digits, 64);
   }
}

@JvmSynthetic
fun `toHexString$default`(var0: Long, var2: HexFormat, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 1) != 0) {
      var2 = HexFormat.Companion.getDefault();
   }

   return toHexString(var0, var2);
}

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public fun String.hexToLong(format: HexFormat = HexFormat.Companion.getDefault()): Long {
   return hexToLong(`$this$hexToLong`, 0, `$this$hexToLong`.length(), format);
}

@JvmSynthetic
fun `hexToLong$default`(var0: java.lang.String, var1: HexFormat, var2: Int, var3: Any): Long {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return hexToLong(var0, var1);
}

internal fun String.hexToLong(startIndex: Int = 0, endIndex: Int = `$this$hexToLong`.length(), format: HexFormat = HexFormat.Companion.getDefault()): Long {
   return hexToLongImpl(`$this$hexToLong`, startIndex, endIndex, format, 16);
}

@JvmSynthetic
fun `hexToLong$default`(var0: java.lang.String, var1: Int, var2: Int, var3: HexFormat, var4: Int, var5: Any): Long {
   if ((var4 and 1) != 0) {
      var1 = 0;
   }

   if ((var4 and 2) != 0) {
      var2 = var0.length();
   }

   if ((var4 and 4) != 0) {
      var3 = HexFormat.Companion.getDefault();
   }

   return hexToLong(var0, var1, var2, var3);
}

private fun Long.toHexStringImpl(numberFormat: NumberHexFormat, digits: String, bits: Int): String {
   if ((bits and 3) != 0) {
      throw new IllegalArgumentException("Failed requirement.".toString());
   } else {
      val value: Long = `$this$toHexStringImpl`;
      val typeHexLength: Int = bits shr 2;
      val minLength: Int = numberFormat.getMinLength();
      val pads: Int = RangesKt.coerceAtLeast(minLength - typeHexLength, 0);
      val prefix: java.lang.String = numberFormat.getPrefix();
      val suffix: java.lang.String = numberFormat.getSuffix();
      var var23: Boolean = numberFormat.getRemoveLeadingZeros();
      val charArray: CharArray = new char[checkFormatLength((long)prefix.length() + (long)pads + (long)typeHexLength + (long)suffix.length())];
      var var24: Int = toCharArrayIfNotEmpty(prefix, charArray, 0);
      if (pads > 0) {
         ArraysKt.fill(charArray, digits.charAt(0), var24, var24 + pads);
         var24 += pads;
      }

      var var26: Int = bits;

      for (int var18 = 0; var18 < typeHexLength; var18++) {
         var26 -= 4;
         val decimal: Int = (int)(value shr var26 and 15L);
         var23 = var23 && (int)(value shr var26 and 15L) == 0 && var26 shr 2 >= minLength;
         if (!var23) {
            charArray[var24++] = digits.charAt(decimal);
         }
      }

      var24 = toCharArrayIfNotEmpty(suffix, charArray, var24);
      return if (var24 == charArray.length) StringsKt.concatToString(charArray) else StringsKt.concatToString$default(charArray, 0, var24, 1, null);
   }
}

private fun String.toCharArrayIfNotEmpty(destination: CharArray, destinationOffset: Int): Int {
   switch ($this$toCharArrayIfNotEmpty.length()) {
      case 0:
         break;
      case 1:
         destination[destinationOffset] = `$this$toCharArrayIfNotEmpty`.charAt(0);
         break;
      default:
         val var5: Int = `$this$toCharArrayIfNotEmpty`.length();
         `$this$toCharArrayIfNotEmpty`.getChars(0, var5, destination, destinationOffset);
   }

   return destinationOffset + `$this$toCharArrayIfNotEmpty`.length();
}

private fun String.hexToIntImpl(startIndex: Int, endIndex: Int, format: HexFormat, typeHexLength: Int): Int {
   AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, `$this$hexToIntImpl`.length());
   val numberFormat: HexFormat.NumberHexFormat = format.getNumber();
   if (numberFormat.isDigitsOnly$kotlin_stdlib()) {
      checkNumberOfDigits(`$this$hexToIntImpl`, startIndex, endIndex, typeHexLength);
      return parseInt(`$this$hexToIntImpl`, startIndex, endIndex);
   } else {
      val prefix: java.lang.String = numberFormat.getPrefix();
      val suffix: java.lang.String = numberFormat.getSuffix();
      checkPrefixSuffixNumberOfDigits(`$this$hexToIntImpl`, startIndex, endIndex, prefix, suffix, numberFormat.getIgnoreCase$kotlin_stdlib(), typeHexLength);
      return parseInt(`$this$hexToIntImpl`, startIndex + prefix.length(), endIndex - suffix.length());
   }
}

private fun String.hexToLongImpl(startIndex: Int, endIndex: Int, format: HexFormat, typeHexLength: Int): Long {
   AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, `$this$hexToLongImpl`.length());
   val numberFormat: HexFormat.NumberHexFormat = format.getNumber();
   if (numberFormat.isDigitsOnly$kotlin_stdlib()) {
      checkNumberOfDigits(`$this$hexToLongImpl`, startIndex, endIndex, typeHexLength);
      return parseLong(`$this$hexToLongImpl`, startIndex, endIndex);
   } else {
      val prefix: java.lang.String = numberFormat.getPrefix();
      val suffix: java.lang.String = numberFormat.getSuffix();
      checkPrefixSuffixNumberOfDigits(`$this$hexToLongImpl`, startIndex, endIndex, prefix, suffix, numberFormat.getIgnoreCase$kotlin_stdlib(), typeHexLength);
      return parseLong(`$this$hexToLongImpl`, startIndex + prefix.length(), endIndex - suffix.length());
   }
}

private fun String.checkPrefixSuffixNumberOfDigits(startIndex: Int, endIndex: Int, prefix: String, suffix: String, ignoreCase: Boolean, typeHexLength: Int) {
   if (endIndex - startIndex - prefix.length() <= suffix.length()) {
      throwInvalidPrefixSuffix(`$this$checkPrefixSuffixNumberOfDigits`, startIndex, endIndex, prefix, suffix);
   }

   val digitsEndIndex: java.lang.String = `$this$checkPrefixSuffixNumberOfDigits`;
   val `$this$checkContainsAt$iv`: Int = startIndex;
   var `index$iv`: Int = endIndex;
   val `endIndex$iv`: java.lang.String = prefix;
   val `part$iv`: Boolean = ignoreCase;
   val `ignoreCase$iv`: java.lang.String = "prefix";
   var var10000: Int;
   if (prefix.length() == 0) {
      var10000 = startIndex;
   } else {
      var `$i$f$checkContainsAt`: Int = 0;

      for (int i$iv = prefix.length(); i$iv < i$iv; i$iv++) {
         if (!CharsKt.equals(
            `endIndex$iv`.charAt(`$i$f$checkContainsAt`), digitsEndIndex.charAt(`$this$checkContainsAt$iv` + `$i$f$checkContainsAt`), `part$iv`
         )) {
            throwNotContainedAt(digitsEndIndex, `$this$checkContainsAt$iv`, `index$iv`, `endIndex$iv`, `ignoreCase$iv`);
         }
      }

      var10000 = `$this$checkContainsAt$iv` + `endIndex$iv`.length();
   }

   val var18: Int = endIndex - suffix.length();
   val var19: java.lang.String = `$this$checkPrefixSuffixNumberOfDigits`;
   `index$iv` = var18;
   val var21: Int = endIndex;
   val var22: java.lang.String = suffix;
   val var23: Boolean = ignoreCase;
   val `partName$ivx`: java.lang.String = "suffix";
   if (suffix.length() != 0) {
      var var26: Int = 0;

      for (int var17 = suffix.length(); i$iv < var17; i$iv++) {
         if (!CharsKt.equals(var22.charAt(var26), var19.charAt(`index$iv` + var26), var23)) {
            throwNotContainedAt(var19, `index$iv`, var21, var22, `partName$ivx`);
         }
      }

      var10000 = `index$iv` + var22.length();
   }

   checkNumberOfDigits(`$this$checkPrefixSuffixNumberOfDigits`, var10000, var18, typeHexLength);
}

private fun String.checkNumberOfDigits(startIndex: Int, endIndex: Int, typeHexLength: Int) {
   val digits: Int = endIndex - startIndex;
   if (endIndex - startIndex < 1) {
      throwInvalidNumberOfDigits(`$this$checkNumberOfDigits`, startIndex, endIndex, "at least", 1);
   } else if (digits > typeHexLength) {
      checkZeroDigits(`$this$checkNumberOfDigits`, startIndex, startIndex + digits - typeHexLength);
   }
}

private fun String.checkZeroDigits(startIndex: Int, endIndex: Int) {
   for (int index = startIndex; index < endIndex; index++) {
      if (`$this$checkZeroDigits`.charAt(index) != '0') {
         throw new NumberFormatException(
            "Expected the hexadecimal digit '0' at index $index, but was '${`$this$checkZeroDigits`.charAt(index)}'.\nThe result won't fit the type being parsed."
         );
      }
   }
}

private fun String.parseInt(startIndex: Int, endIndex: Int): Int {
   var result: Int = 0;

   for (int i = startIndex; i < endIndex; i++) {
      val var10000: Int = result shl 4;
      val `code$iv`: Int = `$this$parseInt`.charAt(i);
      if (`code$iv` ushr 8 != 0 || HEX_DIGITS_TO_DECIMAL[`code$iv`] < 0) {
         throwInvalidDigitAt(`$this$parseInt`, i);
         throw new KotlinNothingValueException();
      }

      result = var10000 or HEX_DIGITS_TO_DECIMAL[`code$iv`];
   }

   return result;
}

private fun String.parseLong(startIndex: Int, endIndex: Int): Long {
   var result: Long = 0L;

   for (int i = startIndex; i < endIndex; i++) {
      val var10000: Long = result shl 4;
      val `code$iv`: Int = `$this$parseLong`.charAt(i);
      if (`code$iv` ushr 8 != 0 || HEX_DIGITS_TO_LONG_DECIMAL[`code$iv`] < 0L) {
         throwInvalidDigitAt(`$this$parseLong`, i);
         throw new KotlinNothingValueException();
      }

      result = var10000 or HEX_DIGITS_TO_LONG_DECIMAL[`code$iv`];
   }

   return result;
}

private inline fun String.checkContainsAt(index: Int, endIndex: Int, part: String, ignoreCase: Boolean, partName: String): Int {
   if (part.length() == 0) {
      return index;
   } else {
      var i: Int = 0;

      for (int var8 = part.length(); i < var8; i++) {
         if (!CharsKt.equals(part.charAt(i), `$this$checkContainsAt`.charAt(index + i), ignoreCase)) {
            throwNotContainedAt(`$this$checkContainsAt`, index, endIndex, part, partName);
         }
      }

      return index + part.length();
   }
}

private inline fun String.decimalFromHexDigitAt(index: Int): Int {
   val code: Int = `$this$decimalFromHexDigitAt`.charAt(index);
   if (code ushr 8 == 0 && HEX_DIGITS_TO_DECIMAL[code] >= 0) {
      return HEX_DIGITS_TO_DECIMAL[code];
   } else {
      throwInvalidDigitAt(`$this$decimalFromHexDigitAt`, index);
      throw new KotlinNothingValueException();
   }
}

private inline fun String.longDecimalFromHexDigitAt(index: Int): Long {
   val code: Int = `$this$longDecimalFromHexDigitAt`.charAt(index);
   if (code ushr 8 == 0 && HEX_DIGITS_TO_LONG_DECIMAL[code] >= 0L) {
      return HEX_DIGITS_TO_LONG_DECIMAL[code];
   } else {
      throwInvalidDigitAt(`$this$longDecimalFromHexDigitAt`, index);
      throw new KotlinNothingValueException();
   }
}

private fun String.throwInvalidNumberOfDigits(startIndex: Int, endIndex: Int, specifier: String, expected: Int) {
   val var10000: java.lang.String = `$this$throwInvalidNumberOfDigits`.substring(startIndex, endIndex);
   throw new NumberFormatException(
      "Expected $specifier $expected hexadecimal digits at index $startIndex, but was \"$var10000\" of length ${endIndex - startIndex}"
   );
}

private fun String.throwNotContainedAt(index: Int, endIndex: Int, part: String, partName: String) {
   val var7: Int = RangesKt.coerceAtMost(index + part.length(), endIndex);
   val var10000: java.lang.String = `$this$throwNotContainedAt`.substring(index, var7);
   throw new NumberFormatException("Expected $partName \"$part\" at index $index, but was $var10000");
}

private fun String.throwInvalidPrefixSuffix(startIndex: Int, endIndex: Int, prefix: String, suffix: String) {
   val var10000: java.lang.String = `$this$throwInvalidPrefixSuffix`.substring(startIndex, endIndex);
   throw new NumberFormatException("Expected a hexadecimal number with prefix \"$prefix\" and suffix \"$suffix\", but was $var10000");
}

private fun String.throwInvalidDigitAt(index: Int): Nothing {
   throw new NumberFormatException("Expected a hexadecimal digit at index $index, but was ${`$this$throwInvalidDigitAt`.charAt(index)}");
}
