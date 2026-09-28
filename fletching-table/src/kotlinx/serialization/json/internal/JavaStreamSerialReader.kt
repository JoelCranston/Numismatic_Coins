package kotlinx.serialization.json.internal

import java.io.InputStream

internal class JavaStreamSerialReader(stream: InputStream) : InternalJsonReader {
   private final val reader: CharsetReader

   init {
      this.reader = new CharsetReader(stream, Charsets.UTF_8);
   }

   public override fun read(buffer: CharArray, bufferOffset: Int, count: Int): Int {
      return this.reader.read(buffer, bufferOffset, count);
   }

   public fun release() {
      this.reader.release();
   }
}
