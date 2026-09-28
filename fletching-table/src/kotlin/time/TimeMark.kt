package kotlin.time

@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = [ExperimentalTime::class])
public interface TimeMark {
   public abstract fun elapsedNow(): Duration {
   }

   public open operator fun plus(duration: Duration): TimeMark {
   }

   public open operator fun minus(duration: Duration): TimeMark {
   }

   public open fun hasPassedNow(): Boolean {
   }

   public open fun hasNotPassedNow(): Boolean {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun `plus-LRDsOJo`(`$this`: TimeMark, var1: Long): TimeMark {
         return new AdjustedTimeMark(`$this`, var1, null);
      }

      @JvmStatic
      fun `minus-LRDsOJo`(`$this`: TimeMark, var1: Long): TimeMark {
         return `$this`.plus-LRDsOJo(Duration.unaryMinus-UwyO8pc(var1));
      }

      @JvmStatic
      fun hasPassedNow(`$this`: TimeMark): Boolean {
         return !Duration.isNegative-impl(`$this`.elapsedNow-UwyO8pc());
      }

      @JvmStatic
      fun hasNotPassedNow(`$this`: TimeMark): Boolean {
         return Duration.isNegative-impl(`$this`.elapsedNow-UwyO8pc());
      }
   }
}
