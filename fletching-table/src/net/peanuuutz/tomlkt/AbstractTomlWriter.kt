package net.peanuuutz.tomlkt

import kotlin.jvm.internal.SourceDebugExtension
import net.peanuuutz.tomlkt.TomlInteger.Base
import net.peanuuutz.tomlkt.internal.StringUtilsKt

@SourceDebugExtension(["SMAP\nAbstractTomlWriter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractTomlWriter.kt\nnet/peanuuutz/tomlkt/AbstractTomlWriter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 StringUtils.kt\nnet/peanuuutz/tomlkt/internal/StringUtilsKt\n*L\n1#1,183:1\n1#2:184\n84#3:185\n81#3:186\n*S KotlinDebug\n*F\n+ 1 AbstractTomlWriter.kt\nnet/peanuuutz/tomlkt/AbstractTomlWriter\n*L\n107#1:185\n113#1:186\n*E\n"])
public abstract class AbstractTomlWriter : TomlWriter {
   public override fun writeChar(char: Char) {
      this.writeString(java.lang.String.valueOf(var1));
   }

   public override fun writeKey(key: String) {
      this.writeString(StringUtilsKt.doubleQuotedIfNotPure(StringUtilsKt.escape$default(key, false, 1, null)));
   }

   public override fun writeKeySeparator() {
      this.writeChar('.');
   }

   public override fun startRegularTableHead() {
      this.writeChar('[');
   }

   public override fun endRegularTableHead() {
      this.writeChar(']');
   }

   public override fun startArrayOfTableHead() {
      this.writeChar('[');
      this.writeChar('[');
   }

   public override fun endArrayOfTableHead() {
      this.writeChar(']');
      this.writeChar(']');
   }

   public override fun writeBooleanValue(boolean: Boolean) {
      this.writeString(java.lang.String.valueOf(var1));
   }

   public override fun writeIntegerValue(integer: Long, base: Base, group: Int, uppercase: Boolean) {
      if (group < 0) {
         throw new IllegalArgumentException("Group size cannot be negative".toString());
      } else if (integer < 0L && base != TomlInteger.Base.Dec) {
         throw new IllegalArgumentException(("Negative integer cannot be represented by other bases, but found $integer").toString());
      } else {
         val var10000: java.lang.String = java.lang.Long.toString(integer, CharsKt.checkRadix(base.getValue()));
         this.writeString(StringUtilsKt.processIntegerString(var10000, base, group, uppercase));
      }
   }

   public override fun writeFloatValue(float: Double) {
      this.writeString(StringUtilsKt.toStringModified(var1));
   }

   public override fun writeStringValue(string: String, isMultiline: Boolean, isLiteral: Boolean) {
      if (!isMultiline && !isLiteral) {
         this.writeString(""${StringUtilsKt.escape$default(string, false, 1, null)}"");
      } else if (!isMultiline) {
         if (StringsKt.contains$default(string, '\'', false, 2, null) || StringsKt.contains$default(string, '\n', false, 2, null)) {
            throw new IllegalArgumentException(("Cannot have '\\'' or '\\n' in literal string, but found $string").toString());
         }

         this.writeString("'$string'");
      } else if (!isLiteral) {
         this.writeString("\"\"\"");
         this.writeLineFeed();
         this.writeString(StringUtilsKt.escape(string, true));
         this.writeString("\"\"\"");
      } else {
         if (StringsKt.contains$default(string, "'''", false, 2, null)) {
            throw new IllegalArgumentException(("Cannot have \"\\'\\'\\'\" in multiline literal string, but found $string").toString());
         }

         this.writeString("'''");
         this.writeLineFeed();
         this.writeString(string);
         this.writeString("'''");
      }
   }

   public override fun writeNullValue() {
      this.writeString("null");
   }

   public override fun startArray() {
      this.writeChar('[');
   }

   public override fun endArray() {
      this.writeChar(']');
   }

   public override fun startInlineTable() {
      this.writeChar('{');
   }

   public override fun endInlineTable() {
      this.writeChar('}');
   }

   public override fun writeKeyValueSeparator() {
      this.writeChar('=');
   }

   public override fun writeElementSeparator() {
      this.writeChar(',');
   }

   public override fun startComment() {
      this.writeChar('#');
   }

   public override fun writeSpace() {
      this.writeChar(' ');
   }

   public override fun writeIndentation(indentation: TomlIndentation) {
      this.writeString(indentation);
   }

   public override fun writeLineFeed() {
      this.writeChar('\n');
   }
}
