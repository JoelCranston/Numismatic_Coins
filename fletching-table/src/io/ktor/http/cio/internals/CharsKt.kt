@file:SourceDebugExtension(["SMAP\nChars.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Chars.kt\nio/ktor/http/cio/internals/CharsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,133:1\n34#1:134\n34#1:135\n1#2:136\n1563#3:137\n1634#3,3:138\n1563#3:141\n1634#3,3:142\n*S KotlinDebug\n*F\n+ 1 Chars.kt\nio/ktor/http/cio/internals/CharsKt\n*L\n15#1:134\n26#1:135\n39#1:137\n39#1:138,3\n48#1:141\n48#1:142,3\n*E\n"])

package io.ktor.http.cio.internals

import io.ktor.http.HttpMethod
import io.ktor.http.cio.internals.CharsKt.writeIntHex.1
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension

internal const val HTAB: Char = '\t'
internal final val DefaultHttpMethods: AsciiCharTree<HttpMethod> =
   AsciiCharTree.Companion.build(HttpMethod.Companion.getDefaultMethods(), CharsKt::DefaultHttpMethods$lambda$0, CharsKt::DefaultHttpMethods$lambda$1)
   private final val HexTable: LongArray
internal final val HexLetterTable: ByteArray

internal fun CharSequence.hashCodeLowerCase(start: Int = 0, end: Int = `$this$hashCodeLowerCase`.length()): Int {
   var hashCode: Int = 0;

   for (int pos = start; pos < end; pos++) {
      val `$this$toLowerCase$iv`: Char = `$this$hashCodeLowerCase`.charAt(pos);
      hashCode = 31 * hashCode
         + (if ('A' <= `$this$toLowerCase$iv` && `$this$toLowerCase$iv` < '[') 97 + (`$this$toLowerCase$iv` - 'A') else `$this$toLowerCase$iv`);
   }

   return hashCode;
}

@JvmSynthetic
fun `hashCodeLowerCase$default`(var0: java.lang.CharSequence, var1: Int, var2: Int, var3: Int, var4: Any): Int {
   if ((var3 and 1) != 0) {
      var1 = 0;
   }

   if ((var3 and 2) != 0) {
      var2 = var0.length();
   }

   return hashCodeLowerCase(var0, var1, var2);
}

internal fun CharSequence.equalsLowerCase(start: Int = 0, end: Int = `$this$equalsLowerCase`.length(), other: CharSequence): Boolean {
   if (end - start != other.length()) {
      return false;
   } else {
      for (int pos = start; pos < end; pos++) {
         var `$this$toLowerCase$iv`: Int = `$this$equalsLowerCase`.charAt(pos);
         val var10000: Int = if (65 <= `$this$toLowerCase$iv` && `$this$toLowerCase$iv` < 91) 97 + (`$this$toLowerCase$iv` - 65) else `$this$toLowerCase$iv`;
         `$this$toLowerCase$iv` = other.charAt(pos - start);
         if (var10000 != (if (65 <= `$this$toLowerCase$iv` && `$this$toLowerCase$iv` < 91) 97 + (`$this$toLowerCase$iv` - 65) else `$this$toLowerCase$iv`)) {
            return false;
         }
      }

      return true;
   }
}

@JvmSynthetic
fun `equalsLowerCase$default`(var0: java.lang.CharSequence, var1: Int, var2: Int, var3: java.lang.CharSequence, var4: Int, var5: Any): Boolean {
   if ((var4 and 1) != 0) {
      var1 = 0;
   }

   if ((var4 and 2) != 0) {
      var2 = var0.length();
   }

   return equalsLowerCase(var0, var1, var2, var3);
}

private inline fun Int.toLowerCase(): Int {
   return if (65 <= `$this$toLowerCase` && `$this$toLowerCase` < 91) 97 + (`$this$toLowerCase` - 65) else `$this$toLowerCase`;
}

internal fun CharSequence.parseHexLong(): Long {
   var result: Long = 0L;
   val table: LongArray = HexTable;
   var i: Int = 0;

   for (int var5 = $this$parseHexLong.length(); i < var5; i++) {
      val v: Int = `$this$parseHexLong`.charAt(i) and '\uffff';
      val digit: Long = if (v < 255) table[v] else -1L;
      if ((if (v < 255) table[v] else -1L) == -1L) {
         hexNumberFormatException(`$this$parseHexLong`, i);
         throw new KotlinNothingValueException();
      }

      result = result shl 4 or digit;
   }

   return result;
}

public fun CharSequence.parseDecLong(): Long {
   val length: Int = `$this$parseDecLong`.length();
   if (length > 19) {
      numberFormatException(`$this$parseDecLong`);
   }

   if (length == 19) {
      return parseDecLongWithCheck(`$this$parseDecLong`);
   } else {
      var result: Long = 0L;

      for (int i = 0; i < length; i++) {
         val digit: Long = `$this$parseDecLong`.charAt(i) - 48L;
         if (digit < 0L || digit > 9L) {
            numberFormatException(`$this$parseDecLong`, i);
         }

         result = (result shl 3) + (result shl 1) + digit;
      }

      return result;
   }
}

private fun CharSequence.parseDecLongWithCheck(): Long {
   var result: Long = 0L;
   var i: Int = 0;

   for (int var4 = $this$parseDecLongWithCheck.length(); i < var4; i++) {
      val digit: Long = `$this$parseDecLongWithCheck`.charAt(i) - 48L;
      if (digit < 0L || digit > 9L) {
         numberFormatException(`$this$parseDecLongWithCheck`, i);
      }

      result = (result shl 3) + (result shl 1) + digit;
      if (result < 0L) {
         numberFormatException(`$this$parseDecLongWithCheck`);
      }
   }

   return result;
}

internal suspend fun ByteWriteChannel.writeIntHex(value: Int) {
   var `$continuation`: Continuation;
   label58: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label58;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   var current: Int;
   var table: ByteArray;
   var digits: Int;
   var var9: Any;
   val `$result`: Any = `$continuation`.result;
   var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   label51:
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (value <= 0) {
            throw new IllegalArgumentException("Does only work for positive numbers".toString());
         }

         current = value;
         table = HexLetterTable;
         digits = 0;

         while (digits++ < 8) {
            val vx: Int = current ushr 28;
            current <<= 4;
            if (vx != 0) {
               val var10001: Byte = HexLetterTable[vx];
               `$continuation`.L$0 = `$this$writeIntHex`;
               `$continuation`.L$1 = table;
               `$continuation`.I$0 = value;
               `$continuation`.I$1 = current;
               `$continuation`.I$2 = digits;
               `$continuation`.I$3 = vx;
               `$continuation`.label = 1;
               if (ByteWriteChannelOperationsKt.writeByte(`$this$writeIntHex`, var10001, `$continuation`) === var9) {
                  return var9;
               }
               break label51;
            }
         }
         break;
      case 1: {
         val var12: Int = `$continuation`.I$3;
         digits = `$continuation`.I$2;
         current = `$continuation`.I$1;
         value = `$continuation`.I$0;
         table = `$continuation`.L$1 as ByteArray;
         `$this$writeIntHex` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      }
      case 2: {
         val v: Int = `$continuation`.I$3;
         digits = `$continuation`.I$2;
         current = `$continuation`.I$1;
         value = `$continuation`.I$0;
         table = `$continuation`.L$1 as ByteArray;
         `$this$writeIntHex` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      }
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (digits++ < 8) {
      val var14: Int = current ushr 28;
      current <<= 4;
      val var16: Byte = table[var14];
      `$continuation`.L$0 = `$this$writeIntHex`;
      `$continuation`.L$1 = table;
      `$continuation`.I$0 = value;
      `$continuation`.I$1 = current;
      `$continuation`.I$2 = digits;
      `$continuation`.I$3 = var14;
      `$continuation`.label = 2;
      if (ByteWriteChannelOperationsKt.writeByte(`$this$writeIntHex`, var16, `$continuation`) === var9) {
         return var9;
      }
   }

   return Unit.INSTANCE;
}

private fun hexNumberFormatException(s: CharSequence, idx: Int): Nothing {
   throw new NumberFormatException("Invalid HEX number: $s, wrong digit: ${s.charAt(idx)}");
}

private fun numberFormatException(cs: CharSequence, idx: Int) {
   throw new NumberFormatException("Invalid number: $cs, wrong digit: ${cs.charAt(idx)} at position $idx");
}

private fun numberFormatException(cs: CharSequence) {
   throw new NumberFormatException("Invalid number $cs: too large for Long type");
}

fun `DefaultHttpMethods$lambda$0`(it: HttpMethod): Int {
   return it.getValue().length();
}

fun `DefaultHttpMethods$lambda$1`(m: HttpMethod, idx: Int): Char {
   return m.getValue().charAt(idx);
}
