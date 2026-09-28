package net.peanuuutz.tomlkt

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encoding.Decoder

@SubclassOptInRequired(markerClass = [TomlSpecific::class])
public interface TomlDecoder : Decoder {
   public val toml: Toml

   public abstract fun decodeTomlElement(): TomlElement {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun <T> decodeSerializableValue(`$this`: TomlDecoder, deserializer: DeserializationStrategy<? extends T>): T {
         return (T)TomlDecoder.access$decodeSerializableValue$jd(`$this`, deserializer);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun <T> decodeNullableSerializableValue(`$this`: TomlDecoder, deserializer: DeserializationStrategy<? extends T>): T {
         return (T)TomlDecoder.access$decodeNullableSerializableValue$jd(`$this`, deserializer);
      }
   }
}
