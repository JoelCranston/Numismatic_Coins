package kotlin.sequences

import kotlin.sequences.TransformingIndexedSequence.iterator.1

internal class TransformingIndexedSequence<T, R>(sequence: Sequence<Any>, transformer: (Int, Any) -> Any) : Sequence<R> {
   private final val sequence: Sequence<Any>
   private final val transformer: (Int, Any) -> Any

   init {
      this.sequence = sequence;
      this.transformer = transformer;
   }

   public override operator fun iterator(): Iterator<Any> {
      return new 1(this);
   }
}
