package kotlin.text

import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nStringNumberConversionsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringNumberConversionsJVM.kt\nkotlin/text/StringsKt__StringNumberConversionsJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,512:1\n267#1,7:513\n267#1,7:520\n267#1,7:527\n267#1,7:534\n1#2:541\n*S KotlinDebug\n*F\n+ 1 StringNumberConversionsJVM.kt\nkotlin/text/StringsKt__StringNumberConversionsJVMKt\n*L\n166#1:513,7\n173#1:520,7\n253#1:527,7\n264#1:534,7\n*E\n"])
internal class StringsKt__StringNumberConversionsJVMKt : StringsKt__StringBuilderKt {
   @SinceKotlin(version = "1.1")
   @InlineOnly
   @JvmStatic
   public inline fun Byte.toString(radix: Int): String {
      val var10000: java.lang.String = Integer.toString(`$this$toString`, CharsKt.checkRadix(radix));
      return var10000;
   }

   @SinceKotlin(version = "1.1")
   @InlineOnly
   @JvmStatic
   public inline fun Short.toString(radix: Int): String {
      val var10000: java.lang.String = Integer.toString(`$this$toString`, CharsKt.checkRadix(radix));
      return var10000;
   }

   @SinceKotlin(version = "1.1")
   @InlineOnly
   @JvmStatic
   public inline fun Int.toString(radix: Int): String {
      val var10000: java.lang.String = Integer.toString(`$this$toString`, CharsKt.checkRadix(radix));
      return var10000;
   }

   @SinceKotlin(version = "1.1")
   @InlineOnly
   @JvmStatic
   public inline fun Long.toString(radix: Int): String {
      val var10000: java.lang.String = java.lang.Long.toString(`$this$toString`, CharsKt.checkRadix(radix));
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun String?.toBoolean(): Boolean {
      return java.lang.Boolean.parseBoolean(`$this$toBoolean`);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.toByte(): Byte {
      return java.lang.Byte.parseByte(`$this$toByte`);
   }

   @SinceKotlin(version = "1.1")
   @InlineOnly
   @JvmStatic
   public inline fun String.toByte(radix: Int): Byte {
      return java.lang.Byte.parseByte(`$this$toByte`, CharsKt.checkRadix(radix));
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.toShort(): Short {
      return java.lang.Short.parseShort(`$this$toShort`);
   }

   @SinceKotlin(version = "1.1")
   @InlineOnly
   @JvmStatic
   public inline fun String.toShort(radix: Int): Short {
      return java.lang.Short.parseShort(`$this$toShort`, CharsKt.checkRadix(radix));
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.toInt(): Int {
      return Integer.parseInt(`$this$toInt`);
   }

   @SinceKotlin(version = "1.1")
   @InlineOnly
   @JvmStatic
   public inline fun String.toInt(radix: Int): Int {
      return Integer.parseInt(`$this$toInt`, CharsKt.checkRadix(radix));
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.toLong(): Long {
      return java.lang.Long.parseLong(`$this$toLong`);
   }

   @SinceKotlin(version = "1.1")
   @InlineOnly
   @JvmStatic
   public inline fun String.toLong(radix: Int): Long {
      return java.lang.Long.parseLong(`$this$toLong`, CharsKt.checkRadix(radix));
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.toFloat(): Float {
      return java.lang.Float.parseFloat(`$this$toFloat`);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.toDouble(): Double {
      return java.lang.Double.parseDouble(`$this$toDouble`);
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun String.toFloatOrNull(): Float? {
      val `str$iv`: java.lang.String = `$this$toFloatOrNull`;

      var p0: java.lang.Float;
      try {
         p0 = if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(`str$iv`)) java.lang.Float.parseFloat(`str$iv`) else null;
      } catch (var5: NumberFormatException) {
         p0 = null;
      }

      return p0;
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun String.toDoubleOrNull(): Double? {
      val `str$iv`: java.lang.String = `$this$toDoubleOrNull`;

      var p0: java.lang.Double;
      try {
         p0 = if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(`str$iv`)) java.lang.Double.parseDouble(`str$iv`) else null;
      } catch (var5: NumberFormatException) {
         p0 = null;
      }

      return p0;
   }

   @SinceKotlin(version = "1.2")
   @InlineOnly
   @JvmStatic
   public inline fun String.toBigInteger(): BigInteger {
      return new BigInteger(`$this$toBigInteger`);
   }

   @SinceKotlin(version = "1.2")
   @InlineOnly
   @JvmStatic
   public inline fun String.toBigInteger(radix: Int): BigInteger {
      return new BigInteger(`$this$toBigInteger`, CharsKt.checkRadix(radix));
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun String.toBigIntegerOrNull(): BigInteger? {
      return StringsKt.toBigIntegerOrNull(`$this$toBigIntegerOrNull`, 10);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun String.toBigIntegerOrNull(radix: Int): BigInteger? {
      CharsKt.checkRadix(radix);
      val length: Int = `$this$toBigIntegerOrNull`.length();
      switch (length) {
         case 0:
            return null;
         case 1:
            if (CharsKt.digitOf(`$this$toBigIntegerOrNull`.charAt(0), radix) < 0) {
               return null;
            }
            break;
         default:
            for (int index = $this$toBigIntegerOrNull.charAt(0) == '-' ? 1 : 0; index < length; index++) {
               if (CharsKt.digitOf(`$this$toBigIntegerOrNull`.charAt(index), radix) < 0) {
                  return null;
               }
            }
      }

      return new BigInteger(`$this$toBigIntegerOrNull`, CharsKt.checkRadix(radix));
   }

   @SinceKotlin(version = "1.2")
   @InlineOnly
   @JvmStatic
   public inline fun String.toBigDecimal(): BigDecimal {
      return new BigDecimal(`$this$toBigDecimal`);
   }

   @SinceKotlin(version = "1.2")
   @InlineOnly
   @JvmStatic
   public inline fun String.toBigDecimal(mathContext: MathContext): BigDecimal {
      return new BigDecimal(`$this$toBigDecimal`, mathContext);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun String.toBigDecimalOrNull(): BigDecimal? {
      val `str$iv`: java.lang.String = `$this$toBigDecimalOrNull`;

      var it: BigDecimal;
      try {
         it = if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(`str$iv`)) new BigDecimal(`str$iv`) else null;
      } catch (var5: NumberFormatException) {
         it = null;
      }

      return it;
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun String.toBigDecimalOrNull(mathContext: MathContext): BigDecimal? {
      val `str$iv`: java.lang.String = `$this$toBigDecimalOrNull`;

      var var6: BigDecimal;
      try {
         var6 = if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(`str$iv`)) new BigDecimal(`str$iv`, mathContext) else null;
      } catch (var7: NumberFormatException) {
         var6 = null;
      }

      return var6;
   }

   @JvmStatic
   private inline fun <T> screenFloatValue(str: String, parse: (String) -> T): T? {
      var var3: Any;
      try {
         var3 = if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(str)) parse.invoke(str) else null;
      } catch (var5: NumberFormatException) {
         var3 = null;
      }

      return (T)var3;
   }

   @JvmStatic
   private fun isValidFloat(s: String): Boolean {
      var endInclusive: Int = s.length() - 1;
      var isHex: java.lang.String = s;

      var l: Int;
      for (l = 0; l <= endInclusive; l++) {
         if (isHex.charAt(l) > ' ') {
            break;
         }
      }

      var var14: Int = l;
      if (l > endInclusive) {
         return false;
      } else {
         isHex = s;

         for (l = endInclusive; l > var14; l--) {
            if (isHex.charAt(l) > ' ') {
               break;
            }
         }

         endInclusive = l;
         if (s.charAt(var14) == '+' || s.charAt(var14) == '-') {
            var14++;
         }

         if (var14 > l) {
            return false;
         } else {
            var var20: Boolean = false;
            if (s.charAt(var14) == '0') {
               if (++var14 > l) {
                  return true;
               }

               if ((s.charAt(var14) or 32) == 120) {
                  var14++;
                  val var8: java.lang.String = s;

                  var var9: Int;
                  for (var9 = var14; var9 <= endInclusive; var9++) {
                     val it: Char = var8.charAt(var9);
                     if ((it - '0' and '\uffff') >= 10 && ((it or 32) - 97 and '\uffff') >= 6) {
                        break;
                     }
                  }

                  var var27: Int = var9;
                  val var34: Boolean = var14 != var9;
                  val var10000: Int;
                  if (var9 > endInclusive) {
                     var10000 = -1;
                  } else {
                     var var38: Boolean = false;
                     if (s.charAt(var9) == '.') {
                        val it: Int = ++var9;
                        val var41: java.lang.String = s;

                        var var45: Int;
                        for (var45 = var27; var45 <= endInclusive; var45++) {
                           val itx: Char = var41.charAt(var45);
                           if ((itx - '0' and '\uffff') >= 10 && ((itx or 32) - 97 and '\uffff') >= 6) {
                              break;
                           }
                        }

                        var27 = var45;
                        var38 = it != var45;
                     }

                     var10000 = if (!var34 && !var38) -1 else var27;
                  }

                  var14 = var10000;
                  if (var10000 == -1 || var10000 > endInclusive) {
                     return false;
                  }

                  var20 = true;
               } else {
                  var14--;
               }
            }

            if (!var20) {
               val var35: java.lang.String = s;

               var var39: Int;
               for (var39 = var14; var39 <= endInclusive; var39++) {
                  if ((var35.charAt(var39) - '0' and '\uffff') >= 10) {
                     break;
                  }
               }

               var var29: Int = var39;
               val var36: Boolean = var14 != var39;
               val var50: Int;
               if (var39 > endInclusive) {
                  var50 = var39;
               } else {
                  var var40: Boolean = false;
                  if (s.charAt(var39) == '.') {
                     val var32: Int = ++var39;
                     val var43: java.lang.String = s;

                     var var47: Int;
                     for (var47 = var29; var47 <= endInclusive; var47++) {
                        if ((var43.charAt(var47) - '0' and '\uffff') >= 10) {
                           break;
                        }
                     }

                     var29 = var47;
                     var40 = var32 != var47;
                  }

                  var50 = if (!var36 && !var40)
                     (
                        if ((if (endInclusive == var29 + 3 - 1) "NaN" else (if (endInclusive == var29 + 8 - 1) "Infinity" else null)) == null)
                           -1
                           else
                           (
                              if (StringsKt.indexOf(
                                       s, if (endInclusive == var29 + 3 - 1) "NaN" else (if (endInclusive == var29 + 8 - 1) "Infinity" else null), var29, false
                                    )
                                    == var29)
                                 endInclusive + 1
                                 else
                                 -1
                           )
                     )
                     else
                     var29;
               }

               var14 = var50;
               if (var50 == -1) {
                  return false;
               }

               if (var50 > endInclusive) {
                  return true;
               }
            }

            l = s.charAt(var14++) or 32;
            if (l != (if (var20) 112 else 101)) {
               return !var20 && (l == 102 || l == 100) && var14 > endInclusive;
            } else if (var14 > endInclusive) {
               return false;
            } else {
               if (s.charAt(var14) == '+' || s.charAt(var14) == '-') {
                  if (++var14 > endInclusive) {
                     return false;
                  }
               }

               val var25: java.lang.String = s;

               var var31: Int;
               for (var31 = var14; var31 <= endInclusive; var31++) {
                  if ((var25.charAt(var31) - '0' and '\uffff') >= 10) {
                     break;
                  }
               }

               if (var31 > endInclusive) {
                  return true;
               } else if (var31 != endInclusive) {
                  return false;
               } else {
                  l = s.charAt(var31) or 32;
                  return l == 102 || l == 100;
               }
            }
         }
      }
   }

   @InlineOnly
   @JvmStatic
   private inline fun guessNamedFloatConstant(start: Int, endInclusive: Int): String? {
      return if (endInclusive == start + 3 - 1) "NaN" else (if (endInclusive == start + 8 - 1) "Infinity" else null);
   }

   @InlineOnly
   @JvmStatic
   private inline fun Char.isAsciiDigit(): Boolean {
      return (`$this$isAsciiDigit` - '0' and '\uffff') < 10;
   }

   @InlineOnly
   @JvmStatic
   private inline fun Char.isHexLetter(): Boolean {
      return ((`$this$isHexLetter` or 32) - 97 and '\uffff') < 6;
   }

   @InlineOnly
   @JvmStatic
   private inline fun Char.asciiLetterToLowerCaseCode(): Int {
      return `$this$asciiLetterToLowerCaseCode` or 32;
   }

   @InlineOnly
   @JvmStatic
   private inline fun String.advanceWhile(start: Int, endInclusive: Int, predicate: (Char) -> Boolean): Int {
      var startx: Int = start;

      while (startx <= endInclusive && predicate.invoke($this$advanceWhile.charAt(startx))) {
         startx++;
      }

      return startx;
   }

   @InlineOnly
   @JvmStatic
   private inline fun String.backtrackWhile(start: Int, endInclusive: Int, predicate: (Char) -> Boolean): Int {
      var endInclusivex: Int = endInclusive;

      while (endInclusivex > start && predicate.invoke($this$backtrackWhile.charAt(endInclusivex))) {
         endInclusivex--;
      }

      return endInclusivex;
   }

   @InlineOnly
   @JvmStatic
   private inline fun String.advanceAndValidateMantissa(start: Int, endInclusive: Int, hexFormat: Boolean, predicate: (Char) -> Boolean): Int {
      val hasIntegerPart: java.lang.String = `$this$advanceAndValidateMantissa`;
      var hasFractionalPart: Int = start;

      while (hasFractionalPart <= endInclusive && predicate.invoke(hasIntegerPart.charAt(hasFractionalPart))) {
         hasFractionalPart++;
      }

      var startx: Int = hasFractionalPart;
      val var12: Boolean = start != hasFractionalPart;
      if (hasFractionalPart > endInclusive) {
         return if (hexFormat) -1 else hasFractionalPart;
      } else {
         var var13: Boolean = false;
         if (`$this$advanceAndValidateMantissa`.charAt(hasFractionalPart) == '.') {
            val checkpoint: Int = ++hasFractionalPart;
            val constant: java.lang.String = `$this$advanceAndValidateMantissa`;
            var var10: Int = startx;

            while (var10 <= endInclusive && predicate.invoke(constant.charAt(var10))) {
               var10++;
            }

            startx = var10;
            var13 = checkpoint != var10;
         }

         if (var12 || var13) {
            return startx;
         } else if (hexFormat) {
            return -1;
         } else {
            val var14: java.lang.String = if (endInclusive == startx + 3 - 1) "NaN" else (if (endInclusive == startx + 8 - 1) "Infinity" else null);
            if ((if (endInclusive == startx + 3 - 1) "NaN" else (if (endInclusive == startx + 8 - 1) "Infinity" else null)) == null) {
               return -1;
            } else {
               return if (StringsKt.indexOf(`$this$advanceAndValidateMantissa`, var14, startx, false) == startx) endInclusive + 1 else -1;
            }
         }
      }
   }

   open fun StringsKt__StringNumberConversionsJVMKt() {
   }
}
