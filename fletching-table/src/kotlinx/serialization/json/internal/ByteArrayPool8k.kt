package kotlinx.serialization.json.internal

internal object ByteArrayPool8k : ByteArrayPoolBase {
   public fun take(): ByteArray {
      return super.take(8196);
   }

   public fun release(array: ByteArray) {
      this.releaseImpl(array);
   }
}
