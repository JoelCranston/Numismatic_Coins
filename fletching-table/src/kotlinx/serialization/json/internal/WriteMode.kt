package kotlinx.serialization.json.internal

import kotlin.enums.EnumEntries

internal enum class WriteMode(begin: Char, end: Char) {
   OBJ('{', '}'),
   LIST('[', ']'),
   MAP('{', '}'),
   POLY_OBJ('[', ']')
   public final val begin: Char
   public final val end: Char

   init {
      this.begin = begin;
      this.end = end;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<WriteMode> {
      return $ENTRIES;
   }
}
