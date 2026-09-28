package it.krzeminski.snakeyaml.engine.kmp.api.lowlevel

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.api.lowlevel.ParseCommon.parse..inlined.Iterable.1
import it.krzeminski.snakeyaml.engine.kmp.events.Event
import okio.Buffer
import okio.Source

internal class ParseCommon(settings: LoadSettings) {
   private final val settings: LoadSettings

   init {
      this.settings = settings;
   }

   public fun parse(string: String): Iterable<Event> {
      return this.parse(new Buffer().writeUtf8(string));
   }

   public fun parse(source: Source): Iterable<Event> {
      return new 1(this, source);
   }
}
