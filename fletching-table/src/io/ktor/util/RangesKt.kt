package io.ktor.util

public operator fun LongRange.contains(other: LongRange): Boolean {
   return other.getFirst() >= `$this$contains`.getStart() && other.getLast() <= `$this$contains`.getEndInclusive();
}
