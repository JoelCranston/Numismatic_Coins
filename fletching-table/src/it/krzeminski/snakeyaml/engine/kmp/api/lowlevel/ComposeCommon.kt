package it.krzeminski.snakeyaml.engine.kmp.api.lowlevel

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.api.lowlevel.ComposeCommon.composeAll..inlined.Iterable.1
import it.krzeminski.snakeyaml.engine.kmp.composer.Composer
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.parser.ParserImpl
import it.krzeminski.snakeyaml.engine.kmp.scanner.StreamReader
import okio.Buffer
import okio.Source

internal class ComposeCommon(settings: LoadSettings) {
   private final val settings: LoadSettings

   init {
      this.settings = settings;
   }

   public fun compose(source: Source): Node? {
      return this.composer(source).getSingleNode();
   }

   public fun compose(string: String): Node? {
      return this.compose(new Buffer().writeUtf8(string));
   }

   public fun composeAll(source: Source): Iterable<Node> {
      return new 1(this, source);
   }

   public fun composeAll(string: String): Iterable<Node> {
      return this.composeAll(new Buffer().writeUtf8(string));
   }

   private fun composer(source: Source): Composer {
      return new Composer(this.settings, new ParserImpl(this.settings, new StreamReader(this.settings, source)));
   }
}
