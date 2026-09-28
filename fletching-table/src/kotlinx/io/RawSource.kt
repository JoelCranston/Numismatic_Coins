package kotlinx.io

public interface RawSource : AutoCloseable {
   public abstract fun readAtMostTo(sink: Buffer, byteCount: Long): Long {
   }

   public abstract override fun close() {
   }
}
