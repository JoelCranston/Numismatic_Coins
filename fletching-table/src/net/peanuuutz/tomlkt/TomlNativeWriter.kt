package net.peanuuutz.tomlkt

import java.io.Writer

public class TomlNativeWriter(nativeWriter: Writer) : AbstractTomlWriter {
   private final val nativeWriter: Writer

   init {
      this.nativeWriter = nativeWriter;
   }

   public override fun writeString(string: String) {
      this.nativeWriter.write(string);
   }

   public override fun writeChar(char: Char) {
      this.nativeWriter.write(var1);
   }
}
