package kotlin.sequences

import kotlin.sequences.IndexingSequence.iterator.1

internal class IndexingSequence<T>(sequence: Sequence<Any>) : Sequence<IndexedValue<? extends T>> {
   private final val sequence: Sequence<Any>

   init {
      this.sequence = sequence;
   }

   public override operator fun iterator(): Iterator<IndexedValue<Any>> {
      return new 1(this);
   }
}
