package kotlin.text

import kotlin.internal.InlineOnly
import kotlin.jvm.internal.SourceDebugExtension

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
public class HexFormat internal constructor(upperCase: Boolean, bytes: kotlin.text.HexFormat.BytesHexFormat, number: kotlin.text.HexFormat.NumberHexFormat) {
   public final val upperCase: Boolean
   public final val bytes: kotlin.text.HexFormat.BytesHexFormat
   public final val number: kotlin.text.HexFormat.NumberHexFormat

   init {
      this.upperCase = upperCase;
      this.bytes = bytes;
      this.number = number;
   }

   public override fun toString(): String {
      val var1: StringBuilder = new StringBuilder();
      var1.append("HexFormat(").append('\n');
      var1.append("    upperCase = ").append(this.upperCase).append(",").append('\n');
      var1.append("    bytes = BytesHexFormat(").append('\n');
      this.bytes.appendOptionsTo$kotlin_stdlib(var1, "        ").append('\n');
      var1.append("    ),").append('\n');
      var1.append("    number = NumberHexFormat(").append('\n');
      this.number.appendOptionsTo$kotlin_stdlib(var1, "        ").append('\n');
      var1.append("    )").append('\n');
      var1.append(")");
      return var1.toString();
   }

   public class Builder @PublishedApi  internal constructor() {
      public final var upperCase: Boolean = HexFormat.Companion.getDefault().getUpperCase()
         internal set

      public final val bytes: kotlin.text.HexFormat.BytesHexFormat.Builder
         public final get() {
            if (this._bytes == null) {
               this._bytes = new HexFormat.BytesHexFormat.Builder();
            }

            val var10000: HexFormat.BytesHexFormat.Builder = this._bytes;
            return var10000;
         }


      private final var _bytes: kotlin.text.HexFormat.BytesHexFormat.Builder?

      public final val number: kotlin.text.HexFormat.NumberHexFormat.Builder
         public final get() {
            if (this._number == null) {
               this._number = new HexFormat.NumberHexFormat.Builder();
            }

            val var10000: HexFormat.NumberHexFormat.Builder = this._number;
            return var10000;
         }


      private final var _number: kotlin.text.HexFormat.NumberHexFormat.Builder?

      @InlineOnly
      public inline fun bytes(builderAction: (kotlin.text.HexFormat.BytesHexFormat.Builder) -> Unit) {
         builderAction.invoke(this.getBytes());
      }

      @InlineOnly
      public inline fun number(builderAction: (kotlin.text.HexFormat.NumberHexFormat.Builder) -> Unit) {
         builderAction.invoke(this.getNumber());
      }

      @PublishedApi
      internal fun build(): HexFormat {
         var var10000: HexFormat;
         var var10002: Boolean;
         var var10003: HexFormat.BytesHexFormat;
         label19: {
            var10000 = new HexFormat;
            var10002 = this.upperCase;
            if (this._bytes != null) {
               var10003 = this._bytes.build$kotlin_stdlib();
               if (var10003 != null) {
                  break label19;
               }
            }

            var10003 = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib();
         }

         var var10004: HexFormat.NumberHexFormat;
         label14: {
            if (this._number != null) {
               var10004 = this._number.build$kotlin_stdlib();
               if (var10004 != null) {
                  break label14;
               }
            }

            var10004 = HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib();
         }

         var10000./* $VF: Unable to resugar constructor */<init>(var10002, var10003, var10004);
         return var10000;
      }
   }

   public class BytesHexFormat internal constructor(bytesPerLine: Int,
      bytesPerGroup: Int,
      groupSeparator: String,
      byteSeparator: String,
      bytePrefix: String,
      byteSuffix: String
   ) {
      public final val bytesPerLine: Int
      public final val bytesPerGroup: Int
      public final val groupSeparator: String
      public final val byteSeparator: String
      public final val bytePrefix: String
      public final val byteSuffix: String
      internal final val noLineAndGroupSeparator: Boolean
      internal final val shortByteSeparatorNoPrefixAndSuffix: Boolean
      internal final val ignoreCase: Boolean

      init {
         this.bytesPerLine = bytesPerLine;
         this.bytesPerGroup = bytesPerGroup;
         this.groupSeparator = groupSeparator;
         this.byteSeparator = byteSeparator;
         this.bytePrefix = bytePrefix;
         this.byteSuffix = byteSuffix;
         this.noLineAndGroupSeparator = this.bytesPerLine == Integer.MAX_VALUE && this.bytesPerGroup == Integer.MAX_VALUE;
         this.shortByteSeparatorNoPrefixAndSuffix = this.bytePrefix.length() == 0 && this.byteSuffix.length() == 0 && this.byteSeparator.length() <= 1;
         this.ignoreCase = HexFormatKt.access$isCaseSensitive(this.groupSeparator)
            || HexFormatKt.access$isCaseSensitive(this.byteSeparator)
            || HexFormatKt.access$isCaseSensitive(this.bytePrefix)
            || HexFormatKt.access$isCaseSensitive(this.byteSuffix);
      }

      public override fun toString(): String {
         val var1: StringBuilder = new StringBuilder();
         var1.append("BytesHexFormat(").append('\n');
         this.appendOptionsTo$kotlin_stdlib(var1, "    ").append('\n');
         var1.append(")");
         return var1.toString();
      }

      internal fun appendOptionsTo(sb: StringBuilder, indent: String): StringBuilder {
         sb.append(indent).append("bytesPerLine = ").append(this.bytesPerLine).append(",").append('\n');
         sb.append(indent).append("bytesPerGroup = ").append(this.bytesPerGroup).append(",").append('\n');
         sb.append(indent).append("groupSeparator = \"").append(this.groupSeparator).append("\",").append('\n');
         sb.append(indent).append("byteSeparator = \"").append(this.byteSeparator).append("\",").append('\n');
         sb.append(indent).append("bytePrefix = \"").append(this.bytePrefix).append("\",").append('\n');
         sb.append(indent).append("byteSuffix = \"").append(this.byteSuffix).append("\"");
         return sb;
      }

      public class Builder internal constructor() {
         public final var bytesPerLine: Int = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytesPerLine()
            internal final set(value) {
               if (value <= 0) {
                  throw new IllegalArgumentException("Non-positive values are prohibited for bytesPerLine, but was $value");
               } else {
                  this.bytesPerLine = value;
               }
            }


         public final var bytesPerGroup: Int = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytesPerGroup()
            internal final set(value) {
               if (value <= 0) {
                  throw new IllegalArgumentException("Non-positive values are prohibited for bytesPerGroup, but was $value");
               } else {
                  this.bytesPerGroup = value;
               }
            }


         public final var groupSeparator: String = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getGroupSeparator()
            internal set

         public final var byteSeparator: String = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getByteSeparator()
            internal final set(value) {
               if (!StringsKt.contains$default(value, '\n', false, 2, null) && !StringsKt.contains$default(value, '\r', false, 2, null)) {
                  this.byteSeparator = value;
               } else {
                  throw new IllegalArgumentException("LF and CR characters are prohibited in byteSeparator, but was $value");
               }
            }


         public final var bytePrefix: String = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytePrefix()
            internal final set(value) {
               if (!StringsKt.contains$default(value, '\n', false, 2, null) && !StringsKt.contains$default(value, '\r', false, 2, null)) {
                  this.bytePrefix = value;
               } else {
                  throw new IllegalArgumentException("LF and CR characters are prohibited in bytePrefix, but was $value");
               }
            }


         public final var byteSuffix: String = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getByteSuffix()
            internal final set(value) {
               if (!StringsKt.contains$default(value, '\n', false, 2, null) && !StringsKt.contains$default(value, '\r', false, 2, null)) {
                  this.byteSuffix = value;
               } else {
                  throw new IllegalArgumentException("LF and CR characters are prohibited in byteSuffix, but was $value");
               }
            }


         internal fun build(): kotlin.text.HexFormat.BytesHexFormat {
            return new HexFormat.BytesHexFormat(
               this.bytesPerLine, this.bytesPerGroup, this.groupSeparator, this.byteSeparator, this.bytePrefix, this.byteSuffix
            );
         }
      }

      internal companion object {
         internal final val Default: kotlin.text.HexFormat.BytesHexFormat
      }
   }

   public companion object {
      public final val Default: HexFormat
      public final val UpperCase: HexFormat
   }

   public class NumberHexFormat internal constructor(prefix: String, suffix: String, removeLeadingZeros: Boolean, minLength: Int) {
      public final val prefix: String
      public final val suffix: String
      public final val removeLeadingZeros: Boolean

      @SinceKotlin(
         version = "2.0"
      )
      public final val minLength: Int

      internal final val isDigitsOnly: Boolean
      internal final val isDigitsOnlyAndNoPadding: Boolean
      internal final val ignoreCase: Boolean

      init {
         this.prefix = prefix;
         this.suffix = suffix;
         this.removeLeadingZeros = removeLeadingZeros;
         this.minLength = minLength;
         this.isDigitsOnly = this.prefix.length() == 0 && this.suffix.length() == 0;
         this.isDigitsOnlyAndNoPadding = this.isDigitsOnly && this.minLength == 1;
         this.ignoreCase = HexFormatKt.access$isCaseSensitive(this.prefix) || HexFormatKt.access$isCaseSensitive(this.suffix);
      }

      public override fun toString(): String {
         val var1: StringBuilder = new StringBuilder();
         var1.append("NumberHexFormat(").append('\n');
         this.appendOptionsTo$kotlin_stdlib(var1, "    ").append('\n');
         var1.append(")");
         return var1.toString();
      }

      internal fun appendOptionsTo(sb: StringBuilder, indent: String): StringBuilder {
         sb.append(indent).append("prefix = \"").append(this.prefix).append("\",").append('\n');
         sb.append(indent).append("suffix = \"").append(this.suffix).append("\",").append('\n');
         sb.append(indent).append("removeLeadingZeros = ").append(this.removeLeadingZeros).append((char)',').append('\n');
         sb.append(indent).append("minLength = ").append(this.minLength);
         return sb;
      }

      @SourceDebugExtension(["SMAP\nHexFormat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HexFormat.kt\nkotlin/text/HexFormat$NumberHexFormat$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,845:1\n1#2:846\n*E\n"])
      public class Builder internal constructor() {
         public final var prefix: String = HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib().getPrefix()
            internal final set(value) {
               if (!StringsKt.contains$default(value, '\n', false, 2, null) && !StringsKt.contains$default(value, '\r', false, 2, null)) {
                  this.prefix = value;
               } else {
                  throw new IllegalArgumentException("LF and CR characters are prohibited in prefix, but was $value");
               }
            }


         public final var suffix: String = HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib().getSuffix()
            internal final set(value) {
               if (!StringsKt.contains$default(value, '\n', false, 2, null) && !StringsKt.contains$default(value, '\r', false, 2, null)) {
                  this.suffix = value;
               } else {
                  throw new IllegalArgumentException("LF and CR characters are prohibited in suffix, but was $value");
               }
            }


         public final var removeLeadingZeros: Boolean = HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib().getRemoveLeadingZeros()
            internal set

         @SinceKotlin(
            version = "2.0"
         )
         public final var minLength: Int = HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib().getMinLength()
            public final set(value) {
               if (value <= 0) {
                  throw new IllegalArgumentException(("Non-positive values are prohibited for minLength, but was $value").toString());
               } else {
                  this.minLength = value;
               }
            }


         internal fun build(): kotlin.text.HexFormat.NumberHexFormat {
            return new HexFormat.NumberHexFormat(this.prefix, this.suffix, this.removeLeadingZeros, this.minLength);
         }
      }

      internal companion object {
         internal final val Default: kotlin.text.HexFormat.NumberHexFormat
      }
   }
}
