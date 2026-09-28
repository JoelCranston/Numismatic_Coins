package net.peanuuutz.tomlkt.internal.emitter

import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.TomlWriter

internal class TomlElementEmitter(toml: Toml, writer: TomlWriter) : AbstractTomlElementEmitter(toml, writer) {
   public override fun createArrayEmitter(array: TomlArray): AbstractTomlElementEmitter {
      return if (!array.isEmpty())
         new TomlBlockArrayEmitter(this, this.getToml().getConfig().getItemsPerLineInBlockArray())
         else
         new TomlInlineArrayEmitter(this);
   }

   public override fun createTableEmitter(table: TomlTable): AbstractTomlElementEmitter {
      return new TomlTableEmitter(this, CollectionsKt.emptyList());
   }
}
