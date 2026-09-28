package kotlin.sequences

import kotlin.sequences.GeneratorSequence.iterator.1

private class GeneratorSequence<T>(getInitialValue: () -> Any?, getNextValue: (Any) -> Any?) : Sequence<T> {
   private final val getInitialValue: () -> Any?
   private final val getNextValue: (Any) -> Any?

   init {
      this.getInitialValue = getInitialValue;
      this.getNextValue = getNextValue;
   }

   public override operator fun iterator(): Iterator<Any> {
      return new 1(this);
   }
}
