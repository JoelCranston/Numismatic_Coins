package kotlin.uuid

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nUuid.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Uuid.kt\nkotlin/uuid/UuidKt__UuidKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,648:1\n1#2:649\n*E\n"])
internal class UuidKt__UuidKt : UuidKt__UuidJVMKt {
   @ExperimentalUuidApi
   @JvmStatic
   internal fun uuidFromRandomBytes(randomBytes: ByteArray): Uuid {
      randomBytes[6] = (byte)(randomBytes[6] and 15);
      randomBytes[6] = (byte)(randomBytes[6] or 64);
      randomBytes[8] = (byte)(randomBytes[8] and 63);
      randomBytes[8] = (byte)(randomBytes[8] or 128);
      return Uuid.Companion.fromByteArray(randomBytes);
   }

   @JvmStatic
   internal fun ByteArray.getLongAtCommonImpl(index: Int): Long {
      return (`$this$getLongAtCommonImpl`[index + 0] and 255L) shl 56 or (`$this$getLongAtCommonImpl`[index + 1] and 255L) shl 48 or (
         `$this$getLongAtCommonImpl`[index + 2] and 255L
      ) shl 40 or (`$this$getLongAtCommonImpl`[index + 3] and 255L) shl 32 or (`$this$getLongAtCommonImpl`[index + 4] and 255L) shl 24 or (
         `$this$getLongAtCommonImpl`[index + 5] and 255L
      ) shl 16 or (`$this$getLongAtCommonImpl`[index + 6] and 255L) shl 8 or `$this$getLongAtCommonImpl`[index + 7] and 255L;
   }

   @ExperimentalUuidApi
   @JvmStatic
   internal fun Long.formatBytesIntoCommonImpl(dst: ByteArray, dstOffset: Int, startIndex: Int, endIndex: Int) {
      var dstIndex: Int = dstOffset;
      var reversedIndex: Int = 7 - startIndex;
      val var8: Int = 8 - endIndex;
      if (8 - endIndex <= reversedIndex) {
         while (true) {
            val byteDigits: Int = HexExtensionsKt.getBYTE_TO_LOWER_CASE_HEX_DIGITS()[(int)(`$this$formatBytesIntoCommonImpl` shr (reversedIndex shl 3) and 255L)];
            dst[dstIndex++] = (byte)(byteDigits shr 8);
            dst[dstIndex++] = (byte)byteDigits;
            if (reversedIndex == var8) {
               break;
            }

            reversedIndex--;
         }
      }
   }

   @JvmStatic
   internal fun String.checkHyphenAt(index: Int) {
      if (`$this$checkHyphenAt`.charAt(index) != '-') {
         throw new IllegalArgumentException(("Expected '-' (hyphen) at index $index, but was '${`$this$checkHyphenAt`.charAt(index)}'").toString());
      }
   }

   @JvmStatic
   internal fun ByteArray.setLongAtCommonImpl(index: Int, value: Long) {
      var i: Int = index;

      for (int reversedIndex = 7; -1 < reversedIndex; reversedIndex--) {
         `$this$setLongAtCommonImpl`[i++] = (byte)(value shr (reversedIndex shl 3));
      }
   }

   @ExperimentalUuidApi
   @JvmStatic
   internal fun uuidParseHexDashCommonImpl(hexDashString: String): Uuid {
      val part1: Long = HexExtensionsKt.hexToLong$default(hexDashString, 0, 8, null, 4, null);
      UuidKt.checkHyphenAt(hexDashString, 8);
      val part2: Long = HexExtensionsKt.hexToLong$default(hexDashString, 9, 13, null, 4, null);
      UuidKt.checkHyphenAt(hexDashString, 13);
      val part3: Long = HexExtensionsKt.hexToLong$default(hexDashString, 14, 18, null, 4, null);
      UuidKt.checkHyphenAt(hexDashString, 18);
      val part4: Long = HexExtensionsKt.hexToLong$default(hexDashString, 19, 23, null, 4, null);
      UuidKt.checkHyphenAt(hexDashString, 23);
      return Uuid.Companion
         .fromLongs(part1 shl 32 or part2 shl 16 or part3, part4 shl 48 or HexExtensionsKt.hexToLong$default(hexDashString, 24, 36, null, 4, null));
   }

   @ExperimentalUuidApi
   @JvmStatic
   internal fun uuidParseHexCommonImpl(hexString: String): Uuid {
      return Uuid.Companion
         .fromLongs(HexExtensionsKt.hexToLong$default(hexString, 0, 16, null, 4, null), HexExtensionsKt.hexToLong$default(hexString, 16, 32, null, 4, null));
   }

   @JvmStatic
   private fun String.truncateForErrorMessage(maxLength: Int): String {
      val var10000: java.lang.String;
      if (`$this$truncateForErrorMessage`.length() <= maxLength) {
         var10000 = `$this$truncateForErrorMessage`;
      } else {
         val var4: StringBuilder = new StringBuilder();
         val var10001: java.lang.String = `$this$truncateForErrorMessage`.substring(0, maxLength);
         var10000 = var4.append(var10001).append("...").toString();
      }

      return var10000;
   }

   @JvmStatic
   private fun ByteArray.truncateForErrorMessage(maxSize: Int): String {
      return ArraysKt.joinToString$default(`$this$truncateForErrorMessage`, null, "[", "]", maxSize, null, null, 49, null);
   }

   open fun UuidKt__UuidKt() {
   }
}
