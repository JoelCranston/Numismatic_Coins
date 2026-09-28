package io.ktor.util

import io.ktor.util.StringValuesSingleImpl.entries.1

public open class StringValuesSingleImpl(caseInsensitiveName: Boolean, name: String, values: List<String>) : StringValues {
   public open val caseInsensitiveName: Boolean
   public final val name: String
   public final val values: List<String>

   init {
      this.caseInsensitiveName = caseInsensitiveName;
      this.name = name;
      this.values = values;
   }

   public override fun getAll(name: String): List<String>? {
      return if (StringsKt.equals(this.name, name, this.getCaseInsensitiveName())) this.values else null;
   }

   public override fun entries(): Set<kotlin.collections.Map.Entry<String, List<String>>> {
      return SetsKt.setOf(new 1(this));
   }

   public override fun isEmpty(): Boolean {
      return false;
   }

   public override fun names(): Set<String> {
      return SetsKt.setOf(this.name);
   }

   public override fun toString(): String {
      return "StringValues(case=${!this.getCaseInsensitiveName()}) ${this.entries()}";
   }

   public override fun hashCode(): Int {
      return StringValuesKt.access$entriesHashCode(this.entries(), 31 * java.lang.Boolean.hashCode(this.getCaseInsensitiveName()));
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is StringValues) {
         return false;
      } else {
         return this.getCaseInsensitiveName() == (other as StringValues).getCaseInsensitiveName()
            && StringValuesKt.access$entriesEquals(this.entries(), (other as StringValues).entries());
      }
   }

   public override fun forEach(body: (String, List<String>) -> Unit) {
      body.invoke(this.name, this.values);
   }

   public override operator fun get(name: String): String? {
      return if (StringsKt.equals(name, this.name, this.getCaseInsensitiveName())) kotlin.collections.CollectionsKt.firstOrNull(this.values) else null;
   }

   public override operator fun contains(name: String): Boolean {
      return StringsKt.equals(name, this.name, this.getCaseInsensitiveName());
   }

   public override fun contains(name: String, value: String): Boolean {
      return StringsKt.equals(name, this.name, this.getCaseInsensitiveName()) && this.values.contains(value);
   }
}
