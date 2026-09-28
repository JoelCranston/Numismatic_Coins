@file:SourceDebugExtension(["SMAP\nCodecs.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Codecs.kt\nio/ktor/http/CodecsKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,322:1\n1104#2,3:323\n13812#3,2:326\n1563#4:328\n1634#4,3:329\n1563#4:332\n1634#4,3:333\n1563#4:336\n1634#4,3:337\n*S KotlinDebug\n*F\n+ 1 Codecs.kt\nio/ktor/http/CodecsKt\n*L\n150#1:323,3\n161#1:326,2\n11#1:328\n11#1:329,3\n22#1:332\n22#1:333,3\n44#1:336\n44#1:337,3\n*E\n"])

package io.ktor.http

import io.ktor.utils.io.charsets.EncodingKt
import io.ktor.utils.io.core.BufferKt
import io.ktor.utils.io.core.ByteReadPacketKt
import io.ktor.utils.io.core.StringsKt
import java.io.Serializable
import java.nio.charset.Charset
import java.nio.charset.CharsetEncoder
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Source

private final val URL_ALPHABET: Set<Byte>
private final val URL_ALPHABET_CHARS: Set<Char>
private final val HEX_ALPHABET: Set<Char>
private final val URL_PROTOCOL_PART: List<Byte>
private final val VALID_PATH_PART: Set<Char>
internal final val ATTRIBUTE_CHARACTERS: Set<Char>
private final val SPECIAL_SYMBOLS: List<Byte>

public fun String.encodeURLQueryComponent(encodeFull: Boolean = false, spaceToPlus: Boolean = false, charset: Charset = Charsets.UTF_8): String {
   val var4: StringBuilder = new StringBuilder();
   val var10000: CharsetEncoder = charset.newEncoder();
   forEach(EncodingKt.encode$default(var10000, `$this$encodeURLQueryComponent`, 0, 0, 6, null), CodecsKt::encodeURLQueryComponent$lambda$0$0);
   return var4.toString();
}

@JvmSynthetic
fun `encodeURLQueryComponent$default`(var0: java.lang.String, var1: Boolean, var2: Boolean, var3: Charset, var4: Int, var5: Any): java.lang.String {
   if ((var4 and 1) != 0) {
      var1 = false;
   }

   if ((var4 and 2) != 0) {
      var2 = false;
   }

   if ((var4 and 4) != 0) {
      var3 = Charsets.UTF_8;
   }

   return encodeURLQueryComponent(var0, var1, var2, var3);
}

public fun String.encodeURLPathPart(): String {
   return encodeURLPath$default(`$this$encodeURLPathPart`, true, false, 2, null);
}

public fun String.encodeURLPath(encodeSlash: Boolean = false, encodeEncoded: Boolean = true): String {
   val var3: StringBuilder = new StringBuilder();
   val `$this$encodeURLPath_u24lambda_u240`: StringBuilder = var3;
   val charset: Charset = Charsets.UTF_8;
   var index: Int = 0;

   while (index < $this$encodeURLPath.length()) {
      val current: Char = `$this$encodeURLPath`.charAt(index);
      if ((encodeSlash || current != '/') && !URL_ALPHABET_CHARS.contains(current) && !VALID_PATH_PART.contains(current)) {
         if (!encodeEncoded
            && current == '%'
            && index + 2 < `$this$encodeURLPath`.length()
            && HEX_ALPHABET.contains(`$this$encodeURLPath`.charAt(index + 1))
            && HEX_ALPHABET.contains(`$this$encodeURLPath`.charAt(index + 2))) {
            `$this$encodeURLPath_u24lambda_u240`.append(current);
            `$this$encodeURLPath_u24lambda_u240`.append(`$this$encodeURLPath`.charAt(index + 1));
            `$this$encodeURLPath_u24lambda_u240`.append(`$this$encodeURLPath`.charAt(index + 2));
            index += 3;
         } else {
            val symbolSize: Int = if (CharsKt.isSurrogate(current)) 2 else 1;
            val var10000: CharsetEncoder = charset.newEncoder();
            forEach(EncodingKt.encode(var10000, `$this$encodeURLPath`, index, index + symbolSize), CodecsKt::encodeURLPath$lambda$0$0);
            index += symbolSize;
         }
      } else {
         `$this$encodeURLPath_u24lambda_u240`.append(current);
         index++;
      }
   }

   return var3.toString();
}

@JvmSynthetic
fun `encodeURLPath$default`(var0: java.lang.String, var1: Boolean, var2: Boolean, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 1) != 0) {
      var1 = false;
   }

   if ((var3 and 2) != 0) {
      var2 = true;
   }

   return encodeURLPath(var0, var1, var2);
}

public fun String.encodeOAuth(): String {
   return encodeURLParameter$default(`$this$encodeOAuth`, false, 1, null);
}

public fun String.encodeURLParameter(spaceToPlus: Boolean = false): String {
   val var2: StringBuilder = new StringBuilder();
   val var10000: CharsetEncoder = Charsets.UTF_8.newEncoder();
   forEach(EncodingKt.encode$default(var10000, `$this$encodeURLParameter`, 0, 0, 6, null), CodecsKt::encodeURLParameter$lambda$0$0);
   return var2.toString();
}

@JvmSynthetic
fun `encodeURLParameter$default`(var0: java.lang.String, var1: Boolean, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = false;
   }

   return encodeURLParameter(var0, var1);
}

internal fun String.percentEncode(allowedSet: Set<Char>): String {
   val content: java.lang.CharSequence = `$this$percentEncode`;
   var resultSize: Int = 0;

   for (int result = 0; result < $this$count$iv.length(); result++) {
      if (!allowedSet.contains(content.charAt(result))) {
         resultSize++;
      }
   }

   if (resultSize == 0) {
      return `$this$percentEncode`;
   } else {
      val var18: ByteArray = StringsKt.toByteArray(`$this$percentEncode`, Charsets.UTF_8);
      val var19: Int = `$this$percentEncode`.length() - resultSize;
      val var21: CharArray = new char[var19 + (var18.length - var19) * 3];
      var var22: Int = 0;

      for (byte element$iv : content) {
         val var15: Char = (char)`element$iv`;
         if (allowedSet.contains((char)`element$iv`)) {
            var21[var22++] = var15;
         } else {
            val var26: Int = `element$iv` and 255;
            var21[var22++] = '%';
            var21[var22++] = hexDigitToChar(var26 shr 4);
            var21[var22++] = hexDigitToChar(var26 and 15);
         }
      }

      return kotlin.text.StringsKt.concatToString(var21);
   }
}

internal fun String.encodeURLParameterValue(): String {
   return encodeURLParameter(`$this$encodeURLParameterValue`, true);
}

public fun String.decodeURLQueryComponent(
   start: Int = 0,
   end: Int = `$this$decodeURLQueryComponent`.length(),
   plusIsSpace: Boolean = false,
   charset: Charset = Charsets.UTF_8
): String {
   return decodeScan(`$this$decodeURLQueryComponent`, start, end, plusIsSpace, charset);
}

@JvmSynthetic
fun `decodeURLQueryComponent$default`(var0: java.lang.String, var1: Int, var2: Int, var3: Boolean, var4: Charset, var5: Int, var6: Any): java.lang.String {
   if ((var5 and 1) != 0) {
      var1 = 0;
   }

   if ((var5 and 2) != 0) {
      var2 = var0.length();
   }

   if ((var5 and 4) != 0) {
      var3 = false;
   }

   if ((var5 and 8) != 0) {
      var4 = Charsets.UTF_8;
   }

   return decodeURLQueryComponent(var0, var1, var2, var3, var4);
}

public fun String.decodeURLPart(start: Int = 0, end: Int = `$this$decodeURLPart`.length(), charset: Charset = Charsets.UTF_8): String {
   return decodeScan(`$this$decodeURLPart`, start, end, false, charset);
}

@JvmSynthetic
fun `decodeURLPart$default`(var0: java.lang.String, var1: Int, var2: Int, var3: Charset, var4: Int, var5: Any): java.lang.String {
   if ((var4 and 1) != 0) {
      var1 = 0;
   }

   if ((var4 and 2) != 0) {
      var2 = var0.length();
   }

   if ((var4 and 4) != 0) {
      var3 = Charsets.UTF_8;
   }

   return decodeURLPart(var0, var1, var2, var3);
}

private fun String.decodeScan(start: Int, end: Int, plusIsSpace: Boolean, charset: Charset): String {
   for (int index = start; index < end; index++) {
      val ch: Char = `$this$decodeScan`.charAt(index);
      if (ch == '%' || plusIsSpace && ch == '+') {
         return decodeImpl(`$this$decodeScan`, start, end, index, plusIsSpace, charset);
      }
   }

   val var10000: java.lang.String;
   if (start == 0 && end == `$this$decodeScan`.length()) {
      var10000 = `$this$decodeScan`.toString();
   } else {
      var10000 = `$this$decodeScan`.substring(start, end);
   }

   return var10000;
}

private fun CharSequence.decodeImpl(start: Int, end: Int, prefixEnd: Int, plusIsSpace: Boolean, charset: Charset): String {
   val sb: StringBuilder = new StringBuilder(if (end - start > 255) (end - start) / 3 else end - start);
   if (prefixEnd > start) {
      sb.append(`$this$decodeImpl`, start, prefixEnd);
   }

   var index: Int = prefixEnd;
   var bytes: ByteArray = null;

   while (index < end) {
      val c: Char = `$this$decodeImpl`.charAt(index);
      if (plusIsSpace && c == '+') {
         sb.append(' ');
         val var19: Serializable = index++;
      } else if (c == '%') {
         if (bytes == null) {
            bytes = new byte[(end - index) / 3];
         }

         var count: Int;
         for (count = 0; index < end && $this$decodeImpl.charAt(index) == '%'; index += 3) {
            if (index + 2 >= end) {
               throw new URLDecodeException(
                  "Incomplete trailing HEX escape: ${`$this$decodeImpl`.subSequence(index, `$this$decodeImpl`.length()).toString()}, in $`$this$decodeImpl` at $index"
               );
            }

            val digit1: Int = charToHexDigit(`$this$decodeImpl`.charAt(index + 1));
            val digit2: Int = charToHexDigit(`$this$decodeImpl`.charAt(index + 2));
            if (digit1 == -1 || digit2 == -1) {
               throw new URLDecodeException(
                  "Wrong HEX escape: %${`$this$decodeImpl`.charAt(index + 1)}${`$this$decodeImpl`.charAt(index + 2)}, in $`$this$decodeImpl`, at $index"
               );
            }

            bytes[count++] = (byte)(digit1 * 16 + digit2);
         }

         val var10000: Serializable = sb.append(kotlin.text.StringsKt.decodeToString$default(bytes, 0, 0 + count, false, 4, null));
      } else {
         sb.append(c);
         val var17: Serializable = index++;
      }
   }

   val var20: java.lang.String = sb.toString();
   return var20;
}

private fun Byte.percentEncode(): String {
   return kotlin.text.StringsKt.concatToString(
      new char[]{'%', hexDigitToChar((`$this$percentEncode` and 255) shr 4), hexDigitToChar(`$this$percentEncode` and 255 and 15)}
   );
}

private fun charToHexDigit(c2: Char): Int {
   return if (48 <= c2 && c2 < 58) c2 - 48 else (if (65 <= c2 && c2 < 71) c2 - 65 + 10 else (if (97 <= c2 && c2 < 103) c2 - 97 + 10 else -1));
}

private fun hexDigitToChar(digit: Int): Char {
   return if (0 <= digit && digit < 10) (char)(48 + digit) else (char)((char)(65 + digit) - '\n');
}

private fun Source.forEach(block: (Byte) -> Unit) {
   ByteReadPacketKt.takeWhile(`$this$forEach`, CodecsKt::forEach$lambda$0);
}

fun `encodeURLQueryComponent$lambda$0$0`(`$spaceToPlus`: Boolean, `$this_buildString`: StringBuilder, `$encodeFull`: Boolean, it: Byte): Unit {
   if (it == 32) {
      if (`$spaceToPlus`) {
         `$this_buildString`.append('+');
      } else {
         `$this_buildString`.append("%20");
      }
   } else if (!URL_ALPHABET.contains(it) && (`$encodeFull` || !URL_PROTOCOL_PART.contains(it))) {
      `$this_buildString`.append(percentEncode(it));
   } else {
      `$this_buildString`.append((char)it);
   }

   return Unit.INSTANCE;
}

fun `encodeURLPath$lambda$0$0`(`$this_buildString`: StringBuilder, it: Byte): Unit {
   `$this_buildString`.append(percentEncode(it));
   return Unit.INSTANCE;
}

fun `encodeURLParameter$lambda$0$0`(`$this_buildString`: StringBuilder, `$spaceToPlus`: Boolean, it: Byte): Unit {
   if (URL_ALPHABET.contains(it) || SPECIAL_SYMBOLS.contains(it)) {
      `$this_buildString`.append((char)it);
   } else if (`$spaceToPlus` && it == 32) {
      `$this_buildString`.append('+');
   } else {
      `$this_buildString`.append(percentEncode(it));
   }

   return Unit.INSTANCE;
}

fun `forEach$lambda$0`(`$block`: Function1, buffer: Buffer): Boolean {
   while (BufferKt.canRead(buffer)) {
      `$block`.invoke(buffer.readByte());
   }

   return true;
}
