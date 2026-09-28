package kotlin.sequences

import java.util.HashSet

private class DistinctIterator<T, K>(source: Iterator<Any>, keySelector: (Any) -> Any) : AbstractIterator<T> {
   private final val source: Iterator<Any>
   private final val keySelector: (Any) -> Any
   private final val observed: HashSet<Any>

   init {
      this.source = source;
      this.keySelector = keySelector;
      this.observed = new HashSet<>();
   }

   protected override fun computeNext() {
      while (this.source.hasNext()) {
         val next: Any = this.source.next();
         if (this.observed.add(this.keySelector.invoke((T)next))) {
            this.setNext((T)next);
            return;
         }
      }

      this.done();
   }
}
