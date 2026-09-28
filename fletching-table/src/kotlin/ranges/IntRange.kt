package kotlin.ranges

public class IntRange(start: Int, endInclusive: Int) : IntProgression(start, endInclusive, 1), ClosedRange<Integer>, OpenEndRange<Integer> {
   public open val start: Int
      public open get() {
         return this.getFirst();
      }


   public open val endInclusive: Int
      public open get() {
         return this.getLast();
      }


   @Deprecated(
      message = "Can throw an exception when it's impossible to represent the value with Int type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw."
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public open val endExclusive: Int
      public open get() {
         if (this.getLast() == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
         } else {
            return this.getLast() + 1;
         }
      }


   public open operator fun contains(value: Int): Boolean {
      return this.getFirst() <= value && value <= this.getLast();
   }

   public override fun isEmpty(): Boolean {
      return this.getFirst() > this.getLast();
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is IntRange
         && (
            this.isEmpty() && (other as IntRange).isEmpty()
               || this.getFirst() == (other as IntRange).getFirst() && this.getLast() == (other as IntRange).getLast()
         );
   }

   public override fun hashCode(): Int {
      return if (this.isEmpty()) -1 else 31 * this.getFirst() + this.getLast();
   }

   public override fun toString(): String {
      return "${this.getFirst()}..${this.getLast()}";
   }

   public companion object {
      public final val EMPTY: IntRange
   }
}
