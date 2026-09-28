package it.krzeminski.snakeyaml.engine.kmp.api.lowlevel

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.events.Event

/** @deprecated */
@Deprecated(message = "No longer used", replaceWith = @ReplaceWith(expression = "it.krzeminski.snakeyaml.engine.kmp.api.lowlevel.Parse", imports = []))
public class ParseString(settings: LoadSettings) {
   private final val parse: ParseCommon

   init {
      this.parse = new ParseCommon(settings);
   }

   public fun parseString(yaml: String): Iterable<Event> {
      return this.parse.parse(yaml);
   }
}
