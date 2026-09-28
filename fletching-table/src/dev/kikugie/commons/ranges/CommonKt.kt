package dev.kikugie.commons.ranges

public inline fun bind(pos: Int, range: IntRange, message: () -> String) {
   if (pos > range.getLast() || range.getFirst() > pos) {
      throw new IndexOutOfBoundsException(message.invoke() as java.lang.String);
   }
}

public infix fun <T : Comparable<T>> ClosedRange<T>.overlaps(other: ClosedRange<T>): Boolean {
   return `$this$overlaps`.contains(other.getStart()) || `$this$overlaps`.contains(other.getEndInclusive());
}

public operator fun <T : Comparable<T>> ClosedRange<T>.contains(other: ClosedRange<T>): Boolean {
   return `$this$contains`.contains(other.getStart()) && `$this$contains`.contains(other.getEndInclusive());
}
