package kotlin.sequences

import kotlin.sequences.DropWhileSequence.iterator.1

internal class DropWhileSequence<T>(sequence: Sequence<Any>, predicate: (Any) -> Boolean) : Sequence<T> {
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
