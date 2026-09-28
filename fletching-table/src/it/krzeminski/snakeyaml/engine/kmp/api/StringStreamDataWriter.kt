package it.krzeminski.snakeyaml.engine.kmp.api

import okio.Buffer

internal class StringStreamDataWriter(buffer: Buffer = new Buffer()) : StreamDataWriter {
   private final val buffer: Buffer

   init {
      this.buffer = buffer;
   }

   public override fun flush() {
      this.buffer.flush();
   }

   public override fun write(str: String) {
      this.buffer.writeUtf8(str);
   }

   public override fun write(str: String, off: Int, len: Int) {
      this.buffer.writeUtf8(str, off, off + len);
   }

   public override fun toString(): String {
      return this.buffer.peek().readUtf8();
   }

   fun StringStreamDataWriter() {
      this(null, 1, null);
   }
}
