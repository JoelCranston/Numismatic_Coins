package net.peanuuutz.tomlkt.internal.encoder

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.modules.SerializersModule
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlEncoder

internal abstract class AbstractTomlEncoder : TomlEncoder {
   public final val toml: Toml

   public final val serializersModule: SerializersModule
      public final get() {
         return this.toml.getSerializersModule();
      }


   public final var currentDiscriminator: String?
      internal set

   open fun AbstractTomlEncoder(toml: Toml) {
      this.toml = toml;
   }

   public override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
      AbstractTomlEncoderKt.encodeSerializableValuePolymorphically(this, serializer, value);
   }

   @ExperimentalSerializationApi
   override fun encodeNotNullMark() {
      TomlEncoder.super.encodeNotNullMark();
   }

   override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
      return TomlEncoder.super.beginCollection(descriptor, collectionSize);
   }

   @ExperimentalSerializationApi
   override fun <T> encodeNullableSerializableValue(serializer: SerializationStrategy<? super T>, value: T?) {
      TomlEncoder.super.encodeNullableSerializableValue(serializer, value);
   }
}
