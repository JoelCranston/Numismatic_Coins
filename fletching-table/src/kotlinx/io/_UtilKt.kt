@file:SourceDebugExtension(["SMAP\n-Util.kt\nKotlin\n*S Kotlin\n*F\n+ 1 -Util.kt\nkotlinx/io/_UtilKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,184:1\n89#1:186\n95#1:187\n1#2:185\n*S KotlinDebug\n*F\n+ 1 -Util.kt\nkotlinx/io/_UtilKt\n*L\n114#1:186\n115#1:187\n*E\n"])

package kotlinx.io

import kotlin.jvm.internal.SourceDebugExtension

internal final val HEX_DIGIT_CHARS: CharArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'}

internal fun checkOffsetAndCount(size: Long, offset: Long, byteCount: Long) {
   if (offset < 0L || offset > size || size - offset < byteCount || byteCount < 0L) {
      throw new IllegalArgumentException("offset ($offset) and byteCount ($byteCount) are not within the range [0..size($size))");
   }
}

internal inline fun checkBounds(size: Int, startIndex: Int, endIndex: Int) {
   checkBounds((long)size, (long)startIndex, (long)endIndex);
}

internal fun checkBounds(size: Long, startIndex: Long, endIndex: Long) {
   if (startIndex < 0L || endIndex > size) {
      throw new IndexOutOfBoundsException("startIndex ($startIndex) and endIndex ($endIndex) are not within the range [0..size($size))");
   } else if (startIndex > endIndex) {
      throw new IllegalArgumentException("startIndex ($startIndex) > endIndex ($endIndex)");
   }
}

internal inline fun checkByteCount(byteCount: Long) {
   if (byteCount < 0L) {
      throw new IllegalArgumentException(("byteCount ($byteCount) < 0").toString());
   }
}

internal inline fun Short.reverseBytesCommon(): Short {
   return (short)((`$this$reverseBytesCommon` and '\uffff' and '\uff00') ushr 8 or (`$this$reverseBytesCommon` and '\uffff' and 255) shl 8);
}

internal inline fun Int.reverseBytesCommon(): Int {
   return (`$this$reverseBytesCommon` and -16777216) ushr 24 or (`$this$reverseBytesCommon` and 16711680) ushr 8 or (`$this$reverseBytesCommon` and 65280) shl 8 or (
      `$this$reverseBytesCommon` and 255
   ) shl 24;
}

internal inline fun Long.reverseBytesCommon(): Long {
   return (`$this$reverseBytesCommon` and -72057594037927936L) ushr 56 or (`$this$reverseBytesCommon` and 71776119061217280L) ushr 40 or (
      `$this$reverseBytesCommon` and 280375465082880L
   ) ushr 24 or (`$this$reverseBytesCommon` and 1095216660480L) ushr 8 or (`$this$reverseBytesCommon` and 4278190080L) shl 8 or (
      `$this$reverseBytesCommon` and 16711680L
   ) shl 24 or (`$this$reverseBytesCommon` and 65280L) shl 40 or (`$this$reverseBytesCommon` and 255L) shl 56;
}

internal inline infix fun Byte.shr(other: Int): Int {
   return `$this$shr` shr other;
}

internal inline infix fun Byte.shl(other: Int): Int {
   return `$this$shl` shl other;
}

internal inline infix fun Byte.and(other: Int): Int {
   return `$this$and` and other;
}

internal inline infix fun Byte.and(other: Long): Long {
   return `$this$and` and other;
}

internal inline infix fun Byte.xor(other: Byte): Byte {
   return (byte)(`$this$xor` xor other);
}

internal inline infix fun Int.and(other: Long): Long {
   return `$this$and` and other;
}

internal inline fun minOf(a: Long, b: Int): Long {
   return Math.min(a, (long)b);
}

internal inline fun minOf(a: Int, b: Long): Long {
   return Math.min((long)a, b);
}

internal fun Byte.toHexString(): String {
   return StringsKt.concatToString(new char[]{HEX_DIGIT_CHARS[`$this$toHexString` shr 4 and 15], HEX_DIGIT_CHARS[`$this$toHexString` and 15]});
}

internal fun Int.toHexString(): String {
   if (`$this$toHexString` == 0) {
      return "0";
   } else {
      val result: CharArray = new char[]{
         HEX_DIGIT_CHARS[`$this$toHexString` shr 28 and 15],
         HEX_DIGIT_CHARS[`$this$toHexString` shr 24 and 15],
         HEX_DIGIT_CHARS[`$this$toHexString` shr 20 and 15],
         HEX_DIGIT_CHARS[`$this$toHexString` shr 16 and 15],
         HEX_DIGIT_CHARS[`$this$toHexString` shr 12 and 15],
         HEX_DIGIT_CHARS[`$this$toHexString` shr 8 and 15],
         HEX_DIGIT_CHARS[`$this$toHexString` shr 4 and 15],
         HEX_DIGIT_CHARS[`$this$toHexString` and 15]
      };
      var i: Int = 0;

      while (i < result.length && result[i] == '0') {
         i++;
      }

      return StringsKt.concatToString(result, i, result.length);
   }
}

internal fun Long.toHexString(): String {
   if (`$this$toHexString` == 0L) {
      return "0";
   } else {
      val result: CharArray = new char[]{
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 60 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 56 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 52 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 48 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 44 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 40 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 36 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 32 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 28 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 24 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 20 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 16 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 12 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 8 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` shr 4 and 15L)],
         HEX_DIGIT_CHARS[(int)(`$this$toHexString` and 15L)]
      };
      var i: Int = 0;

      while (i < result.length && result[i] == '0') {
         i++;
      }

      return StringsKt.concatToString(result, i, result.length);
   }
}

internal inline fun hexNumberLength(v: Long): Int {
   return if (v == 0L) 1 else (64 - java.lang.Long.numberOfLeadingZeros(v) + 3) / 4;
}
