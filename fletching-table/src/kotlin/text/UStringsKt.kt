@file:JvmName(name = "UStringsKt")

package kotlin.text

import kotlin.jvm.internal.Intrinsics

@SinceKotlin(version = "1.5")
public fun UByte.toString(radix: Int): String {
   val var10000: java.lang.String = Integer.toString(var0 and 255, CharsKt.checkRadix(radix));
   return var10000;
}

@SinceKotlin(version = "1.5")
public fun UShort.toString(radix: Int): String {
   val var10000: java.lang.String = Integer.toString(var0 and '\uffff', CharsKt.checkRadix(radix));
   return var10000;
}

@SinceKotlin(version = "1.5")
public fun UInt.toString(radix: Int): String {
   return UnsignedKt.ulongToString((long)var0 and 4294967295L, CharsKt.checkRadix(radix));
}

@SinceKotlin(version = "1.5")
public fun ULong.toString(radix: Int): String {
   return UnsignedKt.ulongToString(var0, CharsKt.checkRadix(radix));
}

@SinceKotlin(version = "1.5")
public fun String.toUByte(): UByte {
   val var10000: UByte = toUByteOrNull(`$this$toUByte`);
   if (var10000 != null) {
      return var10000.unbox-impl();
   } else {
      StringsKt.numberFormatError(`$this$toUByte`);
      throw new KotlinNothingValueException();
   }
}

@SinceKotlin(version = "1.5")
public fun String.toUByte(radix: Int): UByte {
   val var10000: UByte = toUByteOrNull(`$this$toUByte`, radix);
   if (var10000 != null) {
      return var10000.unbox-impl();
   } else {
      StringsKt.numberFormatError(`$this$toUByte`);
      throw new KotlinNothingValueException();
   }
}

@SinceKotlin(version = "1.5")
public fun String.toUShort(): UShort {
   val var10000: UShort = toUShortOrNull(`$this$toUShort`);
   if (var10000 != null) {
      return var10000.unbox-impl();
   } else {
      StringsKt.numberFormatError(`$this$toUShort`);
      throw new KotlinNothingValueException();
   }
}

@SinceKotlin(version = "1.5")
public fun String.toUShort(radix: Int): UShort {
   val var10000: UShort = toUShortOrNull(`$this$toUShort`, radix);
   if (var10000 != null) {
      return var10000.unbox-impl();
   } else {
      StringsKt.numberFormatError(`$this$toUShort`);
      throw new KotlinNothingValueException();
   }
}

@SinceKotlin(version = "1.5")
public fun String.toUInt(): UInt {
   val var10000: UInt = toUIntOrNull(`$this$toUInt`);
   if (var10000 != null) {
      return var10000.unbox-impl();
   } else {
      StringsKt.numberFormatError(`$this$toUInt`);
      throw new KotlinNothingValueException();
   }
}

@SinceKotlin(version = "1.5")
public fun String.toUInt(radix: Int): UInt {
   val var10000: UInt = toUIntOrNull(`$this$toUInt`, radix);
   if (var10000 != null) {
      return var10000.unbox-impl();
   } else {
      StringsKt.numberFormatError(`$this$toUInt`);
      throw new KotlinNothingValueException();
   }
}

@SinceKotlin(version = "1.5")
public fun String.toULong(): ULong {
   val var10000: ULong = toULongOrNull(`$this$toULong`);
   if (var10000 != null) {
      return var10000.unbox-impl();
   } else {
      StringsKt.numberFormatError(`$this$toULong`);
      throw new KotlinNothingValueException();
   }
}

@SinceKotlin(version = "1.5")
public fun String.toULong(radix: Int): ULong {
   val var10000: ULong = toULongOrNull(`$this$toULong`, radix);
   if (var10000 != null) {
      return var10000.unbox-impl();
   } else {
      StringsKt.numberFormatError(`$this$toULong`);
      throw new KotlinNothingValueException();
   }
}

@SinceKotlin(version = "1.5")
public fun String.toUByteOrNull(): UByte? {
   return toUByteOrNull(`$this$toUByteOrNull`, 10);
}

@SinceKotlin(version = "1.5")
public fun String.toUByteOrNull(radix: Int): UByte? {
   val var10000: UInt = toUIntOrNull(`$this$toUByteOrNull`, radix);
   if (var10000 != null) {
      val var2: Int = var10000.unbox-impl();
      return if (Integer.compareUnsigned(var2, UInt.constructor-impl(-1 and 255)) > 0) null else UByte.box-impl(UByte.constructor-impl((byte)var2));
   } else {
      return null;
   }
}

@SinceKotlin(version = "1.5")
public fun String.toUShortOrNull(): UShort? {
   return toUShortOrNull(`$this$toUShortOrNull`, 10);
}

@SinceKotlin(version = "1.5")
public fun String.toUShortOrNull(radix: Int): UShort? {
   val var10000: UInt = toUIntOrNull(`$this$toUShortOrNull`, radix);
   if (var10000 != null) {
      val var2: Int = var10000.unbox-impl();
      return if (Integer.compareUnsigned(var2, UInt.constructor-impl(-1 and '\uffff')) > 0) null else UShort.box-impl(UShort.constructor-impl((short)var2));
   } else {
      return null;
   }
}

@SinceKotlin(version = "1.5")
public fun String.toUIntOrNull(): UInt? {
   return toUIntOrNull(`$this$toUIntOrNull`, 10);
}

@SinceKotlin(version = "1.5")
public fun String.toUIntOrNull(radix: Int): UInt? {
   CharsKt.checkRadix(radix);
   val length: Int = `$this$toUIntOrNull`.length();
   if (length == 0) {
      return null;
   } else {
      val limit: Int = -1;
      val firstChar: Char = `$this$toUIntOrNull`.charAt(0);
      val var13: Byte;
      if (Intrinsics.compare(firstChar, 48) < 0) {
         if (length == 1 || firstChar != '+') {
            return null;
         }

         var13 = 1;
      } else {
         var13 = 0;
      }

      val limitForMaxRadix: Int = 119304647;
      var limitBeforeMul: Int = 119304647;
      val uradix: Int = UInt.constructor-impl(radix);
      var result: Int = 0;

      for (int i = var13; i < length; i++) {
         val digit: Int = CharsKt.digitOf(`$this$toUIntOrNull`.charAt(i), radix);
         if (digit < 0) {
            return null;
         }

         if (Integer.compareUnsigned(result, limitBeforeMul) > 0) {
            if (limitBeforeMul != limitForMaxRadix) {
               return null;
            }

            limitBeforeMul = Integer.divideUnsigned(limit, uradix);
            if (Integer.compareUnsigned(result, limitBeforeMul) > 0) {
               return null;
            }
         }

         result = UInt.constructor-impl(result * uradix);
         result = UInt.constructor-impl(result + UInt.constructor-impl(digit));
         if (Integer.compareUnsigned(result, result) < 0) {
            return null;
         }
      }

      return UInt.box-impl(result);
   }
}

@SinceKotlin(version = "1.5")
public fun String.toULongOrNull(): ULong? {
   return toULongOrNull(`$this$toULongOrNull`, 10);
}

@SinceKotlin(version = "1.5")
public fun String.toULongOrNull(radix: Int): ULong? {
   CharsKt.checkRadix(radix);
   val length: Int = `$this$toULongOrNull`.length();
   if (length == 0) {
      return null;
   } else {
      val limit: Long = -1L;
      val firstChar: Char = `$this$toULongOrNull`.charAt(0);
      val var20: Byte;
      if (Intrinsics.compare(firstChar, 48) < 0) {
         if (length == 1 || firstChar != '+') {
            return null;
         }

         var20 = 1;
      } else {
         var20 = 0;
      }

      val limitForMaxRadix: Long = 512409557603043100L;
      var limitBeforeMul: Long = 512409557603043100L;
      val uradix: Long = ULong.constructor-impl((long)radix);
      var result: Long = 0L;

      for (int i = var20; i < length; i++) {
         val digit: Int = CharsKt.digitOf(`$this$toULongOrNull`.charAt(i), radix);
         if (digit < 0) {
            return null;
         }

         if (java.lang.Long.compareUnsigned(result, limitBeforeMul) > 0) {
            if (limitBeforeMul != limitForMaxRadix) {
               return null;
            }

            limitBeforeMul = java.lang.Long.divideUnsigned(limit, uradix);
            if (java.lang.Long.compareUnsigned(result, limitBeforeMul) > 0) {
               return null;
            }
         }

         result = ULong.constructor-impl(result * uradix);
         result = ULong.constructor-impl(result + ULong.constructor-impl((long)UInt.constructor-impl(digit) and 4294967295L));
         if (java.lang.Long.compareUnsigned(result, result) < 0) {
            return null;
         }
      }

      return ULong.box-impl(result);
   }
}
