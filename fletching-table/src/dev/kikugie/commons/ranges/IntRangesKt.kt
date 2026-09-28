package dev.kikugie.commons.ranges

public inline infix fun Int.extend(length: Int): IntRange {
   return new IntRange(`$this$extend`, `$this$extend` + length);
}

public inline infix fun IntRange.shl(n: Int): IntRange {
   return new IntRange(`$this$shl`.getFirst() - n, `$this$shl`.getLast() - n);
}

public inline infix fun IntRange.shr(n: Int): IntRange {
   return new IntRange(`$this$shr`.getFirst() + n, `$this$shr`.getLast() + n);
}

public infix fun IntRange.extend(other: IntRange): IntRange {
   return new IntRange(Math.min(`$this$extend`.getFirst(), other.getFirst()), Math.max(`$this$extend`.getLast(), other.getLast()));
}

public infix fun IntRange.merge(other: IntRange): IntRange {
   return if (CommonKt.overlaps(`$this$merge`, other)) extend(`$this$merge`, other) else IntRange.Companion.getEMPTY();
}

public infix fun IntRange.cross(other: IntRange): IntRange {
   return if (CommonKt.overlaps(`$this$cross`, other))
      new IntRange(Math.max(`$this$cross`.getFirst(), other.getFirst()), Math.min(`$this$cross`.getLast(), other.getLast()))
      else
      IntRange.Companion.getEMPTY();
}
