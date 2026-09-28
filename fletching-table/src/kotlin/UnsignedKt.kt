@file:JvmName(name = "UnsignedKt")

package kotlin

import kotlin.internal.InlineOnly
import kotlin.jvm.internal.Intrinsics

@PublishedApi
internal fun uintRemainder(v1: UInt, v2: UInt): UInt {
   return UInt.constructor-impl((int)(((long)var0 and 4294967295L) % ((long)var1 and 4294967295L)));
}

@PublishedApi
internal fun uintDivide(v1: UInt, v2: UInt): UInt {
   return UInt.constructor-impl((int)(((long)var0 and 4294967295L) / ((long)var1 and 4294967295L)));
}

@PublishedApi
internal fun ulongDivide(v1: ULong, v2: ULong): ULong {
   label21:
   if (var2 < 0L) {
      return if (java.lang.Long.compareUnsigned(var0, var2) < 0) ULong.constructor-impl(0L) else ULong.constructor-impl(1L);
   } else {
      return if (var0 >= 0L)
         ULong.constructor-impl(var0 / var2)
         else
         ULong.constructor-impl(
            ((var0 ushr 1) / var2 shl 1)
               + (long)(
                  if (java.lang.Long.compareUnsigned(ULong.constructor-impl(var0 - ((var0 ushr 1) / var2 shl 1) * var2), ULong.constructor-impl(var2)) >= 0)
                     1
                     else
                     0
               )
         );
   }
}

@PublishedApi
internal fun ulongRemainder(v1: ULong, v2: ULong): ULong {
   label21:
   if (var2 < 0L) {
      return if (java.lang.Long.compareUnsigned(var0, var2) < 0) var0 else ULong.constructor-impl(var0 - var2);
   } else {
      return if (var0 >= 0L)
         ULong.constructor-impl(var0 % var2)
         else
         ULong.constructor-impl(
            var0
               - ((var0 ushr 1) / var2 shl 1) * var2
               - (
                  if (java.lang.Long.compareUnsigned(ULong.constructor-impl(var0 - ((var0 ushr 1) / var2 shl 1) * var2), ULong.constructor-impl(var2)) >= 0)
                     var2
                     else
                     0L
               )
         );
   }
}

@PublishedApi
internal fun uintCompare(v1: Int, v2: Int): Int {
   return Intrinsics.compare(v1 xor Integer.MIN_VALUE, v2 xor Integer.MIN_VALUE);
}

@PublishedApi
internal fun ulongCompare(v1: Long, v2: Long): Int {
   return Intrinsics.compare(v1 xor java.lang.Long.MIN_VALUE, v2 xor java.lang.Long.MIN_VALUE);
}

@PublishedApi
@InlineOnly
internal inline fun uintToULong(value: Int): ULong {
   return ULong.constructor-impl((long)value and 4294967295L);
}

@PublishedApi
@InlineOnly
internal inline fun uintToLong(value: Int): Long {
   return value and 4294967295L;
}

@PublishedApi
@InlineOnly
internal inline fun uintToFloat(value: Int): Float {
   return (float)uintToDouble(value);
}

@PublishedApi
@InlineOnly
internal inline fun floatToUInt(value: Float): UInt {
   return doubleToUInt((double)value);
}

@PublishedApi
internal fun uintToDouble(value: Int): Double {
   return (value and Integer.MAX_VALUE) + (double)((value ushr 31) shl 30) * 2;
}

@PublishedApi
internal fun doubleToUInt(value: Double): UInt {
   return if (java.lang.Double.isNaN(value))
      0
      else
      (
         if (value <= uintToDouble(0))
            0
            else
            (
               if (value >= uintToDouble(-1))
                  -1
                  else
                  (
                     if (value <= 2.147483647E9)
                        UInt.constructor-impl((int)value)
                        else
                        UInt.constructor-impl(UInt.constructor-impl((int)(value - (double)Integer.MAX_VALUE)) + UInt.constructor-impl(Integer.MAX_VALUE))
                  )
            )
      );
}

@PublishedApi
@InlineOnly
internal inline fun ulongToFloat(value: Long): Float {
   return (float)ulongToDouble(value);
}

@PublishedApi
@InlineOnly
internal inline fun floatToULong(value: Float): ULong {
   return doubleToULong((double)value);
}

@PublishedApi
internal fun ulongToDouble(value: Long): Double {
   return (double)(value ushr 11) * 2048 + (value and 2047L);
}

@PublishedApi
internal fun doubleToULong(value: Double): ULong {
   return if (java.lang.Double.isNaN(value))
      0L
      else
      (
         if (value <= ulongToDouble(0L))
            0L
            else
            (
               if (value >= ulongToDouble(-1L))
                  -1L
                  else
                  (
                     if (value < 9.223372E18F)
                        ULong.constructor-impl((long)value)
                        else
                        ULong.constructor-impl(ULong.constructor-impl((long)(value - 9.223372E18F)) + java.lang.Long.MIN_VALUE)
                  )
            )
      );
}

@InlineOnly
internal inline fun uintToString(value: Int): String {
   return java.lang.String.valueOf((long)value and 4294967295L);
}

@InlineOnly
internal inline fun uintToString(value: Int, base: Int): String {
   return ulongToString((long)value and 4294967295L, base);
}

@InlineOnly
internal inline fun ulongToString(value: Long): String {
   return ulongToString(value, 10);
}

internal fun ulongToString(value: Long, base: Int): String {
   if (value >= 0L) {
      val var8: java.lang.String = java.lang.Long.toString(value, CharsKt.checkRadix(base));
      return var8;
   } else {
      var quotient: Long = (value ushr 1) / base shl 1;
      var rem: Long = value - ((value ushr 1) / base shl 1) * base;
      if (value - ((value ushr 1) / base shl 1) * base >= base) {
         rem -= base;
         quotient++;
      }

      var var10000: StringBuilder = new StringBuilder();
      var var10001: java.lang.String = java.lang.Long.toString(quotient, CharsKt.checkRadix(base));
      var10000 = var10000.append(var10001);
      var10001 = java.lang.Long.toString(rem, CharsKt.checkRadix(base));
      return var10000.append(var10001).toString();
   }
}
