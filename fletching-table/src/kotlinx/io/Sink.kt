package kotlinx.io

public sealed interface Sink : RawSink {
   @InternalIoApi
   public val buffer: Buffer

   public abstract fun write(source: ByteArray, startIndex: Int = 0, endIndex: Int = var1.length) {
   }

   public abstract fun transferFrom(source: RawSource): Long {
   }

   public abstract fun write(source: RawSource, byteCount: Long) {
   }

   public abstract fun writeByte(byte: Byte) {
   }

   public abstract fun writeShort(short: Short) {
   }

   public abstract fun writeInt(int: Int) {
   }

   public abstract fun writeLong(long: Long) {
   }

   public abstract override fun flush() {
   }

   public abstract fun emit() {
   }

   @InternalIoApi
   public abstract fun hintEmit() {
   }
}
