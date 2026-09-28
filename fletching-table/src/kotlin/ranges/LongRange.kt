package kotlin.ranges

public class LongRange(start: Long, endInclusive: Long) : LongProgression(start, endInclusive, 1L), ClosedRange<java.lang.Long>, OpenEndRange<java.lang.Long> {
   public open val start: Long
      public open get() {
         return this.getFirst();
      }


   public open val endInclusive: Long
      public open get() {
         return this.getLast();
      }


   @Deprecated(
      message = "Can throw an exception when it's impossible to represent the value with Long type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw."
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public open val endExclusive: Long
      public open get() {
         if (this.getLast() == java.lang.Long.MAX_VALUE) {
            throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
         } else {
            return this.getLast() + 1L;
         }
      }


   public open operator fun contains(value: Long): Boolean {
      return this.getFirst() <= value && value <= this.getLast();
   }

   public override fun isEmpty(): Boolean {
      return this.getFirst() > this.getLast();
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is LongRange
         && (
            this.isEmpty() && (other as LongRange).isEmpty()
               || this.getFirst() == (other as LongRange).getFirst() && this.getLast() == (other as LongRange).getLast()
         );
   }

   public override fun hashCode(): Int {
      return if (this.isEmpty()) -1 else (int)(31 * (this.getFirst() xor this.getFirst() ushr 32) + (this.getLast() xor this.getLast() ushr 32));
   }

   public override fun toString(): String {
      return "${this.getFirst()}..${this.getLast()}";
   }

   public companion object {
      public final val EMPTY: LongRange
   }
}
