package kotlin.ranges

private open class ComparableRange<T extends java.lang.Comparable<? super T>>(start: Any, endInclusive: Any) : ClosedRange<T> {
   public open val start: Any
   public open val endInclusive: Any

   init {
      this.start = (T)start;
      this.endInclusive = (T)endInclusive;
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is ComparableRange
         && (
            this.isEmpty() && (other as ComparableRange).isEmpty()
               || this.getStart() == (other as ComparableRange).getStart() && this.getEndInclusive() == (other as ComparableRange).getEndInclusive()
         );
   }

   public override fun hashCode(): Int {
      return if (this.isEmpty()) -1 else 31 * this.getStart().hashCode() + this.getEndInclusive().hashCode();
   }

   public override fun toString(): String {
      return "${this.getStart()}..${this.getEndInclusive()}";
   }
}
