package net.peanuuutz.tomlkt.internal.decoder

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.modules.SerializersModule
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlDecoder

internal abstract class AbstractTomlDecoder : TomlDecoder {
   public final val toml: Toml

   public final val serializersModule: SerializersModule
      public final get() {
         return this.toml.getSerializersModule();
      }


   public final var currentDiscriminator: String?
      internal set

   open fun AbstractTomlDecoder(toml: Toml) {
      this.toml = toml;
   }

   public override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
      return (T)AbstractTomlDecoderKt.decodeSerializableValuePolymorphically(this, deserializer);
   }

   @ExperimentalSerializationApi
   override fun <T> decodeNullableSerializableValue(deserializer: DeserializationStrategy<? extends T>): T {
      return TomlDecoder.super.decodeNullableSerializableValue(deserializer);
   }
}
