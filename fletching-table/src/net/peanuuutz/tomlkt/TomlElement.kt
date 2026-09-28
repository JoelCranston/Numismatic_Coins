package net.peanuuutz.tomlkt

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.internal.TomlElementSerializer

@Serializable(with = TomlElementSerializer::class)
public sealed class TomlElement protected constructor() {
   public abstract val content: Any?

   public abstract override fun toString(): String {
   }

   public companion object {
      public fun serializer(): KSerializer<TomlElement> {
         return TomlElementSerializer.INSTANCE;
      }
   }
}
