package kotlin.sequences

import kotlin.sequences.MergingSequence.iterator.1

internal class MergingSequence<T1, T2, V>(sequence1: Sequence<Any>, sequence2: Sequence<Any>, transform: (Any, Any) -> Any) : Sequence<V> {
   private final val sequence1: Sequence<Any>
   private final val sequence2: Sequence<Any>
   private final val transform: (Any, Any) -> Any

   init {
      this.sequence1 = sequence1;
      this.sequence2 = sequence2;
      this.transform = transform;
   }

   public override operator fun iterator(): Iterator<Any> {
      return new 1(this);
   }
}
