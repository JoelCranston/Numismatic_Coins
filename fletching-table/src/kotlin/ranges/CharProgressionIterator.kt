package kotlin.ranges

import java.util.NoSuchElementException
import kotlin.jvm.internal.Intrinsics

internal class CharProgressionIterator(first: Char, last: Char, step: Int) : CharIterator {
   public final val step: Int
   private final val finalElement: Int
   private final var hasNext: Boolean
   private final var next: Int

   init {
      this.step = step;
      this.finalElement = last;
      this.hasNext = if (this.step > 0) Intrinsics.compare((int)first, (int)last) <= 0 else Intrinsics.compare((int)first, (int)last) >= 0;
      this.next = if (this.hasNext) first else this.finalElement;
   }

   public override operator fun hasNext(): Boolean {
      return this.hasNext;
   }

   public override fun nextChar(): Char {
      val value: Int = this.next;
      if (this.next == this.finalElement) {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         }

         this.hasNext = false;
      } else {
         this.next = this.next + this.step;
      }

      return (char)value;
   }
}
