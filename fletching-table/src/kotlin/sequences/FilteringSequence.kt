package kotlin.sequences

import kotlin.sequences.FilteringSequence.iterator.1

internal class FilteringSequence<T>(sequence: Sequence<Any>, sendWhen: Boolean = true, predicate: (Any) -> Boolean) : Sequence<T> {
   private final val sequence: Sequence<Any>
   private final val sendWhen: Boolean
   private final val predicate: (Any) -> Boolean

   init {
      this.sequence = sequence;
      this.sendWhen = sendWhen;
      this.predicate = predicate;
   }

   public override operator fun iterator(): Iterator<Any> {
      return new 1(this);
   }
}
