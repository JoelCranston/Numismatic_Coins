package kotlin.text

import kotlin.jvm.internal.Intrinsics

internal class StringsKt__StringNumberConversionsKt : StringsKt__StringNumberConversionsJVMKt {
   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun String.toByteOrNull(): Byte? {
      return StringsKt.toByteOrNull(`$this$toByteOrNull`, 10);
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun String.toByteOrNull(radix: Int): Byte? {
      val var10000: Int = StringsKt.toIntOrNull(`$this$toByteOrNull`, radix);
      if (var10000 != null) {
         val var2: Int = var10000;
         return if (var2 >= -128 && var2 <= 127) (byte)var2 else null;
      } else {
         return null;
      }
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun String.toShortOrNull(): Short? {
      return StringsKt.toShortOrNull(`$this$toShortOrNull`, 10);
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun String.toShortOrNull(radix: Int): Short? {
      val var10000: Int = StringsKt.toIntOrNull(`$this$toShortOrNull`, radix);
      if (var10000 != null) {
         val var2: Int = var10000;
         return if (var2 >= -32768 && var2 <= 32767) (short)var2 else null;
      } else {
         return null;
      }
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun String.toIntOrNull(): Int? {
      return StringsKt.toIntOrNull(`$this$toIntOrNull`, 10);
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun String.toIntOrNull(radix: Int): Int? {
      CharsKt.checkRadix(radix);
      val length: Int = `$this$toIntOrNull`.length();
      if (length == 0) {
         return null;
      } else {
         val firstChar: Char = `$this$toIntOrNull`.charAt(0);
         val var12: Byte;
         var var13: Boolean;
         var var14: Int;
         if (Intrinsics.compare(firstChar, 48) < 0) {
            if (length == 1) {
               return null;
            }

            var12 = 1;
            switch (firstChar) {
               case '+':
                  var13 = false;
                  var14 = -2147483647;
                  break;
               case ',':
               default:
                  return null;
               case '-':
                  var13 = true;
                  var14 = Integer.MIN_VALUE;
            }
         } else {
            var12 = 0;
            var13 = false;
            var14 = -2147483647;
         }

         val limitForMaxRadix: Int = -59652323;
         var limitBeforeMul: Int = -59652323;
         var result: Int = 0;

         for (int i = var12; i < length; i++) {
            val digit: Int = CharsKt.digitOf(`$this$toIntOrNull`.charAt(i), radix);
            if (digit < 0) {
               return null;
            }

            if (result < limitBeforeMul) {
               if (limitBeforeMul != limitForMaxRadix) {
                  return null;
               }

               limitBeforeMul = var14 / radix;
               if (result < var14 / radix) {
                  return null;
               }
            }

            result = result * radix;
            if (result * radix < var14 + digit) {
               return null;
            }

            result = result - digit;
         }

         return if (var13) result else -result;
      }
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun String.toLongOrNull(): Long? {
      return StringsKt.toLongOrNull(`$this$toLongOrNull`, 10);
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun String.toLongOrNull(radix: Int): Long? {
      CharsKt.checkRadix(radix);
      val length: Int = `$this$toLongOrNull`.length();
      if (length == 0) {
         return null;
      } else {
         val firstChar: Char = `$this$toLongOrNull`.charAt(0);
         val var16: Byte;
         var var17: Boolean;
         var var18: Long;
         if (Intrinsics.compare(firstChar, 48) < 0) {
            if (length == 1) {
               return null;
            }

            var16 = 1;
            switch (firstChar) {
               case '+':
                  var17 = false;
                  var18 = -9223372036854775807L;
                  break;
               case ',':
               default:
                  return null;
               case '-':
                  var17 = true;
                  var18 = java.lang.Long.MIN_VALUE;
            }
         } else {
            var16 = 0;
            var17 = false;
            var18 = -9223372036854775807L;
         }

         val limitForMaxRadix: Long = -256204778801521550L;
         var limitBeforeMul: Long = -256204778801521550L;
         var result: Long = 0L;

         for (int i = var16; i < length; i++) {
            val digit: Int = CharsKt.digitOf(`$this$toLongOrNull`.charAt(i), radix);
            if (digit < 0) {
               return null;
            }

            if (result < limitBeforeMul) {
               if (limitBeforeMul != limitForMaxRadix) {
                  return null;
               }

               limitBeforeMul = var18 / radix;
               if (result < var18 / radix) {
                  return null;
               }
            }

            result = result * radix;
            if (result * radix < var18 + digit) {
               return null;
            }

            result = result - digit;
         }

         return if (var17) result else -result;
      }
   }

   @JvmStatic
   internal fun numberFormatError(input: String): Nothing {
      throw new NumberFormatException("Invalid number format: '$input'");
   }

   open fun StringsKt__StringNumberConversionsKt() {
   }
}
