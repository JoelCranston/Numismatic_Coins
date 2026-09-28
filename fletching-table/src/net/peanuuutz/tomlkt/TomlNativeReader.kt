package net.peanuuutz.tomlkt

import java.io.Reader

public class TomlNativeReader(nativeReader: Reader) : TomlReader {
   private final val nativeReader: Reader

   init {
      this.nativeReader = nativeReader;
   }

   public override fun read(): Int {
      return this.nativeReader.read();
   }
}
