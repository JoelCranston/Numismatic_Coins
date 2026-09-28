package net.peanuuutz.tomlkt

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.internal.TomlNullSerializer

@Serializable(with = TomlNullSerializer::class)
public object TomlNull : TomlElement() {
   public open val content: Nothing?
      public open get() {
         return null;
      }


   public override fun toString(): String {
      return "null";
   }

   public fun serializer(): KSerializer<TomlNull> {
      return TomlNullSerializer.INSTANCE;
   }
}
