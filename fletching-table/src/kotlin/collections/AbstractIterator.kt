package kotlin.collections

import java.util.NoSuchElementException
import kotlin.jvm.internal.markers.KMappedMarker

public abstract class AbstractIterator<T> : java.util.Iterator<T>, KMappedMarker {
   private final var state: Int
   private final var nextValue: Any?

   public override operator fun hasNext(): Boolean {
      var var10000: Boolean;
      switch (this.state) {
         case 0:
            var10000 = this.tryToComputeNext();
            break;
         case 1:
            var10000 = true;
            break;
         case 2:
            var10000 = false;
            break;
         default:
            throw new IllegalArgumentException("hasNext called when the iterator is in the FAILED state.");
      }

      return var10000;
   }

   public override operator fun next(): Any {
      if (this.state == 1) {
         this.state = 0;
         return this.nextValue;
      } else if (this.state != 2 && this.tryToComputeNext()) {
         this.state = 0;
         return this.nextValue;
      } else {
         throw new NoSuchElementException();
      }
   }

   private fun tryToComputeNext(): Boolean {
      this.state = 3;
      this.computeNext();
      return this.state == 1;
   }

   protected abstract fun computeNext() {
   }

   protected fun setNext(value: Any) {
      this.nextValue = (T)value;
      this.state = 1;
   }

   protected fun done() {
      this.state = 2;
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
