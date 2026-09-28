package kotlin.comparisons

import java.util.Comparator
import kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareBy.2
import kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareBy.3
import kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareByDescending.1
import kotlin.internal.InlineOnly
import kotlin.jvm.functions.Function1

internal class ComparisonsKt__ComparisonsKt {
   @JvmStatic
   public fun <T> compareValuesBy(a: T, b: T, vararg selectors: (T) -> Comparable<*>?): Int {
      if (selectors.length <= 0) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      } else {
         return compareValuesByImpl$ComparisonsKt__ComparisonsKt(a, b, selectors);
      }
   }

   @JvmStatic
   private fun <T> compareValuesByImpl(a: T, b: T, selectors: Array<out (T) -> Comparable<*>?>): Int {
      for (Function1 fn : selectors) {
         val diff: Int = ComparisonsKt.compareValues(fn.invoke(a) as java.lang.Comparable, fn.invoke(b) as java.lang.Comparable);
         if (diff != 0) {
            return diff;
         }
      }

      return 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> compareValuesBy(a: T, b: T, selector: (T) -> Comparable<*>?): Int {
      return ComparisonsKt.compareValues(selector.invoke(a) as java.lang.Comparable, selector.invoke(b) as java.lang.Comparable);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T, K> compareValuesBy(a: T, b: T, comparator: Comparator<in K>, selector: (T) -> K): Int {
      return comparator.compare(selector.invoke(a), selector.invoke(b));
   }

   @JvmStatic
   public fun <T : Comparable<*>> compareValues(a: T?, b: T?): Int {
      if (a === b) {
         return 0;
      } else if (a == null) {
         return -1;
      } else {
         return if (b == null) 1 else a.compareTo(b);
      }
   }

   @JvmStatic
   public fun <T> compareBy(vararg selectors: (T) -> Comparable<*>?): Comparator<T> {
      if (selectors.length <= 0) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      } else {
         return ComparisonsKt__ComparisonsKt::compareBy$lambda$0$ComparisonsKt__ComparisonsKt;
      }
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> compareBy(crossinline selector: (T) -> Comparable<*>?): Comparator<T> {
      return new 2(selector);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T, K> compareBy(comparator: Comparator<in K>, crossinline selector: (T) -> K): Comparator<T> {
      return new 3(comparator, selector);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> compareByDescending(crossinline selector: (T) -> Comparable<*>?): Comparator<T> {
      return new 1(selector);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T, K> compareByDescending(comparator: Comparator<in K>, crossinline selector: (T) -> K): Comparator<T> {
      return new kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareByDescending.2(comparator, selector);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Comparator<T>.thenBy(crossinline selector: (T) -> Comparable<*>?): Comparator<T> {
      return new kotlin.comparisons.ComparisonsKt__ComparisonsKt.thenBy.1(`$this$thenBy`, selector);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T, K> Comparator<T>.thenBy(comparator: Comparator<in K>, crossinline selector: (T) -> K): Comparator<T> {
      return new kotlin.comparisons.ComparisonsKt__ComparisonsKt.thenBy.2(`$this$thenBy`, comparator, selector);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Comparator<T>.thenByDescending(crossinline selector: (T) -> Comparable<*>?): Comparator<T> {
      return new kotlin.comparisons.ComparisonsKt__ComparisonsKt.thenByDescending.1(`$this$thenByDescending`, selector);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T, K> Comparator<T>.thenByDescending(comparator: Comparator<in K>, crossinline selector: (T) -> K): Comparator<T> {
      return new kotlin.comparisons.ComparisonsKt__ComparisonsKt.thenByDescending.2(`$this$thenByDescending`, comparator, selector);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Comparator<T>.thenComparator(crossinline comparison: (T, T) -> Int): Comparator<T> {
      return new kotlin.comparisons.ComparisonsKt__ComparisonsKt.thenComparator.1(`$this$thenComparator`, comparison);
   }

   @JvmStatic
   public infix fun <T> Comparator<T>.then(comparator: Comparator<in T>): Comparator<T> {
      return ComparisonsKt__ComparisonsKt::then$lambda$0$ComparisonsKt__ComparisonsKt;
   }

   @JvmStatic
   public infix fun <T> Comparator<T>.thenDescending(comparator: Comparator<in T>): Comparator<T> {
      return ComparisonsKt__ComparisonsKt::thenDescending$lambda$0$ComparisonsKt__ComparisonsKt;
   }

   @JvmStatic
   public fun <T : Any> nullsFirst(comparator: Comparator<in T>): Comparator<T?> {
      return ComparisonsKt__ComparisonsKt::nullsFirst$lambda$0$ComparisonsKt__ComparisonsKt;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T : Comparable<T>> nullsFirst(): Comparator<T?> {
      return ComparisonsKt.nullsFirst(ComparisonsKt.naturalOrder());
   }

   @JvmStatic
   public fun <T : Any> nullsLast(comparator: Comparator<in T>): Comparator<T?> {
      return ComparisonsKt__ComparisonsKt::nullsLast$lambda$0$ComparisonsKt__ComparisonsKt;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T : Comparable<T>> nullsLast(): Comparator<T?> {
      return ComparisonsKt.nullsLast(ComparisonsKt.naturalOrder());
   }

   @JvmStatic
   public fun <T : Comparable<T>> naturalOrder(): Comparator<T> {
      val var10000: NaturalOrderComparator = NaturalOrderComparator.INSTANCE;
      return var10000;
   }

   @JvmStatic
   public fun <T : Comparable<T>> reverseOrder(): Comparator<T> {
      val var10000: ReverseOrderComparator = ReverseOrderComparator.INSTANCE;
      return var10000;
   }

   @JvmStatic
   public fun <T> Comparator<T>.reversed(): Comparator<T> {
      val var10000: Comparator;
      if (`$this$reversed` is ReversedComparator) {
         var10000 = (`$this$reversed` as ReversedComparator).getComparator();
      } else if (`$this$reversed` == NaturalOrderComparator.INSTANCE) {
         val var2: ReverseOrderComparator = ReverseOrderComparator.INSTANCE;
         var10000 = var2;
      } else if (`$this$reversed` == ReverseOrderComparator.INSTANCE) {
         val var3: NaturalOrderComparator = NaturalOrderComparator.INSTANCE;
         var10000 = var3;
      } else {
         var10000 = new ReversedComparator(`$this$reversed`);
      }

      return var10000;
   }

   @JvmStatic
   fun `compareBy$lambda$0$ComparisonsKt__ComparisonsKt`(`$selectors`: Array<Array<Function1>>, a: Any, b: Any): Int {
      return compareValuesByImpl$ComparisonsKt__ComparisonsKt(a, b, `$selectors`);
   }

   @JvmStatic
   fun `then$lambda$0$ComparisonsKt__ComparisonsKt`(`$this_then`: Comparator, `$comparator`: Comparator, a: Any, b: Any): Int {
      val previousCompare: Int = `$this_then`.compare(a, b);
      return if (previousCompare != 0) previousCompare else `$comparator`.compare(a, b);
   }

   @JvmStatic
   fun `thenDescending$lambda$0$ComparisonsKt__ComparisonsKt`(`$this_thenDescending`: Comparator, `$comparator`: Comparator, a: Any, b: Any): Int {
      val previousCompare: Int = `$this_thenDescending`.compare(a, b);
      return if (previousCompare != 0) previousCompare else `$comparator`.compare(b, a);
   }

   @JvmStatic
   fun `nullsFirst$lambda$0$ComparisonsKt__ComparisonsKt`(`$comparator`: Comparator, a: Any, b: Any): Int {
      return if (a === b) 0 else (if (a == null) -1 else (if (b == null) 1 else `$comparator`.compare(a, b)));
   }

   @JvmStatic
   fun `nullsLast$lambda$0$ComparisonsKt__ComparisonsKt`(`$comparator`: Comparator, a: Any, b: Any): Int {
      return if (a === b) 0 else (if (a == null) 1 else (if (b == null) -1 else `$comparator`.compare(a, b)));
   }

   open fun ComparisonsKt__ComparisonsKt() {
   }
}
