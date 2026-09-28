package it.krzeminski.snakeyaml.engine.kmp.scanner

private sealed interface Chomping {
   public val increment: Int?
   public val addExistingFinalLineBreak: Boolean
   public val retainTrailingEmptyLines: Boolean

   @JvmInline
   public inline class Clip : Chomping {
      public open val increment: Int?

      public open val addExistingFinalLineBreak: Boolean
         public open get() {
            return true;
         }


      public open val retainTrailingEmptyLines: Boolean
         public open get() {
            return false;
         }


      override fun getAddExistingFinalLineBreak(): Boolean {
         return getAddExistingFinalLineBreak-impl(this.increment);
      }

      override fun getRetainTrailingEmptyLines(): Boolean {
         return getRetainTrailingEmptyLines-impl(this.increment);
      }

      @JvmStatic
      fun `toString-impl`(arg0: Int): java.lang.String {
         return "Clip(increment=$arg0)";
      }

      public override fun toString(): String {
         return toString-impl(this.increment);
      }

      @JvmStatic
      fun `hashCode-impl`(arg0: Int): Int {
         return if (arg0 == null) 0 else arg0.hashCode();
      }

      public override fun hashCode(): Int {
         return hashCode-impl(this.increment);
      }

      @JvmStatic
      fun `equals-impl`(arg0: Int, other: Any): Boolean {
         if (other !is Chomping.Clip) {
            return false;
         } else {
            return arg0 == (other as Chomping.Clip).unbox-impl();
         }
      }

      public override operator fun equals(other: Any?): Boolean {
         return equals-impl(this.increment, other);
      }

      @JvmStatic
      fun `constructor-impl`(increment: Int?): Int {
         return increment;
      }

      @JvmStatic
      fun `equals-impl0`(p1: Int, p2: Int): Boolean {
         return p1 == p2;
      }
   }

   @JvmInline
   public inline class Keep : Chomping {
      public open val increment: Int?

      public open val addExistingFinalLineBreak: Boolean
         public open get() {
            return true;
         }


      public open val retainTrailingEmptyLines: Boolean
         public open get() {
            return true;
         }


      override fun getAddExistingFinalLineBreak(): Boolean {
         return getAddExistingFinalLineBreak-impl(this.increment);
      }

      override fun getRetainTrailingEmptyLines(): Boolean {
         return getRetainTrailingEmptyLines-impl(this.increment);
      }

      @JvmStatic
      fun `toString-impl`(arg0: Int): java.lang.String {
         return "Keep(increment=$arg0)";
      }

      public override fun toString(): String {
         return toString-impl(this.increment);
      }

      @JvmStatic
      fun `hashCode-impl`(arg0: Int): Int {
         return if (arg0 == null) 0 else arg0.hashCode();
      }

      public override fun hashCode(): Int {
         return hashCode-impl(this.increment);
      }

      @JvmStatic
      fun `equals-impl`(arg0: Int, other: Any): Boolean {
         if (other !is Chomping.Keep) {
            return false;
         } else {
            return arg0 == (other as Chomping.Keep).unbox-impl();
         }
      }

      public override operator fun equals(other: Any?): Boolean {
         return equals-impl(this.increment, other);
      }

      @JvmStatic
      fun `constructor-impl`(increment: Int?): Int {
         return increment;
      }

      @JvmStatic
      fun `equals-impl0`(p1: Int, p2: Int): Boolean {
         return p1 == p2;
      }
   }

   @JvmInline
   public inline class Strip : Chomping {
      public open val increment: Int?

      public open val addExistingFinalLineBreak: Boolean
         public open get() {
            return false;
         }


      public open val retainTrailingEmptyLines: Boolean
         public open get() {
            return false;
         }


      override fun getAddExistingFinalLineBreak(): Boolean {
         return getAddExistingFinalLineBreak-impl(this.increment);
      }

      override fun getRetainTrailingEmptyLines(): Boolean {
         return getRetainTrailingEmptyLines-impl(this.increment);
      }

      @JvmStatic
      fun `toString-impl`(arg0: Int): java.lang.String {
         return "Strip(increment=$arg0)";
      }

      public override fun toString(): String {
         return toString-impl(this.increment);
      }

      @JvmStatic
      fun `hashCode-impl`(arg0: Int): Int {
         return if (arg0 == null) 0 else arg0.hashCode();
      }

      public override fun hashCode(): Int {
         return hashCode-impl(this.increment);
      }

      @JvmStatic
      fun `equals-impl`(arg0: Int, other: Any): Boolean {
         if (other !is Chomping.Strip) {
            return false;
         } else {
            return arg0 == (other as Chomping.Strip).unbox-impl();
         }
      }

      public override operator fun equals(other: Any?): Boolean {
         return equals-impl(this.increment, other);
      }

      @JvmStatic
      fun `constructor-impl`(increment: Int?): Int {
         return increment;
      }

      @JvmStatic
      fun `equals-impl0`(p1: Int, p2: Int): Boolean {
         return p1 == p2;
      }
   }
}
