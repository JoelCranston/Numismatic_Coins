package com.charleskorn.kaml

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.SerializersModule

internal class YamlNullInput(nullValue: YamlNull, yaml: Yaml, context: SerializersModule, configuration: YamlConfiguration) : YamlInput(
      nullValue, yaml, context, configuration
   ) {
   public final val nullValue: YamlNull

   init {
      this.nullValue = nullValue;
   }

   public override fun decodeNotNullMark(): Boolean {
      return false;
   }

   public override fun decodeValue(): Any {
      throw new UnexpectedNullValueException(this.nullValue.getPath());
   }

   public override fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
      throw new UnexpectedNullValueException(this.nullValue.getPath());
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      throw new UnexpectedNullValueException(this.nullValue.getPath());
   }

   public override fun getCurrentLocation(): Location {
      return this.nullValue.getLocation();
   }

   public override fun getCurrentPath(): YamlPath {
      return this.nullValue.getPath();
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      return -1;
   }
}
