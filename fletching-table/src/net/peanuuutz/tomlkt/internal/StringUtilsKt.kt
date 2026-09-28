@file:SourceDebugExtension(["SMAP\nStringUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringUtils.kt\nnet/peanuuutz/tomlkt/internal/StringUtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,261:1\n84#1:262\n1#2:263\n*S KotlinDebug\n*F\n+ 1 StringUtils.kt\nnet/peanuuutz/tomlkt/internal/StringUtilsKt\n*L\n87#1:262\n*E\n"])

package net.peanuuutz.tomlkt.internal

import java.util.Locale
import kotlin.jvm.internal.SourceDebugExtension
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlInteger
import net.peanuuutz.tomlkt.TomlLiteral
import net.peanuuutz.tomlkt.TomlInteger.Base
import net.peanuuutz.tomlkt.internal.StringUtilsKt.processIntegerString.grouped.1

internal const val Comment: Char = '#'
internal const val KeySeparator: Char = '.'
internal const val KeyValueSeparator: Char = '='
internal const val ElementSeparator: Char = ','
internal const val StartTableHead: Char = '['
internal const val EndTableHead: Char = ']'
internal const val StartArray: Char = '['
internal const val EndArray: Char = ']'
internal const val StartInlineTable: Char = '{'
internal const val EndInlineTable: Char = '}'
internal const val DecimalConstraints: String = "0123456789"
internal const val HexadecimalConstraints: String = "0123456789abcdefABCDEF"
internal const val DecimalOrSignConstraints: String = "0123456789-+"
internal const val BareKeyConstraints: String = "abcdefghijklmnopqrstuvwxyz-_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
internal const val DefiniteDateTimeConstraints: String = "Tt:Zz"
internal const val DefiniteNumberConstraints: String = ".acdefABCDEF_"
internal final val BareKeyRegex: Regex = new Regex("[A-Za-z0-9_-]+")
internal final val AsciiMapping: List<String>

internal final val singleQuoted: String
   internal final inline get() {
      return "'$`$this$singleQuoted`'";
   }


internal final val doubleQuoted: String
   internal final inline get() {
      return ""$`$this$doubleQuoted`"";
   }


internal fun String.doubleQuotedIfNotPure(): String {
   return if (BareKeyRegex.matches(`$this$doubleQuotedIfNotPure`)) `$this$doubleQuotedIfNotPure` else ""$`$this$doubleQuotedIfNotPure`"";
}

internal fun Char.escape(multiline: Boolean = false): String {
   return if (`$this$escape` >= 128)
      java.lang.String.valueOf(`$this$escape`)
      else
      (
         if (!multiline)
            AsciiMapping.get(`$this$escape`)
            else
            (
               if (`$this$escape` == '\\')
                  "\\\\"
                  else
                  (
                     if (`$this$escape` == '\t')
                        "\t"
                        else
                        (if (`$this$escape` == '\n') "\n" else (if (`$this$escape` == '\r') "\r" else AsciiMapping.get(`$this$escape`)))
                  )
            )
      );
}

@JvmSynthetic
fun `escape$default`(var0: Char, var1: Boolean, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = false;
   }

   return escape(var0, var1);
}

internal fun String.escape(multiline: Boolean = false): String {
   val builder: StringBuilder = new StringBuilder();
   var var3: Int = 0;

   for (int var4 = $this$escape.length(); var3 < var4; var3++) {
      builder.append(escape(`$this$escape`.charAt(var3), multiline));
   }

   val var10000: java.lang.String = builder.toString();
   return var10000;
}

@JvmSynthetic
fun `escape$default`(var0: java.lang.String, var1: Boolean, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = false;
   }

   return escape(var0, var1);
}

internal fun String.unescape(): String {
   if (StringsKt.isBlank(`$this$unescape`)) {
      return `$this$unescape`;
   } else {
      val builder: StringBuilder = new StringBuilder();
      val lastIndex: Int = StringsKt.getLastIndex(`$this$unescape`);
      var i: Int = 0;

      while (i <= lastIndex) {
         val current: Char = `$this$unescape`.charAt(i);
         if (current != '\\') {
            builder.append(current);
            i++;
         } else {
            if (i == lastIndex) {
               throw new IllegalArgumentException(("Unexpected end in $`$this$unescape`").toString());
            }

            val next: Char = `$this$unescape`.charAt(i + 1);
            switch (next) {
               case '"':
                  builder.append('"');
                  i++;
                  break;
               case 'U':
                  if (lastIndex < i + 9) {
                     throw new IllegalArgumentException(("Unexpected end in $`$this$unescape`").toString());
                  }

                  val var15: java.lang.String = `$this$unescape`.substring(i + 2, i + 10);
                  builder.append((char)Integer.parseInt(var15, CharsKt.checkRadix(16)));
                  i += 9;
                  break;
               case '\\':
                  builder.append('\\');
                  i++;
                  break;
               case 'b':
                  builder.append('\b');
                  i++;
                  break;
               case 'f':
                  builder.append('\f');
                  i++;
                  break;
               case 'n':
                  builder.append('\n');
                  i++;
                  break;
               case 'r':
                  builder.append('\r');
                  i++;
                  break;
               case 't':
                  builder.append('\t');
                  i++;
                  break;
               case 'u':
                  if (lastIndex < i + 5) {
                     throw new IllegalArgumentException(("Unexpected end in $`$this$unescape`").toString());
                  }

                  val var10000: java.lang.String = `$this$unescape`.substring(i + 2, i + 6);
                  builder.append((char)Integer.parseInt(var10000, CharsKt.checkRadix(16)));
                  i += 5;
                  break;
               default:
                  throw new IllegalStateException(("Unknown escape $next").toString());
            }

            i++;
         }
      }

      val var16: java.lang.String = builder.toString();
      return var16;
   }
}

internal fun Float.toStringModified(): String {
   return if (java.lang.Float.isNaN(`$this$toStringModified`))
      "nan"
      else
      (
         if (java.lang.Float.isInfinite(`$this$toStringModified`))
            (if (`$this$toStringModified` > 0.0F) "inf" else "-inf")
            else
            java.lang.String.valueOf(`$this$toStringModified`)
      );
}

internal fun Double.toStringModified(): String {
   return if (java.lang.Double.isNaN(`$this$toStringModified`))
      "nan"
      else
      (
         if (java.lang.Double.isInfinite(`$this$toStringModified`))
            (if (`$this$toStringModified` > 0.0) "inf" else "-inf")
            else
            java.lang.String.valueOf(`$this$toStringModified`)
      );
}

internal fun processIntegerString(raw: String, base: Base, group: Int, uppercase: Boolean): String {
   val isNegative: Boolean = raw.charAt(0) == '-';
   var var10000: java.lang.String;
   if (!isNegative) {
      var10000 = raw;
   } else {
      var10000 = raw.substring(1);
   }

   if (base.compareTo(TomlInteger.Base.Dec) > 0 && uppercase) {
      var10000 = var10000.toUpperCase(Locale.ROOT);
   } else {
      var10000 = var10000;
   }

   val grouped: java.lang.String = if (group == 0)
      var10000
      else
      CollectionsKt.joinToString$default(
         CollectionsKt.asReversed(StringsKt.chunked(StringsKt.reversed(var10000).toString(), group, 1.INSTANCE)), "_", null, null, 0, null, null, 62, null
      );
   return if (!isNegative) "${base.getPrefix()}$grouped" else "-${base.getPrefix()}$grouped";
}

internal fun createNumberTomlLiteral(content: String, isPositive: Boolean, radix: Int, isDouble: Boolean, isExponent: Boolean): TomlLiteral {
   if (isDouble) {
      val var14: Double = if (isPositive) 1.0 else -1.0;
      val var27: Double;
      if (isExponent) {
         val var18: java.util.List = StringsKt.split$default(content, new char[]{'e'}, true, 0, 4, null);
         var27 = java.lang.Double.parseDouble(var18.get(0) as java.lang.String)
            * (var14 * Math.pow(10.0, (double)Integer.parseInt(var18.get(1) as java.lang.String)));
      } else {
         var27 = java.lang.Double.parseDouble(content) * var14;
      }

      return TomlElementKt.TomlLiteral(var27);
   } else {
      val factor: Long = if (isPositive) 1L else -1L;
      val var25: Long;
      if (isExponent) {
         val var9: java.util.List = StringsKt.split$default(content, new char[]{'e'}, true, 0, 4, null);
         var25 = java.lang.Long.parseLong(var9.get(0) as java.lang.String, CharsKt.checkRadix(radix))
            * factor
            * (long)Math.pow(10.0, (double)Integer.parseInt(var9.get(1) as java.lang.String));
      } else {
         val var17: java.lang.Long = StringsKt.toLongOrNull(content, radix);
         if (var17 == null) {
            if (!isPositive) {
               throw new IllegalArgumentException("ULong cannot be negative".toString());
            }

            return TomlElementKt.TomlLiteral-VKZWuLQ(UStringsKt.toULong(content, radix));
         }

         var25 = var17 * factor;
      }

      return TomlElementKt.TomlLiteral(var25);
   }
}
