@file:SourceDebugExtension(["SMAP\nBase64.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Base64.kt\nio/ktor/util/Base64Kt\n+ 2 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 5 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,130:1\n126#1:134\n126#1:135\n129#1:149\n21#2,3:131\n21#2,2:137\n23#2:143\n21#2,2:144\n23#2:151\n1#3:136\n389#4,4:139\n13359#5,3:146\n13362#5:150\n*S KotlinDebug\n*F\n+ 1 Base64.kt\nio/ktor/util/Base64Kt\n*L\n53#1:134\n69#1:135\n115#1:149\n29#1:131,3\n99#1:137,2\n99#1:143\n108#1:144,2\n108#1:151\n100#1:139,4\n114#1:146,3\n114#1:150\n*E\n"])

package io.ktor.util

import io.ktor.utils.io.core.InputKt
import io.ktor.utils.io.core.StringsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.SourcesKt

private const val BASE64_ALPHABET: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
private const val BASE64_MASK: Byte = 63
private const val BASE64_MASK_INT: Int = 63
private const val BASE64_PAD: Char = '='
private final val BASE64_INVERSE_ALPHABET: IntArray

public fun String.encodeBase64(): String {
   val `builder$iv`: Buffer = new Buffer();
   StringsKt.writeText$default(`builder$iv`, `$this$encodeBase64`, 0, 0, null, 14, null);
   return encodeBase64(`builder$iv`);
}

public fun ByteArray.encodeBase64(): String {
   val array: ByteArray = `$this$encodeBase64`;
   var position: Int = 0;
   var writeOffset: Int = 0;
   val charArray: CharArray = new char[`$this$encodeBase64`.length * 8 / 6 + 3];

   while (position + 3 <= array.length) {
      val remaining: Int = array[position];
      val chunk: Int = array[position + 1];
      val padSize: Int = array[position + 2];
      position += 3;
      val index: Int = (remaining and 255) shl 16 or (chunk and 255) shl 8 or padSize and 255;

      for (int indexx = 3; -1 < indexx; indexx--) {
         charArray[writeOffset++] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(index shr 6 * indexx and 63);
      }
   }

   val var13: Int = array.length - position;
   if (array.length - position == 0) {
      return kotlin.text.StringsKt.concatToString(charArray, 0, writeOffset);
   } else {
      val var14: Int = if (var13 == 1)
         (array[position] and 255) shl 16 or 0 or 0
         else
         (array[position] and 255) shl 16 or (array[position + 1] and 255) shl 8 or 0;
      val var15: Int = (3 - var13) * 8 / 6;
      var var16: Int = 3;
      if (var15 <= 3) {
         while (true) {
            charArray[writeOffset++] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(var14 shr 6 * var16 and 63);
            if (var16 == var15) {
               break;
            }

            var16--;
         }
      }

      for (int var17 = 0; var17 < padSize; var17++) {
         charArray[writeOffset++] = '=';
      }

      return kotlin.text.StringsKt.concatToString(charArray, 0, writeOffset);
   }
}

public fun Source.encodeBase64(): String {
   return encodeBase64(SourcesKt.readByteArray(`$this$encodeBase64`));
}

public fun String.decodeBase64String(): String {
   val bytes: ByteArray = decodeBase64Bytes(`$this$decodeBase64String`);
   return kotlin.text.StringsKt.decodeToString$default(bytes, 0, 0 + bytes.length, false, 4, null);
}

public fun String.decodeBase64Bytes(): ByteArray {
   val `builder$iv`: Buffer = new Buffer();
   val `$this$decodeBase64Bytes_u24lambda_u240`: Sink = `builder$iv`;
   val `$this$dropLastWhile$iv`: java.lang.String = `$this$decodeBase64Bytes`;
   var `index$iv`: Int = kotlin.text.StringsKt.getLastIndex(`$this$decodeBase64Bytes`);

   var var10000: java.lang.String;
   while (true) {
      if (-1 >= `index$iv`) {
         var10000 = "";
         break;
      }

      if (`$this$dropLastWhile$iv`.charAt(`index$iv`) != '=') {
         var10000 = `$this$dropLastWhile$iv`.substring(0, `index$iv` + 1);
         break;
      }

      `index$iv`--;
   }

   StringsKt.writeText$default(`$this$decodeBase64Bytes_u24lambda_u240`, var10000, 0, 0, null, 14, null);
   return SourcesKt.readByteArray(decodeBase64Bytes(`builder$iv`));
}

public fun Source.decodeBase64Bytes(): Source {
   val `builder$iv`: Buffer = new Buffer();
   val `$this$decodeBase64Bytes_u24lambda_u241`: Sink = `builder$iv`;
   val data: ByteArray = new byte[4];

   while (!$this$decodeBase64Bytes.exhausted()) {
      val read: Int = InputKt.readAvailable$default(`$this$decodeBase64Bytes`, data, 0, 0, 6, null);
      var `index$iv`: Int = 0;
      var `accumulator$iv`: Int = 0;

      for (byte element$iv : data) {
         `accumulator$iv` |= (byte)((byte)access$getBASE64_INVERSE_ALPHABET$p()[`element$iv` and 255] and 63) shl (3 - `index$iv`++) * 6;
      }

      val chunk: Int = `accumulator$iv`;
      var var22: Int = data.length - 2;
      val var23: Int = data.length - read;
      if (data.length - read <= var22) {
         while (true) {
            `$this$decodeBase64Bytes_u24lambda_u241`.writeByte((byte)(chunk shr 8 * var22 and 255));
            if (var22 == var23) {
               break;
            }

            var22--;
         }
      }
   }

   return `builder$iv`;
}

internal inline fun Int.toBase64(): Char {
   return "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(`$this$toBase64`);
}

internal inline fun Byte.fromBase64(): Byte {
   return (byte)((byte)access$getBASE64_INVERSE_ALPHABET$p()[`$this$fromBase64` and 255] and 63);
}

@JvmSynthetic
fun `access$getBASE64_INVERSE_ALPHABET$p`(): IntArray {
   return BASE64_INVERSE_ALPHABET;
}
