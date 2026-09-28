package net.peanuuutz.tomlkt

@JvmInline
public inline class TomlIndentation {
   public final val representation: String

   @JvmStatic
   public open fun toString(): String {
      return arg0;
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.representation);
   }

   @JvmStatic
   fun `hashCode-impl`(arg0: java.lang.String): Int {
      return arg0.hashCode();
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.representation);
   }

   @JvmStatic
   fun `equals-impl`(arg0: java.lang.String, other: Any): Boolean {
      if (other !is TomlIndentation) {
         return false;
      } else {
         return arg0 == (other as TomlIndentation).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.representation, other);
   }

   @JvmStatic
   fun `constructor-impl`(representation: java.lang.String): java.lang.String {
      if (!StringsKt.isBlank(representation)) {
         throw new IllegalArgumentException(("Cannot use non-blank characters inside representation, but found $representation").toString());
      } else {
         return representation;
      }
   }

   @JvmStatic
   fun `equals-impl0`(p1: java.lang.String, p2: java.lang.String): Boolean {
      return p1 == p2;
   }

   public companion object {
      public final val Space4: TomlIndentation
      public final val Space2: TomlIndentation
      public final val Tab: TomlIndentation
   }
}
