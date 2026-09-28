package kotlin.ranges

import java.util.NoSuchElementException
import kotlin.jvm.internal.markers.KMappedMarker

@SinceKotlin(version = "1.3")
private class UIntProgressionIterator(first: UInt, last: UInt, step: Int) : UIntProgressionIterator(first, last, step), java.util.Iterator<UInt>, KMappedMarker {
   private final val finalElement: UInt
   private final var hasNext: Boolean
   private final val step: UInt
   private final var next: UInt

   fun UIntProgressionIterator(first: Int, last: Int, step: Int) {
      this.finalElement = last;
      this.hasNext = if (step > 0) Integer.compareUnsigned(first, last) <= 0 else Integer.compareUnsigned(first, last) >= 0;
      this.step = UInt.constructor-impl(step);
      this.next = if (this.hasNext) first else this.finalElement;
   }

   public override operator fun hasNext(): Boolean {
      return this.hasNext;
   }

   public open operator fun next(): UInt {
      val value: Int = this.next;
      if (this.next == this.finalElement) {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         }

         this.hasNext = false;
      } else {
         this.next = UInt.constructor-impl(this.next + this.step);
      }

      return value;
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
