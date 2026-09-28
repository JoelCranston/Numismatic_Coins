package kotlin.ranges

@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
public interface OpenEndRange<T extends java.lang.Comparable<? super T>> {
   public val start: Any
   public val endExclusive: Any

   public open operator fun contains(value: Any): Boolean {
   }

   public open fun isEmpty(): Boolean {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <T extends java.lang.Comparable<? super T>> contains(`$this`: OpenEndRange<T>, value: T): Boolean {
         return value.compareTo(`$this`.getStart()) >= 0 && value.compareTo(`$this`.getEndExclusive()) < 0;
      }

      @JvmStatic
      fun <T extends java.lang.Comparable<? super T>> isEmpty(`$this`: OpenEndRange<T>): Boolean {
         return `$this`.getStart().compareTo(`$this`.getEndExclusive()) >= 0;
      }
   }
}
