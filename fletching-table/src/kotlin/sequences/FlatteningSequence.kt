package kotlin.sequences

import kotlin.sequences.FlatteningSequence.iterator.1

internal class FlatteningSequence<T, R, E>(sequence: Sequence<Any>, transformer: (Any) -> Any, iterator: (Any) -> Iterator<Any>) : Sequence<E> {
   private final val sequence: Sequence<Any>
   private final val transformer: (Any) -> Any
   private final val iterator: (Any) -> Iterator<Any>

   init {
      this.sequence = sequence;
      this.transformer = transformer;
      this.iterator = iterator;
   }

   public override operator fun iterator(): Iterator<Any> {
      return new 1(this);
   }

   private object State {
      public const val UNDEFINED: Int = 0
      public const val READY: Int = 1
      public const val DONE: Int = 2
   }
}
