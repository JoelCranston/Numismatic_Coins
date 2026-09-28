package kotlin.text

import java.util.NoSuchElementException
import kotlin.jvm.internal.markers.KMappedMarker

private class LinesIterator(string: CharSequence) : java.util.Iterator<java.lang.String>, KMappedMarker {
   private final val string: CharSequence
   private final var state: Int
   private final var tokenStartIndex: Int
   private final var delimiterStartIndex: Int
   private final var delimiterLength: Int

   init {
      this.string = string;
   }

   public override operator fun hasNext(): Boolean {
      if (this.state != 0) {
         return this.state == 1;
      } else if (this.delimiterLength < 0) {
         this.state = 2;
         return false;
      } else {
         var _delimiterLength: Int = -1;
         var _delimiterStartIndex: Int = this.string.length();
         var idx: Int = this.tokenStartIndex;
         val var4: Int = this.string.length();

         label38:
         while (idx < var4) {
            val c: Char = this.string.charAt(idx);
            switch (c) {
               case '\n':
               case '\r':
                  _delimiterLength = if (c == '\r' && idx + 1 < this.string.length() && this.string.charAt(idx + 1) == '\n') 2 else 1;
                  _delimiterStartIndex = idx;
                  break label38;
               case '\u000b':
               case '\f':
               default:
                  idx++;
            }
         }

         this.state = 1;
         this.delimiterLength = _delimiterLength;
         this.delimiterStartIndex = _delimiterStartIndex;
         return true;
      }
   }

   public open operator fun next(): String {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         this.state = 0;
         val lastIndex: Int = this.delimiterStartIndex;
         val firstIndex: Int = this.tokenStartIndex;
         this.tokenStartIndex = this.delimiterStartIndex + this.delimiterLength;
         return this.string.subSequence(firstIndex, lastIndex).toString();
      }
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   private companion object State {
      public const val UNKNOWN: Int
      public const val HAS_NEXT: Int
      public const val EXHAUSTED: Int
   }
}
