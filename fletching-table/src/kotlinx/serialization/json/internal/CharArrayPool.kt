package kotlinx.serialization.json.internal

internal object CharArrayPool : CharArrayPoolBase {
   public fun take(): CharArray {
      return super.take(128);
   }

   public fun release(array: CharArray) {
      this.releaseImpl(array);
   }
}
