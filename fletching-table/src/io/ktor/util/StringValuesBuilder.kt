package io.ktor.util

public interface StringValuesBuilder {
   public val caseInsensitiveName: Boolean

   public abstract fun getAll(name: String): List<String>? {
   }

   public abstract operator fun contains(name: String): Boolean {
   }

   public abstract fun contains(name: String, value: String): Boolean {
   }

   public abstract fun names(): Set<String> {
   }

   public abstract fun isEmpty(): Boolean {
   }

   public abstract fun entries(): Set<kotlin.collections.Map.Entry<String, List<String>>> {
   }

   public abstract operator fun set(name: String, value: String) {
   }

   public abstract operator fun get(name: String): String? {
   }

   public abstract fun append(name: String, value: String) {
   }

   public abstract fun appendAll(stringValues: StringValues) {
   }

   public abstract fun appendAll(name: String, values: Iterable<String>) {
   }

   public abstract fun appendMissing(stringValues: StringValues) {
   }

   public abstract fun appendMissing(name: String, values: Iterable<String>) {
   }

   public abstract fun remove(name: String) {
   }

   public abstract fun removeKeysWithNoEntries() {
   }

   public abstract fun remove(name: String, value: String): Boolean {
   }

   public abstract fun clear() {
   }

   public abstract fun build(): StringValues {
   }
}
