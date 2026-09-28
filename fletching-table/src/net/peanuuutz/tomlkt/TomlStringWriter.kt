package net.peanuuutz.tomlkt

public class TomlStringWriter : AbstractTomlWriter {
   private final val builder: StringBuilder = new StringBuilder()

   public override fun writeString(string: String) {
      this.builder.append(string);
   }

   public override fun writeChar(char: Char) {
      this.builder.append(var1);
   }

   public override fun toString(): String {
      val var10000: java.lang.String = this.builder.toString();
      return var10000;
   }
}
