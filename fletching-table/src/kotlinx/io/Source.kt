package kotlinx.io

public sealed interface Source : RawSource {
   @InternalIoApi
   public val buffer: Buffer

   public abstract fun exhausted(): Boolean {
   }

   public abstract fun require(byteCount: Long) {
   }

   public abstract fun request(byteCount: Long): Boolean {
   }

   public abstract fun readByte(): Byte {
   }

   public abstract fun readShort(): Short {
   }

   public abstract fun readInt(): Int {
   }

   public abstract fun readLong(): Long {
   }

   public abstract fun skip(byteCount: Long) {
   }

   public abstract fun readAtMostTo(sink: ByteArray, startIndex: Int = 0, endIndex: Int = var1.length): Int {
   }

   public abstract fun readTo(sink: RawSink, byteCount: Long) {
   }

   public abstract fun transferTo(sink: RawSink): Long {
   }

   public abstract fun peek(): Source {
   }
}
