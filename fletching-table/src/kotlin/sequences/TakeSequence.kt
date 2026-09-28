package kotlin.sequences

import kotlin.jvm.internal.SourceDebugExtension
import kotlin.sequences.TakeSequence.iterator.1

@SourceDebugExtension(["SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/TakeSequence\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,731:1\n1#2:732\n*E\n"])
internal class TakeSequence<T>(sequence: Sequence<Any>, count: Int) : Sequence<T>, DropTakeSequence<T> {
   private final val sequence: Sequence<Any>
   private final val count: Int

   init {
      this.sequence = sequence;
      this.count = count;
      if (this.count < 0) {
         throw new IllegalArgumentException(("count must be non-negative, but was ${this.count}.").toString());
      }
   }

   public override fun drop(n: Int): Sequence<Any> {
      return (Sequence<T>)(if (n >= this.count) SequencesKt.emptySequence() else new SubSequence<>(this.sequence, n, this.count));
   }

   public override fun take(n: Int): Sequence<Any> {
      return if (n >= this.count) this else new TakeSequence<>(this.sequence, n);
   }

   public override operator fun iterator(): Iterator<Any> {
      return new 1(this);
   }
}
