package it.krzeminski.snakeyaml.engine.kmp.api.lowlevel

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.events.Event
import java.io.InputStream
import java.io.Reader
import okio.Okio
import okio.Source

public class Parse(settings: LoadSettings) {
   private final val common: ParseCommon

   init {
      this.common = new ParseCommon(settings);
   }

   public fun parse(string: String): Iterable<Event> {
      return this.common.parse(string);
   }

   public fun parse(source: Source): Iterable<Event> {
      return this.common.parse(source);
   }

   public fun parse(reader: Reader): Iterable<Event> {
      return this.parse(TextStreamsKt.readText(reader));
   }

   public fun parse(inputStream: InputStream): Iterable<Event> {
      return this.parse(Okio.source(inputStream));
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "parse(yaml)", imports = []))
   public fun parseString(yaml: String): Iterable<Event> {
      return this.parse(yaml);
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "parse(yaml)", imports = []))
   public fun parseInputStream(yaml: InputStream): Iterable<Event> {
      return this.parse(yaml);
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "parse(yaml)", imports = []))
   public fun parseReader(yaml: Reader): Iterable<Event> {
      return this.parse(yaml);
   }
}
