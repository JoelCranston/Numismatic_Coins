package net.peanuuutz.tomlkt

import net.peanuuutz.tomlkt.TomlInteger.Base

public interface TomlWriter {
   public abstract fun writeString(string: String) {
   }

   public abstract fun writeChar(char: Char) {
   }

   public abstract fun writeKey(key: String) {
   }

   public abstract fun writeKeySeparator() {
   }

   public abstract fun startRegularTableHead() {
   }

   public abstract fun endRegularTableHead() {
   }

   public abstract fun startArrayOfTableHead() {
   }

   public abstract fun endArrayOfTableHead() {
   }

   public abstract fun writeBooleanValue(boolean: Boolean) {
   }

   public abstract fun writeIntegerValue(integer: Long, base: Base = TomlInteger.Base.Dec, group: Int = 0, uppercase: Boolean = true) {
   }

   public abstract fun writeFloatValue(float: Double) {
   }

   public abstract fun writeStringValue(string: String, isMultiline: Boolean = false, isLiteral: Boolean = false) {
   }

   public abstract fun writeNullValue() {
   }

   public abstract fun startArray() {
   }

   public abstract fun endArray() {
   }

   public abstract fun startInlineTable() {
   }

   public abstract fun endInlineTable() {
   }

   public abstract fun writeKeyValueSeparator() {
   }

   public abstract fun writeElementSeparator() {
   }

   public abstract fun startComment() {
   }

   public abstract fun writeSpace() {
   }

   public abstract fun writeIndentation(indentation: TomlIndentation) {
   }

   public abstract fun writeLineFeed() {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
