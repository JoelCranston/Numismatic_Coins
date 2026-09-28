package kotlin.sequences

import kotlin.jvm.internal.SourceDebugExtension
import kotlin.sequences.SubSequence.iterator.1

@SourceDebugExtension(["SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/SubSequence\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,731:1\n1#2:732\n*E\n"])
internal class SubSequence<T>(sequence: Sequence<Any>, startIndex: Int, endIndex: Int) : Sequence<T>, DropTakeSequence<T> {
   private final val sequence: Sequence<Any>
   private final val startIndex: Int
   private final val endIndex: Int

   private final val count: Int
      private final get() {
         return this.endIndex - this.startIndex;
      }


   init {
      this.sequence = sequence;
      this.startIndex = startIndex;
      this.endIndex = endIndex;
      if (this.startIndex < 0) {
         throw new IllegalArgumentException(("startIndex should be non-negative, but is ${this.startIndex}").toString());
      } else if (this.endIndex < 0) {
         throw new IllegalArgumentException(("endIndex should be non-negative, but is ${this.endIndex}").toString());
      } else if (this.endIndex < this.startIndex) {
         throw new IllegalArgumentException(("endIndex should be not less than startIndex, but was ${this.endIndex} < ${this.startIndex}").toString());
      }
   }

   public override fun drop(n: Int): Sequence<Any> {
      return (Sequence<T>)(if (n >= this.getCount()) SequencesKt.emptySequence() else new SubSequence<>(this.sequence, this.startIndex + n, this.endIndex));
   }

   public override fun take(n: Int): Sequence<Any> {
      return if (n >= this.getCount()) this else new SubSequence<>(this.sequence, this.startIndex, this.startIndex + n);
   }

   public override operator fun iterator(): Iterator<Any> {
      return new 1(this);
   }
}
