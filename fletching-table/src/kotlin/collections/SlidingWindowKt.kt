package kotlin.collections

import kotlin.collections.SlidingWindowKt.windowedSequence..inlined.Sequence.1

internal fun checkWindowSizeStep(size: Int, step: Int) {
   if (size <= 0 || step <= 0) {
      throw new IllegalArgumentException(
         (if (size != step) "Both size $size and step $step must be greater than zero." else "size $size must be greater than zero.").toString()
      );
   }
}

internal fun <T> Sequence<T>.windowedSequence(size: Int, step: Int, partialWindows: Boolean, reuseBuffer: Boolean): Sequence<List<T>> {
   checkWindowSizeStep(size, step);
   return new 1(`$this$windowedSequence`, size, step, partialWindows, reuseBuffer);
}

internal fun <T> windowedIterator(iterator: Iterator<T>, size: Int, step: Int, partialWindows: Boolean, reuseBuffer: Boolean): Iterator<List<T>> {
   return (java.util.Iterator<java.util.List<T>>)(if (!iterator.hasNext())
      EmptyIterator.INSTANCE
      else
      SequencesKt.iterator(new kotlin.collections.SlidingWindowKt.windowedIterator.1(size, step, iterator, reuseBuffer, partialWindows, null)));
}
