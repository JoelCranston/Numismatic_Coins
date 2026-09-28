package net.peanuuutz.tomlkt

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder

@SubclassOptInRequired(markerClass = [TomlSpecific::class])
public interface TomlEncoder : Encoder {
   public val toml: Toml

   public abstract fun encodeTomlElement(value: TomlElement) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun encodeNotNullMark(`$this`: TomlEncoder) {
         TomlEncoder.access$encodeNotNullMark$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun beginCollection(`$this`: TomlEncoder, descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
         return TomlEncoder.access$beginCollection$jd(`$this`, descriptor, collectionSize);
      }

      @Deprecated
      @JvmStatic
      fun <T> encodeSerializableValue(`$this`: TomlEncoder, serializer: SerializationStrategy<? super T>, value: T) {
         TomlEncoder.access$encodeSerializableValue$jd(`$this`, serializer, value);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun <T> encodeNullableSerializableValue(`$this`: TomlEncoder, serializer: SerializationStrategy<? super T>, value: T?) {
         TomlEncoder.access$encodeNullableSerializableValue$jd(`$this`, serializer, value);
      }
   }
}
