package it.krzeminski.snakeyaml.engine.kmp.exceptions

import it.krzeminski.snakeyaml.engine.kmp.internal.utils.Character
import java.util.Locale

public class ReaderException(name: String, position: Int, codePoint: Int, message: String) : YamlEngineException(message) {
   public final val name: String
   public final val position: Int
   public final val codePoint: Int

   init {
      this.name = name;
      this.position = position;
      this.codePoint = codePoint;
   }

   public override fun toString(): String {
      val s: java.lang.String = StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(this.codePoint));
      var var10000: java.lang.String = Integer.toString(this.codePoint, CharsKt.checkRadix(16));
      var10000 = var10000.toUpperCase(Locale.ROOT);
      return StringsKt.trimIndent(
         "\n             unacceptable code point '$s' (0x$var10000) ${this.getMessage()}\n             in \"${this.name}\", position ${this.position}\n             "
      );
   }
}
