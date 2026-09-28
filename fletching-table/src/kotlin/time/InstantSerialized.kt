package kotlin.time

import java.io.Externalizable
import java.io.ObjectInput
import java.io.ObjectOutput

@ExperimentalTime
private class InstantSerialized(epochSeconds: Long, nanosecondsOfSecond: Int) : Externalizable {
   public final var epochSeconds: Long
      internal set

   public final var nanosecondsOfSecond: Int
      internal set

   init {
      this.epochSeconds = epochSeconds;
      this.nanosecondsOfSecond = nanosecondsOfSecond;
   }

   public constructor() : this(0L, 0)
   public override fun writeExternal(output: ObjectOutput) {
      output.writeLong(this.epochSeconds);
      output.writeInt(this.nanosecondsOfSecond);
   }

   public override fun readExternal(input: ObjectInput) {
      this.epochSeconds = input.readLong();
      this.nanosecondsOfSecond = input.readInt();
   }

   private fun readResolve(): Any {
      return Instant.Companion.fromEpochSeconds(this.epochSeconds, this.nanosecondsOfSecond);
   }

   public companion object {
      private const val serialVersionUID: Long
   }
}
