package kotlin.sequences

import kotlin.sequences.TakeWhileSequence.iterator.1

internal class TakeWhileSequence<T>(sequence: Sequence<Any>, predicate: (Any) -> Boolean) : Sequence<T> {
   private final val sequence: Sequence<Any>
   private final val predicate: (Any) -> Boolean

   init {
      this.sequence = sequence;
      this.predicate = predicate;
   }

   public override operator fun iterator(): Iterator<Any> {
      return new 1(this);
   }
}
