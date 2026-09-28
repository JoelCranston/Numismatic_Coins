package kotlin.text

import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.CodingErrorAction
import java.util.Arrays
import java.util.Comparator
import java.util.Locale
import java.util.regex.Pattern
import kotlin.String.Companion
import kotlin.internal.InlineOnly
import kotlin.internal.LowPriorityInOverloadResolution
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nStringsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringsJVM.kt\nkotlin/text/StringsKt__StringsJVMKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,885:1\n1179#2,2:886\n1#3:888\n*S KotlinDebug\n*F\n+ 1 StringsJVM.kt\nkotlin/text/StringsKt__StringsJVMKt\n*L\n73#1:886,2\n*E\n"])
internal class StringsKt__StringsJVMKt : StringsKt__StringNumberConversionsKt {
   public final val CASE_INSENSITIVE_ORDER: Comparator<String>
      public final get() {
         val var10000: Comparator = java.lang.String.CASE_INSENSITIVE_ORDER;
         return var10000;
      }


   @InlineOnly
   @JvmStatic
   internal inline fun String.nativeIndexOf(ch: Char, fromIndex: Int): Int {
      return `$this$nativeIndexOf`.indexOf(ch, fromIndex);
   }

   @InlineOnly
   @JvmStatic
   internal inline fun String.nativeIndexOf(str: String, fromIndex: Int): Int {
      return `$this$nativeIndexOf`.indexOf(str, fromIndex);
   }

   @InlineOnly
   @JvmStatic
   internal inline fun String.nativeLastIndexOf(ch: Char, fromIndex: Int): Int {
      return `$this$nativeLastIndexOf`.lastIndexOf(ch, fromIndex);
   }

   @InlineOnly
   @JvmStatic
   internal inline fun String.nativeLastIndexOf(str: String, fromIndex: Int): Int {
      return `$this$nativeLastIndexOf`.lastIndexOf(str, fromIndex);
   }

   @JvmStatic
   public fun String?.equals(other: String?, ignoreCase: Boolean = false): Boolean {
      if (`$this$equals` == null) {
         return other == null;
      } else {
         return if (!ignoreCase) `$this$equals`.equals(other) else `$this$equals`.equalsIgnoreCase(other);
      }
   }

   @JvmStatic
   public fun String.replace(oldChar: Char, newChar: Char, ignoreCase: Boolean = false): String {
      if (!ignoreCase) {
         val var10000: java.lang.String = `$this$replace`.replace(oldChar, newChar);
         return var10000;
      } else {
         val var5: StringBuilder = new StringBuilder(`$this$replace`.length());
         val `$this$replace_u24lambda_u240`: StringBuilder = var5;
         val `$this$forEach$iv`: java.lang.CharSequence = `$this$replace`;

         for (int var10 = 0; var10 < $this$forEach$iv.length(); var10++) {
            val `element$iv`: Char = `$this$forEach$iv`.charAt(var10);
            `$this$replace_u24lambda_u240`.append(if (CharsKt.equals(`element$iv`, oldChar, ignoreCase)) newChar else `element$iv`);
         }

         return var5.toString();
      }
   }

   @JvmStatic
   public fun String.replace(oldValue: String, newValue: String, ignoreCase: Boolean = false): String {
      val `$this$replace_u24lambda_u241`: java.lang.String = `$this$replace`;
      var occurrenceIndex: Int = StringsKt.indexOf(`$this$replace`, oldValue, 0, ignoreCase);
      if (occurrenceIndex < 0) {
         return `$this$replace`;
      } else {
         val oldValueLength: Int = oldValue.length();
         val searchStep: Int = RangesKt.coerceAtLeast(oldValueLength, 1);
         val newLengthHint: Int = `$this$replace`.length() - oldValueLength + newValue.length();
         if (newLengthHint < 0) {
            throw new OutOfMemoryError();
         } else {
            val stringBuilder: StringBuilder = new StringBuilder(newLengthHint);
            var i: Int = 0;

            do {
               stringBuilder.append(`$this$replace_u24lambda_u241`, i, occurrenceIndex).append(newValue);
               i = occurrenceIndex + oldValueLength;
               if (occurrenceIndex >= `$this$replace_u24lambda_u241`.length()) {
                  break;
               }

               occurrenceIndex = StringsKt.indexOf(`$this$replace_u24lambda_u241`, oldValue, occurrenceIndex + searchStep, ignoreCase);
            } while (occurrenceIndex > 0);

            val var10000: java.lang.String = stringBuilder.append(`$this$replace_u24lambda_u241`, i, `$this$replace_u24lambda_u241`.length()).toString();
            return var10000;
         }
      }
   }

   @JvmStatic
   public fun String.replaceFirst(oldChar: Char, newChar: Char, ignoreCase: Boolean = false): String {
      val index: Int = StringsKt.indexOf$default(`$this$replaceFirst`, oldChar, 0, ignoreCase, 2, null);
      return if (index < 0)
         `$this$replaceFirst`
         else
         StringsKt.replaceRange(`$this$replaceFirst`, index, index + 1, java.lang.String.valueOf(newChar)).toString();
   }

   @JvmStatic
   public fun String.replaceFirst(oldValue: String, newValue: String, ignoreCase: Boolean = false): String {
      val index: Int = StringsKt.indexOf$default(`$this$replaceFirst`, oldValue, 0, ignoreCase, 2, null);
      return if (index < 0) `$this$replaceFirst` else StringsKt.replaceRange(`$this$replaceFirst`, index, index + oldValue.length(), newValue).toString();
   }

   @Deprecated(message = "Use uppercase() instead.", replaceWith = @ReplaceWith(expression = "uppercase(Locale.getDefault())", imports = ["java.util.Locale"]))
   @DeprecatedSinceKotlin(warningSince = "1.5", errorSince = "2.1")
   @InlineOnly
   @JvmStatic
   public inline fun String.toUpperCase(): String {
      val var10000: java.lang.String = `$this$toUpperCase`.toUpperCase();
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun String.uppercase(): String {
      val var10000: java.lang.String = `$this$uppercase`.toUpperCase(Locale.ROOT);
      return var10000;
   }

   @Deprecated(message = "Use lowercase() instead.", replaceWith = @ReplaceWith(expression = "lowercase(Locale.getDefault())", imports = ["java.util.Locale"]))
   @DeprecatedSinceKotlin(warningSince = "1.5", errorSince = "2.1")
   @InlineOnly
   @JvmStatic
   public inline fun String.toLowerCase(): String {
      val var10000: java.lang.String = `$this$toLowerCase`.toLowerCase();
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun String.lowercase(): String {
      val var10000: java.lang.String = `$this$lowercase`.toLowerCase(Locale.ROOT);
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharArray.concatToString(): String {
      return new java.lang.String(`$this$concatToString`);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharArray.concatToString(startIndex: Int = 0, endIndex: Int = `$this$concatToString`.length): String {
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, `$this$concatToString`.length);
      return new java.lang.String(`$this$concatToString`, startIndex, endIndex - startIndex);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun String.toCharArray(startIndex: Int = 0, endIndex: Int = `$this$toCharArray`.length()): CharArray {
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, `$this$toCharArray`.length());
      val var4: CharArray = new char[endIndex - startIndex];
      `$this$toCharArray`.getChars(startIndex, endIndex, var4, 0);
      return var4;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.decodeToString(): String {
      return new java.lang.String(`$this$decodeToString`, Charsets.UTF_8);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.decodeToString(startIndex: Int = 0, endIndex: Int = `$this$decodeToString`.length, throwOnInvalidSequence: Boolean = false): String {
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, `$this$decodeToString`.length);
      if (!throwOnInvalidSequence) {
         return new java.lang.String(`$this$decodeToString`, startIndex, endIndex - startIndex, Charsets.UTF_8);
      } else {
         val var10000: java.lang.String = Charsets.UTF_8
            .newDecoder()
            .onMalformedInput(CodingErrorAction.REPORT)
            .onUnmappableCharacter(CodingErrorAction.REPORT)
            .decode(ByteBuffer.wrap(`$this$decodeToString`, startIndex, endIndex - startIndex))
            .toString();
         return var10000;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun String.encodeToByteArray(): ByteArray {
      val var10000: ByteArray = `$this$encodeToByteArray`.getBytes(Charsets.UTF_8);
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun String.encodeToByteArray(startIndex: Int = 0, endIndex: Int = `$this$encodeToByteArray`.length(), throwOnInvalidSequence: Boolean = false): ByteArray {
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, `$this$encodeToByteArray`.length());
      if (!throwOnInvalidSequence) {
         val var13: java.lang.String = `$this$encodeToByteArray`.substring(startIndex, endIndex);
         val var10: Charset = Charsets.UTF_8;
         val var14: ByteArray = var13.getBytes(var10);
         return var14;
      } else {
         val byteBuffer: ByteBuffer = Charsets.UTF_8
            .newEncoder()
            .onMalformedInput(CodingErrorAction.REPORT)
            .onUnmappableCharacter(CodingErrorAction.REPORT)
            .encode(CharBuffer.wrap(`$this$encodeToByteArray`, startIndex, endIndex));
         if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            val var10000: Int = byteBuffer.remaining();
            val var10001: ByteArray = byteBuffer.array();
            if (var10000 == var10001.length) {
               val var11: ByteArray = byteBuffer.array();
               return var11;
            }
         }

         val var6: ByteArray = new byte[byteBuffer.remaining()];
         byteBuffer.get(var6);
         return var6;
      }
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.toCharArray(): CharArray {
      val var10000: CharArray = `$this$toCharArray`.toCharArray();
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.toCharArray(destination: CharArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = `$this$toCharArray`.length()): CharArray {
      `$this$toCharArray`.getChars(startIndex, endIndex, destination, destinationOffset);
      return destination;
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.format(vararg args: Any?): String {
      val var10000: java.lang.String = java.lang.String.format(`$this$format`, Arrays.copyOf(args, args.length));
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun Companion.format(format: String, vararg args: Any?): String {
      val var10000: java.lang.String = java.lang.String.format(format, Arrays.copyOf(args, args.length));
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun String.format(locale: Locale?, vararg args: Any?): String {
      val var10000: java.lang.String = java.lang.String.format(locale, `$this$format`, Arrays.copyOf(args, args.length));
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun Companion.format(locale: Locale?, format: String, vararg args: Any?): String {
      val var10000: java.lang.String = java.lang.String.format(locale, format, Arrays.copyOf(args, args.length));
      return var10000;
   }

   @JvmStatic
   public fun CharSequence.split(regex: Pattern, limit: Int = 0): List<String> {
      StringsKt.requireNonNegativeLimit(limit);
      val var10000: Array<java.lang.String> = regex.split(`$this$split`, if (limit == 0) -1 else limit);
      return ArraysKt.asList(var10000);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.substring(startIndex: Int): String {
      val var10000: java.lang.String = `$this$substring`.substring(startIndex);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.substring(startIndex: Int, endIndex: Int): String {
      val var10000: java.lang.String = `$this$substring`.substring(startIndex, endIndex);
      return var10000;
   }

   @JvmStatic
   public fun String.startsWith(prefix: String, ignoreCase: Boolean = false): Boolean {
      return if (!ignoreCase) `$this$startsWith`.startsWith(prefix) else StringsKt.regionMatches(`$this$startsWith`, 0, prefix, 0, prefix.length(), ignoreCase);
   }

   @JvmStatic
   public fun String.startsWith(prefix: String, startIndex: Int, ignoreCase: Boolean = false): Boolean {
      return if (!ignoreCase)
         `$this$startsWith`.startsWith(prefix, startIndex)
         else
         StringsKt.regionMatches(`$this$startsWith`, startIndex, prefix, 0, prefix.length(), ignoreCase);
   }

   @JvmStatic
   public fun String.endsWith(suffix: String, ignoreCase: Boolean = false): Boolean {
      return if (!ignoreCase)
         `$this$endsWith`.endsWith(suffix)
         else
         StringsKt.regionMatches(`$this$endsWith`, `$this$endsWith`.length() - suffix.length(), suffix, 0, suffix.length(), true);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String(bytes: ByteArray, offset: Int, length: Int, charset: Charset): String {
      return new java.lang.String(bytes, offset, length, charset);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String(bytes: ByteArray, charset: Charset): String {
      return new java.lang.String(bytes, charset);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String(bytes: ByteArray, offset: Int, length: Int): String {
      return new java.lang.String(bytes, offset, length, Charsets.UTF_8);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String(bytes: ByteArray): String {
      return new java.lang.String(bytes, Charsets.UTF_8);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String(chars: CharArray): String {
      return new java.lang.String(chars);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String(chars: CharArray, offset: Int, length: Int): String {
      return new java.lang.String(chars, offset, length);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String(codePoints: IntArray, offset: Int, length: Int): String {
      return new java.lang.String(codePoints, offset, length);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String(stringBuffer: StringBuffer): String {
      return new java.lang.String(stringBuffer);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String(stringBuilder: StringBuilder): String {
      return new java.lang.String(stringBuilder);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.codePointAt(index: Int): Int {
      return `$this$codePointAt`.codePointAt(index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.codePointBefore(index: Int): Int {
      return `$this$codePointBefore`.codePointBefore(index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.codePointCount(beginIndex: Int, endIndex: Int): Int {
      return `$this$codePointCount`.codePointCount(beginIndex, endIndex);
   }

   @JvmStatic
   public fun String.compareTo(other: String, ignoreCase: Boolean = false): Int {
      return if (ignoreCase) `$this$compareTo`.compareToIgnoreCase(other) else `$this$compareTo`.compareTo(other);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.contentEquals(charSequence: CharSequence): Boolean {
      return `$this$contentEquals`.contentEquals(charSequence);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.contentEquals(stringBuilder: StringBuffer): Boolean {
      return `$this$contentEquals`.contentEquals(stringBuilder);
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public infix fun CharSequence?.contentEquals(other: CharSequence?): Boolean {
      return if (`$this$contentEquals` is java.lang.String && other != null)
         (`$this$contentEquals` as java.lang.String).contentEquals(other)
         else
         StringsKt.contentEqualsImpl(`$this$contentEquals`, other);
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun CharSequence?.contentEquals(other: CharSequence?, ignoreCase: Boolean): Boolean {
      return if (ignoreCase) StringsKt.contentEqualsIgnoreCaseImpl(`$this$contentEquals`, other) else StringsKt.contentEquals(`$this$contentEquals`, other);
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.intern(): String {
      val var10000: java.lang.String = `$this$intern`.intern();
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.offsetByCodePoints(index: Int, codePointOffset: Int): Int {
      return `$this$offsetByCodePoints`.offsetByCodePoints(index, codePointOffset);
   }

   @JvmStatic
   public fun CharSequence.regionMatches(thisOffset: Int, other: CharSequence, otherOffset: Int, length: Int, ignoreCase: Boolean = false): Boolean {
      return if (`$this$regionMatches` is java.lang.String && other is java.lang.String)
         StringsKt.regionMatches(`$this$regionMatches` as java.lang.String, thisOffset, other as java.lang.String, otherOffset, length, ignoreCase)
         else
         StringsKt.regionMatchesImpl(`$this$regionMatches`, thisOffset, other, otherOffset, length, ignoreCase);
   }

   @JvmStatic
   public fun String.regionMatches(thisOffset: Int, other: String, otherOffset: Int, length: Int, ignoreCase: Boolean = false): Boolean {
      return if (!ignoreCase)
         `$this$regionMatches`.regionMatches(thisOffset, other, otherOffset, length)
         else
         `$this$regionMatches`.regionMatches(ignoreCase, thisOffset, other, otherOffset, length);
   }

   @Deprecated(message = "Use lowercase() instead.", replaceWith = @ReplaceWith(expression = "lowercase(locale)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5", errorSince = "2.1")
   @InlineOnly
   @JvmStatic
   public inline fun String.toLowerCase(locale: Locale): String {
      val var10000: java.lang.String = `$this$toLowerCase`.toLowerCase(locale);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun String.lowercase(locale: Locale): String {
      val var10000: java.lang.String = `$this$lowercase`.toLowerCase(locale);
      return var10000;
   }

   @Deprecated(message = "Use uppercase() instead.", replaceWith = @ReplaceWith(expression = "uppercase(locale)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5", errorSince = "2.1")
   @InlineOnly
   @JvmStatic
   public inline fun String.toUpperCase(locale: Locale): String {
      val var10000: java.lang.String = `$this$toUpperCase`.toUpperCase(locale);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun String.uppercase(locale: Locale): String {
      val var10000: java.lang.String = `$this$uppercase`.toUpperCase(locale);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.toByteArray(charset: Charset = Charsets.UTF_8): ByteArray {
      val var10000: ByteArray = `$this$toByteArray`.getBytes(charset);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.toPattern(flags: Int = 0): Pattern {
      val var10000: Pattern = Pattern.compile(`$this$toPattern`, flags);
      return var10000;
   }

   @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(expression = "replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }", imports = ["java.util.Locale"]))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public fun String.capitalize(): String {
      val var10001: Locale = Locale.getDefault();
      return StringsKt.capitalize(`$this$capitalize`, var10001);
   }

   @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(expression = "replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @SinceKotlin(version = "1.4")
   @LowPriorityInOverloadResolution
   @JvmStatic
   public fun String.capitalize(locale: Locale): String {
      if (`$this$capitalize`.length() > 0) {
         val firstChar: Char = `$this$capitalize`.charAt(0);
         if (Character.isLowerCase(firstChar)) {
            val var3: StringBuilder = new StringBuilder();
            val titleChar: Char = Character.toTitleCase(firstChar);
            if (titleChar != Character.toUpperCase(firstChar)) {
               var3.append(titleChar);
            } else {
               var var10001: java.lang.String = `$this$capitalize`.substring(0, 1);
               var10001 = var10001.toUpperCase(locale);
               var3.append(var10001);
            }

            val var12: java.lang.String = `$this$capitalize`.substring(1);
            var3.append(var12);
            return var3.toString();
         }
      }

      return `$this$capitalize`;
   }

   @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(expression = "replaceFirstChar { it.lowercase(Locale.getDefault()) }", imports = ["java.util.Locale"]))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public fun String.decapitalize(): String {
      val var10000: java.lang.String;
      if (`$this$decapitalize`.length() > 0 && !Character.isLowerCase(`$this$decapitalize`.charAt(0))) {
         val var6: StringBuilder = new StringBuilder();
         var var10001: java.lang.String = `$this$decapitalize`.substring(0, 1);
         val var8: Locale = Locale.getDefault();
         var10001 = var10001.toLowerCase(var8);
         val var7: StringBuilder = var6.append(var10001);
         var10001 = `$this$decapitalize`.substring(1);
         var10000 = var7.append(var10001).toString();
      } else {
         var10000 = `$this$decapitalize`;
      }

      return var10000;
   }

   @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(expression = "replaceFirstChar { it.lowercase(locale) }", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @SinceKotlin(version = "1.4")
   @LowPriorityInOverloadResolution
   @JvmStatic
   public fun String.decapitalize(locale: Locale): String {
      val var10000: java.lang.String;
      if (`$this$decapitalize`.length() > 0 && !Character.isLowerCase(`$this$decapitalize`.charAt(0))) {
         val var6: StringBuilder = new StringBuilder();
         var var10001: java.lang.String = `$this$decapitalize`.substring(0, 1);
         var10001 = var10001.toLowerCase(locale);
         val var7: StringBuilder = var6.append(var10001);
         var10001 = `$this$decapitalize`.substring(1);
         var10000 = var7.append(var10001).toString();
      } else {
         var10000 = `$this$decapitalize`;
      }

      return var10000;
   }

   @JvmStatic
   public fun CharSequence.repeat(n: Int): String {
      if (n < 0) {
         throw new IllegalArgumentException(("Count 'n' must be non-negative, but was $n.").toString());
      } else {
         var var10000: java.lang.String;
         switch (n) {
            case 0:
               var10000 = "";
               break;
            case 1:
               var10000 = `$this$repeat`.toString();
               break;
            default:
               switch ($this$repeat.length()) {
                  case 0:
                     var10000 = "";
                     break;
                  case 1:
                     val var10: Char = `$this$repeat`.charAt(0);
                     var var6: Int = 0;

                     val var7: CharArray;
                     for (var7 = new char[n]; var6 < n; var6++) {
                        var7[var6] = var10;
                     }

                     var10000 = new java.lang.String(var7);
                     break;
                  default:
                     val sb: StringBuilder = new StringBuilder(n * `$this$repeat`.length());
                     var i: Int = 1;
                     if (1 <= n) {
                        while (true) {
                           sb.append(`$this$repeat`);
                           if (i == n) {
                              break;
                           }

                           i++;
                        }
                     }

                     val var3: java.lang.String = sb.toString();
                     var10000 = var3;
               }
         }

         return var10000;
      }
   }

   open fun StringsKt__StringsJVMKt() {
   }
}
