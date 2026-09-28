package dev.kikugie.fletching_table.extension

@JvmInline
@PublishedApi
internal inline class PatternEntry {
   public final val key: String

   @JvmStatic
   public operator fun component1(): String {
      return StringsKt.trimEnd(StringsKt.substringBefore$default(`$v$c$dev-kikugie-fletching_table-extension-PatternEntry$-this$0`, "->", null, 2, null))
         .toString();
   }

   @JvmStatic
   public operator fun component2(): String {
      return StringsKt.trimStart(StringsKt.substringAfter(`$v$c$dev-kikugie-fletching_table-extension-PatternEntry$-this$0`, "->", "")).toString();
   }

   @JvmStatic
   public open fun toString(): String {
      return "PatternEntry(key=$`$v$c$dev-kikugie-fletching_table-extension-PatternEntry$-this$0`)";
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.key);
   }

   @JvmStatic
   public open fun hashCode(): Int {
      return `$v$c$dev-kikugie-fletching_table-extension-PatternEntry$-this$0`.hashCode();
   }

   override fun hashCode(): Int {
      return hashCode-impl(this.key);
   }

   @JvmStatic
   public open operator fun equals(other: Any?): Boolean {
      if (other !is PatternEntry) {
         return false;
      } else {
         return `$v$c$dev-kikugie-fletching_table-extension-PatternEntry$-this$0` == (other as PatternEntry).unbox-impl();
      }
   }

   override fun equals(other: Any): Boolean {
      return equals-impl(this.key, other);
   }

   @JvmStatic
   fun `constructor-impl`(key: java.lang.String): java.lang.String {
      return key;
   }

   @JvmStatic
   fun `equals-impl0`(p1: java.lang.String, p2: java.lang.String): Boolean {
      return p1 == p2;
   }
}
