package kotlin.sequences

import kotlin.sequences.TransformingSequence.iterator.1

internal class TransformingSequence<T, R>(sequence: Sequence<Any>, transformer: (Any) -> Any) : Sequence<R> {
   private final val sequence: Sequence<Any>
   private final val transformer: (Any) -> Any

   init {
      this.sequence = sequence;
      this.transformer = transformer;
   }

   public override operator fun iterator(): Iterator<Any> {
      return new 1(this);
   }

   internal fun <E> flatten(iterator: (Any) -> Iterator<E>): Sequence<E> {
      return new FlatteningSequence<>(this.sequence, this.transformer, iterator);
   }
}
