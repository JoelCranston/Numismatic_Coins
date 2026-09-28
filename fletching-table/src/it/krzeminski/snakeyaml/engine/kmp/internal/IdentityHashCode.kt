package it.krzeminski.snakeyaml.engine.kmp.internal

@JvmInline
internal inline class IdentityHashCode {
   private final val value: Int

   @JvmStatic
   fun `toString-impl`(arg0: Int): java.lang.String {
      return "IdentityHashCode(value=$arg0)";
   }

   public override fun toString(): String {
      return toString-impl(this.value);
   }

   @JvmStatic
   fun `hashCode-impl`(arg0: Int): Int {
      return Integer.hashCode(arg0);
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.value);
   }

   @JvmStatic
   fun `equals-impl`(arg0: Int, other: Any): Boolean {
      if (other !is IdentityHashCode) {
         return false;
      } else {
         return arg0 == (other as IdentityHashCode).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.value, other);
   }

   @JvmStatic
   fun `constructor-impl`(value: Int): Int {
      return value;
   }

   @JvmStatic
   fun `equals-impl0`(p1: Int, p2: Int): Boolean {
      return p1 == p2;
   }
}
