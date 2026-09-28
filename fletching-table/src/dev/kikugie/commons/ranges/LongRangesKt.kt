package dev.kikugie.commons.ranges

public inline infix fun Long.extend(length: Long): LongRange {
   return new LongRange(`$this$extend`, `$this$extend` + length);
}

public inline infix fun Long.extend(length: Int): LongRange {
   return new LongRange(`$this$extend`, `$this$extend` + length);
}

public inline infix fun LongRange.shl(n: Long): LongRange {
   return new LongRange(`$this$shl`.getFirst() - n, `$this$shl`.getLast() - n);
}

public inline infix fun LongRange.shl(n: Int): LongRange {
   return new LongRange(`$this$shl`.getFirst() - n, `$this$shl`.getLast() - n);
}

public inline infix fun LongRange.shr(n: Long): LongRange {
   return new LongRange(`$this$shr`.getFirst() + n, `$this$shr`.getLast() + n);
}

public inline infix fun LongRange.shr(n: Int): LongRange {
   return new LongRange(`$this$shr`.getFirst() + n, `$this$shr`.getLast() + n);
}

public infix fun LongRange.extend(other: LongRange): LongRange {
   return new LongRange(Math.min(`$this$extend`.getFirst(), other.getFirst()), Math.max(`$this$extend`.getLast(), other.getLast()));
}

public infix fun LongRange.merge(other: LongRange): LongRange {
   return if (CommonKt.overlaps(`$this$merge`, other)) extend(`$this$merge`, other) else LongRange.Companion.getEMPTY();
}

public infix fun LongRange.cross(other: LongRange): LongRange {
   return if (CommonKt.overlaps(`$this$cross`, other))
      new LongRange(Math.max(`$this$cross`.getFirst(), other.getFirst()), Math.min(`$this$cross`.getLast(), other.getLast()))
      else
      LongRange.Companion.getEMPTY();
}
