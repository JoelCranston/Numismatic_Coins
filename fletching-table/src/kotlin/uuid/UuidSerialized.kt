package kotlin.uuid

import java.io.Externalizable
import java.io.ObjectInput
import java.io.ObjectOutput

@ExperimentalUuidApi
private class UuidSerialized(mostSignificantBits: Long, leastSignificantBits: Long) : Externalizable {
   public final var mostSignificantBits: Long
      internal set

   public final var leastSignificantBits: Long
      internal set

   init {
      this.mostSignificantBits = mostSignificantBits;
      this.leastSignificantBits = leastSignificantBits;
   }

   public constructor() : this(0L, 0L)
   public override fun writeExternal(output: ObjectOutput) {
      output.writeLong(this.mostSignificantBits);
      output.writeLong(this.leastSignificantBits);
   }

   public override fun readExternal(input: ObjectInput) {
      this.mostSignificantBits = input.readLong();
      this.leastSignificantBits = input.readLong();
   }

   private fun readResolve(): Any {
      return Uuid.Companion.fromLongs(this.mostSignificantBits, this.leastSignificantBits);
   }

   public companion object {
      private const val serialVersionUID: Long
   }
}
