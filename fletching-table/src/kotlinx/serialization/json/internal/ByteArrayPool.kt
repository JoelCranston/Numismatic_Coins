package kotlinx.serialization.json.internal

internal object ByteArrayPool : ByteArrayPoolBase {
   public fun take(): ByteArray {
      return super.take(512);
   }

   public fun release(array: ByteArray) {
      this.releaseImpl(array);
   }
}
