package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.api.LoadCommon.loadAll..inlined.Iterable.1
import it.krzeminski.snakeyaml.engine.kmp.composer.Composer
import it.krzeminski.snakeyaml.engine.kmp.constructor.BaseConstructor
import it.krzeminski.snakeyaml.engine.kmp.parser.ParserImpl
import it.krzeminski.snakeyaml.engine.kmp.scanner.StreamReader
import okio.Buffer
import okio.Source

internal class LoadCommon(settings: LoadSettings, constructor: BaseConstructor) {
   private final val settings: LoadSettings
   private final val constructor: BaseConstructor

   init {
      this.settings = settings;
      this.constructor = constructor;
   }

   private fun createComposer(source: Source): Composer {
      return new Composer(this.settings, new ParserImpl(this.settings, new StreamReader(this.settings, new YamlUnicodeReader(source))));
   }

   private fun createComposer(string: String): Composer {
      return this.createComposer(new Buffer().writeUtf8(string));
   }

   private fun loadOne(composer: Composer): Any? {
      return this.constructor.constructSingleDocument(composer.getSingleNode());
   }

   public fun loadOne(string: String): Any? {
      return this.loadOne(this.createComposer(string));
   }

   public fun loadOne(source: Source): Any? {
      return this.loadOne(this.createComposer(source));
   }

   private fun loadAll(composer: Composer): Iterable<Any?> {
      return new 1(composer, this);
   }

   public fun loadAll(string: String): Iterable<Any?> {
      return this.loadAll(this.createComposer(string));
   }

   public fun loadAll(source: Source): Iterable<Any?> {
      return this.loadAll(this.createComposer(source));
   }
}
