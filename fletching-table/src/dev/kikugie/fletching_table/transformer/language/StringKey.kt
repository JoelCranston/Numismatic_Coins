package dev.kikugie.fletching_table.transformer.language

@JvmInline
internal inline class StringKey : Key {
   public final val value: String

   @JvmStatic
   public open fun toString(): String {
      return "StringKey(value=$`$v$c$dev-kikugie-fletching_table-transformer-language-StringKey$-this$0`)";
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.value);
   }

   @JvmStatic
   public open fun hashCode(): Int {
      return `$v$c$dev-kikugie-fletching_table-transformer-language-StringKey$-this$0`.hashCode();
   }

   override fun hashCode(): Int {
      return hashCode-impl(this.value);
   }

   @JvmStatic
   public open operator fun equals(other: Any?): Boolean {
      if (other !is StringKey) {
         return false;
      } else {
         return `$v$c$dev-kikugie-fletching_table-transformer-language-StringKey$-this$0` == (other as StringKey).unbox-impl();
      }
   }

   override fun equals(other: Any): Boolean {
      return equals-impl(this.value, other);
   }

   @JvmStatic
   fun `constructor-impl`(value: java.lang.String): java.lang.String {
      return value;
   }

   @JvmStatic
   fun `equals-impl0`(p1: java.lang.String, p2: java.lang.String): Boolean {
      return p1 == p2;
   }
}
