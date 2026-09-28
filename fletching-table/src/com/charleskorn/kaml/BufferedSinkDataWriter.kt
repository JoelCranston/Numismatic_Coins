package com.charleskorn.kaml

import it.krzeminski.snakeyaml.engine.kmp.api.StreamDataWriter
import okio.BufferedSink

private class BufferedSinkDataWriter(sink: BufferedSink) : StreamDataWriter, AutoCloseable {
   public final val sink: BufferedSink

   init {
      this.sink = sink;
   }

   public override fun flush() {
      this.sink.flush();
   }

   public override fun write(str: String) {
      this.sink.writeUtf8(str);
   }

   public override fun write(str: String, off: Int, len: Int) {
      this.sink.writeUtf8(str, off, off + len);
   }

   public override fun close() {
      this.flush();
   }
}
