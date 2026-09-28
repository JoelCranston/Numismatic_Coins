package kotlin.collections

internal class CollectionsKt__ReversedViewsKt : CollectionsKt__MutableCollectionsKt {
   @JvmStatic
   private fun List<*>.reverseElementIndex(index: Int): Int {
      if (0 <= index && index <= CollectionsKt.getLastIndex(`$this$reverseElementIndex`)) {
         return CollectionsKt.getLastIndex(`$this$reverseElementIndex`) - index;
      } else {
         throw new IndexOutOfBoundsException(
            "Element index $index must be in range [${new IntRange(0, CollectionsKt.getLastIndex(`$this$reverseElementIndex`))}]."
         );
      }
   }

   @JvmStatic
   private fun List<*>.reversePositionIndex(index: Int): Int {
      if (0 <= index && index <= `$this$reversePositionIndex`.size()) {
         return `$this$reversePositionIndex`.size() - index;
      } else {
         throw new IndexOutOfBoundsException("Position index $index must be in range [${new IntRange(0, `$this$reversePositionIndex`.size())}].");
      }
   }

   @JvmStatic
   private fun List<*>.reverseIteratorIndex(index: Int): Int {
      return CollectionsKt.getLastIndex(`$this$reverseIteratorIndex`) - index;
   }

   @JvmStatic
   public fun <T> List<T>.asReversed(): List<T> {
      return new ReversedListReadOnly(`$this$asReversed`);
   }

   @JvmName(name = "asReversedMutable")
   @JvmStatic
   public fun <T> MutableList<T>.asReversed(): MutableList<T> {
      return new ReversedList(`$this$asReversed`);
   }

   open fun CollectionsKt__ReversedViewsKt() {
   }
}
