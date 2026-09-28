package it.krzeminski.snakeyaml.engine.kmp.api.lowlevel

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.api.lowlevel.ComposeString.composeAll..inlined.Iterable.1
import it.krzeminski.snakeyaml.engine.kmp.composer.Composer
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.parser.ParserImpl
import it.krzeminski.snakeyaml.engine.kmp.scanner.StreamReader

/** @deprecated */
@Deprecated(message = "Converted to common it.krzeminski.snakeyaml.engine.kmp.api.lowlevel.Compose", replaceWith = @ReplaceWith(expression = "it.krzeminski.snakeyaml.engine.kmp.api.lowlevel.Compose", imports = []))
public class ComposeString(settings: LoadSettings) {
   private final val settings: LoadSettings

   init {
      this.settings = settings;
   }

   public fun compose(string: String): Node? {
      return this.composer(string).getSingleNode();
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "compose(yaml)", imports = []))
   public fun composeString(yaml: String): Node? {
      return this.compose(yaml);
   }

   public fun composeAll(string: String): Iterable<Node> {
      return new 1(this, string);
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "compose(yaml)", imports = []))
   public fun composeAllFromString(yaml: String): Iterable<Node> {
      return this.composeAll(yaml);
   }

   private fun composer(yaml: String): Composer {
      return new Composer(this.settings, new ParserImpl(this.settings, new StreamReader(this.settings, yaml)));
   }
}
