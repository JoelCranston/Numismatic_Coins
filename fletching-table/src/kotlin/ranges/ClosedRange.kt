package kotlin.ranges

public interface ClosedRange<T extends java.lang.Comparable<? super T>> {
   public val start: Any
   public val endInclusive: Any

   public open operator fun contains(value: Any): Boolean {
   }

   public open fun isEmpty(): Boolean {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <T extends java.lang.Comparable<? super T>> contains(`$this`: ClosedRange<T>, value: T): Boolean {
         return value.compareTo(`$this`.getStart()) >= 0 && value.compareTo(`$this`.getEndInclusive()) <= 0;
      }

      @JvmStatic
      fun <T extends java.lang.Comparable<? super T>> isEmpty(`$this`: ClosedRange<T>): Boolean {
         return `$this`.getStart().compareTo(`$this`.getEndInclusive()) > 0;
      }
   }
}
