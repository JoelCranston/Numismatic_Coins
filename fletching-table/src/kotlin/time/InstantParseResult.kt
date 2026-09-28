package kotlin.time

@ExperimentalTime
private sealed interface InstantParseResult {
   public abstract fun toInstant(): Instant {
   }

   public abstract fun toInstantOrNull(): Instant? {
   }

   public class Failure(error: String, input: CharSequence) : InstantParseResult {
      public final val error: String
      public final val input: CharSequence

      init {
         this.error = error;
         this.input = input;
      }

      public override fun toInstant(): Instant {
         throw new InstantFormatException("${this.error} when parsing an Instant from \"${InstantKt.access$truncateForErrorMessage(this.input, 64)}"");
      }

      public override fun toInstantOrNull(): Instant? {
         return null;
      }
   }

   public class Success(epochSeconds: Long, nanosecondsOfSecond: Int) : InstantParseResult {
      public final val epochSeconds: Long
      public final val nanosecondsOfSecond: Int

      init {
         this.epochSeconds = epochSeconds;
         this.nanosecondsOfSecond = nanosecondsOfSecond;
      }

      public override fun toInstant(): Instant {
         if (this.epochSeconds >= Instant.Companion.getMIN$kotlin_stdlib().getEpochSeconds()
            && this.epochSeconds <= Instant.Companion.getMAX$kotlin_stdlib().getEpochSeconds()) {
            return Instant.Companion.fromEpochSeconds(this.epochSeconds, this.nanosecondsOfSecond);
         } else {
            throw new InstantFormatException("The parsed date is outside the range representable by Instant (Unix epoch second ${this.epochSeconds})");
         }
      }

      public override fun toInstantOrNull(): Instant? {
         return if (this.epochSeconds >= Instant.Companion.getMIN$kotlin_stdlib().getEpochSeconds()
               && this.epochSeconds <= Instant.Companion.getMAX$kotlin_stdlib().getEpochSeconds())
            Instant.Companion.fromEpochSeconds(this.epochSeconds, this.nanosecondsOfSecond)
            else
            null;
      }
   }
}
