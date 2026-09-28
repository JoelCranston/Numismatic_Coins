package kotlinx.serialization.json.internal

@JsonFriendModuleApi
public final val ESCAPE_STRINGS: Array<String?>

internal final val ESCAPE_MARKERS: ByteArray
private java.lang.String[] ESCAPE_STRINGS;

private fun toHexChar(i: Int): Char {
   return if ((i and 15) < 10) (char)((i and 15) + 48) else (char)((i and 15) - 10 + 97);
}

internal fun StringBuilder.printQuoted(value: String) {
   `$this$printQuoted`.append('"');
   var lastPos: Int = 0;
   var i: Int = 0;

   for (int var4 = value.length(); i < var4; i++) {
      val c: Int = value.charAt(i);
      if (c < ESCAPE_STRINGS.length && ESCAPE_STRINGS[c] != null) {
         `$this$printQuoted`.append(value, lastPos, i);
         `$this$printQuoted`.append(ESCAPE_STRINGS[c]);
         lastPos = i + 1;
      }
   }

   if (lastPos != 0) {
      `$this$printQuoted`.append(value, lastPos, value.length());
   } else {
      `$this$printQuoted`.append(value);
   }

   `$this$printQuoted`.append('"');
}

internal fun String.toBooleanStrictOrNull(): Boolean? {
   return if (StringsKt.equals(`$this$toBooleanStrictOrNull`, "true", true))
      true
      else
      (if (StringsKt.equals(`$this$toBooleanStrictOrNull`, "false", true)) false else null);
}
