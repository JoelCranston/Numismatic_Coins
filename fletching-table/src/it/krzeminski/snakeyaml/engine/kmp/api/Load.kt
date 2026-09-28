package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.constructor.BaseConstructor
import it.krzeminski.snakeyaml.engine.kmp.constructor.StandardConstructor
import java.io.InputStream
import java.io.Reader
import okio.Okio
import okio.Source

public class Load @JvmOverloads  public constructor(settings: LoadSettings = new LoadSettings(
         null, null, null, null, null, null, 0, false, false, 0, false, null, null, false, 0, null, 65535, null
      ),
   constructor: BaseConstructor = (new StandardConstructor(settings)) as BaseConstructor
) {
   private final val settings: LoadSettings
   private final val constructor: BaseConstructor
   private final val common: LoadCommon

   init {
      this.settings = settings;
      this.constructor = constructor;
      this.common = new LoadCommon(this.settings, this.constructor);
   }

   public fun loadOne(string: String): Any? {
      return this.common.loadOne(string);
   }

   internal fun loadOne(source: Source): Any? {
      return this.common.loadOne(source);
   }

   public fun loadOne(inputStream: InputStream): Any? {
      return this.common.loadOne(Okio.source(inputStream));
   }

   public fun loadOne(reader: Reader): Any? {
      return this.common.loadOne(TextStreamsKt.readText(reader));
   }

   public fun loadAll(string: String): Iterable<Any?> {
      return this.common.loadAll(string);
   }

   internal fun loadAll(source: Source): Iterable<Any?> {
      return this.common.loadAll(source);
   }

   public fun loadAll(inputStream: InputStream): Iterable<Any?> {
      return this.common.loadAll(Okio.source(inputStream));
   }

   public fun loadAll(reader: Reader): Iterable<Any?> {
      return this.common.loadAll(TextStreamsKt.readText(reader));
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "loadAll(yamlStream)", imports = []))
   public fun loadAllFromInputStream(yamlStream: InputStream): Iterable<Any?> {
      return this.loadAll(yamlStream);
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "loadAll(yaml)", imports = []))
   public fun loadAllFromString(yaml: String): Iterable<Any?> {
      return this.loadAll(yaml);
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "loadAll(yamlReader)", imports = []))
   public fun loadAllFromReader(yamlReader: Reader): Iterable<Any?> {
      return this.loadAll(yamlReader);
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "loadOne(yamlStream)", imports = []))
   public fun loadFromInputStream(yamlStream: InputStream): Any? {
      return this.loadOne(yamlStream);
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "loadOne(yamlReader)", imports = []))
   public fun loadFromReader(yamlReader: Reader): Any? {
      return this.loadOne(yamlReader);
   }

   @Deprecated(message = "renamed", replaceWith = @ReplaceWith(expression = "loadOne(yamlReader)", imports = []))
   public fun loadFromString(yaml: String): Any? {
      return this.loadOne(yaml);
   }

   @JvmOverloads
   fun Load(settings: LoadSettings) {
      this(settings, null, 2, null);
   }

   @JvmOverloads
   fun Load() {
      this(null, null, 3, null);
   }
}
