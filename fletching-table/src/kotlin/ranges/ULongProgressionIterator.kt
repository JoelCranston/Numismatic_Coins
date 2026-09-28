package kotlin.ranges

import java.util.NoSuchElementException
import kotlin.jvm.internal.markers.KMappedMarker

@SinceKotlin(version = "1.3")
private class ULongProgressionIterator(first: ULong, last: ULong, step: Long) : ULongProgressionIterator(first, last, step),
   java.util.Iterator<ULong>,
   KMappedMarker {
   private final val finalElement: ULong
   private final var hasNext: Boolean
   private final val step: ULong
   private final var next: ULong

   fun ULongProgressionIterator(first: Long, last: Long, step: Long) {
      this.finalElement = last;
      this.hasNext = if (step > 0L) java.lang.Long.compareUnsigned(first, last) <= 0 else java.lang.Long.compareUnsigned(first, last) >= 0;
      this.step = ULong.constructor-impl(step);
      this.next = if (this.hasNext) first else this.finalElement;
   }

   public override operator fun hasNext(): Boolean {
      return this.hasNext;
   }

   public open operator fun next(): ULong {
      val value: Long = this.next;
      if (this.next == this.finalElement) {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         }

         this.hasNext = false;
      } else {
         this.next = ULong.constructor-impl(this.next + this.step);
      }

      return value;
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
