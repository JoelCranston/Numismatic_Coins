package kotlin.text

import kotlin.enums.EnumEntries

public enum class CharCategory(value: Int, code: String) {
   UNASSIGNED(0, "Cn"),
   UPPERCASE_LETTER(1, "Lu"),
   LOWERCASE_LETTER(2, "Ll"),
   TITLECASE_LETTER(3, "Lt"),
   MODIFIER_LETTER(4, "Lm"),
   OTHER_LETTER(5, "Lo"),
   NON_SPACING_MARK(6, "Mn"),
   ENCLOSING_MARK(7, "Me"),
   COMBINING_SPACING_MARK(8, "Mc"),
   DECIMAL_DIGIT_NUMBER(9, "Nd"),
   LETTER_NUMBER(10, "Nl"),
   OTHER_NUMBER(11, "No"),
   SPACE_SEPARATOR(12, "Zs"),
   LINE_SEPARATOR(13, "Zl"),
   PARAGRAPH_SEPARATOR(14, "Zp"),
   CONTROL(15, "Cc"),
   FORMAT(16, "Cf"),
   PRIVATE_USE(18, "Co"),
   SURROGATE(19, "Cs"),
   DASH_PUNCTUATION(20, "Pd"),
   START_PUNCTUATION(21, "Ps"),
   END_PUNCTUATION(22, "Pe"),
   CONNECTOR_PUNCTUATION(23, "Pc"),
   OTHER_PUNCTUATION(24, "Po"),
   MATH_SYMBOL(25, "Sm"),
   CURRENCY_SYMBOL(26, "Sc"),
   MODIFIER_SYMBOL(27, "Sk"),
   OTHER_SYMBOL(28, "So"),
   INITIAL_QUOTE_PUNCTUATION(29, "Pi"),
   FINAL_QUOTE_PUNCTUATION(30, "Pf")
   public final val value: Int
   public final val code: String
   @JvmStatic
   public CharCategory.Companion Companion = new CharCategory.Companion(null);

   init {
      this.value = value;
      this.code = code;
   }

   public operator fun contains(char: Char): Boolean {
      return Character.getType(var1) == this.value;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<CharCategory> {
      return $ENTRIES;
   }

   public companion object {
      public fun valueOf(category: Int): CharCategory {
         val var10000: CharCategory;
         if (0 <= category && category < 17) {
            var10000 = CharCategory.getEntries().get(category);
         } else {
            if (18 > category || category >= 31) {
               throw new IllegalArgumentException("Category #$category is not defined.");
            }

            var10000 = CharCategory.getEntries().get(category - 1);
         }

         return var10000;
      }
   }
}
