package kotlin.text

import kotlin.text.DelimitedRangesSequence.iterator.1

private class DelimitedRangesSequence(input: CharSequence, startIndex: Int, limit: Int, getNextMatch: (CharSequence, Int) -> Pair<Int, Int>?) :
   Sequence<IntRange> {
   private final val input: CharSequence
   private final val startIndex: Int
   private final val limit: Int
   private final val getNextMatch: (CharSequence, Int) -> Pair<Int, Int>?

   init {
      this.input = input;
      this.startIndex = startIndex;
      this.limit = limit;
      this.getNextMatch = getNextMatch;
   }

   public override operator fun iterator(): Iterator<IntRange> {
      return new 1(this);
   }
}
