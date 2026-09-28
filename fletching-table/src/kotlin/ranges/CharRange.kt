package kotlin.ranges

import kotlin.jvm.internal.Intrinsics

public class CharRange(start: Char, endInclusive: Char) : CharProgression(start, endInclusive, 1), ClosedRange<Character>, OpenEndRange<Character> {
   public open val start: Char
      public open get() {
         return this.getFirst();
      }


   public open val endInclusive: Char
      public open get() {
         return this.getLast();
      }


   @Deprecated(
      message = "Can throw an exception when it's impossible to represent the value with Char type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw."
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public open val endExclusive: Char
      public open get() {
         if (this.getLast() == '\uffff') {
            throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
         } else {
            return (char)(this.getLast() + 1);
         }
      }


   public open operator fun contains(value: Char): Boolean {
      return Intrinsics.compare(this.getFirst(), value) <= 0 && Intrinsics.compare(value, this.getLast()) <= 0;
   }

   public override fun isEmpty(): Boolean {
      return Intrinsics.compare(this.getFirst(), this.getLast()) > 0;
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is CharRange
         && (
            this.isEmpty() && (other as CharRange).isEmpty()
               || this.getFirst() == (other as CharRange).getFirst() && this.getLast() == (other as CharRange).getLast()
         );
   }

   public override fun hashCode(): Int {
      return if (this.isEmpty()) -1 else 31 * this.getFirst() + this.getLast();
   }

   public override fun toString(): String {
      return "${this.getFirst()}..${this.getLast()}";
   }

   public companion object {
      public final val EMPTY: CharRange
   }
}
