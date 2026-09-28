package kotlin.collections

import java.util.NoSuchElementException
import kotlin.jvm.internal.markers.KMappedMarker

internal object EmptyIterator : java.util.ListIterator, KMappedMarker {
   public override operator fun hasNext(): Boolean {
      return false;
   }

   public override fun hasPrevious(): Boolean {
      return false;
   }

   public override fun nextIndex(): Int {
      return 0;
   }

   public override fun previousIndex(): Int {
      return -1;
   }

   public open operator fun next(): Nothing {
      throw new NoSuchElementException();
   }

   public open fun previous(): Nothing {
      throw new NoSuchElementException();
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun set(element: Void) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun add(element: Void) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
