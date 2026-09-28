package io.ktor.http.cio.internals

internal fun nextToken(text: CharSequence, range: MutableRange): CharSequence {
   val spaceOrEnd: Int = findSpaceOrEnd(text, range);
   val s: java.lang.CharSequence = text.subSequence(range.getStart(), spaceOrEnd);
   range.setStart(spaceOrEnd);
   return s;
}

internal fun skipSpacesAndHorizontalTabs(text: CharArrayBuilder, start: Int, end: Int): Int {
   var index: Int;
   for (index = start; index < end; index++) {
      val ch: Char = text.charAt(index);
      if (!kotlin.text.CharsKt.isWhitespace(ch) && ch != '\t') {
         break;
      }
   }

   return index;
}

internal fun skipSpaces(text: CharSequence, range: MutableRange) {
   var idx: Int = range.getStart();
   val end: Int = range.getEnd();
   if (idx < end && kotlin.text.CharsKt.isWhitespace(text.charAt(idx))) {
      idx++;

      while (idx < end && kotlin.text.CharsKt.isWhitespace(text.charAt(idx))) {
         idx++;
      }

      range.setStart(idx);
   }
}

internal fun findSpaceOrEnd(text: CharSequence, range: MutableRange): Int {
   var idx: Int = range.getStart();
   val end: Int = range.getEnd();
   if (idx < end && !kotlin.text.CharsKt.isWhitespace(text.charAt(idx))) {
      idx++;

      while (idx < end) {
         if (kotlin.text.CharsKt.isWhitespace(text.charAt(idx))) {
            return idx;
         }

         idx++;
      }

      return idx;
   } else {
      return idx;
   }
}
