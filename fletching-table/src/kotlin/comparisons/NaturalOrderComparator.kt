package kotlin.comparisons

import java.util.Comparator

private object NaturalOrderComparator : Comparator<java.lang.Comparable<? super Object>> {
   public open fun compare(a: Comparable<Any>, b: Comparable<Any>): Int {
      return a.compareTo(b);
   }

   public override fun reversed(): Comparator<Comparable<Any>> {
      return ReverseOrderComparator.INSTANCE;
   }
}
