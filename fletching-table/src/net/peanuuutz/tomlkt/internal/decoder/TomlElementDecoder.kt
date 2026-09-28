package net.peanuuutz.tomlkt.internal.decoder

import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlElement

internal class TomlElementDecoder(toml: Toml, element: TomlElement) : AbstractTomlElementDecoder(toml) {
   public open val element: TomlElement

   init {
      this.element = element;
   }
}
