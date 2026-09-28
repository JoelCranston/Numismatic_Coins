package kotlinx.io

import java.io.Flushable

public interface RawSink : AutoCloseable, Flushable {
   public abstract fun write(source: Buffer, byteCount: Long) {
   }

   public abstract override fun flush() {
   }

   public abstract override fun close() {
   }
}
