package kotlin.time

@SinceKotlin(version = "2.1")
@ExperimentalTime
public interface Clock {
   public abstract fun now(): Instant {
   }

   public companion object

   public object System : Clock {
      public override fun now(): Instant {
         return InstantJvmKt.systemClockNow();
      }
   }
}
