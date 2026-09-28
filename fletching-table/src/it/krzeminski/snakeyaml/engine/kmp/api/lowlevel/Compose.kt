package it.krzeminski.snakeyaml.engine.kmp.api.lowlevel

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import java.io.InputStream
import java.io.Reader
import okio.Okio
import okio.Source

public class Compose(settings: LoadSettings) {
   private final val common: ComposeCommon

   init {
      this.common = new ComposeCommon(settings);
   }

   public fun compose(source: Source): Node? {
      return this.common.compose(source);
   }

   public fun compose(string: String): Node? {
      return this.common.compose(string);
   }

   public fun composeAll(source: Source): Iterable<Node> {
      return this.common.composeAll(source);
   }

   public fun composeAll(string: String): Iterable<Node> {
      return this.common.composeAll(string);
   }

   public fun compose(inputStream: InputStream): Node? {
      return this.compose(Okio.source(inputStream));
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "compose(yaml)", imports = []))
   public fun composeInputStream(yaml: InputStream): Node? {
      return this.compose(yaml);
   }

   public fun compose(reader: Reader): Node? {
      return this.compose(TextStreamsKt.readText(reader));
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "compose(yaml)", imports = []))
   public fun composeReader(yaml: Reader): Node? {
      return this.compose(yaml);
   }

   public fun composeAll(inputStream: InputStream): Iterable<Node> {
      return this.composeAll(Okio.source(inputStream));
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "composeAll(yaml)", imports = []))
   public fun composeAllFromInputStream(yaml: InputStream): Iterable<Node> {
      return this.composeAll(yaml);
   }

   public fun composeAll(reader: Reader): Iterable<Node> {
      return this.composeAll(TextStreamsKt.readText(reader));
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "composeAll(yaml)", imports = []))
   public fun composeAllFromReader(yaml: Reader): Iterable<Node> {
      return this.composeAll(yaml);
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "compose(yaml)", imports = []))
   public fun composeString(yaml: String): Node? {
      return this.compose(yaml);
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "compose(yaml)", imports = []))
   public fun composeAllFromString(yaml: String): Iterable<Node> {
      return this.composeAll(yaml);
   }
}
