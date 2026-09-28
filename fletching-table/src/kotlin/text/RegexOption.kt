package kotlin.text

import kotlin.enums.EnumEntries

public enum class RegexOption(value: Int, mask: Int = value) : FlagEnum {
   IGNORE_CASE(2, 0, 2, null),
   MULTILINE(8, 0, 2, null),
   LITERAL(16, 0, 2, null),
   UNIX_LINES(1, 0, 2, null),
   COMMENTS(4, 0, 2, null),
   DOT_MATCHES_ALL(32, 0, 2, null),
   CANON_EQ(128, 0, 2, null)
   public open val value: Int
   public open val mask: Int

   init {
      this.value = value;
      this.mask = mask;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<RegexOption> {
      return $ENTRIES;
   }
}
