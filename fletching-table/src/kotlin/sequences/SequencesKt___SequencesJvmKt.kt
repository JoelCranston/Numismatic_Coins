package kotlin.sequences

import java.math.BigDecimal
import java.math.BigInteger
import java.util.Comparator
import java.util.SortedSet
import java.util.TreeSet
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\n_SequencesJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _SequencesJvm.kt\nkotlin/sequences/SequencesKt___SequencesJvmKt\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,172:1\n1463#2,14:173\n1923#2,14:187\n*S KotlinDebug\n*F\n+ 1 _SequencesJvm.kt\nkotlin/sequences/SequencesKt___SequencesJvmKt\n*L\n89#1:173,14\n126#1:187,14\n*E\n"])
internal class SequencesKt___SequencesJvmKt : SequencesKt__SequencesKt {
   @JvmStatic
   public fun <R> Sequence<*>.filterIsInstance(klass: Class<R>): Sequence<R> {
      val var10000: Sequence = SequencesKt.filter(
         `$this$filterIsInstance`, SequencesKt___SequencesJvmKt::filterIsInstance$lambda$0$SequencesKt___SequencesJvmKt
      );
      return var10000;
   }

   @JvmStatic
   public fun <C : MutableCollection<in R>, R> Sequence<*>.filterIsInstanceTo(destination: C, klass: Class<R>): C {
      for (Object element : $this$filterIsInstanceTo) {
         if (klass.isInstance(element)) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T : Comparable<T>> Sequence<T>.toSortedSet(): SortedSet<T> {
      return SequencesKt.toCollection(`$this$toSortedSet`, new TreeSet()) as SortedSet<T>;
   }

   @JvmStatic
   public fun <T> Sequence<T>.toSortedSet(comparator: Comparator<in T>): SortedSet<T> {
      return SequencesKt.toCollection(`$this$toSortedSet`, new TreeSet(comparator)) as SortedSet<T>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.sumOf(selector: (T) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;

      for (Object element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.sumOf(selector: (T) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;

      for (Object element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @JvmStatic
   fun `filterIsInstance$lambda$0$SequencesKt___SequencesJvmKt`(`$klass`: Class, it: Any): Boolean {
      return `$klass`.isInstance(it);
   }

   open fun SequencesKt___SequencesJvmKt() {
   }
}
