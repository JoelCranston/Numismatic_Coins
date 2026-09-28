package net.peanuuutz.tomlkt

public class TomlStringReader(source: String) : TomlReader {
   private final val source: String
   private final val length: Int
   private final var currentIndex: Int

   init {
      this.source = source;
      this.length = this.source.length();
   }

   public override fun read(): Int {
      return if (this.currentIndex >= this.length) -1 else this.source.charAt(this.currentIndex++);
   }
}
