package kotlin.sequences

import java.util.ArrayList
import kotlin.internal.InlineOnly
import kotlin.internal.LowPriorityInOverloadResolution
import kotlin.jvm.functions.Function0
import kotlin.random.Random
import kotlin.sequences.SequencesKt__SequencesKt.Sequence.1

internal class SequencesKt__SequencesKt : SequencesKt__SequencesJVMKt {
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence(crossinline iterator: () -> Iterator<T>): Sequence<T> {
      return new 1(iterator);
   }

   @JvmStatic
   public fun <T> Iterator<T>.asSequence(): Sequence<T> {
      return SequencesKt.constrainOnce(new kotlin.sequences.SequencesKt__SequencesKt.asSequence..inlined.Sequence.1(`$this$asSequence`));
   }

   @JvmStatic
   public fun <T> sequenceOf(vararg elements: T): Sequence<T> {
      return (Sequence<T>)ArraysKt.asSequence(elements);
   }

   @SinceKotlin(version = "2.2")
   @JvmStatic
   public fun <T> sequenceOf(element: T): Sequence<T> {
      return new kotlin.sequences.SequencesKt__SequencesKt.sequenceOf..inlined.Sequence.1(element);
   }

   @SinceKotlin(version = "2.2")
   @InlineOnly
   @JvmStatic
   public inline fun <T> sequenceOf(): Sequence<T> {
      return SequencesKt.emptySequence();
   }

   @JvmStatic
   public fun <T> emptySequence(): Sequence<T> {
      return EmptySequence.INSTANCE;
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>?.orEmpty(): Sequence<T> {
      var var10000: Sequence = `$this$orEmpty`;
      if (`$this$orEmpty` == null) {
         var10000 = SequencesKt.emptySequence();
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun <T> Sequence<T>.ifEmpty(defaultValue: () -> Sequence<T>): Sequence<T> {
      return SequencesKt.sequence(new kotlin.sequences.SequencesKt__SequencesKt.ifEmpty.1(`$this$ifEmpty`, defaultValue, null));
   }

   @JvmStatic
   public fun <T> Sequence<Sequence<T>>.flatten(): Sequence<T> {
      return flatten$SequencesKt__SequencesKt(`$this$flatten`, SequencesKt__SequencesKt::flatten$lambda$0$SequencesKt__SequencesKt);
   }

   @JvmName(name = "flattenSequenceOfIterable")
   @JvmStatic
   public fun <T> Sequence<Iterable<T>>.flatten(): Sequence<T> {
      return flatten$SequencesKt__SequencesKt(`$this$flatten`, SequencesKt__SequencesKt::flatten$lambda$1$SequencesKt__SequencesKt);
   }

   @JvmStatic
   private fun <T, R> Sequence<T>.flatten(iterator: (T) -> Iterator<R>): Sequence<R> {
      return (Sequence<R>)(if (`$this$flatten` is TransformingSequence)
         (`$this$flatten` as TransformingSequence).flatten$kotlin_stdlib(iterator)
         else
         new FlatteningSequence<>(`$this$flatten`, SequencesKt__SequencesKt::flatten$lambda$2$SequencesKt__SequencesKt, iterator));
   }

   @JvmStatic
   public fun <T, R> Sequence<Pair<T, R>>.unzip(): Pair<List<T>, List<R>> {
      val listT: ArrayList = new ArrayList();
      val listR: ArrayList = new ArrayList();

      for (Pair pair : $this$unzip) {
         listT.add(pair.getFirst());
         listR.add(pair.getSecond());
      }

      return TuplesKt.to(listT, listR);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T> Sequence<T>.shuffled(): Sequence<T> {
      return SequencesKt.shuffled(`$this$shuffled`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T> Sequence<T>.shuffled(random: Random): Sequence<T> {
      return SequencesKt.sequence(new kotlin.sequences.SequencesKt__SequencesKt.shuffled.1(`$this$shuffled`, random, null));
   }

   @JvmStatic
   internal fun <T, C, R> flatMapIndexed(source: Sequence<T>, transform: (Int, T) -> C, iterator: (C) -> Iterator<R>): Sequence<R> {
      return SequencesKt.sequence(new kotlin.sequences.SequencesKt__SequencesKt.flatMapIndexed.1(source, transform, iterator, null));
   }

   @JvmStatic
   public fun <T> Sequence<T>.constrainOnce(): Sequence<T> {
      return (Sequence<T>)(if (`$this$constrainOnce` is ConstrainedOnceSequence) `$this$constrainOnce` else new ConstrainedOnceSequence(`$this$constrainOnce`));
   }

   @JvmStatic
   public fun <T : Any> generateSequence(nextFunction: () -> T?): Sequence<T> {
      return (Sequence<T>)SequencesKt.constrainOnce(
         new GeneratorSequence<>(nextFunction, SequencesKt__SequencesKt::generateSequence$lambda$0$SequencesKt__SequencesKt)
      );
   }

   @LowPriorityInOverloadResolution
   @JvmStatic
   public fun <T : Any> generateSequence(seed: T?, nextFunction: (T) -> T?): Sequence<T> {
      return (Sequence<T>)(if (seed == null)
         EmptySequence.INSTANCE
         else
         new GeneratorSequence(SequencesKt__SequencesKt::generateSequence$lambda$1$SequencesKt__SequencesKt, nextFunction));
   }

   @JvmStatic
   public fun <T : Any> generateSequence(seedFunction: () -> T?, nextFunction: (T) -> T?): Sequence<T> {
      return new GeneratorSequence(seedFunction, nextFunction);
   }

   @JvmStatic
   fun `flatten$lambda$0$SequencesKt__SequencesKt`(it: Sequence): java.util.Iterator {
      return it.iterator();
   }

   @JvmStatic
   fun `flatten$lambda$1$SequencesKt__SequencesKt`(it: java.lang.Iterable): java.util.Iterator {
      return it.iterator();
   }

   @JvmStatic
   fun `flatten$lambda$2$SequencesKt__SequencesKt`(it: Any): Any {
      return it;
   }

   @JvmStatic
   fun `generateSequence$lambda$0$SequencesKt__SequencesKt`(`$nextFunction`: Function0, it: Any): Any {
      return `$nextFunction`.invoke();
   }

   @JvmStatic
   fun `generateSequence$lambda$1$SequencesKt__SequencesKt`(`$seed`: Any): Any {
      return `$seed`;
   }

   open fun SequencesKt__SequencesKt() {
   }
}
