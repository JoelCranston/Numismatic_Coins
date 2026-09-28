package it.krzeminski.snakeyaml.engine.kmp.api

import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.nio.charset.Charset

public open class YamlOutputStreamWriter(out: OutputStream, cs: Charset) : OutputStreamWriter(out, cs), StreamDataWriter {
   public open fun processIOException(e: IOException) {
      throw e;
   }

   public override fun flush() {
      try {
         StreamDataWriter.super.flush();
      } catch (var2: IOException) {
         this.processIOException(var2);
      }
   }

   public override fun write(str: String, off: Int, len: Int) {
      try {
         super.write(str, off, len);
      } catch (var5: IOException) {
         this.processIOException(var5);
      }
   }

   public override fun write(str: String) {
      try {
         super.write(str);
      } catch (var3: IOException) {
         this.processIOException(var3);
      }
   }
}
